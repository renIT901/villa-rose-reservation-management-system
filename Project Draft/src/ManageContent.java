import java.awt.EventQueue;
import java.awt.FileDialog;

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
import java.awt.Frame;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import javax.swing.ImageIcon;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ManageContent extends JFrame {

	private JPanel contentPane;
	private String transaction_id;
	private String gen_table;
	private String filename=null;
	String emp_role;
	String exportfolder;
	String sql;
	List<String[]> data = null;
	static String exportfolder1;
    // SQLite database file location
    static final String DB_FILE_LOCATION = "VillaRoseDB.db";
    
    // SQLite JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.sqlite.JDBC";  
    static final String DB_URL = "jdbc:sqlite:" + DB_FILE_LOCATION;

    // Button to initiate backup process
    static JButton backupButton;
    static String filename1= "";
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
	Statement stmt = null;
	
	Image img4 = new ImageIcon(this.getClass().getResource("/villarose.png")).getImage();
	
	public ManageContent(int emp_id) {
		setResizable(false);
		getRole(emp_id);
		setTitle("Villa Rose System");
		System.out.print(emp_id);
		conn = sqliteConnection.dbConnector();
		setBackground(new Color(250, 245, 232));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 960, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 245, 232));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("Manage Content");
		lblNewJgoodiesLabel.setFont(new Font("SansSerif", Font.PLAIN, 40));
		lblNewJgoodiesLabel.setBounds(231, 31, 297, 48);
		contentPane.add(lblNewJgoodiesLabel);
		
		String[] column = {"Select Table to Generate","Transaction_Records","Employee"};
		JComboBox comboBox = new JComboBox(column);
		comboBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
		comboBox.setBounds(231, 114, 311, 24);
		contentPane.add(comboBox);
		loadUserName();
		
		JButton btnNewButton_1 = new JButton("Edit Employees Account");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				conn = sqliteConnection.dbConnector();
				pst = conn.prepareStatement("SELECT role FROM Employee WHERE employee_id =" + "'"+emp_id+"'");
				rs = pst.executeQuery();
				if(rs.next()==true) {
					emp_role=rs.getString("role");
				}
				
				}
				catch (Exception e1) {
					System.out.println(e1);
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
				if(emp_role.equals("Admin")) {
					dispose();
					EditEmployees epage = new EditEmployees(emp_id);
					epage.setLocationRelativeTo(null);
					epage.show();
				}
				else {
					System.out.println(emp_role);
					JOptionPane.showMessageDialog(null, "Current account does not have administritive rights to access Employee Accounts.");
				}
			}
		});
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setBackground(new Color(225, 167, 48));
		btnNewButton_1.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnNewButton_1.setBounds(231, 295, 255, 40);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Backup Database");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backupDatabase();
			}
		});
		btnNewButton_2.setBorder(null);
		btnNewButton_2.setBackground(new Color(225, 167, 48));
		btnNewButton_2.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnNewButton_2.setBounds(231, 594, 187, 40);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_5 = new JButton("Restore Backup");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    JFileChooser chooser = new JFileChooser();
				//File directory = new File("C:\\Users\\Ren Garcia\\Desktop\\Back Up");
				chooser.setCurrentDirectory(new File("C:\\Back Up"));
			    int returnVal = chooser.showOpenDialog(null);
			    if (returnVal == JFileChooser.APPROVE_OPTION) {
			        File file = chooser.getSelectedFile();
			        String filePath = file.getAbsolutePath();
			        try {
			            // Connect to the database to be restored
			            Class.forName("org.sqlite.JDBC");
			            String url = "jdbc:sqlite:VillaRoseDB.db";
			            Connection conn = DriverManager.getConnection(url);

			            // Execute SQL to restore the database from the backup file
			            String sql = "RESTORE FROM '" + filePath + "'";
			            conn.createStatement().execute(sql);

			            // Close the connection
			            conn.close();

			            JOptionPane.showMessageDialog(null, "Database restored successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
			        } catch (ClassNotFoundException ex) {
			            JOptionPane.showMessageDialog(null, "SQLite JDBC driver not found", "Error", JOptionPane.ERROR_MESSAGE);
			        } catch (SQLException ex) {
			            JOptionPane.showMessageDialog(null, "Error restoring database: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			        }
			    }
			}
			
		});
		btnNewButton_5.setBorder(null);
		btnNewButton_5.setBackground(new Color(225, 167, 48));
		btnNewButton_5.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnNewButton_5.setBounds(437, 594, 187, 40);
		contentPane.add(btnNewButton_5);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 200, 683);
		contentPane.add(panel);
		
		JLabel icon = new JLabel("");
		icon.setIcon(new ImageIcon(ManageContent.class.getResource("/villarose.png")));
		icon.setBounds(37, 29, 125, 125);
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
		
		JButton btnReport = new JButton("Generate CSV");
		btnReport.setBounds(231, 146, 187, 40);
		contentPane.add(btnReport);
		btnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String gen_table = (String) comboBox.getSelectedItem();
				conn = sqliteConnection.dbConnector();
				JFileChooser chooser = new JFileChooser();
				File directory = new File("C:\\Villa Rose Resort\\Centralized Folder");
				chooser.setCurrentDirectory(directory);
				int result = chooser.showOpenDialog(null);
				//chooser.showOpenDialog(null);
				//File f = chooser.getSelectedFile();
				//filename = f.getAbsolutePath();
				if(result == JFileChooser.APPROVE_OPTION){
					if (directory.isDirectory()) {
					 File selectedFolder = chooser.getSelectedFile();
					 exportfolder = selectedFolder.getAbsolutePath();
					}
					else {
						//createRepDir();
						chooser.setCurrentDirectory(directory);
						File selectedFolder = chooser.getSelectedFile();
						exportfolder = selectedFolder.getAbsolutePath();
					}
				
					 //System.out.print(exportFolder);
				
				
				if (gen_table.equals("Transaction_Records")){
			        try {
			        	conn = sqliteConnection.dbConnector();
			        	String csvFilePath = exportfolder + ".csv";
						String sql= "Select * from " + gen_table;
						pst= conn.prepareStatement(sql);
						rs = pst.executeQuery();
			             
			            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(csvFilePath));
			             
			            // write header line containing column names       
			            fileWriter.write("transaction_id,first_name,last_name,email,contact_no,check_in,check_out,room_description,room_additionals,balance,amount_paid");
			            while (rs.next()) {
			                String transaction_id = rs.getString("transaction_id");
			                String first_name = rs.getString("first_name");
			                String last_name = rs.getString("last_name");
			                String email = rs.getString("email");
			                String contact_no = rs.getString("contact_no");
			                String check_in = rs.getString("check_in");
			                String check_out = rs.getString("check_out");
			                String room_description = rs.getString("room_description");
			                String room_additionals = rs.getString("room_additionals");
			                String balance = rs.getString("balance");
			                String amount_paid = rs.getString("amount_paid");
			                
			                String[] arr2 = room_additionals.split(",");
							String[] arr = room_description.split(",");
							String desc = String.join("-", arr);
							String adds = String.join("-", arr2);
			                 
			                String line = String.format("\"%s\",%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
			                		transaction_id,first_name,last_name,email,contact_no,check_in,check_out,desc,adds,balance,amount_paid);
			                 
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
				} 
				else if (gen_table.equals("Employee") && emp_role.equals("Admin")) {
					System.out.print(emp_role);
							try {
								conn = sqliteConnection.dbConnector();
					        	String csvFilePath = filename + ".csv";
								String sql= "Select * from Employee";
								pst= conn.prepareStatement(sql);
								rs = pst.executeQuery();
					             
					            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(csvFilePath));
					             
					            // write header line containing column names       
					            fileWriter.write("employee_id,uname,sQuestion,sq_ans,role,fname,lname,email,birthday,date_started,salary,schedule of duties");
					            while (rs.next()) {
					                String employee_id = rs.getString("employee_id");
					                String uname = rs.getString("uname");
					                //String pword = rs.getString("pword");
					                String sQuestion = rs.getString("sQuestion");
					                String sq_ans = rs.getString("sq_ans");
					                String role = rs.getString("role");
					                String fname = rs.getString("fname");
					                String lname = rs.getString("lname");
					                String email = rs.getString("email");
					                String birthday = rs.getString("birthday");
					                String date_started = rs.getString("date_started");
					                String salary = rs.getString("salary");
					                String schedule = rs.getString("schedule_of_duties");
					                String line = String.format("\"%s\",%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
					                		employee_id,uname,sQuestion,sq_ans,role,fname,lname,email,birthday,date_started,salary,schedule);
					                 
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
					
				}else {
					JOptionPane.showMessageDialog(null, "Current account does not have administritive rights to access Employee Accounts.");
				}
				}
			}
		});
		btnReport.setBorder(null);
		btnReport.setBackground(new Color(225, 167, 48));
		btnReport.setFont(new Font("SansSerif", Font.PLAIN, 20));
		
		JButton btnGenerateCsv = new JButton("Generate PDF");
		btnGenerateCsv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String gen_table = (String) comboBox.getSelectedItem();
				if(gen_table.equals("Transaction_Records")) {
				List<String[]> data = retrieveDataFromTable(gen_table);
		        savePDF(data);
				} else if (gen_table.equals("Employee") && emp_role.equals("Admin")) {
					List<String[]> data = retrieveDataFromTable(gen_table);
			        savePDF(data);
				} else if (gen_table.equals("Employee") && emp_role.equals("Employee")) {
					JOptionPane.showMessageDialog(null, "Current account does not have administritive rights to access Employee Accounts.");
				}
			}
		});
		btnGenerateCsv.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnGenerateCsv.setBorder(null);
		btnGenerateCsv.setBackground(new Color(225, 167, 48));
		btnGenerateCsv.setBounds(231, 197, 187, 40);
		contentPane.add(btnGenerateCsv);
	}
	public void loadUserName(){
		try {
			String sql= "Select * from Transaction_Records";
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
    private static void backupDatabase() {
        try {
            // Create a connection to the SQLite database
            Connection conn = DriverManager.getConnection(DB_URL);
            JFileChooser chooser = new JFileChooser();
			//chooser.showOpenDialog(null);
			//File f = chooser.getSelectedFile();
			//filename1 = f.getAbsolutePath();
            
			JFileChooser chooser1 = new JFileChooser();
			File directory = new File("C:\\Villa Rose Resort\\Back Up");
			chooser.setCurrentDirectory(directory);
			int result = chooser.showOpenDialog(null);
			//chooser.showOpenDialog(null);
			//File f = chooser.getSelectedFile();
			//filename = f.getAbsolutePath();
			if(result == JFileChooser.APPROVE_OPTION) {
				if (directory.isDirectory()) {
				 File selectedFolder = chooser.getSelectedFile();
				 exportfolder1 = selectedFolder.getAbsolutePath();
				}
				else {
					//createBackUpDir();
					chooser.setCurrentDirectory(directory);
					File selectedFolder = chooser.getSelectedFile();
					exportfolder1 = selectedFolder.getAbsolutePath();
				}
            // Create backup file with current date and time as file name
            String backupFileName = "database_backup_" + System.currentTimeMillis() + ".db";
            File backupFile = new File(exportfolder1+".db");

            // Copy database file to backup file
            FileChannel src = new FileInputStream(new File(DB_FILE_LOCATION)).getChannel();
            FileChannel dest = new FileOutputStream(backupFile).getChannel();
            dest.transferFrom(src, 0, src.size());
            src.close();
            dest.close();

            // Show success message
            JOptionPane.showMessageDialog(null, "Database backup created at:\n" + backupFile.getAbsolutePath());

            // Close the database connection
            conn.close();
        }} catch (SQLException | IOException ex) {
            // Show error message
            JOptionPane.showMessageDialog(null, "Error creating database backup:\n" + ex.getMessage());
        }
    }
	private void updateTable(String trans_id) {
		conn = sqliteConnection.dbConnector();
		String sql = "SELECT * FROM Transaction_Records where transaction_id="+trans_id;
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

	 private List<String[]> retrieveDataFromTable(String tableName) {
	        data = new ArrayList<>();
	        try {
	    		conn = sqliteConnection.dbConnector();
	    		
	    		if(tableName.equals("Transaction_Records")) {
	    		sql = "SELECT * FROM Transaction_Records";
	    		}
	    		else {
	    			sql = "SELECT * FROM Employee";
	    		}
		            pst = conn.prepareStatement(sql);
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
	                document.open();
	                PdfPTable table = new PdfPTable(data.get(0).length);
	                for (String[] row : data) {
	                    for (String cell : row) {
	                        PdfPCell pdfCell = new PdfPCell(new Paragraph(cell));
	                        table.addCell(pdfCell);
	                    }
	                }
	                document.add(table);
	                document.close();
	                data.clear();
	                JOptionPane.showMessageDialog(null, "Exported Successfully");
	            }
	        } catch (IOException | DocumentException e) {
	            e.printStackTrace();
	        }

	    }
	    private void getRole(int emp_id) {
			try {
				conn = sqliteConnection.dbConnector();
				pst = conn.prepareStatement("SELECT role FROM Employee WHERE employee_id =" + "'"+emp_id+"'");
				rs = pst.executeQuery();
				if(rs.next()==true) {
					emp_role=rs.getString("role");
				}
				
				}
				catch (Exception e1) {
					System.out.println(e1);
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
}

