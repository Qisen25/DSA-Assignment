public class UnitTestDSAGraph
{
    public static void main(String [] args)
    {
        DSAGraph g = new DSAGraph();

        g.addVertex("WA", "poo", 0.0, 0.0);
        g.addVertex("NSW", "moo", 0.0, 0.0);
        g.addVertex("ACT", "shoo", 0.0, 0.0);

        g.addEdge(g.getVertex("poo"), g.getVertex("moo"), 1234.1, 2, "bike");
        g.addEdge(g.getVertex("poo"), g.getVertex("shoo"), 1211.1, 1, "bike");

        System.out.println(g.getEdgeCount());
        System.out.println("==display adj list==");
        g.displayList();
        System.out.println("==display edge list==");
        g.displayEdges();
        System.out.println("==display adj matrix==");
        g.displayMatrix();
        g.DFS(g.getVertex("poo"));
    }
}
