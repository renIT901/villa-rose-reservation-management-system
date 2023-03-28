import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.sqlite.SQLiteException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import java.awt.event.FocusAdapter;
import com.toedter.calendar.JDateChooser;
import javax.swing.JRadioButton;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.SwingConstants;


public class Register extends JFrame {

	private JPanel contentPane;

	private JTextField txtfname;
	private JTextField txtlname;
	private JTextField txtuname;
	private JTextField txtSqans;
	private JComboBox comboBox;
	private JTextField txtEmail;
	private JPasswordField txtpword;
	private JPasswordField txtAdminPass;
	private ButtonGroup bg = new ButtonGroup();
	private String gender;
    private Pattern pattern;
    private Matcher matcher;
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	Statement stm = null;

    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";

    public boolean validate(final String password) {
        matcher = pattern.matcher(password);
        return matcher.matches();
    }
	
	
	public Register() {
		setResizable(false);
		//pattern = Pattern.compile(PASSWORD_PATTERN);
		//addPlaceholderStyle(txtSqans);
		//addPlaceholderStyle(txtAdminPass);
		setTitle("Villa Rose System");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 960, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 245, 232));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Register Employee");
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 40));
		lblNewLabel.setBounds(303, 46, 339, 52);
		contentPane.add(lblNewLabel);
		
        JLabel regIcon = new JLabel();
        regIcon.setIcon(new ImageIcon("1.png"));
		
		JLabel lblNewLabel_1 = new JLabel("First Name:");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(268, 129, 82, 14);
		contentPane.add(lblNewLabel_1);
		
		txtfname = new JTextField();
		txtfname.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtfname.setBounds(360, 124, 282, 24);
		txtfname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (Character.isDigit(c)) {
                    evt.consume(); // ignore input if it is a digit
                }
            }
        });
		contentPane.add(txtfname);
		txtfname.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Last Name:");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(268, 163, 82, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Username:");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel_1_1_1.setBounds(268, 199, 82, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Password:");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1.setBounds(268, 234, 82, 14);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		txtlname = new JTextField();
		txtlname.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtlname.setColumns(10);
		txtlname.setBounds(360, 158, 282, 24);
		txtlname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (Character.isDigit(c)) {
                    evt.consume(); // ignore input if it is a digit
                }
            }
        });
		contentPane.add(txtlname);
		
		txtuname = new JTextField();
		txtuname.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtuname.setColumns(10);
		txtuname.setBounds(360, 194, 282, 24);
		contentPane.add(txtuname);
		
		JDateChooser bday = new JDateChooser();
		bday.setBounds(360, 322, 282, 24);
		contentPane.add(bday);
		
		JRadioButton rMale = new JRadioButton("Male");
		rMale.setBounds(355, 356, 109, 23);
		rMale.setBackground(new Color(250, 245, 232));
		rMale.setFont(new Font("SansSerif", Font.PLAIN, 16));
		contentPane.add(rMale);
		
		JRadioButton rFemale = new JRadioButton("Female");
		rFemale.setBounds(355, 382, 109, 23);
		rFemale.setBackground(new Color(250, 245, 232));
		rFemale.setFont(new Font("SansSerif", Font.PLAIN, 16));
		contentPane.add(rFemale);
		
		bg.add(rMale);
		bg.add(rFemale);
		
		
		String[] colum = {"Select Security Question","In what city were you born?","What is the name of your favorite pet?","What was your favorite food as a child?","What high school did you attend?"};
		comboBox = new JComboBox(colum);
		comboBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
		comboBox.setBounds(284, 428, 358, 24);
		contentPane.add(comboBox); 
		
		JCheckBox shpword = new JCheckBox("Show Password");
		shpword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(shpword.isSelected()) {
					txtpword.setEchoChar((char)0);
				}else {
					txtpword.setEchoChar('*');
				}
			}
		});
		shpword.setBackground(new Color(250, 245, 232));
		shpword.setFont(new Font("SansSerif", Font.PLAIN, 16));
		shpword.setBounds(359, 256, 139, 23);
		contentPane.add(shpword);
		
		
		txtSqans = new JTextField();
		txtSqans.setForeground(new Color(159, 159, 159));
		txtSqans.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtSqans.setText("Provide Answer");
		txtSqans.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent event) {
				if(txtSqans.getText().equals("Provide Answer")) {
					txtSqans.setText("");
					txtSqans.setForeground(new Color(0, 0, 0));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(txtSqans.getText().equals("")) {
					txtSqans.setText("Provide Answer");
					txtSqans.setForeground(new Color(159, 159, 159));
				}			
			}
			
		});
		txtSqans.setToolTipText("");
		txtSqans.setBounds(284, 464, 358, 24);
		contentPane.add(txtSqans);
		txtSqans.setColumns(10);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setBorder(null);
		btnRegister.setBackground(new Color(225, 167, 48));
		btnRegister.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					conn = sqliteConnection.dbConnector();
					String fname = txtfname.getText();
					String lname = txtlname.getText();
					String uname = txtuname.getText();
					String pword = txtpword.getText();
					PasswordValidator validator = new PasswordValidator();
					String sqans = txtSqans.getText();
					String email = txtEmail.getText().toLowerCase();;
					String adpass = txtAdminPass.getText();
					String sq = comboBox.getSelectedItem().toString();
					String adminpass = "";
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String date1 = sdf.format(bday.getDate());
					if(rMale.isSelected()) {
						gender = "Male";
					}
					else if (rFemale.isSelected()) {
						gender = "Female";
					}
					
					
					if(fname.isEmpty()) {
						JOptionPane.showMessageDialog(null, "A field is empty. Please fill up all of the fields.");
					}else if(lname.isEmpty()) {
						JOptionPane.showMessageDialog(null, "A field is empty. Please fill up all of the fields.");
					}else if(uname.isEmpty()) {
						JOptionPane.showMessageDialog(null, "A field is empty. Please fill up all of the fields.");
					}else if(pword.isEmpty()) {
						JOptionPane.showMessageDialog(null, "A field is empty. Please fill up all of the fields.");
					}else if(email.isEmpty()) {
						JOptionPane.showMessageDialog(null, "A field is empty. Please fill up all of the fields.");
					}else if(date1.isEmpty()) {
						JOptionPane.showMessageDialog(null, "A field is empty. Please fill up all of the fields.");
					}else if(sq.isEmpty()) {
						JOptionPane.showMessageDialog(null, "A field is empty. Please fill up all of the fields.");
					}else if(sqans.isEmpty()) {
						JOptionPane.showMessageDialog(null, "A field is empty. Please fill up all of the fields.");
					}else if(bg.getSelection()==null) {
						JOptionPane.showMessageDialog(null, "A field is empty. Please fill up all of the fields.");
					}
					
					else {
					
					stm = conn.createStatement();
					String sql1 = "SELECT pword FROM Employee where employee_id = 1";
					rs = stm.executeQuery(sql1);
					while(rs.next()) {
						adminpass = rs.getString("pword");
					}
				    if (isValidEmail(email)) {
						if(validator.validate(pword)) {
							if (adminpass.equals(adpass)) {
								conn = sqliteConnection.dbConnector();
								String sql = "INSERT INTO Employee(fname,lname,uname,pword,sQuestion,sq_ans,role,email, birthday, gender) VALUES(?,?,?,?,?,?,?,?,?,?)";
								PreparedStatement pst = conn.prepareStatement(sql);
								pst.setString(1, fname);
								pst.setString(2, lname);
								pst.setString(3, uname);
								pst.setString(4, pword);
								pst.setString(5, sq);
								pst.setString(6, sqans);
								pst.setString(7, "Employee");
								pst.setString(8, email);
								pst.setString(9, date1);
								pst.setString(10, gender);
								pst.execute();
								JOptionPane.showMessageDialog(null, "You have successfully registered.");
								
								pst.close();
								conn.close();
							}
							else {
								JOptionPane.showMessageDialog(null, "Wrong admin password. Please try again.");
							}
						}
							else {
								JOptionPane.showMessageDialog(null, "Password must be 8 characters long, and must contain a digit, a lowercase letter, an uppercase letter, and a special character.");
							}
				   }else {
					   JOptionPane.showMessageDialog(null, email + " is not a valid email address. Pleas try again.");
				   }
				}
				}catch(NullPointerException npe) {
					JOptionPane.showMessageDialog(null, "A field is empty. Please fill up all of the fields.");
				}
				catch(SQLiteException sqle) {
					JOptionPane.showMessageDialog(null, "Username has already been taken.");
				}
				catch(Exception e) {
					JOptionPane.showMessageDialog(null, "An error has occured. Please try again");
					System.out.println(e);
				}
				finally {
					JFrame frame;
					frame = new JFrame();
					if (JOptionPane.showConfirmDialog(frame, "Do you want to create another account?")==JOptionPane.YES_NO_OPTION) {
						txtfname.setText("");
						txtlname.setText("");
						txtuname.setText("");
						txtpword.setText("");
						txtSqans.setText("");
						shpword.setSelected(false);
						bday.setDate(null);
						bg.clearSelection();
						txtAdminPass.setText("");
						txtEmail.setText("");
						comboBox.setSelectedIndex(0);
					}else {
						dispose();
						Login lpage = new Login();
						lpage.setLocationRelativeTo(null);
						lpage.show();
					}
					
					try {
						rs.close();
						stm.close();
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					

					
				}
			}
		});
		btnRegister.setBounds(397, 551, 152, 48);
		contentPane.add(btnRegister);
		
		JLabel lblNewLabel_2 = new JLabel("Go back to Login");
		lblNewLabel_2.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				Login lpage = new Login();
				lpage.setLocationRelativeTo(null);
				lpage.show();
			}
		});
		lblNewLabel_2.setBounds(412, 605, 121, 24);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Email:");
		lblNewLabel_1_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1_1_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1_1.setBounds(284, 287, 66, 14);
		contentPane.add(lblNewLabel_1_1_1_1_1);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtEmail.setColumns(10);
		txtEmail.setBounds(360, 284, 282, 24);
		contentPane.add(txtEmail);
		
		txtpword = new JPasswordField();
		txtpword.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtpword.setBounds(360, 229, 282, 24);
		contentPane.add(txtpword);
		
		txtAdminPass = new JPasswordField("Enter Admin Password");
		txtAdminPass.setEchoChar((char)0);
		txtAdminPass.setForeground(new Color(159, 159, 159));
		txtAdminPass.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent event) {
				if(txtAdminPass.getText().equals("Enter Admin Password")) {
					txtAdminPass.setEchoChar('*');
					txtAdminPass.setText("");
					txtAdminPass.setForeground(new Color(0, 0, 0));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(txtAdminPass.getText().equals("")) {
					txtAdminPass.setEchoChar((char)0);
					txtAdminPass.setText("Enter Admin Password");
					txtAdminPass.setForeground(new Color(159, 159, 159));
				}			
			}
		});
		txtAdminPass.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtAdminPass.setBounds(284, 500, 358, 24);
		contentPane.add(txtAdminPass);

		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Birthday:");
		lblNewLabel_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1_1_1_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1_1_1.setBounds(284, 324, 66, 20);
		contentPane.add(lblNewLabel_1_1_1_1_1_1);
		

		
		JLabel lblNewLabel_1_1_1_1_1_1_1 = new JLabel("Gender:");
		lblNewLabel_1_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1_1_1_1_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1_1_1_1.setBounds(284, 362, 66, 14);
		contentPane.add(lblNewLabel_1_1_1_1_1_1_1);
	}
	
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}