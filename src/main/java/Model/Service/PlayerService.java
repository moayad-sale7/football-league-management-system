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
    public List<Player> getPlayers(int clubId) throws SQLException
    {
        List<Player> playersInClub = new ArrayList<>();

        String query = "SELECT * FROM PLAYER WHERE club_id=?";

        preparedStatement = dbConnection.prepareStatement(query);
        preparedStatement.setInt(1, clubId);
        ResultSet result = preparedStatement.executeQuery();

        while(result.next())
        {
            playersInClub.add(new Player(result.getInt("id"),
                    result.getString("name"),
                    result.getDate("birth_of_date"),
                    result.getString("country"),
                    result.getString("position")));
        }

        return playersInClub;
    }

    public List<Player> getPlayerByName(int clubId, String playerName) throws SQLException
    {
        List<Player> players = new ArrayList<>();

        String query = "SELECT * FROM player WHERE name=? AND club_id=?";

        preparedStatement = dbConnection.prepareStatement(query);
        preparedStatement.setString(1, playerName);
        preparedStatement.setInt(2, clubId);
        ResultSet result = null;
        try
        {
            result = preparedStatement.executeQuery();
        }
        catch (Exception exception)
        {
            return null;
        }
        finally
        {
            while (result.next())
                players.add(new Player(result.getInt("id"),
                        result.getString("name"),
                        result.getDate("birth_of_date"),
                        result.getString("country"),
                        result.getString("position")));
        }

        return players;
    }

    public List<Player> getPlayerByDate(int clubId, String date) throws SQLException
    {
        List<Player> players = new ArrayList<>();
        String query = "SELECT * FROM player WHERE birth_of_date=? AND club_id=?";

        preparedStatement = dbConnection.prepareStatement(query);
        preparedStatement.setDate(1, Date.valueOf(date));
        preparedStatement.setInt(2, clubId);

        ResultSet result = null;
        try
        {
            result = preparedStatement.executeQuery();
        }
        catch (Exception exception)
        {
            return null;
        }
        finally
        {
            while (result.next())
                 players.add(new Player(result.getInt("id"),
                        result.getString("name"),
                        result.getDate("birth_of_date"),
                        result.getString("country"),
                        result.getString("position")));
        }
        return players;
    }

    public List<Player> getPlayerByCountry(int clubId, String country) throws SQLException
    {
        List<Player> players = new ArrayList<>();

        String query = "SELECT * FROM player WHERE country=? AND club_id=?";

        preparedStatement = dbConnection.prepareStatement(query);
        preparedStatement.setString(1, country);
        preparedStatement.setInt(2, clubId);

        ResultSet result = null;
        try
        {
            result = preparedStatement.executeQuery();
        }
        catch (Exception exception)
        {
            return null;
        }
        finally
        {
            while (result.next())
                players.add(new Player(result.getInt("id"),
                        result.getString("name"),
                        result.getDate("birth_of_date"),
                        result.getString("country"),
                        result.getString("position")));
        }

        return players;
    }

    public List<Player> getPlayerByPosition(int clubId, String position) throws SQLException
    {
        List<Player> players = new ArrayList<>();

        String query = "SELECT * FROM player WHERE position=? AND club_id=?";

        preparedStatement = dbConnection.prepareStatement(query);
        preparedStatement.setString(1, position);
        preparedStatement.setInt(2, clubId);

        ResultSet result = null;
        try
        {
            result = preparedStatement.executeQuery();
        }
        catch (Exception exception)
        {
            return null;
        }
        finally
        {
            while (result.next())
                players.add(new Player(result.getInt("id"),
                result.getString("name"),
                result.getDate("birth_of_date"),
                result.getString("country"),
                result.getString("position")));
        }

        return players;
    }

    // adding
    public Player addPlayer(int clubId, Player player) throws SQLException
    {
        String name = player.getName();
        Date birthOfDate = player.getBirthOfDate();
        String nationality = player.getCountry();
        String position = player.getPosition();

        String query = "INSERT INTO player " +
                       "(name, country, birth_of_date, position, club_id) " +
                       "VALUES(?,?,?,?,?)";

        preparedStatement = dbConnection.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, nationality);
        preparedStatement.setDate(3, birthOfDate);
        preparedStatement.setString(4, position);
        preparedStatement.setInt(5, clubId);

        try
        {
            preparedStatement.executeUpdate();
        }
        catch (Exception exception)
        {
            return null;
        }

        return player;
    }

    // updating
    public Player updatePlayer(int clubId, int playerId, Player player) throws SQLException
    {
        String name = player.getName();
        Date birthOfDate = player.getBirthOfDate();
        String country = player.getCountry();
        String position = player.getPosition();

        String query = "UPDATE player SET " +
                       "name=COALESCE(?,name), " +
                       "country=COALESCE(?,country), " +
                       "birth_of_date=COALESCE(?,birth_of_date) " +
                       "position=COALESCE(?,position), " +
                       "WHERE id=? AND club_id=?";

        preparedStatement = dbConnection.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, country);
        preparedStatement.setDate(3, birthOfDate);
        preparedStatement.setString(4, position);
        preparedStatement.setInt(5, playerId);
        preparedStatement.setInt(6, clubId);

        try
        {
            preparedStatement.executeUpdate();
        }
        catch (Exception exception)
        {
            return null;
        }

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
