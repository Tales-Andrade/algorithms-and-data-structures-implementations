public class DisjointSets{
    private int[] parent;
    private int[] rank;
    
    DisjointSets(int n){
        if (n > 0)
        {
            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++){
                parent[i] = i;
            }
        }
    }

    public int find(int index){
        if (parent[index] != index){
            parent[index] = find(parent[index]);
        }

        return parent[index];  
    }

    public int union(int indexOne, int indexTwo){

        if (find(indexOne) != find(indexTwo)){
            if (rank[find(indexOne)] == rank[find(indexTwo)]){
                parent[find(indexOne)] = find(indexTwo);
                rank[find(indexTwo)] += 1;
            } else if (rank[indexOne] < rank[indexTwo]) {
                parent[find(indexOne)] = find(indexTwo);
            } else {
                parent[find(indexTwo)] = find(indexOne);
            }
        }

        return find(indexTwo);
    }

    public boolean isConnected(int valueOne, int valueTwo){
        return (find(valueOne) == find(valueTwo));
    }

    public void print(){
        int parentIndex = 0;
        int counter = 0;

        String output = "";

        String[] setStrings = new String[this.parent.length];

        for (int i=0; i<this.parent.length; i++) {
            parentIndex = find(i);
            if (setStrings[parentIndex] == null) {
                setStrings[parentIndex] = String.valueOf(i);
                counter +=1;
            } else {
                setStrings[parentIndex] += "," + i;
            }
        }

        output = counter + " set(s):\n";

        for (int i=0; i<this.parent.length; i++) {
            if (setStrings[i] != null) {
                output += i + " : " + setStrings[i] + "\n";
            }
        }
        

        System.out.println(output);
    }

    public static void main(String[] args){

        DisjointSets ds = new DisjointSets(6);
        
        ds.print();
        System.out.println("-> Union 2 and 3");
        ds.union(2,3);
        ds.print();
        System.out.println("-> Union 2 and 3");
        ds.union(2,3);
        ds.print();
        System.out.println("-> Union 2 and 1");
        ds.union(2,1);
        ds.print();
        System.out.println("-> Union 4 and 5");
        ds.union(4,5);
        ds.print();
        System.out.println("-> Union 3 and 1");
        ds.union(3,1);
        ds.print();
        System.out.println("-> Union 2 and 4");
        ds.union(2,4);
        ds.print();
    }
}