package spaceExplorer;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.Rectangle;
import javax.swing.JTextField;

/** Displays setup screen.
 * @author Karna Malone
 * @author Daniel Watson
*/
public class SetupScreen {

	private JFrame frame;
	private Game gameState;
	private JTextField shipNameInput;
	private int x = 100, y = 100;

	/**
	 * Create setup screen.
	 * @param incomingState Current game state.
	 */
	public SetupScreen(Game incomingState) {
		gameState = incomingState;
		
		Rectangle dimensions = gameState.getLastWindowPosition();
		if(dimensions != null) {
			this.x = dimensions.x+dimensions.width/2-530/2;
			this.y = dimensions.y+dimensions.height/2-646/2;
			if(this.x < 10) {
				this.x = 10;
			}
			if(this.y < 10) {
				this.y = 10;
			}
		}
		
		initialize();
		frame.setVisible(true);
	}
	
	void closeWindow() {
		frame.dispose();
	}
	
	private void finishedWindow() {
		String formattedShipName = shipNameInput.getText().trim();
		if(formattedShipName.length() == 0) {
			gameState.showPopUp("Please enter a vaild ship name.", frame.getBounds());
		}else if(!gameState.crewSlotsFull()) {
			gameState.showPopUp("You need to assign a crew member to each open slot.", frame.getBounds());
		}else {
			setShipName(formattedShipName);
			for(int i = 0; i < gameState.crew.crewMembers.size(); i++) {
	    		if(i >= gameState.getCrewSize()) {
	    			gameState.crew.crewMembers.set(i, null);
	    		}
			}
			gameState.closeSetupScreen(this);
		}
	}
	
	/**
	 * Returns the location and dimensions of the current window
	 * @return Current window position and dimensions
	 */
	public Rectangle getPosition() {
		return frame.getBounds();
	}
	
	/**
	 * Opens crew select screen.
	 * @param crewNumber Crew slot to access
	 */
	private void crewSelect(int crewNumber) {
		setShipName(shipNameInput.getText());
		gameState.openCrewSelect(this, crewNumber);
	}
	
	/**
	 * Returns ship name.
	 * @return Ship Name.
	 */
	private String getShipName() {
		String shipName = gameState.ship.getName();
		if(shipName == null) {
			shipName = "Enter ship name";
		}
		return shipName;
	}
	
	/**
	 * Sets the ship's name.
	 */
	private void setShipName(String shipName) {
		gameState.ship.setName(shipName);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Space Explorer");
		frame.getContentPane().setForeground(Color.DARK_GRAY);
		frame.setBackground(Color.WHITE);
		frame.getContentPane().setBackground(new Color(51, 51, 51));
		
		JLabel lblCrew = new JLabel("Crew");
		lblCrew.setForeground(new Color(255, 255, 255));
		lblCrew.setFont(new Font("Dialog", Font.PLAIN, 21));
		
		JLabel slot0Label = new JLabel("1.");
		slot0Label.setForeground(new Color(255, 255, 255));
		
		JLabel slot1Label = new JLabel("2.");
		slot1Label.setForeground(new Color(255, 255, 255));
		
		JLabel slot2Label = new JLabel("3.");
		slot2Label.setForeground(new Color(255, 255, 255));
		
		JLabel slot3Label = new JLabel("4.");
		slot3Label.setForeground(new Color(255, 255, 255));
		
		JLabel crewSlot0 = new JLabel(gameState.getMemberInfo(0));
		crewSlot0.setForeground(new Color(255, 255, 255));
		crewSlot0.setFont(new Font("Arial", Font.PLAIN, 16));
		
		JLabel crewSlot1 = new JLabel(gameState.getMemberInfo(1));
		crewSlot1.setForeground(new Color(255, 255, 255));
		crewSlot1.setFont(new Font("Arial", Font.PLAIN, 16));
		
		JLabel crewSlot2 = new JLabel(gameState.getMemberInfo(2));
		crewSlot2.setEnabled(false);
		crewSlot2.setForeground(new Color(255, 255, 255));
		crewSlot2.setFont(new Font("Arial", Font.PLAIN, 16));
		
		JLabel crewSlot3 = new JLabel(gameState.getMemberInfo(3));
		crewSlot3.setForeground(new Color(255, 255, 255));
		crewSlot3.setFont(new Font("Arial", Font.PLAIN, 16));
		
		JButton btnSelectCrewMember0 = new JButton("Select Crew Member");
		btnSelectCrewMember0.setBackground(Color.WHITE);
		btnSelectCrewMember0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				crewSelect(0);
			}
		});
		btnSelectCrewMember0.setFont(new Font("Arial", Font.PLAIN, 13));
		btnSelectCrewMember0.setForeground(new Color(51, 51, 51));
		
		JButton btnSelectCrewMember1 = new JButton("Select Crew Member");
		btnSelectCrewMember1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crewSelect(1);
			}
		});
		btnSelectCrewMember1.setFont(new Font("Arial", Font.PLAIN, 13));
		btnSelectCrewMember1.setForeground(new Color(51, 51, 51));
		btnSelectCrewMember1.setBackground(Color.WHITE);
		
		JButton btnSelectCrewMember2 = new JButton("Select Crew Member");
		btnSelectCrewMember2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crewSelect(2);
			}
		});
		btnSelectCrewMember2.setFont(new Font("Arial", Font.PLAIN, 13));
		btnSelectCrewMember2.setForeground(new Color(51, 51, 51));
		btnSelectCrewMember2.setBackground(Color.WHITE);
		
		JButton btnSelectCrewMember3 = new JButton("Select Crew Member");
		btnSelectCrewMember3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crewSelect(3);
			}
		});
		btnSelectCrewMember3.setFont(new Font("Arial", Font.PLAIN, 13));
		btnSelectCrewMember3.setForeground(new Color(51, 51, 51));
		btnSelectCrewMember3.setBackground(Color.WHITE);
		// Enable/disable slots
		btnSelectCrewMember3.setEnabled(false);
		crewSlot3.setEnabled(false);
		slot3Label.setEnabled(false);
		btnSelectCrewMember2.setEnabled(false);
		slot2Label.setEnabled(false);
		switch(gameState.getCrewSize()) {
		  case 4:
			  btnSelectCrewMember3.setEnabled(true);
			  crewSlot3.setEnabled(true);
			  slot3Label.setEnabled(true);
		  case 3:
			  btnSelectCrewMember2.setEnabled(true);
			  crewSlot2.setEnabled(true);
			  slot2Label.setEnabled(true);
			  break;
		}
		
		JPanel setupPanel = new JPanel();
		setupPanel.setBackground(new Color(255, 255, 255));
		
		JPanel bottomNavPanel = new JPanel();
		bottomNavPanel.setBackground(new Color(255, 255, 255));
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCrew)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(slot1Label)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(crewSlot1)
							.addGap(18)
							.addComponent(btnSelectCrewMember1))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(slot3Label)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(crewSlot3)
							.addGap(18)
							.addComponent(btnSelectCrewMember3))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(slot2Label)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(crewSlot2)
							.addGap(18)
							.addComponent(btnSelectCrewMember2))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(slot0Label)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(crewSlot0)
							.addGap(18)
							.addComponent(btnSelectCrewMember0)))
					.addContainerGap(237, Short.MAX_VALUE))
				.addComponent(setupPanel, GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
				.addComponent(bottomNavPanel, GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(setupPanel, GroupLayout.PREFERRED_SIZE, 314, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
					.addComponent(lblCrew)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(slot0Label)
						.addComponent(crewSlot0)
						.addComponent(btnSelectCrewMember0))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(slot1Label)
						.addComponent(crewSlot1)
						.addComponent(btnSelectCrewMember1))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(slot2Label)
						.addComponent(crewSlot2)
						.addComponent(btnSelectCrewMember2))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(slot3Label)
						.addComponent(crewSlot3)
						.addComponent(btnSelectCrewMember3))
					.addGap(26)
					.addComponent(bottomNavPanel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
		);
		
		JButton startGameBTN = new JButton("Start Game");
		startGameBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				finishedWindow();
			}
		});
		startGameBTN.setForeground(new Color(51, 51, 51));
		startGameBTN.setFont(new Font("Tahoma", Font.PLAIN, 13));
		startGameBTN.setBackground(new Color(255, 255, 255));
		GroupLayout gl_bottomNavPanel = new GroupLayout(bottomNavPanel);
		gl_bottomNavPanel.setHorizontalGroup(
			gl_bottomNavPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_bottomNavPanel.createSequentialGroup()
					.addGap(419)
					.addComponent(startGameBTN)
					.addContainerGap(8, Short.MAX_VALUE))
		);
		gl_bottomNavPanel.setVerticalGroup(
			gl_bottomNavPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_bottomNavPanel.createSequentialGroup()
					.addContainerGap(12, Short.MAX_VALUE)
					.addComponent(startGameBTN)
					.addGap(8))
		);
		bottomNavPanel.setLayout(gl_bottomNavPanel);
		
		JLabel lblSetup = new JLabel("Game Setup");
		lblSetup.setBackground(new Color(51, 51, 51));
		lblSetup.setForeground(new Color(51, 51, 51));
		lblSetup.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblDaysInSpace = new JLabel("Days in Space");
		lblDaysInSpace.setForeground(new Color(51, 51, 51));
		lblDaysInSpace.setFont(new Font("Arial", Font.PLAIN, 16));
		
		JLabel lblNumberOfCrew = new JLabel("Number of Crew Members");
		lblNumberOfCrew.setForeground(new Color(51, 51, 51));
		lblNumberOfCrew.setFont(new Font("Arial", Font.PLAIN, 16));
		
		JSpinner selectDays = new JSpinner();
		selectDays.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				gameState.setMaxDays((int) selectDays.getValue());
			}
		});
		selectDays.setFont(new Font("Arial", Font.PLAIN, 15));
		selectDays.setForeground(new Color(255, 255, 255));
		selectDays.setBackground(new Color(255, 255, 255));
		selectDays.setModel(new SpinnerNumberModel(gameState.getMaxDays(), 3, 10, 1));
		
		JSpinner selectCrewSize = new JSpinner();
		selectCrewSize.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				gameState.setCrewSize((int) selectCrewSize.getValue());
				
				// Enable/disable slots
				btnSelectCrewMember3.setEnabled(false);
				crewSlot3.setEnabled(false);
				slot3Label.setEnabled(false);
				btnSelectCrewMember2.setEnabled(false);
				crewSlot2.setEnabled(false);
				slot2Label.setEnabled(false);
				switch(gameState.getCrewSize()) {
				  case 4:
					  btnSelectCrewMember3.setEnabled(true);
					  crewSlot3.setEnabled(true);
					  slot3Label.setEnabled(true);
				  case 3:
					  btnSelectCrewMember2.setEnabled(true);
					  crewSlot2.setEnabled(true);
					  slot2Label.setEnabled(true);
					  break;
				}
			}
		});
		selectCrewSize.setFont(new Font("Arial", Font.PLAIN, 15));
		selectCrewSize.setForeground(Color.WHITE);
		selectCrewSize.setBackground(Color.WHITE);
		selectCrewSize.setModel(new SpinnerNumberModel(gameState.getCrewSize(), 2, 4, 1));
		
		JLabel lblShipName = new JLabel("Ship Name");
		lblShipName.setForeground(new Color(51, 51, 51));
		lblShipName.setFont(new Font("Arial", Font.PLAIN, 16));
		
		shipNameInput = new JTextField((String) null);
		shipNameInput.setFont(new Font("Arial", Font.PLAIN, 15));
		shipNameInput.setToolTipText("Ship Name");
		shipNameInput.setText(getShipName());
		shipNameInput.setColumns(10);
		
		JLabel lblSpaceExplorer = new JLabel("Space Explorer");
		lblSpaceExplorer.setForeground(new Color(51, 51, 51));
		lblSpaceExplorer.setFont(new Font("URW Gothic L", Font.BOLD, 30));
		GroupLayout gl_setupPanel = new GroupLayout(setupPanel);
		gl_setupPanel.setHorizontalGroup(
			gl_setupPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_setupPanel.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_setupPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_setupPanel.createSequentialGroup()
							.addComponent(lblShipName)
							.addGap(28)
							.addComponent(shipNameInput, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_setupPanel.createSequentialGroup()
							.addComponent(lblNumberOfCrew)
							.addGap(28)
							.addComponent(selectCrewSize, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_setupPanel.createSequentialGroup()
							.addComponent(lblDaysInSpace)
							.addGap(109)
							.addComponent(selectDays, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblSetup)
						.addComponent(lblSpaceExplorer))
					.addContainerGap(149, Short.MAX_VALUE))
		);
		gl_setupPanel.setVerticalGroup(
			gl_setupPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_setupPanel.createSequentialGroup()
					.addGap(38)
					.addComponent(lblSpaceExplorer)
					.addGap(22)
					.addComponent(lblSetup)
					.addGap(31)
					.addGroup(gl_setupPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDaysInSpace)
						.addGroup(gl_setupPanel.createSequentialGroup()
							.addGap(1)
							.addComponent(selectDays, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_setupPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNumberOfCrew)
						.addComponent(selectCrewSize, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_setupPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblShipName)
						.addComponent(shipNameInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(42, Short.MAX_VALUE))
		);
		setupPanel.setLayout(gl_setupPanel);
		frame.getContentPane().setLayout(groupLayout);
		frame.setBounds(x, y, 530, 646);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
