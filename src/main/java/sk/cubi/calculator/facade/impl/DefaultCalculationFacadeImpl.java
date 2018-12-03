package sk.cubi.calculator.facade.impl;

import com.google.gson.Gson;
import sk.cubi.calculator.db.CalculationDbFactory;
import sk.cubi.calculator.facade.CalculationFacade;
import sk.cubi.calculator.model.RequestModel;
import sk.cubi.calculator.model.ResponseModel;
import sk.cubi.calculator.service.CalculationService;
import sk.cubi.calculator.service.impl.DefaultCalculationServiceImpl;

import java.sql.SQLException;

public class DefaultCalculationFacadeImpl implements CalculationFacade {

    private CalculationService calculationService;

    public DefaultCalculationFacadeImpl() {
        this(new DefaultCalculationServiceImpl());
    }

    public DefaultCalculationFacadeImpl(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @Override
    public String calculate(String request) {
        Gson gson = new Gson();
        String response1 = "Missing question", response2 = "Missing question";
        RequestModel requestObj = gson.fromJson(request, RequestModel.class);

        //calculate answers
        try {
            if (!requestObj.getQuestion1().isEmpty()) {
                response1 = calculationService.calculate(requestObj.getQuestion1());
                //calcatraz returns empty string in case answer is 0
                response1 = response1.isEmpty() ? "0" : response1;
                CalculationDbFactory.getDbConnector().insertCalculation(requestObj.getQuestion1(), response1);
            }
            if (!requestObj.getQuestion2().isEmpty()) {
                response2 = calculationService.calculate(requestObj.getQuestion2());
                //calcatraz returns empty string in case answer is 0
                response2 = response2.isEmpty() ? "0" : response2;
                CalculationDbFactory.getDbConnector().insertCalculation(requestObj.getQuestion2(), response2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return gson.toJson(new ResponseModel(response1, response2));
    }
}
