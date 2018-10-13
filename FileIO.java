import java.util.*;
import java.io.*;
public class FileIO
{
    private String fileName;
    private DSALinkedList<Nominee> nomList;// consider changing to array;
    //for insertion sort easier

    public FileIO()
    {
        nomList = new DSALinkedList<Nominee>();
    }

    public void readFile(String file)
    {
        int ii = 0;
        int divID, pollID, candID, balPos, ordVotes;
        String state, divName, pollPlace, sname, fname, partyAb, partyName;
        char elect, HistElect;
        double swing; 
        Nominee nominee = null;
        Division div;
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
                    
                    if(!nomExist(candID))
                    {
                        div = new Division(divID, divName);
                        nominee = new Nominee(state, div, candID, sname, fname, balPos, elect, HistElect,
                                           partyAb, partyName);
                        nomList.insertLast(nominee);
                    }
                    findNom(candID).getDiv().addPollPlace(pollID, pollPlace, ordVotes, swing);
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

    public boolean readDirFiles()
    {
        boolean fileFound = false;
        File scanDir;
        File[] files;
        String fileNeed, currDir;

        currDir = ".";
        scanDir = new File(currDir);
        files = scanDir.listFiles();

        for(int i = 0; i < files.length; i++)
        {
            if(files[i].isFile())
            {
                fileNeed = files[i].getName();
                System.out.println(fileNeed);
                if(fileNeed.contains("HouseStateFirstPrefsByPollingPlaceDownload-") &&
                    fileNeed.endsWith(".csv"))
                {
                    this.readFile(fileNeed);
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
        int i = 0;
        while(it.hasNext())
        {
            nom = it.next();
            System.out.println(nom.getFullName());
            i++;
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

    public void listByState(String state)
    {
        Nominee nom = null;
        Iterator<Nominee> it = nomList.iterator();

        while(it.hasNext())
        {
            nom = it.next();
            if(nom.getState().equals(state))
            {
                System.out.println(nom.toString());
            }
        }
    }    

    public void listByParty(String party)
    {
        Nominee nom = null;
        Iterator<Nominee> it = nomList.iterator();

        while(it.hasNext())
        {
            nom = it.next();
            if(nom.getPartyShortName().equals(party))
            {
                System.out.println(nom.toString());
            }
        }
    }

    public void listByDiv(String div)
    {
        Nominee nom = null;
        Iterator<Nominee> it = nomList.iterator();

        while(it.hasNext())
        {
            nom = it.next();
            if(nom.getDivName().equals(div))
            {
                System.out.println(nom.toString());
            }
        }
    }

    /**
    public void sortListBySname()
    {
        DSAStack stack;
        DSAQueue queue;
    **/
}
