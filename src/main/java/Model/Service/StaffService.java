package Model.Service;

import Model.Player;
import Model.Staff;
import Model.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffService
{
    private Connection dbConnection = Database.dbConnection();

    public StaffService() throws SQLException, ClassNotFoundException {}

    public List<Staff> getStaff(int clubId) throws SQLException
    {
        List<Staff> staff = new ArrayList<>();

        String query = "SELECT id, name,birth_of_date, country, role " +
                       "FROM staff WHERE club_id=?";
        PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
        preparedStatement.setInt(1, clubId);
        try(ResultSet result = preparedStatement.executeQuery())
        {
            while(result.next())
                staff.add(new Staff(result.getInt("id"),
                        result.getString("name"),
                        result.getDate("birth_of_date"),
                        result.getString("country"),
                        result.getString("role")));
        }

        return staff;
    }

    public List<Staff> getStaffByName(int clubId, String staffName) throws SQLException
    {
        List<Staff> staffs = new ArrayList<>();

        String query = "SELECT * FROM staff WHERE name=? AND club_id=?";

        PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
        preparedStatement.setString(1, staffName);
        preparedStatement.setInt(2, clubId);
        ResultSet result = null;
        try
        {
            result = preparedStatement.executeQuery();
        }
        catch (Exception exception)
        {
            return null;
        }
        finally
        {
            //public Staff(int id, String name, Date birthOfDate, String country, String role)
            while (result.next())
                staffs.add(new Staff(result.getInt("id"),
                        result.getString("name"),
                        result.getDate("birth_of_date"),
                        result.getString("country"),
                        result.getString("role")));
        }

        return staffs;
    }

    public List<Staff> getStaffByDate(int clubId, String date) throws SQLException
    {
        List<Staff> staffs = new ArrayList<>();

        String query = "SELECT * FROM staff WHERE birth_of_date=? AND club_id=?";

        PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
        preparedStatement.setDate(1, Date.valueOf(date));
        preparedStatement.setInt(2, clubId);
        ResultSet result = null;
        try
        {
            result = preparedStatement.executeQuery();
        }
        catch (Exception exception)
        {
            return null;
        }
        finally
        {
            //public Staff(int id, String name, Date birthOfDate, String country, String role)
            while (result.next())
                staffs.add(new Staff(result.getInt("id"),
                        result.getString("name"),
                        result.getDate("birth_of_date"),
                        result.getString("country"),
                        result.getString("role")));
        }

        return staffs;
    }

    public List<Staff> getStaffByCountry(int clubId, String country) throws SQLException
    {
        List<Staff> staffs = new ArrayList<>();

        String query = "SELECT * FROM staff WHERE country=? AND club_id=?";

        PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
        preparedStatement.setString(1, country);
        preparedStatement.setInt(2, clubId);
        ResultSet result = null;
        try
        {
            result = preparedStatement.executeQuery();
        }
        catch (Exception exception)
        {
            return null;
        }
        finally
        {
            //public Staff(int id, String name, Date birthOfDate, String country, String role)
            while (result.next())
                staffs.add(new Staff(result.getInt("id"),
                        result.getString("name"),
                        result.getDate("birth_of_date"),
                        result.getString("country"),
                        result.getString("role")));
        }

        return staffs;

    }

    public List<Staff> getStaffByRole(int clubId, String role) throws SQLException
    {
        List<Staff> staffs = new ArrayList<>();

        String query = "SELECT * FROM staff WHERE role=? AND club_id=?";

        PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
        preparedStatement.setString(1, role);
        preparedStatement.setInt(2, clubId);
        ResultSet result = null;
        try
        {
            result = preparedStatement.executeQuery();
        }
        catch (Exception exception)
        {
            return null;
        }
        finally
        {
            //public Staff(int id, String name, Date birthOfDate, String country, String role)
            while (result.next())
                staffs.add(new Staff(result.getInt("id"),
                        result.getString("name"),
                        result.getDate("birth_of_date"),
                        result.getString("country"),
                        result.getString("role")));
        }

        return staffs;

    }

    public Staff addStaff(int clubId, Staff staff) throws SQLException
    {
        String name = staff.getName();
        Date birthOfDate = staff.getBirthOfDate();
        String country = staff.getCountry();
        String role = staff.getRole();

        String query = "INSERT INTO staff(name, country, birth_of_date, role, club_Id) VALUES(?,?,?,?,?)";
        PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, country);
        preparedStatement.setDate(3, birthOfDate);
        preparedStatement.setString(4, role);
        preparedStatement.setInt(5, clubId);

        try
        {
            preparedStatement.executeUpdate();
        } catch(Exception exception)
        {
            return null;
        }

        return staff;
    }

    public Staff updateCoach(int clubId, int staffId , Staff staff) throws SQLException
    {
        String name = (staff.getName() == null) ? "" : staff.getName();
        Date birthOfDate = (staff.getBirthOfDate() == null) ? null : staff.getBirthOfDate();
        String country = (staff.getCountry() == null) ? "" : staff.getCountry();
        String role = (staff.getRole() == null) ? "" : staff.getRole();

        String query =
                "UPDATE staff SET " +
                        "name=COALESCE(?,name), " +
                        "country=COALESCE(?,nationality), " +
                        "birth_of_date=COALESCE(?,birth_of_date) " +
                        "role=COALESCE(?,role), " +
                        "WHERE id=? AND club_name=?";

        PreparedStatement preparedStatement = dbConnection.prepareStatement(query);

        preparedStatement.setString(1, name);
        preparedStatement.setString(2, country);
        preparedStatement.setDate(3, birthOfDate);
        preparedStatement.setString(4, role);
        preparedStatement.setInt(5, staffId);
        preparedStatement.setInt(6, clubId);

        try
        {
            preparedStatement.executeUpdate();
        } catch (Exception exception)
        {
            return null;
        }

        return staff;
    }

    public void deleteCoach(int clubId, int coachId) throws SQLException
    {
        String query = "DELETE FROM staff WHERE id=? AND club_id=?";

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
