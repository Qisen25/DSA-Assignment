import java.util.*;
import java.io.*;
//Unit test for DSAGraph from DSA prac4 slightly modified
public class UnitTestGraph
{
    public static void main(String [] args)
    {
        int iNumPassed = 0;
        int iNumTests = 0;
        DSAGraph g = null;

        System.out.println("====================================================================");
        System.out.println("======Create triangular graph with vertices {A,B,C}======");
        System.out.print("====Testing Default Constructor: ");
        try
        {
            iNumTests++;
            g = new DSAGraph();
            System.out.println("PASSED");
            iNumPassed++;
        }
        catch(Exception e)
        {
            System.out.println("Failed");
        }

        System.out.print("====Testing addVertex: ");
        try
        {
            iNumTests++;
            g.addVertex("A", "A", 1.0, 1.0);
            g.addVertex("B", "B", 2.0, 2.0);
            g.addVertex("C", "C", 3.0, 3.0);
            System.out.println("PASSED");
            iNumPassed++;
        }
        catch(Exception e)
        {
            System.out.println("Failed");
        }

        System.out.print("====Testing getVertexCount: ");
        try
        {
            iNumTests++;
            if(g.getVertexCount() != 3)
                throw new IllegalArgumentException("fail");
            System.out.println("total vertices: " + g.getVertexCount() + ", PASSED");
            iNumPassed++;
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("Failed");
        }
        
        System.out.print("====Testing addEdge: ");
        try
        {
            iNumTests++;
            g.addEdge(g.getVertex("A"), g.getVertex("B"), 3.00, 1, "fly");
            g.addEdge(g.getVertex("B"), g.getVertex("A"), 3.00, 1, "fly");
            g.addEdge(g.getVertex("A"), g.getVertex("C"), 2.00, 2, "walk");
            g.addEdge(g.getVertex("C"), g.getVertex("A"), 2.00, 2, "walk");
            g.addEdge(g.getVertex("B"), g.getVertex("C"), 3.00, 3, "teleport");
            g.addEdge(g.getVertex("C"), g.getVertex("B"), 3.00, 3, "teleport");
            System.out.println("PASSED");
            iNumPassed++;
        }
        catch(Exception e)
        {
            System.out.println("Failed");
        }

        System.out.print("====Testing getEdgeCount: ");
        try
        {
            iNumTests++;
            if(g.getEdgeCount() != 6)
                throw new IllegalArgumentException("fail");
            System.out.println("total edges: " + g.getEdgeCount() + ", PASSED");
            iNumPassed++;
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("Failed");
        }

        System.out.println("====Testing displayList====");
        try
        {
            iNumTests++;
            System.out.println("Display adjacency list");
            g.displayList();
            iNumPassed++;
        }
        catch(Exception e)
        {
            System.out.println("failed to display list");
        }
        System.out.println("====Testing displayEdges====");
        try
        {
            iNumTests++;
            System.out.println("Display edge list");
            g.displayEdges();
            iNumPassed++;
        }
        catch(Exception e)
        {
            System.out.println("failed to display edges");
        }
        System.out.println("====Testing displayMatrix====");
        try
        {
            iNumTests++;
            System.out.println("Display adjacency matrix");
            g.displayMatrix();
            iNumPassed++;
        }
        catch(Exception e)
        {
            System.out.println("failed to display matrix");
        }

        try
        {
            iNumTests++;
            if(!g.isAdjacent(g.getVertex("B"), g.getVertex("C")))
                throw new IllegalArgumentException("fail1");
            if(!g.isAdjacent(g.getVertex("C"), g.getVertex("B")))
                throw new IllegalArgumentException("fail2");
            if(!g.isAdjacent(g.getVertex("A"), g.getVertex("B")))
                throw new IllegalArgumentException("fail3");
            if(!g.isAdjacent(g.getVertex("B"), g.getVertex("A")))
                throw new IllegalArgumentException("fail4");
            if(!g.isAdjacent(g.getVertex("A"), g.getVertex("C")))
                throw new IllegalArgumentException("fail5");
            if(!g.isAdjacent(g.getVertex("C"), g.getVertex("A")))
                throw new IllegalArgumentException("fail6");

            System.out.println("====Testing isAdjacent: PASSED");
            System.out.println("is node B adjacent to node C: " + g.isAdjacent(g.getVertex("B"), g.getVertex("C")));
            System.out.println("is node C adjacent to node B: " + g.isAdjacent(g.getVertex("C"), g.getVertex("B")));
            System.out.println("is node A adjacent to node B: " + g.isAdjacent(g.getVertex("A"), g.getVertex("B")));
            System.out.println("is node B adjacent to node A: " + g.isAdjacent(g.getVertex("B"), g.getVertex("A")));
            System.out.println("is node A adjacent to node C: " + g.isAdjacent(g.getVertex("A"), g.getVertex("C")));
            System.out.println("is node C adjacent to node A: " + g.isAdjacent(g.getVertex("C"), g.getVertex("A")));
            iNumPassed++;
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }

        System.out.print("====Testing allVisited: ");
        try
        {
            iNumTests++;
            if(g.allVisited(g.getVertices()))
                throw new IllegalArgumentException("fail");
            System.out.println("PASSED");
            iNumPassed++;
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("Failed");
        }

        try
        {
            iNumTests++;
            System.out.println("===Testing DFS===");
            System.out.println("EXPECTED A=>B, B=>C");
            g.DFS(g.getVertex("A"));
            iNumPassed++;
        }
        catch(Exception e)
        {
            System.out.println("Fail Error causing" + e.getMessage());
        }
        
        try
        {
            iNumTests++;
            System.out.println("===Testing BFS===");
            System.out.println("EXPECTED A=>B, A=>C");
            g.BFS(g.getVertex("A"));
            iNumPassed++;
        }
        catch(Exception e)
        {
            System.out.println("Fail Error causing" + e.getMessage());
        }

        System.out.println("total passes: " + iNumPassed + "/" + iNumTests + " -> " + ((float)iNumPassed/(float)iNumTests)*100.0 + "%");

        System.out.println("====================================================================");
        System.out.println("====Running Alternate Constructor(Reading file)====");

        String file, vert;
        FileIO f = new FileIO();
       
        
        Scanner sc = new Scanner(System.in);
        System.out.print("==Enter File:");
        file = sc.nextLine();
        
        f.graphFromFile(file);
        f.connectGraph();
        DSAGraph graph = f.getGraph();

        System.out.println("total vertices: " + graph.getVertexCount()); 
        System.out.println("total edges: " + graph.getEdgeCount());

        System.out.println("Display adjacency list");
        graph.displayList();
        System.out.println("Display adjacency matrix");
        graph.displayMatrix();

        System.out.print("==Enter a vertex to begin searches:");
        vert = sc.nextLine();

        System.out.println("DFS");
        graph.DFS(graph.getVertex(vert));
        System.out.println("BFS");
        graph.BFS(graph.getVertex(vert));
    }
}
