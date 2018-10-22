import java.util.*; 
import java.io.Serializable;
/**
 *  FILE: DSAQueue.java <br>
 *  PURPOSE: Implementation of queue <br>
 *  REFERENCE: From DSA prac 3 and prac 4
 *
 *  @author Kei Sum Wang - 19126089
 */
public class DSAQueue<E> implements Serializable, Iterable
{
    private DSALinkedList<E> list;
    private int count;

    /**
     * @return list iterator
     */
    public Iterator<E> iterator()
    {
        return list.iterator();
    }

    /**
     * DEFAULT Constructor for queue.
     */
    public DSAQueue()
    {
        list = new DSALinkedList<E>();
        count = 0;
    }
    
    /**
     * get number of items that currently in queue.
     * @return number of items in queue
     */
    public int getCount()
    {
        return this.count;
    }

    /**
     * method checks if queue is empty.
     * @return boolean for whether queue is empty
     */
    public boolean isEmpty()
    {
        return list.isEmpty();
    }

    /**
     * method inserts item at the end of queue.
     * @param any Object
     */
    public void enqueue(E val)
    {
        list.insertLast(val);
        count++;
    }

    /**
     * method to remove item from front of the queue.
     * @return item dequeued from queue
     */
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

    /**
     * method to get item at front queue
     * @return Object at front of queue
     */
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
