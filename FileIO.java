import java.util.*;
import java.io.*;
/**
 *  FILE: FileIO.java <br>
 *  PURPOSE: class for handling file io <br>
 *
 *  @author Kei Sum Wang - 19126089
 */
public class FileIO
{
    /**
     * File io clas field
     */
    private String fileName;
    private DSALinkedList<Party> partyList;
    private DSALinkedList<Division> divList;
    private DSALinkedList<Nominee> nomList;// consider changing to array;
    private boolean houseConstruct;
    private DSAQueue<String> outQueue;
    private DSAQueue<String[]> vertexQueue;
    private DSAQueue<Division> divToVisit;
    private DSAGraph graph;

    /**
     * DEFAULT Constructor for file io
     */
    public FileIO()
    {
        partyList = new DSALinkedList<Party>();
        divList = new DSALinkedList<Division>();
        nomList = new DSALinkedList<Nominee>();
        outQueue = new DSAQueue<String>();
        vertexQueue = new DSAQueue<String[]>();
        divToVisit = new DSAQueue<Division>();
        graph = new DSAGraph();
        houseConstruct = false;
    }

    /**
     * method to read in house candidate files
     * @param file(String) name of file
     */
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

    /**
     * method to read in house state representative files
     * @param filename(String)
     */
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

    /**
     * method to read graph from file
     * @param filename(String)
     */
    public void graphFromFile(String filename)
    {
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        String line, state1, div1, state2, div2;
        String[] lineArray, paramSplit;
        double latt1, longit1, latt2, longit2;
        int count;

        try
        {
            fileStrm = new FileInputStream(filename);    
            rdr = new InputStreamReader(fileStrm);    
            bufRdr = new BufferedReader(rdr); 

            count = 0;
            line = bufRdr.readLine();
            while (line != null)
            {
                if(count > 0)
                {
                    lineArray = line.split(",");
                    //from division
                    state1 = lineArray[0];
                    div1 = lineArray[1];
                    latt1 = Double.parseDouble(lineArray[2]);
                    longit1 = Double.parseDouble(lineArray[3]);

                    //to division
                    state2 = lineArray[4];
                    div2 = lineArray[5];
                    latt2 = Double.parseDouble(lineArray[6]);
                    longit2 = Double.parseDouble(lineArray[7]);

                    this.graph.addVertex(state1, div1, latt1, longit1);//add from place vertex
                    this.graph.addVertex(state2, div2, latt2, longit2);//add to place vertex

                    vertexQueue.enqueue(lineArray);//Store the array later when creating edges
                }
    
                count++;
                line = bufRdr.readLine();
            }
        }
        catch(IOException e)
        {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * method to connect graph based on the file containing graph read in
     */
    public void connectGraph()
    {
        String[] array, hrToMin;
        int mins, hr, min, sec;
        double dist;
        String trans;
       
       while(!vertexQueue.isEmpty())
        {
            array = vertexQueue.dequeue();
            if(array[8].equals("NONE") || array[9].equals("NONE"))
            {
                mins = 0;
                dist = 0;
            }
            else if(array[9].contains(":"))
            {
                hrToMin = array[9].split(":");
                hr = Integer.parseInt(hrToMin[0]);
                min = Integer.parseInt(hrToMin[1]);
                sec = Integer.parseInt(hrToMin[2]);

                //convert hrs to mins and temporarily convert min to sec then back to min
                mins = (hr * 60) + (((min * 60) + sec)/60);
                dist = Integer.parseInt(array[8]);
            }
            else
            {
                mins = Integer.parseInt(array[9])/60;
                dist = Integer.parseInt(array[8]);
            }
            trans = array[10];
            this.graph.addEdge(graph.getVertex(array[1]), graph.getVertex(array[5]), 
                               dist, mins, trans);//make first connection going TO
            this.graph.addEdge(graph.getVertex(array[5]), graph.getVertex(array[1]), 
                               dist, mins, trans);//make second connection for back tracking
        }
    }

    /**
     * method to display graph connections
     */
    public void displayItinerary(String itLoc)
    {
        this.connectGraph();
        //System.out.println("==Adjacency list==");
        //graph.displayList();
        //System.out.println("==Edges==");
        //graph.displayEdges();
        graph.shortPathv2(this.divToVisit, itLoc);
        //graph.shortPath(this.divToVisit);
    }

    //write to file
    /**
     * method to write to file
     * @param output(String) -name of output file
     */
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

    /**
     * method to handle finding required files in current directroy
     * @return boolean telling whether any required file(s) are found
     */
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
        this.graphFromFile("ElectDist1.1.csv");
        this.graphFromFile("AirportDist1.0.csv");
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


    /**
     * method to print all nominees
     */
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

    /**
     * method to check if nominee exists
     * @param id(integer) - nominee id
     * @return boolean telling whether nominee exists
     */
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

    /**
     * method to check if party exists based on party abbreviation
     * @param partyAb(String) - party abbreviation
     * @return boolean telling whether party exists
     */
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

    /**
     * method to check if division exists
     * @param id(integer) - division id
     * @return boolean telling whether division exists
     */
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

    /**
     * method to find nominee based on id
     * @param id(integer) - nominee id
     * @return nom(Nominee)
     */
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

    /**
     * method to find party based on party abbreviation
     * @param partyAb(String) - party abbreviation
     * @return p(Party)
     */
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

    /**
     * method to find division based on id
     * @param id(integer) - division id
     * @return div(Division)
     */
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

    /**
     * method to list nominee based on state, party and division
     * @param state(String) - abbreviation for state
     * @param party(String) - abbreviation for party
     * @param division(String) - division name
     * @return boolean telling whether if no results are found given the params
     */
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

        return (resultsFound == 0);//Purpose is to check whether if no results have been found
    }    

    /**
     * method to list and search nominee based on surname, state and party
     * @param subStr(String) - complete or incomplete string for surname
     * @param stateNm(String) - abbreviation for state
     * @param partyAb(String) - abbreviation for party
     * @return boolean telling whether if no results are found given the params
     */
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

        return (resultsFound == 0);//purpose to check if no results are found
    }

    /**
     * method to list a party's margins for each division
     * @param partySname(String) - party abbreviation
     * @param threshold(real) - margin threshold
     * @return boolean telling whether if no results are found with in threshold
     */
    public boolean listMargin(String partySname, double threshold)
    {
        int divMargInRange = 0;
        boolean found = false;
        Nominee nom = null;
        Iterator<Party> itp = partyList.iterator();
        this.outQueue = new DSAQueue<String>();
        this.divToVisit = new DSAQueue<Division>();

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
                        this.outQueue.enqueue(out);//put output in output queue for potential write to file
                        this.divToVisit.enqueue(div);//store the division for itinerary 
                        divMargInRange++;//counts the amount of margins found with in threshold
                    }
                    
                }

                found = true;
            }
        }

        return (divMargInRange == 0);//purpose of this is to indicate if no results have been found
    }

    /**
     * function to sort the nominee list by nominee specified class fields<br>
     * this sort uses stack to help with sorting
     * REFERENCE: www.geeksforgeeks.org
     * @param field(String) type of field to sort by from nominee class
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

            stack.push(nom);//put list item in stack while condition not met
        }

        while(!stack.isEmpty())
        {
            this.nomList.insertLast(stack.pop());//insert the sorted stack back into list
        }
    }   
}
