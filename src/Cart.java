import java.util.*;

public class Cart {
    private ArrayList<String> items = new ArrayList<String>();
    private ArrayList<String> prices = new ArrayList<String>();

    public Cart() {
    }

    public synchronized void add(String filmname, String price) {
        items.add(filmname);
        prices.add(price);
    }

    public synchronized String[] getFilms() {
        String[] array = new String [items.size()];
        array = items.toArray(array);
        return array;
    }

    public synchronized String[] getPrices() {
        String[] array = new String[prices.size()];
        array = prices.toArray(array);
        return array;
    }

    public synchronized void reset() {
        items.clear();
    }

    public synchronized void remove() {

    }
}
