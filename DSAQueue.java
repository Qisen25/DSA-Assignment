/***************************************************************************
 *  FILE: DSAQueue.java
 *  AUTHOR: Kei Sum Wang - 19126089
 *  PURPOSE: Implementation of queue
 ***************************************************************************/
import java.util.*; 
import java.io.Serializable;
public class DSAQueue<E> implements Serializable, Iterable
{
    private DSALinkedList<E> list;
    private int count;

    public Iterator<E> iterator()
    {
        return list.iterator();
    }

    //Default constructor
    public DSAQueue()
    {
        list = new DSALinkedList<E>();
        count = 0;
    }
    
    //ACCESSOR for getting count
    public int getCount()
    {
        return this.count;
    }

    //method to check if Queue is empty
    public boolean isEmpty()
    {
        return list.isEmpty();
    }

    //method to enqueue item
    public void enqueue(E val)
    {
        list.insertLast(val);
        count++;
    }

    //method to dequeue item from queue
    public E dequeue()
    {
        E top;

        if(isEmpty())
        {
            throw new IllegalArgumentException("Queue empty: cannot dequeue");
        }
        else
        {
            top = list.removeFirst();

            count--;
        }

        return top;
    }

    //method to check what is at front of queue
    public E peek()
    {
        E val;

        if(isEmpty())
        {
            throw new IllegalArgumentException("Stack is empty");
        }
        else
        {
            val = list.peekFirst();
        }

        return val;
    }
}
