import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import net.proteanit.sql.DbUtils;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.Button;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.SpringLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.beans.Statement;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ManageContent extends JFrame {

	private JPanel contentPane;
	private String transaction_id;
	private String gen_table;
	private String filename=null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//ManageContent frame = new ManageContent();
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
	
	public ManageContent(int emp_id) {
		setTitle("Villa Rose System");
		System.out.print(emp_id);
		conn = sqliteConnection.dbConnector();
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
		
		String[] column = {"Select Table to Generate","Testing","Employee"};
		JComboBox comboBox = new JComboBox(column);
		comboBox.setFont(new Font("Calibri Light", Font.PLAIN, 11));
		comboBox.setBounds(20, 93, 311, 22);
		contentPane.add(comboBox);
		loadUserName();
		
		JButton btnNewButton_1 = new JButton("Edit Employees Account");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				EditEmployees epage = new EditEmployees(emp_id);
				epage.setLocationRelativeTo(null);
				epage.show();
			}
		});
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setBackground(new Color(225, 167, 48));
		btnNewButton_1.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		btnNewButton_1.setBounds(20, 239, 187, 30);
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
		lblNewJgoodiesLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				HomePage hpage = new HomePage(emp_id);
				hpage.setLocationRelativeTo(null);
				hpage.show();
				
			}
		});
		lblNewJgoodiesLabel_1.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		lblNewJgoodiesLabel_1.setBounds(537, 20, 77, 22);
		contentPane.add(lblNewJgoodiesLabel_1);
		
		JButton btnReport = new JButton("Generate Report");
		btnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String gen_table = (String) comboBox.getSelectedItem();
				conn = sqliteConnection.dbConnector();
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				File f = chooser.getSelectedFile();
				filename = f.getAbsolutePath();
				
				if (gen_table.equals("Testing")){
			        try {
			        	conn = sqliteConnection.dbConnector();
			        	String csvFilePath = filename + ".csv";
						String sql= "Select * from " + gen_table;
						pst= conn.prepareStatement(sql);
						rs = pst.executeQuery();
			             
			            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(csvFilePath));
			             
			            // write header line containing column names       
			            fileWriter.write("transaction_id,first_name,last_name,email,contact_no,check_in,check_out,room_description,balance,amount_paid");
			            while (rs.next()) {
			                String transaction_id = rs.getString("transaction_id");
			                String first_name = rs.getString("first_name");
			                String last_name = rs.getString("last_name");
			                String email = rs.getString("email");
			                String contact_no = rs.getString("contact_no");
			                String check_in = rs.getString("check_in");
			                String check_out = rs.getString("check_out");
			                String room_description = rs.getString("room_description");
			                String balance = rs.getString("balance");
			                String amount_paid = rs.getString("amount_paid");
			                 
			                String line = String.format("\"%s\",%s,%s,%s,%s,%s,%s,%s,%s,%s",
			                		transaction_id,first_name,last_name,email,contact_no,check_in,check_out,room_description,balance,amount_paid);
			                 
			                fileWriter.newLine();
			                fileWriter.write(line);            
			            }
			            JOptionPane.showMessageDialog(null, "Report created");
			            pst.close();
			            rs.close();
			            conn.close();
			            fileWriter.close();
			             
			        } catch (SQLException e1) {
			            System.out.println("Datababse error:");
			            JOptionPane.showMessageDialog(null, "Database error:");
			        } catch (IOException e2) {
			            System.out.println("File IO error:");
			            JOptionPane.showMessageDialog(null, "File IO error:");
			        }
				} else if (gen_table.equals("Employee")) {
					try {
						conn = sqliteConnection.dbConnector();
			        	String csvFilePath = filename + ".csv";
						String sql= "Select * from " + gen_table;
						pst= conn.prepareStatement(sql);
						rs = pst.executeQuery();
			             
			            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(csvFilePath));
			             
			            // write header line containing column names       
			            fileWriter.write("employee_id,uname,pword,sQuestion,sq_ans,role,fname,lname,email");
			            while (rs.next()) {
			                String employee_id = rs.getString("employee_id");
			                String uname = rs.getString("uname");
			                String pword = rs.getString("pword");
			                String sQuestion = rs.getString("sQuestion");
			                String sq_ans = rs.getString("sq_ans");
			                String role = rs.getString("role");
			                String fname = rs.getString("fname");
			                String lname = rs.getString("lname");
			                String email = rs.getString("email");
			                String line = String.format("\"%s\",%s,%s,%s,%s,%s,%s,%s,%s",
			                		employee_id,uname,pword,sQuestion,sq_ans,role,fname,lname,email);
			                 
			                fileWriter.newLine();
			                fileWriter.write(line);            
			            }
			            JOptionPane.showMessageDialog(null, "Report created");
			            pst.close();
			            rs.close();
			            conn.close();
			            fileWriter.close();
			             
			        } catch (SQLException e1) {
			            System.out.println(e1);
			            JOptionPane.showMessageDialog(null, e1);
			        } catch (IOException e2) {
			            System.out.println("File IO error:");
			            JOptionPane.showMessageDialog(null, "File IO error:");
			        }
				}
			}
		});
		btnReport.setBorder(null);
		btnReport.setBackground(new Color(225, 167, 48));
		btnReport.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		btnReport.setBounds(20, 126, 141, 30);
		contentPane.add(btnReport);
	}
	public void loadUserName(){
		try {
			String sql= "Select * from Testing";
			pst= conn.prepareStatement(sql);
			rs = pst.executeQuery();
			
			while(rs.next()==true){
			conn=sqliteConnection.dbConnector();
			transaction_id = rs.getString("transaction_id");
			String fname = rs.getString("first_name");
			String lname = rs.getString("last_name");
			String name = transaction_id +", " + fname + " " + lname;
			//comboBox_1.addItem(name);
			}
			rs.close();
			pst.close();
			conn.close();
			}catch(SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				System.out.print(e);
			}catch(Exception e1) {
				JOptionPane.showMessageDialog(null, e1);
			}
	}
	private void updateTable(String trans_id) {
		conn = sqliteConnection.dbConnector();
		String sql = "SELECT * FROM Testing where transaction_id="+trans_id;
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			//table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			try {
				rs.close();
				pst.close();
				conn.close();
			} catch (Exception e) {
				
			}
		}
	}
}
