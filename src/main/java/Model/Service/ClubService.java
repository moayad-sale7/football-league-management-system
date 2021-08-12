package Model.Service;

import Model.Club;
import Model.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClubService
{
    Connection dbConnection = Database.dbConnection();

    public ClubService() throws SQLException, ClassNotFoundException
    {}

    public List<Club> getClubs() throws SQLException
    {
        List<Club> allClubs = new ArrayList<>();

        Statement statement = dbConnection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM CLUB");

        while(result.next())
        {
            allClubs.add(new Club(result.getInt("id"),
                    result.getString("name"),
                    result.getDate("founding")));
        }

        return allClubs;
    }

    public Club getClub(int clubId) throws SQLException, ClassNotFoundException
    {
        Club club = null;
        String query = "SELECT * FROM CLUB WHERE id=?";

        PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
        preparedStatement.setInt(1, clubId);
        ResultSet result = preparedStatement.executeQuery();

        try
        {
            result.next();
            club = new Club(result.getInt("id"),
                    result.getString("name"),
                    result.getDate("founding"));
        } catch (SQLException sqlException)
        {
            System.out.println(sqlException.getMessage());
        }

        return club;
    }

    public Club addClub(Club club) throws SQLException
    {
        String name = null;
        Date foundingOfDate = null;
        try
        {
            name = club.getName();
            foundingOfDate = club.getFoundingOfDate();
        } catch(Exception exception)
        {
            return null;
        }

        String query = "INSERT INTO CLUB (name, founding) VALUES(?,?)";

        PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setDate(2, foundingOfDate);
        preparedStatement.executeUpdate();

        return club;
    }

    public Club updateClub(int clubId, Club club) throws SQLException
    {
        String name = (club.getName() == null) ? "" : club.getName();
        Date foundingOfDate = (club.getFoundingOfDate() == null) ? null : club.getFoundingOfDate();

        String query =
                "UPDATE club SET " +
                        "name=COALESCE(?,name), " +
                        "founding=COALESCE(?,founding) " +
                        "WHERE id=?";

        PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setDate(2, foundingOfDate);
        preparedStatement.setInt(3, clubId);

        try
        {
            preparedStatement.executeUpdate();
        } catch(Exception exception)
        {
            return null;
        }

        return club;
    }

    public void deleteClub(int clubId) throws SQLException
    {
        String query = "DELETE FROM club WHERE id=?";
        PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
        preparedStatement.setInt(1, clubId);
        preparedStatement.executeUpdate();
    }
}
