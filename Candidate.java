public class Candidate
{
    private int candidateId;
    private String surname;
    private String firstname;

    public Candidate()
    {
        candidateId = 0;
        surname = "unknown";
        firstname = "unknown";
    }

    public Candidate(int candId, String sname, String fname)
    {
        this.candidateId = candId;
        this.surname = sname;
        this.firstname = fname;
    }

    public int getID()
    {
        return this.candidateId;
    }

    public String getSurname()
    {
        return this.surname;
    }

    public String getFirstname()
    {
        return this.firstname;
    }

    public void setID(int id)
    {
        this.candidateId = id;
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

    public String toString()
    {
        return this.candidateId + "," + this.firstname + "," + this.surname;
    }

    private boolean validateString(String inStr)
    {
        return (!inStr.isEmpty() && inStr != null);
    }
}
