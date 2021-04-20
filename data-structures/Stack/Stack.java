import java.util.*;

public class Stack<E>
{
    private List<E> stack;

    public Stack()
    {
        this.stack = new ArrayList<E>();
    }

    public void push(E element)
    {
        this.stack.add(0, element);
    }

    public E pop()
    {
        if (this.isEmpty())
        {
            throw new EmptyStackException();
        }
        else
        {
            return this.stack.remove(0);
        }
    }

    public boolean isEmpty()
    {
        return ((this.stack.size()) == 0);
    }

    public int getSize()
    {
        return this.stack.size();
    }

    public E peek()
    {
        if (this.isEmpty())
        {
            throw new EmptyStackException();
        }
        else
        {
            return this.stack.get(0);
        }
    }

    public static void main(String[] args)
    {
        Stack<Integer> stack = new Stack<Integer>();

        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println(stack.isEmpty()); // false

        System.out.println(stack.getSize()); // 3

        System.out.println(stack.pop()); // 3

        System.out.println(stack.peek()); // 2
    }
}