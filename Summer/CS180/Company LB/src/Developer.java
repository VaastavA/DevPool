/**
 * A simple class representing a Developer
 * employee at a company
 * Extends the Employee class
 *
 * @author Vaastav Arora, arora74@purdue.edu
 * @version August 13th 2019
 */
public class Developer extends Employee {

    Team team; //Team type of Developer

    /**
     * Simple constructor for Developer class
     * @param name Name of Developer
     * @param age Age of Developer
     * @param t Team type of Developer
     */
    public Developer(String name, int age, Team t){

        super(name,age);
        this.team = t;
    }

    /**
     * Method to get Team type of Developer
     * @return
     */
    public Team getTeam() {
        return team;
    }

    /**
     * Method to set Team type of Developer
     * @param team Team to be set
     */
    public void setTeam(Team team) {
        this.team = team;
    }

    /**
     * Method to get salary of Developer
     * @return
     */
    @Override
    public double getSalary() {
        return 50000;
    }

    /**
     * Method to print Developer info
     */
    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Position: Developer");
    }
}
