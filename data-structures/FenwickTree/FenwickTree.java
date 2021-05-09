import java.util.*;

public class FenwickTree{
    private final int size;
    private long[] fenwickTree;
    private long[] currentTree;
    private long[] tree;

    public FenwickTree(int size){
        this.size = size + 1;
        this.fenwickTree = new long[this.size];
    }

    public FenwickTree(long[] tree){
        this.size = tree.length;

        tree[0] = 0L;

        this.fenwickTree = tree.clone();
        this.tree = tree.clone();

        for (int i = 1; i < this.size; i++){
            int parent = i + lsb(i);
            
            if (parent < this.size){
                this.tree[parent] += this.tree[i];
                this.fenwickTree[parent] += this.fenwickTree[i];
            }
        }

        this.currentTree = fenwickTree.clone();
    }

    public void add(int index, long value){
        while (index < this.size){
            this.currentTree[index] += value;
            this.tree[index] += value;

            index += lsb(index);
        }
    }

    public void set(int index, long value){
        add(index, value - sum(index, index));
    }

    public long get(int index){
        return sum(index, index);
    }

    public long getTwo(int index){
        return (prefixSum(index, currentTree) - prefixSum(index - 1, fenwickTree));
    }

    public long sum(int leftInterval, int rightInterval){
        return (prefixSum(rightInterval, this.tree) - prefixSum(leftInterval - 1, this.tree));
    }

    public long prefixSum(int index, long[] tree){
        long sum = 0L;

        while(index != 0){
            sum += tree[index];

            index -= lsb(index);
        }

        return sum;
    }

    public int lsb(int index){
        return Integer.lowestOneBit(index);
    }

    public void updateRange(int leftInterval, int rightInterval, long value){
        add(leftInterval, +value);
        add(rightInterval + 1, -value);
    }

    public static void main(String[] args){
        long[] values1 = {0, +1, -2, +3, -4, +5, -6};
        long[] values2 = {0, 1, 2, 2, 4};

        FenwickTree ft1 = new FenwickTree(values1);
        FenwickTree ft2 = new FenwickTree(values2);

        System.out.println("First Test - Range Updates and Point Queries");
        ft1.updateRange(1, 4, 10);
        System.out.println(ft1.getTwo(1));
        System.out.println(ft1.getTwo(4));
        System.out.println(ft1.getTwo(5));
        ft1.updateRange(3, 6, -20);
        System.out.println(ft1.getTwo(3));
        System.out.println(ft1.getTwo(4));
        System.out.println(ft1.getTwo(5));
        System.out.println("--------------------------------------------------");
        System.out.println("Second Test - Range Queries and Point Updates");
        System.out.println(ft2.sum(1, 4));
        ft2.add(3, 1);

        System.out.println(ft2.sum(1, 4));
        ft2.set(4, 0);

        System.out.println(ft2.sum(1, 4));
        System.out.println(ft2.get(2));
        System.out.println("--------------------------------------------------");
        System.out.println("This code works.");
    }
}