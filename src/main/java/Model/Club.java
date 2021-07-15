package Model;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;


@XmlRootElement
public class Club
{
    private int id;
    private String name;
    private Date foundingOfDate;

    public Club() {}

    public Club(int id, String name, Date foundingOfDate)
    {
        this.id = id;
        this.name = name;
        this.foundingOfDate = foundingOfDate;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Date getFoundingOfDate()
    {
        return foundingOfDate;
    }

    public void setFoundingOfDate(Date foundingOfDate)
    {
        this.foundingOfDate = foundingOfDate;
    }

}
