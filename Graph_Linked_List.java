import java.util.*;
// Coded by Hugo Felkel, CSCI	310: Assignment	1
// I worked alone on this assignment
// Undirected Graph ADT implemented through an Adjacency List structure
public class Graph_Linked_List
{
    // Global variable for the Adjacency List
    private Map<Integer, List<Integer>> adjacencyList;
    private int count;
    private Iterator<Integer> iterator;

    // Constructor
    public Graph_Linked_List() { adjacencyList = new HashMap<Integer, List<Integer>>(); }

    // Determining the size of each linked list contained at the provided vertex
    // Input: Vertex integer    Output: Integer contains number of edges connected to vertex
    // Preconditions: Assumes the vertex exists and is an int   Postconditions: None
    public int degree(int vertexValue){ return adjacencyList.get(vertexValue).size(); }

    // Searching the adjacencyList for the provided key value
    // Input: Vertex integer    Output: Boolean value
    // Preconditions: Assumes the vertex is an integer  Postconditions: None
    public boolean isVertex(int vertexValue){ return adjacencyList.containsKey(vertexValue); }

    // Add a vertex point to the AdjacencyList
    // Input: Vertex to be added    Output: Nothing
    // Preconditions: Assumes the vertex is an integer   Postconditions: None
    public void addVertex(int vertex){ adjacencyList.put(vertex, new LinkedList<Integer>()); }

    // Add an edge between two vertices
    // Input: Vertex1 and Vertex2 values of where to create an edge between   Output: Nothing
    // Preconditions: Assumes the vertices exist  Postconditions: Handle exceptions and continue on
    public void addEdge(int vertex1, int vertex2)
    {
        List<Integer> sls = adjacencyList.get(vertex1);
        List<Integer> dls = adjacencyList.get(vertex2);

        try{
            // Vertex does not exist
            if (!isVertex(vertex1) || !isVertex(vertex2))
                throw new Exception();
            // Edge actually exists
            else if(isAdjacent(vertex1, vertex2)){
                // Do nothing
            }
            else{
                // Adding the edge between the two vertices
                sls.add(vertex2);
                dls.add(vertex1);
            }
        }catch(Exception e){
            System.out.println("The vertex does not exist. Continuing on...");
        }
    }
    // Remove a vertex from the AdjacencyList if the vertex contains zero edges
    // Input: Vertex integer to be removed    Output: Nothing
    // Preconditions: Assumes the vertex exists  Postconditions: Handle the exception and continue on
    public void removeVertex(int vertexValue){
        try{
            if(adjacencyList.get(vertexValue).size() == 0)
                // Remove vertexValue if no edges connect to the vertexValue
                adjacencyList.remove(vertexValue);
            else{
                throw new Exception();
            }
        }catch(Exception e){
            System.out.println("Vertex " + vertexValue + " degree is not zero. Continuing on...");
        }
    }
    // Remove an edge between two vertices
    // Input: Vertex1 and Vertex2 values on where to remove an edge   Output: Nothing
    // Preconditions: Assumes an edge exists   Postcondition: Exception handled if no edge exists
    public void removeEdge(int vertex1, int vertex2){
        List<Integer> dls = adjacencyList.get(vertex2);
        List<Integer> sls = adjacencyList.get(vertex1);
        try{
            // Vertex does not exist
            if (!isVertex(vertex1) || !isVertex(vertex2))
                throw new IndexOutOfBoundsException();
            // Edge does not exist
            else if(!isAdjacent(vertex1, vertex2)){
                throw new NullPointerException();
            }
            else{
                // Remove the edge from the vertices
                dls.remove((vertex1-1));
                sls.remove((vertex2-1));
            }
        }catch(IndexOutOfBoundsException e1){
            System.out.println("The vertex does not exist. Continuing on...");
        }catch(NullPointerException e2){
            System.out.println("The two vertices don't contain an edge. Continuing on...");
        }
    }
    // Return a boolean value if there exists an edge connecting two vertices
    // Input: Vertex1 and Vertex2 values to determine if an edge exists between the two  Output: Boolean value
    // Preconditions: The two vertices exist  Postconditions: None
    public boolean isAdjacent(int vertex1, int vertex2){
        if(adjacencyList.get(vertex1).contains(vertex2) || adjacencyList.get(vertex2).contains(vertex1))
            // an edge connecting the two vertices exists
            return true;
        // Not adjacent vertices
        return false;
    }

    // Return a boolean value if a path can be found from a starting vertex to a destination vertex
    // Input: Vertex1 and Vertex2 values to determine a path between the two    Output: Boolean value
    // Preconditions: Assumes that the two vertices exist   Postconditions: None
    public boolean isPath(int vertex1, int vertex2){
        boolean visited [] = new boolean[adjacencyList.size()];
        LinkedList<Integer> queue = new LinkedList<Integer>();

        visited[vertex1] = true;
        queue.add(vertex1);
        // start iterating over the queue
        while(queue.size() != 0){
            vertex1 = queue.poll();
            iterator = adjacencyList.get(vertex1).listIterator();
            while(iterator.hasNext()){
                // Store the vertex value in the count value
                count = iterator.next();
                if(count==vertex2)
                    // Desired vertex was reached
                    return true;
                if(!visited[count]){
                    // Mark vertex as visited and add iteration value to queue
                    visited[count] = true;
                    queue.add(count);
                }
            }
        }
        // If BFS without visiting the destination
        return false;
    }

    // Return a string contains all the visited vertices from a starting vertex to a destination vertex
    // Input: Vertex1 and Vertex2 values to create a path between the two   Output: String created on results
    // Preconditions: Assumes that the two vertices exist    Postconditions: None
    public String getPath(int vertex1, int vertex2){
        boolean visited [] = new boolean[adjacencyList.size()];
        LinkedList<Integer> queue = new LinkedList<Integer>();
        String path = Integer.toString(vertex1) + ",";

        // Iterating through the queue to find a path
        visited[vertex1] = true;
        queue.add(vertex1);

        while(queue.size() != 0){
            vertex1 = queue.poll();
            iterator = adjacencyList.get(vertex1).listIterator();

            while(iterator.hasNext()){
                count = iterator.next();
                if(count==vertex2) {
                    // Completed path has been achieved
                    path += Integer.toString(count);
                    return path;
                }
                if(!visited[count]){
                    // Add visited node to list
                    visited[count] = true;
                    queue.add(count);
                    path += Integer.toString(count) + ",";
                }
            }
        }
        // If BFS without visiting the destination(destination)
        if(path.length() < 1)
            path = null;
        return path;
    }

    public static void main(String args[])
    {
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
            glist.addVertex(10);
            System.out.println("Is 1 a vertex: " + glist.isVertex(1));
            System.out.println("Is 3 a vertex: " + glist.isVertex(3));
            //Add edge test
            glist.addEdge(1,5);
            glist.addEdge(6, 1);
            glist.addEdge(1,2);
            glist.addEdge(5,3);
            glist.addEdge(4,3);
            glist.addEdge(4,7);  // Fail result
            // Degree test
            System.out.println("The degree of vertex 1 is: " + glist.degree(1));
            // Remove vertex test
            glist.removeVertex(10);
            glist.removeVertex(11); // Fail result
            // Remove edge test
            glist.removeEdge(1, 2);
            glist.removeEdge(10,1); // Fail result
            glist.removeEdge(7,1); // Fail result
            // Adjacent test
            System.out.println("Are the vertices 1 and 5 adjacent: " + glist.isAdjacent(1,5));
            System.out.println("Are the vertices 1 and 2 adjacent: " + glist.isAdjacent(1,2));
            // isPath test
            System.out.println("Does a path from vertex 1 to vertex 7 exist: " + glist.isPath(1,7));
            // getPath test
            System.out.println("Path from vertex 1 to vertex 7: " + glist.getPath(1, 7));
        }
        catch (Exception e1)
        {
            System.out.println("An error has occurred within the code");
        }
        sc.close();
    }
}