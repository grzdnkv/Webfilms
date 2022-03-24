import java.util.*;

public class Star {
    private ArrayList<String> items = new ArrayList<String>();

    public Star() {
    }

    public synchronized void add(String filmname) {
        items.add(filmname);
    }

    public synchronized String[] getFilms() {
        String[] array = new String [items.size()];
        array = items.toArray(array);
        return array;
    }

    public synchronized void reset() {
        items.clear();
    }

    public synchronized void remove() {

    }
}