package Nash.example.register.controller;

import Nash.example.register.model.Company;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CompanyController {
    @GetMapping("/com")
    public String geto(){
        return "cool";
    }
    @GetMapping("/company")
    public Company getCompany(@RequestParam String name){
       System.out.println("你搜尋的公司名為 : "+ name);
       Company company=new Company();
       company.setName(name);
        return company;

}




}
