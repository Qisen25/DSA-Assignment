/**
 *  FILE: DSAStack.java <br>
 *  PURPOSE: Implementation of stack <br>
 *  REFERENCE: From DSA prac 3 and prac 4
 *
 *  @author Kei Sum Wang - 19126089
 */
public class DSAStack<E>
{
    private DSALinkedList<E> list;
    private int count;

    /**
     * Default constructor for stack 
     */
    public DSAStack()
    {
        list = new DSALinkedList<E>();
        count = 0;
    }

    /**
     * PURPOSE: gets number of items in the stack.
     *
     * @return number of items
     */
    public int getCount()
    {
        return this.count;
    }

    /**
     * PURPOSE: check if stack is empty.
     *
     * @return boolean for whether stack is empty
     */
    public boolean isEmpty()
    {
        return list.isEmpty();
    }

    /**
     * PURPOSE: insert item at the top of stack
     *
     * @param any Object
     */
    public void push(E val)
    {
        list.insertFirst(val);
        count++;
    }

    /**
     * PURPOSE: pop item from top of stack
     *
     * @return Object that is popped from stack
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

    /**
     * PURPOSE: check what item is on top of stack
     *
     * @return Object that is on top of stack
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
