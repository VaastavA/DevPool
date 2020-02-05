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

//TODO: Edge case, a<=0 anb b>N
/**
 * Set of tests for RedBlackBST class
 *
 * @author Vaastav Arora, arora74@purdue.edu
 */

@FixMethodOrder(MethodSorters.JVM)
public class RedBlackBSTTest {

    @Test
    public void testClassStructure(){

        Constructor<RedBlackBST> constructor = null;
        Field root = null;

        Method insert;
        Method delete;
        Method search;
        Method contains;

        Method getValByRank;
        Method rank;

        Method getElements;

        try {
            constructor = RedBlackBST.class.getConstructor();
        } catch (NoSuchMethodException e) {
            System.out.println("Ensure that RedBlackBST has a constructor!");
            Assert.fail();
        }

        try {
            root = RedBlackBST.class.getDeclaredField("root");
        } catch (NoSuchFieldException e) {
            System.out.println("Ensure that RedBlackBST has a field \"Node root\"!");
            Assert.fail();
        }

        try {
            insert = RedBlackBST.class.getDeclaredMethod("insert", Comparable.class, Object.class);
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that RedBlackBST has a method insert(Key key, Value value) that is declared public !");
        }

        try {
            delete = RedBlackBST.class.getDeclaredMethod("delete", Comparable.class);
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that RedBlackBST has a method delete(Key key) declared public !");
        }

        try {
            search = RedBlackBST.class.getDeclaredMethod("search", Comparable.class);
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that RedBlackBST has a method search(Key key) that is declared public !");
        }

        try {
            getValByRank = RedBlackBST.class.getDeclaredMethod("getValByRank", int.class);
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that RedBlackBST has a method getValByRank(int k) that is declared public !");
        }

        try {
            rank = RedBlackBST.class.getDeclaredMethod("rank", Comparable.class);
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that RedBlackBST has a method rank(int k) that is declared public !");
        }

        try {
            getElements = RedBlackBST.class.getDeclaredMethod("getElements",int.class,int.class);
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that RedBlackBST has a method getElements(int a, int b) that is declared public !");
        }

    }

    private void genericTester(String filename){

        Scanner readerTest = null;

        try {
            readerTest = new Scanner(new File(filename));
        } catch (IOException e) {
            System.out.println("Reading Oops");
        }


        RedBlackBSTSol<Integer, Integer> sol = new RedBlackBSTSol<>();
        RedBlackBST<Integer, Integer> test = new RedBlackBST<>();

        while(readerTest.hasNextLine()){
            String[] input  =readerTest.nextLine().split(" ");

            for(String x: input){
                System.out.print(x+" ");
            }

            System.out.println();
            switch (input[0]){
                case "insert":
                    Integer key = Integer.parseInt(input[1]);
                    Integer val = Integer.parseInt(input[2]);
                    sol.insert(key,val);
                    test.insert(key,val);
                    testTree(sol,test,"Insert");
                    break;
                case "delete":
                    Integer key1 = Integer.parseInt(input[1]);
                    sol.delete(key1);
                    test.delete(key1);
                    testTree(sol,test,"Delete");
                    break;
                case "search":
                    Integer key2 = Integer.parseInt(input[1]);
                    Integer ans1 = sol.search(key2);
                    Integer ans2 = test.search(key2);
                    assertEquals("Ensure search returns correct value!",ans1,ans2);
                    break;
                case "getval":
                    Integer key3 = Integer.parseInt(input[1]);
                    Integer ans11 = sol.getValByRank(key3);
                    Integer ans21 = test.getValByRank(key3);
                    assertEquals("Ensure getValByRank returns correct value!",ans11,ans21);
                    break;
                case "rank":
                    Integer key4 = Integer.parseInt(input[1]);
                    Object ans12 = sol.getValByRank(key4);
                    Object ans22 = test.getValByRank(key4);
                    assertEquals("Ensure rank returns correct value!",ans12,ans22);
                    break;
                case "getelement":
                    Integer low = Integer.parseInt(input[1]);
                    Integer high = Integer.parseInt(input[2]);
                    List<Integer> solList = sol.getElements(low,high);
                    List<Integer> testList = test.getElements(low,high);
                    //TODO: list compare
                    break;
                    default:
                        Assert.fail("Error, Invalid test instruction!");

            }
        }

    }

    private void testTree(RedBlackBSTSol sol, RedBlackBST test, String method){
        testTreeNodes(sol.root,test.root,method);

    }

    private void testTreeNodes(RedBlackBSTSol.Node sol, RedBlackBST.Node test,String method){

        if(sol == null  && test == null) return;
        else if(sol == null) Assert.fail("Error in: "+method+". Expected null Node but got non-null Node!");
        else if(test == null) Assert.fail("Error in: "+method+". Expected non-null Node but got null Node!");

        assertEquals("Error in: "+method+". Key mismatch >> Key value does not match!",sol.key, test.key);
        assertEquals("Error in: "+method+". Value mismatch >> Value value does not match!",sol.val, test.val);
        assertEquals("Error in: "+method+". Color mismatch >> Color value does not match!",sol.color,test.color);
        assertEquals("Error in: "+method+". Size mismatch >> Size value does not match!",sol.N, test.N);


        testTreeNodes(sol.left,test.left,method);
        testTreeNodes(sol.right,test.right,method);

    }

    @Test
    public void testSample() {
        genericTester("/Users/vaastavarora/Downloads/project3_solution/src/sampletest.txt");
    }


}