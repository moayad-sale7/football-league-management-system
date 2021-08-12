package Api;

import Model.Staff;
import Model.Service.StaffService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.sql.SQLException;
import java.util.List;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StaffResource
{
    StaffService staffService = new StaffService();

    public StaffResource() throws SQLException, ClassNotFoundException {}

    @GET
    public List<Staff> getCoaches(@PathParam("clubId") int clubId,
                                  @QueryParam("name") String staffName,
                                  @QueryParam("date") String date,
                                  @QueryParam("country") String country,
                                  @QueryParam("role") String role) throws SQLException
    {
        if(staffName != null)
            return staffService.getStaffByName(clubId, staffName);
        else if(date != null)
            return staffService.getStaffByDate(clubId, date);
        else if(country != null)
            return staffService.getStaffByCountry(clubId, country);
        else if(role != null)
            return staffService.getStaffByRole(clubId, role);
        else
            return staffService.getStaff(clubId);
    }

    @POST
    public Staff addCoach(@PathParam("clubId") int clubId, Staff staff) throws SQLException
    {
        return staffService.addStaff(clubId, staff);
    }

    @PUT
    @Path("{staffId}")
    public Staff updateCoach(@PathParam("clubId") int clubId, @PathParam("staffId") int staffId, Staff staff) throws SQLException
    {
        return staffService.updateCoach(clubId, staffId, staff);
    }

    @DELETE
    @Path("{staffId}")
    public void deleteCoach(@PathParam("clubId") int clubId, @PathParam("staffId") int staffId) throws SQLException
    {
        staffService.deleteCoach(clubId, staffId);
    }
}
