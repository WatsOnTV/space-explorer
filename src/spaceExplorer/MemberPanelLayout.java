package spaceExplorer;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Rectangle;
import javax.swing.JProgressBar;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

/** Displays a member panel in the control panel screen.
 * @author Karna Malone
 * @author Daniel Watson
*/
public class MemberPanelLayout {
	private CrewMember member;
	private JProgressBar healthBar;
	private JProgressBar foodBar;
	private JProgressBar energyBar;
	private JLabel lblMemberActions;
	private JComboBox<Object> foodSelect, medsSelect;
	public MemberPanelLayout(JTabbedPane tabbedPane, JPanel memberPanel, Game gameState, int memberNumber, Rectangle mainFrameDimentions) {
		member = gameState.crew.crewMembers.get(memberNumber);
		memberPanel.setBackground(new Color(255, 255, 255));
		
		tabbedPane.addTab(member.getName(), null, memberPanel, null);
		
		String spacePlague = "";
		if(member.hasSpacePlague()) {
			spacePlague = "{INFECTED} ";
		}
		JLabel lblMemberName = new JLabel(spacePlague+member.getName()+" ("+member.getType()+")");
		lblMemberName.setBounds(10, 18, 388, 20);
		lblMemberName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JPanel memberStats = new JPanel();
		memberStats.setBounds(10, 76, 388, 119);
		memberStats.setBackground(new Color(255, 255, 255));
		
		lblMemberActions = new JLabel("Actions Remaining: "+member.getActions());
		lblMemberActions.setBounds(10, 44, 388, 14);
		
		String regainEnergyMsg = "Sleep";
		if(member.getType() == "Robot") {
			regainEnergyMsg = "Recharge Batterys";
		}
		JButton btnSleep = new JButton(regainEnergyMsg+" (1 Action)");
		btnSleep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(member.getActions() > 0) {
					member.useActions(1);
					member.energy(40);
					energyBar.setValue((int) member.getEnergy());
					lblMemberActions.setText("Actions Remaining: "+member.getActions());
				}else {
					gameState.showPopUp(member.getName()+" doesnt have the required actions to sleep.", mainFrameDimentions);
				}
			}
		});
		btnSleep.setBounds(10, 213, 388, 45);
		btnSleep.setBackground(new Color(255, 255, 255));
		
		foodSelect = new JComboBox<Object>();
		foodSelect.setBounds(10, 263, 209, 35);
		foodSelect.setModel(new DefaultComboBoxModel<Object>(gameState.ship.getFoodItemNames()));
		
		medsSelect = new JComboBox<Object>();
		medsSelect.setBounds(10, 311, 209, 35);
		medsSelect.setModel(new DefaultComboBoxModel<Object>(gameState.ship.getMedsItemNames()));
		GridBagLayout gbl_memberStats = new GridBagLayout();
		gbl_memberStats.columnWidths = new int[]{0, 0, 0};
		gbl_memberStats.rowHeights = new int[]{0, 0, 0, 0};
		gbl_memberStats.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_memberStats.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		memberStats.setLayout(gbl_memberStats);
		
		JLabel lblHealth = new JLabel(" Health ");
		lblHealth.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblHealth = new GridBagConstraints();
		gbc_lblHealth.fill = GridBagConstraints.BOTH;
		gbc_lblHealth.insets = new Insets(0, 0, 5, 5);
		gbc_lblHealth.gridx = 0;
		gbc_lblHealth.gridy = 0;
		memberStats.add(lblHealth, gbc_lblHealth);
		
		healthBar = new JProgressBar();
		healthBar.setValue((int) member.getHealth());
		GridBagConstraints gbc_healthBar = new GridBagConstraints();
		gbc_healthBar.fill = GridBagConstraints.BOTH;
		gbc_healthBar.insets = new Insets(0, 0, 5, 0);
		gbc_healthBar.gridx = 1;
		gbc_healthBar.gridy = 0;
		memberStats.add(healthBar, gbc_healthBar);
		
		JLabel lblFood = new JLabel(" Food ");
		lblFood.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblFood = new GridBagConstraints();
		gbc_lblFood.fill = GridBagConstraints.BOTH;
		gbc_lblFood.insets = new Insets(0, 0, 5, 5);
		gbc_lblFood.gridx = 0;
		gbc_lblFood.gridy = 1;
		memberStats.add(lblFood, gbc_lblFood);
		
		foodBar = new JProgressBar();
		foodBar.setValue((int) member.getFood());
		GridBagConstraints gbc_foodBar = new GridBagConstraints();
		gbc_foodBar.fill = GridBagConstraints.BOTH;
		gbc_foodBar.insets = new Insets(0, 0, 5, 0);
		gbc_foodBar.gridx = 1;
		gbc_foodBar.gridy = 1;
		memberStats.add(foodBar, gbc_foodBar);
		
		JLabel lblEnergy = new JLabel(" Energy ");
		lblEnergy.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblEnergy = new GridBagConstraints();
		gbc_lblEnergy.insets = new Insets(0, 0, 0, 5);
		gbc_lblEnergy.fill = GridBagConstraints.BOTH;
		gbc_lblEnergy.gridx = 0;
		gbc_lblEnergy.gridy = 2;
		memberStats.add(lblEnergy, gbc_lblEnergy);
		
		energyBar = new JProgressBar();
		energyBar.setValue((int) member.getEnergy());
		GridBagConstraints gbc_energyBar = new GridBagConstraints();
		gbc_energyBar.fill = GridBagConstraints.BOTH;
		gbc_energyBar.gridx = 1;
		gbc_energyBar.gridy = 2;
		memberStats.add(energyBar, gbc_energyBar);
		
		JButton btnEatFood = new JButton("Eat Food (1 Action)");
		btnEatFood.setForeground(new Color(255, 255, 255));
		btnEatFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(member.getActions() > 0) {
					member.useActions(1);
					gameState.consume("food", (String) foodSelect.getSelectedItem(), member);
					foodSelect.removeItemAt(foodSelect.getSelectedIndex());
					foodBar.setValue((int) member.getFood());
					lblMemberActions.setText("Actions Remaining: "+member.getActions());
				}else {
					gameState.showPopUp(member.getName()+" doesnt have the required actions to eat food.", mainFrameDimentions);
				}
			}
		});
		btnEatFood.setBounds(224, 263, 174, 35);
		btnEatFood.setBackground(new Color(51, 153, 102));
		
		JButton btnUseMedicine = new JButton("Use Medicine (1 Action)");
		btnUseMedicine.setForeground(new Color(255, 255, 255));
		btnUseMedicine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(member.getActions() > 0) {
					member.useActions(1);
					gameState.consume("meds", (String) medsSelect.getSelectedItem(), member);
					medsSelect.removeItemAt(medsSelect.getSelectedIndex());
					healthBar.setValue((int) member.getHealth());
					lblMemberActions.setText("Actions Remaining: "+member.getActions());
					String spacePlague = "";
					if(member.hasSpacePlague()) {spacePlague = "{INFECTED} ";}
					lblMemberName.setText(spacePlague+member.getName()+" ("+member.getType()+")");
				}else {
					gameState.showPopUp(member.getName()+" doesnt have the required actions to use meds.", mainFrameDimentions);
				}
			}
		});
		btnUseMedicine.setBounds(224, 311, 174, 35);
		btnUseMedicine.setBackground(new Color(255, 102, 102));
		
		memberPanel.setLayout(null);
		memberPanel.add(lblMemberName);
		memberPanel.add(lblMemberActions);
		memberPanel.add(memberStats);
		memberPanel.add(btnSleep);
		memberPanel.add(foodSelect);
		memberPanel.add(btnEatFood);
		memberPanel.add(medsSelect);
		memberPanel.add(btnUseMedicine);
	}
	
	/**
	 * Reload the member panel.
	 * @param gameState Current game state.
	 */
	public void updatePanel(Game gameState) {
		healthBar.setValue((int) member.getHealth());
		foodBar.setValue((int) member.getFood());
		energyBar.setValue((int) member.getEnergy());
		lblMemberActions.setText("Actions Remaining: "+member.getActions());
		foodSelect.setModel(new DefaultComboBoxModel<Object>(gameState.ship.getFoodItemNames()));
		medsSelect.setModel(new DefaultComboBoxModel<Object>(gameState.ship.getMedsItemNames()));
	}
}
