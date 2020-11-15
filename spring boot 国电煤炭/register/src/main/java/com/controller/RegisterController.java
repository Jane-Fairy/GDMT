package com.controller;

import com.entity.ResultBean;
import com.service.RegisterService;
import com.util.VerifyCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;


/**
 * 用户注册Controller
 * @Author Harlan
 * @Date 2020/10/29
 */
@RestController
@SessionAttributes
public class RegisterController {

    /**
     * 用户名正则(邮箱格式)
     */
    private final String USERNAME_REGEX = "[a-zA-Z0-9_]{4,16}";
    /**
     * 密码正则(8-16, 数字, 大小写)
     */
    private final String PASSWORD_REGEX = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
    /**
     * 验证码长度
     */
    private final int VERIFY_CODE_LENGTH = 4;

    @Autowired
    private RegisterService registerService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    /**
     * 用户注册
     * @param username 用户名(邮箱)
     * @param password 密码
     * @param req request
     * @return 信息
     */
    @PostMapping("/register")
    @CrossOrigin(allowCredentials="true", allowedHeaders="*")
    public ResultBean<Boolean> registerUser(HttpServletRequest req, String username, String password, String vCode){

        //从redis中取出验证码
        String verifyCode = redisTemplate.opsForValue().get(req.getRemoteAddr());

        //验证码判断
        if (verifyCode == null){
            return new ResultBean<>(400, "请刷新验证码", false);
        }else if (vCode == null || vCode.length() < VERIFY_CODE_LENGTH){
            return new ResultBean<>(400, "请填写验证码", false);
        }else if (!verifyCode.equalsIgnoreCase(vCode)){
            return new ResultBean<>(400,"验证码错误", false);
        }

        //用户信息规则判断
        if (username == null || password == null || !username.matches(USERNAME_REGEX) || !password.matches(PASSWORD_REGEX)){
            return new ResultBean<>(400,"注册信息填写错误",false);
        }else {
            //用户名查重判断
            if (registerService.userNameExist(username)){
                return new ResultBean<>(400, "用户名已存在!", false);
            }
            boolean flag = registerService.registerUser(username, password);
            if (flag){
                redisTemplate.delete(req.getRemoteAddr());
                return new ResultBean<>(200,"注册成功", true);
            }
            return new ResultBean<>(400,"注册失败", false);
        }
    }


    /**
     * 用户名存在判断
     * @param username 用户名
     * @return 信息
     */
    @GetMapping("/register/{username}")
    public ResultBean<Boolean> userNameExist(@PathVariable("username") String username){
        if (username == null || !username.matches(USERNAME_REGEX)){
            return new ResultBean<>(400, "非法用户名!", false);
        }else {
            boolean isExist = registerService.userNameExist(username);
            if (isExist){
                return new ResultBean<>(400, "用户名已存在!", false);
            }else {
                return new ResultBean<>(200, "用户名可用!", true);
            }
        }
    }


    /**
     * 验证验证码正确
     * @param req req
     * @param vCode 输入的验证
     * @return 结果信息
     */
    @GetMapping("/verifyCode/{vCode}")
    public ResultBean<Boolean> verifyCodeResult(HttpServletRequest req, @PathVariable("vCode") String vCode){
        //从redis中获取验证码
        String verifyCode = redisTemplate.opsForValue().get(req.getRemoteAddr());
        //验证码判断
        if (vCode == null || vCode.length() < VERIFY_CODE_LENGTH){
            return new ResultBean<>(400, "请填写验证码", false);
        }else if (verifyCode == null){
            return new ResultBean<>(400, "请刷新验证码", false);
        }else if (verifyCode.equalsIgnoreCase(vCode)){
            return new ResultBean<>(200,"验证码正确", true);
        }else {
            return new ResultBean<>(400, "验证码错误", false);
        }
    }

    /**
     * 用户密码修改
     * @param userId 用户id
     * @param password 用户密码
     * @return 修改结果
     */
    @PostMapping("/modify")
    public ResultBean<Boolean> modifyUser(Integer userId, String password){
        if (userId == null || password == null || !password.matches(PASSWORD_REGEX)){
            return new ResultBean<>(400,"参数错误",false);
        }else {
            if (registerService.existUserById(userId)){
                boolean flag = registerService.modifyPasswordById(userId, password);
                if (flag){
                    return new ResultBean<>(200, "修改成功", true);
                }else {
                    return new ResultBean<>(400, "修改失败", false);
                }
            }
            return new ResultBean<>(400,"用户不存在", false);
        }
    }

    /**
     * 验证码获取
     * @param response resp
     * @param request req
     */
    @GetMapping("/getVerifyCode")
    public void getVerificationCode(HttpServletResponse response, HttpServletRequest request) {
        String verifyCode = VerifyCode.getVerifyCode(request, response);
        if (verifyCode != null){
            //将验证码存入redis保存3分钟 (key:客户端ip地址 - value:验证码)
            redisTemplate.opsForValue().set(request.getRemoteAddr(), verifyCode, 60 * 3, TimeUnit.SECONDS);
        }
    }
}
