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
import java.awt.Rectangle;
import javax.swing.ImageIcon;

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
		setResizable(false);
		setBounds(new Rectangle(0, 0, 960, 720));
		setTitle("Villa Rose System");
		//conn = sqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 960, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 245, 232));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("Edit Employees");
		lblNewJgoodiesLabel.setBounds(220, 20, 211, 38);
		lblNewJgoodiesLabel.setFont(new Font("SansSerif", Font.PLAIN, 30));
		contentPane.add(lblNewJgoodiesLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("SansSerif", Font.PLAIN, 16));
		scrollPane.setBounds(220, 68, 716, 127);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setDefaultEditor(Object.class, null);
		table.setFont(new Font("SansSerif", Font.PLAIN, 16));
		scrollPane.setViewportView(table);
		updateTable();
		
		JRadioButton rdbtnAdmin = new JRadioButton("Admin");
		rdbtnAdmin.setBackground(new Color(250, 245, 232));
		rdbtnAdmin.setFont(new Font("SansSerif", Font.PLAIN, 16));
		buttonGroup.add(rdbtnAdmin);
		rdbtnAdmin.setBounds(669, 352, 109, 23);
		contentPane.add(rdbtnAdmin);
		
		JRadioButton rdbtnEmployee = new JRadioButton("Employee");
		rdbtnEmployee.setBackground(new Color(250, 245, 232));
		rdbtnEmployee.setFont(new Font("SansSerif", Font.PLAIN, 16));
		buttonGroup.add(rdbtnEmployee);
		rdbtnEmployee.setBounds(780, 352, 109, 23);
		contentPane.add(rdbtnEmployee);
		
		String[] sq = {"Select Security Question","In what city were you born?","What is the name of your favorite pet?","What was your favorite food as a child?","What high school did you attend?"};
		JComboBox comboBox_1 = new JComboBox(sq);
		comboBox_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		comboBox_1.setBounds(247, 575, 315, 24);
		contentPane.add(comboBox_1);
		
		String[] colum = {"Select Field","employee_id","fname","lname","uname"};
		JComboBox comboBox = new JComboBox(colum);
		comboBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
		comboBox.setBounds(220, 218, 263, 22);
		contentPane.add(comboBox);
		
		JDateChooser dateStarted = new JDateChooser();
		dateStarted.setBounds(669, 381, 200, 24);
		contentPane.add(dateStarted);
		
		JDateChooser dateBday = new JDateChooser();
		dateBday.setBounds(669, 415, 200, 24);
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
		textField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		textField.setBounds(220, 249, 263, 20);
		
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
		btnNewButton.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnNewButton.setBounds(493, 218, 89, 48);
		contentPane.add(btnNewButton);
		
		fname = new JTextField();
		fname.setFont(new Font("SansSerif", Font.PLAIN, 16));
		fname.setBounds(320, 291, 200, 24);
		contentPane.add(fname);
		fname.setColumns(10);
		
		lname = new JTextField();
		lname.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lname.setBounds(320, 322, 200, 24);
		contentPane.add(lname);
		lname.setColumns(10);
		
		email = new JTextField();
		email.setFont(new Font("SansSerif", Font.PLAIN, 16));
		email.setBounds(320, 353, 200, 24);
		contentPane.add(email);
		email.setColumns(10);
		
		uname = new JTextField();
		uname.setFont(new Font("SansSerif", Font.PLAIN, 16));
		uname.setBounds(669, 291, 200, 24);
		contentPane.add(uname);
		uname.setColumns(10);
		
		pword = new JTextField();
		pword.setFont(new Font("SansSerif", Font.PLAIN, 16));
		pword.setBounds(669, 322, 200, 24);
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
		lblNewJgoodiesLabel123.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewJgoodiesLabel123.setBounds(848, 20, 77, 22);
		contentPane.add(lblNewJgoodiesLabel123);
		
		JLabel lblNewJgoodiesLabel_1 = DefaultComponentFactory.getInstance().createLabel("First Name:");
		lblNewJgoodiesLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewJgoodiesLabel_1.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblNewJgoodiesLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewJgoodiesLabel_1.setBounds(228, 296, 82, 24);
		contentPane.add(lblNewJgoodiesLabel_1);
		
		JLabel lblNewJgoodiesLabel_2 = DefaultComponentFactory.getInstance().createLabel("Last Name:");
		lblNewJgoodiesLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewJgoodiesLabel_2.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblNewJgoodiesLabel_2.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewJgoodiesLabel_2.setBounds(220, 327, 90, 24);
		contentPane.add(lblNewJgoodiesLabel_2);
		
		JLabel lblNewJgoodiesLabel_3 = DefaultComponentFactory.getInstance().createLabel("Email:");
		lblNewJgoodiesLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewJgoodiesLabel_3.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblNewJgoodiesLabel_3.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewJgoodiesLabel_3.setBounds(244, 356, 66, 24);
		contentPane.add(lblNewJgoodiesLabel_3);
		
		JLabel lblNewJgoodiesLabel_4 = DefaultComponentFactory.getInstance().createLabel("Username:");
		lblNewJgoodiesLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewJgoodiesLabel_4.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblNewJgoodiesLabel_4.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewJgoodiesLabel_4.setBounds(578, 291, 81, 24);
		contentPane.add(lblNewJgoodiesLabel_4);
		
		JLabel lblNewJgoodiesLabel_5 = DefaultComponentFactory.getInstance().createLabel("Password:");
		lblNewJgoodiesLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewJgoodiesLabel_5.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblNewJgoodiesLabel_5.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewJgoodiesLabel_5.setBounds(582, 322, 77, 24);
		contentPane.add(lblNewJgoodiesLabel_5);
		
		JLabel lblNewJgoodiesLabel_6 = DefaultComponentFactory.getInstance().createLabel("Role:");
		lblNewJgoodiesLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewJgoodiesLabel_6.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblNewJgoodiesLabel_6.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewJgoodiesLabel_6.setBounds(578, 351, 81, 24);
		contentPane.add(lblNewJgoodiesLabel_6);
		
		sq_ans = new JTextField();
		sq_ans.setFont(new Font("SansSerif", Font.PLAIN, 16));
		sq_ans.setColumns(10);
		sq_ans.setBounds(247, 608, 315, 24);
		contentPane.add(sq_ans);
		
		JButton btnNewButton_1 = new JButton("Update Account");
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setBackground(new Color(225, 167, 48));
		btnNewButton_1.setFont(new Font("SansSerif", Font.PLAIN, 20));
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
		btnNewButton_1.setBounds(766, 537, 170, 48);
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
		btnNewButton_1_1.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnNewButton_1_1.setBounds(766, 595, 170, 48);
		contentPane.add(btnNewButton_1_1);
		
		salary = new JTextField();
		salary.setFont(new Font("SansSerif", Font.PLAIN, 16));
		salary.setColumns(10);
		salary.setBounds(320, 384, 200, 24);
		contentPane.add(salary);
		
		JLabel lblNewLabel = new JLabel("Salary:");
		lblNewLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel.setBounds(224, 389, 86, 24);
		contentPane.add(lblNewLabel);
		
		txtSched = new JTextField();
		txtSched.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtSched.setColumns(10);
		txtSched.setBounds(320, 415, 200, 24);
		contentPane.add(txtSched);
		
		JLabel lblSchedule = new JLabel("Schedule:");
		lblSchedule.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblSchedule.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSchedule.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblSchedule.setBounds(224, 420, 86, 24);
		contentPane.add(lblSchedule);
	
		
		JLabel lblNewLabel_1 = new JLabel("Date Started:");
		lblNewLabel_1.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setBounds(540, 382, 119, 24);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Birthday:");
		lblNewLabel_1_1.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblNewLabel_1_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(582, 414, 77, 24);
		contentPane.add(lblNewLabel_1_1);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 200, 683);
		contentPane.add(panel);
		
		JLabel icon = new JLabel("");
		icon.setIcon(new ImageIcon(EditEmployees.class.getResource("/img/villarose.png")));
		icon.setBounds(37, 29, 125, 125);
		panel.add(icon);
		
		JButton btn_transaction = new JButton("Transactions");
		btn_transaction.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btn_transaction.setBorder(null);
		btn_transaction.setBackground(new Color(225, 167, 48));
		btn_transaction.setBounds(18, 191, 163, 40);
		panel.add(btn_transaction);
		
		JButton btn_managecontent = new JButton("Manage Content");
		btn_managecontent.setSelected(true);
		btn_managecontent.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btn_managecontent.setBorder(null);
		btn_managecontent.setBackground(new Color(225, 167, 48));
		btn_managecontent.setBounds(18, 293, 163, 40);
		panel.add(btn_managecontent);
		
		JButton btn_logout = new JButton("Logout");
		btn_logout.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btn_logout.setBorder(null);
		btn_logout.setBackground(new Color(225, 167, 48));
		btn_logout.setBounds(18, 594, 163, 40);
		panel.add(btn_logout);
		
		JButton btn_transactiontables = new JButton("Transaction Table");
		btn_transactiontables.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btn_transactiontables.setBorder(null);
		btn_transactiontables.setBackground(new Color(225, 167, 48));
		btn_transactiontables.setBounds(18, 242, 163, 40);
		panel.add(btn_transactiontables);
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