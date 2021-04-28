import java.util.*;

class Vertex<E>{
    private ArrayList<Edge> edges = new ArrayList<Edge>();
    private E element;

    Vertex(E element){
        this.element = element;
    }

    void setElement(E element){
        this.element = element;
    }

    E getElement(){
        return this.element;
    }

    void addEdge(Edge edge){
        this.edges.add(edge);
    }

    void removeEdge(Edge edge){
        this.edges.remove(edge);
    }

    boolean hasEdge(Edge edge){
        for (int i = 0; i < this.edges.size(); i++){
            if (edge.equals(this.edges.get(i))) return true;
        }

        return false;
    }

    Edge findEdge(Vertex vertex){
        for (int i = 0; i < this.edges.size(); i++){
            if (this.edges.get(i).getEndVertex().equals(vertex)) return this.edges.get(i);
        }

        return null;
    }

    boolean hasNeighbor(Vertex vertex){
        for (int i = 0; i < this.edges.size(); i++){
            if (this.edges.get(i).getEndVertex().equals(vertex)) return true;
        }

        return false;
    }

    int getDegree(){
        return this.edges.size();
    }

    void clearEdges(){
        for (Edge edge : this.edges){
            this.removeEdge(edge);
        }
    }

    void print(){
        System.out.println(this.element.toString());
    }
}