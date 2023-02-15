import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.Button;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.SpringLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class ManageContent extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageContent frame = new ManageContent();
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
	public ManageContent() {
		setBackground(new Color(250, 245, 232));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 245, 232));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("Manage Content");
		lblNewJgoodiesLabel.setFont(new Font("Calibri Light", Font.PLAIN, 30));
		lblNewJgoodiesLabel.setBounds(10, 11, 204, 38);
		contentPane.add(lblNewJgoodiesLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Calibri Light", Font.PLAIN, 11));
		comboBox.setBounds(20, 60, 311, 22);
		contentPane.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Calibri Light", Font.PLAIN, 11));
		comboBox_1.setBounds(20, 145, 311, 22);
		contentPane.add(comboBox_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 187, 594, 43);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Calibri Light", Font.PLAIN, 11));
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Transaction ID", "First Name", "Last Name", "Email", "Contact #", "Booking Description", "Balance", "Amount Paid", "Payment Date"
			}
		));
		
		JButton btnNewButton = new JButton("Generate Receipt");
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(new Color(225, 167, 48));
		btnNewButton.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		btnNewButton.setBounds(20, 241, 147, 30);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Edit Employees Account");
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setBackground(new Color(225, 167, 48));
		btnNewButton_1.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		btnNewButton_1.setBounds(20, 289, 187, 30);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Backup Database");
		btnNewButton_2.setBorder(null);
		btnNewButton_2.setBackground(new Color(225, 167, 48));
		btnNewButton_2.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		btnNewButton_2.setBounds(10, 374, 144, 30);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("View Backup");
		btnNewButton_3.setBorder(null);
		btnNewButton_3.setBackground(new Color(225, 167, 48));
		btnNewButton_3.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		btnNewButton_3.setBounds(164, 374, 144, 30);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Delete Backup");
		btnNewButton_4.setBorder(null);
		btnNewButton_4.setBackground(new Color(225, 167, 48));
		btnNewButton_4.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		btnNewButton_4.setBounds(316, 374, 144, 30);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Restore Backup");
		btnNewButton_5.setBorder(null);
		btnNewButton_5.setBackground(new Color(225, 167, 48));
		btnNewButton_5.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		btnNewButton_5.setBounds(470, 374, 144, 30);
		contentPane.add(btnNewButton_5);
		
		JLabel lblNewJgoodiesLabel_1 = DefaultComponentFactory.getInstance().createLabel("Homepage");
		lblNewJgoodiesLabel_1.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		lblNewJgoodiesLabel_1.setBounds(537, 20, 77, 22);
		contentPane.add(lblNewJgoodiesLabel_1);
		
		JButton btnNewButton_6 = new JButton("Generate Report");
		btnNewButton_6.setBorder(null);
		btnNewButton_6.setBackground(new Color(225, 167, 48));
		btnNewButton_6.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		btnNewButton_6.setBounds(20, 93, 141, 30);
		contentPane.add(btnNewButton_6);
	}
}
