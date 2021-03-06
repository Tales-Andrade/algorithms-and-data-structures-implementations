import java.util.ArrayList;

public class MaxHeap<T extends Comparable<T>>{
    private  ArrayList<T> heap = new ArrayList<T>();

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

    public T removeMax()
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

        while((hasParent(index)) && (this.heap.get(getParentIndex(index)).compareTo(this.heap.get(index)) < 0))
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

            if ((hasRightChild(index)) && (this.heap.get(getRightChildIndex(index)).compareTo(this.heap.get(getLeftChildIndex(index))) > 0))
            {
                childIndex = getRightChildIndex(index);
            }

            if (this.heap.get(index).compareTo(this.heap.get(childIndex)) < 0)
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
        MaxHeap<Integer> heap = new MaxHeap<Integer>();

        heap.add(2);
        heap.add(1);
        heap.add(4);
        heap.add(7);
        heap.add(9);
        heap.add(8);
        heap.add(5);

        heap.print();

        heap.removeMax();

        heap.print();

        System.out.println(heap.getSize());
        System.out.println("The code is working.");
    }
}