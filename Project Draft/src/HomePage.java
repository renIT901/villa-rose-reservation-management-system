import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Panel;
import javax.swing.JLayeredPane;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import java.awt.GridLayout;
import javax.swing.JMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomePage extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage frame = new HomePage();
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
	public HomePage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1026, 576);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Calibri Light", Font.PLAIN, 11));
		contentPane.setBackground(new Color(250, 245, 232));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 160, 537);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Transactions");
		btnNewButton_1.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		btnNewButton_1.setBackground(new Color(225, 167, 48));
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setBounds(18, 140, 125, 30);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Customer");
		btnNewButton_1_1.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		btnNewButton_1_1.setBorder(null);
		btnNewButton_1_1.setBackground(new Color(225, 167, 48));
		btnNewButton_1_1.setBounds(18, 186, 125, 30);
		panel.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_1_1 = new JButton("Manage Content");
		btnNewButton_1_1_1.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		btnNewButton_1_1_1.setBorder(null);
		btnNewButton_1_1_1.setBackground(new Color(225, 167, 48));
		btnNewButton_1_1_1.setBounds(18, 232, 125, 30);
		panel.add(btnNewButton_1_1_1);
		
		JButton btnNewButton_1_1_1_1 = new JButton("Log Out");
		btnNewButton_1_1_1_1.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		btnNewButton_1_1_1_1.setBorder(null);
		btnNewButton_1_1_1_1.setBackground(new Color(225, 167, 48));
		btnNewButton_1_1_1_1.setBounds(18, 428, 125, 30);
		panel.add(btnNewButton_1_1_1_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("D:\\UST\\Eclipse\\Project-Draft\\Project Draft\\img\\1.png"));
		lblNewLabel.setBounds(60, 45, 39, 46);
		panel.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(170, 261, 830, 265);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"", null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Transaction ID", "Employee ID", "First Name", "Last Name", "Email", "Contact", "Check In", "Check Out", "Room #", "Balance", "Amount Paid", "Payment Proof", "Status", "Edit"
			}
		));
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("First Name:");
		lblNewLabel_1_1_1_1.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		lblNewLabel_1_1_1_1.setBounds(208, 43, 58, 14);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		textField.setColumns(10);
		textField.setBounds(276, 40, 282, 20);
		contentPane.add(textField);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Last Name:");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		lblNewLabel_1_1_1_1_1.setBounds(210, 68, 66, 14);
		contentPane.add(lblNewLabel_1_1_1_1_1);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		textField_1.setColumns(10);
		textField_1.setBounds(276, 65, 282, 20);
		contentPane.add(textField_1);
		
		JLabel lblNewLabel_1_1_1_1_2 = new JLabel("Email:");
		lblNewLabel_1_1_1_1_2.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		lblNewLabel_1_1_1_1_2.setBounds(235, 93, 31, 14);
		contentPane.add(lblNewLabel_1_1_1_1_2);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		textField_2.setColumns(10);
		textField_2.setBounds(276, 90, 282, 20);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		textField_3.setColumns(10);
		textField_3.setBounds(276, 115, 282, 20);
		contentPane.add(textField_3);
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Contact #:");
		lblNewLabel_1_1_1_1_1_1.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		lblNewLabel_1_1_1_1_1_1.setBounds(217, 118, 49, 14);
		contentPane.add(lblNewLabel_1_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_2_1 = new JLabel("Check In:");
		lblNewLabel_1_1_1_1_2_1.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		lblNewLabel_1_1_1_1_2_1.setBounds(223, 143, 43, 14);
		contentPane.add(lblNewLabel_1_1_1_1_2_1);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		textField_4.setColumns(10);
		textField_4.setBounds(276, 165, 282, 20);
		contentPane.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		textField_5.setColumns(10);
		textField_5.setBounds(276, 140, 282, 20);
		contentPane.add(textField_5);
		
		JLabel lblNewLabel_1_1_1_1_1_1_1 = new JLabel("Check Out:");
		lblNewLabel_1_1_1_1_1_1_1.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		lblNewLabel_1_1_1_1_1_1_1.setBounds(214, 168, 52, 14);
		contentPane.add(lblNewLabel_1_1_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_3 = new JLabel("Room #:");
		lblNewLabel_1_1_1_1_3.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		lblNewLabel_1_1_1_1_3.setBounds(632, 43, 40, 14);
		contentPane.add(lblNewLabel_1_1_1_1_3);
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		textField_6.setColumns(10);
		textField_6.setBounds(682, 65, 282, 20);
		contentPane.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		textField_7.setColumns(10);
		textField_7.setBounds(682, 40, 282, 20);
		contentPane.add(textField_7);
		
		JLabel lblNewLabel_1_1_1_1_1_2 = new JLabel("Balance:");
		lblNewLabel_1_1_1_1_1_2.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		lblNewLabel_1_1_1_1_1_2.setBounds(629, 68, 43, 14);
		contentPane.add(lblNewLabel_1_1_1_1_1_2);
		
		JLabel lblNewLabel_1_1_1_1_1_2_1 = new JLabel("Amount Paid:");
		lblNewLabel_1_1_1_1_1_2_1.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		lblNewLabel_1_1_1_1_1_2_1.setBounds(606, 93, 66, 14);
		contentPane.add(lblNewLabel_1_1_1_1_1_2_1);
		
		textField_8 = new JTextField();
		textField_8.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		textField_8.setColumns(10);
		textField_8.setBounds(682, 90, 282, 20);
		contentPane.add(textField_8);
		
		JButton btnNewButton = new JButton("Add Reservation");
		btnNewButton.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(new Color(225, 167, 48));
		btnNewButton.setBounds(743, 116, 160, 30);
		contentPane.add(btnNewButton);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		comboBox.setBounds(681, 165, 282, 22);
		contentPane.add(comboBox);
		
		textField_9 = new JTextField();
		textField_9.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		textField_9.setColumns(10);
		textField_9.setBounds(682, 190, 282, 20);
		contentPane.add(textField_9);
		
		JButton btnNewButton_2 = new JButton("Search");
		btnNewButton_2.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		btnNewButton_2.setBorder(null);
		btnNewButton_2.setBackground(new Color(225, 167, 48));
		btnNewButton_2.setBounds(744, 216, 160, 30);
		contentPane.add(btnNewButton_2);
	}
}
