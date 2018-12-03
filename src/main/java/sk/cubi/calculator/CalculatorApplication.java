package sk.cubi.calculator;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import sk.cubi.calculator.db.CalculationDbFactory;
import sk.cubi.calculator.server.CalculatorServlet;

import javax.servlet.http.HttpServlet;
import java.io.File;
import java.util.Properties;

public class CalculatorApplication {

    public static void main(String[] args) throws LifecycleException, ClassNotFoundException {
        //setup db connection
        Properties props = new Properties();
        props.setProperty("dbHost", "localhost");
        props.setProperty("dbDriverName", "org.postgresql.Driver");
        for (String arg : args) {
            String key = arg.substring(0, arg.indexOf('='));
            String value = arg.substring(arg.indexOf('=') + 1);
            props.setProperty(key, value);
        }
        CalculationDbFactory.createDbConnector(props);

        //setup apache tomcat
        HttpServlet calculatorServlet = new CalculatorServlet();
        File docBase = new File(System.getProperty("java.io.tmpdir"));
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.setHostname("localhost");
        tomcat.getHost().setAppBase(".");
        Context context = tomcat.addContext("", docBase.getAbsolutePath());
        Tomcat.addServlet(context, "Calculation", calculatorServlet);
        context.addServletMapping("/*", "Calculation");

        tomcat.start();
        tomcat.getServer().await();
    }
}
