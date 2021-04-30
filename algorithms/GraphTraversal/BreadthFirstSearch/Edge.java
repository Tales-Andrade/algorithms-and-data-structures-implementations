class Edge<T>{
    private Vertex<T> vertex;
    private int weight;

    // Weighted Graph
    Edge(Vertex<T> vertex, int weight){
        this.vertex = vertex;
        this.weight = weight;
    }

    // Unweighted Graph
    Edge(Vertex<T> vertex){
        this.vertex = vertex;
        this.weight = 0;
    }

    Vertex<T> getVertex(){
        return this.vertex;
    }

    void setVertex(Vertex<T> vertex){
        this.vertex = vertex;
    }

    int getWeight(){
        return this.weight;
    }

    void setWeight(int weight){
        this.weight = weight;
    }
}