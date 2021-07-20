package Model.Service;

import Model.Database;
import Model.Player;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class PlayerService
{
    private Connection dbConnection = Database.dbConnection();
    private PreparedStatement preparedStatement;

    public PlayerService() throws SQLException, ClassNotFoundException {}

    // fetching
    public List<Player> getPlayers(String clubName) throws SQLException
    {
        String FirstCharUpperCaseClubName = clubName.substring(0, 1).toUpperCase() + clubName.substring(1);
        List<Player> playersInClub = new ArrayList<>();

        String query = "SELECT * FROM PLAYER WHERE club_name=?";

        preparedStatement = dbConnection.prepareStatement(query);
        preparedStatement.setString(1, FirstCharUpperCaseClubName);
        ResultSet result = preparedStatement.executeQuery();

        while(result.next())
        {
            playersInClub.add(new Player(result.getInt("id"),
                    result.getString("name"),
                    result.getDate("birth_of_date"),
                    result.getString("nationality"),
                    result.getString("gender"),
                    result.getString("position"),
                    result.getString("club_name")));
        }

        return playersInClub;
    }

    public Player getPlayerByName(String clubName, String playerName) throws SQLException
    {
        Player player = null;
        String FirstCharUpperCaseClubName = clubName.substring(0, 1).toUpperCase() + clubName.substring(1);
        String query = "SELECT * FROM player WHERE name=? AND club_name=?";
        preparedStatement = dbConnection.prepareStatement(query);
       try
       {
           preparedStatement.setString(1, playerName);
       } catch(Exception exception)
       {
           return null;
       }
       preparedStatement.setString(2, FirstCharUpperCaseClubName);
       ResultSet result = preparedStatement.executeQuery();
       while (result.next())
           player = new Player(result.getInt("id"),
                   result.getString("name"),
                   result.getDate("birth_of_date"),
                   result.getString("nationality"),
                   result.getString("gender"),
                   result.getString("position"),
                   result.getString("club_name"));

       return player;
    }

    public Player getPlayerByDate(String clubName, String date) throws SQLException
    {
        Player player = null;
        String FirstCharUpperCaseClubName = clubName.substring(0, 1).toUpperCase() + clubName.substring(1);
        String query = "SELECT * FROM player WHERE birth_of_date=? AND club_name=?";
        preparedStatement = dbConnection.prepareStatement(query);
        try
        {
            preparedStatement.setDate(1, Date.valueOf(date));
        } catch(Exception exception)
        {
            return null;
        }
        preparedStatement.setString(2, FirstCharUpperCaseClubName);
        ResultSet result = preparedStatement.executeQuery();
        while (result.next())
            player = new Player(result.getInt("id"),
                    result.getString("name"),
                    result.getDate("birth_of_date"),
                    result.getString("nationality"),
                    result.getString("gender"),
                    result.getString("position"),
                    result.getString("club_name"));

        return player;
    }

    public Player getPlayerByNationality(String clubName, String nationality) throws SQLException
    {
        Player player = null;
        String FirstCharUpperCaseClubName = clubName.substring(0, 1).toUpperCase() + clubName.substring(1);
        String query = "SELECT * FROM player WHERE nationality=? AND club_name=?";
        preparedStatement = dbConnection.prepareStatement(query);

        try
        {
            preparedStatement.setString(1, nationality);
        } catch(Exception exception)
        {
            return null;
        }
        preparedStatement.setString(2, FirstCharUpperCaseClubName);
        ResultSet result = preparedStatement.executeQuery();
        while (result.next())
            player = new Player(result.getInt("id"),
                    result.getString("name"),
                    result.getDate("birth_of_date"),
                    result.getString("nationality"),
                    result.getString("gender"),
                    result.getString("position"),
                    result.getString("club_name"));

        return player;
    }

    public Player getPlayerByPosition(String clubName, String position) throws SQLException {
        Player player = null;
        String FirstCharUpperCaseClubName = clubName.substring(0, 1).toUpperCase() + clubName.substring(1);
        String query = "SELECT * FROM player WHERE position=? AND club_name=?";
        preparedStatement = dbConnection.prepareStatement(query);

        try
        {
            preparedStatement.setString(1, position);
        } catch(Exception exception)
        {
            return null;
        }
        preparedStatement.setString(2, FirstCharUpperCaseClubName);
        ResultSet result = preparedStatement.executeQuery();
        while (result.next())
            player = new Player(result.getInt("id"),
                    result.getString("name"),
                    result.getDate("birth_of_date"),
                    result.getString("nationality"),
                    result.getString("gender"),
                    result.getString("position"),
                    result.getString("club_name"));

        return player;
    }

    // adding
    public Player addPlayer(String clubName, Player player) throws SQLException
    {
        String name = player.getName();
        Date birthOfDate = player.getBirthOfDate();
        String nationality = player.getNationality();
        String gender = player.getGender();
        String position = player.getPosition();
        String FirstCharUpperCaseClubName = clubName.substring(0, 1).toUpperCase() + clubName.substring(1);


        String query = "INSERT INTO player " +
                "(name, birth_of_date, nationality, gender, position, club_name) " +
                "VALUES(?,?,?,?,?,?)";

        preparedStatement = dbConnection.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setDate(2, birthOfDate);
        preparedStatement.setString(3, nationality);
        preparedStatement.setString(4, gender);
        preparedStatement.setString(5, position);
        preparedStatement.setString(6, FirstCharUpperCaseClubName);

        preparedStatement.executeUpdate();

        return player;
    }

    // updating
    public Player updatePlayer(String clubName, int playerId, Player player) throws SQLException
    {
        String name = player.getName();
        Date birthOfDate = player.getBirthOfDate();
        String nationality = player.getNationality();
        String gender = player.getGender();
        String position = player.getPosition();
        String FirstCharUpperCaseOfClubName = clubName.substring(0, 1).toUpperCase() + clubName.substring(1);

        String query =
                "UPDATE player SET " +
                        "name=COALESCE(?,name), " +
                        "nationality=COALESCE(?,nationality), " +
                        "gender=COALESCE(?,gender), " +
                        "position=COALESCE(?,position), " +
                        "birth_of_date=COALESCE(?,birth_of_date) " +
                        "WHERE id=? AND club_name=?";

        preparedStatement = dbConnection.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, nationality);
        preparedStatement.setString(3, gender);
        preparedStatement.setString(4, position);
        preparedStatement.setDate(5, birthOfDate);
        preparedStatement.setInt(6, playerId);
        preparedStatement.setString(7, FirstCharUpperCaseOfClubName);

        preparedStatement.executeUpdate();

        return player;
    }

    // deleting
    public void deletePlayer(int playerId) throws SQLException
    {
        String query = "DELETE FROM player WHERE id=?";
        preparedStatement = dbConnection.prepareStatement(query);
        try
        {
            preparedStatement.setInt(1, playerId);
        } catch(Exception exception)
        {
            return;
        }
        preparedStatement.executeUpdate();
    }
}
