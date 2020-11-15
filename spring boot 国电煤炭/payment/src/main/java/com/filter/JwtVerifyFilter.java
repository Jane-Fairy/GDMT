package com.filter;

import com.config.RsaKeyProperties;
import com.entity.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utils.JsonUtils;
import com.utils.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class JwtVerifyFilter extends BasicAuthenticationFilter {

    private RsaKeyProperties prop;
    public JwtVerifyFilter(AuthenticationManager authenticationManager, RsaKeyProperties prop) {
        super(authenticationManager);
        this.prop=prop;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //获取对应头名称信息（写回token对应的头名称）,即获取带前缀的token
        String header=request.getHeader("Auth");
        //头名称信息不为空，且以写回token的前缀开头
        if(header!=null&&header.startsWith("Prefix ")){
            //判断是用户还是管理员
            String flag=header.substring(header.length()-1);
            //获取token
            String token=header.substring(7,header.length()-1).trim();
            UsernamePasswordAuthenticationToken authResult=null;
            if (flag.equals("0")){
                //获取token的载荷
                Payload<Admin> payload= JwtUtils.getInfoFromToken(token,prop.getPublicKey(), Admin.class);
                Admin admin=payload.getUserInfo();
                if (admin!=null){
                    List<SimpleGrantedAuthority> sga=new ArrayList<>();
                    for (AdminRole role:admin.getAdminRoles()){
                        sga.add(new SimpleGrantedAuthority("ROLE_"+role.getAdminRoleName()));
                    }
                    authResult=new UsernamePasswordAuthenticationToken(admin.getAdminName(),null,sga);
                }
            }else if (flag.equals("1")){
                //获取token的载荷
                Payload<Users> payload= JwtUtils.getInfoFromToken(token,prop.getPublicKey(), Users.class);
                Users users=payload.getUserInfo();
                if (users!=null){
                    List<SimpleGrantedAuthority> sga=new ArrayList<>();
                    for (UserRole role:users.getRoles()){
                        sga.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
                    }
                    authResult=new UsernamePasswordAuthenticationToken(users.getUserName(),null,sga);
                }
            }
//            //获取token的载荷
//            Payload<Admin> payload= JwtUtils.getInfoFromToken(token,prop.getPublicKey(), Admin.class);
//            Admin admin=payload.getUserInfo();
//            if (admin!=null){
//                List<SimpleGrantedAuthority> sga=new ArrayList<>();
//                for (AdminRole role:admin.getAdminRoles()){
//                    sga.add(new SimpleGrantedAuthority("ROLE_"+role.getAdminRoleName()));
//                }
//                UsernamePasswordAuthenticationToken authResult=new UsernamePasswordAuthenticationToken(admin.getAdminName(),null,sga);
                //将用户信息放入当前服务的session中
                SecurityContextHolder.getContext().setAuthentication(authResult);
                //进入下一步过滤器
                chain.doFilter(request,response);
        } else {
            try {
                chain.doFilter(request,response);
                //响应信息
                response.setContentType("application/json;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                PrintWriter out=response.getWriter();
                ResultBean resultBean=new ResultBean(HttpServletResponse.SC_FORBIDDEN,"权限不足");
                out.write(JsonUtils.toString(resultBean));
                out.flush();
                out.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
