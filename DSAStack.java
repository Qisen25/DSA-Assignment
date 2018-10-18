/***************************************************************************
 *  FILE: DSAStack.java
 *  AUTHOR: Kei Sum Wang - 19126089
 *  PURPOSE: Implementation of stack
 ***************************************************************************/
public class DSAStack<E>
{
    private DSALinkedList<E> list;
    private int count;

    //Default constructor
    public DSAStack()
    {
        list = new DSALinkedList<E>();
        count = 0;
    }

    //ACCESSOR for getting count
    public int getCount()
    {
        return this.count;
    }

    /*
     * Method: isEmpty()
     * PURPOSE: check if stack is empty
     */
    public boolean isEmpty()
    {
        return list.isEmpty();
    }

    /*
     * Method: push()
     * PURPOSE: push item into stack
     */
    public void push(E val)
    {
        list.insertFirst(val);
        count++;
    }

    /*
     * Method: pop()
     * PURPOSE: pop item from top of stack
     */
    public E pop()
    {
        E top;
        if(isEmpty())
        {
            throw new IllegalArgumentException("Stack empty: nothing to pop");
        }
        else
        {
            top = list.removeFirst();
            count--;
        }

        return top;
    }

    /*
     * Method: top()
     * PURPOSE: check what item is on top of stack
     */
    public E top()
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
