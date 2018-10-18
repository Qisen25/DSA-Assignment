import java.util.*;
public class Division
{
    private int divID;
    private String divNm;
    private String state;
    private DSALinkedList<PollPlace> pollList;

    public Division()
    {
        divID = 0;
        divNm = "unknown";
        pollList = new DSALinkedList<PollPlace>();
    }

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
    public int getID()
    {
        return this.divID;
    }

    public String getDivName()
    {
        return this.divNm;
    }

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

    public double getMargin(String partyAb)
    {
        double margin;

        margin = (((double)this.getTotalPartyVotes(partyAb) / (double)this.getTotalVotes())*100) - 50;

        return margin;
    }

//MUTATORS
    public void setID(int id)
    {
        this.divID = id;
    }

    public void setState(String inState)
    {
        this.state = inState;
    }

    public void addPollPlace(int candID, String party, int pollID, String pollPl, int ordVotes, double swing)
    {
        this.pollList.insertLast(new PollPlace(candID, party, pollID, pollPl, ordVotes, swing));
    }

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

    public String toString(String partyAb)
    {
        return "DIVISION:" + "(ID)" + this.divID + "," + this.divNm + "(" + state + ")" + ",MARGIN:" + this.getMargin(partyAb);
    }

//PRIVATE
    private boolean validateString(String inStr)
    {
        return (!inStr.isEmpty() && inStr != null);
    }
}
