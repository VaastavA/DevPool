public class Developer extends Employee {


    public Developer(String name, int age){
        super(name,age);
    }
    @Override
    public double getSalary() {
        return 0;
    }
}
