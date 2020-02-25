import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import java.lang.Math;

public class RecommenderSol {

    /********************************
     * Do not change below code
     ********************************/

    int swaps, compares;
    int[] inversionCounts;
    String[] products;

    public RecommenderSol(){
        swaps = 0;
        compares = 0;
    }

    public int getSwaps() {
        return swaps;
    }

    public int getComapares() {
        return compares;
    }

    private boolean compare(int a ,int b){
        compares++;
        return a <= b;

    }

    private void swap(int[] arr, int index1, int index2){
        swaps++;
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;

        String tempS = products[index1];
        products[index1] = products[index2];
        products[index2] = tempS;
    }

    /********************************
     * Do not change above code
     ********************************/

    public int[] inversionCounts(String dataset, String[] options) {
        HashTable hash = new HashTable();

        try {
            hash.load(dataset);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int[] recProds = new int[options.length];

        for(int i=0;i<recProds.length;i++){
            int[] tempDept = hash.get(options[i]).value.getDepRating();
            recProds[i] = countInversions(tempDept);
        }

        return recProds;

    }

    public String[] recommend(String dataset, String recentPurchase, String[] options){
        products = options.clone();

        HashTable hash = new HashTable();

        try {
            hash.load(dataset);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int[] p1Dept = hash.get(recentPurchase).value.getDepRating();

        int p1 = countInversions(p1Dept);

        int[] recProds = new int[options.length];

        recProds = inversionCounts(dataset, options);

        int[] absCount = recProds.clone();

        for(int i=0;i<recProds.length;i++){
            absCount[i] = Math.abs(absCount[i]-p1);
        }

        sort(absCount, 0, absCount.length-1);

        return products;
    }

    private int countInversions(int[] arr){
        int count = mergeSortAndCount(arr,0,arr.length-1);
//        System.out.println(count);
        return count;
    }

    private static int mergeAndCount(int[] arr, int l, int m, int r) {

        // Left subarray
        int[] left = Arrays.copyOfRange(arr, l, m + 1);

        // Right subarray
        int[] right = Arrays.copyOfRange(arr, m + 1, r + 1);

        int i = 0, j = 0, k = l, swaps = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j])
                arr[k++] = left[i++];
            else {
                arr[k++] = right[j++];
                swaps += (m + 1) - (l + i);
            }
        }

        // Fill from the rest of the left subarray
        while (i < left.length)
            arr[k++] = left[i++];

        // Fill from the rest of the right subarray
        while (j < right.length)
            arr[k++] = right[j++];

        return swaps;
    }

    // Merge sort function
    private static int mergeSortAndCount(int[] arr, int l, int r) {

        int count = 0;

        if (l < r) {
            int m = (l + r) / 2;

            // Left subarray count
            count += mergeSortAndCount(arr, l, m);

            // Right subarray count
            count += mergeSortAndCount(arr, m + 1, r);

            // Merge count
            count += mergeAndCount(arr, l, m, r);
        }

        return count;
    }

    private int partition(int arr[], int low, int high)
    {
        int pivot = arr[high];
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++)
        {
            // If current element is smaller than the pivot
            if (compare(arr[j],pivot))
            {
                i++;
                // swap arr[i] and arr[j]
                swap(arr,i,j);
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)

        swap(arr,i+1,high);

        return i+1;
    }

    void sort(int arr[], int low, int high)
    {
        if (low < high)
        {
            /* pi is partitioning index, arr[pi] is
              now at right place */
            int pi = partition(arr, low, high);
            // Recursively sort elements before
            // partition and after partition
            sort(arr, low, pi-1);
            sort(arr, pi+1, high);
        }
    }
}