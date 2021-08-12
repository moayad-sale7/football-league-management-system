package Api;

import Model.Match;
import Model.Service.MatchService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@Path("/")
public class MatchResource
{
    MatchService matchService = new MatchService();

    public MatchResource() throws SQLException, ClassNotFoundException {}

    @GET
    public List<Match> getMatches(@PathParam("clubId") int clubId,
                                  @QueryParam("date") String matchDate,
                                  @QueryParam("status") String status,
                                  @QueryParam("winner") String winner) throws SQLException
    {
        if(matchDate != null)
            return matchService.getMatchesByDate(clubId, matchDate);
        else if(status != null)
            return matchService.getMatchByStatus(clubId, status);
        else if(winner != null)
            return matchService.getMatchByWinner(clubId, winner);
        else
            return matchService.getMatches(clubId);
    }
}
