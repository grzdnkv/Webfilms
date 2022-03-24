import java.util.*;
import java.io.*;

public class Database {
    private ArrayList<String> items = new ArrayList<String>();
    private ArrayList<String> prices = new ArrayList<String>();
    private ArrayList<String> descriptions = new ArrayList<String>();
    private ArrayList<String> purchases = new ArrayList<String>();
    private ArrayList<String> left = new ArrayList<String>();
    private ArrayList<String> sellers = new ArrayList<String>();
    private ArrayList<String> users = new ArrayList<String>();
    private ArrayList<String> cart = new ArrayList<String>();
    private ArrayList<String> star = new ArrayList<String>();

    public Database() {
    }
    public synchronized void init(String username) {
        try {
            File userdb = new File("../webapps/webfilms/userdb.txt"); //for tomcat
            if (userdb.exists()) {
                FileWriter fw = new FileWriter(userdb, true); // true for appending
                PrintWriter pw = new PrintWriter(fw, true); // true for auto-flush
                pw.println("user:" + username + "\n");
                pw.println("    cart:\n");
                pw.println("    cartend");
                pw.println("    star:\n ");
                pw.println("    starend");
                pw.println("usernameend");
                pw.close();
            }
            File filmsdb = new File("../webapps/webfilms/filmsdb.txt");
            if (filmsdb.exists()) {
                FileWriter fw = new FileWriter(filmsdb, true); // true for appending
                PrintWriter pw = new PrintWriter(fw, true); // true for auto-flush
                pw.println("user:\n");
                pw.println("    films:");
                pw.println("    FILMNAME");
                pw.println("    PRICE");
                pw.println("    filmsend");
                pw.println("usernameend");
                pw.close();
            }
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public synchronized String[] getFilms() {
        try {
        File file = new File("../webapps/webfilms/filmsdb.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = new String();
        while ((line = br.readLine()) != null) {
            if (line.contains("films:")){
                while (!(line = br.readLine()).contains("filmsend")){
                    if (!isAlreadyIn(line, items)){
                        items.add(line.trim());
                        line = br.readLine();
                        prices.add(line.trim());
                        line = br.readLine();
                        descriptions.add(line.trim());
                        line = br.readLine();
                        purchases.add(line.trim());
                        line = br.readLine();
                        left.add(line.trim());
                        line = br.readLine();
                        sellers.add(line.trim());
                    }
                }
            }
        }
        br.close();
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
        String[] array = new String [items.size()];
        array = items.toArray(array);
        return array;
    }

    public synchronized String[] getCart(String username){
        try {
        File file = new File("../webapps/webfilms/userdb-" + username + ".txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = new String();
        while (!(line = br.readLine()).contains("star:")) {
            if (line.contains("cart:")){
                while (!(line = br.readLine()).contains("cartend")){
                    this.cart.add(line.trim());
                }
            }
        }
        br.close();
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
        String[] array = new String [cart.size()];
        array = cart.toArray(array);
        return array;
    }

    public synchronized String[] getStar(String username){
        try {
        File file = new File("../webapps/webfilms/userdb-" + username + ".txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = new String();
        while (!(line = br.readLine()).contains("usernameend")) {
            if (line.contains("star:")){
                while (!(line = br.readLine()).contains("starend")){
                    this.star.add(line.trim());
                }
            }
        }
        br.close();
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
        String[] array = new String [star.size()];
        array = star.toArray(array);
        return array;
    }
    public synchronized String[] getPrices() {
        String[] array = new String[prices.size()];
        array = prices.toArray(array);
        return array;
    }
    public synchronized String[] getDescriptions() {
        String[] array = new String[descriptions.size()];
        array = descriptions.toArray(array);
        return array;
    }
    public synchronized String[] getPurchases() {
        String[] array = new String[purchases.size()];
        array = purchases.toArray(array);
        return array;
    }
    public synchronized String[] getLeft() {
        String[] array = new String[left.size()];
        array = left.toArray(array);
        return array;
    }
    public synchronized String[] getSellers() {
        String[] array = new String[sellers.size()];
        array = sellers.toArray(array);
        return array;
    }

    public synchronized boolean isAlreadyIn(String el, ArrayList<String> list){
        for (int i = 0; i != list.size(); i++){
            if (list.get(i) == el){
                return true;
            }
        }
        return false;
    }

    public synchronized int foundByName(String name){
        return items.indexOf(name);
    }

    public synchronized void filmsUpdate(String[] items, String[] prices, String[] descriptions, String[] purchases, String[] left, String[] sellers){
        this.items = new ArrayList<>(Arrays.asList(items));
        this.prices = new ArrayList<>(Arrays.asList(prices));
        this.descriptions = new ArrayList<>(Arrays.asList(descriptions));
        this.purchases = new ArrayList<>(Arrays.asList(purchases));
        this.left = new ArrayList<>(Arrays.asList(left));
        this.sellers = new ArrayList<>(Arrays.asList(sellers));
        try {
            File filmsdb = new File("../webapps/webfilms/filmsdb.txt");
            filmsdb.delete();
            if (!filmsdb.exists()) {
                FileWriter fw = new FileWriter(filmsdb, true); // true for appending
                PrintWriter pw = new PrintWriter(fw, true); // true for auto-flush
                pw.println("user:\n");
                pw.println("    films:");
                for (int i = 0; i < this.items.size(); i++){
                    pw.println("    " + this.items.get(i));
                    pw.println("    " + this.prices.get(i));
                    pw.println("    " + this.descriptions.get(i));
                    pw.println("    " + this.purchases.get(i));
                    pw.println("    " + this.left.get(i));
                    pw.println("    " + this.sellers.get(i));
                }
                pw.println("    filmsend");
                pw.println("usernameend");
                pw.close();
            }
        }catch (IOException e) {
                System.out.println(e.getMessage());
        }
  
    }

    public synchronized void userInfoUpdate(String username, String[] cart, String[] star){
            this.cart = new ArrayList<>(Arrays.asList(cart));
            this.star = new ArrayList<>(Arrays.asList(star));
        try{
            File userdb = new File("../webapps/webfilms/userdb-" + username + ".txt");
            userdb.delete();
            if (!userdb.exists()) {
                FileWriter fw = new FileWriter(userdb, true); // true for appending
                PrintWriter pw = new PrintWriter(fw, true); // true for auto-flush
                pw.println("user:");
                pw.println(username);
                pw.println("    cart:");
                for(int i = 0; i < this.cart.size(); i++){
                    pw.println("    " + this.cart.get(i));
                }
                pw.println("    cartend");
                pw.println("    star:");
                for (int i = 0; i < this.star.size(); i++){
                    pw.println("    " + this.star.get(i));
                }
                pw.println("    starend");
                pw.println("usernameend");
                pw.close();
            }
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public synchronized void resetStar(String name) {
        try{
        File userdb = new File("../webapps/webfilms/userdb-" + name + ".txt");
            userdb.delete();
            if (!userdb.exists()) {
                FileWriter fw = new FileWriter(userdb, true); // true for appending
                PrintWriter pw = new PrintWriter(fw, true); // true for auto-flush
                pw.println("user:");
                pw.println(name);
                pw.println("    cart:");
                for(int i = 0; i < cart.size(); i++){
                    pw.println("    " + cart.get(i));
                }
                pw.println("    cartend");
                pw.println("    star:");
                pw.println("    starend");
                pw.println("usernameend");
                pw.close();
            }
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public synchronized void resetCart(String name) {
        try{
        File userdb = new File("../webapps/webfilms/userdb-" + name + ".txt");
            userdb.delete();
            if (!userdb.exists()) {
                FileWriter fw = new FileWriter(userdb, true); // true for appending
                PrintWriter pw = new PrintWriter(fw, true); // true for auto-flush
                pw.println("user:");
                pw.println(name);
                pw.println("    cart:");
                pw.println("    cartend");
                pw.println("    star:");
                for (int i = 0; i < star.size(); i++){
                    pw.println("    " + star.get(i));
                }
                pw.println("    starend");
                pw.println("usernameend");
                pw.close();
            }
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}