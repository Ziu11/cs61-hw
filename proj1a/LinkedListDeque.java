public class LinkedListDeque<T> {

    private class TNode {
        private T item;
        private TNode next;
        private TNode prev;

        private TNode(T x,TNode p,TNode n){
            item = x;
            prev = p;
            next = n;
        }
    }

    private TNode sentinel;
    private int size;

    /**
     * create an empty deque
     */

    public LinkedListDeque() {
        sentinel = new TNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /**
     * return the number of items in the deque
     */

    public int size() {
        return size;
    }

    /**
     * return true if deque is empty, false otherwise
     */

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Adds an item of type T to the front of the deque
     */

    public void addFirst(T item) {
        sentinel.next = new TNode(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size++;
    }

    /**
     * Adds an item of type T to the back of the deque
     */

    public void addLast(T item) {
        sentinel.prev = new TNode(item, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size++;
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null
     */

    public T removeFirst() {
        T toRemove = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        if (!isEmpty()) {
            size--;
        }
        return toRemove;
    }

    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, returns null
     */

    public T removeLast() {
        T toRemove = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        if (!isEmpty()) {
            size--;
        }
        return toRemove;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space. Once all the
     * items have been printed, print out a new line
     */

    public void printDeque() {
        TNode toPrint = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.print(toPrint.item + " ");
            toPrint = toPrint.next;
        }
        System.out.println();
    }

    /**
     * Gets the item at the given index, where 0 is the front,
     * 1 is the next item, and so forth. If no such item exists,
     * returns null. Must not alter the deque!
     */

    public T get(int index) {
        TNode toGet = sentinel.next;
        for (int i = 0; i < size; i++) {
            toGet = toGet.next;
        }
        return toGet.item;
    }

    /**
     * Same as get, but uses recursion
     */

    private T getRecursive(int index, TNode curr) {
        if (index == 0) {
            return curr.item;
        }
        return getRecursive(index - 1, curr.next);
    }

    public T getRecursive(int index) {
        return getRecursive(index, sentinel.next);
    }

    /**
     * Creates a deep copy of other
     */

    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new TNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;

        for (int i = 0; i < other.size(); i++) {
            addLast((T) other.get(i));
        }
    }
}
