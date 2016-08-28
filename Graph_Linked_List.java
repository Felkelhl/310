//This is a java program to represent graph as a linked list
import java.util.*;

public class Graph_Linked_List
{
    private Map<Integer, List<Integer>> adjacencyList;

    public Graph_Linked_List()
    {
        adjacencyList = new HashMap<Integer, List<Integer>>();
    }
    public int degree(int vertexValue){
        return adjacencyList.get(vertexValue).size();
    }
    public boolean isVertex(int vertexValue){
        return adjacencyList.containsKey(vertexValue);
    }
    public void addVertex(int vertex){
        adjacencyList.put(vertex, new LinkedList<Integer>());
    }
    public void addEdge(int to, int from)
    {
//        if (to > adjacencyList.size() || from > adjacencyList.size())
//            System.out.println("The vertices does not exists");

        List<Integer> sls = adjacencyList.get(to);
        sls.add(from);
        List<Integer> dls = adjacencyList.get(from);
        dls.add(to);
    }
    public void removeVertex(int vertexValue){
        if(adjacencyList.get(vertexValue).size() == 0)
            adjacencyList.remove(vertexValue);
    }
    public void removeEdge(int to, int from){
        List<Integer> dls = adjacencyList.get(from);
        List<Integer> sls = adjacencyList.get(to);
        dls.remove((to-1));
        sls.remove((from-1));

    }
    public boolean isAdjacent(int to, int from){
        if(adjacencyList.get(to).contains(from) || adjacencyList.get(from).contains(to))
            return true;

        return false;
    }
    public boolean isPath(int s, int d){
        LinkedList<Integer> temp;
        boolean visited [] = new boolean[adjacencyList.size()];
        LinkedList<Integer> queue = new LinkedList<Integer>();

        visited[s] = true;
        queue.add(s);

        Iterator<Integer> i;
        while(queue.size() != 0){
            s = queue.poll();
            int n;
            i = adjacencyList.get(s).listIterator();

            while(i.hasNext()){
                n = i.next();

                if(n==d)
                    return true;
                if(!visited[n]){
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
        // If BFD without visiting the destination(d)
        return false;
    }
    public String getPath(int s, int d){
        LinkedList<Integer> temp;
        boolean visited [] = new boolean[adjacencyList.size()];
        LinkedList<Integer> queue = new LinkedList<Integer>();
        String path = Integer.toString(s) + ",";

        visited[s] = true;
        queue.add(s);
        Iterator<Integer> i;
        while(queue.size() != 0){
            s = queue.poll();
            int n;
            i = adjacencyList.get(s).listIterator();

            while(i.hasNext()){
                n = i.next();
                if(n==d) {
                    path += Integer.toString(n);
                    return path;
                }
                if(!visited[n]){
                    visited[n] = true;
                    queue.add(n);
                    path += Integer.toString(n) + ",";
                }
            }
        }
        // If BFS without visiting the destination(d)
        if(path.length() < 1)
            path = null;
        return path;
    }
    public List<Integer> getEdge(int to)
    {
        if (to > adjacencyList.size())
        {
            System.out.println("The vertices does not exists");
            return null;
        }
        return adjacencyList.get(to);
    }

    public static void main(String args[])
    {
        int v, e, count = 1, to, from;
        Scanner sc = new Scanner(System.in);
        Graph_Linked_List glist;
        try
        {
            glist = new Graph_Linked_List();

            //Add vertex test
            glist.addVertex(5);
            glist.addVertex(2);
            glist.addVertex(1);
            glist.addVertex(4);
            glist.addVertex(3);
            glist.addVertex(7);
            System.out.println("Is 1 a vertex: " + glist.isVertex(1));
            System.out.println("Is 3 a vertex: " + glist.isVertex(3));
            //Add edge test
            glist.addEdge(1,5);
            glist.addEdge(1,2);
            glist.addEdge(5,3);
            glist.addEdge(4,3);
            glist.addEdge(4,7);
            // Degree test
            System.out.println(glist.degree(1));
            // Remove vertex test
            //glist.removeVertex(4);
            //glist.removeVertex(5);
            // Remove edge test
            glist.removeEdge(1, 2);
            // Adjacent test
            glist.isAdjacent(1,5);
            glist.isAdjacent(1,2);
            // isPath test
            System.out.println(glist.isPath(1,7));
            // getPath test
            String test = glist.getPath(1, 7);
            System.out.println(test);

            System.out
                    .println("The Linked List Representation of the graph is: ");

            for (int i = 1; i <= glist.adjacencyList.size(); i++)
            {
                System.out.print(i + "->");
                List<Integer> edgeList = glist.getEdge(i);
                for (int j = 1;; j++)
                {
                    if (j != edgeList.size())
                        System.out.print(edgeList.get(j - 1) + " -> ");
                    else
                    {
                        System.out.print(edgeList.get(j - 1));
                        break;
                    }
                }
                System.out.println();
            }
        }
        catch (Exception E)
        {
            System.out.println("Something went wrong");
        }
        sc.close();
    }
}