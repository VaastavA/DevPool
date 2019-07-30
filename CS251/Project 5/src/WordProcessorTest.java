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
    }


}