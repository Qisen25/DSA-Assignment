public class Division
{
    private int divID;
    private String divNm;
    private DSALinkedList<PollPlace> pollList;

    public Division()
    {
        divID = 0;
        divNm = "unknown";
        pollList = new DSALinkedList<PollPlace>();
    }

    public Division(int divID, String divNm)
    {
        if(validateString(divNm))
        {
            this.divID = divID;
            this.divNm = divNm;
            pollList = new DSALinkedList<PollPlace>();
        }
        else
        {
            throw new IllegalArgumentException("division name cannot be empty");
        }
    }

//ACCESSORS
    public int getID()
    {
        return this.divID;
    }

    public String getDivName()
    {
        return this.divNm;
    }

//MUTATORS
    public void setID(int id)
    {
        this.divID = id;
    }
    public void addPollPlace(int pollID, String pollPl, int ordVotes, double swing)
    {
        this.pollList.insertLast(new PollPlace(pollID, pollPl, ordVotes, swing));
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

    public String toString()
    {
        return this.divID + "," + this.divNm;
    }

//PRIVATE
    private boolean validateString(String inStr)
    {
        return (!inStr.isEmpty() && inStr != null);
    }
}
