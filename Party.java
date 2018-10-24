/**
 *  FILE: Party.java <br>
 *  PURPOSE: class to store Party info <br>
 *
 *  @author Kei Sum Wang - 19126089
 */
public class Party
{
    private String partyAbrev;
    private String partyNm;
    private DSALinkedList<Division> partyDivList;

    /**
     * DEFAULT Constructor for party
     */
    public Party()
    {
        partyAbrev = "unknown";
        partyNm = "unknown";
    }

    /**
     * ALT Constructor for party
     * @param partyAb party abbreviation string
     * @param partyNm party name
     */
    public Party(String partyAb, String partyNm)
    {
        this.partyAbrev = partyAb;
        this.partyNm = partyNm;
        this.partyDivList = new DSALinkedList<Division>();
    }

    /**
     * method to get party abbreviation
     * @return party abbreviation (String)
     */
    public String getPartyShortName()
    {
        return this.partyAbrev;
    }

    /**
     * method to get party name
     * @return party name (String)
     */
    public String getPartyName()
    {
        return this.partyNm;
    }

    /**
     * method to get list of divisions
     * @return Division list
     */
    public DSALinkedList<Division> getDivList()
    {
        return this.partyDivList;
    }

    /**
     * method to set party abbreviation
     * @param sname abbreviation (String)
     */
    public void setPartyShortName(String sname)
    {
        if(validateString(sname))
        {
            this.partyAbrev = sname;
        }
        else
        {
            throw new IllegalArgumentException("party abbreviation cannot be empty");
        }
    }

    /**
     * method to set party name
     * @param pname party abbreviation (String)
     */
    public void setPartyName(String pname)
    {
        if(validateString(pname))
        {
            this.partyNm = pname;
        }
        else
        {
            throw new IllegalArgumentException("party name cannot be empty");
        }
    }

    /**
     * method to add division to list that party has
     * @param Division
     */
    public void addDivision(Division div)
    {
        this.partyDivList.insertLast(div);
    }

    public String toString()
    {
        return "PARTY:" + this.partyNm + "-(" + this.partyAbrev + ")";
    }

    /**
     * method to validate string
     * @param inStr (String)
     */
    private boolean validateString(String inStr)
    {
        return (!inStr.isEmpty() && inStr != null);
    }
}
