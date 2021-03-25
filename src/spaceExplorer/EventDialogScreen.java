package spaceExplorer;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Rectangle;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/** Displays a popup game dialog message.
 * @author Karna Malone
 * @author Daniel Watson
*/
public class EventDialogScreen {

	private JFrame frmSpaceExplorer;
	private Game gameState;
	private String eventTitle, eventDescription;
	private int x, y;
	

	/**
	 * Create the window.
	 * @param title Dialog title.
	 * @param desc Dialog main text description.
	 * @param incomingState Current game state.
	 */
	public EventDialogScreen(String title, String desc, Game incomingState) {
		this.gameState = incomingState;
		this.eventTitle =  title;
		this.eventDescription = desc;
		Rectangle dimensions = gameState.getLastWindowPosition();
		this.x = dimensions.x+dimensions.width/2-561/2;
		this.y = dimensions.y+dimensions.height/2-208/2;
		initialize();
		frmSpaceExplorer.setVisible(true);
	}
	
	void closeWindow() {
		frmSpaceExplorer.dispose();
	}
	
	private void finishedWindow() {
		gameState.closeEventDialog(this);
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
		frmSpaceExplorer.getContentPane().setBackground(Color.WHITE);
		
		JLabel lblSpacePirates = new JLabel(eventTitle);
		lblSpacePirates.setForeground(new Color(51, 51, 51));
		lblSpacePirates.setFont(new Font("Dialog", Font.BOLD, 26));
		
		JTextPane txtpnSpacePiratesHave = new JTextPane();
		txtpnSpacePiratesHave.setEditable(false);
		txtpnSpacePiratesHave.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtpnSpacePiratesHave.setText(eventDescription);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				finishedWindow();
			}
		});
		GroupLayout groupLayout = new GroupLayout(frmSpaceExplorer.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(16)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSpacePirates)
						.addComponent(txtpnSpacePiratesHave, GroupLayout.PREFERRED_SIZE, 524, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnOk, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addComponent(lblSpacePirates)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtpnSpacePiratesHave, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnOk)
					.addContainerGap(13, Short.MAX_VALUE))
		);
		frmSpaceExplorer.getContentPane().setLayout(groupLayout);
		frmSpaceExplorer.setResizable(false);
		frmSpaceExplorer.setTitle("Space Explorer");
		frmSpaceExplorer.setBounds(x, y, 561, 208);
		frmSpaceExplorer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
