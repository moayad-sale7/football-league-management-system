package Model;

import java.sql.*;

public class Database
{
    private static final String URL = "jdbc:postgresql://localhost/spl";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "123";

    public static Connection dbConnection() throws SQLException, ClassNotFoundException
    {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        return connection;
    }

    public static int getSizeOfTable(String table) throws SQLException, ClassNotFoundException
    {
        String query = "SELECT COUNT(*) FROM " + table;
        Statement statement = dbConnection().createStatement();
        ResultSet result = statement.executeQuery(query);

        result.next();
        int count = result.getInt(1);
        return count;
    }
}
