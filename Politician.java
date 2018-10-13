public class Politician
{
    private String surname;
    private String firstname;
    private String partyAbrev;
    private String partyNm;

    public Politician()
    {
        surname = "unknown";
        firstname = "unknown";
        partyAbrev = "unknown";
        partyNm = "unknown";
    }

    public Politician(String sname, String fname, String partyAb, String partyNm)
    {
        if(validateString(sname) && validateString(fname))
        {
            this.surname = sname;
            this.firstname = fname;
            this.partyAbrev = partyAb;
            this.partyNm = partyNm;
        }
        else
        {
            throw new IllegalArgumentException("All politician name fields cannot be empty");
        }
    }

    public String getSurname()
    {
        return this.surname;
    }

    public String getFirstname()
    {
        return this.firstname;
    }

    public String getFullName()
    {
        return this.surname + " " + this.firstname;
    }

    public String getPartyShortName()
    {
        return this.partyAbrev;
    }

    public String getPartyName()
    {
        return this.partyNm;
    }

    public void setSurname(String sname)
    {
        if(validateString(sname))
        {
            this.surname = sname;
        }
        else
        {
            throw new IllegalArgumentException("Surname cannot be empty");
        }
    }

    public void setFirstname(String fname)
    {
        if(validateString(fname))
        {
            this.firstname = fname;
        }
        else
        {
            throw new IllegalArgumentException("First name cannot be empty");
        }
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

    public String toString()
    {
        return this.getFullName() + "," + this.partyNm + "," + this.partyAbrev;
    }

    private boolean validateString(String inStr)
    {
        return (!inStr.isEmpty() && inStr != null);
    }
}
