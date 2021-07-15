package api;

import Model.Match;
import Model.Service.MatchService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

import java.sql.SQLException;
import java.util.List;

@Path("/")
public class MatchResource
{
    MatchService matchService = new MatchService();

    public MatchResource() throws SQLException, ClassNotFoundException {}

    @GET
    public List<Match> getMatches(@PathParam("clubName") String clubName) throws SQLException
    {
        return matchService.getMatches(clubName);
    }
}
