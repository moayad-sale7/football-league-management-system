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
                    result.getDate("founding_of_date")));
        }

        return allClubs;
    }

    public Club getClub(String clubName) throws SQLException, ClassNotFoundException
    {
        String FirstCharUpperCaseClubName = clubName.substring(0, 1).toUpperCase() + clubName.substring(1);
        Club club = null;
        String query = "SELECT * FROM CLUB WHERE name=?";

        PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
        preparedStatement.setString(1, FirstCharUpperCaseClubName);
        ResultSet result = preparedStatement.executeQuery();

        try
        {
            result.next();
            club = new Club(result.getInt("id"),
                    result.getString("name"),
                    result.getDate("founding_of_date"));
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

        String query = "INSERT INTO CLUB (name, founding_of_date) VALUES(?,?)";

        PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setDate(2, foundingOfDate);
        int numberRowAffected = preparedStatement.executeUpdate();

        return club;
    }

    public Club updateClub(String clubName, Club club) throws SQLException
    {
        String FirstCharUpperCaseClubName = clubName.substring(0, 1).toUpperCase() + clubName.substring(1);
        String name = (club.getName() == null) ? "" : club.getName();
        Date foundingOfDate = (club.getFoundingOfDate() == null) ? null : club.getFoundingOfDate();

        String query =
                "UPDATE club SET " +
                        "name= CASE WHEN ?='' THEN name ELSE ? END, " +
                        "founding_of_date= CASE WHEN ? !=null THEN founding_of_date ELSE ? END " +
                        "WHERE name=?";

        PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, name);
        preparedStatement.setDate(3, foundingOfDate);
        preparedStatement.setDate(4, foundingOfDate);
        preparedStatement.setString(5, FirstCharUpperCaseClubName);

        try
        {
            preparedStatement.executeUpdate();
        } catch(Exception exception)
        {
            return null;
        }

        return club;
    }

    public void deleteClub(String clubName) throws SQLException
    {
        String FirstCharUpperCaseClubName = clubName.substring(0, 1).toUpperCase() + clubName.substring(1);

        String query = "DELETE FROM club WHERE name=?";
        PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
        preparedStatement.setString(1, FirstCharUpperCaseClubName);
        preparedStatement.executeUpdate();
    }
}
