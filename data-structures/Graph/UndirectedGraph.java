import java.util.*;

public class UndirectedGraph<T extends Comparable<T>>{
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

    public void addEdge(Edge<T> edgeOne, Edge<T> edgeTwo){
        edgeTwo.getVertex().addEdge(edgeOne);
        edgeOne.getVertex().addEdge(edgeTwo);
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
}