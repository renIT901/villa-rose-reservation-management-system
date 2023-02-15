import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditEmployees extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditEmployees frame = new EditEmployees();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EditEmployees() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 245, 232));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("Edit Employees");
		lblNewJgoodiesLabel.setBounds(10, 11, 188, 38);
		lblNewJgoodiesLabel.setFont(new Font("Calibri Light", Font.PLAIN, 30));
		contentPane.add(lblNewJgoodiesLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Calibri Light", Font.PLAIN, 11));
		scrollPane.setBounds(10, 50, 604, 92);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Calibri Light", Font.PLAIN, 11));
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Employee ID", "First Name", "Last Name", "Username", "Password", "Email", "SQuestion", "SAnswer", "Role"
			}
		));
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(80, 170, 255, 22);
		contentPane.add(comboBox);
		
		textField = new JTextField();
		textField.setFont(new Font("Calibri Light", Font.PLAIN, 11));
		textField.setBounds(80, 203, 255, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(new Color(225, 167, 48));
		btnNewButton.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		btnNewButton.setBounds(345, 185, 89, 30);
		contentPane.add(btnNewButton);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Calibri Light", Font.PLAIN, 11));
		textField_1.setBounds(72, 257, 160, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Calibri Light", Font.PLAIN, 11));
		textField_2.setBounds(72, 288, 160, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Calibri Light", Font.PLAIN, 11));
		textField_3.setBounds(72, 319, 160, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Calibri Light", Font.PLAIN, 11));
		textField_4.setBounds(356, 257, 160, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Calibri Light", Font.PLAIN, 11));
		textField_5.setBounds(356, 288, 160, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblNewJgoodiesLabel_1 = DefaultComponentFactory.getInstance().createLabel("First Name:");
		lblNewJgoodiesLabel_1.setFont(new Font("Calibri Light", Font.PLAIN, 11));
		lblNewJgoodiesLabel_1.setBounds(20, 260, 50, 14);
		contentPane.add(lblNewJgoodiesLabel_1);
		
		JLabel lblNewJgoodiesLabel_2 = DefaultComponentFactory.getInstance().createLabel("Last Name:");
		lblNewJgoodiesLabel_2.setFont(new Font("Calibri Light", Font.PLAIN, 11));
		lblNewJgoodiesLabel_2.setBounds(20, 291, 50, 14);
		contentPane.add(lblNewJgoodiesLabel_2);
		
		JLabel lblNewJgoodiesLabel_3 = DefaultComponentFactory.getInstance().createLabel("Email:");
		lblNewJgoodiesLabel_3.setFont(new Font("Calibri Light", Font.PLAIN, 11));
		lblNewJgoodiesLabel_3.setBounds(44, 322, 26, 14);
		contentPane.add(lblNewJgoodiesLabel_3);
		
		JLabel lblNewJgoodiesLabel_4 = DefaultComponentFactory.getInstance().createLabel("Username:");
		lblNewJgoodiesLabel_4.setFont(new Font("Calibri Light", Font.PLAIN, 11));
		lblNewJgoodiesLabel_4.setBounds(306, 260, 50, 14);
		contentPane.add(lblNewJgoodiesLabel_4);
		
		JLabel lblNewJgoodiesLabel_5 = DefaultComponentFactory.getInstance().createLabel("Password:");
		lblNewJgoodiesLabel_5.setFont(new Font("Calibri Light", Font.PLAIN, 11));
		lblNewJgoodiesLabel_5.setBounds(306, 291, 46, 14);
		contentPane.add(lblNewJgoodiesLabel_5);
		
		JLabel lblNewJgoodiesLabel_6 = DefaultComponentFactory.getInstance().createLabel("Role:");
		lblNewJgoodiesLabel_6.setFont(new Font("Calibri Light", Font.PLAIN, 11));
		lblNewJgoodiesLabel_6.setBounds(330, 322, 26, 14);
		contentPane.add(lblNewJgoodiesLabel_6);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Admin");
		rdbtnNewRadioButton.setBackground(new Color(250, 245, 232));
		rdbtnNewRadioButton.setFont(new Font("Calibri Light", Font.PLAIN, 11));
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(356, 318, 109, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Employee");
		rdbtnNewRadioButton_1.setBackground(new Color(250, 245, 232));
		rdbtnNewRadioButton_1.setFont(new Font("Calibri Light", Font.PLAIN, 11));
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBounds(467, 318, 109, 23);
		contentPane.add(rdbtnNewRadioButton_1);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Calibri Light", Font.PLAIN, 11));
		comboBox_1.setBounds(80, 350, 255, 22);
		contentPane.add(comboBox_1);
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("Calibri Light", Font.PLAIN, 11));
		textField_6.setColumns(10);
		textField_6.setBounds(80, 383, 255, 20);
		contentPane.add(textField_6);
		
		JButton btnNewButton_1 = new JButton("Update Account");
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setBackground(new Color(225, 167, 48));
		btnNewButton_1.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(366, 350, 135, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Delete Account");
		btnNewButton_1_1.setBorder(null);
		btnNewButton_1_1.setBackground(new Color(225, 167, 48));
		btnNewButton_1_1.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		btnNewButton_1_1.setBounds(366, 380, 135, 23);
		contentPane.add(btnNewButton_1_1);
	}
}
