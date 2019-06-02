package kdf11_proj1;
import java.util.*;
// Keegan Fouse (kdf11)
// Also include any notes to the grader here, if anything is not working, or
// commented out, etc.

public class Set<E> implements SetInterface<E> {
    // TODO: everything
    //moviesArray is the array. moviesArray.length is the capacity
    //arraySize is the number of items in the array
    //DEFAULT_CAPACITY is the... default capacity

    private E[] moviesArray;
    private int arraySize;
    private static final int DEFAULT_CAPACITY = 20;

    public Set(int capacity) throws IllegalArgumentException {
        arraySize = 0;
        if (capacity > DEFAULT_CAPACITY) {
            throw new IllegalArgumentException("too thicc");
        }
        moviesArray = (E[]) new Object[capacity];
    }

    public Set() {
        //initializing the set to a default capacity
        this(DEFAULT_CAPACITY);
    }

    public Set(E[] contents) {
        this(DEFAULT_CAPACITY);
        for (int i = 0; i < contents.length; i++) {
            if (contents[i] != null) {
                add(contents[i]);
            }
        }
    }

    @Override
    public int getSize() {
        return arraySize;
    }

    @Override
    public boolean isEmpty() {
        return arraySize == 0;
    }

    @Override
    public boolean add(E newEntry) throws NullPointerException {
        boolean result = false;
        if (newEntry == null) {
            throw new NullPointerException("There is nothing to add");
        }
        //checking to make sure that adding another item
        //wont send us over capacity
        if (arraySize + 1 > moviesArray.length) {
            doubleCapacity();
        }
        if (contains(newEntry)) {
            result = false;
        }
        //add the new entry to the last position of the set
        moviesArray[arraySize] = newEntry;
        arraySize++;
        result = true;
        return result;
    }

    private void doubleCapacity() {
        //instantiating a new variable to hold the new length of the array
        int capacityDoubled = moviesArray.length * 2;
        //copying the previous array into a new array with the doubled size
        moviesArray = Arrays.copyOf(moviesArray, capacityDoubled);
    }

    @Override
    public E remove(E entry) throws NullPointerException {
        E result = null;
        if (entry == null) {
            throw new NullPointerException("There was nothing to remove");
        }
        if (!(contains(entry))) {
            result = null;
        }
        for (int i = 0; i < arraySize; i++) {
            if (moviesArray[i].equals(entry)) {
                result = removeEntry(i);
            }
        }
        return result;
    }

    @Override
    public E remove() {
        //initializing to find arbitrary numbers
        Random rand = new Random();
        E result;
        //storing a random integer from [0, array size] 
        int randomNum = rand.nextInt(arraySize + 1);

        //if the array is empy return null
        if (arraySize == 0) {
            result = null;
            //otherwise remove an item at the generated random index from the array    
        } else {
            result = removeEntry(randomNum);
        }

        return result;
    }

    //does the actual removing from the moviesArray based on the given index
    //found from the Remove() method's itirative search
    private E removeEntry(int index) {
        E result = null;
        if (index > 0 && !this.isEmpty()) {
            result = moviesArray[index];
            int lastIndex = arraySize - 1;

            moviesArray[index] = moviesArray[lastIndex];
            moviesArray[lastIndex] = null;
            arraySize--;
        }
        return result;
    }

    @Override
    public void clear() {
        for (int i = 0; i < arraySize; i++) {
            moviesArray[i] = null;
        }
        arraySize = 0;
    }

    @Override
    public boolean contains(E entry) throws NullPointerException {
        boolean result;
        if (entry == null) {
            throw new NullPointerException("Null Pointer. There is nothing to check");
        }
        result = getIndexOf(entry) != -1;
        return result;
    }

    private int getIndexOf(E entry) {
        for (int i = 0; i < arraySize; i++) {
            if (entry.equals(moviesArray[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Object[] toArray() {
        Object[] newArray = (E[]) new Object[arraySize];
        //filling the objects from the moviesArray into this new array
        for (int i = 0; i < arraySize; i++) {
            newArray[i] = moviesArray[i];
        }
        return newArray;
    }
}
