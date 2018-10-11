public class Division
{
    private int divID;
    private String divNm;
    private int pollPlaceID;
    private String pollPlaceNm;

    public Division()
    {
        divID = 0;
        divNm = "unknown";
        pollPlaceID = 0;
        pollPlaceNm = "unknown";
    }

    public Division(int divID, String divNm, int pPlID, String pPlNm)
    {
        this.divID = divID;
        this.divNm = divNm;
        this.pollPlaceID = pPlID;
        this.pollPlaceNm = pPlNm;
    }

    public int getID()
    {
        return this.divID;
    }

    public String getDivName()
    {
        return this.divNm;
    }

    public int getPollPlID()
    {
        return this.pollPlaceID;
    }

    public String getPollPlName()
    {
        return this.pollPlaceNm;
    }

    public void setID(int id)
    {
        this.divID = id;
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

    public void setPollPlID(int id)
    {
        this.pollPlaceID = id;
    }

    public void setPollPlName(String name)
    {
        if(validateString(name))
        {
            this.pollPlaceNm = name;
        }
        else
        {
            throw new IllegalArgumentException("First name cannot be empty");
        }
    }

    public String toString()
    {
        return this.divID + "," + this.divNm + "," + this.pollPlaceID + "," + this.pollPlaceNm;
    }


    private boolean validateString(String inStr)
    {
        return (!inStr.isEmpty() && inStr != null);
    }
}
