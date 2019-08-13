/**
 * A simple class representing a Manager
 * employee at a company
 * Extends the Employee class
 *
 * @author Vaastav Arora, arora74@purdue.edu
 * @version August 13th 2019
 */
public class Manager extends Employee {

    private Developer[] developers; //Developers under the Manager
    private int teamSize; //Size of team
    Team team; //Team type
    int developerCounter; //Number of developers added so far

    /**
     * Simple constructor for Manager class
     * @param name Name of Manager
     * @param age Age of Manager
     * @param teamSize Size of team under manager
     * @param t Team type of Manager
     */
    public Manager(String name, int age, int teamSize, Team t){

        super(name,age);
        this.teamSize = teamSize;
        this.developers = new Developer[teamSize];
        this.team = t;
        developerCounter = 0; //Zero developers at initialization
    }

    /**
     * Method to get salary of Manager
     * @return salary of Manager
     */
    @Override
    public double getSalary() {
        return 70000 + (2000*teamSize);
    }

    /**
     * Method to print Manager information
     */
    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Position: Manager");
        System.out.println("Developer: ");
        for( Developer d: developers){
            d.printInfo();
        }

    }

    /**
     * Method to return array of Developers
     * @return Array of Developers
     */
    public Developer[] getDevelopers() {
        return developers;
    }

    /**
     * Method to set array of developers
     * @param developers Array to be set
     */
    public void setDevelopers(Developer[] developers) {
        this.developers = developers;
    }

    /**
     * Method to get number of developers added so far
     * @return Number of developers added so far
     */
    public int getDeveloperCounter() {
        return developerCounter;
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
     * Method to get team type of Manager
     * @return Team type of manager
     */
    public Team getTeam() {
        return team;
    }

    /**
     * Method to set team type of Manager
     * @param team Team to be set
     */
    public void setTeam(Team team) {
        this.team = team;
    }

    /**
     * Method to add developer to the developers array
     * @param d Developer to be added
     * @return True if developer was added successfully
     */
    public boolean addDeveloper(Developer d){

        if(developerCounter == teamSize) return false;
        developers[developerCounter] = d;
        developerCounter++;
        return true;
    }
}
