import java.util.ArrayList;

public class MinHeap<T extends Comparable<T>>{
    private  ArrayList<T> heap = new ArrayList<T>();

    public MinHeap(){

    }

    public int getLeftChildIndex(int parentIndex)
    {
        return ((2 * parentIndex) + 1);
    }

    public int getRightChildIndex(int parentIndex)
    {
        return ((2 * parentIndex) + 2);
    }

    public int getParentIndex(int childIndex)
    {
        return ((childIndex - 1)/2);
    }

    public boolean hasLeftChild(int parentIndex)
    {
        return (getLeftChildIndex(parentIndex) < this.heap.size());
    }

    public boolean hasRightChild(int parentIndex)
    {
        return (getRightChildIndex(parentIndex) < this.heap.size());
    }

    public boolean hasParent(int childIndex)
    {
        return (getParentIndex(childIndex) >= 0);
    }

    public void add (T element)
    {
        this.heap.add(element);

        upHeap();
    }

    public void swap(int indexOne, int indexTwo)
    {
        T tmp = this.heap.get(indexOne);
        this.heap.set(indexOne, this.heap.get(indexTwo));
        this.heap.set(indexTwo, tmp);
    }

    public T peek()
    {
        if (this.heap.size() != 0)
        {
            return this.heap.get(0);
        }
        else
        {
            throw new RuntimeException();
        }
    }

    public T removeMin()
    {
        if (this.heap.size() != 0)
        {
            T tmp = this.heap.get(0);
            
            this.heap.set(0, this.heap.get(this.heap.size() - 1));

            this.heap.remove(this.heap.size() - 1);

            downHeap();

            return tmp;
        }
        else
        {
            throw new RuntimeException();
        }
    }

    public void upHeap()
    {
        int index = this.heap.size() - 1;

        while((hasParent(index)) && (this.heap.get(getParentIndex(index)).compareTo(this.heap.get(index)) > 0))
        {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    public void downHeap()
    {
        int index = 0;

        while(hasLeftChild(index))
        {
            int childIndex = getLeftChildIndex(index);

            if ((hasRightChild(index)) && (this.heap.get(getRightChildIndex(index)).compareTo(this.heap.get(getLeftChildIndex(index))) < 0))
            {
                childIndex = getRightChildIndex(index);
            }

            if (this.heap.get(index).compareTo(this.heap.get(childIndex)) >= 0)
            {
                swap(index, childIndex);
                index = childIndex;
            }
            else
            {
                break;
            }

        }
    }

    public int getSize()
    {
        return this.heap.size();
    }

    public void print(){
        System.out.println(this.heap.toString());
    }

    public static void main(String[] args)
    {
        MinHeap<Character> heap = new MinHeap<Character>();

        heap.add('a');
        heap.add('e');
        heap.add('b');
        heap.add('f');
        heap.add('l');
        heap.add('u');
        heap.add('k');
        heap.add('m');


        heap.print();

        heap.add('c');

        heap.print();

        System.out.println(heap.getSize());
    
        heap.removeMin();

        heap.print();

        heap.removeMin();

        heap.print();

        System.out.println("The code is working.");
    }
}