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
    private DSAQueue<String> outQueue;
    private DSAQueue<Division> divToVist;

    public FileIO()
    {
        partyList = new DSALinkedList<Party>();
        divList = new DSALinkedList<Division>();
        nomList = new DSALinkedList<Nominee>();
        outQueue = new DSAQueue<String>();
        divToVist = new DSAQueue<Division>();
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

        int i = 0;
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

    //write to file
    public void writeToFile(String output)
    {
        FileOutputStream fileStrm = null;
        PrintWriter pw;
        
        try
        {
            fileStrm = new FileOutputStream(output);
            pw = new PrintWriter(fileStrm);

            System.out.println("Writing to file......");
            while(!this.outQueue.isEmpty())
            {
                pw.println(this.outQueue.dequeue());
            }
            pw.close();
            fileStrm.close();
        }
        catch(IOException e)
        {
            System.out.println("Error " + e.getMessage());
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

    public boolean listNoms(String state, String party, String div)
    {
        int resultsFound = 0;
        Nominee nom = null;
        Iterator<Nominee> it = nomList.iterator();
        this.outQueue = new DSAQueue<String>();//reset queue for each report that calls this
                                               //prevent other list displays from apppending to queue

        System.out.println("==List of nominees filter by state:" + state + "|party:" + party + "|division:" + div + "==");

        while(it.hasNext())
        {
            nom = it.next();
            //first case user supplies all custom filters
            if(nom.getState().equalsIgnoreCase(state) && nom.getPartyShortName().equalsIgnoreCase(party) &&
               nom.getDivName().equalsIgnoreCase(div))
            {
                System.out.println(nom.toString());
                this.outQueue.enqueue(nom.toString());//queue used later when writing to file
                resultsFound++;
            }
            //second case user gives state and div but selects all parties
            else if(nom.getState().equalsIgnoreCase(state) && party.equalsIgnoreCase("ALL") &&
                    nom.getDivName().equalsIgnoreCase(div))
            {
                System.out.println(nom.toString());
                this.outQueue.enqueue(nom.toString());
                resultsFound++;
            }
            // third user specifies state and party
            else if(nom.getState().equalsIgnoreCase(state) && nom.getPartyShortName().equalsIgnoreCase(party) &&
                    div.equalsIgnoreCase("ALL"))
            {
                System.out.println(nom.toString());
                this.outQueue.enqueue(nom.toString());
                resultsFound++;
            }
            //fourth case user only specifies state
            else if(nom.getState().equalsIgnoreCase(state) && party.equalsIgnoreCase("ALL") &&
                    div.equalsIgnoreCase("ALL"))
            {
                System.out.println(nom.toString());
                this.outQueue.enqueue(nom.toString());
                resultsFound++;
            }
            //fifth case user specifies division and party
            else if(state.equalsIgnoreCase("ALL") && nom.getPartyShortName().equalsIgnoreCase(party) &&
                    nom.getDivName().equalsIgnoreCase(div))
            {
                System.out.println(nom.toString());
                this.outQueue.enqueue(nom.toString());
                resultsFound++;
            }
            //sixth case user only specifies divison
            else if(state.equalsIgnoreCase("ALL") && party.equalsIgnoreCase("ALL") &&
                    nom.getDivName().equalsIgnoreCase(div))
            {
                System.out.println(nom.toString());
                this.outQueue.enqueue(nom.toString());
                resultsFound++;
            }
            //seventh case user choose all state, parties and division
            else if(state.equalsIgnoreCase("ALL") && party.equalsIgnoreCase("ALL") &&
                    div.equalsIgnoreCase("ALL"))
            {
                System.out.println(nom.toString());
                this.outQueue.enqueue(nom.toString());
                resultsFound++;
            }
        }

        return (resultsFound == 0);
    }    

    public boolean searchNomBySname(String substr, String stateNm, String partyAb)
    {
        int resultsFound = 0;
        String surname;
        Nominee nom = null;
        Iterator<Nominee> it = nomList.iterator();
        this.outQueue = new DSAQueue<String>();

        while(it.hasNext())
        {
            nom = it.next();
            if(nom.getSurname().startsWith(substr.toUpperCase()))
            {
                if(nom.getPartyShortName().equalsIgnoreCase(partyAb) && nom.getState().equalsIgnoreCase(stateNm))
                {
                    System.out.println(nom.fullDetails());
                    this.outQueue.enqueue(nom.fullDetails());
                    resultsFound++;
                }
                else if(nom.getPartyShortName().equalsIgnoreCase(partyAb) && stateNm.equalsIgnoreCase("ALL"))
                {
                    System.out.println(nom.fullDetails());
                    this.outQueue.enqueue(nom.fullDetails());
                    resultsFound++;
                }
                else if(nom.getState().equalsIgnoreCase(stateNm) && partyAb.equalsIgnoreCase("ALL"))
                {
                    System.out.println(nom.fullDetails());
                    this.outQueue.enqueue(nom.fullDetails());
                    resultsFound++;
                }
                else if(stateNm.equalsIgnoreCase("ALL") && partyAb.equalsIgnoreCase("ALL"))
                {
                    System.out.println(nom.fullDetails());
                    this.outQueue.enqueue(nom.fullDetails());
                    resultsFound++;
                }
            }
        }

        return (resultsFound == 0);
    }

    /*
     * method to list a party's margins for each division
     */
    public boolean listMargin(String partySname, double threshold)
    {
        int divMargInRange = 0;
        boolean found = false;
        Nominee nom = null;
        Iterator<Party> itp = partyList.iterator();
        this.outQueue = new DSAQueue<String>();
        this.divToVist = new DSAQueue<Division>();

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
                        String out = p.toString() + "," + div.toString(p.getPartyShortName());
                        System.out.println(out);
                        this.outQueue.enqueue(out);
                        this.divToVist.enqueue(div);
                        divMargInRange++;
                    }
                    
                }

                found = true;
            }
        }

        return (divMargInRange == 0);//purpose of this is to indicate whether any results have been found
    }

    /*
     * function to sort the nominee list by nominee specified class fields
     * reference: www.geeksforgeeks.org
     */
    public void sortList(String field)
    {
        Nominee nom = null;
        DSAStack<Nominee> stack = new DSAStack<Nominee>();

        while(!this.nomList.isEmpty())
        {
            nom = this.nomList.removeLast();

            //while stack has stuff and the top is temp is less than the temp nom field
            //put the top back in the list
            while(!stack.isEmpty() && stack.top().getField(field).compareTo(nom.getField(field)) < 0)
            {
                this.nomList.insertLast(stack.pop());
            }

            stack.push(nom);//put list item in stack if while condition not met
        }

        while(!stack.isEmpty())
        {
            this.nomList.insertLast(stack.pop());//insert the sorted stack back into list
        }
    }   
}
