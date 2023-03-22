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
	
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 481);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 245, 232));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String captcha1 = generateRandomString(LENGTH);
		
		captcha = new JTextField(captcha1);
		captcha.setEditable(false);
		captcha.setBorder(new LineBorder(Color.BLACK, 1, true));
		captcha.setBounds(181, 206, 233, 43);
		captcha.setFont(new Font("Calibri Light", Font.PLAIN, 36));
		contentPane.add(captcha);
		captcha.setColumns(10);
		
		txtuname = new JTextField();
		txtuname.setBorder(new LineBorder(Color.BLACK, 1, true));
		txtuname.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		txtuname.setBounds(181, 111, 233, 20);
		contentPane.add(txtuname);
		txtuname.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBorderPainted(false);
		btnLogin.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnLogin.setAlignmentY(Component.TOP_ALIGNMENT);
		btnLogin.setForeground(new Color(0, 0, 0));
		btnLogin.setHorizontalTextPosition(SwingConstants.CENTER);
		btnLogin.setBackground(new Color(225, 167, 48));
		btnLogin.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					conn = sqliteConnection.dbConnector();
					String uname = txtuname.getText();
					String pword = txtpword.getText();
					String ans = captcha_ans.getText();
					
					Statement stm = conn.createStatement();
					String sql = "select * from Employee where uname='"+uname+"' and pword='"+pword+"'";
					ResultSet rs = stm.executeQuery(sql);
					if(rs.next()) {
						int userid = rs.getInt("employee_id");
						System.out.println(userid);
						if(ans.equals(captcha1)) {
						dispose();
						HomePage hpage = new HomePage(userid);
						hpage.setLocationRelativeTo(null);
						hpage.show();
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
		btnLogin.setBounds(206, 309, 187, 30);
		contentPane.add(btnLogin);
		
		JLabel password_icon = new JLabel("");
		password_icon.setBounds(152, 149, 20, 20);
		password_icon.setIcon(new ImageIcon(img2));
		contentPane.add(password_icon);
		
		txtpword = new JPasswordField();
		txtpword.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		txtpword.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		txtpword.setBounds(181, 149, 233, 20);
		contentPane.add(txtpword);
		
		JLabel lblNewLabel_2 = new JLabel("Reservation Management System");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Calibri Light", Font.PLAIN, 30));
		lblNewLabel_2.setBounds(105, 44, 409, 38);
		contentPane.add(lblNewLabel_2);
		
		JCheckBox shpword = new JCheckBox("Show Password");
		shpword.setFont(new Font("Calibri Light", Font.PLAIN, 12));
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
		shpword.setBounds(252, 176, 147, 23);
		contentPane.add(shpword);
		
		JLabel lblNewLabel_3 = new JLabel("Type the code you see above.");
		lblNewLabel_3.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(181, 260, 199, 14);
		contentPane.add(lblNewLabel_3);
		
		captcha_ans = new JTextField();
		captcha_ans.setBorder(new LineBorder(Color.BLACK, 1, true));
		captcha_ans.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		captcha_ans.setBounds(181, 278, 147, 20);
		contentPane.add(captcha_ans);
		captcha_ans.setColumns(10);
		
		JLabel RegisterAcc = new JLabel("Register Account");
		RegisterAcc.setFont(new Font("Calibri Light", Font.PLAIN, 12));
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
		RegisterAcc.setBounds(335, 351, 101, 14);
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
		ForgotPassword.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		ForgotPassword.setBounds(191, 351, 109, 14);
		contentPane.add(ForgotPassword);
		
		JLabel name_icon = new JLabel();
		name_icon.setBounds(49, 29, 40, 40);
		name_icon.setIcon(new ImageIcon(img1));
		contentPane.add(name_icon);
		
		JLabel uname_icon = new JLabel();
		uname_icon.setBounds(152, 111, 20, 20);
		uname_icon.setIcon(new ImageIcon(img3));
		contentPane.add(uname_icon);
		
		
	}
}