public class Party
{
    private String partyAbrev;
    private String partyNm;
    private DSALinkedList<Division> partyDivList;

    public Party()
    {
        partyAbrev = "unknown";
        partyNm = "unknown";
    }

    public Party(String partyAb, String partyNm)
    {
        this.partyAbrev = partyAb;
        this.partyNm = partyNm;
        this.partyDivList = new DSALinkedList<Division>();
    }

    public String getPartyShortName()
    {
        return this.partyAbrev;
    }

    public String getPartyName()
    {
        return this.partyNm;
    }

    public DSALinkedList<Division> getDivList()
    {
        return this.partyDivList;
    }

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

    public void setPartyName(String fname)
    {
        if(validateString(fname))
        {
            this.partyNm = fname;
        }
        else
        {
            throw new IllegalArgumentException("party name cannot be empty");
        }
    }

    public void addDivision(Division div)
    {
        this.partyDivList.insertLast(div);
    }

    public String toString()
    {
        return this.partyNm + "(" + this.partyAbrev + ")";
    }

    private boolean validateString(String inStr)
    {
        return (!inStr.isEmpty() && inStr != null);
    }
}
