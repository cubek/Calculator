package sk.cubi.calculator;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import sk.cubi.calculator.server.CalculatorServlet;

import javax.servlet.http.HttpServlet;
import java.io.File;

public class CalculatorApplication {

    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.setHostname("localhost");
        String appBase = ".";
        tomcat.getHost().setAppBase(appBase);
        File docBase = new File(System.getProperty("java.io.tmpdir"));
        Context context = tomcat.addContext("", docBase.getAbsolutePath());

        HttpServlet calculatorServlet = new CalculatorServlet();

        Tomcat.addServlet(context, "Embedded", calculatorServlet);
        context.addServletMapping("/my-servlet/*", "Embedded");

        tomcat.start();
        tomcat.getServer().await();
    }
}
