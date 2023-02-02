import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtuname;
	private JPasswordField txtpword;

	/**
	 * Launch the application.
	 */
	

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
		setBounds(100, 100, 595, 411);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtuname = new JTextField();
		txtuname.setBounds(182, 88, 233, 20);
		contentPane.add(txtuname);
		txtuname.setColumns(10);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					conn = sqliteConnection.dbConnector();
					String uname = txtuname.getText();
					String pword = txtpword.getText();
					
					Statement stm = conn.createStatement();
					String sql = "select * from Employee where uname='"+uname+"' and pword='"+pword+"'";
					ResultSet rs = stm.executeQuery(sql);
					if(rs.next()) {
						dispose();
						HomePage hpage = new HomePage();
						hpage.show();
					}else {
						JOptionPane.showMessageDialog(null, "Username or password is wrong.");
						txtuname.setText("");
						txtpword.setText("");
					}
				
					conn.close();
					
				}catch(Exception e) {
					System.out.println(e);
				}
			}
		});
		btnLogin.setBounds(152, 285, 263, 31);
		contentPane.add(btnLogin);
		
		JLabel password_icon = new JLabel("");
		password_icon.setBounds(152, 125, 20, 20);
		password_icon.setIcon(new ImageIcon(img2));
		contentPane.add(password_icon);
		
		txtpword = new JPasswordField();
		txtpword.setBounds(182, 125, 233, 20);
		contentPane.add(txtpword);
		
		JLabel lblNewLabel_2 = new JLabel("Reservation Management System");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_2.setBounds(121, 28, 428, 35);
		contentPane.add(lblNewLabel_2);
		
		JCheckBox shpword = new JCheckBox("Show Password");
		shpword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(shpword.isSelected()) {
					txtpword.setEchoChar((char)0);
				}else {
					txtpword.setEchoChar('*');
				}
			}
		});
		shpword.setBounds(223, 152, 147, 23);
		contentPane.add(shpword);
		
		captcha = new JTextField();
		captcha.setBounds(152, 182, 240, 43);
		contentPane.add(captcha);
		captcha.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Type the code you see above.");
		lblNewLabel_3.setBounds(152, 236, 199, 14);
		contentPane.add(lblNewLabel_3);
		
		captcha_ans = new JTextField();
		captcha_ans.setBounds(152, 254, 147, 20);
		contentPane.add(captcha_ans);
		captcha_ans.setColumns(10);
		
		JLabel RegisterAcc = new JLabel("Register Account");
		RegisterAcc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					dispose();
					Register rpage = new Register();
					rpage.show();
				}catch(Exception e1) {
					
				}
			}
		});
		RegisterAcc.setBounds(306, 327, 101, 14);
		contentPane.add(RegisterAcc);
		
		JLabel ForgotPassword = new JLabel("Forgot Password");
		ForgotPassword.setBounds(162, 327, 109, 14);
		contentPane.add(ForgotPassword);
		
		JLabel name_icon = new JLabel("");
		name_icon.setBounds(63, 28, 40, 40);
		name_icon.setIcon(new ImageIcon(img1));
		contentPane.add(name_icon);
		
		JLabel uname_icon = new JLabel();
		uname_icon.setBounds(152, 88, 20, 20);
		uname_icon.setIcon(new ImageIcon(img3));
		contentPane.add(uname_icon);
	}

}
