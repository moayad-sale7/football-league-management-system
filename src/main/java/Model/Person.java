package Model;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;

@XmlRootElement
public class Person
{
    private int id;
    private String name;
    private Date birthOfDate;
    private String country;

    public Person() {}

    public Person(int id, String name, Date birthOfDate, String country)
    {
        this.id = id;
        this.name = name;
        this.birthOfDate = birthOfDate;
        this.country = country;
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

    public Date getBirthOfDate()
    {
        return birthOfDate;
    }

    public void setBirthOfDate(Date birthOfDate)
    {
        this.birthOfDate = birthOfDate;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }
}
