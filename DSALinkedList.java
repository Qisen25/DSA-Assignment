import java.util.*;
/**
 *  FILE: DSALinkedList.java <br>
 *  PURPOSE: Implementation of Linked List <br>
 *  REFERENCE: From DSA prac 4
 *
 *  @author Kei Sum Wang - 19126089
 */
public class DSALinkedList<E> implements Iterable
{
    /**
     * @return Iterator for the list
     */
    public Iterator<E> iterator()
    {
        return new DSALinkedListIterator<E>(this);
    }

    /**
     * class for linked list iterator
     */
    private class DSALinkedListIterator<E> implements Iterator<E>
    {
        private DSALinkedList<E>.DSAListNode<E> iterNext;

        /**
         * constructor linked list iterator
         * @param takes in a linked list
         */
        public DSALinkedListIterator(DSALinkedList<E> theList)
        {
            iterNext = theList.head;
        }

        /**
         * method to check if node has next node
         * @return boolean to check if node has next
         */
        public boolean hasNext()
        {
            return (iterNext != null);
        }

        /**
         * method to get the next node
         * @return value at current node
         */
        public E next()
        {
            E value;

            if (iterNext == null)
            {
                value = null;
            }
            else
            {
                value = iterNext.value;
                iterNext = iterNext.next;
            }

            return value;
        }

        /**
         * method to remove, however unsupported at the moment
         */
        public void remove()
        {
            throw new UnsupportedOperationException("Remove not supported");
        }
    }

    /**
     * class for linked list nodes
     */
    private class DSAListNode<E>
    {
        //DSAListNode CLASSFIELDS
        private E value;
        private DSAListNode<E> next;
        private DSAListNode<E> prev;

        /**
         * Constructor for creating node
         * @param any Object
         */
        public DSAListNode(E inValue)
        {
            value = inValue;

            next = null;
            prev = null;
        }
    }

    //DSALinkedList CLASSFIELDS
    private DSAListNode<E> head;
    private DSAListNode<E> tail;
    private int count;

    /**
     * DEFAULT Constructor for linked list
     */
    public DSALinkedList()
    {
        head = null;
        tail = null;
    }

    /**
     * method to insert an item at front of list
     * @param any Object
     */
    public void insertFirst(E newValue)
    {
        DSAListNode<E> newNd = new DSAListNode<E>(newValue);
        if (isEmpty())
        {
            this.tail = newNd;
        }
        else
        {
            this.head.prev = newNd;
            newNd.next = this.head;
        }

        this.head = newNd;
        this.count++;
    }

    /**
     * method to insert at end of list
     * @param any Object
     */
    public void insertLast(E newValue)
    {
        DSAListNode<E> newNd = new DSAListNode<E>(newValue);
        if (isEmpty())
        {
            this.head = newNd;
        }
        else
        {
            this.tail.next = newNd;
            newNd.prev = this.tail;
        }

        this.tail = newNd;
        this.count++;
    }

    /**
     * method to remove item at front of list
     * @return item that has been remove
     */
    public E removeFirst()
    {
        E nodeValue;
        DSAListNode<E> currNd = null;

        if (isEmpty())
        {
            throw new IllegalArgumentException("list is empty");
        }

        nodeValue = this.head.value;
        if (this.tail.prev == null)
        {
            this.tail = null;
        }
        else
        {
            currNd = this.head.next;//current node will be node after head
            currNd.prev = null;//currNd previous will point to null (delete head)
        }

        this.head = currNd;
        this.count--;
       

        return nodeValue;
    }

    /**
     * method to remove item at end of list
     * @return item that has been remove
     */
    public E removeLast()
    {
        E nodeValue;
        DSAListNode<E> currNd = null;
            
        if (isEmpty())
        {
            throw new IllegalArgumentException("list is empty");
        }

        nodeValue = this.tail.value;
        if (this.head.next == null)
        {
            this.head = null;
        }
        else
        {
            currNd = this.tail.prev;//tail previous node will current node
            currNd.next = null;// current node next will be null (delete tail)

        }
        this.tail = currNd;//make sure currNd is the tail
        this.count--;

        return nodeValue;
    }

    //ACCESORS
    /**
     * method to check if list is empty
     * @return boolean that tells whether list is empty or not
     */
    public boolean isEmpty()
    {
        boolean empty;

        empty = (this.head == null);

        return empty;
    }

    /**
     * method to get number of items in list
     * @return number of items
     */
    public int getCount()
    {
        return this.count;
    }

    /**
     * method to get item at front of list
     * @return item at front of list
     */
    public E peekFirst()
    {
        E nodeValue;
        if (isEmpty())
        {
            throw new IllegalArgumentException("list is empty");
        }
        else
        {
            nodeValue = this.head.value;
        }

        return nodeValue;
    }

    /**
     * method to get item at end of list
     * @return item at end of list
     */
    public E peekLast()
    {
        E nodeValue;
        DSAListNode<E> currNd;
        if (isEmpty())
        {
            throw new IllegalArgumentException("list is empty");
        }
        else
        {
            nodeValue = this.tail.value;
        }

        return nodeValue;
    }

}
