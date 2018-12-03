package sk.cubi.calculator.db;

import java.util.Properties;

public class CalculationDbFactory {

    private static CalculationDbConnector dbConnectorInstance;

    public static void createDbConnector(Properties props) throws ClassNotFoundException {
        dbConnectorInstance = new CalculationDbConnector(props);
    }

    public static CalculationDbConnector getDbConnector() {
        return dbConnectorInstance;
    }
}
