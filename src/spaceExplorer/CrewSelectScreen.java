package spaceExplorer;

import javax.swing.JFrame;
import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Rectangle;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/** Displays crew select screen.
 * @author Karna Malone
 * @author Daniel Watson
*/
public class CrewSelectScreen {

	private JFrame frmSpaceExplorer;
	private Game gameState;
	private JTextField memberName;
	private int memberType;
	private int crewSlot;
	private int x, y;

	/**
	 * Create crew select screen.
	 * @param incomingState Current game state.
	 * @param crewSlot Crew slot to select for.
	 */
	public CrewSelectScreen(Game incomingState, int crewSlot) {
		gameState = incomingState;
		this.crewSlot = crewSlot;
		this.memberType = getTypeNumber();
		Rectangle dimensions = gameState.getLastWindowPosition();
		this.x = dimensions.x+dimensions.width/2-822/2;
		this.y = dimensions.y+dimensions.height/2-509/2;
		if(this.x < 10) {
			this.x = 10;
		}
		if(this.y < 10) {
			this.y = 10;
		}
		initialize();
		frmSpaceExplorer.setVisible(true);
	}
	
	void closeWindow() {
		frmSpaceExplorer.dispose();
	}
	
	private void finishedWindow() {
		String formattedMemberName = memberName.getText().trim();
		if(formattedMemberName.length() == 0) {
			gameState.showPopUp("Please enter a vaild member name.", frmSpaceExplorer.getBounds());
		}else {
			gameState.crew.addMember(formattedMemberName, memberType, crewSlot);
			gameState.closeCrewSelect(this);
		}
	}
	
	/**
	 * Returns the location and dimensions of the current window
	 * @return Current window position and dimensions
	 */
	public Rectangle getPosition() {
		return frmSpaceExplorer.getBounds();
	}
	
	/**
	 * Get member type number
	 * @return member type number
	 */
	public int getTypeNumber() {
		CrewMember member = gameState.crew.crewMembers.get(crewSlot);
		if(member != null) {
			return member.getTypeNumber();
		}else {
			return 0;
		}
	}
	
	/**
	 * Enable/Disable type select radio buttons
	 * @param buttonType Button member type.
	 * @param button to update.
	 */
	private void updateButtons(int buttonType, JRadioButton button) {
		if(memberType == buttonType) {
			button.setSelected(true);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSpaceExplorer = new JFrame();
		frmSpaceExplorer.setTitle("Space Explorer");
		frmSpaceExplorer.getContentPane().setForeground(new Color(51, 51, 51));
		frmSpaceExplorer.getContentPane().setBackground(new Color(51, 51, 51));
		
		JLabel lblMemberType = new JLabel("Member Type");
		lblMemberType.setForeground(new Color(255, 255, 255));
		lblMemberType.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblEngineer = new JLabel("Engineer");
		lblEngineer.setForeground(new Color(255, 255, 255));
		lblEngineer.setFont(new Font("Arial", Font.BOLD, 16));
		
		JLabel engineerAbility1 = new JLabel("Repair Efficiency: ++");
		engineerAbility1.setFont(new Font("Arial", Font.BOLD, 13));
		engineerAbility1.setForeground(new Color(255, 255, 255));
		
		JLabel lblRobot = new JLabel("Robot");
		lblRobot.setForeground(Color.WHITE);
		lblRobot.setFont(new Font("Arial", Font.BOLD, 16));
		
		JLabel robotAbility1 = new JLabel("Hunger Reduction: +++");
		robotAbility1.setFont(new Font("Arial", Font.BOLD, 13));
		robotAbility1.setForeground(Color.WHITE);
		
		JLabel scavengerAbility2 = new JLabel("Energy: -");
		scavengerAbility2.setFont(new Font("Arial", Font.BOLD, 13));
		scavengerAbility2.setForeground(Color.WHITE);
		
		JLabel lblScavenger = new JLabel("Scavenger");
		lblScavenger.setForeground(Color.WHITE);
		lblScavenger.setFont(new Font("Arial", Font.BOLD, 16));
		
		JLabel scavengerAbility1 = new JLabel("Chance of Finding Part: ++");
		scavengerAbility1.setFont(new Font("Arial", Font.BOLD, 13));
		scavengerAbility1.setForeground(Color.WHITE);
		
		JLabel lblBodyBuilder = new JLabel("Body Builder");
		lblBodyBuilder.setForeground(Color.WHITE);
		lblBodyBuilder.setFont(new Font("Arial", Font.BOLD, 16));
		
		JLabel bodyBuilderAbility1 = new JLabel("Damage Reduction: ++");
		bodyBuilderAbility1.setFont(new Font("Arial", Font.BOLD, 13));
		bodyBuilderAbility1.setForeground(Color.WHITE);
		
		JLabel lblAlien = new JLabel("Alien");
		lblAlien.setForeground(Color.WHITE);
		lblAlien.setFont(new Font("Arial", Font.BOLD, 16));
		
		JLabel alienAbility1 = new JLabel("Energy: +");
		alienAbility1.setFont(new Font("Arial", Font.BOLD, 13));
		alienAbility1.setForeground(Color.WHITE);
		
		JLabel lblCoffeeAddict = new JLabel("Coffee Addict");
		lblCoffeeAddict.setForeground(Color.WHITE);
		lblCoffeeAddict.setFont(new Font("Arial", Font.BOLD, 16));
		
		JLabel coffeeAddictAbility1 = new JLabel("Energy: ++");
		coffeeAddictAbility1.setFont(new Font("Arial", Font.BOLD, 13));
		coffeeAddictAbility1.setForeground(Color.WHITE);
		
		JLabel alienAbility2 = new JLabel("Hunger Reduction: +");
		alienAbility2.setFont(new Font("Arial", Font.BOLD, 13));
		alienAbility2.setForeground(Color.WHITE);
		
		JRadioButton selectEngineer = new JRadioButton("Select");
		selectEngineer.setForeground(Color.WHITE);
		selectEngineer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectEngineer.setSelected(true);
				memberType = 0;
			}
		});
		selectEngineer.setBackground(new Color(255, 255, 255));
		selectEngineer.setFont(new Font("Tahoma", Font.BOLD, 12));
		updateButtons(0, selectEngineer);
		
		JRadioButton selectScientist = new JRadioButton("Select");
		selectScientist.setForeground(Color.WHITE);
		selectScientist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectScientist.setSelected(true);
				memberType = 1;
			}
		});
		selectScientist.setFont(new Font("Tahoma", Font.BOLD, 12));
		selectScientist.setBackground(new Color(255, 255, 255));
		selectScientist.setBackground(Color.WHITE);
		updateButtons(1, selectScientist);
		
		JRadioButton selectScavenger = new JRadioButton("Select");
		selectScavenger.setForeground(Color.WHITE);
		selectScavenger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectScavenger.setSelected(true);
				memberType = 2;
			}
		});
		selectScavenger.setFont(new Font("Tahoma", Font.BOLD, 12));
		selectScavenger.setBackground(new Color(255, 255, 255));
		selectScavenger.setBackground(Color.WHITE);
		updateButtons(2, selectScavenger);
		
		JRadioButton selectStrong = new JRadioButton("Select");
		selectStrong.setForeground(Color.WHITE);
		selectStrong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectStrong.setSelected(true);
				memberType = 3;
			}
		});
		selectStrong.setFont(new Font("Tahoma", Font.BOLD, 12));
		selectStrong.setBackground(new Color(255, 255, 255));
		selectStrong.setBackground(Color.WHITE);
		updateButtons(3, selectStrong);
		
		JRadioButton selectToBeDecided0 = new JRadioButton("Select");
		selectToBeDecided0.setForeground(Color.WHITE);
		selectToBeDecided0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectToBeDecided0.setSelected(true);
				memberType = 4;
			}
		});
		selectToBeDecided0.setFont(new Font("Tahoma", Font.BOLD, 12));
		selectToBeDecided0.setBackground(new Color(255, 255, 255));
		selectToBeDecided0.setBackground(Color.WHITE);
		updateButtons(4, selectToBeDecided0);
		
		JRadioButton selectToBeDecided = new JRadioButton("Select");
		selectToBeDecided.setForeground(Color.WHITE);
		selectToBeDecided.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectToBeDecided.setSelected(true);
				memberType = 5;
			}
		});
		selectToBeDecided.setFont(new Font("Tahoma", Font.BOLD, 12));
		selectToBeDecided.setBackground(new Color(255, 255, 255));
		selectToBeDecided.setBackground(Color.WHITE);
		updateButtons(5, selectToBeDecided);
		
		ButtonGroup group = new ButtonGroup();
		group.add(selectEngineer);
		group.add(selectScientist);
		group.add(selectScavenger);
		group.add(selectStrong);
		group.add(selectToBeDecided0);
		group.add(selectToBeDecided);
		
		JPanel topActionsPanel = new JPanel();
		topActionsPanel.setBackground(new Color(255, 255, 255));
		
		JPanel bottomNavPanel = new JPanel();
		bottomNavPanel.setBackground(new Color(255, 255, 255));
		
		JLabel bodyBuilderAbility2 = new JLabel("Hunger Reduction: -");
		bodyBuilderAbility2.setFont(new Font("Arial", Font.BOLD, 13));
		bodyBuilderAbility2.setForeground(Color.WHITE);
		
		JLabel robotAbility2 = new JLabel("Energy: -");
		robotAbility2.setFont(new Font("Arial", Font.BOLD, 13));
		robotAbility2.setForeground(Color.WHITE);
		
		JLabel engineerAbility2 = new JLabel("Energy: -");
		engineerAbility2.setFont(new Font("Arial", Font.BOLD, 13));
		engineerAbility2.setForeground(Color.WHITE);
		
		JLabel coffeeAddictAbility2 = new JLabel("Damage Reduction: -");
		coffeeAddictAbility2.setFont(new Font("Arial", Font.BOLD, 13));
		coffeeAddictAbility2.setForeground(Color.WHITE);
		GroupLayout groupLayout = new GroupLayout(frmSpaceExplorer.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(topActionsPanel, GroupLayout.DEFAULT_SIZE, 797, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblEngineer)
								.addComponent(lblBodyBuilder)
								.addComponent(selectEngineer)
								.addComponent(selectStrong)
								.addComponent(engineerAbility1)
								.addComponent(bodyBuilderAbility1)
								.addComponent(bodyBuilderAbility2)
								.addComponent(engineerAbility2, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
							.addGap(109)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(robotAbility2, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
								.addComponent(alienAbility2)
								.addComponent(alienAbility1, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
								.addComponent(robotAbility1)
								.addComponent(lblRobot)
								.addComponent(lblAlien)
								.addComponent(selectScientist)
								.addComponent(selectToBeDecided0))
							.addGap(108)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(coffeeAddictAbility2)
								.addComponent(selectToBeDecided)
								.addComponent(selectScavenger)
								.addComponent(lblCoffeeAddict)
								.addComponent(lblScavenger)
								.addComponent(scavengerAbility1)
								.addComponent(scavengerAbility2, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
								.addComponent(coffeeAddictAbility1, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)))
						.addComponent(lblMemberType))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addComponent(bottomNavPanel, GroupLayout.DEFAULT_SIZE, 797, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(topActionsPanel, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addComponent(lblMemberType)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblScavenger)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scavengerAbility1))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblEngineer)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(engineerAbility1))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblRobot)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(robotAbility1)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scavengerAbility2)
						.addComponent(robotAbility2)
						.addComponent(engineerAbility2))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(selectEngineer)
						.addComponent(selectScientist)
						.addComponent(selectScavenger))
					.addGap(35)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblBodyBuilder)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(bodyBuilderAbility1))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblAlien)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(alienAbility1))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCoffeeAddict)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(coffeeAddictAbility1)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(alienAbility2)
						.addComponent(bodyBuilderAbility2)
						.addComponent(coffeeAddictAbility2))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(selectStrong)
						.addComponent(selectToBeDecided0)
						.addComponent(selectToBeDecided))
					.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
					.addComponent(bottomNavPanel, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
		);
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		btnDone.setForeground(new Color(51, 51, 51));
		btnDone.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDone.setBackground(new Color(255, 255, 255));
		GroupLayout gl_bottomNavPanel = new GroupLayout(bottomNavPanel);
		gl_bottomNavPanel.setHorizontalGroup(
			gl_bottomNavPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_bottomNavPanel.createSequentialGroup()
					.addContainerGap(676, Short.MAX_VALUE)
					.addComponent(btnDone)
					.addGap(9))
		);
		gl_bottomNavPanel.setVerticalGroup(
			gl_bottomNavPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_bottomNavPanel.createSequentialGroup()
					.addContainerGap(13, Short.MAX_VALUE)
					.addComponent(btnDone)
					.addGap(9))
		);
		bottomNavPanel.setLayout(gl_bottomNavPanel);
		
		JLabel lblCrewMember = new JLabel("Crew Member #"+(crewSlot+1));
		lblCrewMember.setBackground(new Color(51, 51, 51));
		lblCrewMember.setForeground(new Color(51, 51, 51));
		lblCrewMember.setFont(new Font("Arial", Font.PLAIN, 21));
		
		JLabel lblMemberName = new JLabel("Member Name");
		lblMemberName.setBackground(new Color(51, 51, 51));
		lblMemberName.setForeground(new Color(51, 51, 51));
		lblMemberName.setFont(new Font("Arial", Font.PLAIN, 15));
		
		memberName = new JTextField(gameState.getMemberName(crewSlot));
		memberName.setFont(new Font("Arial", Font.PLAIN, 14));
		memberName.setColumns(10);
		GroupLayout gl_topActionsPanel = new GroupLayout(topActionsPanel);
		gl_topActionsPanel.setHorizontalGroup(
			gl_topActionsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_topActionsPanel.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_topActionsPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCrewMember)
						.addGroup(gl_topActionsPanel.createSequentialGroup()
							.addComponent(lblMemberName)
							.addGap(27)
							.addComponent(memberName, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(350, Short.MAX_VALUE))
		);
		gl_topActionsPanel.setVerticalGroup(
			gl_topActionsPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_topActionsPanel.createSequentialGroup()
					.addContainerGap(22, Short.MAX_VALUE)
					.addComponent(lblCrewMember)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_topActionsPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(memberName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMemberName))
					.addGap(19))
		);
		topActionsPanel.setLayout(gl_topActionsPanel);
		frmSpaceExplorer.getContentPane().setLayout(groupLayout);
		frmSpaceExplorer.setBackground(new Color(51, 51, 51));
		frmSpaceExplorer.setResizable(false);
		frmSpaceExplorer.setBounds(x, y, 822, 509);
		frmSpaceExplorer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
