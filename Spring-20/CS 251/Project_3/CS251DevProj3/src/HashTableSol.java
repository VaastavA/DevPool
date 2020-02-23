import java.io.File;
import java.util.Scanner;

public class HashTableSol {

    public class Pair {
        public String key;
        public Product value;
        Pair next;

        public Pair(String key, Product value) {
            this.key = key;
            this.value = value;
        }

        public String toString() {
            return key+ ": $" + value.getPrice() + " Sold By: "+ value.getVendor();
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
    }

    public int hashCode(String name) {
        int index = Math.abs(name.hashCode())%capacity;
        return index;
    }
    
    public int getCapacity() {
        return capacity;
    }

    public void load(String filename) throws Exception {
        Scanner in = new Scanner(new File(filename));
        String line = null;
        in.nextLine(); //This will read the first line, which is Name, Vendor, Price, Department Rate
        
        while (in.hasNextLine()) {
            line = in.nextLine();
            String[] arr = line.split("; ");
            String name = arr[0];
            String vendor = arr[1];
            double price = Double.valueOf(arr[2].split("\\$")[1]); 
            String[] depRateString = arr[3].split("\\[")[1].split("\\]")[0].split(", ");
            int[] depRating = new int[depRateString.length];
            
            for (int i = 0; i<depRating.length; i++) {
                depRating[i] = Integer.parseInt(depRateString[i]);
            }
        
            Product product = new Product(name, vendor, depRating, price);

            put(name,product);


        }
        in.close();

    }

    public void put(String name, Product value) {
        int ind = hashCode(name);
        String key = name;
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
                if(collision.next != null) {
                    int col = hashCode(collision.key);
                    System.out.print("Collision at index " + col + ": " + collision.key);

                    while (collision.next != null) {

                        System.out.print(", " + collision.next.key);
                        collision = collision.next;
                    }
                    System.out.println();
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
                put(current.key, current.value);
                while(current.next != null) {
                    current = current.next;
                    put(current.key,current.value);
                }
            }
        }
        return table;
    }


    public Pair find(String key, int ind) {
        Pair pair = table[ind];
        while (pair != null && !(pair.key.equals(key)))
            pair = pair.next;
        return pair;
    }

    public Pair get(String key) {
        int ind = hashCode(key);
        Pair pair = find(key,ind);
        if (pair == null) {
            return null;
        }
        else {
            return pair;
        }
    }



    public boolean remove(String key) {
        Pair head = get(key);
        Pair prev = null;
        if (head == null) {
            return false;
        }
        else {
            int ind = hashCode(key);

            Pair find = table[ind];
            if (find.key.equals(key)) {
                if (find.next == null) {
                    Pair pair = null;
                    table[ind] = pair;
                }
                else {
                    while(find != null) {
                        if(find.key.equals(key)){
                            if (prev != null){
                                prev.next = find.next;
                            }else{
                                table[ind] = find.next;
                            }

                        }

                        prev = find;
                        find = find.next;
                    }


                }
            }
            else {
                while(find != null) {
                    if(find.key.equals(key)){
                        if (prev != null){
                            prev.next = find.next;
                        }else{
                            table[ind] = find.next;
                        }

                    }

                    prev = find;
                    find = find.next;
                }

            }


            size--;
            return true;
        }


    }



}