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
    public List<Coach> getCoaches(@PathParam("clubId") int clubId) throws SQLException
    {
        return coachService.getCoaches(clubId);
    }

    @POST
    public Coach addCoach(@PathParam("clubId") int clubId, Coach coach) throws SQLException
    {
        return coachService.addCoach(clubId, coach);
    }

    @PUT
    @Path("{coachId}")
    public Coach updateCoach(@PathParam("clubId") int clubId, @PathParam("coachId") int coachId, Coach coach) throws SQLException
    {
        return coachService.updateCoach(clubId, coachId, coach);
    }

    @DELETE
    @Path("{coachId}")
    public void deleteCoach(@PathParam("clubId") int clubId, @PathParam("coachId") int coachId) throws SQLException
    {
        coachService.deleteCoach(clubId, coachId);
    }
}
