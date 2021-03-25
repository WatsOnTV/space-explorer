package spaceExplorer;

import java.util.Random;
import javax.swing.UIManager;

import java.util.ArrayList;
import java.awt.Rectangle;

/** Represents an active game instance and manages essential game classes.
 * @author Karna Malone
 * @author Daniel Watson
*/
public class Game {
	private int day = 1;
	private int maxDays = 3;
	private int crewSize = 2;
	private int score = 0;
	private int diedOvernight = 0;
	private Rectangle lastWindowPosition = null;
	public Crew crew = new Crew();
	public Ship ship = new Ship();
	
	/**
     * Purchases the given item.
     * @param type Food/Meds The type of item being purchased
     * @param item The item to be purchased
     */
    public void purchaseItem(String type, Consumable item) {
    	if(crew.getMoney() >= item.getPrice()) {
    		crew.addMoney(-item.getPrice());
        	if(type == "Food") {
        		ship.addFood(item);
        	}else {
        		ship.addMedical(item);
        	}
        	score += 5;
    	}else {
    		showPopUp("You don't have enough money to buy '"+item.getName()+"'", lastWindowPosition);
    	}
    }
	
	/**
     * Returns the max number of days the game will last.
     * @return Max days in game
     */
    public int getMaxDays() {
    	return maxDays;
    }
    
    /**
     * Sets the number of days the game will last.
     * @param maxDays Max days in game
     */
    public void setMaxDays(int maxDays) {
    	this.maxDays = maxDays;
    }
    
    /**
     * Returns the current game day.
     * @return Current day
     */
    public int getDay() {
    	return day;
    }
    
    /** A given member consumes a given item
     * @param type food or medical.
	 * @param name food/medical item to be consumed.
	 * @param member The member to consume the item.
	*/
	public void consume(String type, String name, CrewMember member) {
		ArrayList<Consumable> from = null;
		Consumable consumable = null;
		if(type == "food") {
			from = ship.getFoodItems();
		}else {
			from = ship.getMedicalItems();
		}
		for(int i = 0; i < from.size(); i++) {
    		Consumable item = from.get(i);
    		if(item.getName() == name) {
    			consumable = item;
    			if(type == "food") {
    				ship.removeFood(item);
    			}else {
    				ship.removeMedical(item);
    			}
    			break;
    		}
		}
		if(consumable.getName() == "Space Plague Ointment") {
			member.setHasSpacePlague(false);
		}
		member.food((int) consumable.getFeeds());
		member.health((int) consumable.getHeals());
	}
	
	/**
	 * Returns member details in given crew slot.
	 * @param memberSlot Member Position.
	 * @return Member details.
	 */
	public String getMemberInfo(int memberSlot) {
		CrewMember member = crew.crewMembers.get(memberSlot);
		if(member != null) {
			String name = member.getName();
			String type = member.getType();
			return name+" ("+type+")";
		}else {
			return "Empty";
		}
	}
	
	/**
	 * Returns member name in given crew slot.
	 * @param memberSlot Member Position.
	 * @return Member Name.
	 */
	public String getMemberName(int memberSlot) {
		CrewMember member = crew.crewMembers.get(memberSlot);
		if(member != null) {
			String name = member.getName();
			return name;
		}else {
			return "Enter Member Name";
		}
	}
    
    /**
     * Increases day by one.
     */
    void nextDay() {
    	computeDailyScore();
    	day++;
    	for(int i = 0; i < crew.crewMembers.size(); i++) {
    		CrewMember crewMember = crew.crewMembers.get(i);
    		if(crewMember != null && i < crewSize) {
    			crewMember.nextDay();
    		}
		}
    	calculateOvernightDeaths();
    	if (crew.crewSize(crewSize) == 0){
    		openGameOver("<html><div style='text-align:center;'>All the crew members abord "+ship.getName()+" have died.<br>You lasted "+day+"/"+maxDays+" days and found "+ship.getNumPartsFound()+"/"+ship.getNumPartsNeeded()+" parts.</div></html>");
    	}else if(day > maxDays) {
    		openGameOver("You ran out of time. You found "+ship.getNumPartsFound()+"/"+ship.getNumPartsNeeded()+" of "+ship.getName()+"'s parts in "+day+"/"+maxDays+" days.");
    	}else {
    		randomEvent();
    	}
    }
    
    /**
     * Searches the current planet.
     * @param member Crew member to search the planet.
     */
    void searchPlanet(CrewMember member) {
    	member.useActions(1);
    	Random rand = new Random(); 
    	String description = null, moneyFound = "";
    	ArrayList<Item> items = ship.getCurrentPlanet().getContainedItems();
    	if(items.size() == 0) {
    		description = "Scans indicate there are no more items to be discovered on this planet.";
    	}else {
    		if(rand.nextInt(6) == 0) {
    			description = "Unfortunately, the search came up empty. However, scans indicate items still on the surface.";
    		}else {
    			Item found = null;
        		if(rand.nextInt(2) == 0) {
        			moneyFound = " "+member.getName()+" also found $25.";
        			crew.addMoney(25);
        			score += 5;
            	}
        		if(member.getType() == "Scavenger") {
        			Item shipPart = null;
        			for(Item item : items) {
        				if(item instanceof ShipPart) {
        					shipPart = item;
        				}
        			}
        			if(shipPart != null && rand.nextInt(2) == 0) {
        				found = shipPart;
        			}
        		}
        		if(found == null) {
        			found = items.get(rand.nextInt(items.size()));
        		}
        		if(found instanceof ShipPart) {
        			ShipPart part = (ShipPart) found;
        			ship.partFound();
        			ship.partsNeeded.remove(part);
        			ship.getCurrentPlanet().removeItem(found);
        			description = "Hurrah! The search discovered one of the lost ship parts. '"+part.getName()+"' was found and has now been reapplied to the ship."+moneyFound;
        			score += 50;
        		}else {
        			Consumable consumable = (Consumable) found;
    	    		if(consumable.getHeals() == 0) {
    	    			ship.foodItems.add(consumable);
    	    		}else {
    	    			ship.medicalItems.add(consumable);
    	    		}
    	    		ship.getCurrentPlanet().removeItem(found);
    	    		description = "The search resulted in a '"+consumable.getName()+"' item being discovered. This item has been added to your inventory."+moneyFound;
    	    		score += 5;
        		}
    		}
    	}
		new EventDialogScreen("Planet Search Report", description, this);
    }
    
    /**
     * Repair the ships shields.
     * @param member Crew member to repair the shields.
     */
    public void repairShields(CrewMember member) {
    	member.useActions(1);
		double amount = ship.getShields() + 25;
		score += 15;
		if(member.getType() == "Engineer") {
			amount = ship.getShields() + 40;
			score += 10;
		}
		ship.setShields(amount);
		openControlPanel();
    }
    
    /**
     * Computes daily score and adds it to the total score.
     */
    public void computeDailyScore() {
    	int dayScore = 0;
    	CrewMember[] crewList = crew.getCrewList(getCrewSize());
    	
    	//Each alive crew member / days left
    	dayScore += (crewList.length*50)*(day/maxDays);
    	
    	for(int i=0; i < crewList.length; i++) {
    		if(crewList[i].getHealth() > 0) {
    			dayScore += 10*(crewList[i].getHealth()/100);
    			dayScore += 5*(crewList[i].getActions()/2);
    		}
    	}
    	
    	score += dayScore;
    }
    
    /**
	 * Returns the location and dimensions of the last window
	 * @return Last window position and dimensions
	 */
    public Rectangle getLastWindowPosition() {
    	return lastWindowPosition;
    }
    
    /**
	 * Sets the location and dimensions of the current window
	 * @param position Current window position and dimensions
	 */
    public void setLastWindowPosition(Rectangle position) {
    	lastWindowPosition = position;
    }
    
    /**
     * Triggers 1 of 3 random in game events.
     */
    private void randomEvent() {
		Random rand = new Random();
		switch(rand.nextInt(3)) {
		    case 0:
		  		if(ship.foodItems.size() > 0 || ship.medicalItems.size() > 0) {
		  			alienPirateEvent();
			  		break;
		  		}
		  	case 1:
		  		newDayEvent();
		  		break;
		  	case 2:
		  		spacePlagueEvent();
		  		break;
		  	
		}
    }
    
    /**
     * Triggers Alien Pirate event.
     */
    private void alienPirateEvent() {
    	Item stolenItem = null;
    	Random rand = new Random();
    	Random randomItem = new Random();
    	int bound = 2;
    	if(ship.medicalItems.size() <= 0) {
    		bound = 1;
    	}
		switch(rand.nextInt(bound)) {
		  	case 0:
		  		if(ship.foodItems.size() > 0) {
		  			stolenItem = ship.foodItems.get(randomItem.nextInt(ship.foodItems.size()));
					ship.foodItems.remove(stolenItem);
			  		break;
		  		}
		  	case 1:
				stolenItem = ship.medicalItems.get(randomItem.nextInt(ship.medicalItems.size()));
				ship.medicalItems.remove(stolenItem);
		  		break;
		}
    	
    	String title = "Alien Pirates";
		String description = "While your crew was asleep, alien pirates boarded the ship and made away with one of the items from the ship's cargo. The item they stole was '"+stolenItem+"'.";
		new EventDialogScreen(title, description, this);
    }
    
    /**
     * Triggers Space Plague event.
     */
    private void spacePlagueEvent() {
    	int infectionPosibility = 1;
    	Random rand = new Random();
    	for(int i = 0; i < crew.crewMembers.size(); i++) {
    		CrewMember crewMember = crew.crewMembers.get(i);
    		int chance = rand.nextInt(infectionPosibility);
    		if(chance == 0 && crewMember != null && !crewMember.hasSpacePlague()) {
    			infectionPosibility = infectionPosibility * 4;
    			crewMember.setHasSpacePlague(true);
    		}
		}
    	String title = "Space Plague";
		String description = "Some of your crew are beginning to express symptoms of the space plague. The space plague will do damage to these crew members over time and can only be cured by applying 'Space Plague Ointment'.";
		new EventDialogScreen(title, description, this);
    }
    
    /**
     * Triggers Asteroid Belt event.
     */
    private void asteroidBeltEvent() {
    	ship.setShields(ship.getShields() - 35);
    	double crewDamage = 20*((100-ship.getShields())/100) + 15;
    	for(int i = 0; i < crew.crewMembers.size(); i++) {
    		CrewMember crewMember = crew.crewMembers.get(i);
    		if(crewMember != null && i < crewSize) {
    			crewMember.health(-crewDamage);
    		}
		}
    	
    	calculateOvernightDeaths();
    	if (crew.crewSize(crewSize) == 0){
    		openGameOver("<html><div style='text-align:center;'>All the crew members abord "+ship.getName()+" have died.<br>You lasted "+day+"/"+maxDays+" days and found "+ship.getNumPartsFound()+"/"+ship.getNumPartsNeeded()+" parts.</div></html>");
    	}else if(ship.getShields() <= 0) {
    		openGameOver("<html><div style='text-align:center;'>"+ship.getName()+" took heavy damage from an asteroid strike. The shields couldn't take the impact and the ship exploded.<br>You lasted "+day+"/"+maxDays+" days and found "+ship.getNumPartsFound()+"/"+ship.getNumPartsNeeded()+" parts.</div></html>");
    	}else {
    		String title = "Asteroid Belt";
    		String description = "While en route to "+ship.getCurrentPlanetName()+", the pilots fell asleep at the wheel. The ship ploughed through an asteroid belt. This caused considerable damage to the ship's shields and injured those aboard.";
    		new EventDialogScreen(title, description, this);
    	}
    }
    
    /**
     * Greets user to new day.
     */
    private void newDayEvent() {
		String title = "A New Day";
		String description = "Your crew wakes up to a new day. Nothing eventful happened overnight.";
		new EventDialogScreen(title, description, this);
    }
    
    /**
     * Greets user to new day.
     */
    private void newPlanetEvent() {
		String title = "Welcome to "+ship.getCurrentPlanetName();
		String description = "Your ship arives unharmed at the next planet in the solar system, "+ship.getCurrentPlanetName()+".";
		new EventDialogScreen(title, description, this);
    }
    
    /**
     * Pilots ship to a the next planet in the solar system with the given pilots.
     * @param member2 
     * @param pilot The member to pilot
     * @param coPilot The member to co-pilot
     */
    void nextPlanet(CrewMember member1, CrewMember member2) {
    	member1.useActions(1);
    	member2.useActions(1);
    	ship.gotoNextPlanet();
    	score += 30;
    	
    	Random rand = new Random();
    	switch(rand.nextInt(2)) {
		  	case 0:
		  		newPlanetEvent();
		  		break;
		  	case 1:
		  		asteroidBeltEvent();
		  		break;
		}
    }
    
    /**
     * Returns the numbers of members in the crew.
     * @return Crew size
     */
    public int getCrewSize() {
    	return crewSize;
    }
    
    /**
     * Sets the number of members in the crew.
     * @param crewSize Size of the crew
     */
    public void setCrewSize(int crewSize) {
    	this.crewSize = crewSize;
    }
    
    /**
	 * Returns a true/false value depending on whether all open crew slots are full
	 * @return Boolean answer
	 */
	public boolean crewSlotsFull() {
		boolean result = true;
		for(int i = 0; i < getCrewSize(); i++) {
			if(crew.crewMembers.get(i) == null) {
				result = false;
				break;
			}
		}
		return result;
	}
	
	/**
	 * Calculates the number of crew members who died overnight
	 */
	public void calculateOvernightDeaths() {
		int before = crew.crewSize(crewSize);
		crew.removeDead();
		int after = crew.crewSize(crewSize);
		diedOvernight = before-after;
	}
	
	/**
	 * Returns the number of crew who died overnight
	 * @return number of crew who died
	 */
	public int getCrewDied() {
		return diedOvernight;
	}
	
	/**
	 * Resets the overnight death toll
	 */
	public void resetDeathToll() {
		diedOvernight = 0;
	}
	
	/**
     * Returns the current game score.
     * @return Game Score
     */
	public int getScore() {
		return score;
	}
	
    /**
     * Open Setup Screen.
     */
    private void launchSetupScreen() {
		new SetupScreen(this);
	}
	
    /**
     * Close Setup Screen.
     * @param setupWindow The setup window to be closed.
     */
	public void closeSetupScreen(SetupScreen setupWindow) {
		lastWindowPosition = setupWindow.getPosition();
		setupWindow.closeWindow();
		ship.generateSolarSystem(maxDays);
		openControlPanel();
	}
	
	/**
     * Open Crew select Screen.
     * @param setupWindow The setup window to be closed.
     * @param crewNumber Crew slot number.
     */
	public void openCrewSelect(SetupScreen setupWindow, int crewNumber) {
		lastWindowPosition = setupWindow.getPosition();
		setupWindow.closeWindow();
		new CrewSelectScreen(this, crewNumber);
	}
	
	/**
     * Close Crew select Screen.
     * @param crewSelectWindow The crew select window to be closed.
     */
	public void closeCrewSelect(CrewSelectScreen crewSelectWindow) {
		lastWindowPosition = crewSelectWindow.getPosition();
		crewSelectWindow.closeWindow();
		launchSetupScreen();
	}
	
	/**
     * Open Control panel Screen.
     */
	public void openControlPanel() {
		new ControlPanelScreen(this);
	}
	
	/**
     * Close Control panel Screen.
     * @param controlPanelWindow The control panel window to be closed.
     */
	public void closeControlPanel(ControlPanelScreen controlPanelWindow) {
		lastWindowPosition = controlPanelWindow.getPosition();
		controlPanelWindow.closeWindow();
	}
	
	/**
     * Close Event dialog Screen.
     * @param eventDialog The event dialog window to be closed.
     */
	public void closeEventDialog(EventDialogScreen eventDialog) {
		lastWindowPosition = eventDialog.getPosition();
		eventDialog.closeWindow();
		if(ship.getPartsNeeded().size() == 0) {
			score += ((200)*(1-day/maxDays))+250;
			openYouWin();
		}else {
			openControlPanel();
		}
		
	}
	
	/**
     * Open Space Shop Screen.
     * @param controlPanelWindow The control panel window to be closed.
     */
	public void openSpaceShop(ControlPanelScreen controlPanelWindow) {
		lastWindowPosition = controlPanelWindow.getPosition();
		controlPanelWindow.closeWindow();
		new SpaceShopScreen(this);
	}
	
	/**
     * Close Space Shop Screen.
     * @param spaceShop The Shop window to be closed.
     */
	public void closeSpaceShop(SpaceShopScreen spaceShop) {
		lastWindowPosition = spaceShop.getPosition();
		spaceShop.closeWindow();
		openControlPanel();
	}
	
	/**
     * Open Planet Search Screen.
     * @param controlPanelWindow The control panel window to be closed.
     */
	public void openPlanetSearch(ControlPanelScreen controlPanelWindow) {
		lastWindowPosition = controlPanelWindow.getPosition();
		controlPanelWindow.closeWindow();
		new SearchPlanetScreen(this);
	}
	
	/**
     * Open Planet Select Screen.
     * @param controlPanelWindow The control panel window to be closed.
     */
	public void openPlanetSelect(ControlPanelScreen controlPanelWindow) {
		lastWindowPosition = controlPanelWindow.getPosition();
		controlPanelWindow.closeWindow();
		new PlanetSelect(this);
	}
	
	/**
     * Close Planet Select Screen.
     * @param planetSelect The planet select window to be closed.
     */
	public void closePlanetSelect(PlanetSelect planetSelect) {
		lastWindowPosition = planetSelect.getPosition();
		planetSelect.closeWindow();
		openControlPanel();
	}
	
	/**
     * Close Planet Search Screen.
     * @param planetSearch The planet search window to be closed.
     */
	public void closePlanetSearch(SearchPlanetScreen planetSearch) {
		lastWindowPosition = planetSearch.getPosition();
		planetSearch.closeWindow();
		openControlPanel();
	}
	
	/**
     * Open Repair Shields Screen.
     * @param controlPanelWindow The control panel window to be closed.
     */
	public void openRepairShields(ControlPanelScreen controlPanelWindow) {
		lastWindowPosition = controlPanelWindow.getPosition();
		controlPanelWindow.closeWindow();
		new RepairShieldsScreen(this);
	}
	
	/**
     * Open You Win Screen.
     */
	public void openYouWin() {
		new YouWinScreen(this);
	}
	
	/**
     * Open Game Over Screen.
     * @param message Game over message
     */
	public void openGameOver(String message) {
		new GameOverScreen(this, message);
	}
	
	/**
     * Close Repair Shields Screen.
     * @param repairShields The repair shields window to be closed.
     */
	public void closeRepairShields(RepairShieldsScreen repairShields) {
		lastWindowPosition = repairShields.getPosition();
		repairShields.closeWindow();
		openControlPanel();
	}
	
	/**
     * Open popup with dialog.
     * @param text Dialog for the popup
     * @param dimensions Size and location of previous window.
     */
	public void showPopUp(String text, Rectangle dimensions) {
		new PopUpMessage(text, dimensions);
	}
	
	/**
     * Starts the game in GUI mode.
     */
	public static void guiSetup() {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		Game game = new Game();
		game.launchSetupScreen();
	}
	
	public static void main(String[] args) {
		guiSetup();
	}
		
}
