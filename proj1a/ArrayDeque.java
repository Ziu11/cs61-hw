public class ArrayDeque<T> {
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;

    /** Creates an empty array deque */

    public ArrayDeque(){
        items = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }

    /** return true if Deque if full, false otherwise*/

    private boolean isFull(){
        return size == items.length;
    }

    /** whether to downsise the Deque*/

    private boolean isSparse(){
        return items.length >= 16 && size < (items.length / 4);
    }

    /** add one circularly*/

    private int plusOne(int index){
        return (index + 1) % items.length;
    }

    /** minus one circularly*/

    private int minusOne(int index){
        return (index - 1 + items.length) % items.length;
    }

    /** resize the Deque*/

    private void resize(int capacity){
        T[] newDeque = (T[]) new Object[capacity];
        int oldIndex = plusOne(nextFirst);
        for (int newIndex = 0; newIndex < size; newIndex++){
            newDeque[newIndex] = items[oldIndex];
            oldIndex = plusOne(oldIndex);
        }
        items = newDeque;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    /** upsize the Deque*/

    private void upSize(){
        resize(size * 2);
    }

    /** downsize the Deque*/

    private void downSize(){
        resize(items.length / 2);
    }

    /** return true if Deque if empty, false otherwise*/

    public boolean isEmpty(){
        return size == 0;
    }

    /** return the number of items in the Deque*/

    public int size(){
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space. Once all the
     * items have been printed, print out a new line
     */

    public void printDeque(){
        for (int i = plusOne(nextFirst); i != nextLast; i = plusOne(i)){
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    /**
     * Adds an item of type T to the front of the deque
     */

    public void addFirst(T x){
        if (isFull()) upSize();
        items[nextFirst] = x;
        nextFirst = minusOne(nextFirst);
        size++;
    }

    /**
     * Adds an item of type T to the back of the deque
     */

    public void addLast(T x){
        if (isFull()) upSize();
        items[nextLast] = x;
        nextLast = plusOne(nextLast);
        size++;
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null
     */

    public T reMoveFirst(){
        if (isSparse()) downSize();
        nextFirst = plusOne(nextFirst);
        T toRemove = items[nextFirst];
        items[nextFirst] = null;
        if (!isEmpty()) size--;
        return toRemove;
    }

    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, returns null
     */

    public T reMoveLast(){
        if (isSparse()) downSize();
        nextLast = minusOne(nextLast);
        T toRemove = items[nextLast];
        items[nextLast] = null;
        if (!isEmpty()) size--;
        return toRemove;
    }

    /**
     * Gets the item at the given index, where 0 is the front,
     * 1 is the next item, and so forth. If no such item exists,
     * returns null. Must not alter the deque!
     */

    public T get(int index){
        if (index >= size) return null;
        int start = plusOne(nextFirst);
        return items[(start + index) % items.length];
    }

    /** create a deep other*/

    public ArrayDeque(ArrayDeque other){
        items = (T[]) new Object[other.size];
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        size = other.size;

        System.arraycopy(other.items, 0, items, 0, other.size);
    }
}
