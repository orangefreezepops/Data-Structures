package kdf11_proj1;
// YOUR NAME (your username)
// Also include any notes to the grader here, if anything is not working, or

import java.util.Arrays;

// commented out, etc.
public class MovieShelf implements MovieShelfInterface {

    private Set<Movie> shelf;
//    private int shelfItems = 0;
    private Movie movie;
    //private static final int DEFAULT_CAPACITY = 0;

    public MovieShelf() {
        this.shelf = new Set<>();
    }

    public MovieShelf(Movie[] contents) {
        shelf = new Set<>(contents);
    }

    @Override
    public boolean addItem(Movie item) {
        boolean result = shelf.add(item);
        return result;
    }

    @Override
    public boolean removeItem(Movie item) {
        if (shelf.remove(item) != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int watchMovie(Movie item) {
        if (shelf.contains(item)) {
            item.watch();
            return item.getWatchCount();
        }
        return -1;
    }

    @Override
    public void printAll() {
        Object[] newArray = shelf.toArray();
        Movie[] movieArray = Arrays.copyOf(newArray, newArray.length, Movie[].class);
        for (int i = 0; i < movieArray.length; i++) {

            System.out.println(movieArray[i].getTitle() + " (" + movieArray[i].getYear() + ")          "
                    + "(Rating: " + movieArray[i].getRating() + "/5; " + "watched "
                    + movieArray[i].getWatchCount() + " times)");
        }
    }

//    public boolean contains(Movie entry) throws NullPointerException {
//        boolean result;
//        if (entry == null) {
//            throw new NullPointerException("Null Pointer. There is nothing to check");
//        }
//        result = getIndexOf(entry) != -1;
//        return result;
//    }
//
//    private int getIndexOf(Movie entry) {
//        for (int i = 0; i < shelfItems; i++) {
//            if (entry.equals(shelf[i])) {
//                return i;
//            }
//        }
//        return -1;
//    }
    @Override
    public boolean borrowMovie(MovieShelfInterface other, Movie item) {
        boolean result = false;
        if (shelf.contains(item)) {
            System.out.println("The borrower already has " + item + "!");
        } else if ((other.removeItem(item) == true) && !(shelf.contains(item))) {
            shelf.add(item);
            return true;
        }
        return result;
    }
}
