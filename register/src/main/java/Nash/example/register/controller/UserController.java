package Nash.example.register.controller;


import Nash.example.register.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserController {


    @GetMapping("/register")
    public String doDefault(){
        return "register";
    }

    @PostMapping("/register")
    public String register(User user){
        Date now = new Date();
        user.setRegDate(now);
        return "success";
    }
}