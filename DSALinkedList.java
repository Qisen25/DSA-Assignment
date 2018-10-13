import java.util.*;
public class DSALinkedList<E> implements Iterable
{
    public Iterator<E> iterator()
    {
        return new DSALinkedListIterator<E>(this);
    }

    //private classes
    private class DSALinkedListIterator<E> implements Iterator<E>
    {
        private DSALinkedList<E>.DSAListNode<E> iterNext;

        public DSALinkedListIterator(DSALinkedList<E> theList)
        {
            iterNext = theList.head;
        }

        public boolean hasNext()
        {
            return (iterNext != null);
        }

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

        public void remove()
        {
            throw new UnsupportedOperationException("Remove not supported");
        }
    }

    private class DSAListNode<E>
    {
        //DSAListNode CLASSFIELDS
        private E value;
        private DSAListNode<E> next;
        private DSAListNode<E> prev;

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

    public DSALinkedList()
    {
        head = null;
        tail = null;
    }

    //MUTATORS
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
    public boolean isEmpty()
    {
        boolean empty;

        empty = (this.head == null);

        return empty;
    }

    public int getCount()
    {
        return this.count;
    }

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
