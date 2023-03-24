import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import net.proteanit.sql.DbUtils;

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
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import com.toedter.calendar.JDateChooser;

public class EditEmployees extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField fname;
	private JTextField lname;
	private JTextField email;
	private JTextField uname;
	private JTextField pword;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField sq_ans;
	String employee_id;
	String sQuestion;
	String role;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//EditEmployees frame = new EditEmployees();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	private JTextField salary;
	private JTextField txtSched;
	
	public EditEmployees(int emp_id) {
		setTitle("Villa Rose System");
		//conn = sqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 540);
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
		table.setDefaultEditor(Object.class, null);
		table.setFont(new Font("Calibri Light", Font.PLAIN, 11));
		scrollPane.setViewportView(table);
		updateTable();
		
		JRadioButton rdbtnAdmin = new JRadioButton("Admin");
		rdbtnAdmin.setBackground(new Color(250, 245, 232));
		rdbtnAdmin.setFont(new Font("Calibri Light", Font.PLAIN, 11));
		buttonGroup.add(rdbtnAdmin);
		rdbtnAdmin.setBounds(356, 318, 109, 23);
		contentPane.add(rdbtnAdmin);
		
		JRadioButton rdbtnEmployee = new JRadioButton("Employee");
		rdbtnEmployee.setBackground(new Color(250, 245, 232));
		rdbtnEmployee.setFont(new Font("Calibri Light", Font.PLAIN, 11));
		buttonGroup.add(rdbtnEmployee);
		rdbtnEmployee.setBounds(467, 318, 109, 23);
		contentPane.add(rdbtnEmployee);
		
		String[] sq = {"Select Security Question","In what city were you born?","What is the name of your favorite pet?","What was your favorite food as a child?","What high school did you attend?"};
		JComboBox comboBox_1 = new JComboBox(sq);
		comboBox_1.setFont(new Font("Calibri Light", Font.PLAIN, 11));
		comboBox_1.setBounds(20, 412, 315, 22);
		contentPane.add(comboBox_1);
		
		String[] colum = {"Select Field","employee_id","fname","lname","uname","pword","sQuestion","sq_ans","role"};
		JComboBox comboBox = new JComboBox(colum);
		comboBox.setBounds(72, 170, 263, 22);
		contentPane.add(comboBox);
		
		JDateChooser dateStarted = new JDateChooser();
		dateStarted.setBounds(356, 348, 160, 20);
		contentPane.add(dateStarted);
		
		JDateChooser dateBday = new JDateChooser();
		dateBday.setBounds(356, 380, 160, 20);
		contentPane.add(dateBday);
		
		textField = new JTextField();
		textField.setText("Search for?");
		textField.setForeground(new Color(159, 159, 159));
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(textField.getText().equals("Search for?")) {
					textField.setText("");
					textField.setForeground(new Color(0, 0, 0));
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(textField.getText().equals("")) {
					textField.setText("Search for?");
					textField.setForeground(new Color(159, 159, 159));
			}
			
		}
		});
		textField.setFont(new Font("Calibri Light", Font.PLAIN, 11));
		textField.setBounds(72, 203, 263, 20);
		
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conn = sqliteConnection.dbConnector();
					System.out.println(emp_id);
					String field = comboBox.getSelectedItem().toString();
					String txtfield = textField.getText();
					pst = conn.prepareStatement("SELECT * FROM Employee WHERE "+field+"='"+txtfield+"'");
					rs = pst.executeQuery();
					if(rs.next()==true) {
						employee_id = rs.getString(1);
						uname.setText(rs.getString(2));
						pword.setText(rs.getString(3));
						sQuestion = rs.getString(4);
						if(sQuestion.equals("In what city were you born?")) {
							comboBox_1.setSelectedIndex(1);
						}
						else if(sQuestion.equals("What is the name of your favorite pet?")) {
							comboBox_1.setSelectedIndex(2);
						}
						else if(sQuestion.equals("What was your favorite food as a child?")) {
							comboBox_1.setSelectedIndex(3);
						}
						else if(sQuestion.equals("What high school did you attend?")) {
							comboBox_1.setSelectedIndex(4);
						}
						sq_ans.setText(rs.getString(5));
						role = rs.getString(5);
						if(role.equals("Admin")) {
							rdbtnAdmin.setSelected(true);
							rdbtnEmployee.setSelected(false);
						}
						else {rdbtnEmployee.setSelected(true); 
						rdbtnAdmin.setSelected(false);
						}
						fname.setText(rs.getString(7));
						lname.setText(rs.getString(8));
						email.setText(rs.getString(9));
						String checkin1 = rs.getString("date_started");
						java.util.Date checkin2 = new SimpleDateFormat("yyyy-MM-dd").parse(checkin1);
						String checkout1 = rs.getString("birthday");
						java.util.Date checkout2 = new SimpleDateFormat("yyyy-MM-dd").parse(checkout1);
						dateStarted.setDate(checkin2);
						dateBday.setDate(checkout2);
						salary.setText(rs.getString("salary"));
						txtSched.setText(rs.getString("schedule_of_duties"));
						
						
						pst.close();
						rs.close();
					}else {
						JOptionPane.showMessageDialog(null, "No Record Found!");
					}
				}catch (Exception e1) {
					e1.printStackTrace();
				}
				finally {
					try {
						conn.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(new Color(225, 167, 48));
		btnNewButton.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		btnNewButton.setBounds(345, 185, 89, 30);
		contentPane.add(btnNewButton);
		
		fname = new JTextField();
		fname.setFont(new Font("Calibri Light", Font.PLAIN, 11));
		fname.setBounds(72, 257, 160, 20);
		contentPane.add(fname);
		fname.setColumns(10);
		
		lname = new JTextField();
		lname.setFont(new Font("Calibri Light", Font.PLAIN, 11));
		lname.setBounds(72, 288, 160, 20);
		contentPane.add(lname);
		lname.setColumns(10);
		
		email = new JTextField();
		email.setFont(new Font("Calibri Light", Font.PLAIN, 11));
		email.setBounds(72, 319, 160, 20);
		contentPane.add(email);
		email.setColumns(10);
		
		uname = new JTextField();
		uname.setFont(new Font("Calibri Light", Font.PLAIN, 11));
		uname.setBounds(356, 257, 160, 20);
		contentPane.add(uname);
		uname.setColumns(10);
		
		pword = new JTextField();
		pword.setFont(new Font("Calibri Light", Font.PLAIN, 11));
		pword.setBounds(356, 288, 160, 20);
		contentPane.add(pword);
		pword.setColumns(10);
	
		
		JLabel lblNewJgoodiesLabel123 = DefaultComponentFactory.getInstance().createLabel("Go back");
		lblNewJgoodiesLabel123.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				ManageContent mpage = new ManageContent(emp_id);
				mpage.setLocationRelativeTo(null);
				mpage.show();
				
			}
		});
		lblNewJgoodiesLabel123.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewJgoodiesLabel123.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		lblNewJgoodiesLabel123.setBounds(537, 20, 77, 22);
		contentPane.add(lblNewJgoodiesLabel123);
		
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
		
		sq_ans = new JTextField();
		sq_ans.setFont(new Font("Calibri Light", Font.PLAIN, 11));
		sq_ans.setColumns(10);
		sq_ans.setBounds(20, 445, 315, 20);
		contentPane.add(sq_ans);
		
		JButton btnNewButton_1 = new JButton("Update Account");
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setBackground(new Color(225, 167, 48));
		btnNewButton_1.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conn = sqliteConnection.dbConnector();
					String sqq = comboBox_1.getSelectedItem().toString();
					String fname1 = fname.getText();
					String lname1 = lname.getText();
					String sq_ans1 = sq_ans.getText();
					String uname1 = uname.getText();
					String pword1 = pword.getText();
					String email1 = email.getText();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String dateS = sdf.format(dateStarted.getDate());
					String dateB = sdf.format(dateBday.getDate());
					String salary1 = salary.getText();
					String sched = txtSched.getText();
					
					if(rdbtnAdmin.isSelected()==true) {
						String role = "Admin";
						
						pst = conn.prepareStatement("UPDATE Employee SET uname=?,pword=?,sq_ans=?,fname=?,uname=?,role=?,SQuestion=?,email=?,salary=?,birthday=?,date_started=?,schedule_of_duties=? WHERE employee_id=?");
						pst.setString(1,uname1);
						pst.setString(2,pword1);
						pst.setString(3,sq_ans1);
						pst.setString(4,fname1);
						pst.setString(5,lname1);
						pst.setString(6, role);
						pst.setString(7, sqq);
						pst.setString(8, email1);
						pst.setString(9,salary1);
						pst.setString(10,dateB);
						pst.setString(11,dateS);
						pst.setString(12,sched);
						pst.setString(13,employee_id);
					}
					else if(rdbtnEmployee.isSelected()==true){
						String role = "Employee";
						
						pst = conn.prepareStatement("UPDATE Employee SET uname=?,pword=?,sq_ans=?,fname=?,uname=?,role=?,SQuestion=?,email=?,salary=?,birthday=?,date_started=?,schedule_of_duties=? WHERE employee_id=?");
						pst.setString(1,uname1);
						pst.setString(2,pword1);
						pst.setString(3,sq_ans1);
						pst.setString(4,fname1);
						pst.setString(5,lname1);
						pst.setString(6, role);
						pst.setString(7, sqq);
						pst.setString(8, email1);
						pst.setString(9,salary1);
						pst.setString(10,dateB);
						pst.setString(11,dateS);
						pst.setString(12,sched);
						pst.setString(13,employee_id);
					}
					
					
					int k = pst.executeUpdate();
					if(k==1) {
						JOptionPane.showMessageDialog(null, "Record has been sucessfully updated!");
						fname.setText("");
						lname.setText("");
						sq_ans.setText("");
						uname.setText("");
						pword.setText("");
						email.setText("");
						salary.setText("");
						txtSched.setText("");
						dateBday.setDate(null);
						dateStarted.setDate(null);
						
						
						buttonGroup.clearSelection();
						comboBox_1.setSelectedIndex(0);
						updateTable();
					}
					conn.close();
					pst.close();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "An Error was encountered while Updating");
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(366, 412, 135, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Delete Account");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "DELETE FROM Employee where employee_id = " + employee_id;
				try {
					conn = sqliteConnection.dbConnector();
					pst = conn.prepareStatement(sql);
					pst.execute();
					fname.setText("");
					lname.setText("");
					sq_ans.setText("");
					uname.setText("");
					pword.setText("");
					email.setText("");
					salary.setText("");
					txtSched.setText("");
					dateBday.setDate(null);
					dateStarted.setDate(null);
					buttonGroup.clearSelection();
					comboBox_1.setSelectedIndex(0);
					
					updateTable();
					JOptionPane.showMessageDialog(null, "Employee record deleted successfully");
					updateTable();
				}catch(Exception e1) {
					System.out.println(e1);
					JOptionPane.showMessageDialog(null, "An error has been encountered.");
					
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
		btnNewButton_1_1.setBorder(null);
		btnNewButton_1_1.setBackground(new Color(225, 167, 48));
		btnNewButton_1_1.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		btnNewButton_1_1.setBounds(366, 442, 135, 23);
		contentPane.add(btnNewButton_1_1);
		
		salary = new JTextField();
		salary.setFont(new Font("Calibri Light", Font.PLAIN, 11));
		salary.setColumns(10);
		salary.setBounds(72, 350, 160, 20);
		contentPane.add(salary);
		
		JLabel lblNewLabel = new JLabel("Salary:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setFont(new Font("Calibri Light", Font.PLAIN, 11));
		lblNewLabel.setBounds(24, 355, 46, 14);
		contentPane.add(lblNewLabel);
		
		txtSched = new JTextField();
		txtSched.setFont(new Font("Calibri Light", Font.PLAIN, 11));
		txtSched.setColumns(10);
		txtSched.setBounds(72, 381, 160, 20);
		contentPane.add(txtSched);
		
		JLabel lblSchedule = new JLabel("Schedule:");
		lblSchedule.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSchedule.setFont(new Font("Calibri Light", Font.PLAIN, 11));
		lblSchedule.setBounds(24, 386, 46, 14);
		contentPane.add(lblSchedule);
	
		
		JLabel lblNewLabel_1 = new JLabel("Date Started:");
		lblNewLabel_1.setFont(new Font("Calibri Light", Font.PLAIN, 11));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setBounds(275, 351, 77, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Birthday:");
		lblNewLabel_1_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1_1.setFont(new Font("Calibri Light", Font.PLAIN, 11));
		lblNewLabel_1_1.setBounds(275, 386, 77, 14);
		contentPane.add(lblNewLabel_1_1);
	}
	private void updateTable() {
		conn = sqliteConnection.dbConnector();
		String sql = "SELECT employee_id,fname,lname,uname,pword,email,role,gender,birthday,date_started,salary,schedule_of_duties,sQuestion,sq_ans FROM Employee";
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			try {
				conn.close();
				rs.close();
				pst.close();
			} catch (Exception e) {
				
			}
		}
	}
}