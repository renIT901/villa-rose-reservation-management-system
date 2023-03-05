import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;

public class SecurityQuestion {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SecurityQuestion window = new SecurityQuestion();
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
	public SecurityQuestion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(250, 245, 232));
		frame.setBackground(new Color(250, 245, 232));
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSecurityQuestion = new JLabel("Security Question");
		lblSecurityQuestion.setFont(new Font("SansSerif", Font.PLAIN, 40));
		lblSecurityQuestion.setBounds(229, 132, 307, 39);
		frame.getContentPane().add(lblSecurityQuestion);
		
		textField = new JTextField();
		textField.setText("Email");
		textField.setForeground(Color.GRAY);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField.setColumns(10);
		textField.setBounds(176, 257, 437, 32);
		frame.getContentPane().add(textField);
		
		JComboBox comboBox = new JComboBox(new Object[]{});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select Security Question", "In what city were you born?", "What is the name of your favorite pet?", "What was your favorite food as a child?", "What high school did you attend?"}));
		comboBox.setFont(new Font("SansSerif", Font.PLAIN, 20));
		comboBox.setBounds(176, 214, 437, 32);
		frame.getContentPane().add(comboBox);
		
		JButton btnProceed = new JButton("Proceed");
		btnProceed.setHorizontalTextPosition(SwingConstants.CENTER);
		btnProceed.setForeground(Color.BLACK);
		btnProceed.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnProceed.setBorderPainted(false);
		btnProceed.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnProceed.setBackground(new Color(225, 167, 48));
		btnProceed.setAlignmentY(0.0f);
		btnProceed.setBounds(290, 367, 187, 35);
		frame.getContentPane().add(btnProceed);
	}
}