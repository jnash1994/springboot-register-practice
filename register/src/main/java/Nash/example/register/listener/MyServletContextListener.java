package Nash.example.register.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyServletContextListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("請求即將超出web應用程式的範圍");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("請求即將進入web應用程式的範圍");
    }
}

