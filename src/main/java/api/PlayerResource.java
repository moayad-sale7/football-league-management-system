package api;

import Model.Player;
import Model.Service.PlayerService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.sql.SQLException;
import java.util.List;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class PlayerResource
{
    PlayerService playerService = new PlayerService();

    public PlayerResource() throws SQLException, ClassNotFoundException {}

    @GET
    public List<Player> getPlayersByClub(@PathParam("clubName") String clubName) throws SQLException
    {
        return playerService.getPlayers(clubName);
    }

    @GET
    @Path("{playerId}")
    public Player getPlayersById(@PathParam("clubName") String clubName, @PathParam("playerId") int id) throws SQLException
    {
        return playerService.getPlayerById(clubName, id);
    }

    @POST
    public Player addPlayer(@PathParam("clubName") String clubName, Player player) throws SQLException
    {
        return playerService.addPlayer(clubName, player);
    }

    @PUT
    @Path("/{playerId}")
    public Player updatePlayer(@PathParam("clubName") String clubName, @PathParam("playerId") int playerId, Player player) throws SQLException
    {
        return playerService.updatePlayer(clubName, playerId, player);
    }

    @DELETE
    @Path("{playerId}")
    public void deletePlayer(@PathParam("playerId") int playerID) throws SQLException
    {
        playerService.deletePlayer(playerID);
    }
}
