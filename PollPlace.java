/**
 *  FILE: PollPlace.java <br>
 *  PURPOSE: class to store PollPlace info <br>
 *
 *  @author Kei Sum Wang - 19126089
 */
public class PollPlace
{
    private int candID;
    private String partyAb;
    private int pollPlaceID;
    private String pollPlaceNm;
    private int ordVotes;
    private double swing;

    public PollPlace()
    {
        candID = 0;
        partyAb = "unknown";
        pollPlaceID = 0;
        pollPlaceNm = "unknown";
        ordVotes = 0;
        swing = 0.0;
    }

    public PollPlace(int cand, String party, int pPlID, String pPlNm, int ord, double sw)
    {
        if(validateString(pPlNm))
        {
            this.candID = cand;
            this.partyAb = party;
            this.pollPlaceID = pPlID;
            this.pollPlaceNm = pPlNm;
            this.ordVotes = ord;
            this.swing = sw;
        }
        else
        {
            throw new IllegalArgumentException("Poll place name cannot be empty");
        }
    }

//ACCESSORS
    public int getCandID()
    {
        return this.pollPlaceID;
    }

    public String getPartyAb()
    {
        return this.partyAb;
    }

    public int getPollPlID()
    {
        return this.pollPlaceID;
    }

    public String getPollPlName()
    {
        return this.pollPlaceNm;
    }

    public int getOrderVotes()
    {
        return this.ordVotes;
    }

    public double getSwing()
    {
        return this.swing;
    }

//MUTATORS
    public void setPollPlID(int id)
    {
        this.pollPlaceID = id;
    }

    public void setPollPlName(String name)
    {
        if(validateString(name))
        {
            this.pollPlaceNm = name;
        }
        else
        {
            throw new IllegalArgumentException("First name cannot be empty");
        }
    }

    public String toString()
    {
        return this.pollPlaceID + "," + this.pollPlaceNm;
    }

//PRIVATE
    /**
     * method to validate string
     * @param inStr (String)
     */
    private boolean validateString(String inStr)
    {
        return (!inStr.isEmpty() && inStr != null);
    }
}
