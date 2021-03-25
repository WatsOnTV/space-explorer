package spaceExplorer;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

/** Displays planet select screen.
 * @author Karna Malone
 * @author Daniel Watson
*/
public class PlanetSelect {

	private JFrame frmSpaceExplorer;
	private Game gameState;
	private int x,y;

	/**
	 * Create planet select window.
	 * @param incomingState current game state.
	 */
	public PlanetSelect(Game incomingState) {
		this.gameState = incomingState;
		Rectangle dimensions = gameState.getLastWindowPosition();
		this.x = dimensions.x+dimensions.width/2-547/2;
		this.y = dimensions.y+dimensions.height/2-235/2;
		initialize();
		frmSpaceExplorer.setVisible(true);
	}

	/**
	 * Returns the location and dimensions of the current window
	 * @return Current window position and dimensions
	 */
	public Rectangle getPosition() {
		return frmSpaceExplorer.getBounds();
	}
	
	public void closeWindow() {
		frmSpaceExplorer.dispose();
	}
	
	private void finishedWindow() {
		gameState.closePlanetSelect(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSpaceExplorer = new JFrame();
		frmSpaceExplorer.setResizable(false);
		frmSpaceExplorer.getContentPane().setBackground(new Color(255, 255, 255));
		frmSpaceExplorer.getContentPane().setForeground(new Color(255, 255, 255));
		frmSpaceExplorer.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmSpaceExplorer.setTitle("Space Explorer");
		frmSpaceExplorer.setBounds(x, y, 547, 235);
		frmSpaceExplorer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblSearchPlanet = new JLabel("Select Planet");
		lblSearchPlanet.setForeground(new Color(51, 51, 51));
		lblSearchPlanet.setFont(new Font("URW Gothic L", Font.BOLD, 20));
		
		JPanel memberSelectPanel = new JPanel();
		memberSelectPanel.setBackground(new Color(51, 51, 51));
		
		JLabel lblCrewMemberTo = new JLabel("Crew Members to Pilot the Ship");
		lblCrewMemberTo.setForeground(new Color(255, 255, 255));
		lblCrewMemberTo.setFont(new Font("Arial", Font.BOLD, 14));
		
		JComboBox<Object> comboBox = new JComboBox<Object>();
		comboBox.setModel(new DefaultComboBoxModel<Object>(gameState.crew.getCrewList(gameState.getCrewSize())));
		comboBox.setSelectedIndex(0);
		
		JComboBox<Object> comboBox_1 = new JComboBox<Object>();
		comboBox_1.setModel(new DefaultComboBoxModel<Object>(gameState.crew.getCrewList(gameState.getCrewSize())));
		comboBox_1.setSelectedIndex(1);
		comboBox_1.removeItemAt(0);
		
		GroupLayout gl_memberSelectPanel = new GroupLayout(memberSelectPanel);
		gl_memberSelectPanel.setHorizontalGroup(
			gl_memberSelectPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_memberSelectPanel.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_memberSelectPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_memberSelectPanel.createSequentialGroup()
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
							.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
							.addGap(18))
						.addGroup(gl_memberSelectPanel.createSequentialGroup()
							.addComponent(lblCrewMemberTo)
							.addContainerGap(302, Short.MAX_VALUE))))
		);
		gl_memberSelectPanel.setVerticalGroup(
			gl_memberSelectPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_memberSelectPanel.createSequentialGroup()
					.addGap(19)
					.addComponent(lblCrewMemberTo)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_memberSelectPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(31, Short.MAX_VALUE))
		);
		
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				finishedWindow();
			}
		});
		
		JButton btnSearchAction = new JButton("Fly (1 Action per Pilot)");
		btnSearchAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrewMember member1 = (CrewMember) comboBox.getSelectedItem();
				CrewMember member2 = (CrewMember) comboBox_1.getSelectedItem();
				if(member1.getActions() > 0 && member2.getActions() > 0) {
					gameState.nextPlanet(member1, member2);
					frmSpaceExplorer.dispose();
				}else if(member1.getActions() <= 0 && member2.getActions() <= 0) {
					gameState.showPopUp(member1.getName()+" and "+member2.getName()+" do not have the required actions to pilot the ship.", frmSpaceExplorer.getBounds());
				}else if(member1.getActions() <= 0){
					gameState.showPopUp(member1.getName()+" doesnt have the required actions to pilot the ship.", frmSpaceExplorer.getBounds());
				}else if(member2.getActions() <= 0) {
					gameState.showPopUp(member2.getName()+" doesnt have the required actions to pilot the ship.", frmSpaceExplorer.getBounds());
				}
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(frmSpaceExplorer.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(16)
					.addComponent(lblSearchPlanet)
					.addContainerGap(269, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(13)
					.addComponent(btnCancel)
					.addPreferredGap(ComponentPlacement.RELATED, 177, Short.MAX_VALUE)
					.addComponent(btnSearchAction)
					.addGap(14))
				.addComponent(memberSelectPanel, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(13)
					.addComponent(lblSearchPlanet)
					.addPreferredGap(ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
					.addComponent(memberSelectPanel, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnSearchAction))
					.addGap(8))
		);
		
		memberSelectPanel.setLayout(gl_memberSelectPanel);
		frmSpaceExplorer.getContentPane().setLayout(groupLayout);
		
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					comboBox_1.removeItemAt(comboBox.getSelectedIndex());
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					int newIndex = comboBox_1.getSelectedIndex();
					comboBox_1.setModel(new DefaultComboBoxModel<Object>(gameState.crew.getCrewList(gameState.getCrewSize())));
					comboBox_1.setSelectedIndex(newIndex);
				}	
			}
		});				
	}
}
