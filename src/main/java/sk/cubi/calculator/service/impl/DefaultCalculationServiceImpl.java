package sk.cubi.calculator.service.impl;

import sk.cubi.calculator.client.HttpClient;
import sk.cubi.calculator.service.CalculationService;

import java.util.HashMap;
import java.util.Map;

public class DefaultCalculationServiceImpl implements CalculationService {

    private HttpClient client;

    public DefaultCalculationServiceImpl() {
        this("https://www.calcatraz.com/calculator/api");
    }

    public DefaultCalculationServiceImpl(String url) {
        this.client = new HttpClient(url);
    }

    @Override
    public String calculate(String question) {
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("c", question);
        return client.makeGetRequest(requestParams).trim();
    }
}
