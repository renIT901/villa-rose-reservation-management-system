import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import net.proteanit.sql.DbUtils;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
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
	private JTextField txtBalance;
	private JTextField txtAmountPaid;
	private JTextField txtField;
	String transaction_id;
	private JTextField path;
	private ImageIcon format = null;
	private String filename=null;
	private int s = 0;
	byte[] person_image=null;
	String accomodation_type = "";
	String additionals = "";
	String at;
	String adts;
	String status;
	String st;
	String trans_type;
	private JCheckBox chkfamily;
	private JCheckBox chkkubo;
	private JCheckBox chkteepee;
	private JCheckBox chkcabana;
	private JCheckBox chkpool;
	private JCheckBox chkgasul;
	private JCheckBox chkcookware;
	private JCheckBox chkmatress;
	private JCheckBox chkextraperson;
	

	
	public HomePage(int emp_id) {
		 String message = "Welcome to Villa Rose Reservation Management System!\n\n"
	                + "By using this application, you agree to the following terms and conditions:\n\n"
	                + "1. Booking reservation is confirmed once 50% deposit is paid.\n"
	                + "2. Full payment shall be settled upon check-in.\n"
	                + "3. Early room check-in is considered once room is available.\n\n"
	                + "4. Strictly NO REFUND for any deposit made, but we will allow for booking rescheduling.\n\n"
	                + "5. A late check-out after 12 noon will be charged 500PHP per hour.\n\n"
	                + "6. Toiletries and dishwashing liquid are not included. These are available for sale ℅ the Caretaker. \n\n"
	                + "7. Griller (Ihawan) available for guest use.\n\n"
	                + "8. Charcoal (Uling) available for sale ℅ the Caretaker.\n\n"
	                + "9. Things such as tent, karaoke, beach umbrella brought outside the premises will be charged accordingly by The Management.\n\n"
	                + "10. Resort furnitures is strictly not allowed to be moved elsewhere.\n\n"
	                + "Click 'Agree' to accept these terms and start using the application.";
		//TermsandCondition(message);
		setResizable(false);
		createMainDir();
		createRepDir();
		createBackUpDir();
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
		
		chkfamily = new JCheckBox("Family Room");
		chkfamily.setFont(new Font("SansSerif", Font.PLAIN, 16));
		chkfamily.setBackground(new Color(250, 245, 232));
		chkfamily.setBounds(901, 41, 117, 23);
		contentPane.add(chkfamily);
		
		chkkubo = new JCheckBox("Kubo Room");
		chkkubo.setFont(new Font("SansSerif", Font.PLAIN, 16));
		chkkubo.setBackground(new Color(250, 245, 232));
		chkkubo.setBounds(901, 66, 117, 23);
		contentPane.add(chkkubo);
		
		chkteepee = new JCheckBox("Teepee Hut");
		chkteepee.setFont(new Font("SansSerif", Font.PLAIN, 16));
		chkteepee.setBackground(new Color(250, 245, 232));
		chkteepee.setBounds(901, 91, 117, 23);
		contentPane.add(chkteepee);
		
		chkcabana = new JCheckBox("Open Cabana");
		chkcabana.setFont(new Font("SansSerif", Font.PLAIN, 16));
		chkcabana.setBackground(new Color(250, 245, 232));
		chkcabana.setBounds(901, 116, 133, 23);
		contentPane.add(chkcabana);
		
		chkpool = new JCheckBox("Tent");
		chkpool.setFont(new Font("SansSerif", Font.PLAIN, 16));
		chkpool.setBackground(new Color(250, 245, 232));
		chkpool.setBounds(901, 141, 170, 23);
		contentPane.add(chkpool);
		
		chkgasul = new JCheckBox("Gasul");
		chkgasul.setBounds(901, 179, 97, 23);
		chkgasul.setFont(new Font("SansSerif", Font.PLAIN, 16));
		chkgasul.setBackground(new Color(250, 245, 232));
		contentPane.add(chkgasul);
		
		chkcookware = new JCheckBox("Cookware Set");
		chkcookware.setBounds(901, 204, 133, 23);
		chkcookware.setFont(new Font("SansSerif", Font.PLAIN, 16));
		chkcookware.setBackground(new Color(250, 245, 232));
		contentPane.add(chkcookware);
		
		chkmatress = new JCheckBox("Extra Matress");
		chkmatress.setBounds(901, 229, 133, 23);
		chkmatress.setFont(new Font("SansSerif", Font.PLAIN, 16));
		chkmatress.setBackground(new Color(250, 245, 232));
		contentPane.add(chkmatress);
		
		chkextraperson = new JCheckBox("Extra Person");
		chkextraperson.setFont(new Font("SansSerif", Font.PLAIN, 16));
		chkextraperson.setBackground(new Color(250, 245, 232));
		chkextraperson.setBounds(901, 254, 133, 23);
		contentPane.add(chkextraperson);
		
		String[] colum2 = {"Select Status","Pending","Fully paid","Cancelled"};
		
		JComboBox comboBoxStatus = new JComboBox(colum2);
		comboBoxStatus.setFont(new Font("SansSerif", Font.PLAIN, 16));
		comboBoxStatus.setBounds(901, 295, 282, 24);
		contentPane.add(comboBoxStatus);
		
		JDateChooser checkin = new JDateChooser();
		checkin.setBounds(355, 168, 290, 20);
		checkin.setFont(new Font("SansSerif", Font.PLAIN, 16));
		contentPane.add(checkin);
		
		JDateChooser checkout = new JDateChooser();
		checkout.setBounds(355, 199, 290, 20);
		checkout.setFont(new Font("SansSerif", Font.PLAIN, 16));
		contentPane.add(checkout);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(265, 268, 410, 289);
		contentPane.add(desktopPane);
		
		String[] colum1 = {"Select Transaction Type","Appointment","Walk-in"};
		
		JComboBox comboBoxType = new JComboBox(colum1);
		comboBoxType.setFont(new Font("SansSerif", Font.PLAIN, 16));
		comboBoxType.setBounds(355, 230, 290, 24);
		contentPane.add(comboBoxType);
		
		JLabel lblNewLabel = new JLabel("First Name:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(253, 40, 84, 24);
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		contentPane.add(lblNewLabel);
		
		txtFname = new JTextField();
		txtFname.setColumns(20);
		txtFname.setBounds(355, 44, 290, 20);
		txtFname.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtFname.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (Character.isDigit(c)) {
                    evt.consume();
                }
			}
		});
		contentPane.add(txtFname);
		
		
		JLabel lblNewLabel_1 = new JLabel("Last Name:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(253, 75, 84, 24);
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		contentPane.add(lblNewLabel_1);
		
		txtLname = new JTextField();
		txtLname.setColumns(20);
		txtLname.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtLname.setBounds(355, 75, 290, 20);
		txtLname.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (Character.isDigit(c)) {
                    evt.consume();
                }
			}
		});
		contentPane.add(txtLname);
		
		JLabel lblNewLabel_2 = new JLabel("Email:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(253, 106, 84, 24);
		lblNewLabel_2.setFont(new Font("SansSerif", Font.PLAIN, 16));
		contentPane.add(lblNewLabel_2);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(30);
		txtEmail.setBounds(355, 106, 290, 20);
		txtEmail.setFont(new Font("SansSerif", Font.PLAIN, 16));
		contentPane.add(txtEmail);
		
		JLabel lblNewLabel_3 = new JLabel("Contact No.:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(240, 137, 97, 24);
		lblNewLabel_3.setFont(new Font("SansSerif", Font.PLAIN, 16));
		contentPane.add(lblNewLabel_3);
		
		txtContactNo = new JTextField();
		//txtContactNo.setColumns(11);
		txtContactNo.setBounds(355, 137, 290, 20);
		txtContactNo.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtContactNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!(Character.isDigit(c) ||
                      (c == KeyEvent.VK_BACK_SPACE) ||
                      (c == KeyEvent.VK_DELETE))) {
                    evt.consume(); // ignore input if it is not a digit or backspace or delete key
                }
            }
        });
		contentPane.add(txtContactNo);
		
		JLabel lblNewLabel_4 = new JLabel("Check-In:");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(240, 168, 97, 24);
		lblNewLabel_4.setFont(new Font("SansSerif", Font.PLAIN, 16));
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Check-Out:");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5.setBounds(240, 199, 97, 24);
		lblNewLabel_5.setFont(new Font("SansSerif", Font.PLAIN, 16));
		contentPane.add(lblNewLabel_5);
		
		JLabel lblRoomNo = new JLabel("Room:");
		lblRoomNo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRoomNo.setBounds(817, 40, 66, 24);
		lblRoomNo.setFont(new Font("SansSerif", Font.PLAIN, 16));
		contentPane.add(lblRoomNo);
		
		JLabel lblBalance = new JLabel("Balance:");
		lblBalance.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBalance.setBounds(817, 343, 66, 24);
		lblBalance.setFont(new Font("SansSerif", Font.PLAIN, 16));
		contentPane.add(lblBalance);
		
		txtBalance = new JTextField();
		txtBalance.setColumns(20);
		txtBalance.setBounds(901, 343, 282, 24);
		txtBalance.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtBalance.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!(Character.isDigit(c) ||
                      (c == KeyEvent.VK_BACK_SPACE) ||
                      (c == KeyEvent.VK_DELETE))) {
                    evt.consume(); // ignore input if it is not a digit or backspace or delete key
                }
            }
        });
		contentPane.add(txtBalance);
		
		JLabel lblAmountPaid = new JLabel("Deposit:");
		lblAmountPaid.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAmountPaid.setBounds(817, 374, 66, 24);
		lblAmountPaid.setFont(new Font("SansSerif", Font.PLAIN, 16));
		contentPane.add(lblAmountPaid);
		
		txtAmountPaid = new JTextField();
		txtAmountPaid.setColumns(20);
		txtAmountPaid.setBounds(901, 374, 282, 24);
		txtAmountPaid.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtAmountPaid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!(Character.isDigit(c) ||
                      (c == KeyEvent.VK_BACK_SPACE) ||
                      (c == KeyEvent.VK_DELETE))) {
                    evt.consume(); // ignore input if it is not a digit or backspace or delete key
                }
            }
        });
		contentPane.add(txtAmountPaid);
		
		JLabel image = new JLabel("");
		image.setBounds(0, 0, 410, 289);
		desktopPane.add(image);
		
		
		JButton btnAdd = new JButton("Add Reservation");
		btnAdd.setFont(new Font("SansSerif", Font.PLAIN, 20));
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
					//int contact = Integer.parseInt(contactNo);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String date1 = sdf.format(checkin.getDate());
					String date2 = sdf.format(checkout.getDate());
					//String roomNo = txtRoomNo.getText();
					String balance = txtBalance.getText();
					int bal = Integer.parseInt(balance);
					String amount = txtAmountPaid.getText();
					int amnt = Integer.parseInt(amount);
					String reservation_type = comboBoxType.getSelectedItem().toString();
				
					String status = comboBoxStatus.getSelectedItem().toString();
					
					
					if(chkfamily.isSelected()) {
						accomodation_type = accomodation_type + " Family Room,";
					}else
						accomodation_type = accomodation_type + "";
					if(chkkubo.isSelected()) {
						accomodation_type = accomodation_type + " Kubo Room,";
					}else
						accomodation_type = accomodation_type + "";
					if(chkteepee.isSelected()) {
						accomodation_type = accomodation_type + " Teepee Hut,";
					}else
						accomodation_type = accomodation_type + "";
					if(chkcabana.isSelected()) {
						accomodation_type = accomodation_type + " Open Cabana,";
					}else
						accomodation_type = accomodation_type + "";
					if(chkpool.isSelected()) {
						accomodation_type = accomodation_type + " Tent,";
					}else
						accomodation_type = accomodation_type + "";
					
					if(chkgasul.isSelected()) {
						additionals = additionals + " Gasul,";
					}else
						additionals = additionals + "";
					if(chkcookware.isSelected()) {
						additionals = additionals + " Cookware Set,";
					}else
						additionals = additionals + "";
					if(chkmatress.isSelected()) {
						additionals = additionals + " Extra Matress,";
					}else
						additionals = additionals + "";
					if(chkextraperson.isSelected()) {
						additionals = additionals + " Extra Person,";
					}else
						additionals = additionals + "";
					
					if(fname.isEmpty()) {
						JOptionPane.showMessageDialog(null, "A field is empty. Please fill up all of the fields.");
					}else if(lname.isEmpty()) {
						JOptionPane.showMessageDialog(null, "A field is empty. Please fill up all of the fields.");
					}else if(email.isEmpty()) {
						JOptionPane.showMessageDialog(null, "A field is empty. Please fill up all of the fields.");
					}else if(contactNo.isEmpty()) {
						JOptionPane.showMessageDialog(null, "A field is empty. Please fill up all of the fields.");
					}else if(date1.isEmpty()) {
						JOptionPane.showMessageDialog(null, "A field is empty. Please fill up all of the fields.");
					}else if(date2.isEmpty()) {
						JOptionPane.showMessageDialog(null, "A field is empty. Please fill up all of the fields.");
					}else if(balance.isEmpty()) {
						JOptionPane.showMessageDialog(null, "A field is empty. Please fill up all of the fields.");
					}else if(amount.isEmpty()) {
						JOptionPane.showMessageDialog(null, "A field is empty. Please fill up all of the fields.");
					}else if(!chkfamily.isSelected() && !chkkubo.isSelected() && !chkteepee.isSelected() && !chkcabana.isSelected() && !chkpool.isSelected()) {
						JOptionPane.showMessageDialog(null, "Room type can't be unselected. Please select atleast one room.");
					}else
						
					if(status.equals("Select Status")) {
						JOptionPane.showMessageDialog(null, "Please select status.");
					}
					else
					if(reservation_type.equals("Select Transaction Type")) {
						JOptionPane.showMessageDialog(null, "Please select transaction type.");
					}
					else
					if(isValidEmail(email)) {
						pst = conn.prepareStatement("INSERT INTO Transaction_Records (first_name, last_name, email, contact_no, check_in, check_out, room_description, balance, amount_paid, payment_proof, room_additionals, employee_id, status, reservation_type) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
						pst.setString(1,fname);
						pst.setString(2,lname);
						pst.setString(3,email);
						pst.setString(4,contactNo);
						pst.setString(5,date1);
						pst.setString(6,date2);
						pst.setString(7,accomodation_type);
						pst.setInt(8,bal);
						pst.setInt(9,amnt);
						pst.setBytes(10, person_image);
						pst.setString(11, additionals);
						pst.setInt(12, emp_id);
						pst.setString(13, status);
						pst.setString(14, reservation_type);
					} else {
						JOptionPane.showMessageDialog(null, "Please enter a valid email address");
					}
					int k = pst.executeUpdate();
					
					if (k==1) {
						JOptionPane.showMessageDialog(null, "Transaction Added Successfully!");
						txtFname.setText("");
						txtLname.setText("");
						txtEmail.setText("");
						txtContactNo.setText("");
						//txtRoomNo.setText("");
						txtBalance.setText("");
						txtAmountPaid.setText("");
						checkin.setDate(null);
						checkout.setDate(null);
						image.setIcon(null);
						path.setText("Path");
						clearOption();
						accomodation_type = "";
						additionals = "";
						comboBoxType.setSelectedIndex(0);
						comboBoxStatus.setSelectedIndex(0);
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
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null, "A field is empty. Please fill up all of the fields.");
					
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Transaction Failed to Save");
				}
				finally {
				
				}
				
			}
		});
		btnAdd.setBounds(901, 408, 282, 40);
		contentPane.add(btnAdd);
		String[] colum = {"Select Field","transaction_id","first_name","last_name","email","contact_no","check_in","check_out"};
		JComboBox comboBox = new JComboBox(colum);
		comboBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
		comboBox.setBounds(901, 478, 282, 24);
		contentPane.add(comboBox);
		
		txtField = new JTextField();
		txtField.setBounds(901, 512, 282, 24);
		txtField.setText("Search for?");
		txtField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtField.setForeground(new Color(159, 159, 159));
		contentPane.add(txtField);
		txtField.setColumns(20);
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
		btnSearch.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnSearch.setBorder(null);
		btnSearch.setBackground(new Color(225, 167, 48));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conn = sqliteConnection.dbConnector();
				try {
					//System.out.println(emp_id);
					//System.out.println(at);
					//System.out.println(adts);
					String field = comboBox.getSelectedItem().toString();
					String txtfield = txtField.getText();
					pst = conn.prepareStatement("SELECT * FROM Transaction_Records WHERE "+field+"='"+txtfield+"'");
					rs = pst.executeQuery();
					
					if(rs.next()==true) {
						trans_type = rs.getString("reservation_type");
						if(trans_type.equals("Appointment")) {
							comboBoxType.setSelectedIndex(1);
						} else if(trans_type.equals("Walk-in")) {
							comboBoxType.setSelectedIndex(2);
						}
						st = rs.getString("status");
						if(st.equals("Pending")) {
							comboBoxStatus.setSelectedIndex(1);
						}else if(st.equals("Fully paid")) {
							comboBoxStatus.setSelectedIndex(2);
						}else if(st.equals("Cancelled")) {
							comboBoxStatus.setSelectedIndex(3);
						}
								
						
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

			
						

						
						at = rs.getString(8);
						if(at.contains("Family")) {
							chkfamily.setSelected(true);
						}else
							chkfamily.setSelected(false);
						if(at.contains("Kubo")) {
							chkkubo.setSelected(true);
						}else
							chkkubo.setSelected(false);
						if(at.contains("Teepee")) {
							chkteepee.setSelected(true);
						}else
							chkteepee.setSelected(false);
						if(at.contains("Open")) {
							chkcabana.setSelected(true);
						}else
							chkcabana.setSelected(false);
						if(at.contains("Tent")) {
							chkpool.setSelected(true);
						}else
							chkpool.setSelected(false);
						
						adts = rs.getString(9);
						if(adts.contains("Gasul")) {
							chkgasul.setSelected(true);
						}else
							chkgasul.setSelected(false);
						if(adts.contains("Cookware Set")) {
							chkcookware.setSelected(true);
						}else
							chkcookware.setSelected(false);
						if(adts.contains("Extra Matress")) {
							chkmatress.setSelected(true);
						}else
							chkmatress.setSelected(false);
						if(adts.contains("Extra Person")) {
							chkextraperson.setSelected(true);
						}else
							chkextraperson.setSelected(false);
						
						txtBalance.setText(rs.getString(10));
						txtAmountPaid.setText(rs.getString(11));
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
					System.out.print(e1);
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
		image.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "","Payment Proof",JOptionPane.INFORMATION_MESSAGE,format);
			}
		});
		btnSearch.setBounds(901, 551, 282, 40);
		contentPane.add(btnSearch);
		
		JLabel lblNewLabel_6 = new JLabel("Select Field:");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel_6.setBounds(786, 478, 97, 24);
		contentPane.add(lblNewLabel_6);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("SansSerif", Font.PLAIN, 20));
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
					//int contact = Integer.parseInt(contactNo);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String date1 = sdf.format(checkin.getDate());
					String date2 = sdf.format(checkout.getDate());
					//String roomNo = txtRoomNo.getText();
					String balance = txtBalance.getText();
					int bal = Integer.parseInt(balance);
					String amount = txtAmountPaid.getText();
					int amnt = Integer.parseInt(amount);
					String reservation_type = comboBoxType.getSelectedItem().toString();
					
					String status = comboBoxStatus.getSelectedItem().toString();
					
					if(chkfamily.isSelected()) {
						accomodation_type = accomodation_type + " Family Room,";
					}else
						accomodation_type = accomodation_type + "";
					if(chkkubo.isSelected()) {
						accomodation_type = accomodation_type + " Kubo Room,";
					}else
						accomodation_type = accomodation_type + "";
					if(chkteepee.isSelected()) {
						accomodation_type = accomodation_type + " Teepee Hut,";
					}else
						accomodation_type = accomodation_type + "";
					if(chkcabana.isSelected()) {
						accomodation_type = accomodation_type + " Open Cabana,";
					}else
						accomodation_type = accomodation_type + "";
					if(chkpool.isSelected()) {
						accomodation_type = accomodation_type + " Tent,";
					}else
						accomodation_type = accomodation_type + "";
					
					if(chkgasul.isSelected()) {
						additionals = additionals + " Gasul,";
					}else
						additionals = additionals + "";
					if(chkcookware.isSelected()) {
						additionals = additionals + " Cookware Set,";
					}else
						additionals = additionals + "";
					if(chkmatress.isSelected()) {
						additionals = additionals + " Extra Matress,";
					}else
						additionals = additionals + "";
					if(chkextraperson.isSelected()) {
						additionals = additionals + " Extra Person,";
					}else
						additionals = additionals + "";
					
					if(fname.isEmpty()) {
						JOptionPane.showMessageDialog(null, "A field is empty. Please fill up all of the fields.");
					}else if(lname.isEmpty()) {
						JOptionPane.showMessageDialog(null, "A field is empty. Please fill up all of the fields.");
					}else if(email.isEmpty()) {
						JOptionPane.showMessageDialog(null, "A field is empty. Please fill up all of the fields.");
					}else if(contactNo.isEmpty()) {
						JOptionPane.showMessageDialog(null, "A field is empty. Please fill up all of the fields.");
					}else if(date1.isEmpty()) {
						JOptionPane.showMessageDialog(null, "A field is empty. Please fill up all of the fields.");
					}else if(date2.isEmpty()) {
						JOptionPane.showMessageDialog(null, "A field is empty. Please fill up all of the fields.");
					}else if(balance.isEmpty()) {
						JOptionPane.showMessageDialog(null, "A field is empty. Please fill up all of the fields.");
					}else if(amount.isEmpty()) {
						JOptionPane.showMessageDialog(null, "A field is empty. Please fill up all of the fields.");
					}else if(!chkfamily.isSelected() && !chkkubo.isSelected() && !chkteepee.isSelected() && !chkcabana.isSelected() && !chkpool.isSelected()) {
						JOptionPane.showMessageDialog(null, "Room type can't be unselected. Please select atleast one room.");
					}else
						
						if(status.equals("Select Status")) {
							JOptionPane.showMessageDialog(null, "Please select status.");
						}
						else
						if(reservation_type.equals("Select Transaction Type")) {
							JOptionPane.showMessageDialog(null, "Please select transaction type.");
						}
						else
					
					if(isValidEmail(email)) {
						if (person_image != null  && person_image.length > 0) {
						pst = conn.prepareStatement("UPDATE Transaction_Records SET first_name=?,last_name=?,email=?,contact_no=?,check_in=?,check_out=?,room_description=?,room_additionals=?,balance=?,amount_paid=?,payment_proof=?,status=?,reservation_type=? WHERE transaction_id=?");
						pst.setString(1,fname);
						pst.setString(2,lname);
						pst.setString(3,email);
						pst.setString(4,contactNo);
						pst.setString(5,date1);
						pst.setString(6,date2);
						pst.setString(7,accomodation_type);
						pst.setString(8, additionals);
						pst.setInt(9,bal);
						pst.setInt(10,amnt);
						pst.setBytes(11, person_image);
						pst.setString(12, status);
						pst.setString(13, reservation_type);
						pst.setString(14, transaction_id);
						}
						else {
							pst = conn.prepareStatement("UPDATE Transaction_Records SET first_name=?,last_name=?,email=?,contact_no=?,check_in=?,check_out=?,room_description=?,room_additionals=?,balance=?,amount_paid=?,status=?,reservation_type=? WHERE transaction_id=?");
							pst.setString(1,fname);
							pst.setString(2,lname);
							pst.setString(3,email);
							pst.setString(4,contactNo);
							pst.setString(5,date1);
							pst.setString(6,date2);
							pst.setString(7,accomodation_type);
							pst.setString(8, additionals);
							pst.setInt(9,bal);
							pst.setInt(10,amnt);
							pst.setString(11, status);
							pst.setString(12, reservation_type);
							pst.setString(13, transaction_id);
						}
					}else {
						JOptionPane.showMessageDialog(null, "Please enter a valid email address");
					}
					
					int k = pst.executeUpdate();
					if(k==1) {
						JOptionPane.showMessageDialog(null, "Record has been sucessfully updated!");
						txtFname.setText("");
						txtLname.setText("");
						txtEmail.setText("");
						txtContactNo.setText("");
						txtBalance.setText("");
						txtAmountPaid.setText("");
						checkin.setDate(null);
						checkout.setDate(null);
						image.setIcon(null);
						path.setText("Path");
						comboBoxType.setSelectedIndex(0);
						clearOption();
						updateTable();
						additionals = "";
						accomodation_type="";
						comboBoxStatus.setSelectedIndex(0);
					}

				} catch(NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null, "Please enter balance and amount paid.");
					nfe.printStackTrace();
					
				}
				
				catch (Exception e1) {
					//JOptionPane.showMessageDialog(null, "An Error was encountered while Updating");
					e1.printStackTrace();
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
		btnUpdate.setBounds(901, 601, 282, 40);
		contentPane.add(btnUpdate);
		
		JButton attach_img = new JButton("Attach Image");
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
		attach_img.setFont(new Font("SansSerif", Font.PLAIN, 20));
		attach_img.setBorder(null);
		attach_img.setBackground(new Color(225, 167, 48));
		attach_img.setBounds(336, 601, 271, 40);
		contentPane.add(attach_img);
		
		path = new JTextField();
		path.setFont(new Font("SansSerif", Font.PLAIN, 16));
		path.setColumns(10);
		path.setBounds(265, 567, 410, 24);
		path.setEditable(false);
		path.setText("Path");
		contentPane.add(path);
		
		JLabel lblAdditionals = new JLabel("Additionals:");
		lblAdditionals.setHorizontalAlignment(SwingConstants.TRAILING);
		lblAdditionals.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblAdditionals.setBounds(786, 179, 97, 24);
		contentPane.add(lblAdditionals);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 200, 683);
		contentPane.add(panel);
		
		JLabel icon = new JLabel("");
		icon.setIcon(new ImageIcon(HomePage.class.getResource("/villarose.png")));
		icon.setBounds(25, 26, 144, 151);
		panel.add(icon);
		
		JButton btn_transaction = new JButton("Transactions");
		btn_transaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
				dispose();
				transaction_table trtpage = new transaction_table(emp_id);
				trtpage.setLocationRelativeTo(null);
				trtpage.show();
			}
		});
		btn_transactiontables.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btn_transactiontables.setBorder(null);
		btn_transactiontables.setBackground(new Color(225, 167, 48));
		btn_transactiontables.setBounds(18, 242, 163, 40);
		panel.add(btn_transactiontables);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setHorizontalAlignment(SwingConstants.TRAILING);
		lblStatus.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblStatus.setBounds(786, 295, 97, 24);
		contentPane.add(lblStatus);
		
		
		JLabel lblNewLabel_6_1 = new JLabel("Type:");
		lblNewLabel_6_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel_6_1.setBounds(240, 230, 97, 24);
		contentPane.add(lblNewLabel_6_1);
		
		
		
		
		updateTable();
		

	}
	private void updateTable() {
		conn = sqliteConnection.dbConnector();
		String sql = "SELECT * FROM Transaction_Records";
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
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
	
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
	
	private void clearOption() {
		chkfamily.setSelected(false);
		chkkubo.setSelected(false);
		chkteepee.setSelected(false);
		chkcabana.setSelected(false);
		chkpool.setSelected(false);
		chkgasul.setSelected(false);
		chkcookware.setSelected(false);
		chkmatress.setSelected(false);
		chkextraperson.setSelected(false);

		
	}
	
	private void createMainDir() {
		File directory = new File("C:\\Villa Rose Resort");
	    boolean success = directory.mkdir();
	    if (success) {
	      System.out.println("Directory created successfully.");
	    } else {
	      System.out.println("Error creating directory.");
	    }
	}
	private void createRepDir() {
		File directory = new File("C:\\Villa Rose Resort\\Centralized Folder");
	    boolean success = directory.mkdir();
	    if (success) {
	      System.out.println("Directory created successfully.");
	    } else {
	      System.out.println("Error creating directory.");
	    }
	}
	
	private static void createBackUpDir() {
		File directory = new File("C:\\Villa Rose Resort\\Back Up");
	    boolean success = directory.mkdir();
	    if (success) {
	      System.out.println("Directory created successfully.");
	    } else {
	      System.out.println("Error creating directory.");
	    }
	}
	private static void TermsandCondition(String message) {
		JOptionPane.showMessageDialog(null, message, "Terms and Conditions", JOptionPane.INFORMATION_MESSAGE);
	}
}