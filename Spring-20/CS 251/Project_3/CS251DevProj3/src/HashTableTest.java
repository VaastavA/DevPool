import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class HashTableTest {
    public void testClassStructure() {
		Constructor<HashTable> constructor = null;
		Method load;
		Method put;
		Method print; 
		Method get_collisions;
		Method resize;
		
		System.out.println("Testing Class Structure....");
		
		try {
			constructor = HashTable.class.getConstructor();
		}catch (NoSuchMethodException e){
			Assert.fail("Ensure that HashTable has a constructor!");
		}
		
		try {
			load = HashTable.class.getDeclaredMethod("load", String.class);
		}catch (NoSuchMethodException e ) {
			Assert.fail("Ensure that HashTable has a method load(String filename) that is declare public!");
		}
		
		try {
			put = HashTable.class.getDeclaredMethod("put", String.class, Product.class);
		}catch (NoSuchMethodException e) {
			Assert.fail("Ensure that HashTable has a method put(String vendor, Product value) that is declare public!");
		}
		
		try {
			print = HashTable.class.getDeclaredMethod("print");
		}catch (NoSuchMethodException e) {
			Assert.fail("Ensure that HashTable has a method print() that is declare public!");
		}
		
		try {
			get_collisions = HashTable.class.getDeclaredMethod("getCollisions");
		}catch (NoSuchMethodException e) {
			Assert.fail("Ensure that HashTable has a method getCollisions() that is declare public!");
		}
		
		try {
			resize = HashTable.class.getDeclaredMethod("resize");
		}catch (NoSuchMethodException e) {
			Assert.fail("Ensure that HashTable has a method resize() that is declare public!");
		}
	}
    
    
    /**
	 * Test put value from zero.
	 * */
	private void zeroPut(String file_location) throws Exception{
		String add_file = file_location + "_add.txt";
        
        Product case_product = loadProduct(add_file)[0]; 
		
		HashTable hash = new HashTable();
		HashTableSol hash_sol = new HashTableSol(); 
		
		hash.put(case_product.getName(), case_product);
		hash_sol.put(case_product.getName(), case_product);
		checkPrint(hash, hash_sol, "zeroPut");
	}
    
    
    /**
	 * Load a file and transfer the file to product array
	 * @param filename the file name of the test file
	 * */
	private Product[] loadProduct(String filename) {
		ArrayList <Product> product_array = new ArrayList<Product>();
		
		try {
			Scanner in = new Scanner(new File(filename));
			String line = null;
			while(in.hasNextLine()) {
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
	        
	            Product new_product = new Product(name, vendor, depRating, price);
	            product_array.add(new_product);
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		 
		return product_array.toArray(new Product[0]);
	}
    
    
    /**
	 *  Examine the content of the hash table by print function.
	 *  @param hash student's hashtable class
	 *  @param hash_sol solution's hashtable class
	 */
	private void checkPrint(HashTable hash, HashTableSol hash_sol, String test_case) throws Exception {
		
		PrintStream old = System.out;
		
		// The output of test code
		ByteArrayOutputStream test_output = new ByteArrayOutputStream();
		PrintStream topt = new PrintStream(test_output);
		System.setOut(topt);
		
		hash.print();
		
		System.out.flush();
		System.setOut(old);
		String test_string = test_output.toString();
		
		
		// The output of solution code
		ByteArrayOutputStream sol_output = new ByteArrayOutputStream();
		PrintStream sopt = new PrintStream(sol_output);
		System.setOut(sopt);
		
		hash_sol.print();
		
		System.out.flush();
		System.setOut(old);
		String sol_string = sol_output.toString();
		
		assertEquals( test_case + " doesn't have the same output as solution", sol_string, test_string);
	}

    
    /**
	 * Test serially put value from zero.
	 * */
	private void zeroSeriallyPut(String file_location) throws Exception{
		
		String add_file = file_location + "_add.txt";
		
		Product case_product[] = loadProduct(add_file); 
		
		HashTable hash = new HashTable();
		HashTableSol hash_sol = new HashTableSol(); 
		
		for (int i = 0; i < case_product.length; i++) {
			hash.put(case_product[i].getName(), case_product[i]);
			hash_sol.put(case_product[i].getName(), case_product[i]);
		}
		checkPrint(hash, hash_sol, "zeroSeriallyPut");
	}
    
    
    /**
	 * Test put single value to the table.
	 * */
	private void standardPut(String file_location) throws Exception{
		String load_file = file_location + ".txt";
		String add_file = file_location + "_add.txt";
		
		Product case_product = loadProduct(add_file)[0]; 
		
		HashTable hash = new HashTable();
		HashTableSol hash_sol = new HashTableSol();
		
		hash.load(load_file);
		hash_sol.load(load_file);
		
		hash.put(case_product.getName(), case_product);
		hash_sol.put(case_product.getName(), case_product);
		checkPrint(hash, hash_sol, "standardPut");
	}
    
    
    /**
	 * Test put multiple values to the table.
	 * */
	private void seriallyPut(String file_location) throws Exception {
		String load_file = file_location + ".txt";
		String add_file = file_location + "_add.txt";
		
		Product[] case_product = loadProduct(add_file); 
		
		HashTable hash = new HashTable();
		HashTableSol hash_sol = new HashTableSol();
		
		hash.load(load_file);
		hash_sol.load(load_file);
		
		for (int i = 0; i < case_product.length; i++) {
			hash.put(case_product[i].getName(), case_product[i]);
			hash_sol.put(case_product[i].getName(), case_product[i]);
		}
		checkPrint(hash, hash_sol, "seriallyPut");
	}

	
    
    /**
	 * Test remove value to the table, and the value is in the table.
	 * */
	private void standardRemove(String file_location) throws Exception{
		String load_file = file_location + ".txt";
		String remove_file = file_location + "_remove.txt";
		
		Product case_product = loadProduct(remove_file)[0]; 
		String product_name = case_product.getName();
		
		HashTable hash = new HashTable();
		HashTableSol hash_sol = new HashTableSol();
		
		hash.load(load_file);
		hash_sol.load(load_file);
		
		hash.remove(product_name);
		hash_sol.remove(product_name);
        
		checkPrint(hash, hash_sol, "standardRemove");
	}
    
    
    /**
	 * Test remove value to the table, and the value is in the table.
	 * */
	private void seriallyRemove(String file_location) throws Exception{
		String load_file = file_location + ".txt";
		String remove_file = file_location + "_remove.txt";
		
		Product[] case_product = loadProduct(remove_file); 
		
		HashTable hash = new HashTable();
		HashTableSol hash_sol = new HashTableSol();
		
		hash.load(load_file);
		hash_sol.load(load_file);
		
		for (int i = 0; i < case_product.length; i++) {
			hash.remove(case_product[i].getName());
			hash_sol.remove(case_product[i].getName());
		}
        
		checkPrint(hash, hash_sol, "standardRemove");
	}
    
    
    /**
	 * Test remove value to the table, and the value is not in the table.
	 * */
	private void zeroRemove(String file_location) throws Exception{
		String load_file = file_location + ".txt";
		String remove_file = file_location + "_zero_remove.txt";
		
		Product case_product = loadProduct(remove_file)[0];
		
		String product_name = case_product.getName();
		
		HashTable hash = new HashTable();
		HashTableSol hash_sol = new HashTableSol();
		
		hash.load(load_file);
		hash_sol.load(load_file);
		
		hash.remove(product_name);
		hash_sol.remove(product_name);
		
		checkPrint(hash, hash_sol, "zeroRemove");
	}
    
    
    /**
	 * Examine resize function, it should return the resized HashTable.
	 * */
	private void testResize(String file_location) throws Exception{
		
		String load_file = file_location + ".txt";
		String add_file = file_location + "_add.txt";
		
		Product case_product[] = loadProduct(add_file); 
		
		HashTable hash = new HashTable();
		HashTableSol hash_sol = new HashTableSol(); 
		
		hash.load(load_file);
		hash_sol.load(load_file);
		assertEquals("The hash table size are differnet before testing resize()", hash_sol.getCapacity(), hash.getCapacity());
		
		
		for (int i = 0; i < case_product.length; i++) {
			hash.put(case_product[i].getName(), case_product[i]);
			hash_sol.put(case_product[i].getName(), case_product[i]);
		}
				
		assertEquals("The hash table size are differnet after testing resize()", hash_sol.getCapacity(), hash.getCapacity());
	} 
    
    
    /**
	 * Test getCollisions function, the function should shows in which 
	 * will print the collisions and the index of the array that they occur.
	 * */ 
	private void collision(String file_location) throws Exception{
		String load_file = file_location + ".txt";
		
		HashTable hash = new HashTable();
		HashTableSol hash_sol = new HashTableSol();
		
		hash.load(load_file);
		hash_sol.load(load_file);
		
		PrintStream old = System.out;
		
		// The output of test code
		ByteArrayOutputStream test_output = new ByteArrayOutputStream();
		PrintStream topt = new PrintStream(test_output);
		System.setOut(topt);
		
		hash.getCollisions();
		
		System.out.flush();
		System.setOut(old);
		String test_string = test_output.toString();
		
		ByteArrayOutputStream sol_output = new ByteArrayOutputStream();
		PrintStream sopt = new PrintStream(sol_output);
		System.setOut(sopt);
		
		hash_sol.getCollisions();
		
		System.out.flush();
		System.setOut(old);
		String sol_string = sol_output.toString();
		
		assertEquals( "The getCollision() doesn't have the same output as solution", sol_string, test_string);
	}
    
    
    /**
	 * Test the hashTable by loading the file
	 * @param filename the file name of the test file, the format should be "./test1". Noted that there is not file format
	 * */
	private void testTable(String filename){
		String file_location = System.getenv("ASNLIB")+ filename;
		
		try {
			zeroPut(file_location);
			zeroSeriallyPut(file_location);
			load(file_location);
			standardPut(file_location);
			seriallyPut(file_location);
			standardRemove(file_location);
			seriallyRemove(file_location);
			zeroRemove(file_location);
			collision(file_location);
			testResize(file_location);
		}catch (Exception e) {
			Assert.fail();
			e.printStackTrace();
		}

	}
    
    //-------------------------------------------------------------------------
    /**
	 * Test load a file to the hash table.
	 * */
	private void load(String file_location) throws Exception{
		String load_file = file_location + ".txt";
		
		HashTable hash = new HashTable();
		HashTableSol hash_sol = new HashTableSol(); 
		
		hash.load(load_file);
		hash_sol.load(load_file);
        
		checkPrint(hash, hash_sol, "load");
	}
    
    /**
	 * Examine get function, it should return a product.
	 * the test file should be two string the first string should exists in the hashtable, but the second one should not in the hashtable.
	 * */
	private void get(String file_location) throws Exception{
		String load_file = file_location + ".txt";
		String get_file = file_location + "_get.txt";
		String e_name = null;
		String ne_name = null;
		
		try {
			Scanner in = new Scanner(new File(get_file));
			if (in.hasNextLine()) {
				String line = in.nextLine();
				String[] arr = line.split(",");
				e_name = arr[0];
				ne_name = arr[1];
			}
		}catch (Exception e) {
			Assert.fail("reading oops");
		}
		
		
		HashTable hash = new HashTable();
		HashTableSol hash_sol = new HashTableSol(); 
		
		hash.load(load_file);
		hash_sol.load(load_file);
		
		//Test get the existing product
		assertEquals("The get function does not provide correct output", hash_sol.get(e_name).toString(), hash.get(e_name).toString());
		
		//Test get the existing product
		assertNull("The get function for non existing products should return null",hash.get(ne_name));
		
	}
        
    @Test(timeout = 10000)
    public void testStructure() {
        testClassStructure();
    }
        
    @Test(timeout = 10000)
    public void testAdd() {
        System.out.println("\nTesting Put....");
        String file_location = System.getenv("ASNLIB") + "/testBigger";
        try {
			zeroPut(file_location);
			zeroSeriallyPut(file_location);
			standardPut(file_location);
			seriallyPut(file_location);
		}catch (Exception e) {
			Assert.fail();
			e.printStackTrace();
		}
    }
    
    @Test(timeout = 10000)
	public void testload(){
		System.out.println("\nRunning load test....");
		String filename = "/testBigger";
		String file_location = System.getenv("ASNLIB") + filename;
		try {
			load(file_location);
		}catch (Exception e) {
			Assert.fail();
			e.printStackTrace();
		}
	}
        
    @Test(timeout = 10000)
	public void testRemove() {
		System.out.println("\nTesting Remove....");
		String file_location = System.getenv("ASNLIB") + "/testBigger";
		try {
			standardRemove(file_location);
			seriallyRemove(file_location);
			zeroRemove(file_location);
		}catch (Exception e) {
			Assert.fail();
			e.printStackTrace();
		}
	}    
    
    @Test(timeout = 10000)
	public void testget(){
		System.out.println("\nRunning get test....");
		String filename = "/testBigger";
		String file_location = System.getenv("ASNLIB") + filename;
		try {
			get(file_location);
		}catch (Exception e) {
			Assert.fail();
			e.printStackTrace();
		}
	}
    
    @Test(timeout = 10000)
	public void testResize() {
		System.out.println("\nTesting Resize....");
		String filename = "/testBigger" ;
		String file_location = System.getenv("ASNLIB") + filename;
		try {
			testResize(file_location);
		}catch (Exception e) {
			Assert.fail();
			e.printStackTrace();
		}
	}
    
    @Test(timeout = 10000)
	public void testCollision() {
		System.out.println("\nTesting Collision....");
		String filename = "/testBigger" ;
		String file_location = System.getenv("ASNLIB")+ filename;
		try {
			collision(file_location);
		}catch (Exception e) {
			Assert.fail();
			e.printStackTrace();
		}
	}
        
    @Test(timeout = 10000)
	public void testcase() {
		System.out.println("\nRunning testcase....");
		testTable("/testBigger");
	}
}