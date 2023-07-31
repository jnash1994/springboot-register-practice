package Nash.example.register.config;


import Nash.example.register.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration =
                registry.addInterceptor(new LoginInterceptor());
        // 所有路径都被拦截
        registration.addPathPatterns("/**");
        WebMvcConfigurer.super.addInterceptors(registry);
         //添加不拦截路径
        registration.excludePathPatterns(
                "/user/login",
                "/**/*.js",
                "/**/*.css",
                "/static/**");
    }
}
