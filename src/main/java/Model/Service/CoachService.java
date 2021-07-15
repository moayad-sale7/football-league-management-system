package Model.Service;

import Model.Coach;
import Model.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoachService
{
    private Connection dbConnection = Database.dbConnection();

    public CoachService() throws SQLException, ClassNotFoundException {}

    public List<Coach> getCoaches(int clubId) throws SQLException
    {
        List<Coach> allCoaches = new ArrayList<>();

        String query = "SELECT * FROM coach WHERE club_id=?";
        PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
        preparedStatement.setInt(1, clubId);
        try(ResultSet result = preparedStatement.executeQuery())
        {
            while(result.next())
                allCoaches.add(new Coach(result.getInt("id"),
                        result.getString("name"),
                        result.getDate("birth_of_date"),
                        result.getString("nationality"),
                        result.getString("gender"),
                        result.getString("club_name")));
        }

        return allCoaches;
    }

    public Coach addCoach(int clubId, Coach coach) throws SQLException
    {
        String name = coach.getName();
        Date birthOfDate = coach.getBirthOfDate();
        String nationality = coach.getNationality();
        String gender = coach.getGender();
        int cId = clubId;

        String query = "INSERT INTO " +
                "coach(name, birth_of_date, nationality, gender, club_id) " +
                "VALUES(?,?,?,?,?)";
        PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setDate(2, birthOfDate);
        preparedStatement.setString(3, nationality);
        preparedStatement.setString(4, gender);
        preparedStatement.setInt(5, cId);
        try
        {
            preparedStatement.executeUpdate();
        } catch(Exception exception)
        {
            return null;
        }

        return coach;
    }

    public Coach updateCoach(int clubId, int coachId ,Coach coach) throws SQLException
    {
        String name = (coach.getName() == null) ? "" : coach.getName();
        Date birthOfDate = (coach.getBirthOfDate() == null) ? null :coach.getBirthOfDate();
        String nationality = (coach.getNationality() == null) ? "" : coach.getNationality();
        String gender = (coach.getGender() == null) ? "" : coach.getGender();

        coach.setName(name);
        coach.setBirthOfDate(birthOfDate);
        coach.setNationality(nationality);
        coach.setGender(gender);
        coach.setId(coachId);

        String query =
                "UPDATE coach SET " +
                        "name= CASE WHEN ?='' THEN name ELSE ? END, " +
                        "nationality= CASE WHEN ?='' THEN nationality ELSE ? END, " +
                        "gender= CASE WHEN ?='' THEN gender ELSE ? END, " +
                        "birth_of_date= CASE WHEN ? !=null THEN ? ELSE birth_of_date END " +
                        "WHERE id=? AND club_id=?";

        PreparedStatement preparedStatement = dbConnection.prepareStatement(query);

        preparedStatement.setString(1, name);
        preparedStatement.setString(2, name);

        preparedStatement.setString(3, nationality);
        preparedStatement.setString(4, nationality);

        preparedStatement.setString(5, gender);
        preparedStatement.setString(6, gender);

        preparedStatement.setDate(7, birthOfDate);
        preparedStatement.setDate(8, birthOfDate);

        preparedStatement.setInt(9, coachId);
        preparedStatement.setInt(10, clubId);

        preparedStatement.executeUpdate();

        return coach;
    }

    public void deleteCoach(int clubId, int coachId) throws SQLException
    {
        String query = "DELETE FROM coach WHERE id=? AND club_id=?";

        PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
        preparedStatement.setInt(1, coachId);
        preparedStatement.setInt(2, clubId);
        try
        {
            preparedStatement.executeUpdate();
        } catch(Exception exception)
        {
            return;
        }
    }

}
