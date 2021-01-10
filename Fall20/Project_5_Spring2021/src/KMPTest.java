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
            text = HuffmanCode.class.getDeclaredField("text");
        } catch (NoSuchFieldException e) {
            System.out.println("Ensure that KMP has a field char text!");
            Assert.fail();
        }

        assertEquals("Ensure that text is of type String !", text.getType(), String.class);

        try {
            pattern = HuffmanCode.class.getDeclaredField("pattern");
        } catch (NoSuchFieldException e) {
            System.out.println("Ensure that KMP has a field char pattern!");
            Assert.fail();
        }

        assertEquals("Ensure that pattern is of type String !", pattern.getType(), String.class);

        try {
            lps = HuffmanCode.class.getDeclaredField("lps");
        } catch (NoSuchFieldException e) {
            System.out.println("Ensure that KMP has a field char lps !");
            Assert.fail();
        }

        assertEquals("Ensure that ch is of type char !", lps.getType(), Integer[].class);
    }

    @Test
    public void testClassMethods() {

        Method computeLPSArray;
        Method KMPSearch;
        Method getLps;

        try {
            computeLPSArray = HuffmanCode.class.getDeclaredMethod("computeLPSArray");
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that KMP has a method computeLPSArray that is declared public !");
        }

        try {
            KMPSearch = HuffmanCode.class.getDeclaredMethod("computeLPSArray");
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that KMP has a method KMPSearch that is declared public !");
        }

        try {
            getLps = HuffmanCode.class.getDeclaredMethod("computeLPSArray");
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that KMP has a method getLps that is declared public !");
        }

    }

    
}
