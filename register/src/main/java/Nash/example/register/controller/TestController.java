package Nash.example.register.controller;

import Nash.example.register.model.Company;
import Nash.example.register.model.Student;
import Nash.example.register.model.UserModel;
import Nash.example.register.model.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


@Controller
public class TestController {
    @Autowired
    private UserRepository userRepository;

    @Value("${name}")
    private  String sool;
    @RequestMapping("/test")
    public void test(HttpServletResponse response) throws IOException {
        response.addHeader("Content-Type","text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        out.write("Hello World");
        out.close();
    }

    @GetMapping("/view")
    public ModelAndView view(){
        List<Company> companies=new ArrayList<>();
        Company c1=new Company();
        c1.setName("google");
        c1.setContactPhone("0977849");
        c1.setSize(9);
        Company c2=new Company();
        c2.setName("cool");
        c2.setContactPhone("98766412");
        c2.setSize(19);
        Company c3=new Company();
        c3.setName("shit");
        c3.setContactPhone("123456");
        c3.setSize(39);
        companies.add(c1);
        companies.add(c2);
        companies.add(c3);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("msg","請都入");
        modelAndView.addObject("companies",companies);
        modelAndView.setViewName("modelview");
        return  modelAndView;
    }
    @GetMapping("/h")
    public String h(@RequestParam String name, Model model){
        model.addAttribute("name",name);
        System.out.println(name);
        return "user";
    }



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
    @GetMapping("/gg")
    public String save(){
        UserModel userModel=new UserModel();
        userModel.setUsername("fgqgqghh");
        userModel.setPassword("fgwegwegweg");
        userModel.setEmail("mmm@mmm");
        userRepository.save(userModel);
        return "sc";
    }



}
