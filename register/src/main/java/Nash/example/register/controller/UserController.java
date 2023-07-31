package Nash.example.register.controller;


import Nash.example.register.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;


@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/register")
    public String doDefault( User user){
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

    @GetMapping("/login")
    public String doLogin(){
        return "login";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, HttpSession session, Model model){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if("admin".equals(username) && "1234".equals(password)){
            // 验证通过后，在Session对象中保存用户名
            session.setAttribute("user", username);
            // 从请求对象中取出用户先前访问的页面的URI
            String originUri = request.getParameter("originUri");
            // 如果origin_uri不为空，则将客户端重定向到用户先前访问的页面，
            // 否则将客户端重定向到首页。
            if(null != originUri && !originUri.isEmpty()){
                return "redirect:" + originUri;
            } else {
                return "redirect:/index";
            }
        }else{
            // 如果验证失败，则从请求对象中获取用户先前访问页面的URI。
            // 如果该URI存在，则再次将它作为originUri属性的值保存到请求对象中。
            String originUri = request.getParameter("originUri");
            if(null != originUri && !originUri.isEmpty()){
                request.setAttribute("originUri", originUri);
            }
            model.addAttribute("error","用户名或密码错误！");
            return "login";
        }
    }

}
