package Model;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;

@XmlRootElement
public class Player extends Person
{
    private String position;

    public Player() {}

    public Player(int id, String name, Date birthOfDate, String country, String position)
    {
        super(id, name, birthOfDate, country);
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
