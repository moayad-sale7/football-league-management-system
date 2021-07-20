package api;

import Model.Club;
import Model.Service.ClubService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.sql.SQLException;
import java.util.List;

@Path("clubs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClubResource
{
    ClubService clubService = new ClubService();

    public ClubResource() throws SQLException, ClassNotFoundException {}

    //Main methods for club
    @GET
    public List<Club> getClubs() throws SQLException, ClassNotFoundException
    {
        return clubService.getClubs();
    }

    @GET
    @Path("/{clubName}")
    public Club getClub(@PathParam("clubName") String clubId) throws SQLException, ClassNotFoundException
    {
        return clubService.getClub(clubId);
    }

    @POST
    public Club addClub(Club club) throws SQLException
    {
        return clubService.addClub(club);
    }

    @PUT
    @Path("{clubName}")
    public Club updateClub(@PathParam("clubName") String clubName, Club club) throws SQLException
    {
        return clubService.updateClub(clubName, club);
    }

    @DELETE
    @Path("{clubName}")
    public void deleteClub(@PathParam("clubName") String clubName) throws SQLException
    {
        clubService.deleteClub(clubName);
    }

    // subresource "player"
    @Path("{clubName}/players")
    public PlayerResource playerResource() throws SQLException, ClassNotFoundException
    {
        return new PlayerResource();
    }

    //subresource "coach"
    @Path("{clubName}/coaches")
    public CoachResource coachResource() throws SQLException, ClassNotFoundException
    {
        return new CoachResource();
    }

    //subresource "match"
    @Path("{clubName}/matches")
    public MatchResource matchResource() throws SQLException, ClassNotFoundException
    {
        return new MatchResource();
    }
}
