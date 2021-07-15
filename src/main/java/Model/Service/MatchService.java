package Model.Service;

import Model.Database;
import Model.Match;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatchService
{
    Connection dbConnection = Database.dbConnection();

    public MatchService() throws SQLException, ClassNotFoundException {}

    public List<Match> getMatches(String clubName) throws SQLException
    {
        List<Match> matches = new ArrayList<>();
        String FirstCharUpperCaseClubName = clubName.substring(0, 1).toUpperCase() + clubName.substring(1);

        String query = "SELECT * FROM match WHERE first_club=? OR second_club=?";
        PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
        preparedStatement.setString(1, FirstCharUpperCaseClubName);
        preparedStatement.setString(2, FirstCharUpperCaseClubName);
        ResultSet result = preparedStatement.executeQuery();

        while(result.next())
            matches.add(new Match(result.getInt("id"),
                    result.getString("first_club"),
                    result.getString("second_club"),
                    result.getDate("match_date"),
                    result.getBoolean("is_finished"),
                    result.getString("winner"),
                    result.getInt("first_club_score"),
                    result.getInt("second_club_score")));

        return matches;
    }

}
