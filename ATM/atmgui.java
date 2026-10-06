import java.awt.EventQueue;
import java.sql.*;
import java.util.Date;
import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class atmgui {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					atmgui window = new atmgui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection attachSQL = null;
	public atmgui() {
		attachSQL = sqlconnect.connect();
		initialize();
	}
	private String username=null, userPIN=null, act =null;
	private double balance=0;
	private Random rand = new Random();
	private double bill = 0;
	private JTable table;

	
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 897, 490);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel usernamegui = new JPanel();
		usernamegui.setBackground(new Color(0, 0, 0));
		frame.getContentPane().add(usernamegui, "UsernameGUI");
		GridBagLayout gbl_usernamegui = new GridBagLayout();
		gbl_usernamegui.columnWidths = new int[]{881, 0};
		gbl_usernamegui.rowHeights = new int[]{150, 79, 63, 37, 51, 0};
		gbl_usernamegui.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_usernamegui.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		usernamegui.setLayout(gbl_usernamegui);
		
		JPanel header0 = new JPanel();
		header0.setBackground(new Color(255, 215, 0));
		GridBagConstraints gbc_header0 = new GridBagConstraints();
		gbc_header0.fill = GridBagConstraints.BOTH;
		gbc_header0.insets = new Insets(0, 0, 5, 0);
		gbc_header0.gridx = 0;
		gbc_header0.gridy = 0;
		usernamegui.add(header0, gbc_header0);
		header0.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Wakai's ATM");
		lblNewLabel.setFont(new Font("Consolas", Font.PLAIN, 60));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 38, 881, 79);
		header0.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("ENTER");
		JTextField usernameField = new JTextField();
		usernameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					btnNewButton.doClick();
				}
			}
		});
		usernameField.setFont(new Font("Tahoma", Font.PLAIN, 40));
		usernameField.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_usernameField = new GridBagConstraints();
		gbc_usernameField.fill = GridBagConstraints.VERTICAL;
		gbc_usernameField.insets = new Insets(0, 0, 5, 0);
		gbc_usernameField.gridx = 0;
		gbc_usernameField.gridy = 2;
		usernamegui.add(usernameField, gbc_usernameField);
		usernameField.setColumns(10);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.VERTICAL;
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 4;
		usernamegui.add(btnNewButton, gbc_btnNewButton);
		
		btnNewButton.setBackground(new Color(255, 215, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(usernameField.getText().equalsIgnoreCase("Wakai")) {
					JOptionPane.showMessageDialog(frame, "Hello, Wakai!");
					CardLayout layout = (CardLayout) frame.getContentPane().getLayout();
					layout.next(frame.getContentPane());
					return;
				}
				
				try {
					String find = "SELECT * FROM Accounts WHERE Name=?";
					PreparedStatement nigga = attachSQL.prepareStatement(find);
					nigga.setString(1, usernameField.getText());
					ResultSet wow = nigga.executeQuery();
					int count = 0;
					username=usernameField.getText();
					while(wow.next()) {
						count++;
					}
					if(count==1) {
						JOptionPane.showMessageDialog(frame, "Success!");
						CardLayout layout = (CardLayout) frame.getContentPane().getLayout();
						layout.next(frame.getContentPane());
					}else
					if(count>1){
						JOptionPane.showMessageDialog(frame, "Duplicated");
					}else {
						JOptionPane.showMessageDialog(frame, "Unsuccessful");
					}
					wow.close();
					nigga.close();
					
				}catch(Exception x) {
					JOptionPane.showMessageDialog(null, x.getMessage());
				}
			}
		});
		
		JLabel headerText0 = new JLabel("Enter Username:");
		headerText0.setForeground(new Color(255, 215, 0));
		headerText0.setHorizontalAlignment(SwingConstants.CENTER);
		headerText0.setFont(new Font("Consolas", Font.PLAIN, 50));
		GridBagConstraints gbc_headerText0 = new GridBagConstraints();
		gbc_headerText0.fill = GridBagConstraints.BOTH;
		gbc_headerText0.insets = new Insets(0, 0, 5, 0);
		gbc_headerText0.gridx = 0;
		gbc_headerText0.gridy = 1;
		usernamegui.add(headerText0, gbc_headerText0);

		JPanel pingui = new JPanel();
		pingui.setBackground(Color.BLACK);
		frame.getContentPane().add(pingui, "pinGUI");
		GridBagLayout gbl_pingui = new GridBagLayout();
		gbl_pingui.columnWidths = new int[]{881, 0};
		gbl_pingui.rowHeights = new int[]{150, 57, 79, 79, 0};
		gbl_pingui.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_pingui.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pingui.setLayout(gbl_pingui);
		
		JPanel header1 = new JPanel();
		header1.setLayout(null);
		header1.setBackground(new Color(255, 215, 0));
		GridBagConstraints gbc_header1 = new GridBagConstraints();
		gbc_header1.fill = GridBagConstraints.BOTH;
		gbc_header1.insets = new Insets(0, 0, 5, 0);
		gbc_header1.gridx = 0;
		gbc_header1.gridy = 0;
		pingui.add(header1, gbc_header1);
		
		JLabel headerText1 = new JLabel("Wakai's ATM");
		headerText1.setHorizontalAlignment(SwingConstants.CENTER);
		headerText1.setFont(new Font("Consolas", Font.PLAIN, 60));
		headerText1.setBounds(0, 38, 881, 79);
		header1.add(headerText1);
		
		JPasswordField passwordField = new JPasswordField(20);
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				boolean correct=true;
						
				if (correct) {
			        for (char check : passwordField.getPassword()) {
				           if (!Character.isDigit(check)) {
			                correct = false;
			                break;
			            }
			        }
				}
				
				 if(passwordField.getPassword().length>4 || !correct) {
					JOptionPane.showMessageDialog(frame, "PIN must only have 4-digits number");
					passwordField.setText("");
				}
			}
		});
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 60));
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(username.equalsIgnoreCase("Wakai") && new String(passwordField.getPassword()).equalsIgnoreCase("1030")) {
					JOptionPane.showMessageDialog(frame, "Success!");
					
					CardLayout layout = (CardLayout) frame.getContentPane().getLayout();
					layout.show(frame.getContentPane(),"Admin");
					
					usernameField.setText("");
					passwordField.setText("");
					return;
				}
				
				
				try {
					
					String query = "SELECT * FROM Accounts WHERE Name=? AND PIN=?;";
					PreparedStatement ps = attachSQL.prepareStatement(query);
					ps.setString(1, username);
					ps.setString(2, new String(passwordField.getPassword()));
					
					userPIN=new String(passwordField.getPassword());

					
					ResultSet rs = ps.executeQuery();
					int count = 0;
					while(rs.next()) {
						balance = rs.getInt("Balance");
						count++;
					}
					if(count==1) {
						JOptionPane.showMessageDialog(frame, "Success!");
						
						CardLayout layout = (CardLayout) frame.getContentPane().getLayout();
						layout.next(frame.getContentPane());
						
						usernameField.setText("");
						passwordField.setText("");
					}else
					if(count>1){
						JOptionPane.showMessageDialog(frame, "Duplicated");
					}else {
						JOptionPane.showMessageDialog(frame, "Unsuccessful");
					}
					rs.close();
					ps.close();
					
				}catch(Exception x) {
					JOptionPane.showMessageDialog(frame, x.getMessage());
				}
			}
		});
		
		JLabel lblEnterUsername_1 = new JLabel("Enter 4-Digit PIN");
		lblEnterUsername_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterUsername_1.setForeground(new Color(255, 215, 0));
		lblEnterUsername_1.setFont(new Font("Consolas", Font.PLAIN, 50));
		GridBagConstraints gbc_lblEnterUsername_1 = new GridBagConstraints();
		gbc_lblEnterUsername_1.fill = GridBagConstraints.BOTH;
		gbc_lblEnterUsername_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblEnterUsername_1.gridx = 0;
		gbc_lblEnterUsername_1.gridy = 2;
		pingui.add(lblEnterUsername_1, gbc_lblEnterUsername_1);
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.anchor = GridBagConstraints.NORTH;
		gbc_passwordField.gridx = 0;
		gbc_passwordField.gridy = 3;
		pingui.add(passwordField, gbc_passwordField);
		passwordField.setColumns(4);
		
		JPanel mainMenu = new JPanel();
		mainMenu.setBackground(new Color(0, 0, 0));
		frame.getContentPane().add(mainMenu, "Main Menu");
		GridBagLayout gbl_mainMenu = new GridBagLayout();
		gbl_mainMenu.columnWidths = new int[]{271, 66, 188, 80, 276, 0};
		gbl_mainMenu.rowHeights = new int[]{81, 39, 49, 64, 49, 60, 54, 0};
		gbl_mainMenu.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_mainMenu.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		mainMenu.setLayout(gbl_mainMenu);
		
		JLabel descriptionInfo = new JLabel("Select Transaction");
		
		JPanel containerInfo = new JPanel();
		GridBagConstraints gbc_containerInfo = new GridBagConstraints();
		gbc_containerInfo.fill = GridBagConstraints.BOTH;
		gbc_containerInfo.insets = new Insets(0, 0, 0, 5);
		gbc_containerInfo.gridheight = 5;
		gbc_containerInfo.gridx = 2;
		gbc_containerInfo.gridy = 2;
		mainMenu.add(containerInfo, gbc_containerInfo);
		containerInfo.setLayout(null);
		
		descriptionInfo.setBounds(10, 11, 163, 251);
		descriptionInfo.setVerticalAlignment(SwingConstants.TOP);
		containerInfo.add(descriptionInfo);
		
		JLabel enterAmountText = new JLabel("");
		JButton Deposit = new JButton("DEPOSIT");
		Deposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout layout = (CardLayout) frame.getContentPane().getLayout();
				layout.show(frame.getContentPane(), "Enter Amount");
				enterAmountText.setText("Enter Deposit Amount:");
				act="deposit";
			}
		});
		
		JButton PayBills = new JButton("PAY BILLS");
		PayBills.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bill = rand.nextDouble(1000)+1;
				CardLayout layout = (CardLayout) frame.getContentPane().getLayout();
				layout.show(frame.getContentPane(),"Enter Amount");
				enterAmountText.setText("Enter Amount to Pay:");
				JOptionPane.showMessageDialog(frame, String.format("Current Bill is Php. %.2f",bill));
				act="Pay";
			}
		});
		PayBills.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				descriptionInfo.setText("<html><p style=\"text-align: justify;\">"
						+ "Bill payment through an ATM is a "
						+ "feature that allows customers to "
						+ "pay their utility bills, such as "
						+ "electricity, water, gas, internet,"
						+ " or phone bills, directly from "
						+ "their bank account using an "
						+ "Automated Teller Machine (ATM). "
						+ "This service simplifies managing "
						+ "regular payments without needing "
						+ "to visit the company’s office, "
						+ "use cash, or log into "
						+ "online banking.</p>");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				descriptionInfo.setText("Select Transaction");
			}
		});
		
		Deposit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				descriptionInfo.setText("<html><p style=\"text-align: justify;\">"
						+ "Cash deposit through an ATM allows"
						+ " customers to add physical cash "
						+ "directly into their bank account "
						+ "without visiting a bank branch."
						+ " This feature is especially useful"
						+ " for quick deposits outside of regular"
						+ " banking hours and reduces the need "
						+ "for face-to-face interactions.</p>");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				descriptionInfo.setText("Select Transaction");
			}
		});

		JPanel header2 = new JPanel();
		header2.setBackground(new Color(255, 215, 0));
		GridBagConstraints gbc_header2 = new GridBagConstraints();
		gbc_header2.fill = GridBagConstraints.BOTH;
		gbc_header2.insets = new Insets(0, 0, 5, 0);
		gbc_header2.gridwidth = 5;
		gbc_header2.gridx = 0;
		gbc_header2.gridy = 0;
		mainMenu.add(header2, gbc_header2);
		
		
		JLabel BalanceInquiryInfo = new JLabel("");
		BalanceInquiryInfo.setFont(new Font("Tahoma", Font.PLAIN, 30));
		BalanceInquiryInfo.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel headerText2 = new JLabel("Wakai's ATM");
		headerText2.setHorizontalAlignment(SwingConstants.CENTER);
		headerText2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		headerText2.setBackground(new Color(255, 215, 0));
		header2.add(headerText2);
		JPanel balanceInq = new JPanel();
		JButton BalanceInquiry = new JButton("BALANCE INQUIRY");
		BalanceInquiry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					CardLayout layout = (CardLayout) frame.getContentPane().getLayout();
					layout.show(frame.getContentPane(), "BalanceInquiry");
					
					String balanceText = "<html><p style=\"text-align: justify;\">"
							+ "You're current balance is: "
							+ "Php. "+balance+"</p>";
					act="Current Balance";
					BalanceInquiryInfo.setText(balanceText);
					
				}catch(Exception x) {
					JOptionPane.showMessageDialog(frame, x.getMessage());
				}
			}
		});
		BalanceInquiry.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				descriptionInfo.setText("<html><p style=\"text-align: justify;\">"
						+ "Balance inquiry is a feature in"
						+ " an ATM that lets customers check "
						+ "the current balance of their bank"
						+ " account. It provides quick and "
						+ "easy access to account information"
						+ " without visiting a bank branch or"
						+ " logging into online banking.</p>");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				descriptionInfo.setText("Select Transaction");
			}
		});
		BalanceInquiry.setBackground(new Color(255, 215, 0));
		BalanceInquiry.setHorizontalAlignment(SwingConstants.LEADING);
		BalanceInquiry.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_BalanceInquiry = new GridBagConstraints();
		gbc_BalanceInquiry.fill = GridBagConstraints.BOTH;
		gbc_BalanceInquiry.insets = new Insets(0, 0, 5, 5);
		gbc_BalanceInquiry.gridx = 0;
		gbc_BalanceInquiry.gridy = 2;
		mainMenu.add(BalanceInquiry, gbc_BalanceInquiry);

		JButton Withdraw = new JButton("WITHDRAW");
		Withdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout layout = (CardLayout) frame.getContentPane().getLayout();
				layout.show(frame.getContentPane(), "Enter Amount");
				enterAmountText.setText("Enter Withdrawal Amount:");
				act="withdraw";
			}
		});
		Withdraw.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				descriptionInfo.setText("<html><p style=\"text-align: justify;\">"
						+ " Withdrawal allows customers to "
						+ "withdraw physical cash from their "
						+ "bank account without needing to "
						+ "visit a branch or interact with "
						+ "a bank teller. This feature "
						+ "provides quick and easy access "
						+ "to funds, anytime and anywhere "
						+ "an ATM is available.</p>");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				descriptionInfo.setText("Select Transaction");
			}
		});
		Withdraw.setBackground(new Color(255, 215, 0));
		Withdraw.setHorizontalAlignment(SwingConstants.TRAILING);
		Withdraw.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_Withdraw = new GridBagConstraints();
		gbc_Withdraw.fill = GridBagConstraints.BOTH;
		gbc_Withdraw.insets = new Insets(0, 0, 5, 0);
		gbc_Withdraw.gridx = 4;
		gbc_Withdraw.gridy = 2;
		mainMenu.add(Withdraw, gbc_Withdraw);
		Deposit.setBackground(new Color(255, 215, 0));
		Deposit.setHorizontalAlignment(SwingConstants.LEADING);
		Deposit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_Deposit = new GridBagConstraints();
		gbc_Deposit.fill = GridBagConstraints.BOTH;
		gbc_Deposit.insets = new Insets(0, 0, 5, 5);
		gbc_Deposit.gridx = 0;
		gbc_Deposit.gridy = 4;
		mainMenu.add(Deposit, gbc_Deposit);
		
		JButton GiveCash = new JButton("FUND TRANSFER");
		GiveCash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout layout = (CardLayout) frame.getContentPane().getLayout();
				layout.show(frame.getContentPane(), "Transfer Fund");
				act="transfer";
			}
		});
		GiveCash.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				descriptionInfo.setText("<html><p style=\"text-align: justify;\">"
						+ "Fund transfer through an ATM is a "
						+ "feature that allows customers to "
						+ "move money from one bank account "
						+ "to another directly. This can "
						+ "include transfers between a "
						+ "customer’s own accounts or "
						+ "sending money to another person’s "
						+ "account, even in a different bank.</p>");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				descriptionInfo.setText("Select Transaction");
			}
		});
		GiveCash.setBackground(new Color(255, 215, 0));
		GiveCash.setHorizontalAlignment(SwingConstants.TRAILING);
		GiveCash.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_GiveCash = new GridBagConstraints();
		gbc_GiveCash.fill = GridBagConstraints.BOTH;
		gbc_GiveCash.insets = new Insets(0, 0, 5, 0);
		gbc_GiveCash.gridx = 4;
		gbc_GiveCash.gridy = 4;
		mainMenu.add(GiveCash, gbc_GiveCash);
		PayBills.setBackground(new Color(255, 215, 0));
		PayBills.setHorizontalAlignment(SwingConstants.LEADING);
		PayBills.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_PayBills = new GridBagConstraints();
		gbc_PayBills.fill = GridBagConstraints.BOTH;
		gbc_PayBills.insets = new Insets(0, 0, 0, 5);
		gbc_PayBills.gridx = 0;
		gbc_PayBills.gridy = 6;
		mainMenu.add(PayBills, gbc_PayBills);
		
		JButton ChangePIN = new JButton("CHANGE PIN");
		ChangePIN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				act="Change PIN";
				CardLayout layout = (CardLayout) frame.getContentPane().getLayout();
				layout.show(frame.getContentPane(), act);
				
			}
		});
		ChangePIN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				descriptionInfo.setText("<html><p style=\"text-align: justify;\">"
						+ "The PIN change feature in an ATM "
						+ "allows customers to update or reset "
						+ "their Personal Identification Number "
						+ "(PIN) for their debit, credit, or "
						+ "ATM card. This PIN is a crucial "
						+ "security measure that protects your"
						+ " account from unauthorized access, "
						+ "and changing it periodically or when"
						+ " there’s suspicion of misuse helps "
						+ "maintain account safety.</p>");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				descriptionInfo.setText("Select Transaction");
			}
		});
		ChangePIN.setBackground(new Color(255, 215, 0));
		ChangePIN.setHorizontalAlignment(SwingConstants.TRAILING);
		ChangePIN.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_ChangePIN = new GridBagConstraints();
		gbc_ChangePIN.fill = GridBagConstraints.BOTH;
		gbc_ChangePIN.gridx = 4;
		gbc_ChangePIN.gridy = 6;
		mainMenu.add(ChangePIN, gbc_ChangePIN);
		
		
		balanceInq.setBackground(Color.BLACK);
		frame.getContentPane().add(balanceInq, "BalanceInquiry");
		balanceInq.setLayout(null);
		
		JPanel header3 = new JPanel();
		header3.setBackground(new Color(255, 215, 0));
		header3.setBounds(0, 0, 881, 86);
		balanceInq.add(header3);
		
		JLabel headerText3 = new JLabel("Wakai's ATM");
		headerText3.setHorizontalAlignment(SwingConstants.CENTER);
		headerText3.setFont(new Font("Tahoma", Font.PLAIN, 30));
		headerText3.setBackground(new Color(255, 215, 0));
		header3.add(headerText3);
		
		JButton yesBalanceInq = new JButton("YES");
		yesBalanceInq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout layout = (CardLayout) frame.getContentPane().getLayout();
				layout.show(frame.getContentPane(), "Main Menu");
				
			}
		});
		yesBalanceInq.setFont(new Font("Tahoma", Font.PLAIN, 20));
		yesBalanceInq.setBounds(231, 380, 119, 37);
		balanceInq.add(yesBalanceInq);
		
		JLabel QuestionBalanceInquiry = new JLabel("Do you wish to make another Transaction?");
		QuestionBalanceInquiry.setForeground(new Color(255, 215, 0));
		QuestionBalanceInquiry.setHorizontalAlignment(SwingConstants.CENTER);
		QuestionBalanceInquiry.setFont(new Font("Tahoma", Font.PLAIN, 30));
		QuestionBalanceInquiry.setBackground(new Color(255, 215, 0));
		QuestionBalanceInquiry.setBounds(10, 332, 861, 37);
		balanceInq.add(QuestionBalanceInquiry);
		
		JPanel receipt = new JPanel();
		receipt.setBackground(Color.BLACK);
		frame.getContentPane().add(receipt, "Receipt");
		receipt.setLayout(null);
		
		JPanel receiptContainer = new JPanel();
		receiptContainer.setBounds(210, 72, 460, 368);
		receiptContainer.setBackground(new Color(105, 105, 105));
		receipt.add(receiptContainer);
		receiptContainer.setLayout(null);
		
		JLabel receiptText = new JLabel("");
		receiptText.setHorizontalAlignment(SwingConstants.CENTER);
		receiptText.setBounds(10, 11, 440, 293);
		receiptText.setForeground(new Color(255, 255, 0));
		receiptText.setFont(new Font("Consolas", Font.PLAIN, 20));
		receiptContainer.add(receiptText);
		
		JButton noBalanceInq = new JButton("NO");
		noBalanceInq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Date date = new Date();
					CardLayout layout = (CardLayout) frame.getContentPane().getLayout();
					layout.show(frame.getContentPane(), "Receipt");
					receiptText.setText("<html><p style=\"text-align: center;\">"
							+ "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^<br>"
							+ "Transaction Receipt of "+username
							+ "<br>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~<br>"
							+ "You have "+act+" of<br>Php. "+balance+"<br><br>"
							+ "Date of Transaction:<br>"+date
							+ "<br>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
							+ "<br>BALANCE INQUIRY"
							+ "<br>^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^</p>");
				}catch(Exception x) {
					JOptionPane.showMessageDialog(frame, x.getMessage());
				}
			}
		});
		noBalanceInq.setFont(new Font("Tahoma", Font.PLAIN, 20));
		noBalanceInq.setBounds(487, 380, 119, 37);
		balanceInq.add(noBalanceInq);
		
		JPanel containerBalanceInq = new JPanel();
		containerBalanceInq.setBackground(new Color(255, 255, 0));
		containerBalanceInq.setBounds(231, 141, 375, 180);
		balanceInq.add(containerBalanceInq);
		containerBalanceInq.setLayout(null);
		
		BalanceInquiryInfo.setBounds(10, 11, 355, 158);
		containerBalanceInq.add(BalanceInquiryInfo);
		
		JPanel enterAmount = new JPanel();
		enterAmount.setBackground(Color.BLACK);
		frame.getContentPane().add(enterAmount, "Enter Amount");
		GridBagLayout gbl_enterAmount = new GridBagLayout();
		gbl_enterAmount.columnWidths = new int[]{881, 0};
		gbl_enterAmount.rowHeights = new int[]{86, 64, 74, 58, 42, 51, 0};
		gbl_enterAmount.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_enterAmount.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		enterAmount.setLayout(gbl_enterAmount);
		
		JPanel header4 = new JPanel();
		header4.setBackground(new Color(255, 215, 0));
		GridBagConstraints gbc_header4 = new GridBagConstraints();
		gbc_header4.fill = GridBagConstraints.BOTH;
		gbc_header4.insets = new Insets(0, 0, 5, 0);
		gbc_header4.gridx = 0;
		gbc_header4.gridy = 0;
		enterAmount.add(header4, gbc_header4);
		
		JLabel headerText4 = new JLabel("Wakai's ATM");
		headerText4.setHorizontalAlignment(SwingConstants.CENTER);
		headerText4.setFont(new Font("Tahoma", Font.PLAIN, 30));
		headerText4.setBackground(new Color(255, 215, 0));
		header4.add(headerText4);
		
		JButton confirmAmount = new JButton("ENTER");
		
		enterAmountText.setHorizontalAlignment(SwingConstants.CENTER);
		enterAmountText.setForeground(new Color(255, 215, 0));
		enterAmountText.setFont(new Font("Consolas", Font.PLAIN, 50));
		GridBagConstraints gbc_enterAmountText = new GridBagConstraints();
		gbc_enterAmountText.fill = GridBagConstraints.BOTH;
		gbc_enterAmountText.insets = new Insets(0, 0, 5, 0);
		gbc_enterAmountText.gridx = 0;
		gbc_enterAmountText.gridy = 2;
		enterAmount.add(enterAmountText, gbc_enterAmountText);
		JTextField amountField = new JTextField();
		amountField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				boolean correct=true;
				int count=amountField.getText().length();
				char[] set= new char[count];
				for(int i=0; i<count;i++) {
					set[i]= amountField.getText().charAt(i);
				}
		        for (char check : set) {
		        	if(check=='.') {
		        		continue;
		        	}else
		        	
		           if (!Character.isDigit(check)) {
		        	   for(int i=0; i<set.length;i++) {
		        		   set[i]=0;
		        	   }
		                correct = false;
		                break;
		            }
				}
		        if (!correct) {
		        	amountField.setText("");
		        	JOptionPane.showMessageDialog(frame, "Invalid Input!");
		        }
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					confirmAmount.doClick();
				}
			}
		});
		amountField.setHorizontalAlignment(SwingConstants.CENTER);
		amountField.setFont(new Font("Tahoma", Font.PLAIN, 40));
		amountField.setColumns(10);
		GridBagConstraints gbc_amountField = new GridBagConstraints();
		gbc_amountField.fill = GridBagConstraints.VERTICAL;
		gbc_amountField.insets = new Insets(0, 0, 5, 0);
		gbc_amountField.gridx = 0;
		gbc_amountField.gridy = 3;
		enterAmount.add(amountField, gbc_amountField);

		JButton finish = new JButton("CONFIRM");
		finish.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					finish.doClick();
				}
			}
		});
		finish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout layout = (CardLayout) frame.getContentPane().getLayout();
				layout.show(frame.getContentPane(),"UsernameGUI");
				username=null;
				userPIN=null;
				balance=0;
				act=null;
			}
		});
		finish.setBounds(170, 315, 119, 42);
		finish.setFont(new Font("Tahoma", Font.PLAIN, 20));
		finish.setBackground(new Color(255, 215, 0));
		receiptContainer.add(finish);
		
		confirmAmount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date date = new Date();
				boolean error=false;
				double cash = Double.parseDouble(amountField.getText());
				
				
				if(act.equals("withdraw")) {
					double withdrawCash = cash;
					if(withdrawCash>0 && withdrawCash<=balance) {
						balance-=withdrawCash;
						JOptionPane.showMessageDialog(frame, "Successfully "+act+"n!");
					}else {
						error=true;
						JOptionPane.showMessageDialog(frame, "Invalid Input or insufficient balance!");
					}
				}else if(act.equals("deposit")) {
					double depositCash = cash;
					if(depositCash>0) {
						balance+=depositCash;
						JOptionPane.showMessageDialog(frame, "Successfully "+act+"ed!");
					}else {
						error=true;
						JOptionPane.showMessageDialog(frame, "Invalid Input!");
					}
				}else if(act.equals("Pay")) {
					double payBill = cash;
					if(payBill>0 && payBill<=balance && payBill<=bill) {
						balance-=payBill;
						bill-=payBill;
						JOptionPane.showMessageDialog(frame, "Successfully "+act+" for the bill!");
					}else {
						error=true;
						JOptionPane.showMessageDialog(frame, "Invalid Input or insufficient balance!");
					}
				}
				
				
				if(!error) {
				try {
					String query = "UPDATE Accounts SET Balance=? WHERE Name=? AND PIN=?";
					PreparedStatement ps = attachSQL.prepareStatement(query);
					ps.setDouble(1, balance);
					ps.setString(2, username);
					ps.setString(3, userPIN);
					ps.executeUpdate();
					
						CardLayout layout = (CardLayout) frame.getContentPane().getLayout();
						layout.show(frame.getContentPane(),"Receipt");
						receiptText.setText("<html><p style=\"text-align: center;\">"
								+ "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^<br>"
								+ "Transaction Receipt of "+username
								+ "<br>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~<br>"
								+ "You have "+act
								+ " an amount of<br>Php. "+cash
								+ "<br><br>"
								+ "Date of Transaction:<br>"+date
								+ "<br>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
								+ "<br>Balance: Php. "+balance
								+ "<br>^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^</p>");
						amountField.setText("");
					ps.close();
				}catch(Exception x) {
					JOptionPane.showMessageDialog(frame, x.getMessage());
				}
				}else {
					CardLayout layout = (CardLayout) frame.getContentPane().getLayout();
					layout.show(frame.getContentPane(),"Main Menu");
				}
			}
		});
		confirmAmount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		confirmAmount.setBackground(new Color(255, 215, 0));
		GridBagConstraints gbc_confirmAmount = new GridBagConstraints();
		gbc_confirmAmount.fill = GridBagConstraints.VERTICAL;
		gbc_confirmAmount.gridx = 0;
		gbc_confirmAmount.gridy = 5;
		enterAmount.add(confirmAmount, gbc_confirmAmount);
		
		JPanel changeUserPIN = new JPanel();
		changeUserPIN.setBackground(Color.BLACK);
		frame.getContentPane().add(changeUserPIN, "Change PIN");
		GridBagLayout gbl_changeUserPIN = new GridBagLayout();
		gbl_changeUserPIN.columnWidths = new int[]{863, 0};
		gbl_changeUserPIN.rowHeights = new int[]{76, 354, 0};
		gbl_changeUserPIN.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_changeUserPIN.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		changeUserPIN.setLayout(gbl_changeUserPIN);
		
		JPanel header5 = new JPanel();
		header5.setBackground(new Color(255, 215, 0));
		GridBagConstraints gbc_header5 = new GridBagConstraints();
		gbc_header5.fill = GridBagConstraints.BOTH;
		gbc_header5.insets = new Insets(0, 0, 5, 0);
		gbc_header5.gridx = 0;
		gbc_header5.gridy = 0;
		changeUserPIN.add(header5, gbc_header5);
		
		JLabel headerText5 = new JLabel("Wakai's ATM");
		headerText5.setHorizontalAlignment(SwingConstants.CENTER);
		headerText5.setFont(new Font("Tahoma", Font.PLAIN, 30));
		headerText5.setBackground(new Color(255, 215, 0));
		header5.add(headerText5);
		
		JPanel changePINcontainer = new JPanel();
		changePINcontainer.setBackground(new Color(0, 0, 0));
		GridBagConstraints gbc_changePINcontainer = new GridBagConstraints();
		gbc_changePINcontainer.anchor = GridBagConstraints.SOUTH;
		gbc_changePINcontainer.gridx = 0;
		gbc_changePINcontainer.gridy = 1;
		changeUserPIN.add(changePINcontainer, gbc_changePINcontainer);
		GridBagLayout gbl_changePINcontainer = new GridBagLayout();
		gbl_changePINcontainer.columnWidths = new int[]{346, 33, 91, 0};
		gbl_changePINcontainer.rowHeights = new int[]{32, 55, 32, 55, 32, 55, 33, 0};
		gbl_changePINcontainer.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_changePINcontainer.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		changePINcontainer.setLayout(gbl_changePINcontainer);
		
		JButton changePINbutton = new JButton("ENTER");
		JPasswordField confirmNewPIN = new JPasswordField();
		GridBagConstraints gbc_confirmNewPIN = new GridBagConstraints();
		gbc_confirmNewPIN.anchor = GridBagConstraints.NORTH;
		gbc_confirmNewPIN.fill = GridBagConstraints.HORIZONTAL;
		gbc_confirmNewPIN.insets = new Insets(0, 0, 5, 5);
		gbc_confirmNewPIN.gridx = 0;
		gbc_confirmNewPIN.gridy = 5;
		changePINcontainer.add(confirmNewPIN, gbc_confirmNewPIN);
		confirmNewPIN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				boolean correct=true;
				int count=confirmNewPIN.getPassword().length;
				char[] set= new char[count];
				for(int i=0; i<count;i++) {
					set[i]= confirmNewPIN.getPassword()[i];
				}
		        for (char check : set) {
		           if (!Character.isDigit(check)) {
		        	   for(int i=0; i<set.length;i++) {
		        		   set[i]=0;
		        	   }
		        	   
		                correct = !correct;
		                break;
		            }
				}
		        if (!correct) {
		        	confirmNewPIN.setText("");
		        	JOptionPane.showMessageDialog(frame, "Invalid Input!");
		        }
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					changePINbutton.doClick();
				}
			}
		});
		confirmNewPIN.setHorizontalAlignment(SwingConstants.CENTER);
		confirmNewPIN.setFont(new Font("Tahoma", Font.PLAIN, 40));
		confirmNewPIN.setColumns(4);
		
		JPasswordField oldPIN = new JPasswordField();
		GridBagConstraints gbc_oldPIN = new GridBagConstraints();
		gbc_oldPIN.anchor = GridBagConstraints.NORTH;
		gbc_oldPIN.fill = GridBagConstraints.HORIZONTAL;
		gbc_oldPIN.insets = new Insets(0, 0, 5, 5);
		gbc_oldPIN.gridx = 0;
		gbc_oldPIN.gridy = 1;
		changePINcontainer.add(oldPIN, gbc_oldPIN);
		oldPIN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				boolean correct=true;
				
				if (correct) {
			        for (char check : oldPIN.getPassword()) {
				           if (!Character.isDigit(check)) {
			                correct = false;
			                break;
			            }
			        }
				}
				
				 if(oldPIN.getPassword().length>4 || !correct) {
					JOptionPane.showMessageDialog(frame, "PIN must only have 4-digits number");
					oldPIN.setText("");
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					changePINbutton.doClick();
				}
			}
		});
		oldPIN.setHorizontalAlignment(SwingConstants.CENTER);
		oldPIN.setFont(new Font("Tahoma", Font.PLAIN, 40));
		oldPIN.setColumns(4);
		
		JPasswordField newPIN = new JPasswordField();
		GridBagConstraints gbc_newPIN = new GridBagConstraints();
		gbc_newPIN.anchor = GridBagConstraints.NORTH;
		gbc_newPIN.fill = GridBagConstraints.HORIZONTAL;
		gbc_newPIN.insets = new Insets(0, 0, 5, 5);
		gbc_newPIN.gridx = 0;
		gbc_newPIN.gridy = 3;
		changePINcontainer.add(newPIN, gbc_newPIN);
		newPIN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				boolean correct=true;
				
				if (correct) {
			        for (char check : newPIN.getPassword()) {
				           if (!Character.isDigit(check)) {
			                correct = false;
			                break;
			            }
			        }
				}
				 if(newPIN.getPassword().length>4 || !correct) {
					JOptionPane.showMessageDialog(frame, "PIN must only have 4-digits number");
					newPIN.setText("");
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					changePINbutton.doClick();
				}
			}
		});
		newPIN.setHorizontalAlignment(SwingConstants.CENTER);
		newPIN.setFont(new Font("Tahoma", Font.PLAIN, 40));
		newPIN.setColumns(4);
		
		GridBagConstraints gbc_changePINbutton = new GridBagConstraints();
		gbc_changePINbutton.anchor = GridBagConstraints.NORTHWEST;
		gbc_changePINbutton.gridx = 2;
		gbc_changePINbutton.gridy = 6;
		changePINcontainer.add(changePINbutton, gbc_changePINbutton);
		changePINbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date date = new Date();
				String oldUserPIN = userPIN,
						confirmOldUserPIN=new String(oldPIN.getPassword()),
						newUserPIN = new String(newPIN.getPassword()),
						confirmNewUserPIN = new String(confirmNewPIN.getPassword());
				boolean canChangePIN=true;
				
				if(confirmOldUserPIN.equals("") || newUserPIN.equals("") || confirmNewUserPIN.equals("")) {
					oldPIN.setText("");
					newPIN.setText("");
					confirmNewPIN.setText("");
					JOptionPane.showMessageDialog(frame, "Don't leave empty PIN");
					canChangePIN=false;
				}else
				
				if(!oldUserPIN.equals(confirmOldUserPIN)) {	
					oldPIN.setText("");
					newPIN.setText("");
					confirmNewPIN.setText("");
					JOptionPane.showMessageDialog(frame, "Must be your OLD PIN");
					canChangePIN=false;
				}else
				
				if(!newUserPIN.equals(confirmNewUserPIN)) {
					oldPIN.setText("");
					newPIN.setText("");
					confirmNewPIN.setText("");
					JOptionPane.showMessageDialog(frame, "Unmatching NEW PIN and CONFIRM NEW PIN");
					canChangePIN=false;
				}else
					
				if(newUserPIN.equals(confirmOldUserPIN)) {
					oldPIN.setText("");
					newPIN.setText("");
					confirmNewPIN.setText("");
					JOptionPane.showMessageDialog(frame, "NEW PIN must not be the same as OLD PIN");
					canChangePIN=false;
				}
				
				if(canChangePIN) {
					try {
						String query = "UPDATE Accounts SET PIN=? WHERE Name=? AND PIN=?";
						PreparedStatement ps = attachSQL.prepareStatement(query);
						ps.setString(1, newUserPIN);
						ps.setString(2, username);
						ps.setString(3, userPIN);
						ps.executeUpdate();
						JOptionPane.showMessageDialog(frame, "Success!");
							CardLayout layout = (CardLayout) frame.getContentPane().getLayout();
							layout.show(frame.getContentPane(),"Receipt");
							receiptText.setText("<html><p style=\"text-align: center;\">"
									+ "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^<br>"
									+ "Transaction Receipt of "+username
									+ "<br>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~<br>"
									+ "You have "+act+"<br><br>"
									+ "Date of Transaction:<br>"+date
									+ "<br>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
									+ "<br>NEW PIN CONFIRMED"
									+ "<br>^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^</p>");
							oldPIN.setText("");
							newPIN.setText("");
							confirmNewPIN.setText("");
							userPIN=newUserPIN;
							
						ps.close();
						act=null;
					}catch(Exception x) {
						JOptionPane.showMessageDialog(frame, x.getMessage());
					}
				}
				oldUserPIN = "";
				newUserPIN = "";
				confirmNewUserPIN = "";
			}
		});
		changePINbutton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		changePINbutton.setBackground(new Color(255, 215, 0));
		
		JLabel lblChangeUserPin_1_1 = new JLabel("Old User PIN:");
		GridBagConstraints gbc_lblChangeUserPin_1_1 = new GridBagConstraints();
		gbc_lblChangeUserPin_1_1.fill = GridBagConstraints.BOTH;
		gbc_lblChangeUserPin_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblChangeUserPin_1_1.gridx = 0;
		gbc_lblChangeUserPin_1_1.gridy = 0;
		changePINcontainer.add(lblChangeUserPin_1_1, gbc_lblChangeUserPin_1_1);
		lblChangeUserPin_1_1.setForeground(new Color(255, 215, 0));
		lblChangeUserPin_1_1.setFont(new Font("Consolas", Font.PLAIN, 20));

		JLabel lblNewUserPin = new JLabel("New User PIN:");
		GridBagConstraints gbc_lblNewUserPin = new GridBagConstraints();
		gbc_lblNewUserPin.fill = GridBagConstraints.BOTH;
		gbc_lblNewUserPin.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewUserPin.gridx = 0;
		gbc_lblNewUserPin.gridy = 2;
		changePINcontainer.add(lblNewUserPin, gbc_lblNewUserPin);
		lblNewUserPin.setForeground(new Color(255, 215, 0));
		lblNewUserPin.setFont(new Font("Consolas", Font.PLAIN, 20));
		
		JLabel lblChangeUserPin = new JLabel("Confirm New User PIN:");
		GridBagConstraints gbc_lblChangeUserPin = new GridBagConstraints();
		gbc_lblChangeUserPin.fill = GridBagConstraints.BOTH;
		gbc_lblChangeUserPin.insets = new Insets(0, 0, 5, 5);
		gbc_lblChangeUserPin.gridx = 0;
		gbc_lblChangeUserPin.gridy = 4;
		changePINcontainer.add(lblChangeUserPin, gbc_lblChangeUserPin);
		lblChangeUserPin.setForeground(new Color(255, 215, 0));
		lblChangeUserPin.setFont(new Font("Consolas", Font.PLAIN, 20));
		
		JPanel fundTransfer = new JPanel();
		fundTransfer.setBackground(Color.BLACK);
		frame.getContentPane().add(fundTransfer, "Transfer Fund");
		GridBagLayout gbl_fundTransfer = new GridBagLayout();
		gbl_fundTransfer.columnWidths = new int[]{881, 0};
		gbl_fundTransfer.rowHeights = new int[]{66, 65, 309, 0};
		gbl_fundTransfer.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_fundTransfer.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		fundTransfer.setLayout(gbl_fundTransfer);
		
		JPanel header6 = new JPanel();
		header6.setBackground(new Color(255, 215, 0));
		GridBagConstraints gbc_header6 = new GridBagConstraints();
		gbc_header6.fill = GridBagConstraints.BOTH;
		gbc_header6.insets = new Insets(0, 0, 5, 0);
		gbc_header6.gridx = 0;
		gbc_header6.gridy = 0;
		fundTransfer.add(header6, gbc_header6);
		
		JLabel headerText6 = new JLabel("Wakai's ATM");
		headerText6.setHorizontalAlignment(SwingConstants.CENTER);
		headerText6.setFont(new Font("Tahoma", Font.PLAIN, 30));
		headerText6.setBackground(new Color(255, 215, 0));
		header6.add(headerText6);
		
		JPanel transferContainer = new JPanel();
		transferContainer.setBackground(Color.BLACK);
		GridBagConstraints gbc_transferContainer = new GridBagConstraints();
		gbc_transferContainer.fill = GridBagConstraints.VERTICAL;
		gbc_transferContainer.gridx = 0;
		gbc_transferContainer.gridy = 2;
		fundTransfer.add(transferContainer, gbc_transferContainer);
		GridBagLayout gbl_transferContainer = new GridBagLayout();
		gbl_transferContainer.columnWidths = new int[]{341, 119, 0};
		gbl_transferContainer.rowHeights = new int[]{27, 51, 27, 51, 27, 51, 42, 0};
		gbl_transferContainer.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_transferContainer.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		transferContainer.setLayout(gbl_transferContainer);
		
		JButton transferButton = new JButton("ENTER");
		JTextField transferAcc = new JTextField();
		transferAcc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					transferButton.doClick();
				}
			}
		});
		transferAcc.setFont(new Font("Tahoma", Font.PLAIN, 30));
		transferAcc.setHorizontalAlignment(SwingConstants.CENTER);
		transferAcc.setColumns(10);
		GridBagConstraints gbc_transferAcc = new GridBagConstraints();
		gbc_transferAcc.fill = GridBagConstraints.BOTH;
		gbc_transferAcc.insets = new Insets(0, 0, 5, 5);
		gbc_transferAcc.gridx = 0;
		gbc_transferAcc.gridy = 1;
		transferContainer.add(transferAcc, gbc_transferAcc);
		
		JTextField transferBal = new JTextField();
		transferBal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					transferButton.doClick();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				boolean correct=true;
				int count=transferBal.getText().length();
				char[] set= new char[count];
				for(int i=0; i<count;i++) {
					set[i]= transferBal.getText().charAt(i);
				}

		        for (char check : set) {
		        	if(check=='.') {
		        		continue;
		        	}else if (!Character.isDigit(check)) {
		        	   
		        	   
		        	   for(int i=0; i<set.length;i++) {
		        		   set[i]=0;
		        	   }
		        	   
		                correct = !correct;
		                break;
		            }
				}
		        if (!correct) {
		        	transferBal.setText("");
		        	JOptionPane.showMessageDialog(frame, "Invalid Input!");
		        }
			}
		});
		
		JPasswordField transferPIN = new JPasswordField();
		transferPIN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					transferButton.doClick();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				boolean correct=true;
				
				if (correct) {
			        for (char check : transferPIN.getPassword()) {
				           if (!Character.isDigit(check)) {
			                correct = false;
			                break;
			            }
			        }
				}
				 if(transferPIN.getPassword().length>4 || !correct) {
					JOptionPane.showMessageDialog(frame, "PIN must only have 4-digits number");
					transferPIN.setText("");
				}
			}
		});
		
		transferButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date date = new Date();
				String compareTransfer=null,
						accountTransfer= transferAcc.getText();
				double fromTransfer=0,
						balanceTransfer= Double.parseDouble(transferBal.getText());
				String confirmPINtransfer= new String(transferPIN.getPassword());
				
				boolean beginTransfer=true;

				if(transferAcc.getText().equals("") || transferBal.getText().equals("") || new String(transferPIN.getPassword()).equals("")) {
					transferAcc.setText("");
					transferBal.setText("");
					transferPIN.setText("");
					JOptionPane.showMessageDialog(frame, "Fill up the form to confirm transaction!");
					beginTransfer=false;
				}else
					
				if(username.equals(accountTransfer)) {
					transferAcc.setText("");
					transferBal.setText("");
					transferPIN.setText("");
					JOptionPane.showMessageDialog(frame, "YOU MUST NOT USE YOUR OWN USERNAME!");
					beginTransfer=false;
				}else
				
				if(balanceTransfer<=0 || balanceTransfer>balance) {
					transferAcc.setText("");
					transferBal.setText("");
					transferPIN.setText("");
					JOptionPane.showMessageDialog(frame, "Invalid input, value must be positive and less than your balance!");
					beginTransfer=false;
				}else
					
				if(!confirmPINtransfer.equals(userPIN)) {
					transferAcc.setText("");
					transferBal.setText("");
					transferPIN.setText("");
					JOptionPane.showMessageDialog(frame, "Incorrect User PIN!");
					beginTransfer=false;
				}
				if(beginTransfer){
					try {
						String query = "SELECT * FROM Accounts WHERE Name=?";
						PreparedStatement ps = attachSQL.prepareStatement(query);
						ps.setString(1, accountTransfer);
						
						ResultSet rs = ps.executeQuery();
						int count = 0;
						while(rs.next()) {
							compareTransfer = rs.getString("Name");
							fromTransfer=rs.getDouble("Balance");
							count++;
						}
						if(count==1) {
							JOptionPane.showMessageDialog(frame, "Success!");
							
							CardLayout layout = (CardLayout) frame.getContentPane().getLayout();
							layout.next(frame.getContentPane());
						}else
						if(count>1){
							JOptionPane.showMessageDialog(frame, "Duplicated");
						}else {
							JOptionPane.showMessageDialog(frame, "Unsuccessful");
						}
						rs.close();

						if(compareTransfer.equals(accountTransfer)) {
							balance-=balanceTransfer;
							fromTransfer+=balanceTransfer;
							
							query = "UPDATE Accounts SET Balance=? WHERE Name=?;UPDATE Accounts SET Balance=? WHERE Name=?;";
							ps = attachSQL.prepareStatement(query);
							ps.setDouble(1, balance);
							ps.setString(2, username);
							ps.setDouble(1, fromTransfer);
							ps.setString(2, compareTransfer);
							ps.executeUpdate();
							
							CardLayout layout = (CardLayout) frame.getContentPane().getLayout();
							layout.show(frame.getContentPane(),"Receipt");
							receiptText.setText("<html><p style=\"text-align: center;\">"
									+ "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^<br>"
									+ "Transaction Receipt of "+username
									+ "<br>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~<br>"
									+ "You have "+act
									+ " an amount of<br>Php. "+balanceTransfer
									+ "<br>to "+compareTransfer+"<br><br>"
									+ "Date of Transaction:<br>"+date
									+ "<br>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
									+ "<br>Balance: Php. "+balance
									+ "<br>^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^</p>");
							transferAcc.setText("");
							transferBal.setText("");
							transferPIN.setText("");
						}
					}catch(Exception x) {
						transferAcc.setText("");
						transferBal.setText("");
						transferPIN.setText("");
						JOptionPane.showMessageDialog(frame, x.getMessage());
					}	
				}else {
					CardLayout layout = (CardLayout) frame.getContentPane().getLayout();
					layout.show(frame.getContentPane(), "Main Menu");
				}
			}
		});
		transferButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		transferButton.setBackground(new Color(255, 215, 0));
		GridBagConstraints gbc_transferButton = new GridBagConstraints();
		gbc_transferButton.fill = GridBagConstraints.BOTH;
		gbc_transferButton.gridx = 1;
		gbc_transferButton.gridy = 6;
		transferContainer.add(transferButton, gbc_transferButton);
		
		JLabel lblChangeUserPin_1_1_1 = new JLabel("Enter Username to Transfer Balance:");
		lblChangeUserPin_1_1_1.setForeground(new Color(255, 215, 0));
		lblChangeUserPin_1_1_1.setFont(new Font("Consolas", Font.PLAIN, 20));
		GridBagConstraints gbc_lblChangeUserPin_1_1_1 = new GridBagConstraints();
		gbc_lblChangeUserPin_1_1_1.fill = GridBagConstraints.BOTH;
		gbc_lblChangeUserPin_1_1_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblChangeUserPin_1_1_1.gridwidth = 2;
		gbc_lblChangeUserPin_1_1_1.gridx = 0;
		gbc_lblChangeUserPin_1_1_1.gridy = 0;
		transferContainer.add(lblChangeUserPin_1_1_1, gbc_lblChangeUserPin_1_1_1);
		
		JLabel lblEnterAmountOf = new JLabel("Enter Amount of Balance to Transfer:");
		lblEnterAmountOf.setForeground(new Color(255, 215, 0));
		lblEnterAmountOf.setFont(new Font("Consolas", Font.PLAIN, 20));
		GridBagConstraints gbc_lblEnterAmountOf = new GridBagConstraints();
		gbc_lblEnterAmountOf.fill = GridBagConstraints.BOTH;
		gbc_lblEnterAmountOf.insets = new Insets(0, 0, 5, 0);
		gbc_lblEnterAmountOf.gridwidth = 2;
		gbc_lblEnterAmountOf.gridx = 0;
		gbc_lblEnterAmountOf.gridy = 2;
		transferContainer.add(lblEnterAmountOf, gbc_lblEnterAmountOf);
		transferBal.setFont(new Font("Tahoma", Font.PLAIN, 30));
		transferBal.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_transferBal = new GridBagConstraints();
		gbc_transferBal.fill = GridBagConstraints.BOTH;
		gbc_transferBal.insets = new Insets(0, 0, 5, 5);
		gbc_transferBal.gridx = 0;
		gbc_transferBal.gridy = 3;
		transferContainer.add(transferBal, gbc_transferBal);
		transferBal.setColumns(10);
		
		JLabel lblEnterYourPin = new JLabel("Enter your PIN to Confirm:");
		lblEnterYourPin.setForeground(new Color(255, 215, 0));
		lblEnterYourPin.setFont(new Font("Consolas", Font.PLAIN, 20));
		GridBagConstraints gbc_lblEnterYourPin = new GridBagConstraints();
		gbc_lblEnterYourPin.fill = GridBagConstraints.BOTH;
		gbc_lblEnterYourPin.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnterYourPin.gridx = 0;
		gbc_lblEnterYourPin.gridy = 4;
		transferContainer.add(lblEnterYourPin, gbc_lblEnterYourPin);
		transferPIN.setHorizontalAlignment(SwingConstants.CENTER);
		transferPIN.setFont(new Font("Tahoma", Font.PLAIN, 40));
		transferPIN.setColumns(4);
		GridBagConstraints gbc_transferPIN = new GridBagConstraints();
		gbc_transferPIN.fill = GridBagConstraints.BOTH;
		gbc_transferPIN.insets = new Insets(0, 0, 5, 5);
		gbc_transferPIN.gridx = 0;
		gbc_transferPIN.gridy = 5;
		transferContainer.add(transferPIN, gbc_transferPIN);
		
		JPanel header7 = new JPanel();
		header7.setBounds(0, 0, 881, 61);
		header7.setBackground(new Color(255, 215, 0));
		receipt.add(header7);
		
		JLabel headerText7 = new JLabel("Wakai's ATM");
		headerText7.setHorizontalAlignment(SwingConstants.CENTER);
		headerText7.setFont(new Font("Tahoma", Font.PLAIN, 30));
		headerText7.setBackground(new Color(255, 215, 0));
		header7.add(headerText7);
		
		JPanel Admin = new JPanel();
		Admin.setBackground(Color.BLACK);
		frame.getContentPane().add(Admin, "Admin");
		Admin.setLayout(null);
		
		JPanel header8 = new JPanel();
		header8.setBackground(Color.RED);
		header8.setBounds(0, 0, 881, 61);
		Admin.add(header8);
		
		JLabel headerText6_1 = new JLabel("Wakai's ATM");
		headerText6_1.setHorizontalAlignment(SwingConstants.CENTER);
		headerText6_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		headerText6_1.setBackground(new Color(255, 215, 0));
		header8.add(headerText6_1);
		DefaultTableModel model = new DefaultTableModel();
		JButton checkAcc = new JButton("Check Account");
		checkAcc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
						
						
			        	String find = "SELECT * FROM Accounts";
						PreparedStatement nigga = attachSQL.prepareStatement(find);;
						ResultSet rs = nigga.executeQuery();
			  
			            ResultSetMetaData metaData = rs.getMetaData();
			            int columnCount = metaData.getColumnCount();

			            for (int column = 1; column <= columnCount; column++) {
			                model.addColumn(metaData.getColumnName(column));
			            }

			            while (rs.next()) {
			                Object[] row = new Object[columnCount];
			                for (int column = 1; column <= columnCount; column++) {
			                    row[column - 1] = rs.getObject(column);
			                }
			                model.addRow(row);
			            }

			        } catch (SQLException x) {
			            x.printStackTrace();
			        }
				
				CardLayout layout = (CardLayout) frame.getContentPane().getLayout();
				layout.show(frame.getContentPane(), "Check Account");
			}
		});
		checkAcc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		checkAcc.setBounds(536, 235, 175, 45);
		Admin.add(checkAcc);
		
		JButton createAcc = new JButton("Create Account");
		createAcc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		createAcc.setBounds(182, 235, 175, 45);
		Admin.add(createAcc);
		
		JButton delAcc = new JButton("Delete Account");
		delAcc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		delAcc.setBounds(182, 313, 175, 45);
		Admin.add(delAcc);
		
		JButton exitAcc = new JButton("Exit");
		exitAcc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout layout = (CardLayout) frame.getContentPane().getLayout();
				layout.show(frame.getContentPane(), "UsernameGUI");
			}
		});
		exitAcc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		exitAcc.setBounds(536, 313, 175, 45);
		Admin.add(exitAcc);
		
		JPanel accDescription = new JPanel();
		accDescription.setBounds(182, 100, 529, 116);
		Admin.add(accDescription);
		accDescription.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Welcome back, Wakai!");
		lblNewLabel_1.setBounds(10, 5, 509, 100);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		accDescription.add(lblNewLabel_1);
		
		JPanel accountsGui = new JPanel();
		accountsGui.setBackground(Color.BLACK);
		frame.getContentPane().add(accountsGui, "Check Account");
		accountsGui.setLayout(null);
		
		JPanel header9 = new JPanel();
		header9.setBackground(Color.RED);
		header9.setBounds(0, 0, 881, 61);
		accountsGui.add(header9);
		
		JLabel headerText6_1_1 = new JLabel("Wakai's ATM");
		headerText6_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		headerText6_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		headerText6_1_1.setBackground(new Color(255, 215, 0));
		header9.add(headerText6_1_1);
        

		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout layout = (CardLayout) frame.getContentPane().getLayout();
				layout.show(frame.getContentPane(), "Admin");
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1.setBounds(669, 371, 120, 52);
		accountsGui.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(196, 132, 562, 228);
		accountsGui.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
		frame.setVisible(true);
	}
}

