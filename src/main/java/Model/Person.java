package Model;


import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;

@XmlRootElement
public class Person
{
    private int id;
    private String name;
    private Date birthOfDate;
    private String nationality;
    private String gender;
    private String clubName;

    public Person() {}

    public Person(int id, String name, Date birthOfDate, String nationality, String gender, String clubName)
    {
        this.id = id;
        this.name = name;
        this.birthOfDate = birthOfDate;
        this.nationality = nationality;
        this.gender = gender;
        this.clubName = clubName;
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

    public String getNationality()
    {
        return nationality;
    }

    public void setNationality(String nationality)
    {
        this.nationality = nationality;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }
}
