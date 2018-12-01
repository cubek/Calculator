package sk.cubi.calculator;

import sk.cubi.calculator.client.HttpClient;

import java.util.HashMap;
import java.util.Map;

public class CalculatorApplication {

    public static void main(String[] args) {
        HttpClient client = new HttpClient("https://www.calcatraz.com/calculator/api");

        Map<String, String> params = new HashMap<>();
        params.put("c", "2+2");

        client.makeGetRequest(params);
    }
}
