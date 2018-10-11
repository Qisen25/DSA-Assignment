import java.util.*;
/*
 * Author Kei Sum Wang - 19126089
 * Graph implementation
 */
public class DSAGraph
{
    //Vertex class -private inner class
    private class DSAVertex
    {
        private String label;
        private DSALinkedList<DSAVertex> links;//adj list
        private boolean visited;

        public DSAVertex(String inLabel)
        {
            this.label = inLabel;
            this.links = new DSALinkedList<DSAVertex>();
            this.visited = false;
        }

        public void addEdge(DSAVertex vertex)
        {
            this.links.insertLast(vertex);
        }

        public String toString()
        {
            String str = "";
            DSAVertex adj = null;
            Iterator<DSAVertex> it = links.iterator();

            while(it.hasNext())
            {
                adj = it.next();
                str += adj.label + " ";
            }

            return str;
        }
    }

    //Graph class fields
    private DSALinkedList<DSAVertex> vertices;

    public DSAGraph()
    {
        this.vertices = new DSALinkedList<DSAVertex>();
    }

    //ALTERNATE CTOR for reading file
    public DSAGraph(String fileName)
    {
        DSAGraph graph = null;

        FileIO f = new FileIO(fileName);
        f.buildGraph();
        graph = f.getGraph();

        this.vertices = graph.getVertices();
    }

    //method add vertex
    public void addVertex(String label)
    {
        if(this.getVertex(label) == null)//check if vertex doesn't exist then add it, this is to prevent duplicate nodes
        {
            vertices.insertLast(new DSAVertex(label));
        }
    }

    /*
     * addEdge
     */
    public void addEdge(DSAVertex v1, DSAVertex v2)
    {
        //v1 is start of edge
        //v2 is end of edge
        v1.links.insertLast(v2);
    }

    //method to get vertex count
    public int getVertexCount()
    {
        return vertices.getCount();
    }

    //method to get list of vertices
    public DSALinkedList<DSAVertex> getVertices()
    {
        return vertices;
    }

    //method to get edge count
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

    //method to get vertex by label
    public DSAVertex getVertex(String inLabel)
    {
        boolean found = false;
        DSAVertex v = null;
        Iterator<DSAVertex> it = vertices.iterator();

        while(it.hasNext() && !found)
        {
            v = it.next();
            if(v.label.equals(inLabel))
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

    //getter for vertex adjacency list
    public DSALinkedList getAdjacent(DSAVertex vertex)
    {
        return vertex.links;
    }

    //method to check if two vertices are adjacent
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

//Methods to Display graph
    
    public void displayList()
    {
        DSAVertex v = null, adj = null;
        Iterator<DSAVertex> it; 
        
        it = vertices.iterator();

        while(it.hasNext())
        {
            v = it.next();
            System.out.println(v.label + "=> " + v.toString());
        }

    }

    public void displayMatrix()
    {
        int[][] matrix = createMatrix();
        Iterator<DSAVertex> it;

        it = vertices.iterator();
        while(it.hasNext())
        {
            System.out.print("  " + it.next().label);
        }
        System.out.println();

        it = vertices.iterator();
        for(int ii = 0; ii < matrix.length; ii++)
        {
            System.out.print(it.next().label + " ");
            for(int jj = 0; jj < matrix[ii].length; jj++)
            {
                System.out.print(matrix[ii][jj] + "  ");
            }
            System.out.println();
        }
    }

//Search Methods
    //Depth-first-search
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
                    System.out.println(top.label + "=>" + newVertex.label);
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

    //Breadth-first-search
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
                    System.out.println(front.label + "->" + newVertex.label);
                    newVertex.visited = true;
                    queue.enqueue(newVertex);
                }
            }
        }
    }

    //Method to clear visit of vertices
    public void clearAllVisits()
    {
        Iterator<DSAVertex> it;

        it = this.vertices.iterator();

        while(it.hasNext())
        {
            it.next().visited = false;
        }
    }

    //Method to check if all vertices have been visited
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
       
//private methods
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
