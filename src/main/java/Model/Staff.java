package Model;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;

@XmlRootElement
public class Staff extends Person
{
    private String role;

    public Staff() {}

    public Staff(int id, String name, Date birthOfDate, String country, String role)
    {
        super(id, name, birthOfDate, country);
        this.role = role;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }
}
