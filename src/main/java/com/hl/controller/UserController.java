package com.hl.controller;

import com.hl.pojo.User;
import com.hl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String Login(String email, String password, Model model){
        HashMap hashMap = new HashMap();
        hashMap.put("email",email);
        User user = userService.queryUser(hashMap);
        if(user != null){
            if(user.getPassword().equals(password)){
                return "hello";
            }else {
                model.addAttribute("msg2","密码错误");
                model.addAttribute("email",email);
                model.addAttribute("password",password);
                return "login";
            }
        }else {
            model.addAttribute("msg1","用户名错误");
            model.addAttribute("email",email);
            model.addAttribute("password",password);
            return "login";
        }
    }

    @GetMapping("/queryEmail")
    @ResponseBody
    public Map queryEmail(String email){
        System.out.println("进入到queryEmail");
        System.out.println(email);
        HashMap hashMap = new HashMap();
        hashMap.put("email",email);
        if(email != null){
            User user = userService.queryUser(hashMap);
            if(user != null && user.getEmail().equals(email)){
                hashMap.put("infor","error");
                return hashMap;
            }
            hashMap.put("infor","success");
        }
        return hashMap;
    }

}
