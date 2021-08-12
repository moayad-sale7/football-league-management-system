package Api;

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
    public List<Player> getPlayers(@PathParam("clubId") int clubId,
                                   @QueryParam("name") String playerName,
                                   @QueryParam("date") String date,
                                   @QueryParam("country") String country,
                                   @QueryParam("position") String position) throws SQLException
    {
        if(playerName != null)
            return playerService.getPlayerByName(clubId, playerName);
        else if(date != null)
            return playerService.getPlayerByDate(clubId, date);
        else if(country != null)
            return playerService.getPlayerByCountry(clubId, country);
        else if(position != null)
            return playerService.getPlayerByPosition(clubId, position);
        else
            return playerService.getPlayers(clubId);
    }

    @POST
    public Player addPlayer(@PathParam("clubId") int clubId, Player player) throws SQLException
    {
        return playerService.addPlayer(clubId, player);
    }

    @PUT
    @Path("player/{playerId}")
    public Player updatePlayer(@PathParam("clubId") int clubId,
                               @PathParam("playerId") int playerId,
                               Player player) throws SQLException
    {
        return playerService.updatePlayer(clubId, playerId, player);
    }

    @DELETE
    @Path("player/{playerId}")
    public void deletePlayer(@PathParam("playerId") int playerId) throws SQLException
    {
        playerService.deletePlayer(playerId);
    }
}
