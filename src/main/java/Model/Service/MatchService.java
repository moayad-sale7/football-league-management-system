package Model.Service;

import Model.Database;
import Model.Match;

import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class MatchService
{
    Connection dbConnection = Database.dbConnection();

    public MatchService() throws SQLException, ClassNotFoundException {}

    // fetch all the matches of the selected club
    public List<Match> getMatches(int clubId) throws SQLException
    {
        List<Match> matches = new ArrayList<>();
        String query = "SELECT " +
                "match.id, " +
                "match.date, " +
                "match.status, " +
                "match.winner, " +
                "home.name, " +
                "away.name, " +
                "match.club_home_score, " +
                "match.club_away_score " +
                "FROM match " +
                "INNER JOIN club home ON home.id = match.club_home " +
                "INNER JOIN club away ON away.id = match.club_away " +
                "WHERE away.id=? OR home.id=?;";

        PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
        preparedStatement.setInt(1, clubId);
        preparedStatement.setInt(2, clubId);
        ResultSet result = preparedStatement.executeQuery();

        while(result.next())
            matches.add(new Match(result.getInt(1), // match id
                    result.getString(5), // club home
                    result.getString(6), // club away
                    result.getDate(2), // match date
                    result.getString(3), // match status
                    result.getString(4), // match winner
                    result.getInt(7), // club home score
                    result.getInt(8))); // club away score

        return matches;
    }

    public List<Match> getMatchesByDate(int clubId, String matchDate) throws SQLException {
        List<Match> matches = new ArrayList<>();

        String query = "SELECT " +
                "match.id, " +
                "match.date, " +
                "match.status, " +
                "match.winner, " +
                "home.name, " +
                "away.name, " +
                "match.club_home_score, " +
                "match.club_away_score " +
                "FROM match " +
                "INNER JOIN club home ON home.id=match.club_home " +
                "INNER JOIN club away ON away.id=match.club_away " +
                "WHERE away.id=? OR home.id=? AND date=?;";

        PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
        preparedStatement.setInt(1, clubId);
        preparedStatement.setInt(2, clubId);
        preparedStatement.setDate(3, Date.valueOf(matchDate));
        ResultSet result = preparedStatement.executeQuery();

        while(result.next())
            matches.add(new Match(result.getInt(1), // match id
                    result.getString(5), // club home
                    result.getString(6), // club away
                    result.getDate(2), // match date
                    result.getString(3), // match status
                    result.getString(4), // match winner
                    result.getInt(7), // club home score
                    result.getInt(8))); // club away score

        return matches;
    }

    public List<Match> getMatchByStatus(int clubId, String status) throws SQLException {
        List<Match> matches = new ArrayList<>();

        String query = "SELECT " +
                "match.id, " +
                "match.date, " +
                "match.status, " +
                "match.winner, " +
                "home.name, " +
                "away.name, " +
                "match.club_home_score, " +
                "match.club_away_score " +
                "FROM match " +
                "INNER JOIN club home ON home.id=match.club_home " +
                "INNER JOIN club away ON away.id=match.club_away " +
                "WHERE away.id=? OR home.id=? AND status=?;";

        PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
        preparedStatement.setInt(1, clubId);
        preparedStatement.setInt(2, clubId);
        preparedStatement.setString(3, status);
        ResultSet result = preparedStatement.executeQuery();

        while(result.next())
            matches.add(new Match(result.getInt(1), // match id
                    result.getString(5), // club home
                    result.getString(6), // club away
                    result.getDate(2), // match date
                    result.getString(3), // match status
                    result.getString(4), // match winner
                    result.getInt(7), // club home score
                    result.getInt(8))); // club away score

        return matches;
    }

    public List<Match> getMatchByWinner(int clubId, String winner) throws SQLException {
        List<Match> matches = new ArrayList<>();

        String query = "SELECT " +
                "match.id, " +
                "match.date, " +
                "match.status, " +
                "match.winner, " +
                "home.name, " +
                "away.name, " +
                "match.club_home_score, " +
                "match.club_away_score " +
                "FROM match " +
                "INNER JOIN club home ON home.id=match.club_home " +
                "INNER JOIN club away ON away.id=match.club_away " +
                "WHERE away.id=? OR home.id=? AND winner=?;";

        PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
        preparedStatement.setInt(1, clubId);
        preparedStatement.setInt(2, clubId);
        preparedStatement.setString(3, winner);
        ResultSet result = preparedStatement.executeQuery();

        while(result.next())
            matches.add(new Match(result.getInt(1), // match id
                    result.getString(5), // club home
                    result.getString(6), // club away
                    result.getDate(2), // match date
                    result.getString(3), // match status
                    result.getString(4), // match winner
                    result.getInt(7), // club home score
                    result.getInt(8))); // club away score

        return matches;
    }

    // updating (not finished yet)
    public Match updateNatch(int clubId, int matchId, Match match) throws SQLException {

        // club list mapped to their id to used to update match table
        Map<String, Integer> clubList = new Hashtable<>();
        clubList.put("Ittihad", 1);
        clubList.put("Hilal", 2);
        clubList.put("Shabab", 3);

        String query = "UPDATE match SET date=COALESCE(?,date), status=COALESCE(?,status), winner=COALESCE(?,winner), " +
                "club_home=COALESCE(?,club_home), club_away=COALESCE(?,club_away), " +
                "club_home_score=COALESCE(?,club_home_score), club_away_score=COALESCE(?,club_away_score) " +
                "WHERE id=?";

        PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
        preparedStatement.setDate(1, match.getDateOfMatch());
        preparedStatement.setString(2, match.getStatus());
        preparedStatement.setString(3, match.getWinner());
        preparedStatement.setInt(4, clubList.get(match.getClubHome()));
        preparedStatement.setInt(5, clubList.get(match.getClubAway()));
        preparedStatement.setInt(6, match.getClubHomeScore());
        preparedStatement.setInt(7, match.getClubAwayScore());
        preparedStatement.setInt(8, clubId);

        return null;
    }


}
