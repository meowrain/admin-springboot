package cn.meowrain.controller;

import cn.meowrain.pojo.Result;
import cn.meowrain.pojo.User;
import cn.meowrain.service.UserService;
import cn.meowrain.utils.JwtUtil;
import cn.meowrain.utils.Md5Util;
import cn.meowrain.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.apache.ibatis.annotations.Param;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username,@Pattern(regexp = "^\\S{5,16}$") String password){
        // 查询用户
        User user = userService.findByUserName(username);
        if(user == null) {
            //没有占用
            //完成注册
            userService.register(username,password);
            return Result.success();
        }else {
            //已经占用
            return Result.error("用户名被占用，注册失败！");
        }
    }

    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{5,16}$") String username,@Pattern(regexp = "^\\S{5,16}$") String password){
        User user = userService.findByUserName(username);
        if(user == null) {
            return Result.error("没有这个用户！");
        }
        if(Md5Util.getMD5String(password).equals(user.getPassword())){
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",user.getId());
            claims.put("username",user.getUsername());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }
        return Result.error("密码错误！");
    }

//    @GetMapping("/userInfo")
//    public Result<User> userInfo(@RequestHeader(name = "Authorization") String token) {
//        Map<String, Object> claims = JwtUtil.parseToken(token);
//        String username = (String) claims.get("username");
//        User user = userService.findByUserName(username);
//        return Result.success(user);
//    }
    @GetMapping("/userInfo")
    public Result<User> userInfo() {
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUserName(username);
        return Result.success(user);
    }

    @PutMapping("/update")
    public void update(@RequestBody @Validated User user){
        userService.update(user);
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl){
        userService.updateAvatarUrl(avatarUrl);
        return Result.success("操作成功！");
    }
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> params){
        //1.校验参数
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");
        if(!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)){
            return Result.error("缺少必要参数");
        }
        //校验原密码是否正确
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String)map.get("username");
        User user = userService.findByUserName(username);
        if(!Md5Util.getMD5String(oldPwd).equals(user.getPassword())){
            return Result.error("原密码错误");
        }
        if(!newPwd.equals(rePwd)){
            return Result.error("新输入两个密码不一致！");
        }
        userService.updatePwd(newPwd);
        return Result.success("密码修改成功！");
    }
}
