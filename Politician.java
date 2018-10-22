/**
 *  FILE: Politician.java <br>
 *  PURPOSE: class to store Politician info <br>
 *
 *  @author Kei Sum Wang - 19126089
 */
public class Politician
{
    /**
     * politician class fields
     */
    private String surname;
    private String firstname;
    private Party party;

//DEFAULT CONSTRUCTOR
    /**
     * DEFAULT Constructor for creating politician
     */
    public Politician()
    {
        surname = "unknown";
        firstname = "unknown";
        party = new Party();
    }

//ALTERNATE CONSTRUCTOR
    /**
     * ALT Constructor for creating politician
     * @param sname surname(String)
     * @param fname first name(String)
     * @param party (Party)
     */
    public Politician(String sname, String fname, Party party)
    {
        if(validateString(sname) && validateString(fname))
        {
            this.surname = sname;
            this.firstname = fname;
            this.party = party;
        }
        else
        {
            throw new IllegalArgumentException("All politician name fields cannot be empty");
        }
    }

//ACCESSORS
    /**
     * method to get surname
     * @return surname (String)
     */
    public String getSurname()
    {
        return this.surname;
    }

    /**
     * method to get first name
     * @return firstname (String)
     */
    public String getFirstname()
    {
        return this.firstname;
    }

    /**
     * method to get fullname
     * @return fullname (String)
     */
    public String getFullName()
    {
        return this.firstname + " " + this.surname;
    }

    /**
     * method to get party abbreviation
     * @return party abbreviation (String)
     */
    public String getPartyShortName()
    {
        return this.party.getPartyShortName();
    }

    /**
     * method to get party name
     * @return partyname (String)
     */
    public String getPartyName()
    {
        return this.party.getPartyName();
    }

//MUTATORS
    /**
     * method to set surname
     * @param sname (String)
     */
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

    /**
     * method to set firstname
     * @param fname (String)
     */
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

    public String toString()
    {
        return "FULLNAME:" + this.getFullName() + ", " + this.party.toString();
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
