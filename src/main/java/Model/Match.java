package Model;

import java.util.Date;

public class Match
{
    private int id;
    private String firstClub;
    private String secondClub;
    private Date dateOfMatch;
    private boolean isFinished;
    private String winner;
    private int firstClubScore;
    private int secondClubScore;

    public Match() {}

    public Match(int id, String firstClub, String secondClub, Date dateOfMatch, boolean isFinished, String winner, int firstClubScore, int secondClubScore)
    {
        this.id = id;
        this.firstClub = firstClub;
        this.secondClub = secondClub;
        this.dateOfMatch = dateOfMatch;
        this.isFinished = isFinished;
        this.winner = winner;
        this.firstClubScore = firstClubScore;
        this.secondClubScore = secondClubScore;
    }

    public String getFirstClub()
    {
        return firstClub;
    }

    public void setFirstClub(String firstClub)
    {
        this.firstClub = firstClub;
    }

    public String getSecondClub()
    {
        return secondClub;
    }

    public void setSecondClub(String secondClub)
    {
        this.secondClub = secondClub;
    }

    public Date getDateOfMatch()
    {
        return dateOfMatch;
    }

    public void setDateOfMatch(Date dateOfMatch)
    {
        this.dateOfMatch = dateOfMatch;
    }

    public boolean isFinished()
    {
        return isFinished;
    }

    public void setFinished(boolean finished)
    {
        isFinished = finished;
    }

    public String getWinner()
    {
        return winner;
    }

    public void setWinner(String winner)
    {
        this.winner = winner;
    }

    public int getFirstClubScore()
    {
        return firstClubScore;
    }

    public void setFirstClubScore(int firstClubScore)
    {
        this.firstClubScore = firstClubScore;
    }

    public int getSecondClubScore()
    {
        return secondClubScore;
    }

    public void setSecondClubScore(int secondClubScore)
    {
        this.secondClubScore = secondClubScore;
    }
}
