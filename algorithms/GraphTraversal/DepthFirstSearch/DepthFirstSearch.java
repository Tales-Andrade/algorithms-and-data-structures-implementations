import java.util.*;

public class DepthFirstSearch<T>{

    public static <T> void recursiveDFS(Vertex<T> vertex){
        vertex.setVisited(true);
        System.out.print(vertex.getElement().toString() + " ");

        for (Edge<T> edge : vertex.getAdjList()){
            if (!(edge.getVertex().isVisited())){
                recursiveDFS(edge.getVertex());
            }
        }
    }

    public static <T> void stackDFS(Vertex<T> vertex){
        Stack<Vertex<T>> stack = new Stack<Vertex<T>>();

        stack.push(vertex);
        vertex.setVisited(true);
        System.out.print(vertex.getElement().toString() + " ");

        while(!(stack.isEmpty())){
            Vertex<T> tmp = stack.pop();

            for (Edge<T> edge : tmp.getAdjList()){
                if (!(edge.getVertex().isVisited())){
                    edge.getVertex().setVisited(true);
                    System.out.print(edge.getVertex().getElement().toString() + " ");
                    stack.push(edge.getVertex());
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
        System.out.println("Test for recursive depth first search algorithm");
        DepthFirstSearch.recursiveDFS(v1);

        System.out.println();
        System.out.println("---------------------------");
        System.out.println("Test for stack depth first search algorithm");
        g.resetVisited();
        DepthFirstSearch.stackDFS(v1);

        System.out.println();
        System.out.println("---------------------------");
        System.out.println("The code works.");
    }
}