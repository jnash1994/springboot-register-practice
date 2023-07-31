package Nash.example.register.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果访问的不是登录页面，则判断用户是否已经登录
        Object user = request.getSession().getAttribute("user");
        if (user == null) {
            // 如果用户没有登录，则将用户的请求URI作为originUri属性的值保存到请求对象中
            String requestUri = request.getRequestURI();

            String strQuery = request.getQueryString();
            if (null != strQuery) {
                requestUri = requestUri + "?" + strQuery;
            }
            request.setAttribute("originUri", requestUri);

            request.setAttribute("error", "您没有访问权限，请先登录！");
            request.getRequestDispatcher("/user/login").forward(request, response);

            return false;
        } else {
            // 已登录，返回true，将请求交由执行链继续处理
            return true;
        }
    }

}

