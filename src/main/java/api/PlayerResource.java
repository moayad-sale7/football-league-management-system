package api;

import Model.Player;
import Model.Service.PlayerService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class PlayerResource
{
    PlayerService playerService = new PlayerService();

    public PlayerResource() throws SQLException, ClassNotFoundException {}

    @GET
    public List<Player> getPlayers(@PathParam("clubName") String clubName,
                                   @QueryParam("name") String playerName,
                                   @QueryParam("date") String date,
                                   @QueryParam("nationality") String nationality,
                                   @QueryParam("position") String position) throws SQLException
    {
        if(playerName != null)
            return Arrays.asList(playerService.getPlayerByName(clubName, playerName));
        else if(date != null)
            return Arrays.asList(playerService.getPlayerByDate(clubName, date));
        else if(nationality != null)
            return Arrays.asList(playerService.getPlayerByNationality(clubName, nationality));
        else if(position != null)
            return Arrays.asList(playerService.getPlayerByPosition(clubName, position));
        else
            return playerService.getPlayers(clubName);
    }

    @POST
    public Player addPlayer(@PathParam("clubName") String clubName, Player player) throws SQLException
    {
        return playerService.addPlayer(clubName, player);
    }

    @PUT
    public Player updatePlayer(@PathParam("clubName") String clubName,
                               @QueryParam("id") int playerId, 
                               Player player) throws SQLException
    {
        return playerService.updatePlayer(clubName, playerId, player);
    }

    @DELETE
    public void deletePlayer(@QueryParam("id") int playerId) throws SQLException
    {
        playerService.deletePlayer(playerId);
    }
}
