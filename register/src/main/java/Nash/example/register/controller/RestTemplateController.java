package Nash.example.register.controller;

import Nash.example.register.model.Company;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RestTemplateController {
    @GetMapping("/getForObjectFromMocki")
    public Company getForObjectFromMocki(){
        RestTemplate restTemplate=new RestTemplate();
        //利用 https://mocki.io/產生api 對照Stuednt
       Company company=restTemplate.getForObject(
                "https://mocki.io/v1/671a3451-14be-46ea-b1ed-e209d141cebf", Company.class);
        System.out.println(company.getName());
        System.out.println(company.getSize());
        System.out.println(company.getContactPhone());
        return company;
    }
    @GetMapping("/getForObjectWithParam")
    public Company getForObject(){
RestTemplate restTemplate=new RestTemplate();
Map<String,String> queryParamMap=new HashMap<>();
queryParamMap.put("name","Mike");
Company company=restTemplate.getForObject(
"http://localhost:8080/company?name={name}",Company.class,queryParamMap);

//Company company=restTemplate.getForObject(
//"http://localhost:8080/company?name=true",Company.class);

//        String ss=restTemplate.getForObject(
//"http://localhost:8080/com", String.class);

        return company;
    }
    @GetMapping("/getForEntity")//取出更完整
    public Company getForEntity(){
        RestTemplate restTemplate=new RestTemplate();
        String url="http://localhost:8080/company?name=Nash";
     ResponseEntity<Company> companyEntity=restTemplate.getForEntity(
             url,Company.class);
        Company company=companyEntity.getBody();
     System.out.println("http 狀態碼為 : "+companyEntity.getStatusCode());
     System.out.println(company.getName());
     System.out.println(company.getSize());
     return company;
 }
@GetMapping("/postForObject")
    public Company postForObject(){
    RestTemplate restTemplate=new RestTemplate();
    Company companyRequestBody= new Company();
    companyRequestBody.setName("Google");
    companyRequestBody.setSize(40);
    companyRequestBody.setContactPhone("wfwWfwfwfwf");
    String url="http://localhost:8080/company";
    Company company=restTemplate.postForObject(url,companyRequestBody,Company.class);
    return company;
    }
    @GetMapping("/postForEntity")
    public Company postForEntity(){
        RestTemplate restTemplate=new RestTemplate();
        Company companyRequestBody= new Company();
        companyRequestBody.setName("John");
        String url="http://localhost:8080/company";
        ResponseEntity<Company> responseEntity=restTemplate.postForEntity(url,companyRequestBody,Company.class);
        Company company=responseEntity.getBody();
        System.out.println("狀態碼為 : "+responseEntity.getStatusCode());
        return company;
    }

    @GetMapping("/exchange")//使用exchange發起get請求
    public Company exchangeGet(){
        RestTemplate restTemplate=new RestTemplate();
        HttpHeaders requestHeaders=new HttpHeaders();
        requestHeaders.set("header1","123");
        String url="http://localhost:8080/company?name={name}";

        HttpEntity requestEntity=new HttpEntity(requestHeaders);

        Map<String,String> queryParamMap=new HashMap<>();
        queryParamMap.put("name","Nash");

        ResponseEntity<Company> responseEntity= restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                Company.class,
                queryParamMap);
        Company company=responseEntity.getBody();
        return company;
    }

    @GetMapping("/exchangePost")//使用exchange發起post請求
    public Company exchangePost(){
        RestTemplate restTemplate=new RestTemplate();
        HttpHeaders requestHeaders=new HttpHeaders();
        requestHeaders.set("header1","456");
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        Company companyRequestBody=new Company();
        companyRequestBody.setName("Mike");
        String url="http://localhost:8080/company";
        HttpEntity requestEntity=new HttpEntity(companyRequestBody,requestHeaders);
        ResponseEntity<Company> responseEntity= restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                Company.class);
        Company company=responseEntity.getBody();
        return company;
    }




}
