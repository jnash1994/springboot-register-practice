package Nash.example.register.filter;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

public class CompressionResponseWrapper extends HttpServletResponseWrapper {

    private final GZIPServletOutputStream gzipsos;
    private final PrintWriter pw;

    public CompressionResponseWrapper(HttpServletResponse response)
            throws IOException {
        super(response);
        //用響應輸出串流建立GZIPOutputStream物件
        gzipsos = new GZIPServletOutputStream(response.getOutputStream());
        ////用GZIPServletOutputStream物件作為參數，建構PrintWriter
        pw = new PrintWriter(gzipsos);
    }

//    重寫setContentLength()方法，以避免Content-Length實體標頭所指出的長度
//    和壓縮後的實體正文長度不匹配
    @Override
    public void setContentLength(int len) {}

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return gzipsos;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return pw;
    }

    //篩檢程式呼叫這個方法來得到 GZIPOutputStream物件，以便完成
   // 將壓縮資料寫入輸出串流的操作
    public GZIPOutputStream getGZIPOutputStream() {
        return gzipsos.getGZIPOutputStream();
    }

}
