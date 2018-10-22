/**
 *  FILE: Nominee.java <br>
 *  PURPOSE: Nominee class to store Nominee info <br>
 *
 *  @author Kei Sum Wang - 19126089
 */
public class Nominee extends Politician
{
    /**
     * nominee class fields
     */
    private int nomineeId;
    private String state;
    private Division div;
    private int ballotPos;
    private char elected;
    private char HistoricElected;

    /**
     * DEFAULT Constructor for creating nominee.
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
     * ALT Constructor for creating nominee.
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
    /**
     * method to get nominee id.
     * @return nominee id(integer)
     */
    public int getNomineeID()
    {
        return this.nomineeId;
    }

    /**
     * method to get nominee surname.
     * @return nominee surname(String)
     */
    public String getSurname()
    {
        return super.getSurname();
    }

    /**
     * method to get nominee fullname.
     * @return nominee fullname(String)
     */
    public String getFullName()
    {
        return super.getFullName();
    }

    /**
     * method to get state abbreviation.
     * @return state abbreviation(String)
     */
    public String getState()
    {
        return this.state;
    }

    /**
     * method to get division.
     * @return division(Division)
     */
    public Division getDiv()
    {
        return this.div;
    }

    /**
     * method to get division name.
     * @return division name(String)
     */
    public String getDivName()
    {
        return this.div.getDivName();
    }

    /**
     * method to get party name.
     * @return party name(String)
     */
    public String getPartyName()
    {
        return super.getPartyName();
    }

    /**
     * method to get party short name.
     * @return party short name(String)
     */
    public String getPartyShortName()
    {
        return super.getPartyShortName();
    }

    /**
     * method to get ballot position.
     * @return ballot position(integer)
     */
    public int getBalPos()
    {
        return this.ballotPos;
    }

    /**
     * method to get if nominee elected.
     * @return nominee elected(character)
     */
    public char getElected()
    {
        return this.elected;
    }

    /**
     * method to get if nominee historically elected.
     * @return nominee historically elected(character)
     */
    public char getHistoricElected()
    {
        return this.HistoricElected;
    }

    /**
     * method to get a specific field of nominee<br>
     * JUSTIFICATION: This was implemented to make nominee sort implementation<br>
     *                easier without needing to make repeat code and create a sort<br>
     *                for very field, sort will be found in FileIO.java.
     * @param type of field needed (String)
     * @return field (String)
     */
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
    /**
     * method to add poll place nominees list of poll places.
     * @param pollID (integer) -polling place id
     * @param pollplace (String) -polling place name
     * @param ordVotes (integer) -order votes
     * @param swing (real)
     */
    public void addPollPlace(int pollID, String pollPl, int ordVotes, double swing)
    {
        this.div.addPollPlace(this.nomineeId, this.getPartyShortName(), pollID, pollPl, ordVotes, swing);
    }

    /**
     * method to set ballot position.
     * @param position (integer)
     */
    public void setBalPos(int pos)
    {
        this.ballotPos = pos;
    }

    /**
     * method to get parent class toString()<br>
     * this contains less detail than nominee's toString
     * @return parent class toString()
     */
    public String politicianString()
    {
        return super.toString();
    }

    /**
     * method to get nominee details, contains less detail than fullDetails<br>
     * concat state and division to parent class toString
     * @return nominee details (toString)
     */
    public String toString()
    {
        return (super.toString() + ", STATE:" + state + ", DIVISION:" + div.getDivName());
    }

    /**
     * method to get nominee full details.
     * @return nominee details (toString)
     */
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












