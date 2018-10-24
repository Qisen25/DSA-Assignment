import java.util.*;
/**
 *  FILE: Division.java <br>
 *  PURPOSE: class to store Division info <br>
 *
 *  @author Kei Sum Wang - 19126089
 */
public class Division
{
    private int divID;
    private String divNm;
    private String state;
    private DSALinkedList<PollPlace> pollList;

    /**
     * DEFAULT constructor for creating Division
     */
    public Division()
    {
        divID = 0;
        divNm = "unknown";
        pollList = new DSALinkedList<PollPlace>();
    }

    /**
     * ALT constructor for creating Division
     * @param divID(integer)
     * @param divNm(String)
     * @param state(String)
     */
    public Division(int divID, String divNm, String state)
    {
        if(validateString(divNm))
        {
            this.divID = divID;
            this.divNm = divNm;
            this.state = state;
            pollList = new DSALinkedList<PollPlace>();
        }
        else
        {
            throw new IllegalArgumentException("division name cannot be empty");
        }
    }

//ACCESSORS
    /**
     * method to get division id
     * @return division id(integer)
     */
    public int getID()
    {
        return this.divID;
    }

    /**
     * method to get division name
     * @return division name(String)
     */
    public String getDivName()
    {
        return this.divNm;
    }

    /**
     * method to get state abbreviation
     * @return state abbreviation(String)
     */
    public String getState()
    {
        return this.state;
    }

    /**
     * method to get total votes for party specified
     * @return total party votes(integer)
     */
    public int getTotalPartyVotes(String partyAb)
    {
        int total = 0;
        PollPlace p = null;
        Iterator<PollPlace> it = pollList.iterator();

        while(it.hasNext())
        {
            p = it.next();
            if(p.getPartyAb().equals(partyAb))
            {
                total += p.getOrderVotes();
            }
        }
        return total;
    }

    /**
     * method to get total votes of current division
     * @return total votes in division(integer)
     */
    public int getTotalVotes()
    {
        int total = 0;
        PollPlace p = null;
        Iterator<PollPlace> it = pollList.iterator();

        while(it.hasNext())
        {
            p = it.next();
            total += p.getOrderVotes();
            //total += 1;
        }
        return total;
    }

    /**
     * method to get margins for specified party
     * @return margin(real)
     */
    public double getMargin(String partyAb)
    {
        double margin;

        margin = (((double)this.getTotalPartyVotes(partyAb) / (double)this.getTotalVotes())*100) - 50;

        return margin;
    }

//MUTATORS
    /**
     * method to set division id
     * @param id(integer)
     */
    public void setID(int id)
    {
        this.divID = id;
    }

    /**
     * method to set state abbreviation
     * @param inState(String)
     */
    public void setState(String inState)
    {
        this.state = inState;
    }

    /**
     * method to add poll place to current division poll place list
     * @param candID(integer) -nominee id
     * @param party(String) -party abbreviation
     * @param pollID(integer) -poll place id
     * @param pollPl(string) -poll place name
     * @param ordVotes(integer) -order votes
     * @param swing(real) -swing
     */
    public void addPollPlace(int candID, String party, int pollID, String pollPl, int ordVotes, double swing)
    {
        this.pollList.insertLast(new PollPlace(candID, party, pollID, pollPl, ordVotes, swing));
    }

    /**
     * method to set division name
     * @param division name(String)
     */
    public void setDivName(String name)
    {
        if(validateString(name))
        {
            this.divNm = name;
        }
        else
        {
            throw new IllegalArgumentException("Surname cannot be empty");
        }
    }

    public String toString()
    {
        return "(ID)" + this.divID + ", " + this.divNm + "(" + state + ")";
    }

    /**
     * method gives details of division and margins of specified party
     * @param party abbreviation(String)
     * @return division details along with party margin(String)
     */
    public String toString(String partyAb)
    {
        return "DIVISION:" + "(ID)" + this.divID + "," + this.divNm + "(" + state + ")" + ",MARGIN:" + this.getMargin(partyAb);
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
