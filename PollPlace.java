public class PollPlace
{
    private int pollPlaceID;
    private String pollPlaceNm;
    private int ordVotes;
    private double swing;

    public PollPlace()
    {
        pollPlaceID = 0;
        pollPlaceNm = "unknown";
        ordVotes = 0;
        swing = 0.0;
    }

    public PollPlace(int pPlID, String pPlNm, int ordVotes, double swing)
    {
        if(validateString(pPlNm))
        {
            this.pollPlaceID = pPlID;
            this.pollPlaceNm = pPlNm;
            this.ordVotes = ordVotes;
            this.swing = swing;
        }
        else
        {
            throw new IllegalArgumentException("Poll place name cannot be empty");
        }
    }

//ACCESSORS
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
    private boolean validateString(String inStr)
    {
        return (!inStr.isEmpty() && inStr != null);
    }
}
