import java.util.*;

public class BreadthFirstSearch<T>{

    public static <T> void queueBFS(Vertex<T> vertex){
        Queue<Vertex<T>> queue = new LinkedList<Vertex<T>>();

        vertex.setVisited(true);
        System.out.print(vertex.getElement().toString() + " ");
        queue.add(vertex);

        while(!(queue.isEmpty())){
            Vertex<T> tmp = queue.poll();

            for(Edge<T> edge : tmp.getAdjList()){
                if(!(edge.getVertex().isVisited())){
                    edge.getVertex().setVisited(true);
                    System.out.print(edge.getVertex().getElement().toString() + " ");
                    queue.add(edge.getVertex());
                }
            }
        }
    }

    public static void main(String[] args){
        
        DirectGraph<Character> g = new DirectGraph<Character>();

        Vertex<Character> v1 = new Vertex<Character>('a');
        Vertex<Character> v2 = new Vertex<Character>('b');
        Vertex<Character> v3 = new Vertex<Character>('c');
        Vertex<Character> v4 = new Vertex<Character>('d');
        Vertex<Character> v5 = new Vertex<Character>('e');
        Vertex<Character> v6 = new Vertex<Character>('f');
        Vertex<Character> v7 = new Vertex<Character>('g');
        Vertex<Character> v8 = new Vertex<Character>('h');
        Vertex<Character> v9 = new Vertex<Character>('i');

        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);
        g.addVertex(v6);
        g.addVertex(v7);
        g.addVertex(v8);
        g.addVertex(v9);

        g.addEdge(v1, v2);
        g.addEdge(v1, v4);
        g.addEdge(v2, v1);
        g.addEdge(v2, v3);
        g.addEdge(v2, v5);
        g.addEdge(v3, v2);
        g.addEdge(v3, v6);
        g.addEdge(v4, v1);
        g.addEdge(v4, v5);
        g.addEdge(v4, v7);
        g.addEdge(v5, v2);
        g.addEdge(v5, v4);
        g.addEdge(v5, v6);
        g.addEdge(v5, v8);
        g.addEdge(v6, v3);
        g.addEdge(v6, v5);
        g.addEdge(v6, v9);
        g.addEdge(v7, v4);
        g.addEdge(v7, v8);
        g.addEdge(v8, v5);
        g.addEdge(v8, v7);
        g.addEdge(v8, v9);
        g.addEdge(v9, v6);
        g.addEdge(v9, v8);

        System.out.println("Test for direct graph");
        g.print();

        System.out.println("---------------------------");
        System.out.println("Test for queue breath first search algorithm");
        BreadthFirstSearch.queueBFS(v1);

        System.out.println();
        
        g.resetVisited();
        System.out.println("---------------------------");
        System.out.println("The code works.");
    }
}