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
        Node<E> newNode = new Node<E>(element);

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
        Node<E> newNode = new Node<E>(element);
        
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
        if ((index >= 0) && (index <= this.size))
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
                Node<E> newNode = new Node<E>(element);
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

    public void remove(E element)
    {
        int index = -1;

        Node<E> tmp = head;

        for (int i = 0; i < this.size; i++)
        {
            if (tmp.getElement().equals(element))
            {
                index = i;
            }

            tmp = tmp.getNext();
        }

        if (index != -1)
        {
            remove(index);
        }
    }

    public E remove(int index)
    {

        if ((index >= 0) && (index < this.size))
        {
            Node<E> tmp = head;

            if (head.getNext() == null)
            {
                this.clear();

                return tmp.getElement();
            }

            if (index == 0)
            {
                head = head.getNext();

                size -=  1;

                return head.getElement();
            }
            else
            {
                for (int i = 0; i < index - 1; i++)
                {
                    tmp = tmp.getNext();
                }

                Node<E> toRemove = tmp.getNext();

                tmp.setNext(toRemove.getNext());

                if (toRemove.getNext() == null)
                {
                    tail = tmp;
                }

                toRemove.setNext(null);

                size -=  1;

                return toRemove.getElement();
            }
        }
        else
        {
            throw new IndexOutOfBoundsException();
        }

    }

    public void set(int index, E element)
    {
        if ((index >= 0) && (index < this.size))
        {
                Node<E> tmp = head;

                for (int i = 0; i < index; i++)
                {
                    tmp = tmp.getNext();
                }

                tmp.setElement(element);
        }
        else
        {
            throw new IndexOutOfBoundsException();
        }
    }

    public E get(int index)
    {
        if ((index >= 0) && (index < this.size))
        {
                Node<E> tmp = head;

                for (int i = 0; i < index; i++)
                {
                    tmp = tmp.getNext();
                }

                return tmp.getElement();
        }
        else
        {
            throw new IndexOutOfBoundsException();
        }
    }

    public boolean isEmpty()
    {
        return (this.size == 0);
    }

    public void clear()
    {
        this.head = null;
        this.tail = null;
        size = 0;
    }

    public int getSize()
    {
        return this.size;
    }

    public void print()
    {
        Node<E> tmp = head;

        for (int i = 0; i < size; i++)
        {
            System.out.print(" " + tmp.getElement().toString());
            tmp = tmp.getNext();
        }

        System.out.println();
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
        SingleLinkedList<String> sll = new SingleLinkedList<String>();

        sll.addHead("Hello,");
        System.out.println(sll.getSize());
        sll.add(1, "I am a");
        sll.addTail("wizard!");

        sll.print();

        sll.set(2, "warrior!");

        sll.print();

        sll.remove(1);

        sll.print();
    }
}