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
    @PostMapping("/company")
    public Company postCompany(@RequestBody Company company){
        System.out.println(company.getSize());
        System.out.println(company.getContactPhone());
        System.out.println(company.getName());
        return  company;
    }
    @GetMapping("/list")
    public List<Company> returnList(){
        List<Company> stringList=new ArrayList<>();
        Company company1=new Company();
        company1.setName("kkkkkk");
        company1.setSize(11111);
        Company company2=new Company();
        company2.setSize(11111);
        Company company3=new Company();
        company3.setContactPhone("笑死電話");
        stringList.add(company1);
        stringList.add(company2);
        stringList.add(company3);

        return stringList;


    }



}
