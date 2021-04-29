import java.util.*;

class Vertex<T>{
    private ArrayList<Edge<T>> adjList = new ArrayList<Edge<T>>();
    private T element;

    Vertex(T element){
        this.element = element;
    }

    void setElement(T element){
        this.element = element;
    }

    T getElement(){
        return this.element;
    }

    void addEdge(Edge<T> edge){
        this.adjList.add(edge);
    }

    void removeEdge(Edge<T> edge){
        this.adjList.remove(edge);
    }

    boolean hasEdge(Edge<T> edge){

        for (Edge<T> edgeTwo : this.adjList){
            if (edge.equals(edgeTwo)) return true;
        }

        return false;
    }

    Edge<T> findEdge(Vertex<T> vertex){
        for (Edge<T> edge : this.adjList){
            if (edge.getVertex().equals(vertex)) return edge;
        }

        return null;
    }

    boolean hasNeighbor(Vertex<T> vertex){
        for (Edge<T> edge : this.adjList){
            if (edge.getVertex().equals(vertex)) return true;
        }

        return false;
    }

    ArrayList<T> getNeighbors(){
        ArrayList<T> neighbors = new ArrayList<T>(this.adjList.size());

        for (Edge<T> edge : this.adjList){
            neighbors.add(edge.getVertex().getElement());
        }

        return neighbors;
    }

    boolean isAdjacent(Vertex<T> vertex){
        for (Edge<T> edge : this.adjList){
            if (edge.getVertex().equals(vertex)) return true;
        }

        return false;
    }

    int getTotalWeight(){
        int totalWeight = 0;

        for (Edge<T> edge : this.adjList){
            totalWeight += edge.getWeight();
        }

        return totalWeight;
    }

    int getDegree(){
        return this.adjList.size();
    }

    void clearEdges(){
        for (Edge<T> edge : this.adjList){
            this.removeEdge(edge);
        }
    }

    void print(){
        for (int i = 0; i < this.adjList.size(); i++){
            if (i == (this.adjList.size() - 1)){
                System.out.print(this.adjList.get(i).getVertex().getElement().toString());
            }
            else {
                System.out.print(this.adjList.get(i).getVertex().getElement().toString() + ", ");
            }
        }
    }
}