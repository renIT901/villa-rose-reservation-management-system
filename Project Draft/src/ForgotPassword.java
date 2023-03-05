import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Label;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;

public class ForgotPassword {

	private JFrame frame;
	private JTextField txtEmail;
	private JTextField txtUsername;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgotPassword window = new ForgotPassword();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ForgotPassword() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(250, 245, 232));
		frame.getContentPane().setForeground(new Color(250, 245, 232));
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Forget Password");
		lblNewLabel.setBounds(237, 108, 298, 52);
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 40));
		frame.getContentPane().add(lblNewLabel);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtEmail.setForeground(Color.GRAY);
		txtEmail.setText("Email");
		txtEmail.setBounds(171, 200, 437, 32);
		frame.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtUsername.setForeground(Color.GRAY);
		txtUsername.setText("Username");
		txtUsername.setColumns(10);
		txtUsername.setBounds(171, 243, 437, 32);
		frame.getContentPane().add(txtUsername);
		
		JButton btnProceed = new JButton("Proceed");
		btnProceed.setHorizontalTextPosition(SwingConstants.CENTER);
		btnProceed.setForeground(Color.BLACK);
		btnProceed.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnProceed.setBorderPainted(false);
		btnProceed.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnProceed.setBackground(new Color(225, 167, 48));
		btnProceed.setAlignmentY(0.0f);
		btnProceed.setBounds(283, 352, 187, 35);
		frame.getContentPane().add(btnProceed);
		frame.setBounds(new Rectangle(0, 0, 800, 600));
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}