import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import net.proteanit.sql.DbUtils;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HomePage extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//HomePage frame = new HomePage();
					//frame.setLocationRelativeTo(null);
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
	private JTextField txtFname;
	private JTextField txtLname;
	private JTextField txtEmail;
	private JTextField txtContactNo;
	private JTextField txtRoomNo;
	private JTextField txtBalance;
	private JTextField txtAmountPaid;
	private JTextField txtField;
	private JTable table_1;
	String transaction_id;
	private JTextField path;
	private ImageIcon format = null;
	private String filename=null;
	private int s = 0;
	byte[] person_image=null;

	
	public HomePage(int emp_id) {
		setTitle("Villa Rose System");
		conn = sqliteConnection.dbConnector();
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
		
		JDateChooser checkin = new JDateChooser();
		checkin.setBounds(287, 154, 271, 20);
		contentPane.add(checkin);
		
		JDateChooser checkout = new JDateChooser();
		checkout.setBounds(287, 184, 271, 20);
		contentPane.add(checkout);
		
		JLabel icon = new JLabel("");
		icon.setBounds(60, 45, 39, 46);
		panel.add(icon);
		icon.setIcon(new ImageIcon(img1));
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(225, 214, 166, 98);
		contentPane.add(desktopPane);
		
		JButton btn_transaction = new JButton("Transactions");
		btn_transaction.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		btn_transaction.setBackground(new Color(225, 167, 48));
		btn_transaction.setBorder(null);
		btn_transaction.setBounds(18, 140, 125, 30);
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
		btn_managecontent.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		btn_managecontent.setBorder(null);
		btn_managecontent.setBackground(new Color(225, 167, 48));
		btn_managecontent.setBounds(18, 186, 125, 30);
		panel.add(btn_managecontent);
		
		JButton btn_logout = new JButton("Logout");
		btn_logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Successfully Logged Out");
				dispose();
				Login lpage = new Login();
				lpage.setLocationRelativeTo(null);
				lpage.show();
			}
		});
		btn_logout.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		btn_logout.setBorder(null);
		btn_logout.setBackground(new Color(225, 167, 48));
		btn_logout.setBounds(18, 428, 125, 30);
		panel.add(btn_logout);
		
		JLabel lblNewLabel = new JLabel("First Name:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setBounds(195, 38, 84, 14);
		lblNewLabel.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		contentPane.add(lblNewLabel);
		
		txtFname = new JTextField();
		txtFname.setBounds(287, 35, 271, 20);
		txtFname.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		contentPane.add(txtFname);
		txtFname.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Last Name:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1.setBounds(195, 66, 84, 14);
		lblNewLabel_1.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		contentPane.add(lblNewLabel_1);
		
		txtLname = new JTextField();
		txtLname.setColumns(10);
		txtLname.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		txtLname.setBounds(287, 63, 271, 20);
		contentPane.add(txtLname);
		
		JLabel lblNewLabel_2 = new JLabel("Email:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2.setBounds(195, 97, 84, 14);
		lblNewLabel_2.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		contentPane.add(lblNewLabel_2);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(287, 94, 271, 20);
		txtEmail.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		contentPane.add(txtEmail);
		
		JLabel lblNewLabel_3 = new JLabel("Contact No.:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_3.setBounds(195, 129, 84, 14);
		lblNewLabel_3.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		contentPane.add(lblNewLabel_3);
		
		txtContactNo = new JTextField();
		txtContactNo.setColumns(10);
		txtContactNo.setBounds(287, 126, 271, 20);
		txtContactNo.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		contentPane.add(txtContactNo);
		
		JLabel lblNewLabel_4 = new JLabel("Check-In:");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_4.setBounds(195, 157, 84, 14);
		lblNewLabel_4.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Check-Out:");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_5.setBounds(195, 187, 84, 14);
		lblNewLabel_5.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		contentPane.add(lblNewLabel_5);
		
		JLabel lblRoomNo = new JLabel("Room No.:");
		lblRoomNo.setHorizontalAlignment(SwingConstants.TRAILING);
		lblRoomNo.setBounds(585, 38, 66, 14);
		lblRoomNo.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		contentPane.add(lblRoomNo);
		
		txtRoomNo = new JTextField();
		txtRoomNo.setColumns(10);
		txtRoomNo.setBounds(663, 35, 271, 20);
		txtRoomNo.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		contentPane.add(txtRoomNo);
		
		JLabel lblBalance = new JLabel("Balance:");
		lblBalance.setHorizontalAlignment(SwingConstants.TRAILING);
		lblBalance.setBounds(585, 66, 66, 14);
		lblBalance.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		contentPane.add(lblBalance);
		
		txtBalance = new JTextField();
		txtBalance.setColumns(10);
		txtBalance.setBounds(663, 63, 271, 20);
		txtBalance.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		contentPane.add(txtBalance);
		
		JLabel lblAmountPaid = new JLabel("Amount Paid:");
		lblAmountPaid.setHorizontalAlignment(SwingConstants.TRAILING);
		lblAmountPaid.setBounds(585, 97, 66, 14);
		lblAmountPaid.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		contentPane.add(lblAmountPaid);
		
		txtAmountPaid = new JTextField();
		txtAmountPaid.setColumns(10);
		txtAmountPaid.setBounds(663, 94, 271, 20);
		txtAmountPaid.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		contentPane.add(txtAmountPaid);
		
		JLabel image = new JLabel("");
		image.setBounds(0, 0, 166, 98);
		desktopPane.add(image);
		
		
		JButton btnAdd = new JButton("Add Reservation");
		btnAdd.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		btnAdd.setBorder(null);
		btnAdd.setBackground(new Color(225, 167, 48));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conn = sqliteConnection.dbConnector();
					String fname = txtFname.getText();
					String lname = txtLname.getText();
					String email = txtEmail.getText();
					String contactNo = txtContactNo.getText();
					int contact = Integer.parseInt(contactNo);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String date1 = sdf.format(checkin.getDate());
					String date2 = sdf.format(checkout.getDate());
					String roomNo = txtRoomNo.getText();
					String balance = txtBalance.getText();
					int bal = Integer.parseInt(balance);
					String amount = txtAmountPaid.getText();
					int amnt = Integer.parseInt(amount);
					
					pst = conn.prepareStatement("INSERT INTO Testing (first_name, last_name, email, contact_no, check_in, check_out, room_description, balance, amount_paid, payment_proof) VALUES(?,?,?,?,?,?,?,?,?,?)");
					pst.setString(1,fname);
					pst.setString(2,lname);
					pst.setString(3,email);
					pst.setInt(4,contact);
					pst.setString(5,date1);
					pst.setString(6,date2);
					pst.setString(7,roomNo);
					pst.setInt(8,bal);
					pst.setInt(9,amnt);
					pst.setBytes(10, person_image);
					
					int k = pst.executeUpdate();
					
					if (k==1) {
						JOptionPane.showMessageDialog(null, "Transaction Added Successfully!");
						txtFname.setText("");
						txtLname.setText("");
						txtEmail.setText("");
						txtContactNo.setText("");
						txtRoomNo.setText("");
						txtBalance.setText("");
						txtAmountPaid.setText("");
						checkin.setDate(null);
						checkout.setDate(null);
						image.setIcon(null);
						path.setText("null");
						rs.close();
						pst.close();
						conn.close();
						updateTable();
					}else {
						JOptionPane.showMessageDialog(null, "Transaction Failed to Save");
					}

					rs.close();
					pst.close();
					conn.close();
				} catch (Exception e1) {
					System.out.print(e1);
					
				} finally {
				
				}
				
			}
		});
		btnAdd.setBounds(687, 122, 215, 23);
		contentPane.add(btnAdd);
		String[] colum = {"Select Field","transaction_id","first_name","last_name","email","contact_no","check_in","check_out","room_description","balance","amount_paid"};
		JComboBox comboBox = new JComboBox(colum);
		comboBox.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		comboBox.setBounds(663, 153, 271, 22);
		contentPane.add(comboBox);
		
		txtField = new JTextField();
		txtField.setBounds(663, 184, 271, 20);
		txtField.setText("Search for?");
		txtField.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		txtField.setForeground(new Color(159, 159, 159));
		contentPane.add(txtField);
		txtField.setColumns(10);
		txtField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtField.getText().equals("Search for?")) {
					txtField.setText("");
					txtField.setForeground(new Color(0, 0, 0));
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtField.getText().equals("")) {
					txtField.setText("Search for?");
					txtField.setForeground(new Color(159, 159, 159));
			}
			
		}
		});
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		btnSearch.setBorder(null);
		btnSearch.setBackground(new Color(225, 167, 48));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conn = sqliteConnection.dbConnector();
				try {
					System.out.println(emp_id);
					String field = comboBox.getSelectedItem().toString();
					String txtfield = txtField.getText();
					pst = conn.prepareStatement("SELECT * FROM Testing WHERE "+field+"='"+txtfield+"'");
					rs = pst.executeQuery();
					
					if(rs.next()==true) {
						transaction_id = rs.getString(1);
						txtFname.setText(rs.getString(2));
						txtLname.setText(rs.getString(3));
						txtEmail.setText(rs.getString(4));
						txtContactNo.setText(rs.getString(5));
						String checkin1 = rs.getString(6);
						java.util.Date checkin2 = new SimpleDateFormat("yyyy-MM-dd").parse(checkin1);
						String checkout1 = rs.getString(7);
						java.util.Date checkout2 = new SimpleDateFormat("yyyy-MM-dd").parse(checkout1);
						checkin.setDate(checkin2);
						checkout.setDate(checkout2);
						txtRoomNo.setText(rs.getString(8));
						txtBalance.setText(rs.getString(9));
						txtAmountPaid.setText(rs.getString(10));
						byte[]imagedata = rs.getBytes("payment_proof");
						format = new ImageIcon(imagedata);
						Image img = format.getImage();
						Image imgScale = img.getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH);
						ImageIcon scaledIcon = new ImageIcon(imgScale);
						image.setIcon(scaledIcon);


						
						
					}else {
						JOptionPane.showMessageDialog(null, "No Record Found!");
					}
					pst.close();
					rs.close();
				}
				
				catch (NullPointerException e2) {
					image.setIcon(null);
					
				}
				catch (Exception e1) {
					
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
		image.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "","Payment Proof",JOptionPane.INFORMATION_MESSAGE,format);
			}
		});
		btnSearch.setBounds(687, 216, 215, 23);
		contentPane.add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(170, 323, 830, 203);
		contentPane.add(scrollPane);
		
		table_1 = new JTable();
		table_1.setDefaultEditor(Object.class, null);
		table_1.setFont(new Font("Calibri Light", Font.PLAIN, 11));
		scrollPane.setViewportView(table_1);
		
		JLabel lblNewLabel_6 = new JLabel("Select Field:");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_6.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(585, 157, 66, 14);
		contentPane.add(lblNewLabel_6);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		btnUpdate.setBorder(null);
		btnUpdate.setBackground(new Color(225, 167, 48));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) throws NumberFormatException {
				try {
					conn = sqliteConnection.dbConnector();
					String fname = txtFname.getText();
					String lname = txtLname.getText();
					String email = txtEmail.getText();
					String contactNo = txtContactNo.getText();
					int contact = Integer.parseInt(contactNo);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String date1 = sdf.format(checkin.getDate());
					String date2 = sdf.format(checkout.getDate());
					String roomNo = txtRoomNo.getText();
					String balance = txtBalance.getText();
					int bal = Integer.parseInt(balance);
					String amount = txtAmountPaid.getText();
					int amnt = Integer.parseInt(amount);
					
					pst = conn.prepareStatement("UPDATE Testing SET first_name=?,last_name=?,email=?,contact_no=?,check_in=?,check_out=?,room_description=?,balance=?,amount_paid=?,payment_proof=? WHERE transaction_id=?");
					pst.setString(1,fname);
					pst.setString(2,lname);
					pst.setString(3,email);
					pst.setInt(4,contact);
					pst.setString(5,date1);
					pst.setString(6,date2);
					pst.setString(7,roomNo);
					pst.setInt(8,bal);
					pst.setInt(9,amnt);
					pst.setBytes(10, person_image);
					pst.setString(11, transaction_id);
					
					int k = pst.executeUpdate();
					if(k==1) {
						JOptionPane.showMessageDialog(null, "Record has been sucessfully updated!");
						txtFname.setText("");
						txtLname.setText("");
						txtEmail.setText("");
						txtContactNo.setText("");
						txtRoomNo.setText("");
						txtBalance.setText("");
						txtAmountPaid.setText("");
						checkin.setDate(null);
						checkout.setDate(null);
						image.setIcon(null);
						path.setText("");
						updateTable();
					}
					rs.close();
					pst.close();
					conn.close();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "An Error was encountered while Updating");
					e1.printStackTrace();
				}
				
			}
		});
		btnUpdate.setBounds(687, 250, 215, 25);
		contentPane.add(btnUpdate);
		
		JButton attach_img = new JButton("Attach");
		attach_img.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				File f = chooser.getSelectedFile();
				filename = f.getAbsolutePath();
				path.setText(filename);
				
				try {
					File image = new File(filename);
					FileInputStream fis = new FileInputStream(image);
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					byte[] buf = new byte[1024];
					for(int readNum; (readNum=fis.read(buf)) !=-1;) {
						bos.write(buf,0,readNum);
					}
					person_image = bos.toByteArray();

				}
				catch(Exception e1) {
					System.out.println(e1);
				}
			}
		});
		attach_img.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		attach_img.setBorder(null);
		attach_img.setBackground(new Color(225, 167, 48));
		attach_img.setBounds(401, 215, 160, 23);
		contentPane.add(attach_img);
		
		path = new JTextField();
		path.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		path.setColumns(10);
		path.setBounds(401, 250, 160, 20);
		contentPane.add(path);
		updateTable();
		

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
}
