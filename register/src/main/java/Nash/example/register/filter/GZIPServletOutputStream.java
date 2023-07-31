package Nash.example.register.filter;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class GZIPServletOutputStream extends ServletOutputStream {

    private GZIPOutputStream gzipos;

    public GZIPServletOutputStream(ServletOutputStream sos) throws IOException {
        //使用響應輸出串流物件建構GZIPOutputStream過濾串流物件
        this.gzipos = new GZIPOutputStream(sos);
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {

    }

    @Override
    public void write(int data) throws IOException {
        //將寫入操作委託給GZIPOutputStream物件的write()方法，從而實作響應輸出串流的壓縮
        gzipos.write(data);
    }

    public GZIPOutputStream getGZIPOutputStream() {
        return gzipos;
    }
}