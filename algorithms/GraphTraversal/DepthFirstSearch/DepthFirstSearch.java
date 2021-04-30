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