/**
 *  FILE: Nominee.java <br>
 *  PURPOSE: Nominee class to store Nominee info <br>
 *
 *  @author Kei Sum Wang - 19126089
 */
public class Nominee extends Politician
{
    private int nomineeId;
    private String state;
    //TODO make division id and/or name a nominee field
    private Division div;
    private int ballotPos;
    private char elected;
    private char HistoricElected;

    /**
     * DEFAULT Constructor for creating politician
     */
    public Nominee()
    {
        super();
        nomineeId = 0;
        state = "unknown";
        div = new Division();
        ballotPos = 0;
        elected = '?';
        HistoricElected = '?';
    }

    /**
     * DEFAULT Constructor for creating politician
     * @param state(String) -state abbreviation
     * @param div(Division) -Division
     * @param candId(Integer) -nominee id
     * @param sname(String) -surname
     * @param fname(String) -first name
     * @param balPos(Integer) -ballot position
     * @param elec(Character) -elected
     * @param histElec(Character) -historically elected
     * @param party (Party)
     */
    public Nominee(String state, Division div, int candId, String sname, String fname, 
                   int balPos, char elec, char histElec, Party party)
    {
        super(sname, fname, party);
        if(validateString(state))
        {
            this.nomineeId = candId;
            this.state = state;
            this.div = div;
            this.ballotPos = balPos;
            this.elected = elec;
            this.HistoricElected = histElec;
        }
        else
        {
            throw new IllegalArgumentException("name of state cannot be empty");
        }
    }

//ACCESSORS
    public int getNomineeID()
    {
        return this.nomineeId;
    }

    public String getSurname()
    {
        return super.getSurname();
    }

    public String getFullName()
    {
        return super.getFullName();
    }

    public String getState()
    {
        return this.state;
    }

    public Division getDiv()
    {
        return this.div;
    }

    public String getDivName()
    {
        return this.div.getDivName();
    }

    public String getPartyName()
    {
        return super.getPartyName();
    }

    public String getPartyShortName()
    {
        return super.getPartyShortName();
    }

    public int getBalPos()
    {
        return this.ballotPos;
    }

    public char getElected()
    {
        return this.elected;
    }

    public char getHistoricElected()
    {
        return this.HistoricElected;
    }

    public String getField(String type)
    {
        String str = "";

        if(type.equals("sname"))
        {
            str = this.getSurname();
        }
        else if(type.equals("statename"))
        {
            str = this.state;
        }
        else if(type.equals("partysname"))
        {
            str = this.getPartyShortName();
        }
        else if(type.equals("divname"))
        {
            str = this.getDivName();
        }
        else if(type.equals("ALL"))
        {
            str = this.getSurname() + "," + this.state + "," +
                  this.getPartyShortName() + "," + this.getDivName();
        }
        return str;
    }

//MUTATOR    
    public void addPollPlace(int pollID, String pollPl, int ordVotes, double swing)
    {
        this.div.addPollPlace(this.nomineeId, this.getPartyShortName(), pollID, pollPl, ordVotes, swing);
    }

    public void setBalPos(int pos)
    {
        this.ballotPos = pos;
    }

    public String politicianString()
    {
        return super.toString();
    }

    public String toString()
    {
        return (super.toString() + ", STATE:" + state + ", DIVISION:" + div.getDivName());
    }

    public String fullDetails()
    {
        return ("ID:" + nomineeId + "," + super.toString() + ",STATE:" + state + ",DIVISION:" + div.toString() + ",BALLOT POSITION:" + ballotPos +
                ",ELECTED:" + elected + ",HISTORIC ELECTED:" + HistoricElected);
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












