package spaceExplorer;

/** Represents an individual crew member.
 * @author Karna Malone
 * @author Daniel Watson
*/
abstract public class CrewMember {
	private String memberName;
	private double health = 100, food = 100, energy = 100;
	private int actions = 2;
	private boolean hasSpacePlague = false;
	private boolean dead = false;
	
	/** Initializes CrewMember with given name.
	 * @param name The name of the new CrewMember.
	*/
	public CrewMember(String name) {
		memberName = name;
	}
	
	/** Returns a string representation of the crew member
	 * @return Crew member name (type)
	*/
	public String toString() {
		return memberName+" ("+getType()+")";
	}
	
	/** Returns the type of crew member.
	 * @return Crew member type.
	*/
	abstract public String getType();
	
	/** Adds the given amount to the member's health.
	 * @param amount Amount of damage member will take.
	*/
	abstract public void health(double amount);
	
	/** Adds the given amount of food to the crew member.
	 * @param amount The amount of food to add.
	*/
	abstract public void food(double amount);
	
	/** Adds the given amount of energy to the crew member.
	 * @param amount The amount of energy to add.
	*/
	abstract public void energy(double amount);
	
	/** Performs daily actions to member's stats.
	*/
	public void nextDay() {
		food(-25);
		energy(-20);
		addActions(2);
		
		if(hasSpacePlague()) {
			health(-10);
		}
		if(food <= 0) {
			health(-20);
		}
		if(energy <= 0) {
			food(-6);
			health(-6);
		}
	}
	
	/** Returns the type number of crew member.
	 * @return Crew member type number.
	*/
	abstract public int getTypeNumber();
	
	/** Returns the crew member's name.
	 * @return Member name.
	*/
	public String getName() {
		return memberName;
	}
	
	/** Returns whether or not the member has the space plague.
	 * @return Has or has not got the space plague
	*/
	public boolean hasSpacePlague() {
		return hasSpacePlague;
	}
	
	/** Sets wether or not the member has the space plague
	 * @param value Has or has not got the space plague
	*/
	public void setHasSpacePlague(boolean value) {
		hasSpacePlague = value;
	}
	
	/** Returns the crew member's health.
	 * @return Member health level
	*/
	public double getHealth() {
		return health;
	}
	
	/** Returns the crew member's food level.
	 * @return Member food level
	*/
	public double getFood() {
		return food;
	}
	
	/** Returns the crew member's energy level.
	 * @return Member energy level
	*/
	public double getEnergy() {
		return energy;
	}
	
	/** Returns the number of actions the crew member has left.
	 * @return Actions left
	*/
	public int getActions() {
		return actions;
	}
	
	/** Adds the given amount to the member's health.
	 * @param amount Amount of damage member will take.
	*/
	public void setHealth(double amount) {
		if (this.health+amount <= 0){
			this.health = 0;
			this.dead = true;
		}else if(this.health+amount >= 100) {
			this.health = 100;
		}else {
			this.health += amount;
		}
	}
	
	/** Adds the given amount of food to the crew member.
	 * @param amount The amount of food to add.
	*/
	public void setFood(double amount) {
		if (this.food+amount <= 0){
			this.food = 0;
		}else if(this.food+amount >= 100) {
			this.food = 100;
		}else {
			this.food += amount;
		}
	}
	
	/** Adds the given amount of energy to the crew member.
	 * @param amount The amount of energy to add.
	*/
	public void setEnergy(double amount) {
		if (this.energy+amount <= 0){
			this.energy = 0;
		}else if(this.energy+amount >= 100) {
			this.energy = 100;
		}else {
			this.energy += amount;
		}
	}
	
	/** Adds the given amount of actions to the crew member.
	 * @param amount The amount of actions to add.
	*/
	public void addActions(int amount) {
		if (this.actions+amount >= 2){
			this.actions = 2;
		}else{
			this.actions += amount;
		}
	}
	
	/** Uses the given amount of actions.
	 * @param amount The amount of actions to be used.
	*/
	public void useActions(int amount) {
		if (this.actions-amount <= 0){
			this.actions = 0;
		}else{
			this.actions -= amount;
		}
	}
	
	/** Checks if the crew member is dead
	 * @return True if crew member is dead
	*/
	public boolean isDead() {
		return this.dead;
	}
	
	/** Sets crew member status to dead
	*/
	public void setDead() {
		this.dead = true;
	}
}
