package libs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LibSQL {

    //variables
    private String username;
    private String password;
    private String database;
    private String url;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    //constructor
    public LibSQL(String username, String password, String database) {
        this.username = username;
        this.password = password;
        this.database = database;
        this.url = "jdbc:mysql://localhost:3306/" + database;
    }

    //method that allows to return asynchronously the results of a request in parameter
    public ResultSet executeQuery(String query) {
        try {
            connection = java.sql.DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }


}
