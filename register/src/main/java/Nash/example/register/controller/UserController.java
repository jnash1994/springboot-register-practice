package Nash.example.register.controller;


import Nash.example.register.model.Company;
import Nash.example.register.model.Student;
import Nash.example.register.model.User;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/check")
    public ResponseEntity<String> check(){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Hello World");
    }
    @PostMapping("/fortest/{id}")
    @ResponseBody
    public Student opo(@PathVariable int id){
        Student student=new Student();
        student.setName("幹");
        student.setAge(40);
        System.out.println(id);
        return student;

    }

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

    @GetMapping("/objectToJson")//物件轉換為json
    @ResponseBody
    public String objectToJson() throws JsonProcessingException {
        Student student=new Student();
        student.setAge(40);
        student.setName("喔是喔真的假的");
       ObjectMapper objectMapper=new ObjectMapper();
        String json=objectMapper.writeValueAsString(student);
        return json;

    }
    @GetMapping("/jsonToObject") //json轉為物件
    @ResponseBody
    public Student jsonToObject() throws JsonProcessingException {

        ObjectMapper objectMapper=new ObjectMapper();
        String json="{\"name\":\"喔是喔真的假的\",\"age\":40}";
        Student student=objectMapper.readValue(json,Student.class);
        return student;

    }



}