import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.lang.reflect.Field;


import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;


public class RecommenderTest {
	
	
	/**
	 * Check whether the inverse counting are the same 
	 * */
	private void recommend(String file_location){
		String load_file = file_location + ".txt";
		String rec = file_location + "_rec.txt";
		
		String recentPurchase = null;
		String[] options = null;
		
		String[] test_recommend = null;
		String[] sol_recommend = null;
		
		Recommender test = new Recommender();
		RecommenderSol sol = new RecommenderSol();
		
		
		try {
			Scanner in = new Scanner(new File(rec));
			if (in.hasNextLine()) {
				String line = in.nextLine();
				String[] arr = line.split(",");
				recentPurchase = arr[0];
				options = arr[1].split(" ");
				
			}
		}catch(Exception e) {
			Assert.fail("reading oops");
		}	
		
		test_recommend = test.recommend(load_file, recentPurchase, options);
		sol_recommend = sol.recommend(load_file, recentPurchase, options);

		assertArrayEquals("The recommendation list should be the same", sol_recommend, test_recommend);
		
		
		int test_swap = test.getSwaps();
		int sol_swap = sol.getSwaps();
		
		int test_compare = test.getComapares();
		int sol_compare = test.getComapares();
		
		if(test_swap > 1.1 * sol_swap || test_swap < 0.9 * sol_swap) {
			Assert.fail("Please use quick sort for sorting and use the default function in starter code");
		}
		
		if(test_compare > 1.1 * sol_compare || test_compare < 0.9 * sol_compare) {
			Assert.fail("Please use quick sort for sorting and use the default function in starter code");
		}
	}
	
	private void count(String file_location) {
		String load_file = file_location + ".txt";
		String rec = file_location + "_rec.txt";
		
		String recentPurchase = null;
		String[] options = null;
		
		int[] test_counting = null;
		int[] sol_counting = null;
		
		Recommender test = new Recommender();
		RecommenderSol sol = new RecommenderSol();
		
		
		try {
			Scanner in = new Scanner(new File(rec));
			if (in.hasNextLine()) {
				String line = in.nextLine();
				String[] arr = line.split(",");
				recentPurchase = arr[0];
				options = arr[1].split(" ");
			}
		}catch(Exception e) {
			Assert.fail("reading oops");
		}	
		
		test_counting = test.inversionCounts(load_file, options);
		sol_counting = sol.inversionCounts(load_file, options);
		
		assertArrayEquals("The inverse count list should be the same", sol_counting, test_counting);

	}
		
	@Test(timeout=1000)
	public void testRecommend() {
		System.out.println("\nStart testing recommend function");
		String file_path = System.getenv("ASNLIB") + "/testBigger";
		recommend(file_path);
	}
	
	@Test(timeout=1000)
	public void testCount() {
		System.out.println("\nStart testing count function");
		String file_path = System.getenv("ASNLIB") + "/testBigger";
		count(file_path);
	}
		
}
