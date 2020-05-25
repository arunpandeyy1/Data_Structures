/**
 * @author Arun Pandey
 */
import java.util.Iterator;
@SuppressWarnings("unchecked")
public class DynamicArray<T> implements Iterable<T>
{
    private T[] array;
    private int length = 0;
    private int capacity = 0;
    public DynamicArray()
    {
        this(10);        
    }
    public DynamicArray(int capacity)
    {
        if(capacity<0)
        {
            throw new IllegalArgumentException("Illegal capacity: "+capacity);
        }
        this.capacity = capacity;
        array = (T[]) new Object[capacity];
    }
    public int size()
    {
        return length;
    }
    public boolean isEmpty()
    {
        return (size() ==0);
    }
    public T getIndex(int index)
    {
        return array[index];
    }
    public void set(int index,T element)
    {
        array[index] = element;
    }
    public void clear()
    {
        for(int i=0;i<length;i++)
        {
            array[i] = null;
        }
        length = 0;
    }
    public void add(T element)
    {
        if(length+1>=capacity)
        {
            if(capacity==0)
            {
                capacity = 1;
            }
            else
            {
                capacity*= 2;
            }
            T[] newArray = (T[]) new Object[capacity];
            for(int i=0;i<length;i++)
            {
                newArray[i] = array[i];
            }
            array = newArray;
        }
        array[length++] = element;
    }
    public T removeAt(int index)
    {
        if(index>=length||index<0)
        {
            throw new IndexOutOfBoundsException();
        }
        T data = array[index];
        T[] newArray = (T[]) new Object[length-1];
        for(int i=0,j=0;i<length;i++,j++)
        {
            if(i==index)
            {
                j--;
            }
            else
            {
                newArray[j] = array[i];
            }
        }
        array = newArray;
        capacity = length--;
        return data;
    }
    public int indexOf(Object obj)
    {
        for(int i=0;i<length;i++)
        {
            if(obj==null)
            {
                if(array[i]==null)
                {
                    return i;
                }
            }
            else
            {
                if(obj.equals(array[i]))
                {
                    return i;
                }
            }
        }
        return -1;
    }
    public boolean remove(Object obj)
    {
        int index = indexOf(obj);
        if(index == -1)
        {
            return false;
        }
        removeAt(index);
        return true;
    }
    public boolean contains(Object obj)
    {
        return indexOf(obj)!=-1;
    }
    public Iterator<T> iterator() 
    {
       return new Iterator<T>()
       {
           int index = 0;
            public boolean hasNext()
            {
                return index < length;
            }
            public T next() 
            {
                return array[index++];
            }
            public void remove() 
            {
                throw new UnsupportedOperationException();
            }
           
       };
    }
    public String toString()
    {
        if(length==0)
        {
            return "[]";
        }
        else
        {
            StringBuilder sb = new StringBuilder(length).append("[");
            for(int i=0;i<length-1;i++)
            {
                sb.append(array[i]+", ");
            }
            return sb.append(array[length-1]+"]").toString();
        }
    }
}