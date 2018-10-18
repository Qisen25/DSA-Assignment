public class Nominee extends Politician
{
    private int nomineeId;
    private String state;
    //TODO make division id and/or name a nominee field
    private Division div;
    private int ballotPos;
    private char elected;
    private char HistoricElected;

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
        return ("ID: " + nomineeId + "," + super.toString() + ",STATE: " + state + ",DIVISION: " + div.toString() + ",BALLOT POSITION: " + ballotPos +
                ",ELECTED: " + elected + ",HISTORIC ELECTED: " + HistoricElected);
    }

//PRIVATE
    private boolean validateString(String inStr)
    {
        return (!inStr.isEmpty() && inStr != null);
    }

}












