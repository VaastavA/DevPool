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

/**
 * Set of tests for WordProcessor class
 *
 * @author Vaastav Arora, arora74@purdue.edu
 * @version July 30th 2019
 */

@FixMethodOrder(MethodSorters.JVM)
public class WordProcessorTest {

    @Test
    public void testClassPropertyNode() {

        Constructor<WordProcessor.Node> constructorNode = null;

        Field nodeChar = null;
        Field nodeLeft = null;
        Field nodeEqual = null;
        Field nodeRight = null;
        Field nodeIsEnd = null;

        try {
            constructorNode = WordProcessor.Node.class.getConstructor(char.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            System.out.println("Ensure that Node has a constructor!");
            Assert.fail();
        }

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

    @Test
    public void testLargeOne() {

        WordProcessor test = new WordProcessor();
        WordProcessorSol sol = new WordProcessorSol();

        Scanner s = null;
        HashSet<String> searchSet = new HashSet<>();

        try {
            s = new Scanner(new File("/Users/vaastavarora/IdeaProjects/Project 5/src/LotrOne.txt"));
        } catch (FileNotFoundException e) {
            Assert.fail("File not found exception");
        }

        int newlineCount = 0;

        while(s.hasNextLine()) {
            String[] in = s.nextLine().split(" ");

            for(String i: in) {

                if(i.equals("")) continue;

                test.addWord(i);
                sol.addWord(i);
                searchSet.add(i);
            }
        }

        trieCompare(sol.getWordTrie(),test.getWordTrie());

        for(String j:searchSet) {
            Assert.assertEquals("Ensure wordSearch() returns the correct value!", sol.wordSearch(j), test.wordSearch(j));
        }

        for(int k = 0;k<26;k++) {
            String temp = "";
            temp += (char)('a'+k);
            optionsCompare(sol.autoCompleteOptions(temp), test.autoCompleteOptions(temp));
        }
    }

    @Test
    public void testLargeTwo() {

    }

    @Test
    public void testLargeThree() {

    }

    @Test
    public void testLargeFour() {

    }

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