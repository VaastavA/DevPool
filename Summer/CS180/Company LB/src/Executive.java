/**
 * A simple class representing an Executive
 * employee at a company
 * Extends the Employee class
 *
 * @author Vaastav Arora, arora74@purdue.edu
 * @version August 13th 2019
 */
public class Executive extends Employee {

    private Manager[] managers; //Managers under Executive
    private int teamSize; //Size of team
    int managerCounter; //Number of Managers added so far

    /**
     * Simple constructor for Executive class
     * @param name Name of Executive
     * @param age Age of Executive
     * @param teamSize Size of team under Executive
     */
    public Executive(String name, int age, int teamSize){
        super(name,age);
        this.teamSize = teamSize;
        managerCounter = 0;
    }

    /**
     * Method to get salary of Executive
     * @return
     */
    @Override
    public double getSalary() {
        return 100000+(5000*teamSize);
    }

    /**
     * Method to print Executive info
     */
    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Position: Executive");
        System.out.println("Managers: ");
        for( Manager d: managers){
            d.printInfo();
        }
    }

    /**
     * Method to return array of managers
     * @return Array of managers
     */
    public Manager[] getManagers() {
        return managers;
    }

    /**
     * Method to set array of managers
     * @param managers Array to be set
     */
    public void setManagers(Manager[] managers) {
        this.managers = managers;
    }

    /**
     * Method to get size of team under Manager
     * @return Size of team
     */
    public int getTeamSize() {
        return teamSize;
    }

    /**
     * Method to set size of team under Manager
     * @param teamSize Size to be set
     */
    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }

    /**
     * Method to get number of managers added so far
     * @return Number of managers added so far
     */
    public int getManagerCounter() {
        return managerCounter;
    }

    /**
     * Method to add manager to the managers array
     * @param m Manager to be added
     * @return True if manager was added successfully
     */
    public boolean addManager(Manager m) {

        if(managerCounter == teamSize) return false;
        managers[managerCounter] = m;
        managerCounter++;
        return true;
    }
}
