import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Recommmender{

    /********************************
     * Do not change below code
     ********************************/

    int swaps, compares;
    int[] inversionCounts;
    List<String> products;

    public Recommmender(){
        swaps = 0;
        compares = 0;
    }
    
    public int getComapares() {
        return compares;
    }
    
    public int getswaps() {
        return swaps;
    }

    private boolean compare(int a ,int b){
        compares++;
        return a < b;

    }

    private void swap(int[] arr, int index1, int index2){
        swaps++;
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;

        Collections.swap(products, index1, index2);
    }

    /********************************
     * Do not change above code
     ********************************/

    /**
     * This function is for the calculate inversion counts of each option's.
     * @param dataset is file name of all data for hash table
     * @param options is the list of product name which we want to getting the inversion counts
     * @return it is integer array of each option's inversion counts. The order of return should be matched with options.
     */
    public int[] inversionCounts(String dataset, String[] options) {
    	// TODO
        
        return null;    	
    }
    
    /**
     * Get the sequence of recommendation from the dataset by sorting the inverse count.
     * Compare the similarity of depRating between RecentPurchase's and each option's.  
     * Use inverse count to get the similarity of two array. 
     * */
    public int[] recommend(String dataset, String recentPurchase, String[] options){
    	// Don't change products values. 
        products = options;
    	// TODO
    	
        return null;
    

}