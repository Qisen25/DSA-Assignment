/*
 *Author: Kei Wang - 19126089
 *Politician class
 */
public class Politician
{
    private String surname;
    private String firstname;
    private Party party;

//DEFAULT CONSTRUCTOR
    public Politician()
    {
        surname = "unknown";
        firstname = "unknown";
        party = new Party();
    }

//ALTERNATE CONSTRUCTOR
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
        return this.firstname + " " + this.surname;
    }

//MUTATORS
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

    public String getPartyShortName()
    {
        return this.party.getPartyShortName();
    }

    public String getPartyName()
    {
        return this.party.getPartyName();
    }

    public String toString()
    {
        return "FULLNAME:" + this.getFullName() + ", " + this.party.toString();
    }

//PRIVATE
    private boolean validateString(String inStr)
    {
        return (!inStr.isEmpty() && inStr != null);
    }
}
