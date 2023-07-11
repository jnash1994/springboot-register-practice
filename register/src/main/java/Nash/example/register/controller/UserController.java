package Nash.example.register.controller;


import Nash.example.register.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserController {


    @GetMapping("/register")
    public String doDefault(User user){
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid User user , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "register";
        }
        Date now = new Date();
        user.setRegDate(now);
        return "success";
    }
}