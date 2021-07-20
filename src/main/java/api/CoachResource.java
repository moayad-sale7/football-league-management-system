package api;

import Model.Coach;
import Model.Service.CoachService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.sql.SQLException;
import java.util.List;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CoachResource
{
    CoachService coachService = new CoachService();

    public CoachResource() throws SQLException, ClassNotFoundException {}

    @GET
    public List<Coach> getCoaches(@PathParam("clubName") String clubName) throws SQLException
    {
        return coachService.getCoaches(clubName);
    }

    @POST
    public Coach addCoach(@PathParam("clubName") String clubName, Coach coach) throws SQLException
    {
        return coachService.addCoach(clubName, coach);
    }

    @PUT
    @Path("{coachName}")
    public Coach updateCoach(@PathParam("clubName") String clubName, @PathParam("coachName") String coachName, Coach coach) throws SQLException
    {
        return coachService.updateCoach(clubName, coachName, coach);
    }

    @DELETE
    @Path("{coachName}")
    public void deleteCoach(@PathParam("clubName") String clubName, @PathParam("coachName") String coachName) throws SQLException
    {
        coachService.deleteCoach(clubName, coachName);
    }
}
