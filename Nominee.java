public class Nominee
{
    private String state;
    private int divID;
    private String divNm;
    private int PollPlaceID;
    private String PollPlaceNm;
    private int candidateId;
    private String surname;
    private String firstname;
    private int ballotPos;
    private char elected;
    private char HistoricElected;
    private String partyAbrev;
    private String partyNm;
    private int ordVotes;
    private double swing;

    public Nominee()
    {
        state = "unknown";
        divID = 0;
        divNm = "unknown";
        PollPlaceID = 0;
        PollPlaceNm = "unknown";
        candidateId = 0;
        surname = "unknown";
        firstname = "unknown";
        ballotPos = 0;
        elected = '?';
        HistoricElected = '?';
        partyAbrev = "unknown";
        partyNm = "unknown";
        ordVotes = 0;
        swing = 0.0;
    }

    public Nominee(String state, int divID, String divNm, int pPlID, String pPlNm,
                   int candID, String sname, String fname, int balPos, char elec, 
                   char histElec, String partyAb, String partyNm, int ordVotes, double swing)
    {
        this.state = state;
        this.divID = divID;
        this.divNm = divNm;
        this.PollPlaceID = pPlID;
        this.PollPlaceNm = pPlNm;
        this.candidateId = candID;
        this.surname = sname;
        this.firstname = fname;
        this.ballotPos = balPos;
        this.elected = elec;
        this.HistoricElected = histElec;
        this.partyAbrev = partyAb;
        this.partyNm = partyNm;
        this.ordVotes = ordVotes;
        this.swing = swing;
    }

}












