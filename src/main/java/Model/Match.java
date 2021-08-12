package Model;

import java.sql.Date;

public class Match
{
    private int id;
    private String clubHome;
    private String clubAway;
    private Date dateOfMatch;
    private String status;
    private String winner;
    private int clubHomeScore;
    private int clubAwayScore;

    public Match() {}

    public Match(int id, String clubHome, String clubAway, Date dateOfMatch,
                 String status, String winner, int clubHomeScore, int clubAwayScore)
    {
        this.id = id;
        this.clubHome = clubHome;
        this.clubAway = clubAway;
        this.dateOfMatch = dateOfMatch;
        this.status = status;
        this.winner = winner;
        this.clubHomeScore = clubHomeScore;
        this.clubAwayScore = clubAwayScore;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getClubHome()
    {
        return clubHome;
    }

    public void setClubHome(String clubHome)
    {
        this.clubHome = clubHome;
    }

    public String getClubAway()
    {
        return clubAway;
    }

    public void setClubAway(String clubAway)
    {
        this.clubAway = clubAway;
    }

    public Date getDateOfMatch()
    {
        return dateOfMatch;
    }

    public void setDateOfMatch(Date dateOfMatch)
    {
        this.dateOfMatch = dateOfMatch;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getWinner()
    {
        return winner;
    }

    public void setWinner(String winner)
    {
        this.winner = winner;
    }

    public int getClubHomeScore()
    {
        return clubHomeScore;
    }

    public void setClubHomeScore(int clubHomeScore)
    {
        this.clubHomeScore = clubHomeScore;
    }

    public int getClubAwayScore()
    {
        return clubAwayScore;
    }

    public void setClubAwayScore(int clubAwayScore)
    {
        this.clubAwayScore = clubAwayScore;
    }
}
