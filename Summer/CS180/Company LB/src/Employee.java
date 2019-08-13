/**
 * A simple abstract class representing
 * Employees at a company
 *
 * @author Vaastav Arora, arora74@purdue.edu
 * @version August 13th 2019
 */
public abstract class Employee {

    private String name; //Name of Employee
    private int age; //Age of Employee
    private int ID; //ID
    private static int employeeIDs;
    private double salary;

    /**
     * Basic Constructor for the Employee class
     * @param name Name of the employee
     * @param age Age of the employee
     */
    public Employee(String name, int age){

        this.name = name;
        this.age = age;
        this.ID = employeeIDs++; //Assign unique ID to each employee
    }

    /**
     * Method to get salary of employee
     * @return salary of employee
     */
    public abstract double getSalary();

    /**
     * Method to print employee information
     */
    public void printInfo() {
        System.out.println("Employee Number #"+ID);
        System.out.println("Name: "+name);
        System.out.println("Age: "+age);
    }


    /**
     * Method to get name of employee
     * @return Name of empoyee
     */
    public String getName() {
        return name;
    }

    /**
     * Method to set name of employee
     * @param name Name to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method to get age of employee
     * @return Age of employee
     */
    public int getAge() {
        return age;
    }

    /**
     * Method to set age of employee
     * @param age Age to be set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Method to get ID of employee
     * @return ID of employee
     */
    public int getID() {
        return ID;
    }

    /**
     * Method to set ID of employee
     * @param ID ID to be set
     */
    public void setID(int ID) {
        this.ID = ID;
    }
}
