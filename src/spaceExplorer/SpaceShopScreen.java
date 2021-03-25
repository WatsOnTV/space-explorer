package spaceExplorer;

import spaceExplorer.FoodItems.*;
import spaceExplorer.MedicalItems.*;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

/** Displays space shop screen.
 * @author Karna Malone
 * @author Daniel Watson
*/
public class SpaceShopScreen {

	private JFrame frmSpaceExplorer;
	private Game gameState;
	private int x,y;
	private JLabel lblMoney;
	private JTextPane txtYourItems;

	/**
	 * Create space shop screen.
	 * @param incomingState Current game state.
	 */
	public SpaceShopScreen(Game incomingState) {
		this.gameState = incomingState;
		Rectangle dimensions = gameState.getLastWindowPosition();
		this.x = dimensions.x+dimensions.width/2-1076/2;
		this.y = dimensions.y+dimensions.height/2-575/2;
		initialize();
		frmSpaceExplorer.setVisible(true);
	}
	
	void closeWindow() {
		frmSpaceExplorer.dispose();
	}
	
	private void finishedWindow() {
		gameState.closeSpaceShop(this);
	}
	
	/**
	 * Updates money and items owned in the UI
	 */
	private void updateContent() {
		lblMoney.setText("Money: $"+gameState.crew.getMoney());
		txtYourItems.setText(gameState.ship.getStringFoodItems()+"\r\n"+gameState.ship.getStringMedicalItems());
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
		frmSpaceExplorer.setResizable(false);
		frmSpaceExplorer.setTitle("Space Explorer");
		frmSpaceExplorer.getContentPane().setBackground(Color.WHITE);
		frmSpaceExplorer.setBounds(x, y, 1091, 596);
		frmSpaceExplorer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblSpaceOutpostShop = new JLabel("Space Outpost Shop");
		lblSpaceOutpostShop.setForeground(new Color(51, 51, 51));
		lblSpaceOutpostShop.setFont(new Font("URW Gothic L", Font.BOLD, 25));
		
		JPanel shopPanel = new JPanel();
		shopPanel.setBackground(new Color(51, 51, 51));
		
		lblMoney = new JLabel("Money: $"+gameState.crew.getMoney());
		lblMoney.setForeground(new Color(51, 51, 51));
		lblMoney.setFont(new Font("URW Gothic L", Font.BOLD, 15));
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				finishedWindow();
			}
		});
		GroupLayout groupLayout = new GroupLayout(frmSpaceExplorer.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(shopPanel, GroupLayout.DEFAULT_SIZE, 1060, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addComponent(lblSpaceOutpostShop)
					.addContainerGap(792, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(25)
					.addComponent(lblMoney)
					.addPreferredGap(ComponentPlacement.RELATED, 833, Short.MAX_VALUE)
					.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addGap(22))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(17)
					.addComponent(lblSpaceOutpostShop)
					.addGap(15)
					.addComponent(shopPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMoney)
						.addComponent(btnBack))
					.addGap(14))
		);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(51, 51, 51));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(51, 51, 51));
		
		JLabel lblMedicalItems = new JLabel("Medical Items");
		lblMedicalItems.setForeground(Color.WHITE);
		lblMedicalItems.setFont(new Font("Arial", Font.BOLD, 19));
		
		JLabel label_7 = new JLabel("1.");
		label_7.setForeground(Color.WHITE);
		label_7.setFont(new Font("SansSerif", Font.PLAIN, 13));
		
		JLabel lblBandage = new JLabel("Bandage ($6.00)");
		lblBandage.setForeground(Color.WHITE);
		lblBandage.setFont(new Font("SansSerif", Font.BOLD, 12));
		
		JButton btnBuyBandage = new JButton("Purchase");
		btnBuyBandage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameState.purchaseItem("Meds", new Bandage());
				updateContent();
			}
		});
		
		JLabel label_9 = new JLabel("2.");
		label_9.setForeground(Color.WHITE);
		label_9.setFont(new Font("SansSerif", Font.PLAIN, 13));
		
		JLabel lblFirstAidKit = new JLabel("First Aid Kit ($9.00)");
		lblFirstAidKit.setForeground(Color.WHITE);
		lblFirstAidKit.setFont(new Font("SansSerif", Font.BOLD, 12));
		
		JButton btnBuyFirstAidKit = new JButton("Purchase");
		btnBuyFirstAidKit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameState.purchaseItem("Meds", new FirstAidKit());
				updateContent();
			}
		});
		
		JLabel label_11 = new JLabel("3.");
		label_11.setForeground(Color.WHITE);
		label_11.setFont(new Font("SansSerif", Font.PLAIN, 13));
		
		JLabel lblIv = new JLabel("IV ($14.00)");
		lblIv.setForeground(Color.WHITE);
		lblIv.setFont(new Font("SansSerif", Font.BOLD, 12));
		
		JButton btnBuyIV = new JButton("Purchase");
		btnBuyIV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameState.purchaseItem("Meds", new IV());
				updateContent();
			}
		});
		
		JLabel label_13 = new JLabel("4.");
		label_13.setForeground(Color.WHITE);
		label_13.setFont(new Font("SansSerif", Font.PLAIN, 13));
		
		JLabel lblMorphine = new JLabel("Morphine ($18.00)");
		lblMorphine.setForeground(Color.WHITE);
		lblMorphine.setFont(new Font("SansSerif", Font.BOLD, 12));
		
		JButton btnBuyMorphine = new JButton("Purchase");
		btnBuyMorphine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameState.purchaseItem("Meds", new Morphine());
				updateContent();
			}
		});
		
		JLabel label_15 = new JLabel("5.");
		label_15.setForeground(Color.WHITE);
		label_15.setFont(new Font("SansSerif", Font.PLAIN, 13));
		
		JLabel lblSpacePlagueOintment = new JLabel("Space Plague Ointment ($25.00)");
		lblSpacePlagueOintment.setForeground(Color.WHITE);
		lblSpacePlagueOintment.setFont(new Font("SansSerif", Font.BOLD, 12));
		
		JButton btnBuySpacePlagueOintment = new JButton("Purchase");
		btnBuySpacePlagueOintment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameState.purchaseItem("Meds", new SpacePlagueOintment());
				updateContent();
			}
		});
		
		JLabel lblHealth = new JLabel("+ 6 Health");
		lblHealth.setForeground(Color.WHITE);
		lblHealth.setFont(new Font("Arial", Font.PLAIN, 10));
		
		JLabel lblHealth_1 = new JLabel("+ 10 Health");
		lblHealth_1.setForeground(Color.WHITE);
		lblHealth_1.setFont(new Font("Arial", Font.PLAIN, 10));
		
		JLabel lblHealth_2 = new JLabel("+ 15 Health");
		lblHealth_2.setForeground(Color.WHITE);
		lblHealth_2.setFont(new Font("Arial", Font.PLAIN, 10));
		
		JLabel lblHealth_3 = new JLabel("+ 20 Health");
		lblHealth_3.setForeground(Color.WHITE);
		lblHealth_3.setFont(new Font("Arial", Font.PLAIN, 10));
		
		JLabel lblHealth_4 = new JLabel("+ 25 Health & Cures Space Plague");
		lblHealth_4.setForeground(Color.WHITE);
		lblHealth_4.setFont(new Font("Arial", Font.PLAIN, 10));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(lblMedicalItems)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(3)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel_2.createSequentialGroup()
									.addComponent(label_13)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
										.addComponent(lblMorphine, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblHealth_3))
									.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
									.addComponent(btnBuyMorphine, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_2.createSequentialGroup()
									.addComponent(label_11)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
										.addComponent(lblIv, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblHealth_2))
									.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
									.addComponent(btnBuyIV, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_2.createSequentialGroup()
									.addComponent(label_9)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
										.addComponent(lblFirstAidKit, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblHealth_1))
									.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
									.addComponent(btnBuyFirstAidKit, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_2.createSequentialGroup()
									.addComponent(label_7)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
										.addComponent(lblBandage, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblHealth))
									.addGap(30)
									.addComponent(btnBuyBandage, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_2.createSequentialGroup()
									.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_2.createSequentialGroup()
											.addComponent(label_15)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(lblSpacePlagueOintment, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel_2.createSequentialGroup()
											.addGap(23)
											.addComponent(lblHealth_4)))
									.addPreferredGap(ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
									.addComponent(btnBuySpacePlagueOintment, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(17, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblMedicalItems)
							.addGap(31)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblBandage)
								.addComponent(btnBuyBandage)
								.addComponent(label_7))
							.addGap(26)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblFirstAidKit)
								.addComponent(btnBuyFirstAidKit)
								.addComponent(label_9))
							.addGap(26)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblIv)
								.addComponent(btnBuyIV)
								.addComponent(label_11))
							.addGap(26)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblMorphine)
								.addComponent(btnBuyMorphine)
								.addComponent(label_13))
							.addGap(26)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSpacePlagueOintment)
								.addComponent(btnBuySpacePlagueOintment)
								.addComponent(label_15)))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblHealth)
							.addGap(42)
							.addComponent(lblHealth_1)
							.addGap(41)
							.addComponent(lblHealth_2, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
							.addGap(41)
							.addComponent(lblHealth_3, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
							.addGap(46)))
					.addContainerGap(65, Short.MAX_VALUE))
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap(301, Short.MAX_VALUE)
					.addComponent(lblHealth_4, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addGap(55))
		);
		panel_2.setLayout(gl_panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		GroupLayout gl_shopPanel = new GroupLayout(shopPanel);
		gl_shopPanel.setHorizontalGroup(
			gl_shopPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_shopPanel.createSequentialGroup()
					.addGap(21)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE)
					.addGap(46)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
					.addGap(39)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(23, Short.MAX_VALUE))
		);
		gl_shopPanel.setVerticalGroup(
			gl_shopPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_shopPanel.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_shopPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 391, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_shopPanel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(panel_2, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
							.addComponent(panel_1, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 369, Short.MAX_VALUE)))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		
		JLabel lblYourItems = new JLabel("Your Items:");
		lblYourItems.setForeground(new Color(51, 51, 51));
		lblYourItems.setFont(new Font("Arial", Font.BOLD, 15));
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(14)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblYourItems))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(15)
					.addComponent(lblYourItems)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 329, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(17, Short.MAX_VALUE))
		);
		
		txtYourItems = new JTextPane();
		txtYourItems.setEditable(false);
		txtYourItems.setBackground(new Color(255, 255, 255));
		txtYourItems.setForeground(new Color(51, 51, 51));
		txtYourItems.setFont(new Font("Arial", Font.PLAIN, 13));
		txtYourItems.setText(gameState.ship.getStringFoodItems()+"\r\n"+gameState.ship.getStringMedicalItems());
		scrollPane.setViewportView(txtYourItems);
		panel_3.setLayout(gl_panel_3);
		
		JLabel lblFood = new JLabel("Food Items");
		lblFood.setForeground(new Color(255, 255, 255));
		lblFood.setFont(new Font("Arial", Font.BOLD, 19));
		
		JLabel label = new JLabel("1.");
		label.setFont(new Font("SansSerif", Font.PLAIN, 13));
		label.setForeground(new Color(255, 255, 255));
		
		JLabel lblFruit = new JLabel("Fruit ($2.50)");
		lblFruit.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblFruit.setForeground(new Color(255, 255, 255));
		
		JButton btnBuyFruit = new JButton("Purchase");
		btnBuyFruit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameState.purchaseItem("Food", new Fruit());
				updateContent();
			}
		});
		
		JButton btnBuyCookie = new JButton("Purchase");
		btnBuyCookie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameState.purchaseItem("Food", new Cookie());
				updateContent();
			}
		});
		
		JLabel lblCookie = new JLabel("Cookie ($4.50)");
		lblCookie.setForeground(Color.WHITE);
		lblCookie.setFont(new Font("SansSerif", Font.BOLD, 12));
		
		JLabel label_2 = new JLabel("2.");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("SansSerif", Font.PLAIN, 13));
		
		JLabel label_1 = new JLabel("3.");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("SansSerif", Font.PLAIN, 13));
		
		JLabel lblChocolate = new JLabel("Chocolate ($6.00)");
		lblChocolate.setForeground(Color.WHITE);
		lblChocolate.setFont(new Font("SansSerif", Font.BOLD, 12));
		
		JButton btnBuyChocolate = new JButton("Purchase");
		btnBuyChocolate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gameState.purchaseItem("Food", new Chocolate());
				updateContent();
			}
		});
		
		JButton btnBuyPasta = new JButton("Purchase");
		btnBuyPasta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gameState.purchaseItem("Food", new Pasta());
				updateContent();
			}
		});
		
		JLabel lblPasta = new JLabel("Pasta ($8.00)");
		lblPasta.setForeground(Color.WHITE);
		lblPasta.setFont(new Font("SansSerif", Font.BOLD, 12));
		
		JLabel label_3 = new JLabel("4.");
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("SansSerif", Font.PLAIN, 13));
		
		JLabel label_4 = new JLabel("5.");
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("SansSerif", Font.PLAIN, 13));
		
		JLabel lblBurger = new JLabel("Burger ($10.00)");
		lblBurger.setForeground(Color.WHITE);
		lblBurger.setFont(new Font("SansSerif", Font.BOLD, 12));
		
		JButton btnBuyBurger = new JButton("Purchase");
		btnBuyBurger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameState.purchaseItem("Food", new Burger());
				updateContent();
			}
		});
		
		JButton btnBuyPackagedMeal = new JButton("Purchase");
		btnBuyPackagedMeal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameState.purchaseItem("Food", new PackagedMeal());
				updateContent();
			}
		});
		
		JLabel lblPackagedMeal = new JLabel("Packaged Meal ($14.00)");
		lblPackagedMeal.setForeground(Color.WHITE);
		lblPackagedMeal.setFont(new Font("SansSerif", Font.BOLD, 12));
		
		JLabel label_6 = new JLabel("6.");
		label_6.setForeground(Color.WHITE);
		label_6.setFont(new Font("SansSerif", Font.PLAIN, 13));
		
		JLabel lblFood_1 = new JLabel("+ 8 Food");
		lblFood_1.setFont(new Font("Arial", Font.PLAIN, 10));
		lblFood_1.setForeground(Color.WHITE);
		
		JLabel lblFood_2 = new JLabel("+ 10 Food");
		lblFood_2.setForeground(Color.WHITE);
		lblFood_2.setFont(new Font("Arial", Font.PLAIN, 10));
		
		JLabel lblFood_3 = new JLabel("+ 15 Food");
		lblFood_3.setForeground(Color.WHITE);
		lblFood_3.setFont(new Font("Arial", Font.PLAIN, 10));
		
		JLabel lblFood_4 = new JLabel("+ 20 Food");
		lblFood_4.setForeground(Color.WHITE);
		lblFood_4.setFont(new Font("Arial", Font.PLAIN, 10));
		
		JLabel lblFood_5 = new JLabel("+ 25 Food");
		lblFood_5.setForeground(Color.WHITE);
		lblFood_5.setFont(new Font("Arial", Font.PLAIN, 10));
		
		JLabel lblFood_6 = new JLabel("+ 35 Food");
		lblFood_6.setForeground(Color.WHITE);
		lblFood_6.setFont(new Font("Arial", Font.PLAIN, 10));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblFood)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(3)
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblFruit, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblFood_1))
							.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
							.addComponent(btnBuyFruit, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(3)
							.addComponent(label_2)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCookie, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblFood_2))
							.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
							.addComponent(btnBuyCookie, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(3)
							.addComponent(label_1)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblChocolate, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblFood_3))
							.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
							.addComponent(btnBuyChocolate, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(3)
							.addComponent(label_3)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPasta, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblFood_4, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
							.addComponent(btnBuyPasta, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(3)
							.addComponent(label_4)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblBurger, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblFood_5))
							.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
							.addComponent(btnBuyBurger, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(3)
							.addComponent(label_6)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblFood_6, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPackagedMeal, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
							.addComponent(btnBuyPackagedMeal, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap(12, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblFood)
									.addGap(31)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblFruit)
										.addComponent(btnBuyFruit)
										.addComponent(label))
									.addGap(26)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblCookie)
										.addComponent(btnBuyCookie)
										.addComponent(label_2))
									.addGap(26)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblChocolate)
										.addComponent(btnBuyChocolate)
										.addComponent(label_1))
									.addGap(26)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblPasta)
										.addComponent(btnBuyPasta)
										.addComponent(label_3))
									.addGap(26)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblBurger)
										.addComponent(btnBuyBurger)
										.addComponent(label_4))
									.addGap(26)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblPackagedMeal)
										.addComponent(btnBuyPackagedMeal)
										.addComponent(label_6)))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblFood_1)
									.addGap(41)
									.addComponent(lblFood_2)
									.addGap(41)
									.addComponent(lblFood_3)
									.addGap(41)
									.addComponent(lblFood_4, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
									.addGap(41)
									.addComponent(lblFood_5)
									.addGap(46)))
							.addGap(13))
						.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
							.addComponent(lblFood_6, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		panel_1.setLayout(gl_panel_1);
		shopPanel.setLayout(gl_shopPanel);
		frmSpaceExplorer.getContentPane().setLayout(groupLayout);
	}
}
