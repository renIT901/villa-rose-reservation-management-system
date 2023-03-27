import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Rectangle;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.Label;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ForgotPassword {

	JFrame frame;
	private JTextField txtEmail;
	private JTextField txtUsername;
	private JTextField txtSecurityQuestion;
	private JTextField txtAnswer;
	private JPasswordField txtPass;
	String employee_id;
	String securityQ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgotPassword window = new ForgotPassword();
					window.frame.setVisible(true);
					window.frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	public ForgotPassword() {
		initialize();
	}


	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(250, 245, 232));
		frame.getContentPane().setForeground(new Color(250, 245, 232));
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Reset Password");
		lblNewLabel.setBounds(262, 60, 298, 52);
		lblNewLabel.setFont(new Font("Calibri Light", Font.PLAIN, 40));
		frame.getContentPane().add(lblNewLabel);
		
		JCheckBox shpword = new JCheckBox("Show Password");
		shpword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(shpword.isSelected()) {
					txtPass.setEchoChar((char)0);
				}else {
					txtPass.setEchoChar('*');
				}
			}
		});
		shpword.setBackground(new Color(250, 245, 232));
		shpword.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		shpword.setBounds(324, 424, 113, 23);
		frame.getContentPane().add(shpword);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		txtEmail.setForeground(new Color(159, 159, 159));
		txtEmail.setText("Email");
		txtEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent event) {
				if(txtEmail.getText().equals("Email")) {
					txtEmail.setText("");
					txtEmail.setForeground(new Color(0, 0, 0));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(txtEmail.getText().equals("")) {
					txtEmail.setText("Email");
					txtEmail.setForeground(new Color(159, 159, 159));
				}			
			}
		});
		txtEmail.setBounds(235, 152, 300, 25);
		frame.getContentPane().add(txtEmail);

		txtEmail.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Go back to Login");
		lblNewLabel_2.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				Login lpage = new Login();
				lpage.setLocationRelativeTo(null);
				lpage.show();
			}
		});
		lblNewLabel_2.setBounds(335, 500, 120, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		txtUsername = new JTextField("Username");
		txtUsername.setForeground(new Color(159, 159, 159));
		txtUsername.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		txtUsername.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent event) {
				if(txtUsername.getText().equals("Username")) {
					txtUsername.setText("");
					txtUsername.setForeground(new Color(0, 0, 0));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(txtUsername.getText().equals("")) {
					txtUsername.setText("Username");
					txtUsername.setForeground(new Color(159, 159, 159));
				}			
			}
		});
		txtUsername.setText("Username");
		txtUsername.setColumns(10);
		txtUsername.setBounds(235, 195, 300, 25);
		txtUsername.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		frame.getContentPane().add(txtUsername);
		
		JButton btnProceed = new JButton("Search");
		btnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				conn = sqliteConnection.dbConnector();
				String email = txtEmail.getText().toLowerCase();
				String username = txtUsername.getText();
				pst = conn.prepareStatement("SELECT * FROM Employee WHERE email =" + "'"+email+"' and uname =" + "'"+username+"'");
				rs = pst.executeQuery();
				if(rs.next()==true) {
					employee_id = rs.getString(1);
					securityQ = rs.getString("SQuestion");
					txtSecurityQuestion.setText(securityQ);
				}
				else {
					JOptionPane.showMessageDialog(null, "Username or Email is Incorrect.");
				}
				
				}
				catch (Exception e1) {
					
					System.out.println(e1);
				}finally {
					try {
						rs.close();
						pst.close();
						conn.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
			}
		});
		btnProceed.setHorizontalTextPosition(SwingConstants.CENTER);
		btnProceed.setForeground(Color.BLACK);
		btnProceed.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		btnProceed.setBorderPainted(false);
		btnProceed.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnProceed.setBackground(new Color(225, 167, 48));
		btnProceed.setAlignmentY(0.0f);
		btnProceed.setBounds(295, 231, 170, 30);
		frame.getContentPane().add(btnProceed);
		
		txtSecurityQuestion = new JTextField();
		txtSecurityQuestion.setText("Security Question");
		txtSecurityQuestion.setForeground(Color.GRAY);
		txtSecurityQuestion.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		txtSecurityQuestion.setColumns(10);
		txtSecurityQuestion.setEditable(false);
		txtSecurityQuestion.setBounds(235, 305, 300, 25);
		frame.getContentPane().add(txtSecurityQuestion);
		
		txtAnswer = new JTextField();
		txtAnswer.setText("Answer");
		txtAnswer.setForeground(new Color(159, 159, 159));
		txtAnswer.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		txtAnswer.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent event) {
				if(txtAnswer.getText().equals("Answer")) {
					txtAnswer.setText("");
					txtAnswer.setForeground(new Color(0, 0, 0));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(txtAnswer.getText().equals("")) {
					txtAnswer.setText("Answer");
					txtAnswer.setForeground(new Color(159, 159, 159));
				}			
			}
		});
		txtAnswer.setColumns(10);
		txtAnswer.setBounds(235, 348, 300, 25);
		frame.getContentPane().add(txtAnswer);
		
		txtPass = new JPasswordField("Enter New Password");
		txtPass.setBounds(235, 391, 300, 25);
		txtPass.setEchoChar((char)0);
		txtPass.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		txtPass.setForeground(new Color(159, 159, 159));
		txtPass.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent event) {
				if(txtPass.getText().equals("Enter New Password")) {
					txtPass.setEchoChar('*');
					txtPass.setText("");
					txtPass.setForeground(new Color(0, 0, 0));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(txtPass.getText().equals("")) {
					txtPass.setEchoChar((char)0);
					txtPass.setText("Enter New Password");
					txtPass.setForeground(new Color(159, 159, 159));
				}			
			}
		});
		frame.getContentPane().add(txtPass);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conn = sqliteConnection.dbConnector();
					String ans = txtAnswer.getText();
					String pass = txtPass.getText();
					if(securityQ == null && employee_id == null) {
						JOptionPane.showMessageDialog(null, "Please Search for Email and Username.");
					}
					
					pst = conn.prepareStatement("UPDATE Employee SET pword=? WHERE employee_id=? and sq_ans=?");
					pst.setString(1, pass);
					PasswordValidator validator = new PasswordValidator();
					pst.setString(2, employee_id);
					pst.setString(3, ans);
					
					int k = pst.executeUpdate();
					if(k==1) {
						if(validator.validate(pass)) {
						JOptionPane.showMessageDialog(null, "Password has been succesfully changed!");
						frame.dispose();
						Login lpage = new Login();
						lpage.setLocationRelativeTo(null);
						lpage.show();
						txtEmail.setText("Email");
						txtUsername.setText("Username");
						txtSecurityQuestion.setText("Security Question");
						txtAnswer.setText("Answer");
						txtPass.setText("Enter New Password");
						}
						else
							JOptionPane.showMessageDialog(null, "Password must be 8 characters long, and must contain a digit, a lowercase letter, an uppercase letter, and a special character.");
							txtPass.setText("");
					}
					else
						JOptionPane.showMessageDialog(null, "Incorrect answer. Please try again.");
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "An error was encountered while changing password.");
					e1.printStackTrace();
				} finally {
					try {
						pst.close();
						conn.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnSubmit.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSubmit.setForeground(Color.BLACK);
		btnSubmit.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		btnSubmit.setBorderPainted(false);
		btnSubmit.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnSubmit.setBackground(new Color(225, 167, 48));
		btnSubmit.setAlignmentY(0.0f);
		btnSubmit.setBounds(295, 454, 170, 30);
		frame.getContentPane().add(btnSubmit);
		frame.setBounds(new Rectangle(0, 0, 800, 600));
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}



}