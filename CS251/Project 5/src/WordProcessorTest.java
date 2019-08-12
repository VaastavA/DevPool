import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Set of tests for WordProcessor class
 *
 * @author Vaastav Arora, arora74@purdue.edu
 * @version July 30th 2019
 */

@FixMethodOrder(MethodSorters.JVM)
public class WordProcessorTest {

    /*
     * Checks Constructor and fields of WordProcessor.Node class
     */
    @Test
    public void testClassPropertyNode() {

        Constructor<WordProcessorSol.Node> constructorNode = null; // Constructor test not working

        Field nodeChar = null;
        Field nodeLeft = null;
        Field nodeEqual = null;
        Field nodeRight = null;
        Field nodeIsEnd = null;

        try {
            nodeChar = WordProcessor.Node.class.getDeclaredField("c");

        } catch (NoSuchFieldException e){
            System.out.println("Ensure that Node has a field \'c\' of type char!");
            Assert.fail();
        }

        try {
            nodeLeft = WordProcessor.Node.class.getDeclaredField("left");
        } catch (NoSuchFieldException e){
            System.out.println("Ensure that Node has a field \'left\' of type Node!");
            Assert.fail();
        }

        try {
            nodeEqual = WordProcessor.Node.class.getDeclaredField("equal");
        } catch (NoSuchFieldException e){
            System.out.println("Ensure that Node has a field \'equal\' of type Node!");
            Assert.fail();
        }

        try {
            nodeRight = WordProcessor.Node.class.getDeclaredField("right");
        } catch (NoSuchFieldException e){
            System.out.println("Ensure that Node has a field \'right\' of type Node!");
            Assert.fail();
        }

        try {
            nodeIsEnd = WordProcessor.Node.class.getDeclaredField("isEnd");
        } catch (NoSuchFieldException e){
            System.out.println("Ensure that Node has a field \'isEnd\' of type boolean");
            Assert.fail();
        }

    }

    /*
     * Check Constructor,fields and methods of WordProcessor class
     */
    @Test
    public void testClassPropertyAndMethodWordProcessor() {

        Constructor<WordProcessor> constructorWordProcessor = null;

        Field wordTrie = null;

        Method addWord = null;
        Method addAllWords = null;
        Method wordSearch = null;
        Method isEmpty = null;
        Method clear = null;
        Method autoCompleteOptions = null;
        Method getWordTrie = null;


        try {
            constructorWordProcessor = WordProcessor.class.getConstructor();
        } catch (NoSuchMethodException e){
            System.out.println("Ensure that WordProcessor has a constructor!");
            Assert.fail();
        }

        try {
            wordTrie = WordProcessor.class.getDeclaredField("wordTrie");
        } catch (NoSuchFieldException e) {
            System.out.println("Ensure that WordProcessor has a field \'wordTrie\' of type Node!");
        }

        try {
            addWord = WordProcessor.class.getDeclaredMethod("addWord", String.class);
        } catch (NoSuchMethodException e) {
            System.out.println("Ensure that WordProcessor has a method addWord() that takes a String parameter!");
            Assert.fail();
        }

        try {
            addAllWords = WordProcessor.class.getDeclaredMethod("addAllWords", String[].class);
        } catch (NoSuchMethodException e) {
            System.out.println("Ensure that WordProcessor has a method addAllWords() that takes a String[] parameter");
            Assert.fail();
        }

        try {
            wordSearch = WordProcessor.class.getDeclaredMethod("wordSearch", String.class);
        } catch (NoSuchMethodException e) {
            System.out.println("Ensure that WordProcessor has a method wordSearch() that takes a String parameter!");
            Assert.fail();
        }

        try {
            isEmpty = WordProcessor.class.getDeclaredMethod("isEmpty");
        } catch (NoSuchMethodException e) {
            System.out.println("Ensure that WordProcessor has a method isEmpty()!");
            Assert.fail();
        }

        try {
            clear = WordProcessor.class.getDeclaredMethod("clear");
        } catch (NoSuchMethodException e) {
            System.out.println("Ensure that WordProcessor has a method clear()!");
            Assert.fail();
        }

        try {
            autoCompleteOptions = WordProcessor.class.getDeclaredMethod("autoCompleteOptions", String.class);
        } catch (NoSuchMethodException e) {
            System.out.println("Ensure that WordProcessor has a method autoCompleteOptions() that takes a String parameter!");
            Assert.fail();
        }

        try {
            getWordTrie = WordProcessor.class.getDeclaredMethod("getWordTrie");
        } catch (NoSuchMethodException e) {
            System.out.println("Ensure that WordProcessor has a method getWordTrie()!");
        }
    }

    /*
     * Tests one word with WordProcessor class
     */
    @Test
    public void testOneWord() {

        WordProcessor test = new WordProcessor();
        WordProcessorSol sol = new WordProcessorSol();

        Assert.assertEquals("Ensure that wordTrie is initialized correctly in the constructor!",sol.getWordTrie(),test.getWordTrie());

        test.addWord("Mango");
        sol.addWord("Mango");

        trieCompare(sol.getWordTrie(),test.getWordTrie());

        Assert.assertEquals("Ensure wordSearch() returns the correct value!", sol.wordSearch("Mango"), test.wordSearch("Mango"));
        Assert.assertEquals("Ensure isEmpty() returns the correct value!", sol.isEmpty(),test.isEmpty());

        optionsCompare(sol.autoCompleteOptions("Ma"),test.autoCompleteOptions("Ma"));

        test.clear();
        sol.clear();

        Assert.assertEquals("Ensure clear() works as described!", sol.isEmpty(),test.isEmpty());

    }

    /*
     * Tests four words with WordProcessor class
     */
    @Test
    public void testFourWords() {

        WordProcessor test = new WordProcessor();
        WordProcessorSol sol = new WordProcessorSol();

        Assert.assertEquals("Ensure that wordTrie is initialized correctly in the constructor!",sol.getWordTrie(),test.getWordTrie());

        test.addWord("cat");
        sol.addWord("cat");

        trieCompare(sol.getWordTrie(),test.getWordTrie());
        Assert.assertEquals("Ensure isEmpty() returns the correct value!", sol.isEmpty(),test.isEmpty());


        test.addWord("bug");
        sol.addWord("bug");

        trieCompare(sol.getWordTrie(),test.getWordTrie());
        Assert.assertEquals("Ensure isEmpty() returns the correct value!", sol.isEmpty(),test.isEmpty());


        test.addWord("cats");
        sol.addWord("cats");

        trieCompare(sol.getWordTrie(),test.getWordTrie());
        Assert.assertEquals("Ensure isEmpty() returns the correct value!", sol.isEmpty(),test.isEmpty());


        test.addWord("up");
        sol.addWord("up");

        trieCompare(sol.getWordTrie(),test.getWordTrie());
        Assert.assertEquals("Ensure isEmpty() returns the correct value!", sol.isEmpty(),test.isEmpty());

        optionsCompare(sol.autoCompleteOptions("Ma"),test.autoCompleteOptions("Ma"));
        optionsCompare(sol.autoCompleteOptions("ca"),test.autoCompleteOptions("ca"));

        Assert.assertEquals("Ensure wordSearch() returns the correct value!", sol.wordSearch("bug"), test.wordSearch("bug"));
        Assert.assertEquals("Ensure wordSearch() returns the correct value!", sol.wordSearch("cats"), test.wordSearch("cats"));
        Assert.assertEquals("Ensure wordSearch() returns the correct value!", sol.wordSearch("up"), test.wordSearch("up"));
        Assert.assertEquals("Ensure wordSearch() returns the correct value!", sol.wordSearch("cat"), test.wordSearch("cat"));

        test.clear();
        sol.clear();

        Assert.assertEquals("Ensure clear() works as described!", sol.isEmpty(),test.isEmpty());

        Assert.assertEquals("Ensure wordSearch() returns the correct value!", sol.wordSearch("bug"), test.wordSearch("bug"));
        Assert.assertEquals("Ensure wordSearch() returns the correct value!", sol.wordSearch("cats"), test.wordSearch("cats"));
        Assert.assertEquals("Ensure wordSearch() returns the correct value!", sol.wordSearch("up"), test.wordSearch("up"));
        Assert.assertEquals("Ensure wordSearch() returns the correct value!", sol.wordSearch("cat"), test.wordSearch("cat"));

        test.addWord("Mango");
        sol.addWord("Mango");

        trieCompare(sol.getWordTrie(),test.getWordTrie());

        Assert.assertEquals("Ensure wordSearch() returns the correct value!", sol.wordSearch("Mango"), test.wordSearch("Mango"));
        Assert.assertEquals("Ensure isEmpty() returns the correct value!", sol.isEmpty(),test.isEmpty());

        optionsCompare(sol.autoCompleteOptions("Ma"),test.autoCompleteOptions("Ma"));

        test.clear();
        sol.clear();

        Assert.assertEquals("Ensure clear() works as described!", sol.isEmpty(),test.isEmpty());


    }

    /*
     * Tests each word in LotrOne.txt
     */
    @Test
    public void testLargeOne() {

        WordProcessor test = new WordProcessor();
        WordProcessorSol sol = new WordProcessorSol();

        Scanner s = null;
        HashSet<String> searchSet = new HashSet<>();

        TreeMap<String, Integer>[] searchTracker = new TreeMap[5];

        for(int i=0;i<searchTracker.length;i++) {
            searchTracker[i] = new TreeMap<>();
        }

        try {
            s = new Scanner(new File("/Users/vaastavarora/IdeaProjects/Project 5/src/LotrOne.txt"));
        } catch (FileNotFoundException e) {
            Assert.fail("File not found exception");
        }

        while(s.hasNextLine()) {
            String[] in = s.nextLine().replaceAll("[^a-zA-Z0-9 ]","").split("\\s+");

            for(int i=0;i<in.length;i++) {
                if (!in[i].equals("")) {
                    if (!test.wordSearch(in[i])) {
                        for (int j = 1; j < in[i].length() && j < 6; j++) {
                            searchTracker[j-1].put(in[i].substring(0, j), searchTracker[j-1].getOrDefault(in[i].substring(0, j), 0) + 1);
                        }
                    }

                    test.addWord(in[i]);
                    sol.addWord(in[i]);

                    searchSet.add(in[i]);
                }
            }
        }

        trieCompare(sol.getWordTrie(),test.getWordTrie());

        for(String j:searchSet) {
            Assert.assertEquals("Ensure wordSearch() returns the correct value!", sol.wordSearch(j), test.wordSearch(j));
        }

        for(int i=0;i<searchTracker.length;i++) {
            for(String j:searchTracker[i].keySet()) {
                if (j.equals("")) System.out.println("Shouldn't have happened");;
                int a = (test.wordSearch(j))? 0: searchTracker[i].get(j);
                Assert.assertEquals("Ensure number of suggestions returned is correct!",a,test.autoCompleteOptions(j).size());
            }
        }
    }

    /*
     * Tests each sentence in LotrOne.txt
     */
    @Test
    public void testLargeTwo() {

        WordProcessor test = new WordProcessor();
        WordProcessorSol sol = new WordProcessorSol();

        Scanner s = null;
        HashSet<String> searchSet = new HashSet<>();

        TreeMap<String, Integer>[] searchTracker = new TreeMap[10];

        for(int i=0;i<searchTracker.length;i++) {
            searchTracker[i] = new TreeMap<>();
        }

        try {
            s = new Scanner(new File("/Users/vaastavarora/IdeaProjects/Project 5/src/LotrOne.txt"));
        } catch (FileNotFoundException e) {
            Assert.fail("File not found exception");
        }

        while(s.hasNextLine()) {
            String in = s.nextLine();
            if(in.equals("")) continue;

            if (!test.wordSearch(in)) {
                for (int j = 1; j < in.length() && j < 11; j++) {
                    searchTracker[j-1].put(in.substring(0, j), searchTracker[j-1].getOrDefault(in.substring(0, j), 0) + 1);
                }
            }

            test.addWord(in);
            sol.addWord(in);
            searchSet.add(in);
        }

        trieCompare(sol.getWordTrie(),test.getWordTrie());

        for(String j:searchSet) {
            Assert.assertEquals("Ensure wordSearch() returns the correct value!", sol.wordSearch(j), test.wordSearch(j));
        }

        for(int i=0;i<searchTracker.length;i++) {
            for(String j:searchTracker[i].keySet()) {
                if (j.equals("")) System.out.println("Shouldn't have happened");;
                int a = (test.wordSearch(j))? 0: searchTracker[i].get(j);
                Assert.assertEquals("Ensure number of suggestions returned is correct!",a,test.autoCompleteOptions(j).size());
            }
        }
    }

    /*
     * Tests each word in Silmarillion.txt
     */
    @Test
    public void testLargeThree() {

        WordProcessor test = new WordProcessor();
        WordProcessorSol sol = new WordProcessorSol();

        Scanner s = null;
        HashSet<String> searchSet = new HashSet<>();

        TreeMap<String, Integer>[] searchTracker = new TreeMap[5];

        for(int i=0;i<searchTracker.length;i++) {
            searchTracker[i] = new TreeMap<>();
        }

        try {
            s = new Scanner(new File("/Users/vaastavarora/IdeaProjects/Project 5/src/Silmarillion.txt"));
        } catch (FileNotFoundException e) {
            Assert.fail("File not found exception");
        }

        while(s.hasNextLine()) {
            String[] in = s.nextLine().replaceAll("[^a-zA-Z0-9 ]","").split("\\s+");

            for(int i=0;i<in.length;i++) {
                if (!in[i].equals("")) {
                    if (!test.wordSearch(in[i])) {
                        for (int j = 1; j < in[i].length() && j < 6; j++) {
                            searchTracker[j-1].put(in[i].substring(0, j), searchTracker[j-1].getOrDefault(in[i].substring(0, j), 0) + 1);
                        }
                    }

                    test.addWord(in[i]);
                    sol.addWord(in[i]);

                    searchSet.add(in[i]);
                }
            }
        }

        trieCompare(sol.getWordTrie(),test.getWordTrie());

        for(String j:searchSet) {
            Assert.assertEquals("Ensure wordSearch() returns the correct value!", sol.wordSearch(j), test.wordSearch(j));
        }

        for(int i=0;i<searchTracker.length;i++) {
            for(String j:searchTracker[i].keySet()) {
                if (j.equals("")) System.out.println("Shouldn't have happened");;
                int a = (test.wordSearch(j))? 0: searchTracker[i].get(j);
                Assert.assertEquals("Ensure number of suggestions returned is correct!",a,test.autoCompleteOptions(j).size());
            }
        }
    }

    /*
     * Tests each sentence in Silmarillion.txt
     */
    @Test
    public void testLargeFour() {

        WordProcessor test = new WordProcessor();
        WordProcessorSol sol = new WordProcessorSol();

        Scanner s = null;
        HashSet<String> searchSet = new HashSet<>();

        TreeMap<String, Integer>[] searchTracker = new TreeMap[10];

        for(int i=0;i<searchTracker.length;i++) {
            searchTracker[i] = new TreeMap<>();
        }

        try {
            s = new Scanner(new File("/Users/vaastavarora/IdeaProjects/Project 5/src/Silmarillion.txt"));
        } catch (FileNotFoundException e) {
            Assert.fail("File not found exception");
        }

        while(s.hasNextLine()) {
            String in = s.nextLine();
            if(in.equals("")) continue;

            if (!test.wordSearch(in)) {
                for (int j = 1; j < in.length() && j < 11; j++) {
                    searchTracker[j-1].put(in.substring(0, j), searchTracker[j-1].getOrDefault(in.substring(0, j), 0) + 1);
                }
            }

            test.addWord(in);
            sol.addWord(in);
            searchSet.add(in);
        }

        trieCompare(sol.getWordTrie(),test.getWordTrie());

        for(String j:searchSet) {
            Assert.assertEquals("Ensure wordSearch() returns the correct value!", sol.wordSearch(j), test.wordSearch(j));
        }

        for(int i=0;i<searchTracker.length;i++) {
            for(String j:searchTracker[i].keySet()) {
                if (j.equals("")) System.out.println("Shouldn't have happened");;
                int a = (test.wordSearch(j))? 0: searchTracker[i].get(j);
                Assert.assertEquals("Ensure number of suggestions returned is correct!",a,test.autoCompleteOptions(j).size());
            }
        }
    }

    /**
     * Helper function to test contructed trie in WordProcessor
     * @param exp Expected wordTrie Node
     * @param actual Actual wordTrie Node
     */
    private void trieCompare(WordProcessorSol.Node exp, WordProcessor.Node actual) {

        if( exp == null && actual == null) return;

        if(exp == null) Assert.fail("Ensure Nodes are added correctly!");
        else if(actual == null) Assert.fail("Ensure Nodes are added correctly!");
        else {

            if( exp.c != actual.c || exp.isEnd != actual.isEnd) Assert.fail("Ensure correct Nodes are added to the trie!");
            trieCompare(exp.left,actual.left);
            trieCompare(exp.equal,actual.equal);
            trieCompare(exp.right,actual.right);
        }
    }

    /**
     * Helper function to check list returned by autoCompleteOptions
     * @param exp Expected list of word suggestions
     * @param actual Actual list of word suggestions
     */
    private void optionsCompare(List<String> exp, List<String> actual) {

        HashSet<String> expStrings = new HashSet<>();

        for(String i: exp) {
            expStrings.add(i);
        }

        for(String i: actual) {
            if(!expStrings.contains(i)) Assert.fail("Ensure autoCompleteOptions returns the correct recommendation strings!");
            expStrings.remove(i);
        }

        if(expStrings.size() >0) Assert.fail("Ensure autoCompleteOptions list includes all recommendation strings!");
    }
}