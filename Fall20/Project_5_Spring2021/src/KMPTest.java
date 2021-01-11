import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.JVM)
public class KMPTest {

    @Test
    public void testClassProperty() {

        Constructor<KMP> constructor = null;
        Field text = null;
        Field pattern = null;
        Field lps = null;

        try {
            constructor = KMP.class.getConstructor(String.class, String.class);
        } catch (NoSuchMethodException e) {
            System.out.println("Ensure that KMP has the described constructor!");
            Assert.fail();
        }

        try {
            text = KMP.class.getDeclaredField("text");
        } catch (NoSuchFieldException e) {
            System.out.println("Ensure that KMP has a field char text!");
            Assert.fail();
        }

        assertEquals("Ensure that text is of type String !", text.getType(), String.class);

        try {
            pattern = KMP.class.getDeclaredField("pattern");
        } catch (NoSuchFieldException e) {
            System.out.println("Ensure that KMP has a field char pattern!");
            Assert.fail();
        }

        assertEquals("Ensure that pattern is of type String !", pattern.getType(), String.class);

        try {
            lps = KMP.class.getDeclaredField("lps");
        } catch (NoSuchFieldException e) {
            System.out.println("Ensure that KMP has a field char lps !");
            Assert.fail();
        }

        assertEquals("Ensure that lps is of type char !", lps.getType(), int[].class);
    }

    @Test
    public void testClassMethods() {

        Method computeLPSArray;
        Method KMPSearch;
        Method getLps;

        try {
            computeLPSArray = KMP.class.getDeclaredMethod("computeLPSArray");
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that KMP has a method computeLPSArray that is declared public !");
        }

        try {
            KMPSearch = KMP.class.getDeclaredMethod("computeLPSArray");
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that KMP has a method KMPSearch that is declared public !");
        }

        try {
            getLps = KMP.class.getDeclaredMethod("computeLPSArray");
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that KMP has a method getLps that is declared public !");
        }

    }

    private void tester(String text, String pattern) {

        System.out.println(pattern);

        KMP test = new KMP(text, pattern);
        KMP sol = new KMP(text, pattern);

        test.computeLPSArray();
        sol.computeLPSArray();

        assertEquals(" Ensure LPS array is of correct length !", sol.getLps().length, test.getLps().length);

        for(int i=0;i<sol.getLps().length;i++) assertEquals(String.format(" Mismatch at lps index %d ", i), sol.getLps()[i], test.getLps()[i]);

        assertEquals(" Wrong KMP search results !", sol.KMPSearch(), test.KMPSearch());

    }

    @Test
    public void KMP_test_1() {
        System.out.println("Starting KMP test 1");
        tester("aaaaaaaa","aa");
        System.out.println("Ending KMP test 1");
    }

    @Test
    public void KMP_test_2() {
        System.out.println("Starting KMP test 2");
        tester("abacababaxababx","abab");
        tester( "cbabcbabc", "bab");
        tester("abcdef","g");
        tester("xbcdabcababcdabxababcdabcabx", "abcdabcab");
        tester("foooazbarbazquuxzotarbazbarba", "barbaz");
        System.out.println("Ending KMP test 2");
    }

    @Test
    public void KMP_test_3() {
        System.out.println("Starting KMP test 3");

        Scanner testStream = null;
        StringBuilder in = new StringBuilder();
        Random rand = new Random(42);

        try {
            testStream = new Scanner(new File("test/"+"Silmarillion.txt"));
        } catch (IOException e) {
            Assert.fail("Test File opening failed");
        }

        while (testStream.hasNext()){ in.append(testStream.next()); }

        for(int i = 0; i<100; i++) {
            int len = rand.nextInt(in.length()/10);
            int l = rand.nextInt(in.length() - len) ;

            tester(in.toString(), in.toString().substring(l, l + len));
        }

        System.out.println("Ending KMP test 3");
    }

    @Test
    public void KMP_test_4() {
        System.out.println("Starting KMP test 4");

        Scanner testStream = null;
        StringBuilder in = new StringBuilder();
        Random rand = new Random(42);

        try {
            testStream = new Scanner(new File("test/"+"Advanced.txt"));
        } catch (IOException e) {
            Assert.fail("Test File opening failed");
        }

        while (testStream.hasNext()){ in.append(testStream.next()); }

        for(int i = 0; i<100; i++) {
            int len = rand.nextInt(in.length()/10);
            int l = rand.nextInt(in.length() - len) ;

            tester(in.toString(), in.toString().substring(l, l + len));
        }

        System.out.println("Ending KMP test 4");
    }


}
