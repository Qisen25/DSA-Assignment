/**
 *  FILE: PollPlace.java <br>
 *  PURPOSE: class to store PollPlace info <br>
 *
 *  @author Kei Sum Wang - 19126089
 */
public class PollPlace
{
    /**
     * poll place class fields
     */
    private int candID;
    private String partyAb;
    private int pollPlaceID;
    private String pollPlaceNm;
    private int ordVotes;
    private double swing;

    /**
     * DEFAULT Constructor for creating poll place
     */
    public PollPlace()
    {
        candID = 0;
        partyAb = "unknown";
        pollPlaceID = 0;
        pollPlaceNm = "unknown";
        ordVotes = 0;
        swing = 0.0;
    }

    /**
     * ALT Constructor for creating poll place.
     * @param cand(integer) -nominee id
     * @param party(String) -party abbreviation
     * @param pPlID(integer) -poll place id
     * @param pPlNm(String) -poll place name
     * @param ord(integer) -order votes
     * @param sw(real) -swing
     */
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
    /**
     * method to get nominee id
     * @return nominee id(integer)
     */
    public int getCandID()
    {
        return this.candID;
    }

    /**
     * method to get party abbreviation
     * @return party abbreviation(String)
     */
    public String getPartyAb()
    {
        return this.partyAb;
    }

    /**
     * method to get poll place id
     * @return poll place id(integer)
     */
    public int getPollPlID()
    {
        return this.pollPlaceID;
    }

    /**
     * method to get poll place name
     * @return poll place name(String)
     */
    public String getPollPlName()
    {
        return this.pollPlaceNm;
    }

    /**
     * method to get order votes
     * @return order votes(integer)
     */
    public int getOrderVotes()
    {
        return this.ordVotes;
    }

    /**
     * method to get swing
     * @return swing(real)
     */
    public double getSwing()
    {
        return this.swing;
    }

//MUTATORS
    /**
     * method to set poll place id
     * @param poll place id(integer)
     */
    public void setPollPlID(int id)
    {
        this.pollPlaceID = id;
    }

    /**
     * method to set poll place name
     * @param poll place name(String)
     */
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
