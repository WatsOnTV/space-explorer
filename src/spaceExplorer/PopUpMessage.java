package spaceExplorer;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;

/** Displays popup message screen.
 * @author Karna Malone
 * @author Daniel Watson
*/
public class PopUpMessage {

	private JFrame frmSpaceExplorer;
	private String message;
	private int x, y;
	
	/**
	 * Initialize the popup window
	 * @param message Message to be displayed
	 * @param dimensions Previous windows size and position
	 */
	public PopUpMessage(String message, Rectangle dimensions) {
		this.message = message;
		this.x = dimensions.x+dimensions.width/2-245/2;
		this.y = dimensions.y+dimensions.height/2-137/2;
		initialize();
		frmSpaceExplorer.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSpaceExplorer = new JFrame();
		frmSpaceExplorer.getContentPane().setBackground(Color.WHITE);
		frmSpaceExplorer.setAlwaysOnTop(true);
		frmSpaceExplorer.setTitle("Space Explorer");
		frmSpaceExplorer.setResizable(false);
		frmSpaceExplorer.setBounds(x, y, 259, 149);
		frmSpaceExplorer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JTextPane txtpnErrorPleaseFill = new JTextPane();
		txtpnErrorPleaseFill.setEditable(false);
		txtpnErrorPleaseFill.setForeground(new Color(51, 51, 51));
		txtpnErrorPleaseFill.setBounds(12, 12, 229, 66);
		txtpnErrorPleaseFill.setBackground(Color.WHITE);
		txtpnErrorPleaseFill.setFont(new Font("Arial", Font.PLAIN, 14));
		txtpnErrorPleaseFill.setText(message);
		frmSpaceExplorer.getContentPane().setLayout(null);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmSpaceExplorer.dispose();
			}
		});
		btnOk.setBounds(174, 84, 67, 23);
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frmSpaceExplorer.getContentPane().add(btnOk);
		frmSpaceExplorer.getContentPane().add(txtpnErrorPleaseFill);
	}
}