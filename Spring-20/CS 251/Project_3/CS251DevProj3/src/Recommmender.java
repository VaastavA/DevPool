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
     * Get the sequence of recommendation from the dataset by sorting the inverse count.
     * Compare the similarity of depRating between RecentPurchase's and each option's.  
     * Use inverse count to get the similarity of two array. 
     * */
    public int[] recommend(String dataset ,String recentPurchase, List<String> options){

        return null;
    }

}