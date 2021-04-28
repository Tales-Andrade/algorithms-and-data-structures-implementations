import java.util.*;

public class DirectGraph<E>{
    private ArrayList<Edge> edges = new ArrayList<Edge>();
    private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
    private int nb_vertices = 0;

    public Edge findEdge(Vertex startVertex, Vertex endVertex){
        return startVertex.findEdge(endVertex);
    }

    public Vertex getVertex (E element){
        for (Vertex vertex : this.vertices){
            if (vertex.getElement().equals(element)) return vertex;
        }

        return null;
    }

    public int getSize(){
        return this.nb_vertices;
    }
}