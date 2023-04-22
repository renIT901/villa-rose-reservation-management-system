import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

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

import com.toedter.calendar.JDateChooser;

import net.proteanit.sql.DbUtils;
import javax.swing.JTextArea;

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
	int amount_paid;
	int balance;
	/**
	 * @wbp.nonvisual location=452,769
	 */
	private final JTextArea txtReceipt = new JTextArea();
	/**
	 * Create the frame.
	 */
	public transaction_table(int emp_id) {
		setResizable(false);
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
		scrollPane.setBounds(210, 80, 1046, 482);
		contentPane.add(scrollPane);
		
		table_1 = new JTable();
		table_1.setDefaultEditor(Object.class, null);
		table_1.setFont(new Font("Calibri Light", Font.PLAIN, 11));
		scrollPane.setViewportView(table_1);
		updateTable();
		
		comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		comboBox_1.setBounds(232, 572, 311, 24);
		contentPane.add(comboBox_1);
		
		JButton btnprint = new JButton("Print Receipt");
		btnprint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					txtReceipt.print();
					txtReceipt.setText("");
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally {
					
				}
			}
		});
		btnprint.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnprint.setBorder(null);
		btnprint.setBackground(new Color(225, 167, 48));
		btnprint.setBounds(232, 604, 147, 40);
		contentPane.add(btnprint);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 200, 683);
		contentPane.add(panel);
		
		JLabel icon = new JLabel("");
		icon.setIcon(new ImageIcon(transaction_table.class.getResource("/img/villarose.png")));
		icon.setBounds(25, 26, 144, 151);
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
		
		JLabel lblNewLabel = new JLabel("Transaction Table");
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 30));
		lblNewLabel.setBounds(232, 10, 319, 60);
		contentPane.add(lblNewLabel);
		loadUserName();
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conn = sqliteConnection.dbConnector();
				try {
				String item = (String)comboBox_1.getSelectedItem();
				String substrings[] = item.split(",");
				trans_id = substrings[0];
				pst = conn.prepareStatement("SELECT * FROM Testing WHERE transaction_id='"+trans_id+"'");
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
					
					
					int new_balance = balance-amount_paid;
					String[] arr2 = additionals.split(",");
					String[] arr = room_type.split(",");
					if(arr.length == 1) {
						if(arr2.length == 1) {
					txtReceipt.append("\t\t       Villa Rose Resorts \n" +
							"\t         Dinadiawan, 3P6M+QWW, Dipaculao, Aurora \n"+
							"\t\t Contact No. 0926 610 7400\n\n"+
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
							"\t Balance: \t\t\t" + new_balance + "\n\n" +
							"\n=======================================================================\n");
						} else if (arr2.length == 2) {
			txtReceipt.append("\t\t       Villa Rose Resorts \n" +
					"\t         Dinadiawan, 3P6M+QWW, Dipaculao, Aurora \n"+
					"\t\t Contact No. 0926 610 7400\n\n"+
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
					"\t Balance: \t\t\t" + new_balance + "\n\n" +
					"\n=======================================================================\n");
				} else if(arr2.length == 3) {
			txtReceipt.append("\t\t       Villa Rose Resorts \n" +
					"\t         Dinadiawan, 3P6M+QWW, Dipaculao, Aurora \n"+
					"\t\t Contact No. 0926 610 7400\n\n"+
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
					"\t Balance: \t\t\t" + new_balance + "\n\n" +
					"\n=======================================================================\n");
				}
					} else if (arr.length == 2) {
						if(arr2.length == 1) {
						txtReceipt.append("\t\t       Villa Rose Resorts \n" +
								"\t         Dinadiawan, 3P6M+QWW, Dipaculao, Aurora \n"+
								"\t\t Contact No. 0926 610 7400\n\n"+
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
								"\t Balance: \t\t\t" + new_balance + "\n\n" +
								"\n=======================================================================\n");
						} else if (arr2.length == 2) {
							txtReceipt.append("\t\t       Villa Rose Resorts \n" +
									"\t         Dinadiawan, 3P6M+QWW, Dipaculao, Aurora \n"+
									"\t\t Contact No. 0926 610 7400\n\n"+
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
									"\t Balance: \t\t\t" + new_balance + "\n\n" +
									"\n=======================================================================\n");
							} else if (arr2.length == 3) {
								txtReceipt.append("\t\t       Villa Rose Resorts \n" +
										"\t         Dinadiawan, 3P6M+QWW, Dipaculao, Aurora \n"+
										"\t\t Contact No. 0926 610 7400\n\n"+
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
										"\t Balance: \t\t\t" + new_balance + "\n\n" +
										"\n=======================================================================\n");
								} 
						} else if (arr.length == 3) {
							if(arr2.length == 1) {
								txtReceipt.append("\t\t       Villa Rose Resorts \n" +
										"\t         Dinadiawan, 3P6M+QWW, Dipaculao, Aurora \n"+
										"\t\t Contact No. 0926 610 7400\n\n"+
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
										"\t Balance: \t\t\t" + new_balance + "\n\n" +
										"\n=======================================================================\n");
								} else if (arr2.length == 2) {
									txtReceipt.append("\t\t       Villa Rose Resorts \n" +
											"\t         Dinadiawan, 3P6M+QWW, Dipaculao, Aurora \n"+
											"\t\t Contact No. 0926 610 7400\n\n"+
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
											"\t Balance: \t\t\t" + new_balance + "\n\n" +
											"\n=======================================================================\n");
									} else if (arr2.length == 3) {
										txtReceipt.append("\t\t       Villa Rose Resorts \n" +
												"\t         Dinadiawan, 3P6M+QWW, Dipaculao, Aurora \n"+
												"\t\t Contact No. 0926 610 7400\n\n"+
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
												"\t Balance: \t\t\t" + new_balance + "\n\n" +
												"\n=======================================================================\n");
										} 
							} else if (arr.length == 4) {
								if(arr2.length == 1) {
									txtReceipt.append("\t\t       Villa Rose Resorts \n" +
											"\t         Dinadiawan, 3P6M+QWW, Dipaculao, Aurora \n"+
											"\t\t Contact No. 0926 610 7400\n\n"+
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
											"\t Balance: \t\t\t" + new_balance + "\n\n" +
											"\n=======================================================================\n");
									} else if (arr2.length == 2) {
										txtReceipt.append("\t\t       Villa Rose Resorts \n" +
												"\t         Dinadiawan, 3P6M+QWW, Dipaculao, Aurora \n"+
												"\t\t Contact No. 0926 610 7400\n\n"+
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
												"\t Balance: \t\t\t" + new_balance + "\n\n" +
												"\n=======================================================================\n");
										} else if (arr2.length == 3) {
											txtReceipt.append("\t\t       Villa Rose Resorts \n" +
													"\t         Dinadiawan, 3P6M+QWW, Dipaculao, Aurora \n"+
													"\t\t Contact No. 0926 610 7400\n\n"+
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
													"\t Balance: \t\t\t" + new_balance + "\n\n" +
													"\n=======================================================================\n");
											} 
								} else if (arr.length == 5) {
									if(arr2.length == 1) {
										txtReceipt.append("\t\t       Villa Rose Resorts \n" +
												"\t         Dinadiawan, 3P6M+QWW, Dipaculao, Aurora \n"+
												"\t\t Contact No. 0926 610 7400\n\n"+
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
												"\t Balance: \t\t\t" + new_balance + "\n\n" +
												"\n=======================================================================\n");
										} else if (arr2.length == 2) {
											txtReceipt.append("\t\t       Villa Rose Resorts \n" +
													"\t         Dinadiawan, 3P6M+QWW, Dipaculao, Aurora \n"+
													"\t\t Contact No. 0926 610 7400\n\n"+
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
													"\n=======================================================================\n" +
													"\t Amount Paid: \t\t\t"+ amount_paid + "\n\n" +
													"\t Balance: \t\t\t" + new_balance + "\n\n" +
													"\n=======================================================================\n");
											} else if (arr2.length == 3) {
												txtReceipt.append("\t\t       Villa Rose Resorts \n" +
														"\t         Dinadiawan, 3P6M+QWW, Dipaculao, Aurora \n"+
														"\t\t Contact No. 0926 610 7400\n\n"+
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
														"\n=======================================================================\n" +
														"\t Amount Paid: \t\t\t"+ amount_paid + "\n\n" +
														"\t Balance: \t\t\t" + new_balance + "\n\n" +
														"\n=======================================================================\n");
												} 
									} else {
										System.out.print("asd");
									}
				}
				}
				catch(Exception e1) {
					System.out.println(e1);
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
		String sql = "SELECT * FROM Testing";
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			try {
				rs.close();
				pst.close();
			} catch (Exception e) {
				
			}
		}
	}
	
	private void getID() {
		conn = sqliteConnection.dbConnector();
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
		String sql = "SELECT * FROM Testing where transaction_id="+trans_id;
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
}
