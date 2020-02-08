import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class HashTableSol {

    public class Pair {
        public int key;
        public Product value;
        Pair next;

        public Pair(int key, Product value) {
            this.key = key;
            this.value = value;
        }

        public String toString() {
            return value.toString() + " " +key;
        }


    }
    int capacity = 20;
    int size = 0;
    Pair[] table;
    private static final int[] primes = {11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271};

    public HashTableSol() {
        table = new Pair[capacity];
    }


    public static void main(String[] args) throws Exception {
        HashTableSol hash = new HashTableSol();
      //  hash.load("test.txt");
       // hash.print();
       // hash.getCollisions();
        hash.load("testBigger.txt");
        hash.print();
       // System.out.println(hash.size + " " + hash.capacity);
       // hash.getCollisions();
        hash.getRating();


    }

    public int hashCode(String name) {
        int index = Math.abs(name.hashCode())%capacity;
        return index;
    }

    public void load(String filename) throws Exception {
        Scanner in = new Scanner(new File(filename));
        String line = null;

        while (in.hasNextLine()) {
            line = in.nextLine();
            String[] arr = line.split(" ");
            String vendor = arr[0];
            int depRating = Integer.valueOf(arr[1]);
            double price = Double.valueOf(arr[2]);
            int stock = Integer.valueOf(arr[3]);

            Product product = new Product(vendor, depRating, price, stock);

            put(vendor,product);


        }
        in.close();

    }

    public void put(String vendor, Product value) {
        int ind = hashCode(vendor);
        int key = ind;
        Pair find = table[ind];
        Pair pair = new Pair(key, value);
        if (find == null) {
            table[ind] = pair;
        }
        else {
            while (find.next != null ) {
                find = find.next;
            }
            pair.next = null;
            find.next = pair;
        }

        size++;
        if ((1.0*size)/capacity >= 0.75)
        {
            resize();

        }
    }

    public void print() {
        Pair print;
        for (int i = 0; i < table.length; i++) {
            print = table[i];
            if (print != null) {
                System.out.println(print.toString());
                while (print.next != null) {
                    System.out.println(print.next.toString());
                    print = print.next;
                }
            }
        }
    }

    public void getCollisions() {
        Pair collision;
        for(int i = 0; i < table.length; i++) {
            collision = table[i];
            if (collision != null) {
                while(collision.next != null) {
                    int col = collision.key;
                    System.out.print("Collision at index "+ col + ": " + collision.value.getVendor());
                    System.out.println(", " + collision.next.value.getVendor());
                    collision = collision.next;
                }
            }
        }
    }

    public Pair[] resize() {
        Pair[] temp = table;
        int prime = 0;
        for (int i = 0; i < primes.length; i++) {
            if (primes[i]>(size)) {
                prime = primes[i];
                break;
            }
        }
        int mult = 2 * prime;
        capacity = mult * capacity;
        table = new Pair[capacity];
        size = 0;
        for (int i = 0; i < capacity/mult; i++) {
            Pair current = temp[i];
            if(current != null) {
                put(current.value.getVendor(), current.value);
                while(current.next != null) {
                    current = current.next;
                    put(current.value.getVendor(),current.value);
                }
            }
        }
        return table;
    }


    public void getRating() throws Exception {
        File ratings = new File("ratings.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(ratings));
        Pair rate;
        for (int i = 0; i < table.length; i++) {
            rate = table[i];
            if (rate != null) {
               // System.out.println(rate.value.getDepRating());
                writer.write(rate.value.getVendor() + " " + rate.value.depRating);
                writer.write("\n");

                while (rate.next != null) {
                   // System.out.println(rate.next.value.getDepRating());
                    writer.write(rate.next.value.getVendor() + " " + rate.next.value.depRating);
                    writer.write("\n");
                    rate = rate.next;
                }
            }
        }
        writer.close();
    }



}
