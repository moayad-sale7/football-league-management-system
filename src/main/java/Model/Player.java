package Model;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;

@XmlRootElement
public class Player extends Person
{
    private String position;

    public Player() {}

    public Player(int id, String name, Date birthOfDate, String nationality, String gender, String position, String clubName)
    {
        super(id, name, birthOfDate, nationality, gender, clubName);
        this.position = position;
    }

    public String getPosition()
    {
        return position;
    }

    public void setPosition(String position)
    {
        this.position = position;
    }

}
