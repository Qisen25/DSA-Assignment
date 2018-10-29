import java.util.*;
/**
 *  FILE: DSAGraph.java <br>
 *  PURPOSE: Implementation of a graph <br>
 *  REFERENCE: From DSA prac 6
 *
 *  @author Kei Sum Wang - 19126089
 */
public class DSAGraph
{
    /**
     * Vertex class -private inner class
     */
    private class DSAVertex
    {
        private String state;
        private String division;
        private double lattitude;
        private double longitude;

        private DSALinkedList<DSAEdge> edges;//edge list
        private DSALinkedList<DSAVertex> links;//edge list
        private boolean visited;
        private boolean mustVisit;
        private boolean passThrough;
        private boolean airportForCampaign;
        private int visitDuration;

        public DSAVertex(String inState, String inDiv, double inLat, double inLong)
        {
            this.state = inState;
            this.division = inDiv;
            this.lattitude = inLat;
            this.longitude = inLong;
            this.edges = new DSALinkedList<DSAEdge>();
            this.links = new DSALinkedList<DSAVertex>();
            this.visited = false;
            this.mustVisit = false;
            this.passThrough = false;
            this.airportForCampaign = false;
            this.visitDuration = 180;//3 hours(180 minutes) to meet and greet
        }

        /**
         * method to get adjacent list from node
         * @return list in string form
         */
        public String adjacentList()
        {
            String str = "";
            DSAVertex adj = null;
            Iterator<DSAVertex> it = this.links.iterator();

            while(it.hasNext())
            {
                adj = it.next();
                str += adj.toString() + " ";
            }

            return str;
        }

        /**
         * method to get edge list from node
         * @return list in string form
         */
        public String edgeListString()
        {
            String str = "";
            DSAEdge edge = null;
            Iterator<DSAEdge> it = this.edges.iterator();

            while(it.hasNext())
            {
                edge = it.next();
                str += edge.toString() + "\n";
            }

            return str;
        }

        public String toString()
        {
            return this.division + "(" + this.state + ")";
        }
    }

    /**
     * Edge class -private inner class
     */
    private class DSAEdge
    {
        private DSAVertex start;//from vertex
        private DSAVertex dest;//to vertex
        private double distance;
        private int minutes;
        private int totalVisitTime;
        private String transport;
        private boolean visited;

        public DSAEdge(DSAVertex inV1, DSAVertex inV2, double dist, int mins, String trans)
        {
            this.start = inV1;
            this.dest = inV2;
            this.distance = dist;
            this.minutes = mins;
            this.totalVisitTime = 0;
            this.transport = trans;
            this.visited = false;
        }

        /**
         * method to add meet/greet hours to the total time
         * @return visitDur - total visitation hours
         */
        public int addVisitDuration()
        {
            int visitDur = 0;
            if(!start.visited && !this.dest.division.contains("Airport") && !this.start.division.contains("Airport"))
            {
                visitDur = start.visitDuration + dest.visitDuration;
                this.totalVisitTime += visitDur;
            }
            else if(this.start.division.contains("Airport") && !this.dest.division.contains("Airport"))
            {
                visitDur = dest.visitDuration;
                this.totalVisitTime += visitDur;
            }
            else if(start.visited && !this.dest.division.contains("Airport") && !this.start.division.contains("Airport"))
            {
                visitDur = dest.visitDuration;
                this.totalVisitTime += visitDur;
            }


            return visitDur;
        }

        public String toString()
        {
            return "From: " + start.toString() + ", To: " +
                    dest.toString() + ", Distance: " + distance + "m, " +
                    "Time: " + minutes + "mins, " + "Mode Of Transport: " + transport + ", Time for meet/greet: " + totalVisitTime + "mins";
        }

    }


    //Graph class fields
    private DSALinkedList<DSAVertex> vertices;
    private DSAQueue<String> writeToFile;

    /**
     * DEFAULT Constructor for graph
     */
    public DSAGraph()
    {
        this.vertices = new DSALinkedList<DSAVertex>();
        this.writeToFile = new DSAQueue<String>();
    }

    /**
     * method to add vertex(place) to graph
     * @param state abbreviation of state(String)
     * @param div name of division(String)
     * @param latt lattitude(Real)
     * @param longit lattitude(Real)
     */
    public void addVertex(String state, String div, double latt, double longit)
    {
        if(this.getVertex(div) == null)//check if vertex doesn't exist then add it, this is to prevent duplicate nodes
        {
            vertices.insertLast(new DSAVertex(state, div, latt, longit));
        }
    }

    /**
     * method to add edge to graph
     * @param v1 start vertex
     * @param v2 end vertex
     * @param dist distance(Real)
     * @param mins minutes(int)
     * @param trans transport type(String)
     */
    public void addEdge(DSAVertex v1, DSAVertex v2, double dist, int mins, String trans)
    {
        //v1 is start of edge
        //v2 is end of edge
        v1.links.insertLast(v2);//add to adjacency list
        v1.edges.insertLast(new DSAEdge(v1, v2, dist, mins, trans));//create edge, insert
    }

    /**
     * method to get vertex count
     * @return num of vertex
     */
    public int getVertexCount()
    {
        return vertices.getCount();
    }

    /**
     * method to get list of vertices
     * @return list of vertices
     */
    public DSALinkedList<DSAVertex> getVertices()
    {
        return vertices;
    }

    /**
     * method to get edge count
     * @return number of edges
     */
    public int getEdgeCount()
    {
        int numEdge = 0;
        DSAVertex v = null;
        Iterator<DSAVertex> it = vertices.iterator();

        while(it.hasNext())
        {
            v = it.next();
            numEdge += v.links.getCount();
        }

        return numEdge;
    }

    /**
     * method to get vertex by label
     * @param inLabel label of vertex(String)
     * @return vertex
     */
    public DSAVertex getVertex(String inLabel)
    {
        boolean found = false;
        DSAVertex v = null;
        Iterator<DSAVertex> it = vertices.iterator();

        while(it.hasNext() && !found)
        {
            v = it.next();
            if(v.division.equals(inLabel))
            {
                found = true;
            }
            else
            {
                v = null;
            }
        }

        return v;
    }

    /**
     * getter for vertex adjacency list
     * @param vertex
     * @return adjacency of vertex
     */
    public DSALinkedList getAdjacent(DSAVertex vertex)
    {
        return vertex.links;
    }

    /**
     * method to check if two vertices are adjacent
     * @param v1 vertex
     * @param v2 vertex
     * @return boolean telling whether vertices are adjacent
     */
    public boolean isAdjacent(DSAVertex v1, DSAVertex v2)
    {
        boolean adjacent = false;
        DSAVertex checkVertex = null;
        Iterator<DSAVertex> it = v1.links.iterator();

        while(it.hasNext())
        {
            checkVertex = it.next();
            if(checkVertex.equals(v2))
            {
                adjacent = true;
            }
        }

        return adjacent;
    }

    /**
     * Method to Display adjacency list of graph    
     */
    public void displayList()
    {
        DSAVertex v = null, adj = null;
        Iterator<DSAVertex> it; 
        
        it = vertices.iterator();

        while(it.hasNext())
        {
            v = it.next();
            System.out.println(v.toString() + "=> " + v.adjacentList());
        }

    }
    
    /**
     * Method to Display edge list of graph    
     */
    public void displayEdges()
    {
        DSAVertex v = null;
        Iterator<DSAVertex> it; 
        
        it = vertices.iterator();

        while(it.hasNext())
        {
            v = it.next();
            System.out.print(v.edgeListString());
        }

    }

    /**
     * Method to Display adjacency matrix of graph    
     */
    public void displayMatrix()
    {
        int[][] matrix = createMatrix();
        Iterator<DSAVertex> it;

        it = vertices.iterator();
        while(it.hasNext())
        {
            System.out.print("  " + it.next().division);
        }
        System.out.println();

        it = vertices.iterator();
        for(int ii = 0; ii < matrix.length; ii++)
        {
            System.out.print(it.next().division + " ");
            for(int jj = 0; jj < matrix[ii].length; jj++)
            {
                System.out.print(matrix[ii][jj] + "  ");
            }
            System.out.println();
        }
    }

//Search Methods
    /**
     * Depth-first-search
     * @param v vertex
     */
    public void DFS(DSAVertex v)
    {
        boolean found;
        DSAVertex newVertex = null, top, temp;
        DSAStack<DSAVertex> stack;
        Iterator<DSAVertex> vLinks;

        this.clearAllVisits();//mark all vertices as new
        stack = new DSAStack<DSAVertex>();
        v.visited = true;

        stack.push(v);
        while(!stack.isEmpty())
        {
            found = false;
            top = stack.top();
            vLinks = top.links.iterator();
            while(vLinks.hasNext() && !found)//loop until new vertex is found
            {
                newVertex = vLinks.next();
                if(!newVertex.visited)
                {
                    found = true;//this to stop loop once a new vertex is found
                    System.out.println(top.division + "=>" + newVertex.division);
                    newVertex.visited = true;
                    stack.push(newVertex);
                }
            }

            //Directed graph problem.
            //without this, the search would not reach nodes with empty adjlist
            //even though other nodes were connect to this node.
            //These nodes weren't used in the 2nd loop after the first push
            temp = stack.pop();
            stack.push(temp);/*to fix this, temporarily pop what was recently
                               pushed then push it back, then use check below*/

            if(this.allVisited(temp.links) || temp.links.isEmpty())//check if vertex on top stack has all adjacent vertices visited or is empty
            {
                stack.pop();
            }
        }

    }

    /**
     * Breadth-first-search
     * @param v vertex
     */
    public void BFS(DSAVertex v)
    {
        DSAVertex newVertex, front;
        DSAQueue<DSAVertex> queue;
        Iterator<DSAVertex> vLinks;

        this.clearAllVisits();//mark all vertices as new
        queue = new DSAQueue<DSAVertex>();
        v.visited = true;

        queue.enqueue(v);
        while(!queue.isEmpty())
        {
            front = queue.dequeue();//get front of queue
            vLinks = front.links.iterator();
            while(vLinks.hasNext())
            {
                newVertex = vLinks.next();
                if(!newVertex.visited)
                {
                    System.out.println(front.division + "->" + newVertex.division);
                    newVertex.visited = true;
                    queue.enqueue(newVertex);
                }
            }
        }
    }

    //implement a must visit for each node
    //then search for any node that needs to be visited and has the shortest time
    /**
     * Shortest pathv2 not complete<br>
     * based on breadth first search and some what Dijkstra algorithm<br>
     * Please see justification report for reason why my algo is not really optimal<br>
     * @param divQueue - Divisions from margin stored in a queue to help prepare traversal
     * @param divName - name of start division for traversal
     */
    public void shortPathv2(DSAQueue<Division> divQueue, String divName)
    {
        boolean found = false;
        int shortTime = Integer.MAX_VALUE;
        int totalTravelTime = 0, meetGreet = 0, curMeet, totalTime;
        DSAVertex newVertex, front, v;
        DSAEdge curEdge;
        DSAQueue<DSAVertex> queue;
        DSAQueue<Division> toVisit;
        Iterator<DSAEdge> edg;

        

        toVisit = setupMustVisits(divQueue);//setup the the nodes of the graph based on itinerary, mark them as must visits
        queue = new DSAQueue<DSAVertex>();
        this.clearAllVisits();//mark all vertices as new
        setAltPath(toVisit);//check if existing nodes that don't have many reach points that set an alternate path to go through to visit
        v = this.getVisitVertex(divName);// enter a location name that is part of the margin list
        v.visited = true;
        System.out.println("====Optimal Path for campaign starting from division: " + v.toString() + "====");

        meetGreet += v.visitDuration;//initially will be meeting and greeting for 3 hours at start location
        if(toVisit.getCount() == 1)//if there is only one division in itinerary
        {
            System.out.println("No travel, only visit at " + v.toString());
        }

        queue.enqueue(v);
        while(!queue.isEmpty())
        {
            curMeet = 0;
            found = false;
            front = queue.dequeue();//get front of queue
            edg = front.edges.iterator();
            //System.out.println(!campaignDone(toVisit));
            while(edg.hasNext() && !found)
            {
                curEdge = edg.next();
                //totalTravelTime =  curEdge.minutes;
                //System.out.println(curEdge.dest.division);
                //if(!vertexQ.isEmpty() && !curEdge.dest.visited && totalTravelTime < shortTime && curEdge.dest.equals(getVertex(vertexQ.peek().getDivName()))) 
                if(!stateAllVisit(front))//check if the current node in queue, state has all the nodes visited 
                {
                    //check if any nodes are needed to pass through to get nodes that can't be reached by most nodes
                    if(curEdge.dest.passThrough && anyPassThrough(curEdge.dest) && curEdge.transport.equals("car"))//check if node and its neighbours need to be passed by car
                    {
                        System.out.println(curEdge.toString());
                        writeToFile.enqueue(curEdge.toString());
                        shortTime = curEdge.minutes;//get current time
                        curEdge.dest.visited = true;
                        curEdge.visited = true;//mark as visited
                                    
                        curEdge.start.passThrough = false;//set passthrough as false since we have already visited the mustVisit node
                        found = true;
                        queue.enqueue(curEdge.dest);
                        meetGreet += curMeet;//add current meet duration
                        totalTravelTime += curEdge.minutes;
                    }
                    else
                    {
                        if(curEdge.dest.passThrough)//node is a passthrough then set visited to false if we need to return just in case
                                                    //if there are no other nodes to visit
                        {
                            curEdge.dest.visited = false;
                        }

                        DSAVertex tempNBR = findFastNeighbour(front);//get fastest neighbour by car 
                    //if(!stateAllVisit(front) && findFastNeighbour(front).equals(curEdge.dest)) 
                        if(tempNBR != null)
                        {
                        //System.out.println(curEdge.dest.division);
                           //System.out.println(
                            if(tempNBR.equals(curEdge.dest) && curEdge.transport.equals("car"))//make sure current dest matches the fastest neighbour
                            {
                                if(matchDivs(curEdge.dest.division, toVisit))//if dest is part of itinerary then add meet greet duration
                                {
                                    curMeet = curEdge.addVisitDuration();
                                }
                                else//if it is a pass node then set node pass through to false since its already traversed and longer priority
                                {
                                    //curEdge.dest.mustVisit = false;
                                    curEdge.dest.passThrough = false;
                                }
                                System.out.println(curEdge.toString());
                                writeToFile.enqueue(curEdge.toString());
                                shortTime = curEdge.minutes;
                                curEdge.dest.visited = true;
                                curEdge.start.visited = true;
                                front.visited = true;
                                curEdge.visited = true;//make sure current edge is completely visited
                                found = true;
                                queue.enqueue(curEdge.dest);
                                meetGreet += curMeet;
                                totalTravelTime += curEdge.minutes;
                            }
                        }
                        else// if no fast neighbour is found then look for an airport with in the state
                        {
                            //curEdge.dest.visited = false;
                            //System.out.println(curEdge.dest.division);
                            //tempNBR = findFastNeighbour(curEdge.dest);
            
                                if(curEdge.dest.division.contains("Airport") && curEdge.dest.state.equals(front.state))
                                {
                                    System.out.println(curEdge.toString());
                                    writeToFile.enqueue(curEdge.toString());
                                    shortTime = curEdge.minutes;
                                    curEdge.dest.mustVisit = false;
                                    curEdge.dest.visited = true;
                                    curEdge.visited = true;
                                    found = true;
                                    queue.enqueue(curEdge.dest);
                                    meetGreet += curMeet;
                                    totalTravelTime += curEdge.minutes;
                                }
                                else if(curEdge.dest.division.equals(front.division))
                                {
                                    System.out.println(curEdge.toString());
                                    writeToFile.enqueue(curEdge.toString());
                                    shortTime = curEdge.minutes;
                                    curEdge.dest.mustVisit = false;
                                    curEdge.dest.visited = true;
                                    curEdge.visited = true;
                                    found = true;
                                    queue.enqueue(curEdge.dest);
                                    meetGreet += curMeet;
                                    totalTravelTime += curEdge.minutes;
                                }
                        
                        }
                    }
                }
                //check if a division connects to a division that it not connected to a must visit
                //else if(curEdge.dest.mustVisit && curEdge.start.division.contains("Airport") && findFastNeighbour(curEdge.dest).equals(curEdge.start))
                //else if(curEdge.dest.mustVisit && curEdge.start.division.contains("Airport") && findFastNeighbour(curEdge.dest).equals(curEdge.start))
                else if(stateAllVisit(front) && !campaignDone(toVisit))// if the front of queue has all states visited and campaign not done 
                {
                    //System.out.println(stateAllVisit(front) && !campaignDone(toVisit)); 
                    //System.out.println(curEdge.dest.division + " " + vertexQ.peek().getState());
                    //System.out.println(curEdge.dest.division + " " + front.state);
                    if(!curEdge.start.division.contains("Airport") && curEdge.dest.division.contains("Airport") && curEdge.transport.equals("car") &&
                        curEdge.dest.airportForCampaign)//look for nearest airport within the division
                    {
                        curMeet = curEdge.addVisitDuration();
                        System.out.println(curEdge.toString());
                        writeToFile.enqueue(curEdge.toString());
                        curEdge.dest.visited = true;
                        curEdge.visited = true;
                        found = true;
                        queue.enqueue(curEdge.dest);
                        meetGreet += curMeet;
                        totalTravelTime += curEdge.minutes;
                    }
                    else if(curEdge.dest.airportForCampaign && curEdge.start.division.contains("Airport") && !curEdge.dest.division.contains("Airport") &&
                            !curEdge.dest.visited)//otherwise if current edge is an airport and the destination is not an airport
                    {
                        if(!stateHasOwnAirport(curEdge.dest))//check if a destination has its own airport, needed since the ACT didn't have airport in csv
                        {
                            DSAVertex tempAir = findNearAirportFromDiv(curEdge.dest);//find the nearest airport within division
                            //System.out.println(tempAir != null);
                            if(tempAir != null)
                            {
                                //System.out.println(curEdge.dest.division);
                                if(tempAir.equals(curEdge.start))// make sure the airport matches the destination aiport
                                {
                                    curMeet = curEdge.addVisitDuration();
                                    System.out.println(curEdge.toString());
                                    writeToFile.enqueue(curEdge.toString());
                                    shortTime = curEdge.minutes;
                                    curEdge.dest.visited = true;
                                    curEdge.visited = true;//make sure edge is visited
                                    found = true;
                                    queue.enqueue(curEdge.dest);
                                    meetGreet += curMeet;
                                    totalTravelTime += curEdge.minutes;
                                }
                            }
                        }
                    }
                    else if(curEdge.start.division.contains("Airport") && curEdge.transport.equals("plane") && !curEdge.dest.visited)//check if edge is flight
                    {
                        DSAVertex temp = fastInterstateByPlane(curEdge.start);//get fastest flight by interstate

                        if(temp != null)
                        {
                            if(temp.equals(curEdge.dest))//make sure the flight matches the destination
                            {
                                //System.out.println(fastInterstateByPlane();
                                curMeet = curEdge.addVisitDuration();
                                System.out.println(curEdge.toString());
                                writeToFile.enqueue(curEdge.toString());
                                curEdge.dest.visited = true;
                                curEdge.visited = true;
                                found = true;
                                queue.enqueue(curEdge.dest);
                                meetGreet += curMeet;
                                totalTravelTime += curEdge.minutes;
                            }
                        }
                    }
                }//endif
            }//endwhile

        }
            String summary = "===Campaign Summary===" + "\nTotal travel time for journey: " + totalTravelTime + "mins" +
            "\nTotal time for meet and greets: " + meetGreet + "mins" + "\nTotal time overall for campaign: " + (totalTravelTime+meetGreet) + "mins";

            System.out.println(summary);
            writeToFile.enqueue(summary);//write summary to file

            resetItinerary(toVisit);//reset the itinerary map after campaign traversal done
            clearAllVisits();
    }

    /**
     * method to set up the nodes that need to be visited<br>
     * @param divQ - Queue containing the require division for itinerary traversal
     * @return retQ - return copy of imported queue
     */
    public DSAQueue<Division> setupMustVisits(DSAQueue<Division> divQ)
    {
        Division temp = null;
        DSAVertex airport = null;
        DSAQueue<Division> retQ = new DSAQueue<Division>();

        while(!divQ.isEmpty())
        {
            temp = divQ.dequeue();
            getVertex(temp.getDivName()).mustVisit = true;// find the vertex and set to mustVisit
            airport = findAirportFromDiv(getVertex(temp.getDivName()));
            if(airport != null)//if airport exists then set
            {
                airport.airportForCampaign = true;
            }
            retQ.enqueue(temp);
        }


        return retQ;
    }

    /**
     * method to reset the graph mustVisit flag
     * @param divQ - containing the require divisions
     */
    public void resetItinerary(DSAQueue<Division> divQ)
    {
        DSAVertex airport = null;
        Division temp = null;

        while(!divQ.isEmpty())
        {
            temp = divQ.dequeue();
            getVertex(temp.getDivName()).mustVisit = false;
            airport = findAirportFromDiv(getVertex(temp.getDivName()));
            if(airport != null)
            {
                airport.airportForCampaign = false;
            }
        }
    }

    /**
     * method to set up an alternative path when a node doesn't have many reachable node
     * @param divQ - queue containing the required divisions
     */
    public void setAltPath(DSAQueue<Division> divQ)
    {
        DSAVertex airport = null;
        Division cur = null;
        Iterator<Division> it = divQ.iterator();

        while(it.hasNext())
        {
            cur = it.next();
            setPathToVisit(getVertex(cur.getDivName()));
            airport = findAirportFromDiv(getVertex(cur.getDivName()));
            if(airport != null)
            {
                setPathToVisit(airport);
            }
        }
    }

    /**
     * method to set up the paths to visit<br>
     * this is used to help setAltPath method
     * @param div - the division vertex
     */
    public void setPathToVisit(DSAVertex div)
    {
        DSAVertex cur = null;
        Iterator<DSAVertex> it = div.links.iterator();

        while(it.hasNext())
        {
            cur = it.next();
            if(!anyMustVisits(div))
            {
                cur.mustVisit = true;
                cur.passThrough = true;
                div.passThrough = true;// let division pair with current passThrough
            }
        }
    }

    /**
     * checks if division has any mustVisit node that need to be visited
     * @param div - a Division vertex
     * @return a boolean telling whether there are any nodes
     */
    public boolean anyMustVisits(DSAVertex div)
    {
        int mustVisit = 0;
        DSAVertex cur = null;
        Iterator<DSAVertex> it = div.links.iterator();

        while(it.hasNext())
        {
            cur = it.next();
            if(cur.mustVisit)// && !cur.visited)
            {
                mustVisit++;
            }
        }

        return (mustVisit > 0);
    }

    /**
     * checks if division has any passThrough node that need to be visited
     * @param div - a Division vertex
     * @return a boolean telling whether there are any nodes
     */
    public boolean anyPassThrough(DSAVertex div)
    {
        int pass = 0;
        DSAVertex cur = null;
        Iterator<DSAVertex> it = div.links.iterator();

        while(it.hasNext())
        {
            cur = it.next();
            if(cur.passThrough && !cur.visited)
            {
                pass++;
            }
        }

        return (pass > 0);
    }

    /**
     * checks if division has any more node that need to be visited
     * @param div - a Division vertex
     * @return a boolean telling whether there are any nodes
     */
    public boolean anyMoreVisits(DSAVertex div)
    {
        int found = 0;
        DSAVertex cur = null;
        Iterator<DSAVertex> it = div.links.iterator();

        while(it.hasNext())
        {
            cur = it.next();
            if(cur.mustVisit && !cur.visited)
            {
                found++;
            }
        }
    
        System.out.println(found); 
        return (found > 0);
    }


    /**
     * checks if a state has its own airport
     * @param div - a Division vertex
     * @return a boolean telling whether the state has an airport
     */
    public boolean stateHasOwnAirport(DSAVertex div)
    {
        boolean found = false;
        DSAVertex temp = null;
        Iterator<DSAVertex> it = div.links.iterator();

        while(it.hasNext() && !found)
        {
            temp = it.next();

            if(temp.division.contains("Airport") && temp.state.equals(div.state))
            {
                found = true;
            }
        }

        return found;
    }

    /**
     * method to find closest neighbour
     * @param start - a Division vertex
     * @return fastDiv - a Division that is the fastest to travel
     */
    public DSAVertex findFastNeighbour(DSAVertex start)
    {
        int quickest = Integer.MAX_VALUE;
        double longDist = 0.0;
        DSAEdge curEdg = null; 
        DSAVertex fastDiv = null;
        Iterator<DSAEdge> it = start.edges.iterator();

        while(it.hasNext())
        {
            curEdg = it.next();
            if((curEdg.dest.mustVisit) && (!curEdg.dest.visited) && curEdg.transport.equals("car"))
            {
                if(curEdg.minutes < quickest)
                {
                    quickest = curEdg.minutes;
                    longDist = curEdg.distance;
                    fastDiv = curEdg.dest;
                }
                else if(curEdg.minutes == quickest)//if the times a equivalent
                {
                    if(curEdg.distance > longDist)//then get the fastest travel
                    {
                        quickest = curEdg.minutes;
                        longDist = curEdg.distance;
                        fastDiv = curEdg.dest;
                    }
                }
            }
        }

        return fastDiv;
    }

    /**
     * find nearest airport from given division
     * @param start - a Division vertex
     * @return fastDiv - nearest airport
     */
    public DSAVertex findNearAirportFromDiv(DSAVertex start)
    {
        int quickest = Integer.MAX_VALUE;
        double longDist = 0.0;
        DSAEdge curEdg = null; 
        DSAVertex fastDiv = null;
        Iterator<DSAEdge> it = start.edges.iterator();

        while(it.hasNext())
        {
            curEdg = it.next();
            if(curEdg.dest.division.contains("Airport") && curEdg.dest.airportForCampaign)//only if airport is need for campaign travel
            {
                if(curEdg.minutes < quickest)
                {
                    quickest = curEdg.minutes;
                    longDist = curEdg.distance;
                    fastDiv = curEdg.dest;
                }
                else if(curEdg.minutes == quickest)
                {
                    if(curEdg.distance > longDist)
                    {
                        quickest = curEdg.minutes;
                        longDist = curEdg.distance;
                        fastDiv = curEdg.dest;
                    }
                }
            }
        }

        return fastDiv;
    }

    /**
     * checks if overall campaign is done
     * @param divQueue - queue containing required division
     * @return a boolean telling whether campaign is done
     */
    public boolean campaignDone(DSAQueue<Division> divQueue)
    {
        int total, done = 0;
        DSAVertex temp = null;
        Division cur = null;
        Iterator<Division> it = divQueue.iterator();

        total = divQueue.getCount();
        while(it.hasNext())
        {
            cur = it.next();
            temp = getVertex(cur.getDivName());
            if(temp.mustVisit && temp.visited)
            {
                done++;
            }
        }

        return (done / total == 1); 
    }

    //implement a airport version for this since fenner could not be reached in when tested
    /**
     * checks if all divisions in a state are all visited
     * @param start - a Division vertex
     * @return a boolean telling whether all div visited within state
     */
    public boolean stateAllVisit(DSAVertex start)
    {
        int total = 0, visits = 0;
        DSAVertex fastDiv = null;
        DSAVertex cur = null;
        Iterator<DSAVertex> it = vertices.iterator();

        while(it.hasNext())
        {
            cur = it.next();
            if(cur.mustVisit && cur.state.equals(start.state))//makes sure current is a mustVisit and is part of import vertex state
            {
                if(cur.visited)
                {
                    visits++;//get amount visited
                }
                total++;//get total of must visits
            }
        }

        return (visits / total == 1); 
    }

    /**
     * method to get the vertex to start from, or to visit
     * @param div - a Division vertex
     * @return reqDiv - the required division
     */
    public DSAVertex getVisitVertex(String div)
    {
        DSAVertex reqDiv = getVertex(div);
        
        if(reqDiv == null)
        {
            throw new IllegalArgumentException("Division does not exist");
        }
        else if(!reqDiv.mustVisit)
        {
            throw new IllegalArgumentException("Division specified not part of itinerary");
        }

        return reqDiv;
    }

    /**
     * method to get neighbour that has a direct visit to a mustVisit and passThrough node
     * @param fromDiv - a Division vertex
     * @return visitFromNBR - the node that contains neighbours that are mustVisit and passThrough
     */
    public DSAVertex getNBRDirectVisit(DSAVertex fromDiv)
    {
        boolean found = false;
        DSAVertex v = null;
        DSAVertex visitFromNBR = null;
        Iterator<DSAVertex> adjList, NBRList;
        adjList = fromDiv.links.iterator();

        
        while(adjList.hasNext() && !found)
        {
            v = adjList.next();
            NBRList = fromDiv.links.iterator();
            if(v.passThrough)
            {
                while(NBRList.hasNext() && !found)
                {
                    visitFromNBR = NBRList.next();
                    if(visitFromNBR.mustVisit && !visitFromNBR.visited)
                    {
                        found = true;
                    }
                }
            }
        }

        return visitFromNBR;
    }
    
    /**
     * method to fastest flight
     * @param airport - a Division vertex
     * @return shortState - the destination node of the is part of the shortest flight
     */
    public DSAVertex fastInterstateByPlane(DSAVertex airport)
    {
        int quickest = Integer.MAX_VALUE;
        double longDist = 0.0;
        DSAEdge curEdg = null;
        DSAVertex shortState = null;
        DSAStack<Integer> timeStack = new DSAStack<Integer>();
        Iterator<DSAEdge> edgeIt;

        //airport = findAirportFromDiv(fromDiv);
        edgeIt = airport.edges.iterator();

        while(edgeIt.hasNext())
        {
            curEdg = edgeIt.next();
            if(curEdg.dest.division.contains("Airport") && !curEdg.dest.state.equals(airport.state) &&
               curEdg.start.division.contains("Airport") && curEdg.dest.airportForCampaign && !curEdg.dest.visited)
            {
                if(curEdg.minutes < quickest)
                {
                    quickest = curEdg.minutes;
                    longDist = curEdg.distance;
                    shortState = curEdg.dest;
                }
                else if(curEdg.minutes == quickest)
                {
                    if(curEdg.distance > longDist)
                    {
                        quickest = curEdg.minutes;
                        longDist = curEdg.distance;
                        shortState = curEdg.dest;
                    }
                }

            }
        }
        return shortState;
    }

    /**
     * method to check if a specified divison is part of the itinerary
     * @param state - String for state abbreviation
     * @param div - a Division vertex
     * @return found - boolean to tell if specified state is found
     */
    public boolean matchStates(String state, DSAQueue<Division> div)
    {
        boolean found = false;
        Division cur = null;
        Iterator<Division> it = div.iterator();

        while(it.hasNext() && !found)
        {
            cur = it.next();
            found = (state.equals(cur.getState()));
        }

        return found;
    }

    /**
     * method to check if a specified divison is part of the itinerary
     * @param divName - String for division name
     * @param div - a Division vertex
     * @return found - boolean to tell if specified division is found
     */
    public boolean matchDivs(String divName, DSAQueue<Division> div)
    {
        boolean found = false;
        Division cur = null;
        Iterator<Division> it = div.iterator();

        while(it.hasNext() && !found)
        {
            cur = it.next();
            found = (divName.equals(cur.getDivName()));
        }

        return found;
    }

    /**
     * method to find nearest within division
     * @param fromDiv - a Division vertex
     * @return airport - an airport within the division
     */
    public DSAVertex findAirportFromDiv(DSAVertex fromDiv)
    {
        boolean found = false;
        int totalTime = 0, temp = 0;
        DSAEdge curEdg = null;
        DSAVertex airport = null;
        Iterator<DSAEdge> edgeIt;

        edgeIt = fromDiv.edges.iterator();

        while(edgeIt.hasNext() && !found)
        {
            curEdg = edgeIt.next();
            if(curEdg.dest.division.contains("Airport"))
            {
                found = true;
                airport = curEdg.dest;
            }
        }

        return airport;
    }
                
    /**
     * Method to clear visit of vertices
     */
    public void clearAllVisits()
    {
        Iterator<DSAVertex> it;

        it = this.vertices.iterator();

        while(it.hasNext())
        {
            it.next().visited = false;
        }
    }

    /**
     * Method to check if all vertices have been visited
     * @return boolean if all visited or not
     */
    public boolean allVisited(DSALinkedList<DSAVertex> list)
    {
        int visits, total;
        Iterator<DSAVertex> it;

        visits = 0;
        total = list.getCount();
        
        it = list.iterator();

        while(it.hasNext())
        {
            if(it.next().visited == true)
            {
                visits++;
            }
        }

        return (visits == total);
    }

    public DSAQueue<String> getGraphOutput()
    {
        return this.writeToFile;
    }
       
//private methods
    /**
     * method to create an adjacency matrix
     */
    private int[][] createMatrix()
    {
        int size, ii, jj;
        int[][] matrix; 
        DSAVertex v1 = null, v2 = null;
        Iterator<DSAVertex> it1, it2;
        
        size = vertices.getCount();
        matrix = new int[size][size]; 
        it1 = vertices.iterator();

        ii = 0;
        while(it1.hasNext())
        {
            jj = 0;
            v1 = it1.next();
            it2 = vertices.iterator();

            while(it2.hasNext())
            {
                v2 = it2.next();
                if(this.isAdjacent(v1, v2))
                {
                    matrix[ii][jj] = 1;
                }
                else
                {
                    matrix[ii][jj] = 0;
                }
                jj++;
            }
            ii++;
        }
        return matrix;
    }
}
