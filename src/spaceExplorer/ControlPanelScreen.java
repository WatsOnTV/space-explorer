package spaceExplorer;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.Dimension;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JProgressBar;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/** Displays control panel screen.
 * @author Karna Malone
 * @author Daniel Watson
*/
public class ControlPanelScreen {

	private JFrame frmSpaceExplorer;
	private Game gameState;
	private MemberPanelLayout memberPanelLayout0, memberPanelLayout1, memberPanelLayout2, memberPanelLayout3;
	private int x, y;
	private int currPanel;

	/**
	 * Create control panel window.
	 * @param incomingState Current Game State.
	 */
	public ControlPanelScreen(Game incomingState) {
		gameState = incomingState;
		Rectangle dimensions = gameState.getLastWindowPosition();
		this.x = dimensions.x+dimensions.width/2-1096/2;
		this.y = dimensions.y+dimensions.height/2-697/2;
		if(this.x < 10) {
			this.x = 10;
		}
		if(this.y < 10) {
			this.y = 10;
		}
		
		initialize();
		frmSpaceExplorer.setVisible(true);
		
		if(gameState.getCrewDied() > 0) {
			gameState.showPopUp(gameState.getCrewDied()+" of your crew members have died.", frmSpaceExplorer.getBounds());
		}
		gameState.resetDeathToll();
	}
	
	void closeWindow() {
		frmSpaceExplorer.dispose();
	}
	
	private void finishedWindow() {
		gameState.closeControlPanel(this);
	}
	
	private void openShop() {
		gameState.openSpaceShop(this);
	}
	
	private void openPlanetSearch() {
		gameState.openPlanetSearch(this);
	}
	
	private void openPlanetSelect() {
		gameState.openPlanetSelect(this);
	}
	
	private void openRepairShields() {
		gameState.openRepairShields(this);
	}
	
	/**
	 * Returns the location and dimensions of the current window
	 * @return Current window position and dimensions
	 */
	public Rectangle getPosition() {
		return frmSpaceExplorer.getBounds();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSpaceExplorer = new JFrame();
		frmSpaceExplorer.setMinimumSize(new Dimension(1096, 697));
		frmSpaceExplorer.getContentPane().setFont(new Font("SansSerif", Font.PLAIN, 12));
		frmSpaceExplorer.getContentPane().setBackground(new Color(51, 51, 51));
		frmSpaceExplorer.setTitle("Space Explorer");
		frmSpaceExplorer.setBackground(new Color(51, 51, 51));
		frmSpaceExplorer.setBounds(x, y, 1096, 697);
		frmSpaceExplorer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(192, 192, 192));
		
		JLabel lblCurrentPlanet = new JLabel("Current Planet:");
		lblCurrentPlanet.setFont(new Font("Courier New", Font.PLAIN, 11));
		lblCurrentPlanet.setForeground(new Color(255, 255, 255));
		lblCurrentPlanet.setBackground(new Color(255, 255, 255));
		
		JLabel lblPlanetName = new JLabel(gameState.ship.getCurrentPlanetName());
		lblPlanetName.setForeground(new Color(255, 255, 255));
		lblPlanetName.setFont(new Font("URW Gothic L", Font.BOLD, 30));
		
		JLabel lblDay = new JLabel("Day: "+gameState.getDay()+" / "+gameState.getMaxDays());
		lblDay.setFont(new Font("Courier New", Font.BOLD, 14));
		lblDay.setForeground(new Color(255, 255, 255));
		
		JButton btnNextDay = new JButton("Go To Next Day");
		btnNextDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameState.setLastWindowPosition(frmSpaceExplorer.getBounds());
				gameState.nextDay();
				finishedWindow();
			}
		});
		btnNextDay.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNextDay.setBackground(Color.WHITE);
		
		JLabel lblShip = new JLabel("Ship:");
		lblShip.setForeground(Color.WHITE);
		lblShip.setFont(new Font("Courier New", Font.PLAIN, 11));
		lblShip.setBackground(Color.WHITE);
		
		JLabel lblShipName = new JLabel(gameState.ship.getName());
		lblShipName.setForeground(Color.WHITE);
		lblShipName.setFont(new Font("URW Gothic L", Font.BOLD, 30));
		
		JLabel lblParts = new JLabel("Parts: "+gameState.ship.getNumPartsFound()+" / "+gameState.ship.getNumPartsNeeded());
		lblParts.setForeground(Color.WHITE);
		lblParts.setFont(new Font("Courier New", Font.BOLD, 14));
		
		JPanel gameActionsWrap = new JPanel();
		gameActionsWrap.setBackground(new Color(255, 255, 255));
		
		JLabel lblMoney = new JLabel("Money: $"+gameState.crew.getMoney());
		lblMoney.setForeground(Color.WHITE);
		lblMoney.setFont(new Font("Courier New", Font.BOLD, 14));
		
		JButton btnVisitShop = new JButton("Visit Nearest Space Outpost");
		btnVisitShop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openShop();
			}
		});
		btnVisitShop.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnVisitShop.setBackground(Color.WHITE);
		
		JPanel shieldPanel = new JPanel();
		shieldPanel.setForeground(new Color(51, 51, 51));
		shieldPanel.setBackground(new Color(255, 255, 255));
		GridBagLayout gbl_shieldPanel = new GridBagLayout();
		gbl_shieldPanel.columnWidths = new int[]{0, 0, 0};
		gbl_shieldPanel.rowHeights = new int[]{0, 0};
		gbl_shieldPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_shieldPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		shieldPanel.setLayout(gbl_shieldPanel);
		
		JLabel lblShieldCapacity = new JLabel(" Shield Capacity ");
		lblShieldCapacity.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblShieldCapacity.setForeground(new Color(51, 51, 51));
		GridBagConstraints gbc_lblShieldCapacity = new GridBagConstraints();
		gbc_lblShieldCapacity.insets = new Insets(0, 0, 0, 5);
		gbc_lblShieldCapacity.gridx = 0;
		gbc_lblShieldCapacity.gridy = 0;
		shieldPanel.add(lblShieldCapacity, gbc_lblShieldCapacity);
		
		JProgressBar shieldCapacityBar = new JProgressBar();
		shieldCapacityBar.setBackground(new Color(102, 102, 102));
		shieldCapacityBar.setValue(gameState.ship.getShields());
		GridBagConstraints gbc_shieldCapacityBar = new GridBagConstraints();
		gbc_shieldCapacityBar.fill = GridBagConstraints.BOTH;
		gbc_shieldCapacityBar.gridx = 1;
		gbc_shieldCapacityBar.gridy = 0;
		shieldPanel.add(shieldCapacityBar, gbc_shieldCapacityBar);
		
		JButton btnRepairShields = new JButton("Repair Shields");
		btnRepairShields.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openRepairShields();
			}
		});
		
		JButton btnPilotShip = new JButton("Pilot Ship To New Planet");
		btnPilotShip.setForeground(new Color(255, 255, 255));
		btnPilotShip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(gameState.crew.crewSize(gameState.getCrewSize()) > 1) {
					openPlanetSelect();
					lblPlanetName.setText(gameState.ship.getCurrentPlanetName());
				}else {
					gameState.showPopUp("You do not have enough crew to go anywhere!\nYou find yourself trapped.", frmSpaceExplorer.getBounds());
				}
				
			}
		});
		btnPilotShip.setBackground(new Color(51, 153, 102));
		
		JButton btnSearchPlanet = new JButton("Search Planet");
		btnSearchPlanet.setForeground(new Color(255, 255, 255));
		btnSearchPlanet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPlanetSearch();
			}
		});
		btnSearchPlanet.setBackground(new Color(0, 102, 153));
		GroupLayout gl_gameActionsWrap = new GroupLayout(gameActionsWrap);
		gl_gameActionsWrap.setHorizontalGroup(
			gl_gameActionsWrap.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_gameActionsWrap.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_gameActionsWrap.createParallelGroup(Alignment.LEADING)
						.addComponent(btnRepairShields, GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
						.addComponent(shieldPanel, GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_gameActionsWrap.createSequentialGroup()
							.addComponent(btnSearchPlanet, GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
							.addGap(16)
							.addComponent(btnPilotShip, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_gameActionsWrap.setVerticalGroup(
			gl_gameActionsWrap.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_gameActionsWrap.createSequentialGroup()
					.addContainerGap()
					.addComponent(shieldPanel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnRepairShields, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_gameActionsWrap.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSearchPlanet, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
						.addComponent(btnPilotShip, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
					.addGap(12))
		);
		gameActionsWrap.setLayout(gl_gameActionsWrap);	
		
		if(gameState.getCrewSize() > 0 && gameState.crew.crewMembers.get(0) != null) {
			JPanel memberPanel = new JPanel();
			memberPanelLayout0 = new MemberPanelLayout(tabbedPane, memberPanel, gameState, 0, frmSpaceExplorer.getBounds());
		}

		if(gameState.getCrewSize() > 1 && gameState.crew.crewMembers.get(1) != null) {
			JPanel memberPanel1 = new JPanel();
			memberPanelLayout1 = new MemberPanelLayout(tabbedPane, memberPanel1, gameState, 1, frmSpaceExplorer.getBounds());
		}

		if(gameState.getCrewSize() > 2 && gameState.crew.crewMembers.get(2) != null) {
			JPanel memberPanel2 = new JPanel();
			memberPanelLayout2 = new MemberPanelLayout(tabbedPane, memberPanel2, gameState, 2, frmSpaceExplorer.getBounds());
		}

		if(gameState.getCrewSize() > 3 && gameState.crew.crewMembers.get(3) != null) {
			JPanel memberPanel3 = new JPanel();
			memberPanelLayout3 = new MemberPanelLayout(tabbedPane, memberPanel3, gameState, 3, frmSpaceExplorer.getBounds());
		}
		
		ChangeListener tabChanged = new ChangeListener() {
	      public void stateChanged(ChangeEvent changeEvent) {
	    	  if(memberPanelLayout0 != null) {memberPanelLayout0.updatePanel(gameState); currPanel = 0;}
	    	  if(memberPanelLayout1 != null) {memberPanelLayout1.updatePanel(gameState); currPanel = 1;}
	    	  if(memberPanelLayout2 != null) {memberPanelLayout2.updatePanel(gameState); currPanel = 2;}
	    	  if(memberPanelLayout3 != null) {memberPanelLayout3.updatePanel(gameState); currPanel = 3;}
	      }
	    };
	    tabbedPane.addChangeListener(tabChanged);
	    
		JLabel lblPartsList = new JLabel("<html>"+gameState.ship.getStringPartsNeeded("<br>")+"<html>");
		lblPartsList.setFont(new Font("Courier New", Font.BOLD, 13));
		lblPartsList.setForeground(new Color(255, 255, 255));
		
		GroupLayout groupLayout = new GroupLayout(frmSpaceExplorer.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPartsList)
								.addComponent(gameActionsWrap, GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnVisitShop, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 243, Short.MAX_VALUE)
									.addComponent(btnNextDay, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)))
							.addGap(21))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(lblCurrentPlanet))
						.addComponent(lblPlanetName)
						.addComponent(lblDay)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(lblShip, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblShipName)
						.addComponent(lblParts)
						.addComponent(lblMoney, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 409, GroupLayout.PREFERRED_SIZE)
					.addGap(22))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(17)
							.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(56)
							.addComponent(lblCurrentPlanet)
							.addGap(1)
							.addComponent(lblPlanetName)
							.addGap(11)
							.addComponent(lblDay)
							.addGap(41)
							.addComponent(lblShip)
							.addGap(1)
							.addComponent(lblShipName)
							.addGap(11)
							.addComponent(lblParts)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblPartsList)
							.addPreferredGap(ComponentPlacement.RELATED, 131, Short.MAX_VALUE)
							.addComponent(lblMoney)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnVisitShop, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNextDay, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(gameActionsWrap, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)))
					.addGap(24))
		);
		frmSpaceExplorer.getContentPane().setLayout(groupLayout);
	}
}
