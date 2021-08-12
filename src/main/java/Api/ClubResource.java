package Api;

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
    @Path("/{clubId}")
    public Club getClub(@PathParam("clubId") int clubId) throws SQLException, ClassNotFoundException
    {
        return clubService.getClub(clubId);
    }

    @POST
    public Club addClub(Club club) throws SQLException
    {
        return clubService.addClub(club);
    }

    @PUT
    @Path("{clubId}")
    public Club updateClub(@PathParam("clubId") int clubId, Club club) throws SQLException
    {
        return clubService.updateClub(clubId, club);
    }

    @DELETE
    @Path("{clubId}")
    public void deleteClub(@PathParam("clubId") int clubId) throws SQLException
    {
        clubService.deleteClub(clubId);
    }

    // subresource "player"
    @Path("{clubId}/players")
    public PlayerResource playerResource() throws SQLException, ClassNotFoundException
    {
        return new PlayerResource();
    }

    //subresource "staff"
    @Path("{clubId}/staffs")
    public StaffResource staffResource() throws SQLException, ClassNotFoundException
    {
        return new StaffResource();
    }

    //subresource "match"
    @Path("{clubId}/matches")
    public MatchResource matchResource() throws SQLException, ClassNotFoundException
    {
        return new MatchResource();
    }
}
