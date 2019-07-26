public abstract class Employee {

    private String name;
    private int age;
    private static int employeeID;
    private double salary;

    public Employee(String name, int age){

        this.name = name;
        this.age = age;
    }

    public abstract double getSalary();


}
