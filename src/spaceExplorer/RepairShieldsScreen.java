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

/** Displays repair shields screen.
 * @author Karna Malone
 * @author Daniel Watson
*/
public class RepairShieldsScreen {

	private JFrame frmSpaceExplorer;
	private Game gameState;
	private int x,y;

	/**
	 * Create repair shields screen.
	 * @param incomingState Current game state.
	 */
	public RepairShieldsScreen(Game incomingState) {
		this.gameState = incomingState;
		Rectangle dimensions = gameState.getLastWindowPosition();
		this.x = dimensions.x+dimensions.width/2-414/2;
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
	
	void closeWindow() {
		frmSpaceExplorer.dispose();
	}
	
	private void finishedWindow() {
		gameState.closeRepairShields(this);
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
		frmSpaceExplorer.setBounds(x, y, 414, 235);
		frmSpaceExplorer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblSearchPlanet = new JLabel("Repair Shields");
		lblSearchPlanet.setForeground(new Color(51, 51, 51));
		lblSearchPlanet.setFont(new Font("URW Gothic L", Font.BOLD, 20));
		
		JPanel memberSelectPanel = new JPanel();
		memberSelectPanel.setBackground(new Color(51, 51, 51));
		
		JLabel lblCrewMemberTo = new JLabel("Crew Member to Perform the Repair");
		lblCrewMemberTo.setForeground(new Color(255, 255, 255));
		lblCrewMemberTo.setFont(new Font("Arial", Font.BOLD, 14));
		
		JComboBox<Object> comboBox = new JComboBox<Object>();
		comboBox.setModel(new DefaultComboBoxModel<Object>(gameState.crew.getCrewList(gameState.getCrewSize())));
		GroupLayout gl_memberSelectPanel = new GroupLayout(memberSelectPanel);
		gl_memberSelectPanel.setHorizontalGroup(
			gl_memberSelectPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_memberSelectPanel.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_memberSelectPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBox, 0, 372, Short.MAX_VALUE)
						.addComponent(lblCrewMemberTo))
					.addGap(18))
		);
		gl_memberSelectPanel.setVerticalGroup(
			gl_memberSelectPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_memberSelectPanel.createSequentialGroup()
					.addGap(19)
					.addComponent(lblCrewMemberTo)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(25, Short.MAX_VALUE))
		);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				finishedWindow();
			}
		});
		
		JButton btnSearchAction = new JButton("Repair (1 Action)");
		btnSearchAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrewMember member = (CrewMember) comboBox.getSelectedItem();
				if(member.getActions() > 0) {
					gameState.repairShields(member);
					frmSpaceExplorer.dispose();
				}else {
					gameState.showPopUp(member.getName()+" doesnt have the required actions to repair the shields.", frmSpaceExplorer.getBounds());
				}
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(frmSpaceExplorer.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(16)
					.addComponent(lblSearchPlanet)
					.addContainerGap(299, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(13)
					.addComponent(btnCancel)
					.addPreferredGap(ComponentPlacement.RELATED, 231, Short.MAX_VALUE)
					.addComponent(btnSearchAction)
					.addGap(14))
				.addComponent(memberSelectPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(13)
					.addComponent(lblSearchPlanet)
					.addGap(14)
					.addComponent(memberSelectPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnSearchAction))
					.addGap(8))
		);
		
		memberSelectPanel.setLayout(gl_memberSelectPanel);
		frmSpaceExplorer.getContentPane().setLayout(groupLayout);
	}
}
