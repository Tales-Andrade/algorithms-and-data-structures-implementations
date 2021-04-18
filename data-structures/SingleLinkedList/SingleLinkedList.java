public class SingleLinkedList<E>
{
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public SingleLinkedList()
    {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void addHead(E element)
    {
        Node<E> newNode = new Node(element);

        if (size == 0)
        {
            this.head = newNode;
            this.tail = newNode;
        }
        else
        {
            newNode.setNext(this.head);
            this.head = newNode;
        }

        size += 1;
    }

    public void addTail(E element)
    {
        Node<E> newNode = new Node(element);
        
        if (size == 0)
        {
            this.head = newNode;
            this.tail = newNode;
        }
        else
        {
            this.tail.setNext(newNode);
            this.tail = newNode;
        }

        size += 1;

    }

    public void add(int index, E element)
    {
        if ((index >= 0) && (index < this.size))
        {
            if (index == 0)
            {
                addHead(element);
            }
            else if (index == this.size)
            {
                addTail(element);
            }
            else
            {
                Node<E> newNode = new Node(element);
                Node<E> tmp = head;

                for (int i = 0; i < index; i++)
                {
                    tmp = tmp.getNext();
                }

                newNode.setNext(tmp.getNext());

                tmp.setNext(newNode);

                size += 1;
            }
        }
        else
        {
            throw new IndexOutOfBoundsException();
        }

    }

    public void clear()
    {
        this.head = null;
        this.tail = null;
        size = 0;
    }

    private class Node<E>
    {
        private Node<E> next;
        private E element;

        Node(E element)
        {
            this.element = element;
            this.next = null;
        }

        void setNext(Node<E> next)
        {
            this.next = next;
        }

        Node<E> getNext()
        {
            return this.next;
        }

        void setElement(E element)
        {
            this.element = element;
        }

        E getElement()
        {
            return this.element;
        }
    }

    public static void main(String[] args)
    {
    }
}