import java.util.*;

public class DirectGraph<T extends Comparable<T>>{
    private HashMap<T, Vertex<T>> vertexMap = new HashMap<T, Vertex<T>>();

    public void addVertex(Vertex<T> vertex){
        vertexMap.put(vertex.getElement(), vertex);
    }

    public Vertex<T> removeVertex(Vertex<T> vertex){
        return vertexMap.remove(vertex.getElement());
    }

    public Vertex<T> getVertex(Vertex<T> vertex){
        return vertexMap.get(vertex.getElement());
    }

    public void addEdge(Vertex<T> vertex, Edge<T> edge){
        vertex.addEdge(edge);
    }

    public void removeEdge(Vertex<T> vertex, Edge<T> edge){
        vertex.removeEdge(edge);
    }

    public Edge<T> findEdge(Vertex<T> vertexOne, Vertex<T> vertexTwo){
        return vertexOne.findEdge(vertexTwo);
    }

    public ArrayList<T> getNeighbors(Vertex<T> vertex){
         return vertex.getNeighbors();
    }

    public boolean isAdjacent(Vertex<T> vertexOne, Vertex<T> vertexTwo){
        return vertexOne.isAdjacent(vertexTwo);
    }

    public int getSize(){
        return this.vertexMap.size();
    }

    public int getTotalWeight(){
        int totalWeight = 0;

        for (Vertex<T> vertex : this.vertexMap.values()){
            totalWeight += vertex.getTotalWeight();
        }

        return totalWeight;
    }

    public void print(){
        for (Vertex<T> vertex : this.vertexMap.values()){
            System.out.print(vertex.getElement().toString() + ": ");
            vertex.print();
            System.out.println();
        }
    }

    public static void main(String[] args){
        DirectGraph<Integer> g = new DirectGraph<Integer>();

        Vertex<Integer> v1 = new Vertex<Integer>(1);
        Vertex<Integer> v2 = new Vertex<Integer>(2);
        Vertex<Integer> v3 = new Vertex<Integer>(3);
        Vertex<Integer> v4 = new Vertex<Integer>(4);
        Vertex<Integer> v5 = new Vertex<Integer>(5);
        Vertex<Integer> v6 = new Vertex<Integer>(6);
        Vertex<Integer> v7 = new Vertex<Integer>(7);

        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);
        g.addVertex(v6);
        g.addVertex(v7);

        Edge<Integer> e1 = new Edge<Integer>(v2);
        Edge<Integer> e2 = new Edge<Integer>(v3);
        Edge<Integer> e3 = new Edge<Integer>(v3);
        Edge<Integer> e4 = new Edge<Integer>(v4);
        Edge<Integer> e5 = new Edge<Integer>(v4);
        Edge<Integer> e6 = new Edge<Integer>(v4);
        Edge<Integer> e7 = new Edge<Integer>(v5);
        Edge<Integer> e8 = new Edge<Integer>(v6);
        Edge<Integer> e9 = new Edge<Integer>(v6);
        Edge<Integer> e10 = new Edge<Integer>(v6);
        Edge<Integer> e11 = new Edge<Integer>(v7);
        Edge<Integer> e12 = new Edge<Integer>(v7);

        g.addEdge(v1, e1);
        g.addEdge(v1, e2);
        g.addEdge(v4, e3);
        g.addEdge(v1, e4);
        g.addEdge(v2, e5);
        g.addEdge(v5, e6);
        g.addEdge(v2, e7);
        g.addEdge(v3, e8);
        g.addEdge(v4, e9);
        g.addEdge(v7, e10);
        g.addEdge(v4, e11);
        g.addEdge(v5, e12);

        g.print();

        System.out.println("The code works.");
    }
}