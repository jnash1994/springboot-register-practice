package Nash.example.register.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

@WebFilter(urlPatterns = "/*", filterName = "compressionFilter")
public class CompressionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResp = (HttpServletResponse) servletResponse;

        String acceptEncodings = httpReq.getHeader("Accept-Encoding");
        if (acceptEncodings != null && acceptEncodings.indexOf("gzip") > -1) {
            // 得到響應物件的封裝類別物件
            CompressionResponseWrapper respWrapper = new CompressionResponseWrapper(
                    httpResp);

            // 設置Content-Encoding實體標頭，告訴瀏覽器實體正文採用了gzip壓縮編碼
            respWrapper.setHeader("Content-Encoding", "gzip");
            filterChain.doFilter(httpReq, respWrapper);

            //得到GZIPOutputStream输出串流物件
            GZIPOutputStream gzipos = respWrapper.getGZIPOutputStream();
            //呼叫GZIPOutputStream输出串流物件的finish()方法完成將壓縮資料寫入響應輸出的操作，
            // 無須關閉輸出串流
            gzipos.finish();
        } else {
            filterChain.doFilter(httpReq, httpResp);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}