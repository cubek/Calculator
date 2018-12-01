package sk.cubi.calculator.facade;

import com.google.gson.Gson;
import sk.cubi.calculator.model.RequestModel;
import sk.cubi.calculator.model.ResponseModel;
import sk.cubi.calculator.service.CalculationService;
import sk.cubi.calculator.service.DefaultCalculationServiceImpl;

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
        String response1, response2;
        RequestModel requestObj = gson.fromJson(request, RequestModel.class);

        //calculate answers
        response1 = requestObj.getQuestion1().isEmpty() ? "Missing question" : calculationService.calculate(requestObj.getQuestion1());
        response2 = requestObj.getQuestion2().isEmpty() ? "Missing question" : calculationService.calculate(requestObj.getQuestion2());

        //calcatraz returns empty string in case answer is 0
        response1 = response1.isEmpty() ? "0" : response1;
        response2 = response2.isEmpty() ? "0" : response2;

        return gson.toJson(new ResponseModel(response1, response2));
    }
}
