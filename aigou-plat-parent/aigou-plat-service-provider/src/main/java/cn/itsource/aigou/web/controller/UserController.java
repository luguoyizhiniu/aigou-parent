package cn.itsource.aigou.web.controller;

import cn.itsource.aigou.commonutils.AjaxResult;
import cn.itsource.aigou.domain.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public AjaxResult login(@RequestBody User user){
        if ("admin".equals(user.getName()) && "admin".equals(user.getPassword())){
            return AjaxResult.me();
        }else {
            return AjaxResult.me().setSuccess(false).setMsg("登录失败").setObject("少侠请重新来过");
        }
    }
}
