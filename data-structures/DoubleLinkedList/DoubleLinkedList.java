public class DoubleLinkedList<E>
{
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public DoubleLinkedList()
    {
        head = new Node<E>(null);
        tail = head;
        size = 0;
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
            this.head.setPrevious(newNode);
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
            newNode.setPrevious(this.tail);
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
                newNode.setPrevious(tmp);

                tmp.getNext().setPrevious(newNode);
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
                head.getNext().setPrevious(null);
                head = head.getNext();

                size -=  1;

                return tmp.getElement();
            }
            else
            {
                for (int i = 0; i < index; i++)
                {
                    tmp = tmp.getNext();
                }

                if (index == (size - 1))
                {
                    tmp.getPrevious().setNext(tmp.getNext());
                }
                else
                {
                    tmp.getPrevious().setNext(tmp.getNext());
                
                    tmp.getNext().setPrevious(tmp.getPrevious());
                }

                size -=  1;

                return tmp.getElement();
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

    public void reverse()
    {
        Node<E> tmp, next;

        tmp = this.head;

        for (int i = 0; i < this.getSize(); i++)
        {
            next = tmp.getNext();
            tmp.setNext(tmp.getPrevious());
            tmp.setPrevious(next);
            tmp = next;
        }

        tmp = head;
        head = tail;
        tail = tmp;
    }

    private class Node<E>
    {
        private E element;
        private Node<E> next;
        private Node<E> previous;


        Node(E element)
        {
            this.element = element;
            this.next = null;
            this.previous = null;
        }

        E getElement()
        {
            return this.element;
        }

        void setElement(E element)
        {
            this.element = element;
        }

        Node<E> getPrevious()
        {
            return previous;
        }

        void setPrevious(Node<E> previous)
        {
            this.previous = previous;
        }

        Node<E> getNext()
        {
            return next;
        }

        void setNext(Node<E> next)
        {
            this.next = next;
        }
    }

    public static void main(String[] args)
    {
        DoubleLinkedList<String> dll = new DoubleLinkedList<String>();

        dll.addHead("Hello,");
        System.out.println(dll.getSize());
        dll.add(1, "I am a");
        dll.addTail("wizard!");

        dll.print();

        dll.set(2, "warrior!");

        dll.print();

        dll.remove(1);

        dll.print();

        System.out.println(dll.get(1));

        dll.reverse();

        dll.print();
    }
}