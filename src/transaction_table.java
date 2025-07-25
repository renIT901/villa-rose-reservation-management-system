import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;

import net.proteanit.sql.DbUtils;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;

public class transaction_table extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//transaction_table frame = new transaction_table();
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
	Image img1 = new ImageIcon(this.getClass().getResource("/1.png")).getImage();
	private JTable table_1;
	String transaction_id;
	private ImageIcon format = null;
	private String filename=null;
	private int s = 0;
	byte[] person_image=null;
	private JComboBox comboBox_1;
	String first_name;
	String last_name;
	String check_in;
	String check_out;
	String room_type;
	String trans_id;
	String additionals;
	String st;
	String sql1;
	String sortingused = "5";
	int amount_paid;
	int balance;
	String status;
	List<String[]> data = null;
	private JCheckBox chckbxStatus;
	/**
	 * @wbp.nonvisual location=452,769
	 */
	private final JTextArea txtReceipt = new JTextArea();
	private JTextField textField;
	/**
	 * @wbp.nonvisual location=562,769
	 */
	private final JTextArea txtSign = new JTextArea();
	/**
	 * Create the frame.
	 */
	public transaction_table(int emp_id) {
		setResizable(false);
		System.setProperty("user.home", "C:\\Villa Rose Resort\\Centralized Folder");
		setTitle("Villa Rose System");
		conn = sqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Calibri Light", Font.PLAIN, 11));
		contentPane.setBackground(new Color(250, 245, 232));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		String[] colum = {"Select Field","transaction_id","first_name","last_name","email","contact_no","check_in","check_out","room_description","balance","amount_paid"};
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(210, 238, 1046, 324);
		contentPane.add(scrollPane);
		
		table_1 = new JTable();
		table_1.setDefaultEditor(Object.class, null);
		table_1.setFont(new Font("Calibri Light", Font.PLAIN, 11));
		scrollPane.setViewportView(table_1);
		updateTable();
		
		comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		comboBox_1.setBounds(232, 582, 311, 24);
		contentPane.add(comboBox_1);
		
		JButton btnprint = new JButton("Print Receipt");
		btnprint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
		            String[] lines = txtReceipt.getText().split("\\n");

					savePDF2(txtReceipt.getText(),txtSign.getText());
					//txtReceipt.print();
					txtReceipt.setText("");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally {
					
				}
			}
		});
		btnprint.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnprint.setBorder(null);
		btnprint.setBackground(new Color(225, 167, 48));
		btnprint.setBounds(232, 614, 147, 40);
		contentPane.add(btnprint);
		
		String[] colum2 = {"Select Status","Pending","Fully paid","Cancelled"};
		
		JComboBox comboBoxStatus = new JComboBox(colum2);
		comboBoxStatus.setFont(new Font("SansSerif", Font.PLAIN, 16));
		comboBoxStatus.setBounds(232, 147, 311, 24);
		contentPane.add(comboBoxStatus);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 200, 683);
		contentPane.add(panel);
		
		JLabel icon = new JLabel("");
		icon.setIcon(new ImageIcon(transaction_table.class.getResource("/villarose.png")));
		icon.setBounds(25, 26, 144, 151);
		panel.add(icon);
		
		JButton btn_transaction = new JButton("Transactions");
		btn_transaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				HomePage hpage = new HomePage(emp_id);
				hpage.setLocationRelativeTo(null);
				hpage.show();
			}
		});
		btn_transaction.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btn_transaction.setBorder(null);
		btn_transaction.setBackground(new Color(225, 167, 48));
		btn_transaction.setBounds(18, 191, 163, 40);
		panel.add(btn_transaction);
		
		JButton btn_managecontent = new JButton("Manage Content");
		btn_managecontent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ManageContent mpage = new ManageContent(emp_id);
				mpage.setLocationRelativeTo(null);
				mpage.show();
			}
		});
		btn_managecontent.setSelected(true);
		btn_managecontent.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btn_managecontent.setBorder(null);
		btn_managecontent.setBackground(new Color(225, 167, 48));
		btn_managecontent.setBounds(18, 293, 163, 40);
		panel.add(btn_managecontent);
		
		JDateChooser checkin = new JDateChooser();
		checkin.setBounds(709, 81, 311, 20);
		contentPane.add(checkin);
		
		
		JButton btn_logout = new JButton("Logout");
		btn_logout.addActionListener(new ActionListener() {
			private JFrame frame;
			public void actionPerformed(ActionEvent e) {
				frame = new JFrame();

				if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to Logout?")==JOptionPane.YES_NO_OPTION) {
					dispose();
					Login lpage = new Login();
					lpage.setLocationRelativeTo(null);
					lpage.show();
				}
			}
		});
		btn_logout.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btn_logout.setBorder(null);
		btn_logout.setBackground(new Color(225, 167, 48));
		btn_logout.setBounds(18, 594, 163, 40);
		panel.add(btn_logout);
		
		JButton btn_transactiontables = new JButton("Transaction Table");
		btn_transactiontables.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_transactiontables.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btn_transactiontables.setBorder(null);
		btn_transactiontables.setBackground(new Color(225, 167, 48));
		btn_transactiontables.setBounds(18, 242, 163, 40);
		panel.add(btn_transactiontables);
		
		JLabel lblNewLabel = new JLabel("Transaction Table");
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 30));
		lblNewLabel.setBounds(232, 10, 319, 60);
		contentPane.add(lblNewLabel);
		
		String[] colum1 = {"Select Field","first_name","last_name","employee_id"};
		JComboBox comboBox = new JComboBox(colum1);
		comboBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
		comboBox.setBounds(232, 81, 311, 24);
		contentPane.add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(232, 116, 311, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		String[] colum3 = {"Select Reservation Type","Walk-in","Appointment"};
		JComboBox comboBoxType = new JComboBox(colum3);
		comboBoxType.setFont(new Font("SansSerif", Font.PLAIN, 16));
		comboBoxType.setBounds(709, 112, 311, 24);
		contentPane.add(comboBoxType);
		
		JButton btnFilter = new JButton("Filter");
		btnFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					
					//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					//String date1 = sdf.format(checkin.getDate());
					//String date2 = sdf.format(checkout.getDate());
	
					String field = comboBox.getSelectedItem().toString();
					String filter = textField.getText();
					String type = comboBox_1.getSelectedItem().toString();
					
					String status = comboBoxStatus.getSelectedItem().toString();
					
					String reservation = comboBoxType.getSelectedItem().toString();
					
					try {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						String date1 = sdf.format(checkin.getDate());
						
						
						if (status.equals("Select Status") && comboBox.getSelectedIndex() == 0 && filter.isEmpty() && comboBoxType.getSelectedIndex() == 0){
							sortTable3(date1);
						}
						
						else if (status.equals("Select Status")  && comboBoxType.getSelectedIndex() == 0) {
							sortTable4(date1,field,filter);
						}
						else if (comboBox.getSelectedIndex() == 0 && filter.isEmpty()  && comboBoxType.getSelectedIndex() == 0) { 
						sortTable5(date1,status);
						}
						else if (comboBoxType.getSelectedIndex() == 0) {
							sortTable6(date1,status,field,filter);
						} else if (status.equals("Select Status") && comboBox.getSelectedIndex() == 0 && filter.isEmpty()){
							sortTable11(reservation,date1);
						}
						else if (status.equals("Select Status")) {
							sortTable12(reservation,date1,field,filter);
						}
						else if (comboBox.getSelectedIndex() == 0 && filter.isEmpty()) { 
						sortTable13(reservation,date1,status);
						}
						else {
							sortTable14(reservation,date1,status,field,filter);
						}
					} catch (NullPointerException e1) {

						if (status.equals("Select Status") && comboBoxType.getSelectedIndex() == 0){
							sortTable2(field,filter);
						}
						else if (filter.isEmpty()  && comboBox.getSelectedIndex() == 0 && comboBoxType.getSelectedIndex() == 0) {
							sortTable1(status);
						} else if (comboBoxType.getSelectedIndex() == 0) {
							sortTable(field,filter,status);
						}
						else if (status.equals("Select Status") && comboBox.getSelectedIndex() == 0 && filter.isEmpty()){
							sortTable7(reservation);
						} else if (status.equals("Select Status")) {
							sortTable8(reservation,field,filter);
						} else if (comboBox.getSelectedIndex() == 0 && filter.isEmpty()) { 
							sortTable9(reservation,status);
						}
						else {
						sortTable10(reservation,status,field,filter);
						}
					    // or set a default date
					   // Date defaultDate = new Date();
					    //jDateChooser.setDate(defaultDate);
					}
					catch (Exception e2) {
						System.out.print(e2);
					}
			
					

					
				}
				catch (Exception e1){
					System.out.print(e1);
					JOptionPane.showMessageDialog(null, "Select filter options.");
				}
			}
		});
		btnFilter.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnFilter.setBorder(null);
		btnFilter.setBackground(new Color(225, 167, 48));
		btnFilter.setBounds(232, 180, 147, 40);
		contentPane.add(btnFilter);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTable();
				comboBox.setSelectedIndex(0);
				comboBoxStatus.setSelectedIndex(0);
				comboBoxType.setSelectedIndex(0);
				checkin.setDate(null);
				textField.setText("");
			}
		});
		btnReset.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnReset.setBorder(null);
		btnReset.setBackground(new Color(225, 167, 48));
		btnReset.setBounds(396, 182, 147, 40);
		contentPane.add(btnReset);
		
		JButton btnExportToPdf = new JButton("Export to PDF");
		btnExportToPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String field = comboBox.getSelectedItem().toString();
					String filter = textField.getText();
					String status = comboBoxStatus.getSelectedItem().toString();
					String type = comboBox_1.getSelectedItem().toString();	
					String reservation = comboBoxType.getSelectedItem().toString();


					
					try {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						String date1 = sdf.format(checkin.getDate());
							if (sortingused.equals("4")) {
							data = retrieveDataFromTable3(date1);
							}
							else if (sortingused.equals("5")) {
							data = retrieveDataFromTable4(date1,field,filter);
							}
							else if (sortingused.equals("6")) {
							data = retrieveDataFromTable5(date1,status);
							}
							else if (sortingused.equals("7")) {
							data = retrieveDataFromTable6(date1,field,filter,status);
							}
							else if (sortingused.equals("12")) {
								data = retrieveDataFromTable11(reservation,date1);
								}
								else if (sortingused.equals("13")) {
								data = retrieveDataFromTable12(reservation,date1,field,filter);
								}
								else if (sortingused.equals("14")) {
								data = retrieveDataFromTable13(reservation,date1,status);
								}
								else if (sortingused.equals("15")) {
								data = retrieveDataFromTable14(reservation,date1,status,field,filter);
								}
					}catch (NullPointerException e1){
						if (sortingused.equals("3")){
							data = retrieveDataFromTable2(field,filter);
						}
						else if (sortingused.equals("2")) {
							data = retrieveDataFromTable1(status);
						}
						else if (sortingused.equals("1")) {
						data = retrieveDataFromTable(field,filter,status);
						}
						//else if (filter.isEmpty() && field.equals("Select Field") && status.equals("Select Status")) {
							//data = retrieveDataFromTable3();
						//}
						
						else if (sortingused.equals("8")) {
						data = retrieveDataFromTable7(reservation);
						}
						else if (sortingused.equals("9")) {
						data = retrieveDataFromTable8(reservation,field,filter);
						}
						else if (sortingused.equals("10")) {
						data = retrieveDataFromTable9(reservation,status);
						}
						else if (sortingused.equals("11")) {
						data = retrieveDataFromTable10(reservation,status,field,filter);
						}
						else {
							retrieveDataFromTable();
						}
					}
					
					
					
					savePDF(data);
	
				}
				catch(Exception e2) {
					System.out.print(e2);
					JOptionPane.showMessageDialog(null, "Select filter options.");
				}
				//List<String[]> data = retrieveDataFromTable(gen_table);
		       
			}
		});
		btnExportToPdf.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnExportToPdf.setBorder(null);
		btnExportToPdf.setBackground(new Color(225, 167, 48));
		btnExportToPdf.setBounds(1080, 582, 147, 40);
		contentPane.add(btnExportToPdf);
	
		
		JLabel lblNewLabel_4 = new JLabel("Date:");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(602, 77, 97, 24);
		contentPane.add(lblNewLabel_4);
		

		
		
		loadUserName();
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conn = sqliteConnection.dbConnector();
				try {
				String item = (String)comboBox_1.getSelectedItem();
				String substrings[] = item.split(",");
				trans_id = substrings[0];
				pst = conn.prepareStatement("SELECT * FROM Transaction_Records WHERE transaction_id='"+trans_id+"'");
				rs = pst.executeQuery();
				
				if(rs.next()==true) {
					first_name = rs.getString("first_name");
					last_name = rs.getString("last_name");
					check_in = rs.getString("check_in");
					check_out = rs.getString("check_out");
					room_type = rs.getString("room_description");
					amount_paid = rs.getInt("amount_paid");
					additionals = rs.getString("room_additionals");
					balance = rs.getInt("balance");
					
					txtSign.append("\n________________\n"+
							"   Villa Rose Resort\n" +
							"          Owner");
					
					int new_balance = balance-amount_paid;
					String[] arr2 = additionals.split(",");
					String[] arr = room_type.split(",");
					if(arr.length == 1) {
						if(arr2.length == 1) {
					txtReceipt.append("\t\t       Villa Rose Resorts \n" +
							"\t         Dinadiawan, 3P6M+QWW, Dipaculao, Aurora \n"+
							"\t\t Contact No. 0926 610 7400\n"+
							"\n=======================================================================\n" +
							"\t Transaction No: \t\t " + trans_id +"\n\n" +
							"\t First Name: \t\t\t" +first_name+ "\n\n" +
							"\t Last Name: \t\t\t" + last_name+ "\n\n" +
							"\t Check In: \t\t\t" + check_in + "\n\n" +
							"\t Check Out: \t\t\t" + check_out+ "\n\n" +
							"\t Accomodation: \t\t" + arr[0]+ "\n\n" +
							"\t Additionals: \t\t\t" + arr2[0]+ "\n\n" +
							"\n=======================================================================\n" +
							"\t Amount Paid: \t\t\t"+ amount_paid + "\n\n" +
							"\t Balance: \t\t\t" + new_balance + "\n" +
							"\n=======================================================================\n\n\n");
						} else if (arr2.length == 2) {
			txtReceipt.append("\t\t       Villa Rose Resorts \n" +
					"\t         Dinadiawan, 3P6M+QWW, Dipaculao, Aurora \n"+
					"\t\t Contact No. 0926 610 7400\n"+
					"\n=======================================================================\n" +
					"\t Transaction No: \t\t " + trans_id +"\n\n" +
					"\t First Name: \t\t\t" +first_name+ "\n\n" +
					"\t Last Name: \t\t\t" + last_name+ "\n\n" +
					"\t Check In: \t\t\t" + check_in + "\n\n" +
					"\t Check Out: \t\t\t" + check_out+ "\n\n" +
					"\t Accomodation: \t\t" + arr[0]+ "\n\n" +
					"\t Additionals: \t\t\t" + arr2[0]+ "\n\n" +
					"\t\t\t\t" + arr2[1] + " \n\n " +
					"\n=======================================================================\n" +
					"\t Amount Paid: \t\t\t"+ amount_paid + "\n\n" +
					"\t Balance: \t\t\t" + new_balance + "\n" +
					"\n=======================================================================\n\n\n");
				} else if(arr2.length == 3) {
			txtReceipt.append("\t\t       Villa Rose Resorts \n" +
					"\t         Dinadiawan, 3P6M+QWW, Dipaculao, Aurora \n"+
					"\t\t Contact No. 0926 610 7400\n"+
					"\n=======================================================================\n" +
					"\t Transaction No: \t\t " + trans_id +"\n\n" +
					"\t First Name: \t\t\t" +first_name+ "\n\n" +
					"\t Last Name: \t\t\t" + last_name+ "\n\n" +
					"\t Check In: \t\t\t" + check_in + "\n\n" +
					"\t Check Out: \t\t\t" + check_out+ "\n\n" +
					"\t Accomodation: \t\t" + arr[0]+ "\n\n" +
					"\t Additionals: \t\t\t" + arr2[0]+ "\n\n" +
					"\t\t\t\t" + arr2[1] + " \n\n " +
					"\t\t\t\t" + arr2[2] + " \n\n " +
					"\n=======================================================================\n" +
					"\t Amount Paid: \t\t\t"+ amount_paid + "\n\n" +
					"\t Balance: \t\t\t" + new_balance + "\n" +
					"\n=======================================================================\n\n\n");
				}else if(arr2.length == 4) {
					txtReceipt.append("\t\t       Villa Rose Resorts \n" +
							"\t         Dinadiawan, 3P6M+QWW, Dipaculao, Aurora \n"+
							"\t\t Contact No. 0926 610 7400\n"+
							"\n=======================================================================\n" +
							"\t Transaction No: \t\t " + trans_id +"\n\n" +
							"\t First Name: \t\t\t" +first_name+ "\n\n" +
							"\t Last Name: \t\t\t" + last_name+ "\n\n" +
							"\t Check In: \t\t\t" + check_in + "\n\n" +
							"\t Check Out: \t\t\t" + check_out+ "\n\n" +
							"\t Accomodation: \t\t" + arr[0]+ "\n\n" +
							"\t Additionals: \t\t\t" + arr2[0]+ "\n\n" +
							"\t\t\t\t" + arr2[1] + " \n\n " +
							"\t\t\t\t" + arr2[2] + " \n\n " +
							"\t\t\t\t" + arr2[3] + " \n\n " +
							"\n=======================================================================\n" +
							"\t Amount Paid: \t\t\t"+ amount_paid + "\n\n" +
							"\t Balance: \t\t\t" + new_balance + "\n" +
							"\n=======================================================================\n\n\n");
						}
					} else if (arr.length == 2) {
						if(arr2.length == 1) {
						txtReceipt.append("\t\t       Villa Rose Resorts \n" +
								"\t         Dinadiawan, 3P6M+QWW, Dipaculao, Aurora \n"+
								"\t\t Contact No. 0926 610 7400\n"+
								"\n=======================================================================\n" +
								"\t Transaction No: \t\t " + trans_id +"\n\n" +
								"\t First Name: \t\t\t" +first_name+ "\n\n" +
								"\t Last Name: \t\t\t" + last_name+ "\n\n" +
								"\t Check In: \t\t\t" + check_in + "\n\n" +
								"\t Check Out: \t\t\t" + check_out+ "\n\n" +
								"\t Accomodation: \t\t" + arr[0]+ "\n\n" +
								"\t\t\t\t" + arr[1] + " \n\n " +
								"\t Additionals: \t\t\t" + arr2[0]+ "\n\n" +
								"\n=======================================================================\n" +
								"\t Amount Paid: \t\t\t"+ amount_paid + "\n\n" +
								"\t Balance: \t\t\t" + new_balance + "\n" +
								"\n=======================================================================\n\n\n");
						} else if (arr2.length == 2) {
							txtReceipt.append("\t\t       Villa Rose Resorts \n" +
									"\t         Dinadiawan, 3P6M+QWW, Dipaculao, Aurora \n"+
									"\t\t Contact No. 0926 610 7400\n"+
									"\n=======================================================================\n" +
									"\t Transaction No: \t\t " + trans_id +"\n\n" +
									"\t First Name: \t\t\t" +first_name+ "\n\n" +
									"\t Last Name: \t\t\t" + last_name+ "\n\n" +
									"\t Check In: \t\t\t" + check_in + "\n\n" +
									"\t Check Out: \t\t\t" + check_out+ "\n\n" +
									"\t Accomodation: \t\t" + arr[0]+ "\n\n" +
									"\t\t\t\t" + arr[1] + " \n\n " +
									"\t Additionals: \t\t\t" + arr2[0]+ "\n\n" +
									"\t\t\t\t" + arr2[1] + " \n\n " +
									"\n=======================================================================\n" +
									"\t Amount Paid: \t\t\t"+ amount_paid + "\n\n" +
									"\t Balance: \t\t\t" + new_balance + "\n" +
									"\n=======================================================================\n\n\n");
							} else if (arr2.length == 3) {
								txtReceipt.append("\t\t       Villa Rose Resorts \n" +
										"\t         Dinadiawan, 3P6M+QWW, Dipaculao, Aurora \n"+
										"\t\t Contact No. 0926 610 7400\n"+
										"\n=======================================================================\n" +
										"\t Transaction No: \t\t " + trans_id +"\n\n" +
										"\t First Name: \t\t\t" +first_name+ "\n\n" +
										"\t Last Name: \t\t\t" + last_name+ "\n\n" +
										"\t Check In: \t\t\t" + check_in + "\n\n" +
										"\t Check Out: \t\t\t" + check_out+ "\n\n" +
										"\t Accomodation: \t\t" + arr[0]+ "\n\n" +
										"\t\t\t\t" + arr[1] + " \n\n " +
										"\t Additionals: \t\t\t" + arr2[0]+ "\n\n" +
										"\t\t\t\t" + arr2[1] + " \n\n " +
										"\t\t\t\t" + arr2[2] + " \n\n " +
										"\n=======================================================================\n" +
										"\t Amount Paid: \t\t\t"+ amount_paid + "\n\n" +
										"\t Balance: \t\t\t" + new_balance + "\n" +
										"\n=======================================================================\n\n\n");
								} else if (arr2.length == 4) {
									txtReceipt.append("\t\t       Villa Rose Resorts \n" +
											"\t         Dinadiawan, 3P6M+QWW, Dipaculao, Aurora \n"+
											"\t\t Contact No. 0926 610 7400\n"+
											"\n=======================================================================\n" +
											"\t Transaction No: \t\t " + trans_id +"\n\n" +
											"\t First Name: \t\t\t" +first_name+ "\n\n" +
											"\t Last Name: \t\t\t" + last_name+ "\n\n" +
											"\t Check In: \t\t\t" + check_in + "\n\n" +
											"\t Check Out: \t\t\t" + check_out+ "\n\n" +
											"\t Accomodation: \t\t" + arr[0]+ "\n\n" +
											"\t\t\t\t" + arr[1] + " \n\n " +
											"\t Additionals: \t\t\t" + arr2[0]+ "\n\n" +
											"\t\t\t\t" + arr2[1] + " \n\n " +
											"\t\t\t\t" + arr2[2] + " \n\n " +
											"\t\t\t\t" + arr2[3] + " \n\n " +
											"\n=======================================================================\n" +
											"\t Amount Paid: \t\t\t"+ amount_paid + "\n\n" +
											"\t Balance: \t\t\t" + new_balance + "\n" +
											"\n=======================================================================\n\n\n");
									} 
						} else if (arr.length == 3) {
							if(arr2.length == 1) {
								txtReceipt.append("\t\t       Villa Rose Resorts \n" +
										"\t         Dinadiawan, 3P6M+QWW, Dipaculao, Aurora \n"+
										"\t\t Contact No. 0926 610 7400\n"+
										"\n=======================================================================\n" +
										"\t Transaction No: \t\t " + trans_id +"\n\n" +
										"\t First Name: \t\t\t" +first_name+ "\n\n" +
										"\t Last Name: \t\t\t" + last_name+ "\n\n" +
										"\t Check In: \t\t\t" + check_in + "\n\n" +
										"\t Check Out: \t\t\t" + check_out+ "\n\n" +
										"\t Accomodation: \t\t" + arr[0]+ "\n\n" +
										"\t\t\t\t" + arr[1] + " \n\n " +
										"\t\t\t\t" + arr[2] + " \n\n " +
										"\t Additionals: \t\t\t" + arr2[0]+ "\n\n" +
										"\n=======================================================================\n" +
										"\t Amount Paid: \t\t\t"+ amount_paid + "\n\n" +
										"\t Balance: \t\t\t" + new_balance + "\n" +
										"\n=======================================================================\n\n\n");
								} else if (arr2.length == 2) {
									txtReceipt.append("\t\t       Villa Rose Resorts \n" +
											"\t         Dinadiawan, 3P6M+QWW, Dipaculao, Aurora \n"+
											"\t\t Contact No. 0926 610 7400\n"+
											"\n=======================================================================\n" +
											"\t Transaction No: \t\t " + trans_id +"\n\n" +
											"\t First Name: \t\t\t" +first_name+ "\n\n" +
											"\t Last Name: \t\t\t" + last_name+ "\n\n" +
											"\t Check In: \t\t\t" + check_in + "\n\n" +
											"\t Check Out: \t\t\t" + check_out+ "\n\n" +
											"\t Accomodation: \t\t" + arr[0]+ "\n\n" +
											"\t\t\t\t" + arr[1] + " \n\n " +
											"\t\t\t\t" + arr[2] + " \n\n " +
											"\t Additionals: \t\t\t" + arr2[0]+ "\n\n" +
											"\t\t\t\t" + arr2[1] + " \n\n " +
											"\n=======================================================================\n" +
											"\t Amount Paid: \t\t\t"+ amount_paid + "\n\n" +
											"\t Balance: \t\t\t" + new_balance + "\n" +
											"\n=======================================================================\n\n\n");
									} else if (arr2.length == 3) {
										txtReceipt.append("\t\t       Villa Rose Resorts \n" +
												"\t         Dinadiawan, 3P6M+QWW, Dipaculao, Aurora \n"+
												"\t\t Contact No. 0926 610 7400\n"+
												"\n=======================================================================\n" +
												"\t Transaction No: \t\t " + trans_id +"\n\n" +
												"\t First Name: \t\t\t" +first_name+ "\n\n" +
												"\t Last Name: \t\t\t" + last_name+ "\n\n" +
												"\t Check In: \t\t\t" + check_in + "\n\n" +
												"\t Check Out: \t\t\t" + check_out+ "\n\n" +
												"\t Accomodation: \t\t" + arr[0]+ "\n\n" +
												"\t\t\t\t" + arr[1] + " \n\n " +
												"\t\t\t\t" + arr[2] + " \n\n " +
												"\t Additionals: \t\t\t" + arr2[0]+ "\n\n" +
												"\t\t\t\t" + arr2[1] + " \n\n " +
												"\t\t\t\t" + arr2[2] + " \n\n " +
												"\n=======================================================================\n" +
												"\t Amount Paid: \t\t\t"+ amount_paid + "\n\n" +
												"\t Balance: \t\t\t" + new_balance + "\n" +
												"\n=======================================================================\n\n\n");
										} else if (arr2.length == 4) {
											txtReceipt.append("\t\t       Villa Rose Resorts \n" +
													"\t         Dinadiawan, 3P6M+QWW, Dipaculao, Aurora \n"+
													"\t\t Contact No. 0926 610 7400\n"+
													"\n=======================================================================\n" +
													"\t Transaction No: \t\t " + trans_id +"\n\n" +
													"\t First Name: \t\t\t" +first_name+ "\n\n" +
													"\t Last Name: \t\t\t" + last_name+ "\n\n" +
													"\t Check In: \t\t\t" + check_in + "\n\n" +
													"\t Check Out: \t\t\t" + check_out+ "\n\n" +
													"\t Accomodation: \t\t" + arr[0]+ "\n\n" +
													"\t\t\t\t" + arr[1] + " \n\n " +
													"\t\t\t\t" + arr[2] + " \n\n " +
													"\t Additionals: \t\t\t" + arr2[0]+ "\n\n" +
													"\t\t\t\t" + arr2[1] + " \n\n " +
													"\t\t\t\t" + arr2[2] + " \n\n " +
													"\t\t\t\t" + arr2[3] + " \n\n " +
													"\n=======================================================================\n" +
													"\t Amount Paid: \t\t\t"+ amount_paid + "\n\n" +
													"\t Balance: \t\t\t" + new_balance + "\n" +
													"\n=======================================================================\n\n\n");
											} 
							} else if (arr.length == 4) {
								if(arr2.length == 1) {
									txtReceipt.append("\t\t       Villa Rose Resorts \n" +
											"\t         Dinadiawan, 3P6M+QWW, Dipaculao, Aurora \n"+
											"\t\t Contact No. 0926 610 7400\n"+
											"\n=======================================================================\n" +
											"\t Transaction No: \t\t " + trans_id +"\n\n" +
											"\t First Name: \t\t\t" +first_name+ "\n\n" +
											"\t Last Name: \t\t\t" + last_name+ "\n\n" +
											"\t Check In: \t\t\t" + check_in + "\n\n" +
											"\t Check Out: \t\t\t" + check_out+ "\n\n" +
											"\t Accomodation: \t\t" + arr[0]+ "\n\n" +
											"\t\t\t\t" + arr[1] + " \n\n " +
											"\t\t\t\t" + arr[2] + " \n\n " +
											"\t\t\t\t" + arr[3] + " \n\n " +
											"\t Additionals: \t\t\t" + arr2[0]+ "\n\n" +
											"\n=======================================================================\n" +
											"\t Amount Paid: \t\t\t"+ amount_paid + "\n\n" +
											"\t Balance: \t\t\t" + new_balance + "\n" +
											"\n=======================================================================\n\n\n");
									} else if (arr2.length == 2) {
										txtReceipt.append("\t\t       Villa Rose Resorts \n" +
												"\t         Dinadiawan, 3P6M+QWW, Dipaculao, Aurora \n"+
												"\t\t Contact No. 0926 610 7400\n"+
												"\n=======================================================================\n" +
												"\t Transaction No: \t\t " + trans_id +"\n\n" +
												"\t First Name: \t\t\t" +first_name+ "\n\n" +
												"\t Last Name: \t\t\t" + last_name+ "\n\n" +
												"\t Check In: \t\t\t" + check_in + "\n\n" +
												"\t Check Out: \t\t\t" + check_out+ "\n\n" +
												"\t Accomodation: \t\t" + arr[0]+ "\n\n" +
												"\t\t\t\t" + arr[1] + " \n\n " +
												"\t\t\t\t" + arr[2] + " \n\n " +
												"\t\t\t\t" + arr[3] + " \n\n " +
												"\t Additionals: \t\t\t" + arr2[0]+ "\n\n" +
												"\t\t\t\t" + arr2[1] + " \n\n " +
												"\n=======================================================================\n" +
												"\t Amount Paid: \t\t\t"+ amount_paid + "\n\n" +
												"\t Balance: \t\t\t" + new_balance + "\n" +
												"\n=======================================================================\n\n\n");
										} else if (arr2.length == 3) {
											txtReceipt.append("\t\t       Villa Rose Resorts \n" +
													"\t         Dinadiawan, 3P6M+QWW, Dipaculao, Aurora \n"+
													"\t\t Contact No. 0926 610 7400\n"+
													"\n=======================================================================\n" +
													"\t Transaction No: \t\t " + trans_id +"\n\n" +
													"\t First Name: \t\t\t" +first_name+ "\n\n" +
													"\t Last Name: \t\t\t" + last_name+ "\n\n" +
													"\t Check In: \t\t\t" + check_in + "\n\n" +
													"\t Check Out: \t\t\t" + check_out+ "\n\n" +
													"\t Accomodation: \t\t" + arr[0]+ "\n\n" +
													"\t\t\t\t" + arr[1] + " \n\n " +
													"\t\t\t\t" + arr[2] + " \n\n " +
													"\t\t\t\t" + arr[3] + " \n\n " +
													"\t Additionals: \t\t\t" + arr2[0]+ "\n\n" +
													"\t\t\t\t" + arr2[1] + " \n\n " +
													"\t\t\t\t" + arr2[2] + " \n\n " +
													"\n=======================================================================\n" +
													"\t Amount Paid: \t\t\t"+ amount_paid + "\n\n" +
													"\t Balance: \t\t\t" + new_balance + "\n" +
													"\n=======================================================================\n\n\n");
											} 
										else if (arr2.length == 3) {
											txtReceipt.append("\t\t       Villa Rose Resorts \n" +
													"\t         Dinadiawan, 3P6M+QWW, Dipaculao, Aurora \n"+
													"\t\t Contact No. 0926 610 7400\n"+
													"\n=======================================================================\n" +
													"\t Transaction No: \t\t " + trans_id +"\n\n" +
													"\t First Name: \t\t\t" +first_name+ "\n\n" +
													"\t Last Name: \t\t\t" + last_name+ "\n\n" +
													"\t Check In: \t\t\t" + check_in + "\n\n" +
													"\t Check Out: \t\t\t" + check_out+ "\n\n" +
													"\t Accomodation: \t\t" + arr[0]+ "\n\n" +
													"\t\t\t\t" + arr[1] + " \n\n " +
													"\t\t\t\t" + arr[2] + " \n\n " +
													"\t\t\t\t" + arr[3] + " \n\n " +
													"\t Additionals: \t\t\t" + arr2[0]+ "\n\n" +
													"\t\t\t\t" + arr2[1] + " \n\n " +
													"\t\t\t\t" + arr2[2] + " \n\n " +
													"\t\t\t\t" + arr2[3] + " \n\n " +
													"\n=======================================================================\n" +
													"\t Amount Paid: \t\t\t"+ amount_paid + "\n\n" +
													"\t Balance: \t\t\t" + new_balance + "\n" +
													"\n=======================================================================\n\n\n");
											} 
								} else if (arr.length == 5) {
									if(arr2.length == 1) {
										txtReceipt.append("\t\t       Villa Rose Resorts \n" +
												"\t         Dinadiawan, 3P6M+QWW, Dipaculao, Aurora \n"+
												"\t\t Contact No. 0926 610 7400\n"+
												"\n=======================================================================\n" +
												"\t Transaction No: \t\t " + trans_id +"\n\n" +
												"\t First Name: \t\t\t" +first_name+ "\n\n" +
												"\t Last Name: \t\t\t" + last_name+ "\n\n" +
												"\t Check In: \t\t\t" + check_in + "\n\n" +
												"\t Check Out: \t\t\t" + check_out+ "\n\n" +
												"\t Accomodation: \t\t" + arr[0]+ "\n\n" +
												"\t\t\t\t" + arr[1] + " \n\n " +
												"\t\t\t\t" + arr[2] + " \n\n " +
												"\t\t\t\t" + arr[3] + " \n\n " +
												"\t\t\t\t" + arr[4] + " \n\n " +
												"\t Additionals: \t\t\t" + arr2[0]+ "\n\n" +
												"\n=======================================================================\n" +
												"\t Amount Paid: \t\t\t"+ amount_paid + "\n\n" +
												"\t Balance: \t\t\t" + new_balance + "\n" +
												"\n=======================================================================\n\n\n");
										} else if (arr2.length == 2) {
											txtReceipt.append("\t\t       Villa Rose Resorts \n" +
													"\t         Dinadiawan, 3P6M+QWW, Dipaculao, Aurora \n"+
													"\t\t Contact No. 0926 610 7400\n"+
													"\n=======================================================================\n" +
													"\t Transaction No: \t\t " + trans_id +"\n\n" +
													"\t First Name: \t\t\t" +first_name+ "\n\n" +
													"\t Last Name: \t\t\t" + last_name+ "\n\n" +
													"\t Check In: \t\t\t" + check_in + "\n\n" +
													"\t Check Out: \t\t\t" + check_out+ "\n\n" +
													"\t Accomodation: \t\t" + arr[0]+ "\n\n" +
													"\t\t\t\t" + arr[1] + " \n\n " +
													"\t\t\t\t" + arr[2] + " \n\n " +
													"\t\t\t\t" + arr[3] + " \n\n " +
													"\t\t\t\t" + arr[4] + " \n\n " +
													"\t Additionals: \t\t\t" + arr2[0]+ "\n\n" +
													"\t\t\t\t" + arr2[1] + " \n" +
													"\n=======================================================================\n" +
													"\t Amount Paid: \t\t\t"+ amount_paid + "\n\n" +
													"\t Balance: \t\t\t" + new_balance + "\n" +
													"\n=======================================================================\n\n\n");
											} else if (arr2.length == 3) {
												txtReceipt.append("\t\t       Villa Rose Resorts \n" +
														"\t         Dinadiawan, 3P6M+QWW, Dipaculao, Aurora \n"+
														"\t\t Contact No. 0926 610 7400\n"+
														"\n=======================================================================\n" +
														"\t Transaction No: \t\t " + trans_id +"\n\n" +
														"\t First Name: \t\t\t" +first_name+ "\n\n" +
														"\t Last Name: \t\t\t" + last_name+ "\n\n" +
														"\t Check In: \t\t\t" + check_in + "\n\n" +
														"\t Check Out: \t\t\t" + check_out+ "\n\n" +
														"\t Accomodation: \t\t" + arr[0]+ "\n\n" +
														"\t\t\t\t" + arr[1] + " \n\n " +
														"\t\t\t\t" + arr[2] + " \n\n " +
														"\t\t\t\t" + arr[3] + " \n\n " +
														"\t\t\t\t" + arr[4] + " \n\n " +
														"\t Additionals: \t\t\t" + arr2[0]+ "\n\n" +
														"\t\t\t\t" + arr2[1] + " \n\n " +
														"\t\t\t\t" + arr2[2] + " \n " +
														"\n=======================================================================\n" +
														"\t Amount Paid: \t\t\t"+ amount_paid + "\n\n" +
														"\t Balance: \t\t\t" + new_balance + "\n" +
														"\n=======================================================================\n\n\n");
												} else if (arr2.length == 4) {
													txtReceipt.append("\t\t       Villa Rose Resorts \n" +
															"\t         Dinadiawan, 3P6M+QWW, Dipaculao, Aurora \n"+
															"\t\t Contact No. 0926 610 7400\n"+
															"\n=======================================================================\n" +
															"\t Transaction No: \t\t " + trans_id +"\n\n" +
															"\t First Name: \t\t\t" +first_name+ "\n\n" +
															"\t Last Name: \t\t\t" + last_name+ "\n\n" +
															"\t Check In: \t\t\t" + check_in + "\n\n" +
															"\t Check Out: \t\t\t" + check_out+ "\n\n" +
															"\t Accomodation: \t\t" + arr[0]+ "\n\n" +
															"\t\t\t\t" + arr[1] + " \n\n " +
															"\t\t\t\t" + arr[2] + " \n\n " +
															"\t\t\t\t" + arr[3] + " \n\n " +
															"\t\t\t\t" + arr[4] + " \n\n " +
															"\t Additionals: \t\t\t" + arr2[0]+ "\n\n" +
															"\t\t\t\t" + arr2[1] + " \n\n " +
															"\t\t\t\t" + arr2[2] + " \n\n " +
															"\t\t\t\t" + arr2[3] + " \n" +
															"\n=======================================================================\n" +
															"\t Amount Paid: \t\t\t"+ amount_paid + "\n\n" +
															"\t Balance: \t\t\t" + new_balance + "\n" +
															"\n=======================================================================\n\n\n");
													} 
									} else {
										;
									}
				}
				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
				finally {
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
		

	}
	private void updateTable() {
		conn = sqliteConnection.dbConnector();
		String sql = "SELECT * FROM Transaction_Records";
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Select filter options.");
		} finally {
			try {
				rs.close();
				pst.close();
			} catch (Exception e) {
				System.out.print(e);
			}
		}
	}
	
	private void sortTable(String field, String sort, String status) {
		sortingused = "1";
		conn = sqliteConnection.dbConnector();
		String sql = "SELECT * FROM Transaction_Records WHERE " + field + " = " + "'"+sort+"'" + " AND status = " + "'"+status+"'";
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			
				
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Select filter options.");
		} finally {
			try {
				conn.close();
				rs.close();
				pst.close();
			} catch (Exception e) {
				System.out.print(e);
			}
		}
	}
	
	private void sortTable1(String status) {
		sortingused = "2";
		conn = sqliteConnection.dbConnector();
		String sql = "SELECT * FROM Transaction_Records WHERE status = " + "'"+status+"'";
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			
				
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Select filter options.");
		} finally {
			try {
				conn.close();
				rs.close();
				pst.close();
			} catch (Exception e) {
				System.out.print(e);
			}
		}
	}
	
	private void sortTable2(String field, String sort) {
		sortingused = "3";
		conn = sqliteConnection.dbConnector();
		String sql = "SELECT * FROM Transaction_Records WHERE " + field + " = " + "'"+sort+"'";
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			
				
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Select filter options.");
		} finally {
			try {
				conn.close();
				rs.close();
				pst.close();
			} catch (Exception e) {
				System.out.print(e);
			}
		}
	}
	
	private void sortTable3(String checkin) {
		sortingused = "4";
		conn = sqliteConnection.dbConnector();
		String sql = "SELECT * FROM Transaction_Records WHERE check_in =" + "'"+checkin+"'";
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			//JOptionPane.showMessageDialog(null, "Select filter options.");
		} finally {
			try {
				conn.close();
				rs.close();
				pst.close();
			} catch (Exception e) {
				System.out.print(e);
			}
		}
	}
	
	private void sortTable4(String checkin, String field, String sort) {
		sortingused = "5";
		conn = sqliteConnection.dbConnector();
		String sql = "SELECT * FROM Transaction_Records WHERE " + field + " = " + "'"+sort+"' AND check_in =" + "'"+checkin+"'";
		
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			
				
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Select filter options.");
		} finally {
			try {
				conn.close();
				rs.close();
				pst.close();
			} catch (Exception e) {
				System.out.print(e);
			}
		}
	}
	
	private void sortTable5(String checkin, String status) {
		sortingused = "6";
		conn = sqliteConnection.dbConnector();
		String sql = "SELECT * FROM Transaction_Records WHERE status =" + "'"+status+"'" + " AND check_in =" + "'"+checkin+"'";
		
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			
				
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Select filter options.");
		} finally {
			try {
				conn.close();
				rs.close();
				pst.close();
			} catch (Exception e) {
				System.out.print(e);
			}
		}
	}
	
	private void sortTable6(String checkin, String status, String field, String sort) {
		sortingused = "7";
		conn = sqliteConnection.dbConnector();
		String sql = "SELECT * FROM Transaction_Records WHERE " + field + " = " + "'"+sort+"' AND check_in =" + "'"+checkin+"' And status=" + "'"+status+"'";
		
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			
				
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Select filter options.");
		} finally {
			try {
				conn.close();
				rs.close();
				pst.close();
			} catch (Exception e) {
				System.out.print(e);
			}
		}
	}
	
	private void sortTable7(String reservation) {
		sortingused = "8";
		conn = sqliteConnection.dbConnector();
		String sql = "SELECT * FROM Transaction_Records WHERE reservation_type=" + "'"+reservation+"'";
		
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			
				
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Select filter options.");
		} finally {
			try {
				conn.close();
				rs.close();
				pst.close();
			} catch (Exception e) {
				System.out.print(e);
			}
		}
	}
	
	private void sortTable8(String reservation, String field, String sort) {
		sortingused = "9";
		conn = sqliteConnection.dbConnector();
		String sql = "SELECT * FROM Transaction_Records WHERE " + field + " = " + "'"+sort+"' AND reservation_type =" + "'"+reservation+"'";
		
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			
				
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Select filter options.");
		} finally {
			try {
				conn.close();
				rs.close();
				pst.close();
			} catch (Exception e) {
				System.out.print(e);
			}
		}
	}
	
	private void sortTable9(String reservation, String status) {
		sortingused = "10";
		conn = sqliteConnection.dbConnector();
		String sql = "SELECT * FROM Transaction_Records WHERE reservation_type =" + "'"+reservation+"' And status=" + "'"+status+"'";
		
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			
				
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Select filter options.");
		} finally {
			try {
				conn.close();
				rs.close();
				pst.close();
			} catch (Exception e) {
				System.out.print(e);
			}
		}
	}
	
	private void sortTable10(String reservation, String status, String field, String sort) {
		sortingused = "11";
		conn = sqliteConnection.dbConnector();
		String sql = "SELECT * FROM Transaction_Records WHERE " + field + " = " + "'"+sort+"' AND reservation_type ="+"'"+reservation+"' And status=" + "'"+status+"'";
		
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			
				
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			System.out.print(e);
			JOptionPane.showMessageDialog(null, "Select filter options.");
		} finally {
			try {
				conn.close();
				rs.close();
				pst.close();
			} catch (Exception e) {
				System.out.print(e);
			}
		}
	}
	
	private void sortTable11(String reservation, String checkin) {
		sortingused = "12";
		conn = sqliteConnection.dbConnector();
		String sql = "SELECT * FROM Transaction_Records WHERE  check_in =" + "'"+checkin+"' And reservation_type=" + "'"+reservation+"'";
		
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			
				
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Select filter options.");
		} finally {
			try {
				conn.close();
				rs.close();
				pst.close();
			} catch (Exception e) {
				System.out.print(e);
			}
		}
	}
	
	private void sortTable14(String reservation, String checkin, String status, String field, String sort) {
		sortingused = "15";
		conn = sqliteConnection.dbConnector();
		String sql = "SELECT * FROM Transaction_Records WHERE " + field + " = " + "'"+sort+"' AND check_in =" + "'"+checkin+"' And status=" + "'"+status+"'  And reservation_type=" + "'"+reservation+"'";
		
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			
				
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Select filter options.");
		} finally {
			try {
				conn.close();
				rs.close();
				pst.close();
			} catch (Exception e) {
				System.out.print(e);
			}
		}
	}
	
	private void sortTable12(String reservation, String checkin, String field, String sort) {
		sortingused = "13";
		conn = sqliteConnection.dbConnector();
		String sql = "SELECT * FROM Transaction_Records WHERE " + field + " = " + "'"+sort+"' AND check_in =" + "'"+checkin+"' And reservation_type=" + "'"+reservation+"'";
		
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			
				
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Select filter options.");
		} finally {
			try {
				conn.close();
				rs.close();
				pst.close();
			} catch (Exception e) {
				System.out.print(e);
			}
		}
	}
	
	private void sortTable13(String reservation, String checkin, String status) {
		sortingused = "14";
		conn = sqliteConnection.dbConnector();
		String sql = "SELECT * FROM Transaction_Records WHERE check_in =" + "'"+checkin+"' AND status=" + "'"+status+"'  AND reservation_type=" + "'"+reservation+"'";
		
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			
				
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			System.out.print(e);
			JOptionPane.showMessageDialog(null, "Select filter options.");
		} finally {
			try {
				conn.close();
				rs.close();
				pst.close();
			} catch (Exception e) {
				System.out.print(e);
			}
		}
	}
	
	private void getID() {
		conn = sqliteConnection.dbConnector();
	}
	public void loadUserName(){
		try {
			String sql= "Select * from Transaction_Records";
			pst= conn.prepareStatement(sql);
			rs = pst.executeQuery();
			comboBox_1.addItem("Select Transaction Record");
			while(rs.next()==true){
			conn=sqliteConnection.dbConnector();
			transaction_id = rs.getString("transaction_id");
			String fname = rs.getString("first_name");
			String lname = rs.getString("last_name");
			String name = transaction_id +", " + fname + " " + lname;
			comboBox_1.addItem(name);
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
	
	private void print(String trans_id) {
		conn = sqliteConnection.dbConnector();
		String sql = "SELECT * FROM Transaction_Records where transaction_id="+trans_id;
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			
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
	
	 private List<String[]> retrieveDataFromTable(String field, String sort, String status) {
	        data = new ArrayList<>();
	        try {
	        	String[] myArray = {"transaction_id", "first_name", "last_name", "email", "contact_no", "check_in", "check_out", "room_description", "room_additionals", "balance", "amount_paid", "payment_proof", "employee_id", "status", "reservation_type"};
	        	data.add(myArray);
	    		conn = sqliteConnection.dbConnector();
	    		sql1 = "SELECT * FROM Transaction_Records WHERE " + field + " = " + "'"+sort+"'" + " AND status = " + "'"+status+"'";		        
	    		pst = conn.prepareStatement(sql1);
		        ResultSet rs = pst.executeQuery();
	    		
	            while (rs.next()) {
	                String[] row = new String[rs.getMetaData().getColumnCount()];
	                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
	                    row[i-1] = rs.getString(i);
	                }
	                data.add(row);
	            }
	            rs.close();
	            pst.close();
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return data;
	    }
	 
	 private List<String[]> retrieveDataFromTable1(String status) {
	        data = new ArrayList<>();
	        try {
	        	String[] myArray = {"transaction_id", "first_name", "last_name", "email", "contact_no", "check_in", "check_out", "room_description", "room_additionals", "balance", "amount_paid", "payment_proof", "employee_id", "status", "reservation_type"};
	        	data.add(myArray);
	    		conn = sqliteConnection.dbConnector();
	    		sql1 = "SELECT * FROM Transaction_Records WHERE status = " + "'"+status+"'";
		        pst = conn.prepareStatement(sql1);
		        ResultSet rs = pst.executeQuery();
	    		
	            while (rs.next()) {
	                String[] row = new String[rs.getMetaData().getColumnCount()];
	                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
	                    row[i-1] = rs.getString(i);
	                }
	                data.add(row);
	            }
	            rs.close();
	            pst.close();
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return data;
	    }
	 
	 private List<String[]> retrieveDataFromTable2(String field, String sort) {
	        data = new ArrayList<>();
	        try {
	        	String[] myArray = {"transaction_id", "first_name", "last_name", "email", "contact_no", "check_in", "check_out", "room_description", "room_additionals", "balance", "amount_paid", "payment_proof", "employee_id", "status", "reservation_type"};
	        	data.add(myArray);
	    		conn = sqliteConnection.dbConnector();
	    		sql1 = "SELECT * FROM Transaction_Records WHERE " + field + " = " + "'"+sort+"'";;
		        pst = conn.prepareStatement(sql1);
		        ResultSet rs = pst.executeQuery();
	    		
	            while (rs.next()) {
	                String[] row = new String[rs.getMetaData().getColumnCount()];
	                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
	                    row[i-1] = rs.getString(i);
	                }
	                data.add(row);
	            }
	            rs.close();
	            pst.close();
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return data;
	    }
	 
	 private List<String[]> retrieveDataFromTable3(String checkin) {
	        data = new ArrayList<>();
	        try {
	        	String[] myArray = {"transaction_id", "first_name", "last_name", "email", "contact_no", "check_in", "check_out", "room_description", "room_additionals", "balance", "amount_paid", "payment_proof", "employee_id", "status", "reservation_type"};
	        	data.add(myArray);
	    		conn = sqliteConnection.dbConnector();
	    		sql1 = "SELECT * FROM Transaction_Records WHERE check_in =" + "'"+checkin+"'";;
		        pst = conn.prepareStatement(sql1);
		        ResultSet rs = pst.executeQuery();
	    		
	            while (rs.next()) {
	                String[] row = new String[rs.getMetaData().getColumnCount()];
	                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
	                    row[i-1] = rs.getString(i);
	                }
	                data.add(row);
	            }
	            rs.close();
	            pst.close();
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return data;
	    }
	 
	 private List<String[]> retrieveDataFromTable4(String checkin, String field, String sort) {
	        data = new ArrayList<>();
	        try {
	        	String[] myArray = {"transaction_id", "first_name", "last_name", "email", "contact_no", "check_in", "check_out", "room_description", "room_additionals", "balance", "amount_paid", "payment_proof", "employee_id", "status", "reservation_type"};
	        	data.add(myArray);
	    		conn = sqliteConnection.dbConnector();
	    		sql1 = "SELECT * FROM Transaction_Records WHERE " + field + " = " + "'"+sort+"' AND check_in =" + "'"+checkin+"'";
		        pst = conn.prepareStatement(sql1);
		        ResultSet rs = pst.executeQuery();
	    		
	            while (rs.next()) {
	                String[] row = new String[rs.getMetaData().getColumnCount()];
	                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
	                    row[i-1] = rs.getString(i);
	                }
	                data.add(row);
	            }
	            rs.close();
	            pst.close();
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return data;
	    }
	 
	 private List<String[]> retrieveDataFromTable5(String checkin, String status) {
	        data = new ArrayList<>();
	        try {
	        	String[] myArray = {"transaction_id", "first_name", "last_name", "email", "contact_no", "check_in", "check_out", "room_description", "room_additionals", "balance", "amount_paid", "payment_proof", "employee_id", "status", "reservation_type"};
	        	data.add(myArray);
	    		conn = sqliteConnection.dbConnector();
	    		sql1 =  "SELECT * FROM Transaction_Records WHERE status =" + "'"+status+"'" + " AND check_in =" + "'"+checkin+"'";
		        pst = conn.prepareStatement(sql1);
		        ResultSet rs = pst.executeQuery();
	    		
	            while (rs.next()) {
	                String[] row = new String[rs.getMetaData().getColumnCount()];
	                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
	                    row[i-1] = rs.getString(i);
	                }
	                data.add(row);
	            }
	            rs.close();
	            pst.close();
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return data;
	    }

	 private List<String[]> retrieveDataFromTable6(String checkin, String status, String field, String sort) {
	        data = new ArrayList<>();
	        try {
	        	String[] myArray = {"transaction_id", "first_name", "last_name", "email", "contact_no", "check_in", "check_out", "room_description", "room_additionals", "balance", "amount_paid", "payment_proof", "employee_id", "status", "reservation_type"};
	        	data.add(myArray);
	    		conn = sqliteConnection.dbConnector();
	    		sql1 =  "SELECT * FROM Transaction_Records WHERE " + field + " = " + "'"+sort+"' AND check_in =" + "'"+checkin+"' And status=" + "'"+status+"'";
		        pst = conn.prepareStatement(sql1);
		        ResultSet rs = pst.executeQuery();
	    		
	            while (rs.next()) {
	                String[] row = new String[rs.getMetaData().getColumnCount()];
	                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
	                    row[i-1] = rs.getString(i);
	                }
	                data.add(row);
	            }
	            rs.close();
	            pst.close();
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return data;
	    }
	 
	 private List<String[]> retrieveDataFromTable7(String reservation) {
	        data = new ArrayList<>();
	        try {
	        	String[] myArray = {"transaction_id", "first_name", "last_name", "email", "contact_no", "check_in", "check_out", "room_description", "room_additionals", "balance", "amount_paid", "payment_proof", "employee_id", "status", "reservation_type"};
	        	data.add(myArray);
	    		conn = sqliteConnection.dbConnector();
	    		sql1 =  "SELECT * FROM Transaction_Records WHERE reservation_type=" + "'"+reservation+"'";
	    		pst = conn.prepareStatement(sql1);
		        ResultSet rs = pst.executeQuery();
	    		
	            while (rs.next()) {
	                String[] row = new String[rs.getMetaData().getColumnCount()];
	                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
	                    row[i-1] = rs.getString(i);
	                }
	                data.add(row);
	            }
	            rs.close();
	            pst.close();
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return data;
	    }
	 
	 private List<String[]> retrieveDataFromTable8(String reservation, String field, String sort) {
	        data = new ArrayList<>();
	        try {
	        	String[] myArray = {"transaction_id", "first_name", "last_name", "email", "contact_no", "check_in", "check_out", "room_description", "room_additionals", "balance", "amount_paid", "payment_proof", "employee_id", "status", "reservation_type"};
	        	data.add(myArray);
	    		conn = sqliteConnection.dbConnector();
	    		sql1 =  "SELECT * FROM Transaction_Records WHERE " + field + " = " + "'"+sort+"' AND reservation_type =" + "'"+reservation+"'";
		        pst = conn.prepareStatement(sql1);
		        ResultSet rs = pst.executeQuery();
	    		
	            while (rs.next()) {
	                String[] row = new String[rs.getMetaData().getColumnCount()];
	                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
	                    row[i-1] = rs.getString(i);
	                }
	                data.add(row);
	            }
	            rs.close();
	            pst.close();
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return data;
	    }
	 
	 private List<String[]> retrieveDataFromTable9(String reservation, String status) {
	        data = new ArrayList<>();
	        try {
	        	String[] myArray = {"transaction_id", "first_name", "last_name", "email", "contact_no", "check_in", "check_out", "room_description", "room_additionals", "balance", "amount_paid", "payment_proof", "employee_id", "status", "reservation_type"};
	        	data.add(myArray);
	    		conn = sqliteConnection.dbConnector();
	    		sql1 =  "SELECT * FROM Transaction_Records WHERE reservation_type =" + "'"+reservation+"' And status=" + "'"+status+"'";
		        pst = conn.prepareStatement(sql1);
		        ResultSet rs = pst.executeQuery();
	    		
	            while (rs.next()) {
	                String[] row = new String[rs.getMetaData().getColumnCount()];
	                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
	                    row[i-1] = rs.getString(i);
	                }
	                data.add(row);
	            }
	            rs.close();
	            pst.close();
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return data;
	    }
	 
	 private List<String[]> retrieveDataFromTable10(String reservation, String status, String field, String sort) {
	        data = new ArrayList<>();
	        try {
	        	String[] myArray = {"transaction_id", "first_name", "last_name", "email", "contact_no", "check_in", "check_out", "room_description", "room_additionals", "balance", "amount_paid", "payment_proof", "employee_id", "status", "reservation_type"};
	        	data.add(myArray);
	    		conn = sqliteConnection.dbConnector();
	    		sql1 =  "SELECT * FROM Transaction_Records WHERE " + field + " = " + "'"+sort+"' AND reservation_type ="+"'"+reservation+"' And status=" + "'"+status+"'";
		        pst = conn.prepareStatement(sql1);
		        ResultSet rs = pst.executeQuery();
	    		
	            while (rs.next()) {
	                String[] row = new String[rs.getMetaData().getColumnCount()];
	                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
	                    row[i-1] = rs.getString(i);
	                }
	                data.add(row);
	            }
	            rs.close();
	            pst.close();
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return data;
	    }
	 
	 private List<String[]> retrieveDataFromTable11(String reservation, String checkin) {
	        data = new ArrayList<>();
	        try {
	        	String[] myArray = {"transaction_id", "first_name", "last_name", "email", "contact_no", "check_in", "check_out", "room_description", "room_additionals", "balance", "amount_paid", "payment_proof", "employee_id", "status", "reservation_type"};
	        	data.add(myArray);
	    		conn = sqliteConnection.dbConnector();
	    		sql1 =  "SELECT * FROM Transaction_Records WHERE  check_in =" + "'"+checkin+"' And reservation_type=" + "'"+reservation+"'";
		        pst = conn.prepareStatement(sql1);
		        ResultSet rs = pst.executeQuery();
	    		
	            while (rs.next()) {
	                String[] row = new String[rs.getMetaData().getColumnCount()];
	                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
	                    row[i-1] = rs.getString(i);
	                }
	                data.add(row);
	            }
	            rs.close();
	            pst.close();
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return data;
	    }
	 
	 private List<String[]> retrieveDataFromTable12(String reservation, String checkin, String field, String sort) {
	        data = new ArrayList<>();
	        try {
	        	String[] myArray = {"transaction_id", "first_name", "last_name", "email", "contact_no", "check_in", "check_out", "room_description", "room_additionals", "balance", "amount_paid", "payment_proof", "employee_id", "status", "reservation_type"};
	        	data.add(myArray);
	    		conn = sqliteConnection.dbConnector();
	    		sql1 =  "SELECT * FROM Transaction_Records WHERE " + field + " = " + "'"+sort+"' AND check_in =" + "'"+checkin+"' And reservation_type=" + "'"+reservation+"'";
		        pst = conn.prepareStatement(sql1);
		        ResultSet rs = pst.executeQuery();
	    		
	            while (rs.next()) {
	                String[] row = new String[rs.getMetaData().getColumnCount()];
	                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
	                    row[i-1] = rs.getString(i);
	                }
	                data.add(row);
	            }
	            rs.close();
	            pst.close();
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return data;
	    }
	 private List<String[]> retrieveDataFromTable13(String reservation, String checkin, String status) {
	        data = new ArrayList<>();
	        try {
	        	String[] myArray = {"transaction_id", "first_name", "last_name", "email", "contact_no", "check_in", "check_out", "room_description", "room_additionals", "balance", "amount_paid", "payment_proof", "employee_id", "status", "reservation_type"};
	        	data.add(myArray);
	    		conn = sqliteConnection.dbConnector();
	    		sql1 =  "SELECT * FROM Transaction_Records WHERE check_in =" + "'"+checkin+"' AND status=" + "'"+status+"'  AND reservation_type=" + "'"+reservation+"'";
		        pst = conn.prepareStatement(sql1);
		        ResultSet rs = pst.executeQuery();
	    		
	            while (rs.next()) {
	                String[] row = new String[rs.getMetaData().getColumnCount()];
	                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
	                    row[i-1] = rs.getString(i);
	                }
	                data.add(row);
	            }
	            rs.close();
	            pst.close();
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return data;
	    }
	 
	 private List<String[]> retrieveDataFromTable14(String reservation, String checkin, String status, String field, String sort) {
	        data = new ArrayList<>();
	        try {
	        	String[] myArray = {"transaction_id", "first_name", "last_name", "email", "contact_no", "check_in", "check_out", "room_description", "room_additionals", "balance", "amount_paid", "payment_proof", "employee_id", "status", "reservation_type"};
	        	data.add(myArray);
	    		conn = sqliteConnection.dbConnector();
	    		sql1 =  "SELECT * FROM Transaction_Records WHERE " + field + " = " + "'"+sort+"' AND check_in =" + "'"+checkin+"' And status=" + "'"+status+"'  And reservation_type=" + "'"+reservation+"'";
		        pst = conn.prepareStatement(sql1);
		        ResultSet rs = pst.executeQuery();
	    		
	            while (rs.next()) {
	                String[] row = new String[rs.getMetaData().getColumnCount()];
	                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
	                    row[i-1] = rs.getString(i);
	                }
	                data.add(row);
	            }
	            rs.close();
	            pst.close();
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return data;
	    }
	 
	 private List<String[]> retrieveDataFromTable() {
	        data = new ArrayList<>();
	        try {
	        	String[] myArray = {"transaction_id", "first_name", "last_name", "email", "contact_no", "check_in", "check_out", "room_description", "room_additionals", "balance", "amount_paid", "payment_proof", "employee_id", "status", "reservation_type"};
	        	data.add(myArray);
	    		conn = sqliteConnection.dbConnector();
	    		sql1 =  "SELECT * FROM Transaction_Records";
		        pst = conn.prepareStatement(sql1);
		        ResultSet rs = pst.executeQuery();
	    		
	            while (rs.next()) {
	                String[] row = new String[rs.getMetaData().getColumnCount()];
	                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
	                    row[i-1] = rs.getString(i);
	                }
	                data.add(row);
	            }
	            rs.close();
	            pst.close();
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return data;
	    }
	 
	    private void savePDF(List<String[]> data) {
	        Document document = new Document(PageSize.A4.rotate());
	        try {
	            FileDialog dialog = new FileDialog((Frame)null, "Save PDF", FileDialog.SAVE);
	            dialog.setDirectory("C:\\Villa Rose Resort\\Centralized Folder");
	            dialog.setFile("*.pdf");
	            dialog.setVisible(true);
	            String fileName = dialog.getFile();
	            if (fileName != null) {
	                fileName = fileName.endsWith(".pdf") ? fileName : fileName + ".pdf";
	                PdfWriter.getInstance(document, new FileOutputStream(dialog.getDirectory() + fileName));
	                document.setMargins(0f, 0f, 50f, 20f);
	                document.open();
	                Font font = new Font("TimesRoman", Font.PLAIN, 10);

	                PdfPTable table = new PdfPTable(data.get(0).length);
	                float[] columnWidths = new float[] {50f, 50f, 50f, 50f, 50f, 50f, 50f, 50f, 50f, 50f, 50f, 50f, 50f, 50f, 50f}; // Example widths for 3 columns
	                table.setWidths(columnWidths);
	                for (String[] row : data) {
	                    for (String cell : row) {
	                        PdfPCell pdfCell = new PdfPCell(new Paragraph(cell));
	                        //pdfCell.setWidth(50f);
	                        table.addCell(pdfCell);
	                    }
	                }
	                document.add(table);
	                document.close();
	                data.clear();
	                JOptionPane.showMessageDialog(null, "Exported Successfully.");
	            }
	        } catch (IOException | DocumentException e) {
	            e.printStackTrace();
	        }

	    }
	    
	    private void savePDF2(String receipt, String sign) {
	        Document document = new Document(PageSize.A4);
	        try {
	            FileDialog dialog = new FileDialog((Frame)null, "Save PDF", FileDialog.SAVE);
	            dialog.setDirectory("C:\\Villa Rose Resort\\Centralized Folder");
	            dialog.setFile("*.pdf");
	            dialog.setVisible(true);
	            String fileName = dialog.getFile();
	            if (fileName != null) {
	                fileName = fileName.endsWith(".pdf") ? fileName : fileName + ".pdf";
	                PdfWriter.getInstance(document, new FileOutputStream(dialog.getDirectory() + fileName));
	                Paragraph paragraph = new Paragraph(receipt);
	                paragraph.setAlignment(Element.ALIGN_CENTER);
	                document.open();
	                document.add(paragraph);
	                document.add(new Paragraph(sign));
	                document.close();
	                JOptionPane.showMessageDialog(null, "Exported Successfully.");
	            }
	        } catch (IOException | DocumentException e) {
	            e.printStackTrace();
	        }

	    }
	    
	    private List<String[]> retrieveDataFromTable3() {
	        data = new ArrayList<>();
	        try {
	        	String[] myArray = {"transaction_id", "first_name", "last_name", "email", "contact_no", "check_in", "check_out", "room_description", "room_additionals", "balance", "amount_paid", "payment_proof", "employee_id", "status", "reservation_type"};
	        	data.add(myArray);
	    		conn = sqliteConnection.dbConnector();
	
	    		sql1 = "SELECT * FROM Transaction_Records";
		        pst = conn.prepareStatement(sql1);
		        ResultSet rs = pst.executeQuery();
	    		
	            while (rs.next()) {
	                String[] row = new String[rs.getMetaData().getColumnCount()];
	                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
	                    row[i-1] = rs.getString(i);
	                }
	                data.add(row);
	            }
	            rs.close();
	            pst.close();
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return data;
	    }
}