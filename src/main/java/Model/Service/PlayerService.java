package Model.Service;

import Model.Database;
import Model.Player;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class PlayerService
{
    Connection dbConnection = Database.dbConnection();

    public PlayerService() throws SQLException, ClassNotFoundException {}

    public List<Player> getPlayers(String clubName) throws SQLException
    {
        String FirstCharUpperCaseClubName = clubName.substring(0, 1).toUpperCase() + clubName.substring(1);
        List<Player> playersInClub = new ArrayList<>();

        String query = "SELECT * FROM PLAYER WHERE club_name=?";

        PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
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

    public Player getPlayerById(String clubName, int id) throws SQLException
    {
        Player player = null;
        String FirstCharUpperCaseClubName = clubName.substring(0, 1).toUpperCase() + clubName.substring(1);
        String query = "SELECT * FROM player WHERE id=? AND club_name=?";
        PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
       try
       {
           preparedStatement.setInt(1, id);
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

        PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setDate(2, birthOfDate);
        preparedStatement.setString(3, nationality);
        preparedStatement.setString(4, gender);
        preparedStatement.setString(5, position);
        preparedStatement.setString(6, FirstCharUpperCaseClubName);

        preparedStatement.executeUpdate();

        return player;
    }

    public Player updatePlayer(String clubName, int playerId, Player player) throws SQLException
    {

        // check if null before giving the value cus that will make it easy to check in sql query
        String name = (player.getName() == null) ? "" : player.getName();
        Date birthOfDate = (player.getBirthOfDate() == null) ? null :player.getBirthOfDate();
        String nationality = (player.getNationality() == null) ? "" : player.getNationality();
        String gender = (player.getGender() == null) ? "" : player.getGender();
        String position = (player.getPosition() == null) ? "" : player.getPosition();
        String FirstCharUpperCaseClubName = clubName.substring(0, 1).toUpperCase() + clubName.substring(1);

        //set the values to "player"
        player.setName(name);
        player.setBirthOfDate(birthOfDate);
        player.setNationality(nationality);
        player.setGender(gender);
        player.setPosition(position);
        player.setId(playerId);

        String query =
                "UPDATE player SET name= CASE WHEN ?='' THEN name ELSE ? END," +
                        " nationality= CASE WHEN ?='' THEN nationality ELSE ? END," +
                        " gender= CASE WHEN ?='' THEN gender ELSE ? END," +
                        " position= CASE WHEN ?='' THEN position ELSE ? END, " +
                        "birth_of_date= CASE WHEN ? !=null THEN ? ELSE birth_of_date END " +
                        "WHERE id=? AND club_name=?";

        PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, name);

        preparedStatement.setString(3, nationality);
        preparedStatement.setString(4, nationality);

        preparedStatement.setString(5, gender);
        preparedStatement.setString(6, gender);

        preparedStatement.setString(7, position);
        preparedStatement.setString(8, position);

        preparedStatement.setDate(9, birthOfDate);
        preparedStatement.setDate(10, birthOfDate);

        preparedStatement.setInt(11, playerId);
        preparedStatement.setString(12, FirstCharUpperCaseClubName);

        preparedStatement.executeUpdate();

        return player;
    }

    public void deletePlayer(int playerId) throws SQLException
    {
        String query = "DELETE FROM player WHERE id=?";
        PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
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
