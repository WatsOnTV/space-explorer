package spaceExplorer.MemberTypes;

import spaceExplorer.CrewMember;

/** Represents an Engineer member type, which extends the CrewMember abstract class.
 * @author Karna Malone
 * @author Daniel Watson
*/
public class EngineerMember extends CrewMember{
	private String type = "Engineer";
	private int typeNum = 0;
	
	/** Initializes CrewMember with given name.
	 * @param name The name of the new CrewMember.
	*/
	public EngineerMember(String name) {
		super(name);
	}
	
	/** Returns the type of crew member.
	 * @return Crew member type.
	*/
	public String getType() {
		return type;
	}
	
	/** Returns the type number of crew member.
	 * @return Crew member type number.
	*/
	public int getTypeNumber() {
		return typeNum;
	}
	
	/** Adds the given amount to the member's health.
	 * @param amount Amount of damage member will take.
	*/
	public void health(double amount) {
		setHealth(amount);
	}
	
	/** Adds the given amount of food to the crew member.
	 * @param amount The amount of food to add.
	*/
	public void food(double amount) {
		setFood(amount);
	}
	
	/** Adds the given amount of energy to the crew member.
	 * @param amount The amount of energy to add.
	*/
	public void energy(double amount) {
		setEnergy(amount*1.3);
	}
}
