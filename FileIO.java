import java.util.*;
import java.io.*;
public class FileIO
{
    private String fileName;
    private DSALinkedList<Party> partyList;
    private DSALinkedList<Division> divList;
    private DSALinkedList<Nominee> nomList;// consider changing to array;
    //for insertion sort easier
    private boolean houseConstruct;

    public FileIO()
    {
        partyList = new DSALinkedList<Party>();
        divList = new DSALinkedList<Division>();
        nomList = new DSALinkedList<Nominee>();
        houseConstruct = false;
    }

    public void readHouseCand(String file)
    {
        int ii = 0;
        int divID, pollID, candID;
        String state, divName, sname, fname, partyAb, partyName;
        char elect, HistElect;
        Nominee nominee = null;
        Division div;
        Party party;
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        String line;
        String[] lineArray, paramSplit;

        try
        {
            fileStrm = new FileInputStream(file);    
            rdr = new InputStreamReader(fileStrm);    
            bufRdr = new BufferedReader(rdr); 

            line = bufRdr.readLine();
            while (line != null)
            {
                if(ii > 1)
                {
                    //System.out.println(ii);
                    lineArray = line.split(",");
                    //division fields
    
                    state = lineArray[0];
                    divID = Integer.parseInt(lineArray[1]);
                    divName = lineArray[2];
                    partyAb = lineArray[3];
                    partyName = lineArray[4];
                    //    System.out.println(partyName);
                    if(partyName.equals("\"Shooters"))
                    {
                        partyName = lineArray[4] + "," + lineArray[5];
                        candID = Integer.parseInt(lineArray[6]);
                        sname = lineArray[7];
                        fname = lineArray[8];
                        elect = lineArray[9].charAt(0);
                        HistElect = lineArray[10].charAt(0);
                    }
                    else
                    {
                        candID = Integer.parseInt(lineArray[5]);
                        sname = lineArray[6];
                        fname = lineArray[7];
                        elect = lineArray[8].charAt(0);
                        HistElect = lineArray[9].charAt(0);
                    }
                    
                    if(!divExist(divID))
                    {
                        div = new Division(divID, divName, state);
                        divList.insertLast(div);
                    }
                    
                    if(!partyExist(partyAb))
                    {
                        party = new Party(partyAb, partyName);
                        partyList.insertLast(party);
                    }

                    party = findParty(partyAb);
                    div = findDiv(divID);
                    party.addDivision(div);
                    nominee = new Nominee(state, div, candID, sname, fname, 0, elect, HistElect, party);
                    nomList.insertLast(nominee);
                }
                ii++;
                line = bufRdr.readLine();
            }
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void readStateRep(String file)
    {
        int ii = 0;
        int divID, pollID, candID, balPos, ordVotes;
        String state, divName, pollPlace, sname, fname, partyAb, partyName;
        char elect, HistElect;
        double swing; 
        Nominee nominee = null;
        Division div;
        Party party;
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        String line;
        String[] lineArray, paramSplit;

        try
        {
            fileStrm = new FileInputStream(file);    
            rdr = new InputStreamReader(fileStrm);    
            bufRdr = new BufferedReader(rdr); 

            line = bufRdr.readLine();
            while (line != null)
            {
                if(ii > 1)
                {
                    //System.out.println(ii);
                    lineArray = line.split(",");
                    //division fields
                    divID = Integer.parseInt(lineArray[1]);
                    divName = lineArray[2];
                    pollID = Integer.parseInt(lineArray[3]);
                    pollPlace = lineArray[4];
    
                    state = lineArray[0];
                    candID = Integer.parseInt(lineArray[5]);
                    sname = lineArray[6];
                    fname = lineArray[7];
                    balPos = Integer.parseInt(lineArray[8]);
                    elect = lineArray[9].charAt(0);
                    HistElect = lineArray[10].charAt(0);
                    partyAb = lineArray[11];
                    partyName = lineArray[12];
                    //    System.out.println(partyName);
                    if(partyName.equals("\"Shooters"))
                    {
                        partyName = lineArray[12] + "," + lineArray[13];
                        ordVotes = Integer.parseInt(lineArray[14]);
                        swing = Double.parseDouble(lineArray[15]);
                    }
                    else
                    {
                        ordVotes = Integer.parseInt(lineArray[13]);
                        swing = Double.parseDouble(lineArray[14]);
                    }
                    
                    if(!divExist(divID))
                    {
                        div = new Division(divID, divName, state);
                        divList.insertLast(div);
                    }
                    
                    if(!partyExist(partyAb))
                    {
                        party = new Party(partyAb, partyName);
                        partyList.insertLast(party);
                    }
                    
                    div = findDiv(divID);
                    div.addPollPlace(candID, partyAb, pollID, pollPlace, ordVotes, swing);
                    party = findParty(partyAb);
                    if(!nomExist(candID))
                    {
                        nominee = new Nominee(state, div, candID, sname, fname, balPos, elect, HistElect, party);
                        nomList.insertLast(nominee);
                    }
                    else
                    {
                        findNom(candID).setBalPos(balPos);
                    //findNom(candID).addPollPlace(pollID, pollPlace, ordVotes, swing);
                    }
                }
                ii++;
                line = bufRdr.readLine();
            }
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public boolean getHouseConstructed()
    {
        return this.houseConstruct;
    }

    public boolean readDirFiles()
    {
        boolean fileFound = false;
        File scanDir;
        File[] files;
        String fileNeed, currDir;

        currDir = ".";
        scanDir = new File(currDir);
        files = scanDir.listFiles();

        this.readHouseCand("HouseCandidatesDownload-20499.csv");
        for(int i = 0; i < files.length; i++)
        {
            if(files[i].isFile())
            {
                fileNeed = files[i].getName();
                if(fileNeed.startsWith("HouseStateFirstPrefsByPollingPlaceDownload-") &&
                    fileNeed.endsWith(".csv"))
                {
                    this.readStateRep(fileNeed);
                    fileFound = true;
                }
            }
        }

        return fileFound;
    }


    public void printAll()
    {
        Nominee nom = null;
        Iterator<Nominee> it = nomList.iterator();
        while(it.hasNext())
        {
            nom = it.next();
            System.out.println(nom.getFullName());
        }
    }

    public boolean nomExist(int id)
    {
        boolean found = false;
        Nominee nom = null;
        Iterator<Nominee> it = nomList.iterator();

        while(it.hasNext() && !found)
        {
            nom = it.next();
            if(nom.getNomineeID() == id)
            {
                found = true;
            }
        }
        return found;
    }

    public boolean partyExist(String partyAb)
    {
        boolean found = false;
        Party p = null;
        Iterator<Party> it = partyList.iterator();

        while(it.hasNext() && !found)
        {
            p = it.next();
            if(p.getPartyShortName().equals(partyAb))
            {
                found = true;
            }
        }
        return found;
    }

    public boolean divExist(int id)
    {
        boolean found = false;
        Division div = null;
        Iterator<Division> it = divList.iterator();

        while(it.hasNext() && !found)
        {
            div = it.next();
            if(div.getID() == id)
            {
                found = true;
            }
        }
        return found;
    }

    public Nominee findNom(int id)
    {
        boolean found = false;
        Nominee nom = null;
        Iterator<Nominee> it = nomList.iterator();

        while(it.hasNext() && !found)
        {
            nom = it.next();
            if(nom.getNomineeID() == id)
            {
                found = true;
            }
        }
        return nom;
    }

    public Party findParty(String partyAb)
    {
        boolean found = false;
        Party p = null;
        Iterator<Party> it = partyList.iterator();

        while(it.hasNext() && !found)
        {
            p = it.next();
            if(p.getPartyShortName().equals(partyAb))
            {
                found = true;
            }
        }
        return p;
    }

    public Division findDiv(int id)
    {
        boolean found = false;
        Division div = null;
        Iterator<Division> it = divList.iterator();

        while(it.hasNext() && !found)
        {
            div = it.next();
            if(div.getID() == id)
            {
                found = true;
            }
        }
        return div;
    }

    public void listByState(String state)
    {
        Nominee nom = null;
        Iterator<Nominee> it = nomList.iterator();
        
        if(state.equalsIgnoreCase("all"))
        {
            System.out.println("==List of nominees from " + state + " states==");
        }
        else
        {
            System.out.println("==List of nominees from " + state + " state==");
        }

        while(it.hasNext())
        {
            nom = it.next();
            if(nom.getState().equalsIgnoreCase(state))
            {
                System.out.println(nom.politicianString());
            }
            else if(state.equalsIgnoreCase("all"))
            {
                System.out.println(nom.politicianString());
            }
        }
    }    

    public void listByParty(String party)
    {
        Nominee nom = null;
        Iterator<Nominee> it = nomList.iterator();
        
        if(party.equalsIgnoreCase("all"))
        {
            System.out.println("==List of nominees from " + party + " parties==");
        }
        else
        {
            System.out.println("==List of nominees from " + party + " party==");
        }

        while(it.hasNext())
        {
            nom = it.next();
            if(nom.getPartyShortName().equalsIgnoreCase(party))
            {
                System.out.println(nom.politicianString());
            }
            else if(party.equalsIgnoreCase("all"))
            {
                System.out.println(nom.politicianString());
            }
        }
    }

    public void listByDiv(String div)
    {
        Nominee nom = null;
        Iterator<Nominee> it = nomList.iterator();
        
        if(div.equalsIgnoreCase("all"))
        {
            System.out.println("==List of nominees from " + div + " divisions==");
        }
        else
        {
            System.out.println("==List of nominees from " + div + " division==");
        }

        while(it.hasNext())
        {
            nom = it.next();
            if(nom.getDivName().equalsIgnoreCase(div))
            {
                System.out.println(nom.politicianString());
            }
            else if(div.equalsIgnoreCase("all"))
            {
                System.out.println(nom.politicianString());
            }
        }
    }

    public void searchNomBySname(String substr, String op, String theOptionName)
    {
        String surname;
        Nominee nom = null;
        Iterator<Nominee> it = nomList.iterator();

        while(it.hasNext())
        {
            nom = it.next();
            if(nom.getSurname().startsWith(substr.toUpperCase()))
            {
                if(op.equalsIgnoreCase("party"))
                {
                    if(nom.getPartyShortName().equalsIgnoreCase(theOptionName))
                    {
                        System.out.println(nom.toString());
                    }
                }
                else if(op.equalsIgnoreCase("state"))
                {
                    if(nom.getState().equalsIgnoreCase(theOptionName))
                    {
                        System.out.println(nom.toString());
                    }
                }

                if(theOptionName.equalsIgnoreCase("ALL"))//just search whole list
                {
                    System.out.println(nom.toString());
                }
            }
        }
    }

//TODO revert back and useless implementation for margin soon
//Convert this back to Nominee objects after testing
    public boolean listMargin(String partySname, double threshold)
    {
        int divMargInRange = 0;
        boolean found = false;
        Nominee nom = null;
        Iterator<Party> itp = partyList.iterator();

        while(itp.hasNext() && !found)
        {
            Party p = itp.next();

            if(p.getPartyShortName().equalsIgnoreCase(partySname))
            {
                Iterator<Division> itd = p.getDivList().iterator();
                while(itd.hasNext())
                {
                    Division div = itd.next();
                    if(div.getMargin(p.getPartyShortName()) > -threshold && div.getMargin(p.getPartyShortName()) < threshold)
                    {
                        System.out.print("PARTY:" + p.toString() + ",");
                        System.out.println(div.toString(p.getPartyShortName()));
                        divMargInRange++;
                    }
                    
                }

                found = true;
            }
        }

        return (divMargInRange == 0);
    }

    /**
    public void sortListBySname()
    {
        DSAStack stack;
        DSAQueue queue;
    **/
}
