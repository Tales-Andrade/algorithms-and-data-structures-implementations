import java.util.* ;

public class ArrayList<T>
{
    private T arrayList[];
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayList()
    {
        this.arrayList = (T[]) new Object[10];
        this.size = 0;
    }

    @SuppressWarnings("unchecked")
    public ArrayList(int capacity)
    {
        this.arrayList = (T[]) new Object[capacity];
        this.size = 0;
    }
 
    public T get(int index)
    {
        if ((index >= 0) && (index < this.size))
        {
            return this.arrayList[index];
        }
        else
        {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public void set(int index, T data)
    {
        if ((index >= 0) && (index < this.size))
        {
            this.arrayList[index] = data;
        }
        else
        {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @SuppressWarnings("unchecked")
    public void add(int index, T data)
    {
        if ((index >= 0) && (index <= this.size))
        {
            if (this.size == this.arrayList.length)
            {
                T[] tmp = (T[]) new Object[this.size * 2];
                
                for (int i = 0; i < this.size; i++)
                {
                    tmp[i] = this.arrayList[i];
                }

                this.arrayList = tmp;
            }

            for (int i = this.size; i > index; i--)
            {
                this.arrayList[i] = this.arrayList[i - 1];
            }

            arrayList[index] = data;
            size += 1;
        }
        else
        {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public void remove(int index)
    {
        if ((index >= 0) && (index < this.size))
        {
            T tmp = this.arrayList[index];

            for (int i = index; i < (this.size-1); i++)
            {
                this.arrayList[i] = this.arrayList[i + 1];
            }

            this.arrayList[size - 1] = null;
            this.size -= 1;
        }
        else
        {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public void remove(T data)
    {
        int index = -1;

        for (int i = 0; i < this.size; i++)
        {
            if (this.arrayList[i].equals(data))
            {
                index = i;
            }
        }

        if (index != -1)
        {
            remove(index);
        }
    }

    public void clear()
    {
        for (int i = 0; i < this.size; i++)
        {
            this.arrayList[i] = null;
        }

        this.size = 0;
    }

    public int getSize()
    {
        return this.size;
    }

    public boolean isEmpty()
    {
        return (this.size == 0);
    }

    public static void main(String[] args)
    {
        ArrayList<Integer> arr = new ArrayList<Integer>();

        System.out.println(arr.isEmpty());

        arr.add(0, 1);
        arr.add(1, 3);
        arr.add(2, 5);

        System.out.println(arr.get(1));

        System.out.println(arr.isEmpty());
    }
}