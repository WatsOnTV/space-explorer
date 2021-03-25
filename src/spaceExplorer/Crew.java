package spaceExplorer;
import spaceExplorer.MemberTypes.*;

import java.util.ArrayList;


/** Represents the crew members aboard the ship.
 * @author Karna Malone
 * @author Daniel Watson
*/
public class Crew {
	ArrayList<CrewMember> crewMembers = new ArrayList<CrewMember>(4);
	private float money = 50;
	
	public Crew() {
		for (int i = 0; i < 4; i++) {
			crewMembers.add(null);
		}
	}
	
	/** Adds a member to the crew.
	 * @param name Name of new crew member
	 * @param type Number representation of the crew member type (0 to 5)
	 * @param memberSlot Member slot (0 to 3)
	*/
	public void addMember(String name, int type, int memberSlot) {
		CrewMember newMember = null;
		switch(type) {
			case 0:
				newMember = new EngineerMember(name);
				break;
			case 1:
				newMember = new RobotMember(name);
				break;
			case 2:
				newMember = new ScavengerMember(name);
				break;
			case 3:
				newMember = new BodyBuilderMember(name);
				break;
			case 4:
				newMember = new AlienMember(name);
				break;
			case 5:
				newMember = new CoffeeAddictMember(name);
				break;
		}
		crewMembers.set(memberSlot, newMember);
	}
	
	/** Returns the amount of money the crew has.
	 * @return amount of money the crew has.
	*/
	public double getMoney() {
		return money;
	}
	
	/** Adds the given amount to the crew's money.
	 * @param amount The CrewMember to be added to the crew.
	*/
	public void addMoney(double amount) {
		money += amount;
	}
	
	/** Returns a CrewMember[] list of current crew members
	 * @param slots Number of slots to include in list.
	 * @return Crew member list
	*/
	public CrewMember[] getCrewList(int slots) {
		ArrayList<CrewMember> members = new ArrayList<CrewMember>();  
		for(int i=0;i<slots;i++) {
			if (crewMembers.get(i) != null && !crewMembers.get(i).isDead() ) {
				members.add(crewMembers.get(i));
			}
		}
		CrewMember[] result = members.toArray(new CrewMember[members.size()]);
		return result;
	}
	
	/** Returns the current number of crew alive
	 * @param slots Slots in use.
	 * @return Crew Size.
	*/
	public int crewSize(int slots) {
		int count = 0;
		for(int i=0;i<slots;i++) {
			if (crewMembers.get(i) != null) {
				count++;
			}
		}
		return count;
	}

	/** Removes any dead crew members from the array
	*/
	public void removeDead() {
		for(int i=0;i<4;i++) {
			if (crewMembers.get(i) != null && crewMembers.get(i).isDead()) {
				crewMembers.set(i, null);
			}
		}
	}
}
