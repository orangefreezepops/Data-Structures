package kdf11_proj1;
/**
 * Set is an interface that describes the operations of the ADT set. A set is a
 * homogeneous collection of objects. It is unordered, there are no limits on
 * the number of items it can store, and it cannot contain duplicate items.
 */
public interface SetInterface<E> {

    /**
     * Determines the current number of entries in this set.
     *
     * @return  The integer number of entries currently in this set
     */
    public int getSize();

    /**
     * Determines whether this set is empty.
     *
     * @return  true if this set is empty; false if not
     */
    public boolean isEmpty();

    /**
     * Adds a new entry to this set, avoiding duplicates.
     *
     * <p> If newEntry is not null, this set does not contain newEntry, and this
     * set has available capacity (if applicable), then add modifies the set so
     * that it contains newEntry. All other entries remain unmodified.
     * Duplicates are determined using the .equals() method.
     *
     * <p> If newEntry is null, then add throws NullPointerException without
     * modifying the set. If this set already contains newEntry, then add
     * returns false without modifying the set. If this set has a capacity
     * limit, and does not have available capacity, then add throws
     * SetFullException without modifying the set. If this set does not have a
     * capacity limit (i.e., if it resizes as needed), then it will never throw
     * SetFullException.
     *
     * @param newEntry  The object to be added as a new entry
     * @return  true if the addition is successful; false if the item already is
     * in this set
     * @throws SetFullException  If this set has a fixed capacity and does not
     * have the capacity to store an additional entry
     * @throws NullPointerException  If newEntry is null
     */
    public boolean add(E newEntry) throws SetFullException,
                                          NullPointerException;

    /**
     * Removes a specific entry from this set, if possible.
     *
     * <p> If this set contains the entry, remove modifies the set so that it no
     * longer contains entry. All other entries remain unmodified. Identifying
     * this entry is accomplished using the .equals() method. The removed entry
     * will be returned.
     *
     * <p> If this set does not contain entry, remove will return null without
     * modifying the set. Because null cannot be added, a return value of null
     * will never indicate a successful removal.
     *
     * <p> If the specified entry is null, then remove throws
     * NullPointerException without modifying the set.
     *
     * @param entry  The entry to be removed
     * @return  The removed entry if removal was successful; null otherwise
     * @throws NullPointerException  If entry is null
     */
    public E remove(E entry) throws NullPointerException;

    /**
     * Removes an arbitrary entry from this set, if possible.
     *
     * <p> If this set contains at least one entry, remove will modify the set
     * so that it no longer contains one of its entries. All other entries
     * remain unmodified. The removed entry will be returned.
     *
     * <p> If this set is empty, remove will return null without modifying the
     * set. Because null cannot be added, a return value of null will never
     * indicate a successful removal.
     *
     * @return  The removed entry if the removal was successful; null otherwise
     */
    public E remove();

    /**
     * Removes all entries from this set.
     *
     * <p> If this set is already empty, clear will not modify the set.
     * Otherwise, the set will be modified so that it contains no entries.
     */
    public void clear();

    /**
     * Tests whether this set contains a given entry. Equality is determined
     * using the .equals() method.
     *
     * <p> If this set contains entry, then contains returns true. Otherwise
     * (including if this set is empty), contains returns false. If entry is
     * null, then remove throws NullPointerException. The method never modifies
     * this set.
     *
     * @param entry  The entry to locate
     * @return  true if this set contains entry; false if not
     * @throws NullPointerException  If entry is null
     */
    public boolean contains(E entry) throws NullPointerException;

    /**
     * Retrieves all entries that are in this set.
     *
     * <p> An array is returned that contains a reference to each of the entries
     * in this set. The returned array's length will be equal to the number of
     * elements in this set, and thus the array will contain no null values.
     *
     * <p> If the implementation of set is array-backed, toArray will not return
     * the private backing array. Instead, a new array will be allocated with
     * exactly the appropriate capacity (including an array of size 0, if the
     * set is empty).
     *
     * @return  A newly-allocated array of all the entries in this set
     */
    public Object[] toArray();

}

