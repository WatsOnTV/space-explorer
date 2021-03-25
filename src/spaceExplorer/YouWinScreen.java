package spaceExplorer;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Rectangle;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/** Displays you win screen.
 * @author Karna Malone
 * @author Daniel Watson
*/
public class YouWinScreen {

	private JFrame frmSpaceExplorer;
	private Game gameState;
	private int x,y;

	/**
	 * Create you win screen.
	 * @param incomingState Current game state.
	 */
	public YouWinScreen(Game incomingState) {
		this.gameState = incomingState;
		Rectangle dimensions = gameState.getLastWindowPosition();
		this.x = dimensions.x+dimensions.width/2-895/2;
		this.y = dimensions.y+dimensions.height/2-563/2;
		initialize();
		frmSpaceExplorer.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSpaceExplorer = new JFrame();
		frmSpaceExplorer.setResizable(false);
		frmSpaceExplorer.getContentPane().setBackground(new Color(51, 51, 51));
		
		JLabel lblYouWin = new JLabel("You Win", SwingConstants.CENTER);
		lblYouWin.setFont(new Font("URW Gothic L", Font.BOLD, 50));
		lblYouWin.setForeground(new Color(255, 255, 255));
		
		JLabel lblCongratsYouCollected = new JLabel("Congrats. You found all "+gameState.ship.getName()+"'s missing parts in "+gameState.getDay()+"/"+gameState.getMaxDays()+" days.", SwingConstants.CENTER);
		lblCongratsYouCollected.setForeground(new Color(255, 255, 255));
		lblCongratsYouCollected.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JPanel scorePanel = new JPanel();
		scorePanel.setBackground(new Color(255, 255, 255));
		
		JButton btnExitGame = new JButton("Exit Game");
		btnExitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmSpaceExplorer.dispose();
			}
		});
		GroupLayout groupLayout = new GroupLayout(frmSpaceExplorer.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scorePanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 889, Short.MAX_VALUE)
				.addComponent(lblCongratsYouCollected, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 889, Short.MAX_VALUE)
				.addComponent(lblYouWin, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 889, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(363)
					.addComponent(btnExitGame, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(365, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(100)
					.addComponent(lblYouWin)
					.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
					.addComponent(lblCongratsYouCollected)
					.addGap(46)
					.addComponent(scorePanel, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
					.addGap(33)
					.addComponent(btnExitGame, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addGap(92))
		);
		
		JLabel lblYourScore = new JLabel("Your Score:", SwingConstants.CENTER);
		lblYourScore.setForeground(new Color(51, 51, 51));
		lblYourScore.setFont(new Font("Arial", Font.BOLD, 13));
		
		JLabel label = new JLabel(gameState.getScore()+"", SwingConstants.CENTER);
		label.setForeground(new Color(51, 51, 51));
		label.setFont(new Font("Arial", Font.BOLD, 50));
		GroupLayout gl_scorePanel = new GroupLayout(scorePanel);
		gl_scorePanel.setHorizontalGroup(
			gl_scorePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_scorePanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_scorePanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(label, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 923, Short.MAX_VALUE)
						.addComponent(lblYourScore, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 923, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_scorePanel.setVerticalGroup(
			gl_scorePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_scorePanel.createSequentialGroup()
					.addContainerGap(19, Short.MAX_VALUE)
					.addComponent(lblYourScore)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addGap(23))
		);
		scorePanel.setLayout(gl_scorePanel);
		frmSpaceExplorer.getContentPane().setLayout(groupLayout);
		frmSpaceExplorer.setTitle("Space Explorer");
		frmSpaceExplorer.setBounds(x, y, 895, 563);
		frmSpaceExplorer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
