public class HashTable {


    int capacity = 20;
    int size = 0;
    private static final int[] primes = {11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271};

    /**
     * Initialize an array of length capacity,
     */
    public HashTable() {
        //TODO: HashTable Constructor
    }

    /**
     * Use to test for yourself
     */
    public static void main(String[] args) throws Exception {
        HashTable hash = new HashTable();
        String filename = "";
        hash.load(filename);
        hash.print();

    }


    /**
     * Use this function to generate the HashCode. DO NOT CHANGE!
     *
     * @return hashCode of key
     */

    public int hashCode(String name) {
        int index = Math.abs(name.hashCode())%capacity;
        return index;
    }


    /**
     * Load contents of filename into your hashtable
     */
    public void load(String filename) throws Exception {
       //TODO: Load Function

    }

    /**
     * Adds the (key, value) pair to the table.
     * key = index, value = Product value
     * Use Seperate Chaining in the case of Collisions.
     * Resize the hashtable if the load factor becomes greater than 0.75 after inserting the (key, value) pair.
     */
    public void put(String vendor, Product value) {
        //TODO: Put Function
    }


    /**
     * Prints all values in the hashtable if they are not null.
     */
    public void print() {
        //TODO: Print Function
    }


    /**
     * Print Collisions in HashTable
     */
    public void getCollisions() {
        //TODO: getCollisions Function
    }

    /**
     * Resize the hashtable if the load factor becomes greater than 0.75 after inserting this (key, value) pair.
     * We hard code a list of prime number for you to use. You can assume you will never run out of prime number to use.
     * Return the resized HashTable.
     */
    public Object resize() {
        //TODO: Resize Function


        /* Change to Return HashTable */
        return null;
    }

    
    //TODO: get department ratings from hashtable for part 2
}