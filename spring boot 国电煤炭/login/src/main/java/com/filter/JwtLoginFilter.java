package com.filter;

import com.config.RsaKeyProperties;
import com.entity.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utils.JsonUtils;
import com.utils.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {
    private RsaKeyProperties prop;
    private AuthenticationManager authenticationManager;
    private String flag;

    public JwtLoginFilter(AuthenticationManager authenticationManager, RsaKeyProperties prop) {
        this.prop = prop;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        flag=request.getHeader("flag");
        try {
            if (flag.equals("0")){
                Admin admin= new ObjectMapper().readValue(request.getInputStream(), Admin.class);
                UsernamePasswordAuthenticationToken authResult=new UsernamePasswordAuthenticationToken(admin.getAdminName(),admin.getAdminPassword());
                return authenticationManager.authenticate(authResult);
            }
            Users users=new ObjectMapper().readValue(request.getInputStream(), Users.class);
            UsernamePasswordAuthenticationToken authResult=new UsernamePasswordAuthenticationToken(users.getUserName(),users.getUserPassword());
            return authenticationManager.authenticate(authResult);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                //异常响应信息格式及编码
                response.setContentType("application/json;charset=utf-8");
                //设置响应状态码(未认证)
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                PrintWriter out=response.getWriter();
                ResultBean resultBean=new ResultBean(HttpServletResponse.SC_UNAUTHORIZED,"用户名或密码错误");
                out.write(JsonUtils.toString(resultBean));
                out.flush();
                out.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token=null;
        Admin admin=null;
        Users users=null;
       if (flag.equals("0")){
           admin=new Admin();
           admin.setAdminName(authResult.getName());
           List<AdminRole> roleList=new ArrayList<>();
           for (GrantedAuthority authority:authResult.getAuthorities()){
               AdminRole adminRole=new AdminRole();
               adminRole.setAdminRoleName(authority.getAuthority().substring(authority.getAuthority().indexOf("_")+1).trim());
               roleList.add(adminRole);
           }
           admin.setAdminRoles(roleList);
           token= JwtUtils.generateTokenExpireInMinutes(admin,prop.getPrivateKey(),24*60)+0;
       }else if (flag.equals("1")){
           users=new Users();
           users.setUserName(authResult.getName());
           List<UserRole> roles=new ArrayList<>();
           for (GrantedAuthority sga:authResult.getAuthorities()){
               UserRole role=new UserRole();
               role.setRoleName(sga.getAuthority().substring(sga.getAuthority().indexOf("_")+1).trim());
               roles.add(role);
           }
           users.setRoles(roles);
           //生成token（用户对象，私钥，过期时间）
            token= JwtUtils.generateTokenExpireInMinutes(users,prop.getPrivateKey(),24*60)+1;
       }
        response.setHeader("Auth","Prefix "+token);
        response.addHeader("Access-Control-Expose-Headers","Auth");
        try {
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            PrintWriter out=response.getWriter();
            if (flag.equals("0")){
                ResultBean resultBean=new ResultBean(HttpServletResponse.SC_OK,"认证成功",admin.getAdminName());
                out.write(new ObjectMapper().writeValueAsString(resultBean));
            }else if (flag.equals("1")){
                ResultBean resultBean=new ResultBean(HttpServletResponse.SC_OK,"认证成功",users.getUserName());
                out.write(new ObjectMapper().writeValueAsString(resultBean));
            }
            out.flush();
            out.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
