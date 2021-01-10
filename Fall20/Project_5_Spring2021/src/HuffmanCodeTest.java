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
 * HuffmanCode testing class
 *
 * @author Vaastav Arora
 * @version January 9, 2021
 */
@FixMethodOrder(MethodSorters.JVM)
public class HuffmanCodeTest {

    /**
     * Enum describing types of tests
     */
    enum TestType {
        ENCODE, DECODE, BOTH
    }

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

        assertEquals("Ensure that huffmanTree is of type Node !", huffmanTree.getType(), HuffmanCode.Node.class);

        try {
            encoding = HuffmanCode.class.getDeclaredField("encoding");
        } catch (NoSuchFieldException e) {
            System.out.println("Ensure that HuffmanCode has a field HashMap<Character, String> encoding!");
            Assert.fail();
        }

        assertEquals("Ensure that encoding is of type HashMap<Character, String> !", encoding.getType(), HashMap.class);

        try {
            decoding = HuffmanCode.class.getDeclaredField("decoding");
        } catch (NoSuchFieldException e) {
            System.out.println("Ensure that HuffmanCode has a field HashMap<String, Character> decoding!");
            Assert.fail();
        }

        assertEquals("Ensure that text is of type HashMap<String, Character> !", decoding.getType(), HashMap.class);

        try {
            text = HuffmanCode.class.getDeclaredField("text");
        } catch (NoSuchFieldException e) {
            System.out.println("Ensure that HuffmanCode has a field String text!");
            Assert.fail();
        }

        assertEquals("Ensure that text is of type String !", text.getType(), String.class);

    }

    @Test
    public void testClassMethods() {

        Method getHuffmanTree;
        Method getEncoding;
        Method getDecoding;

        Method compress;
        Method expand;

        try {
            getHuffmanTree = HuffmanCode.class.getDeclaredMethod("getHuffmanTree");
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that HuffmanCode has a method getHuffmanTree that is declared public !");
        }

        try {
            getEncoding = HuffmanCode.class.getDeclaredMethod("getEncoding");
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that HuffmanCode has a method getEncoding that is declared public !");
        }

        try {
            getDecoding = HuffmanCode.class.getDeclaredMethod("getDecoding");
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that HuffmanCode has a method getDecoding that is declared public !");
        }

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

    private void treeChecker(HuffmanCodeSolution.Node sol, HuffmanCode.Node test) {

        if(sol == null && test == null) return;

        if(sol == null && test != null)
            Assert.fail(String.format("Huffman Tree does not match!\n" +
                    "Expected node: Null\n" +
                    "Actual node: (ch,%s) (freq, %d) (isLeaf, %d))",
                    test.getCh(), test.getFreq(), test.isLeaf()));

        if(sol != null && test == null)
            Assert.fail(String.format("Huffman Tree does not match!\n" +
                    "Expected node: (ch,%s) (freq, %d) (isLeaf, %d))\n" +
                    "Actual node: Null",
                    sol.getCh(), sol.getFreq(), sol.isLeaf()));

        boolean ch = (sol.getCh() == test.getCh());
        boolean freq = (sol.getFreq() == test.getFreq());
        boolean isLeaf = (sol.isLeaf() == test.isLeaf());

        if(!(ch && freq && isLeaf))
            Assert.fail(String.format("Huffman Tree does not match!\n" +
                "Expected node: (ch,%s) (freq, %d) (isLeaf, %d)\n" +
                "Actual node: (ch,%s) (freq, %d) (isLeaf, %d))",
                sol.getCh(), sol.getFreq(), sol.isLeaf(),
                test.getCh(), test.getFreq(), test.isLeaf()));

        treeChecker(sol.getLeft(), test.getLeft());
        treeChecker(sol.getRight(), test.getRight());

    }

    private void encodingChecker(HashMap<Character, String> sol, HashMap<Character, String> test){

        try{
            for (Character k : test.keySet())
            {
                if (!sol.get(k).equals(test.get(k))) {
                    Assert.fail(String.format(
                            "Encoding Map does not match!\n" +
                            "Expected value %s for key %c\n" +
                            "Actual value %s for key %c",
                            sol.get(k), k, test.get(k), k));
                }
            }
        } catch (NullPointerException np) {
            Assert.fail(
                    "Encoding Map does not match!\n" +
                    "Unexpected key found");
        }

        try {
            for (Character k : sol.keySet())
            {
                if (!test.containsKey(k)) {
                    Assert.fail(String.format(
                            "Encoding Map does not match!\n" +
                            "Expected value %s for key %c\n" +
                            "Actual value of %c: Not found",
                            sol.get(k), k, k));
                }
            }
        } catch (NullPointerException np) {
            Assert.fail(
                    "Encoding Map does not match\n" +
                    "Required key not found");
        }

    }

    private void decodingChecker(HashMap<String, Character> sol, HashMap<String, Character> test){

        try {
            for (String k : test.keySet()) {
                if (!sol.get(k).equals(test.get(k))) {
                    Assert.fail(String.format(
                            "Decoding Map does not match!\n" +
                            "Expected value %c for key %s\n" +
                            "Actual value %c for key %s",
                            sol.get(k), k, test.get(k), k));
                }
            }
        } catch (NullPointerException np) {
            Assert.fail(
                    "Decoding Map does not match!\n" +
                    "Unexpected key found");
        }

        try {
            for (String k : sol.keySet())
            {
                if (!test.containsKey(k)) {
                    Assert.fail(String.format(
                            "Decoding Map does not match!\n" +
                            "Expected value %c for key %d\n" +
                            "Actual value of %d: Not found",
                            sol.get(k), k, k));
                }
            }
        } catch (NullPointerException np) {
            Assert.fail(
                    "Decoding Map does not match\n" +
                    "Required key not found");
        }
    }

    private void tester(String filename, int testCount, TestType type) {

        Scanner testStream = null;
        StringBuilder in = new StringBuilder();
        Random rand = new Random(42);

        try {
            testStream = new Scanner(new File("test/"+filename));
        } catch (IOException e) {
            Assert.fail("Test File opening failed");
        }

        while (testStream.hasNext()){ in.append(testStream.next()); }

        HuffmanCodeSolution sol = new HuffmanCodeSolution(in.toString());
        HuffmanCode test = new HuffmanCode(in.toString());

        treeChecker(sol.getHuffmanTree(), test.getHuffmanTree());


        if(type != TestType.DECODE ) {

            encodingChecker(sol.getEncoding(), test.getEncoding());

            Character[] keys = (Character[]) sol.getEncoding().keySet().toArray(new Character[sol.getEncoding().size()]);

            for(int i = 0; i< testCount; i++) {

                int len = rand.nextInt(testCount)+ 1;
                StringBuilder inst = new StringBuilder();

                for(int j = 0; j<len; j++) { inst.append(keys[rand.nextInt(keys.length)]); }

                assertEquals("Compressed String does not match !", sol.compress(inst.toString()), test.compress(inst.toString()));
            }
        }

        if(type != TestType.ENCODE) {

            decodingChecker(sol.getDecoding(), test.getDecoding());

            String[] keys = (String[]) sol.getDecoding().keySet().toArray(new String[sol.getDecoding().size()]);

            for(int i = 0; i< testCount; i++) {
                int len = rand.nextInt(testCount) + 1;
                StringBuilder inst = new StringBuilder();

                for (int j = 0; j < len; j++) { inst.append(keys[rand.nextInt(keys.length)]); }

                assertEquals("Expanded String does not match !", sol.expand(inst.toString()), test.expand(inst.toString()));
            }
        }


    }


    @Test
    public void test_compress_1() {
        System.out.println("Starting test_compress_1");
        tester("Basic.txt", 1, TestType.ENCODE);
        System.out.println("Ending test_compress_1");
    }

    @Test
    public void test_compress_2() {
        System.out.println("Starting test_compress_2");
        tester("Intermediate.txt", 10, TestType.ENCODE);
        System.out.println("Ending test_compress_2");

    }

    @Test
    public void test_compress_3() {
        System.out.println("Starting test_compress_3");
        tester("Advanced.txt", 100, TestType.ENCODE);
        System.out.println("Ending test_compress_3");
    }

    @Test
    public void test_compress_4() {
        System.out.println("Starting test_compress_4");
        tester("Silmarillion.txt", 1000, TestType.ENCODE);
        System.out.println("Ending test_compress_4");
    }

    @Test
    public void test_compress_fail() {

    }

    @Test
    public void test_expand_1() {
        System.out.println("Starting test_expand_1");
        tester("Basic.txt", 1, TestType.DECODE);
        System.out.println("Ending test_expand_1");
    }

    @Test
    public void test_expand_2() {
        System.out.println("Starting test_expand_2");
        tester("Intermediate.txt", 10, TestType.DECODE);
        System.out.println("Ending test_expand_2");
    }

    @Test
    public void test_expand_3() {
        System.out.println("Starting test_expand_3");
        tester("Advanced.txt", 100, TestType.DECODE);
        System.out.println("Ending test_expand_3");
    }

    @Test
    public void test_expand_4() {
        System.out.println("Starting test_expand_4");
        tester("Silmarillion.txt", 1000, TestType.DECODE);
        System.out.println("Ending test_expand_4");
    }

    @Test
    public void test_expand_fail() {

    }

    @Test
    public void test_combined() {
        System.out.println("Starting test_combined");
        tester("Advanced.txt", 1, TestType.BOTH);
        System.out.println("Ending test_combined");
    }

    @Test
    public void test_fail() {
        System.out.println("Starting test_fail_1");
        tester("Silmarillion.txt", 1, TestType.BOTH);
        System.out.println("Ending test_fail_1");

    }
}
