package Nash.example.register.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * 如果没有在Spring Boot的配置文件中使用
 * springfox.documentation.enabled=false关闭Swagger的功能，
 * 可以不用@EnableOpenApi注解
 */
@Configuration
@EnableOpenApi
@EnableWebMvc
public class Swagger3Configuration {
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("Nash.example.register.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    // 配置API的基本信息，这些信息会在API文档上显示
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("文章系统RESTful API文档")  // 文档名称
                .description("文章系统RESTful API文档")  // 文档说明
                .contact(new Contact("sun.com", "www.sun.com", "8888@sun.com")) // 联系人
                .termsOfServiceUrl("")  //服务条款
                .version("1.0")  // 版本
                .build();
    }
}
