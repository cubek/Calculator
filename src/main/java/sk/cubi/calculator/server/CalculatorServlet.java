package sk.cubi.calculator.server;

import sk.cubi.calculator.facade.CalculationFacade;
import sk.cubi.calculator.facade.impl.DefaultCalculationFacadeImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.stream.Collectors;

public class CalculatorServlet extends HttpServlet {

    private CalculationFacade calculationFacade;

    public CalculatorServlet() {
        this.calculationFacade = new DefaultCalculationFacadeImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Writer w = res.getWriter();
        w.write(calculationFacade.calculate(requestBody));
        w.flush();
        w.close();
    }
}
