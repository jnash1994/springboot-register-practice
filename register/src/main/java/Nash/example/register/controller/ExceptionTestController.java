package Nash.example.register.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/excep")
public class ExceptionTestController {
    @GetMapping("/ex1")
    public String excep1(){
        int result = 5 / 0;
        return "success";
    }

    @GetMapping("/ex2")
    public String excep2() throws Exception {
        throw new Exception("抛出一个异常");
    }


//   @ExceptionHandler(value=Exception.class)
//    public String excepHandler(Exception e, Model model){
//        model.addAttribute("msg", e.getMessage());
//        return "excep";
//    }
}
