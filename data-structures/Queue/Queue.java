import java.util.*;

public class Queue<E>
{
    private List<E> queue;

    public Queue()
    {
        queue = new ArrayList<E>();
    }

    public void enqueue(E element)
    {
        this.queue.add(this.queue.size(), element);
    }

    public E dequeue()
    {
        if (this.isEmpty())
        {
            throw new RuntimeException("The queue is empty.");
        }
        else
        {
            return this.queue.remove(0);
        }
    }

    public boolean isEmpty()
    {
        return ((this.queue.size()) == 0);
    }

    public int getSize()
    {
        return this.queue.size();
    }

    public E peek()
    {
        if (this.isEmpty())
        {
            throw new RuntimeException("The queue is empty.");
        }
        else
        {
            return this.queue.get(0);
        }
    }

    public static void main(String[] args)
    {
        Queue<Integer> queue = new Queue<Integer>();

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        System.out.println(queue.isEmpty()); // false

        System.out.println(queue.getSize()); // 3

        System.out.println(queue.dequeue()); // 1

        System.out.println(queue.peek()); // 2
    }
}