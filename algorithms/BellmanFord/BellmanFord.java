import java.util.*;

public class BellmanFord{
    Graph graph;

    BellmanFord(int numVertices, int numEdges){
        graph = new Graph(numVertices, numEdges);
    }

    public void addEdge(int source, int destination, int weight){
        this.graph.addEdge(source, destination, weight);
    }

    public int[] bellmanFord(int source){
        int[] distances = new int[this.graph.getNumVertices()];

        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        for (int i = 1; i < this.graph.getNumVertices(); i++){
            for (int j = 0; j < this.graph.getNumEdges(); j++){
                Relax(distances, this.graph.edges.get(j).getSource(), this.graph.edges.get(j).getDestination(), this.graph.edges.get(j).getWeight());
            }
        }

        for (int j = 0; j < this.graph.getNumEdges(); j++){
            if (Relax(distances, this.graph.edges.get(j).getSource(), this.graph.edges.get(j).getDestination(), this.graph.edges.get(j).getWeight())){
                return null;
            }
        }

        return distances;
    }

    public void print(int[] distances, int source){
        if (distances == null){
            System.out.println("There exists a negative-weight cycle reachable from the source vertex.");
            return;
        }

        for (int i = 0; i < distances.length; i++){
            System.out.println("The distance from the source vertex " + source + " to the destination vertex " + i + " is " + distances[i]);
        }
    }

    private boolean Relax(int[] distances, int u, int v, int weight){
        if ((distances[u] != Integer.MAX_VALUE) && ((distances[u] + weight) < distances[v])){
            distances[v] = distances[u] + weight;

            return true;
        }

        return false;
    }
    
    private class Edge{
        private int source;
        private int destination;
        private int weight;

        Edge(int source, int destination, int weight){
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        int getSource(){
            return this.source;
        }

        void setSource(int source){
            this.source = source;
        }

        int getDestination(){
            return this.destination;
        }

        void setDestination(int destination){
            this.destination = destination;
        }

        int getWeight(){
            return this.weight;
        }

        void setWeight(int weight){
            this.weight = weight;
        }
    }

    private class Graph{
        private int numVertices;
        private int numEdges;
        ArrayList<Edge> edges = new ArrayList<Edge>();
        
        Graph(int numVertices, int numEdges){
            this.numVertices = numVertices;
            this.numEdges = numEdges;
        }

        void addEdge(int source, int destination, int weight){
            this.edges.add(new Edge(source, destination, weight));
        }

        int getNumVertices(){
            return this.numVertices;
        }

        void setNumVertices(int numVertices){
            this.numVertices = numVertices;
        }

        int getNumEdges(){
            return this.numEdges;
        }

        void setNumEdges(int numEdges){
            this.numEdges = numEdges;
        }
    }

    public static void main(String[] args){
        System.out.println("Exercise 1: \n");
        BellmanFord bf = new BellmanFord(3, 4);

        bf.addEdge(0, 1, 5);
        bf.addEdge(1, 0, -2);
        bf.addEdge(0, 2, -4);
        bf.addEdge(2, 1, 7);

        bf.print(bf.bellmanFord(0), 0);
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("Exercise 2: \n");
        BellmanFord bf2 = new BellmanFord(3, 4);

        bf2.addEdge(0, 1, 5);
        bf2.addEdge(1, 0, -4);
        bf2.addEdge(0, 2, -4);
        bf2.addEdge(2, 1, 7);

        bf2.print(bf2.bellmanFord(0), 0);
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("Exercise 3: \n");
        BellmanFord bf3 = new BellmanFord(5, 8);

        bf3.addEdge(0, 1, -1);
        bf3.addEdge(0, 2, 4);
        bf3.addEdge(1, 2, 3);
        bf3.addEdge(1, 3, 2);
        bf3.addEdge(1, 4, 2);
        bf3.addEdge(3, 2, 5);
        bf3.addEdge(3, 1, 1);
        bf3.addEdge(4, 3, -3);

        bf3.print(bf3.bellmanFord(0), 0);
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("Exercise 4: \n");
        BellmanFord bf4 = new BellmanFord(9, 10);

        bf4.addEdge(0, 1, 1);
        bf4.addEdge(1, 2, 1);
        bf4.addEdge(2, 4, 1);
        bf4.addEdge(4, 3, -3);
        bf4.addEdge(3, 2, 1);
        bf4.addEdge(1, 5, 4);
        bf4.addEdge(1, 6, 4);
        bf4.addEdge(5, 6, 5);
        bf4.addEdge(6, 7, 4);
        bf4.addEdge(5, 7, 3);

        bf4.print(bf4.bellmanFord(0), 0);
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("The code works");
    }
}