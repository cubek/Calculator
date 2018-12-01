package sk.cubi.calculator.model;

public class ResponseModel {

    private String result1;
    private String result2;

    public ResponseModel() {
    }

    public ResponseModel(String result1, String result2) {
        this.result1 = result1;
        this.result2 = result2;
    }

    public String getResult1() {
        return result1;
    }

    public void setResult1(String result1) {
        this.result1 = result1;
    }

    public String getResult2() {
        return result2;
    }

    public void setResult2(String result2) {
        this.result2 = result2;
    }
}
