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

@FixMethodOrder(MethodSorters.JVM)
public class HuffmanCodeTest {

    @Test
    public void testClassPropertyNode() {
        Constructor<HuffmanCode.Node> constructor = null;
        Field ch = null;
        Field freq = null;
        Field left = null, right = null;

        try {
            constructor = HuffmanCode.Node.class.getConstructor(char.class, int.class, HuffmanCode.Node.class, HuffmanCode.Node.class);
        } catch (NoSuchMethodException e) {
            System.out.println("Ensure that HuffmanCode.Node has the described constructor!");
            Assert.fail();
        }

        try {
            ch = HuffmanCode.class.getDeclaredField("ch");
        } catch (NoSuchFieldException e) {
            System.out.println("Ensure that HuffmanCode.Node has a field char ch!");
            Assert.fail();
        }

        assertEquals("Ensure that ch is of type char !", ch.getClass(), char.class);


        try {
            freq = HuffmanCode.class.getDeclaredField("freq");
        } catch (NoSuchFieldException e) {
            System.out.println("Ensure that HuffmanCode.Node has a field int freq!");
            Assert.fail();
        }

        assertEquals("Ensure that freq is of type int !", freq.getClass(), int.class);

        try {
            left = HuffmanCode.class.getDeclaredField("left");
        } catch (NoSuchFieldException e) {
            System.out.println("Ensure that HuffmanCode.Node has a field Node left!");
            Assert.fail();
        }

        assertEquals("Ensure that left is of type HuffmanCode.Node !", left.getClass(), HuffmanCode.Node.class);


        try {
            right = HuffmanCode.class.getDeclaredField("right");
        } catch (NoSuchFieldException e) {
            System.out.println("Ensure that HuffmanCode.Node has a field Node right!");
            Assert.fail();
        }

        assertEquals("Ensure that right is of type HuffmanCode.Node !", right.getClass(), HuffmanCode.Node.class);


    }

    @Test
    public void testClassMethodsNode() {

        Method getCh;
        Method getFreq;
        Method getLeft;
        Method getRight;
        Method isLeaf;
        Method toCompare;

        try {
            getCh = HuffmanCode.class.getDeclaredMethod("getCh");
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that HuffmanCode.Node has a method getCh that is declared public !");
        }

        try {
            getFreq = HuffmanCode.class.getDeclaredMethod("getFreq");
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that HuffmanCode.Node has a method getFreq that is declared public !");
        }

        try {
            getLeft = HuffmanCode.class.getDeclaredMethod("getLeft");
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that HuffmanCode.Node has a method getLeft that is declared public !");
        }

        try {
            getRight = HuffmanCode.class.getDeclaredMethod("getRight");
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that HuffmanCode.Node has a method getRight that is declared public !");
        }

        try {
            isLeaf = HuffmanCode.class.getDeclaredMethod("isLeaf");
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that HuffmanCode.Node has a method isLeaf that is declared public !");
        }

        try {
            toCompare = HuffmanCode.class.getDeclaredMethod("toCompare", HuffmanCode.Node.class);
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that HuffmanCode.Node has a method toCompare that is declared public !");
        }

    }

    @Test
    public void testClassProperty() {

        Constructor<HuffmanCode> constructor = null;
        Field huffmanTree = null;
        Field encoding = null;
        Field decoding = null;
        Field text = null;

        try {
            constructor = HuffmanCode.class.getConstructor(String.class);
        } catch (NoSuchMethodException e) {
            System.out.println("Ensure that HuffmanCode has the described constructor!");
            Assert.fail();
        }

        try {
            huffmanTree = HuffmanCode.class.getDeclaredField("huffmanTree");
        } catch (NoSuchFieldException e) {
            System.out.println("Ensure that HuffmanCode has a field Node huffmanTree!");
            Assert.fail();
        }

        assertEquals("Ensure that text is of type String !", huffmanTree.getClass(), HuffmanCode.Node.class);

        try {
            encoding = HuffmanCode.class.getDeclaredField("encoding");
        } catch (NoSuchFieldException e) {
            System.out.println("Ensure that HuffmanCode has a field HashMap<Character, String> encoding!");
            Assert.fail();
        }

        assertEquals("Ensure that text is of type String !", encoding.getClass(), HashMap.class);

        try {
            decoding = HuffmanCode.class.getDeclaredField("decoding");
        } catch (NoSuchFieldException e) {
            System.out.println("Ensure that HuffmanCode has a field HashMap<String, Character> decoding!");
            Assert.fail();
        }

        assertEquals("Ensure that text is of type String !", decoding.getClass(), HashMap.class);

        try {
            text = HuffmanCode.class.getDeclaredField("text");
        } catch (NoSuchFieldException e) {
            System.out.println("Ensure that HuffmanCode has a field String text!");
            Assert.fail();
        }

        assertEquals("Ensure that text is of type String !", text.getClass(), String.class);

    }

    @Test
    public void testClassMethods() {

        Method compress;
        Method expand;

        try {
            compress = HuffmanCode.class.getDeclaredMethod("compress", String.class);
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that HuffmanCode has a method compress that is declared public !");
        }

        try {
            compress = HuffmanCode.class.getDeclaredMethod("expand", String.class);
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that HuffmanCode has a method expand that is declared public !");
        }
        
    }
}
