import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.Random;

import javax.swing.*;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Component;
import java.awt.ComponentOrientation;
import javax.swing.border.EtchedBorder;
import java.awt.Rectangle;



public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtuname;
	private JPasswordField txtpword;
    private static final String CHARACTERS = "ABCDEFGHJKMNOPQRSTUVWXYZabcdefghjkmnopqrstuvwxyz0123456789";
    private static final int LENGTH = 6;
    private static int userid;

	/**
	 * Launch the application.
	 */
    private static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	Connection conn = null;
	private JTextField captcha;
	private JTextField captcha_ans;
	Image img1 = new ImageIcon(this.getClass().getResource("/1.png")).getImage();
	Image img2 = new ImageIcon(this.getClass().getResource("/2.png")).getImage();
	Image img3 = new ImageIcon(this.getClass().getResource("/3.png")).getImage();
	Image img4 = new ImageIcon(this.getClass().getResource("/villarose.png")).getImage();
	
	public Login() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 245, 232));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String captcha1 = generateRandomString(LENGTH);
		
		captcha = new JTextField(captcha1);
		captcha.setEditable(false);
		captcha.setBorder(new LineBorder(Color.BLACK, 1, true));
		captcha.setBounds(276, 324, 233, 43);
		captcha.setFont(new Font("SansSerif", Font.PLAIN, 20));
		contentPane.add(captcha);
		captcha.setColumns(10);
		
		txtuname = new JTextField();
		txtuname.setBorder(new LineBorder(Color.BLACK, 1, true));
		txtuname.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtuname.setBounds(276, 231, 233, 24);
		contentPane.add(txtuname);
		txtuname.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBorderPainted(false);
		btnLogin.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnLogin.setAlignmentY(Component.TOP_ALIGNMENT);
		btnLogin.setForeground(new Color(0, 0, 0));
		btnLogin.setHorizontalTextPosition(SwingConstants.CENTER);
		btnLogin.setBackground(new Color(225, 167, 48));
		btnLogin.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					conn = sqliteConnection.dbConnector();
					String uname = txtuname.getText();
					String pword = txtpword.getText();
					String ans = captcha_ans.getText();
					
					Statement stm = conn.createStatement();
					String sql = "SELECT * from Employee where uname='"+uname+"' and pword='"+pword+"'";
					ResultSet rs = stm.executeQuery(sql);
					if(rs.next()) {
						userid = rs.getInt("employee_id");
						System.out.println(userid);
						if(ans.equals(captcha1)) {
						dispose();
						 String message = "Welcome to Villa Rose Reservation Management System!\n\n"
					                + "By using this application, you agree to the following terms and conditions:\n\n"
					                + "1. Booking reservation is confirmed once 50% deposit is paid.\n\n"
					                + "2. Full payment shall be settled upon check-in.\n\n"
					                + "3. Early room check-in is considered once room is available.\n\n"
					                + "4. Strictly NO REFUND for any deposit made, but we will allow for booking rescheduling.\n\n"
					                + "5. A late check-out after 12 noon will be charged 500PHP per hour.\n\n"
					                + "6. Toiletries and dishwashing liquid are not included. These are available for sale ℅ the Caretaker. \n\n"
					                + "7. Griller (Ihawan) available for guest use.\n\n"
					                + "8. Charcoal (Uling) available for sale ℅ the Caretaker.\n\n"
					                + "9. Things such as tent, karaoke, beach umbrella brought outside the premises will be charged accordingly by The Management.\n\n"
					                + "10. Resort furnitures is strictly not allowed to be moved elsewhere.\n\n\n"
					                + "Payment Options\n\n"
					                + "Direct PNB\n"
					                + "Villa Rose Resort\n"
					                + "2284104059596\n\n"
					                + "or\n\n"
					                + "Gcash to PNB\n\n"
					                + "*A copy of the deposit slip should be sent, once payment is made.\n\n"
					                + "Click 'Agree' to accept these terms and start using the application.";
						TermsandCondition(message);
					
						}
						else {
							JOptionPane.showMessageDialog(null, "Captcha is incorrect.");
						}
					}else {
						JOptionPane.showMessageDialog(null, "Username or password is incorrect.");
						txtuname.setText("");
						txtpword.setText("");
					}
					conn.close();
					
				}catch(Exception e) {
					System.out.println(e);
				}
			}
		});
		btnLogin.setBounds(330, 449, 125, 40);
		contentPane.add(btnLogin);
		
		JLabel password_icon = new JLabel("");
		password_icon.setBounds(235, 265, 20, 20);
		password_icon.setIcon(new ImageIcon(img2));
		contentPane.add(password_icon);
		
		txtpword = new JPasswordField();
		txtpword.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		txtpword.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtpword.setBounds(276, 265, 233, 24);
		contentPane.add(txtpword);
		
		JLabel lblNewLabel_2 = new JLabel("Reservation Management System");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("SansSerif", Font.PLAIN, 30));
		lblNewLabel_2.setBounds(165, 165, 456, 38);
		contentPane.add(lblNewLabel_2);
		
		JCheckBox shpword = new JCheckBox("Show Password");
		shpword.setFont(new Font("SansSerif", Font.PLAIN, 16));
		shpword.setBackground(new Color(250, 245, 232));
		shpword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(shpword.isSelected()) {
					txtpword.setEchoChar((char)0);
				}else {
					txtpword.setEchoChar('*');
				}
			}
		});
		shpword.setBounds(319, 295, 147, 23);
		contentPane.add(shpword);
		
		JLabel lblNewLabel_3 = new JLabel("Type the code you see above.");
		lblNewLabel_3.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(276, 377, 222, 24);
		contentPane.add(lblNewLabel_3);
		
		captcha_ans = new JTextField();
		captcha_ans.setBorder(new LineBorder(Color.BLACK, 1, true));
		captcha_ans.setFont(new Font("SansSerif", Font.PLAIN, 16));
		captcha_ans.setBounds(276, 411, 199, 24);
		contentPane.add(captcha_ans);
		captcha_ans.setColumns(10);
		
		JLabel RegisterAcc = new JLabel("Register Account");
		RegisterAcc.setFont(new Font("SansSerif", Font.PLAIN, 16));
		RegisterAcc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					dispose();
					Register rpage = new Register();
					rpage.setLocationRelativeTo(null);
					rpage.show();
				}catch(Exception e1) {
					
				}
			}
		});
		RegisterAcc.setBounds(471, 506, 125, 24);
		contentPane.add(RegisterAcc);
		
		JLabel ForgotPassword = new JLabel("Forgot Password");
		ForgotPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				ForgotPassword fppage = new ForgotPassword();
				fppage.frame.setLocationRelativeTo(null);
				fppage.frame.show();
			}
		});
		ForgotPassword.setFont(new Font("SansSerif", Font.PLAIN, 16));
		ForgotPassword.setBounds(196, 506, 125, 24);
		contentPane.add(ForgotPassword);
		
		JLabel name_icon = new JLabel();
		name_icon.setBounds(303, 11, 150, 150);
		name_icon.setIcon(new ImageIcon(img4));
		contentPane.add(name_icon);
		
		JLabel uname_icon = new JLabel();
		uname_icon.setBounds(235, 235, 20, 20);
		uname_icon.setIcon(new ImageIcon(img3));
		contentPane.add(uname_icon);
		
		
	}
	private static void TermsandCondition(String message) {
		//JOptionPane.showMessageDialog(null, message, "Terms and Conditions", JOptionPane.INFORMATION_MESSAGE);
		int option = JOptionPane.showOptionDialog(null, message, "Terms and Conditions", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"Agree"}, "Agree");
		if (option < 0) {
			Login frame = new Login();
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
			System.out.print(option);
		}
		else {
			System.out.print(option);
			HomePage hpage = new HomePage(userid);
			hpage.setLocationRelativeTo(null);
			hpage.show();
		}
		}

	}
