package Model;

import java.sql.*;

public class Database
{
    private static final String URL = "jdbc:postgresql://localhost/football_league";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "123";

    public static Connection dbConnection() throws SQLException, ClassNotFoundException
    {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        return connection;
    }
}
