package Model;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;

@XmlRootElement
public class Coach extends Person
{
    public Coach() {}

    public Coach(int id, String name, Date birthOfDate, String nationality, String gender, String clubName)
    {
        super(id, name, birthOfDate, nationality, gender, clubName);
    }
}
