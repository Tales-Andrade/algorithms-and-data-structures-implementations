class Edge<Vertex>{
    private Vertex startVertex;
    private Vertex endVertex;
    private int weight;

    // Weighted Graph
    Edge(Vertex vertexOne, Vertex vertexTwo, int weight){
        this.startVertex = vertexOne;
        this.endVertex = vertexTwo;
        this.weight = weight;
    }

    // Unweighted Graph
    Edge(Vertex vertexOne, Vertex vertexTwo){
        this.startVertex = vertexOne;
        this.endVertex = vertexTwo;
    }

    Vertex getEndVertex(){
        return this.endVertex;
    }

    void swap(){
        Vertex tmp = this.startVertex;
        this.startVertex = this.endVertex;
        this.endVertex = tmp;
    }

    void print(){
        System.out.println(startVertex.toString() + "-----" + endVertex.toString());
    }
}