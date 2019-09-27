import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class EmployeeTest {

    @Test
    public void testClassProperty() {
        Constructor constructor = null;
        Field name = null;
        Field age = null;
        Field ID = null;
        Field employeeID = null;
        Field salary = null;

        int typeInt = 0;
        String typeString = "";

        try {
            constructor = Employee.class.getConstructor(String.class,int.class);

        } catch (NoSuchMethodException e) {
            System.out.println("Ensure that Employee has a constructor of type Employee(String, int)!");
            Assert.fail();
        }

        try {
             name = Employee.class.getDeclaredField("name");

             if(name.getModifiers() != Modifier.PRIVATE){
                 throw new NoSuchFieldException();
             }

        } catch (NoSuchFieldException e) {
            System.out.println("Ensure that Employee has a field String name and it's private!");
            Assert.fail();
        }

        assertTrue("Ensure that name is of type String !",String.class.equals(name.getType()));


        try {
             age = Employee.class.getDeclaredField("age");

            if(age.getModifiers() != Modifier.PRIVATE) {
                throw new NoSuchFieldException();
            }

        } catch (NoSuchFieldException e) {
            System.out.println("Ensure that Employee has a field int age and it's private!");
            Assert.fail();
        }

        assertTrue("Ensure that age is of type int !", int.class.equals(age.getType()));

        try {
             ID = Employee.class.getDeclaredField("ID");

            if(ID.getModifiers() != Modifier.PRIVATE){
                throw new NoSuchFieldException();
            }

        } catch (NoSuchFieldException e) {
            System.out.println("Ensure that Employee has a field int ID and it's private!");
            Assert.fail();
        }

        assertTrue("Ensure that ID is of type int !", int.class.equals(ID.getType()));

        try {
             employeeID = Employee.class.getDeclaredField("employeeIDs");

            if(employeeID.getModifiers() != Modifier.){
                System.out.println('p');
                throw new NoSuchFieldException();
            }

            if (employeeID.getModifiers() != Modifier.STATIC){
                System.out.println("static");
                throw new NoSuchFieldException();
            }

        } catch (NoSuchFieldException e) {
            System.out.println("Ensure that Employee has a field int employeeIDs and it's private and static!");
            Assert.fail();
        }

        assertTrue("Ensure that employeeIDs is of type int !", int.class.equals(employeeID.getType()));



        try {
            salary = Employee.class.getDeclaredField("salary");

            if(employeeID.getModifiers() != Modifier.PRIVATE){
                throw new NoSuchFieldException();
            }

        } catch (NoSuchFieldException e) {
            System.out.println("Ensure that has a field int salary and it's private!");
            Assert.fail();
        }

        assertTrue("Ensure that salary is of type int !", int.class.equals(salary.getType()));

    }

    @Test
    public void testClassMethods() {

        Method getName;
        Method setName;
        Method getAge;
        Method setAge;
        Method getID;
        Method setID;
        Method printInfo;

        try {
             getName = Employee.class.getDeclaredMethod("getName");
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that Employee has a method getName() that is declared public !");
        }

        try {
             setName = Employee.class.getDeclaredMethod("setName", String.class);
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that Employee has a method setName(int Name) that takes an \'String\' argument and is declared public !");
        }

        try {
            getAge = Employee.class.getDeclaredMethod("getName");
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that Employee has a method getAge() that is declared public !");
        }

        try {
             setAge = Employee.class.getDeclaredMethod("setAge", int.class);
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that Employee has a method setAge(int age) that takes an \'int\' argument and is declared public !");
        }

        try {
             getID = Employee.class.getDeclaredMethod("getID");
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that Employee has a method getID() that is declared public !");
        }

        try {
             setID = Employee.class.getDeclaredMethod("setID", int.class);
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that Employee has a method setID(int ID) that takes an \'int\' argument and is declared public !");
        }

        try {
             printInfo = Employee.class.getDeclaredMethod("printInfo");
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that Employee has a method printInfo that is declared public !");
        }
    }

    @Test
    public void testAbstract(){

        Method salary;

        try{
            salary = Employee.class.getDeclaredMethod("getSalary");

            if(salary.getModifiers() != Modifier.ABSTRACT){
                throw new NoSuchMethodException();
            }
        } catch (NoSuchMethodException e){
            Assert.fail("Ensure that Employee has a method getSalary() that is declared abstract and public !");
        }
    }

    @Test
    public void testPrintInfo(){

    }

    private void setEmployeeId(){

        Field employeeID = null;

        try{
            employeeID = Employee.class.getDeclaredField("employeeIDs");
        }catch (NoSuchFieldException e){
            System.out.println("Ensure that Employee has a field int employeeIDs and it's private and static!");
            Assert.fail();
        }

        try {
            employeeID.setAccessible(true);
            employeeID.setInt(null, 0);
        }catch (IllegalAccessException e){
            System.out.println("Failure in test case");
            Assert.fail();
        }
    }

    @Test
    public void testEmployee(){

        setEmployeeId();

        Employee e = new Employee("Dick Roman",74) {
            @Override
            public double getSalary() {
                return 1687990;
            }
        };

        assertEquals("Ensure name is set correctly and getName() returns correct value!",e.getName(),"Dick Roman");
        e.setName("Dick Chaney");
        assertEquals("Ensure setName() works as described!",e.getName(),"Dick Chaney");

        assertEquals("Ensure age is set correctly and getAge() returns correct value!",e.getAge(),74);
        e.setAge(95);
        assertEquals("Ensure setAge() works as described!",e.getAge(),95);

        assertEquals("Ensure ID is set correctly and getID() returns correct value!",e.getID(),0);
        e.setID(2003);
        assertEquals("Ensure setID() works as intended!",e.getID(),2003);


        Employee ee = new Employee("Obama",30) {
            @Override
            public double getSalary() {
                return 0;
            }
        };

        assertEquals("Ensure employeeID is different for every new employee (1 greater than previous employeeID)!",ee.getID(),1);
    }
}