package sk.cubi.calculator.db;

import java.sql.*;
import java.util.Properties;

public class CalculationDbConnector {

    private final String insertStatement = "insert into calculation(myformula, myresult, createdate) values (?, ?, ?)";
    private final String url;
    private final String user;
    private final String pwd;

    CalculationDbConnector(Properties props) throws ClassNotFoundException {
        Class.forName(props.getProperty("dbDriverName"));
        this.url = "jdbc:postgresql://" + props.getProperty("dbHost") + "/" + props.getProperty("dbName");
        this.user = props.getProperty("dbUser");
        this.pwd = props.getProperty("dbPwd");
    }

    public void insertCalculation(String formula, String result) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, pwd);
             PreparedStatement stmt = connection.prepareStatement(insertStatement)) {
            Timestamp now = new Timestamp(System.currentTimeMillis());
            stmt.setString(1, formula);
            stmt.setString(2, result);
            stmt.setTimestamp(3, now);
            stmt.executeUpdate();
        }
    }
}
