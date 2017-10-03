// USER sql 
import java.awt.EventQueue;
import java.awt.GridLayout;
//import java.awt.*;
import java.awt.Image;
import java.sql.*;
import java.awt.image.*;
import javax.swing.*;

import java.awt.font.*;
import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.SystemColor;

import net.proteanit.sql.DbUtils;
import java.awt.Component;	


import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.JXDatePicker;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;


public class hw3 {
	Connection connection = null;
	
	Boolean searchBusiness;
	String queryString;
	
	
	private JFrame frmYelp;


	// sql
	private JScrollPane scrollPane_query;
	private JTextArea textArea_query;
	private JComboBox comboBox_select_andor;
	
	// result
	private JTable table_result;
	
	private ArrayList<String> subcategorynames = new ArrayList<>();
	private ArrayList categorynames = new ArrayList();
	
	// category
	private JScrollPane scrollPane_category;
	private JPanel panel_category;
    private ArrayList<JCheckBox> categories;

    
    private JCheckBox checkBox_category_1;
    private JCheckBox checkBox_category_2;
    private JCheckBox checkBox_category_3;
    private JCheckBox checkBox_category_4;
    private JCheckBox checkBox_category_5;
    private JCheckBox checkBox_category_6;
    private JCheckBox checkBox_category_7;
    private JCheckBox checkBox_category_8;
    private JCheckBox checkBox_category_9;
    private JCheckBox checkBox_category_10;
    private JCheckBox checkBox_category_11;
    private JCheckBox checkBox_category_12;
    private JCheckBox checkBox_category_13;
    private JCheckBox checkBox_category_14;
    private JCheckBox checkBox_category_15;
    private JCheckBox checkBox_category_16;
    private JCheckBox checkBox_category_17;
    private JCheckBox checkBox_category_18;
    private JCheckBox checkBox_category_19;
    private JCheckBox checkBox_category_20;
    private JCheckBox checkBox_category_21;
    private JCheckBox checkBox_category_22;
    private JCheckBox checkBox_category_23;
    private JCheckBox checkBox_category_24;
    private JCheckBox checkBox_category_25;
    private JCheckBox checkBox_category_26;
    private JCheckBox checkBox_category_27;
    private JCheckBox checkBox_category_28;
    
    // subcategory
	private JPanel panel_subcategory;
	private JScrollPane scrollPane_subcategory;
    public ArrayList<JCheckBox> subcategories = new ArrayList<>();

	// checkin
	private JComboBox comboBox_checkin_day1;
	private JComboBox comboBox_checkin_day2;
	private JComboBox comboBox_checkin_hour1;
	private JComboBox comboBox_checkin_hour2;
	private JComboBox comboBox_checkin_numberofcheckin;
	private JTextField textField_checkin_value;
	
	// review
	private JXDatePicker datePicker_review_from;
	private JXDatePicker datePicker_review_to;
	private JTextField textField_review_value1;
	private JTextField textField_review_value2;
	private JComboBox comboBox_review_star;
	private JComboBox comboBox_review_votes;
	
	// user 
	private org.jdesktop.swingx.JXDatePicker datePicker_user_membersince;
	private JComboBox comboBox_user_reviewcount;
	private JComboBox comboBox_user_numberoffriend;
	private JComboBox comboBox_user_avgstars;
	private JTextField textField_user_value1;
	private JTextField textField_user_value2;
	private JTextField textField_user_value3;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					hw3 window = new hw3();
					window.frmYelp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static Connection dbConnector() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			//JOptionPane.showMessageDialog(null, "Connection Successful");
			return conn;
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}

	/**
	 * Create the application.
	 */
	public hw3() {
		initialize();
		connection = dbConnector();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		searchBusiness = true;

		frmYelp = new JFrame();
		frmYelp.getContentPane().setBackground(new Color(102, 205, 170));
		frmYelp.setTitle("YELP");
		frmYelp.setBounds(100, 100, 1200, 620);
		frmYelp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmYelp.getContentPane().setLayout(null);
		
		// create business_category
		panel_category = new JPanel();
		panel_category.setBackground(new Color(240, 248, 255));
		panel_category.setBorder(new LineBorder(new Color(0, 204, 153), 1, true));
		panel_category.setLayout(new GridLayout(0, 1, 0, 10));
		
		scrollPane_category = new JScrollPane(panel_category);
		scrollPane_category.setBounds(10, 50, 150, 310);
		frmYelp.getContentPane().add(scrollPane_category);
		
		// create business_subcategory
		panel_subcategory = new JPanel();
		panel_subcategory.setBackground(new Color(240, 248, 255));
		panel_subcategory.setLayout(new GridLayout(0, 1, 0, 10));

		// category
		checkBox_category_1 = new JCheckBox("Active Life");
		checkBox_category_2 = new JCheckBox("Arts & Entertainment");
		checkBox_category_3 = new JCheckBox("Automotive");
		checkBox_category_4 = new JCheckBox("Automotive");
		checkBox_category_5 = new JCheckBox("Car Rental");
		checkBox_category_6 = new JCheckBox("Cafes");
		checkBox_category_7 = new JCheckBox("Beauty & Spas");
		checkBox_category_8 = new JCheckBox("Convenience Stores");
		checkBox_category_9 = new JCheckBox("Dentists");
		checkBox_category_10 = new JCheckBox("Doctors");
		checkBox_category_11 = new JCheckBox("Drugstores");
		checkBox_category_12 = new JCheckBox("Department Stores");
		checkBox_category_13 = new JCheckBox("Education");
		checkBox_category_14 = new JCheckBox("Event Planning & Services");
		checkBox_category_15 = new JCheckBox("Flowers & Gifts");
		checkBox_category_16 = new JCheckBox("Food");
		checkBox_category_17 = new JCheckBox("Home Services");
		checkBox_category_18 = new JCheckBox("Home & Garden");
		checkBox_category_19 = new JCheckBox("Hospitals");
		checkBox_category_20 = new JCheckBox("Hotels & Travel");
		checkBox_category_21 = new JCheckBox("Hardware Stores");
		checkBox_category_22 = new JCheckBox("Grocery");
		checkBox_category_23 = new JCheckBox("Medical Centers");
		checkBox_category_24 = new JCheckBox("Nurseries & Gardening");
		checkBox_category_25 = new JCheckBox("Nightlife");
		checkBox_category_26 = new JCheckBox("Restaurants");
		checkBox_category_27 = new JCheckBox("Shopping");
		checkBox_category_28 = new JCheckBox("Transportation");
		
		 categories = new ArrayList<>();
		 categories.add(checkBox_category_1);
		 categories.add(checkBox_category_2);
		 categories.add(checkBox_category_3);
		 categories.add(checkBox_category_4);
		 categories.add(checkBox_category_5);
		 categories.add(checkBox_category_6);
		 categories.add(checkBox_category_7);
		 categories.add(checkBox_category_8);
		 categories.add(checkBox_category_9);
		 categories.add(checkBox_category_10);
		 categories.add(checkBox_category_11);
		 categories.add(checkBox_category_12);
		 categories.add(checkBox_category_13);
		 categories.add(checkBox_category_14);
		 categories.add(checkBox_category_15);
		 categories.add(checkBox_category_16);
		 categories.add(checkBox_category_17);
		 categories.add(checkBox_category_18);
		 categories.add(checkBox_category_19);
		 categories.add(checkBox_category_20);
		 categories.add(checkBox_category_21);
		 categories.add(checkBox_category_22);
		 categories.add(checkBox_category_23);
		 categories.add(checkBox_category_24);
		 categories.add(checkBox_category_25);
		 categories.add(checkBox_category_26);
		 categories.add(checkBox_category_27);
		 categories.add(checkBox_category_28);
		 
		for (int i = 0; i < categories.size(); i++) {
			categories.get(i).setBackground(new Color(240, 248, 255) );
			panel_category.add(categories.get(i));
		}
		
		
		for (int i = 0; i < categories.size(); i++) {
			categories.get(i).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setSubcategory();
					setBusinessQueryString();
				}
			});
		}

		JPanel panel_checkin = new JPanel();
		panel_checkin.setBackground(new Color(240, 248, 255));
		panel_checkin.setBorder(new LineBorder(new Color(255, 255, 255), 3, true));
		panel_checkin.setBounds(312, 50, 150, 310);
		frmYelp.getContentPane().add(panel_checkin);
		panel_checkin.setLayout(null);
		
		JLabel label_checkin_from = new JLabel("From");
		label_checkin_from.setForeground(new Color(0, 0, 0));
		label_checkin_from.setFont(new Font("Segoe UI", Font.BOLD, 12));
		label_checkin_from.setBounds(5, 5, 60, 15);
		panel_checkin.add(label_checkin_from);
		
		comboBox_checkin_day1 = new JComboBox();
		comboBox_checkin_day1.setBackground(new Color(245, 245, 245));
		comboBox_checkin_day1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setBusinessQueryString();
			}
		});
		comboBox_checkin_day1.setModel(new DefaultComboBoxModel(new String[] {"Day", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}));
		comboBox_checkin_day1.setBounds(5, 30, 70, 25);
		panel_checkin.add(comboBox_checkin_day1);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblTo.setBounds(5, 70, 60, 15);
		panel_checkin.add(lblTo);
		
		JLabel label_checkin_numberofcheckins = new JLabel("Number of checkins");
		label_checkin_numberofcheckins.setBackground(new Color(240, 240, 240));
		label_checkin_numberofcheckins.setFont(new Font("Segoe UI", Font.BOLD, 12));
		label_checkin_numberofcheckins.setBounds(5, 145, 130, 15);
		panel_checkin.add(label_checkin_numberofcheckins);
		
		comboBox_checkin_numberofcheckin = new JComboBox();
		comboBox_checkin_numberofcheckin.setBackground(new Color(245, 245, 245));
		comboBox_checkin_numberofcheckin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setBusinessQueryString();
			}
		});
		comboBox_checkin_numberofcheckin.setModel(new DefaultComboBoxModel(new String[] {"=", ">", "<"}));
		comboBox_checkin_numberofcheckin.setBounds(5, 175, 130, 25);
		panel_checkin.add(comboBox_checkin_numberofcheckin);
		
		JLabel label_checkin_value = new JLabel("Value");
		label_checkin_value.setFont(new Font("Segoe UI", Font.BOLD, 12));
		label_checkin_value.setBounds(5, 220, 43, 15);
		panel_checkin.add(label_checkin_value);
		
		textField_checkin_value = new JTextField();
		textField_checkin_value.setColumns(10);
		textField_checkin_value.setBounds(55, 215, 80, 25);
		panel_checkin.add(textField_checkin_value);
		
		textField_checkin_value.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
              setBusinessQueryString();
            }
            public void removeUpdate(DocumentEvent e) {
              setBusinessQueryString();
            }
            public void insertUpdate(DocumentEvent e) {
              setBusinessQueryString();
            }
        }); 
        
		comboBox_checkin_day2 = new JComboBox();
		comboBox_checkin_day2.setBackground(new Color(245, 245, 245));
		comboBox_checkin_day2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setBusinessQueryString();
			}
		});
		comboBox_checkin_day2.setModel(new DefaultComboBoxModel(new String[] {"Day", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}));
		comboBox_checkin_day2.setBounds(5, 100, 70, 25);
		panel_checkin.add(comboBox_checkin_day2);
		
		comboBox_checkin_hour1 = new JComboBox();
		comboBox_checkin_hour1.setBackground(new Color(245, 245, 245));
		comboBox_checkin_hour1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setBusinessQueryString();
			}
		});
		comboBox_checkin_hour1.setModel(new DefaultComboBoxModel(new String[] {"Hour", "00:00 - 01:00", "01:00 - 02:00", "02:00 - 03:00", "03:00 - 04:00", "04:00 - 05:00", "05:00 - 06:00", "06:00 - 07:00", "07:00 - 08:00", "08:00 - 09:00", "09:00 - 10:00", "10:00 - 11:00", "11:00 - 12:00", "12:00 - 13:00", "13:00 - 14:00", "14:00 - 15:00", "15:00 - 16:00", "16:00 - 17:00", "17:00 - 18:00", "18:00 - 19:00", "19:00 - 20:00", "20:00 - 21:00", "21:00 - 22:00", "22:00 - 23:00", "23:00 - 00:00"}));
		comboBox_checkin_hour1.setBounds(75, 30, 70, 25);
		panel_checkin.add(comboBox_checkin_hour1);
		
		comboBox_checkin_hour2 = new JComboBox();
		comboBox_checkin_hour2.setBackground(new Color(245, 245, 245));
		comboBox_checkin_hour2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setBusinessQueryString();
			}
		});
		comboBox_checkin_hour2.setModel(new DefaultComboBoxModel(new String[] {"Hour", "00:00 - 01:00", "01:00 - 02:00", "02:00 - 03:00", "03:00 - 04:00", "04:00 - 05:00", "05:00 - 06:00", "06:00 - 07:00", "07:00 - 08:00", "08:00 - 09:00", "09:00 - 10:00", "10:00 - 11:00", "11:00 - 12:00", "12:00 - 13:00", "13:00 - 14:00", "14:00 - 15:00", "15:00 - 16:00", "16:00 - 17:00", "17:00 - 18:00", "18:00 - 19:00", "19:00 - 20:00", "20:00 - 21:00", "21:00 - 22:00", "22:00 - 23:00", "23:00 - 00:00"}));
		comboBox_checkin_hour2.setBounds(75, 100, 70, 25);
		panel_checkin.add(comboBox_checkin_hour2);
		
		JPanel panel_review = new JPanel();
		panel_review.setBackground(new Color(240, 248, 255));
		panel_review.setBorder(new LineBorder(new Color(255, 255, 255), 3, true));
		panel_review.setBounds(465, 50, 150, 310);
		panel_review.setLayout(null);
		frmYelp.getContentPane().add(panel_review);
		
		JLabel label_review_from = new JLabel("From");
		label_review_from.setFont(new Font("Segoe UI", Font.BOLD, 12));
		label_review_from.setBounds(5, 30, 60, 15);
		panel_review.add(label_review_from);
		
		JLabel lbl_review_to = new JLabel("To");
		lbl_review_to.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lbl_review_to.setBounds(5, 60, 60, 15);
		panel_review.add(lbl_review_to);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.GRAY);
		separator.setBounds(0, 100, 150, 5);
		panel_review.add(separator);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBackground(Color.GRAY);
		separator_3.setBounds(0, 210, 150, 5);
		panel_review.add(separator_3);
		
		JLabel label_review_star = new JLabel("Star:");
		label_review_star.setFont(new Font("Segoe UI", Font.BOLD, 12));
		label_review_star.setHorizontalAlignment(SwingConstants.LEFT);
		label_review_star.setBounds(5, 130, 60, 15);
		panel_review.add(label_review_star);
		
		JLabel label_review_value = new JLabel("Value:");
		label_review_value.setFont(new Font("Segoe UI", Font.BOLD, 12));
		label_review_value.setHorizontalAlignment(SwingConstants.LEFT);
		label_review_value.setBounds(5, 160, 60, 15);
		panel_review.add(label_review_value);
		
		JLabel label = new JLabel("Votes:");
		label.setFont(new Font("Segoe UI", Font.BOLD, 12));
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setBounds(5, 240, 60, 15);
		panel_review.add(label);
		
		JLabel label_1 = new JLabel("Value:");
		label_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setBounds(5, 270, 60, 15);
		panel_review.add(label_1);
		
		textField_review_value1 = new JTextField();
		textField_review_value1.setBounds(55, 157, 90, 25);
		panel_review.add(textField_review_value1);
		textField_review_value1.setColumns(10);
		textField_review_value1.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
              setBusinessQueryString();
            }
            public void removeUpdate(DocumentEvent e) {
              setBusinessQueryString();
            }
            public void insertUpdate(DocumentEvent e) {
              setBusinessQueryString();
            }
        }); 

		textField_review_value2 = new JTextField();
		textField_review_value2.setColumns(10);
		textField_review_value2.setBounds(55, 267, 90, 25);
		panel_review.add(textField_review_value2);
		textField_review_value2.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
              setBusinessQueryString();
            }
            public void removeUpdate(DocumentEvent e) {
              setBusinessQueryString();
            }
            public void insertUpdate(DocumentEvent e) {
              setBusinessQueryString();
            }
        }); 

		comboBox_review_star = new JComboBox();
		comboBox_review_star.setBackground(new Color(245, 245, 245));
		comboBox_review_star.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//setBusinessQueryString();
			}
		});
		comboBox_review_star.setModel(new DefaultComboBoxModel(new String[] {"=", ">", "<"}));
		comboBox_review_star.setBounds(55, 125, 90, 25);
		panel_review.add(comboBox_review_star);
		
		comboBox_review_votes = new JComboBox();
		comboBox_review_votes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setBusinessQueryString();
			}
		});
		comboBox_review_votes.setModel(new DefaultComboBoxModel(new String[] {"=", ">", "<"}));
		comboBox_review_votes.setBounds(55, 235, 90, 25);
		panel_review.add(comboBox_review_votes);
		
		datePicker_review_from = new JXDatePicker();
		datePicker_review_from.setBackground(new Color(245, 245, 245));
		datePicker_review_from.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setBusinessQueryString();
			}
		});
		datePicker_review_from.getEditor().setEditable(false);
		datePicker_review_from.setFormats(new String[] {"yyyy-MM-dd"});
		datePicker_review_from.setBounds(41, 30, 105, 23);
		panel_review.add(datePicker_review_from);
		
		datePicker_review_to = new JXDatePicker();
		datePicker_review_to.setBackground(new Color(245, 245, 245));
		datePicker_review_to.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setBusinessQueryString();
			}
		});
		datePicker_review_to.getEditor().setEditable(false);
		datePicker_review_to.setFormats(new String[] {"yyyy-MM-dd"});
		datePicker_review_to.setBounds(41, 56, 105, 23);
		panel_review.add(datePicker_review_to);
		
		JPanel panel_users = new JPanel();
		panel_users.setBorder(new LineBorder(new Color(255, 255, 255), 3, true));
		panel_users.setBounds(10, 390, 300, 170);
		panel_users.setBackground(new Color(240, 248, 255));
		frmYelp.getContentPane().add(panel_users);
		panel_users.setLayout(null);
		
		JLabel lbl_user_membersince = new JLabel("Member Since");
		lbl_user_membersince.setBackground(new Color(0, 0, 0));
		lbl_user_membersince.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lbl_user_membersince.setBounds(5, 10, 80, 15);
		panel_users.add(lbl_user_membersince);
		
		JLabel lbl_user_reviewcount = new JLabel("Review Count");
		lbl_user_reviewcount.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lbl_user_reviewcount.setBounds(5, 40, 80, 15);
		panel_users.add(lbl_user_reviewcount);
		
		JLabel lbl_user_numberof = new JLabel("# of Friends");
		lbl_user_numberof.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lbl_user_numberof.setBounds(5, 70, 80, 15);
		panel_users.add(lbl_user_numberof);
		
		JLabel lbl_user_averagestars = new JLabel("Avg stars");
		lbl_user_averagestars.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lbl_user_averagestars.setBounds(5, 100, 80, 15);
		panel_users.add(lbl_user_averagestars);
		
		JLabel lbl_user_select= new JLabel("Select");
		lbl_user_select.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lbl_user_select.setBounds(5, 130, 80, 15);
		panel_users.add(lbl_user_select);
		
		comboBox_user_reviewcount = new JComboBox();
		comboBox_user_reviewcount.setBackground(new Color(245, 245, 245));
		comboBox_user_reviewcount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setUserQueryString();
			}
		});
		
		comboBox_user_reviewcount.setModel(new DefaultComboBoxModel(new String[] {"=", ">", "<"}));
		comboBox_user_reviewcount.setBounds(100, 40, 80, 15);
		panel_users.add(comboBox_user_reviewcount);
		
		comboBox_user_numberoffriend = new JComboBox();
		comboBox_user_numberoffriend.setBackground(new Color(245, 245, 245));
		comboBox_user_numberoffriend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setUserQueryString();
			}
		});
		comboBox_user_numberoffriend.setModel(new DefaultComboBoxModel(new String[] {"=", ">", "<"}));
		comboBox_user_numberoffriend.setBounds(100, 70, 80, 15);
		panel_users.add(comboBox_user_numberoffriend);
		
		comboBox_user_avgstars = new JComboBox();
		comboBox_user_avgstars.setBackground(new Color(245, 245, 245));
		comboBox_user_avgstars.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setUserQueryString();
			}
		});
		comboBox_user_avgstars.setModel(new DefaultComboBoxModel(new String[] {"=", ">", "<"}));
		comboBox_user_avgstars.setBounds(100, 100, 80, 15);
		panel_users.add(comboBox_user_avgstars);
		
		JLabel lbl_user_value1 = new JLabel("Value");
		lbl_user_value1.setBounds(185, 40, 40, 15);
		panel_users.add(lbl_user_value1);
		
		JLabel label_user_value2 = new JLabel("Value");
		label_user_value2.setBounds(185, 70, 40, 15);
		panel_users.add(label_user_value2);
		
		JLabel label_user_value3 = new JLabel("Value");
		label_user_value3.setBounds(185, 100, 40, 15);
		panel_users.add(label_user_value3);
		
		textField_user_value1 = new JTextField();
		textField_user_value1.setBounds(225, 40, 70, 15);
		panel_users.add(textField_user_value1);
		textField_user_value1.setColumns(10);
		textField_user_value1.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
            	setUserQueryString();
            }
            public void removeUpdate(DocumentEvent e) {
            	setUserQueryString();
            }
            public void insertUpdate(DocumentEvent e) {
            	setUserQueryString();
            }
        }); 

		textField_user_value2 = new JTextField();
		textField_user_value2.setColumns(10);
		textField_user_value2.setBounds(225, 70, 70, 15);
		panel_users.add(textField_user_value2);
		textField_user_value2.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
            	setUserQueryString();
            }
            public void removeUpdate(DocumentEvent e) {
            	setUserQueryString();
            }
            public void insertUpdate(DocumentEvent e) {
            	setUserQueryString();
            }
        }); 
		
		textField_user_value3 = new JTextField();
		textField_user_value3.setColumns(10);
		textField_user_value3.setBounds(225, 100, 70, 15);
		panel_users.add(textField_user_value3);
		textField_user_value3.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
            	setUserQueryString();
            }
            public void removeUpdate(DocumentEvent e) {
            	setUserQueryString();
            }
            public void insertUpdate(DocumentEvent e) {
            	setUserQueryString();
            }
        }); 
		
		comboBox_select_andor = new JComboBox();
		comboBox_select_andor.setBackground(new Color(245, 245, 245));
		comboBox_select_andor.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		comboBox_select_andor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setUserQueryString();
			}
		});
		comboBox_select_andor.setModel(new DefaultComboBoxModel(new String[] {"AND", "OR"}));
		comboBox_select_andor.setBounds(100, 130, 190, 20);
		panel_users.add(comboBox_select_andor);	
		
		datePicker_user_membersince = new JXDatePicker();
		datePicker_user_membersince.setBackground(new Color(245, 245, 245));
		datePicker_user_membersince.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setUserQueryString();
			}
		});
		datePicker_user_membersince.getEditor().setEditable(false);
		datePicker_user_membersince.setFormats(new String[] {"yyyy-MM-dd"});
		datePicker_user_membersince.setBounds(95, 6, 200, 23);
		panel_users.add(datePicker_user_membersince);
		
		JLabel lbl_category = new JLabel("Category");
		lbl_category.setForeground(new Color(255, 255, 255));
		lbl_category.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lbl_category.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_category.setBounds(10, 30, 150, 20);
		lbl_category.setBackground(new Color(255, 102, 51));
		lbl_category.setOpaque(true);
		frmYelp.getContentPane().add(lbl_category);
		
		JLabel lblSubcategory = new JLabel("Subcategory");
		lblSubcategory.setBackground(new Color(255, 102, 51));
		lblSubcategory.setForeground(new Color(255, 255, 255));
		lblSubcategory.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblSubcategory.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubcategory.setBounds(160, 30, 150, 20);
		lblSubcategory.setOpaque(true);
		frmYelp.getContentPane().add(lblSubcategory);
		
		JLabel lblCheckin = new JLabel("Checkin");
		lblCheckin.setBackground(new Color(255, 102, 51));
		lblCheckin.setForeground(new Color(255, 255, 255));
		lblCheckin.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheckin.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblCheckin.setBounds(312, 30, 150, 20);
		lblCheckin.setOpaque(true);
		frmYelp.getContentPane().add(lblCheckin);
		
		JLabel lblReview = new JLabel("Review");
		lblReview.setBackground(new Color(255, 102, 51));
		lblReview.setForeground(new Color(255, 255, 255));
		lblReview.setHorizontalAlignment(SwingConstants.CENTER);
		lblReview.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblReview.setBounds(465, 30, 150, 20);
		lblReview.setOpaque(true);
		frmYelp.getContentPane().add(lblReview);
		
		JLabel lblBusiness = new JLabel("Business");
		lblBusiness.setToolTipText("");
		lblBusiness.setForeground(new Color(255, 102, 51));
		lblBusiness.setHorizontalAlignment(SwingConstants.CENTER);
		lblBusiness.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblBusiness.setBounds(10, 10, 300, 20);
		lblBusiness.setBackground(new Color(255, 255, 255));
		frmYelp.getContentPane().add(lblBusiness);
		lblBusiness.setOpaque(true);
		
		JLabel lblUsers = new JLabel("Users");
		lblUsers.setForeground(new Color(255, 69, 0));
		lblUsers.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblUsers.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsers.setBounds(10, 370, 300, 20);
		lblUsers.setBackground(new Color(255, 255, 255));
		lblUsers.setOpaque(true);
		frmYelp.getContentPane().add(lblUsers);
		
		JLabel lblResult = new JLabel("Result");
		lblResult.setBackground(new Color(255, 102, 51));
		lblResult.setForeground(new Color(255, 255, 255));
		lblResult.setHorizontalAlignment(SwingConstants.CENTER);
		lblResult.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblResult.setBounds(618, 30, 554, 20);
		lblResult.setOpaque(true);
		frmYelp.getContentPane().add(lblResult);
		
		JButton Query = new JButton("Query");
		Query.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		Query.setBackground(new Color(255, 255, 255));
		Query.setBounds(312, 370, 303, 20);
		Query.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PreparedStatement pst = connection.prepareStatement(queryString);
					ResultSet rs = pst.executeQuery();

					table_result.setModel(DbUtils.resultSetToTableModel(rs)); 	//DbUtils是rs2xml.jar里提供的class
					
					pst.close();
					rs.close();
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		frmYelp.getContentPane().add(Query);
		
		textArea_query = new JTextArea();
		textArea_query.setBackground(new Color(240, 248, 255));
		scrollPane_query = new JScrollPane(textArea_query);
		scrollPane_query.setBounds(312, 390, 303, 170);
		frmYelp.getContentPane().add(scrollPane_query);

		JScrollPane scrollPane_result = new JScrollPane();
		scrollPane_result.setBounds(618, 50, 554, 510);
		frmYelp.getContentPane().add(scrollPane_result);
		
		table_result = new JTable();
		table_result.setBackground(new Color(240, 248, 255));
		table_result.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		scrollPane_result.setViewportView(table_result);

		scrollPane_subcategory = new JScrollPane(panel_subcategory);
		scrollPane_subcategory.setBounds(160, 50, 150, 310);
		frmYelp.getContentPane().add(scrollPane_subcategory);
		
	
		table_result.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				if (searchBusiness == true) {
					try {
						int row = table_result.getSelectedRow();
						
						String business_id = (table_result.getModel().getValueAt(row, 0)).toString();
						
						//String query = "select review.review_id, review.review_date, review.stars, review.votes review.content from review, business \nWHERE " + "review.business_id = '" + business_id +"'";
						//String query = "select review.content from review, business WHERE " + "review.business_id = '" + "rdAdANPNOcvUtoFgcaY9KA'";
						String query = "select YELPUSER.name, REVIEW.content from YELPUSER,REVIEW \nWHERE " + "review.business_id = '" + business_id +"'" + " AND " + "YELPUSER.user_id = " + "REVIEW.user_id";
						JOptionPane.showMessageDialog(null,  query);
						
						
						Statement pst = connection.createStatement();
	
						pst.executeQuery(query);
						
						ResultSet rs = pst.getResultSet();
						
						String result = "";
						int review_number = 0;
						
						if (rs != null) {
							while(rs.next()) {
								review_number++;
								//JOptionPane.showMessageDialog(null,  query);
								result += "USER NAME:      " + rs.getString(1) +"\n";
								result += "REVIEW CONTENT: " + rs.getString(2) + "\n";
								//JOptionPane.showMessageDialog(null, rs.getString(1) + " " + rs.getString(2) + "" + rs.getDouble(3) + " " + rs.getInt(4));
							}
						}
						
						String show_result = "There are  " + review_number + "  records of reviews of this business.\n\n";
						show_result += result;
						JOptionPane.showMessageDialog(null,  show_result);
						pst.close();
						rs.close();
						
					} catch(Exception e2) {
						e2.printStackTrace();
					}
				} else {
					try {
						int row = table_result.getSelectedRow();
						
						String user_id = (table_result.getModel().getValueAt(row, 0)).toString();
						
						//String query = "select review.review_id, review.review_date, review.stars, review.votes review.content from review, business \nWHERE " + "review.business_id = '" + business_id +"'";
						//String query = "select review.content from review, business WHERE " + "review.business_id = '" + "rdAdANPNOcvUtoFgcaY9KA'";
						String query = "select review.content from review \nWHERE " + "review.user_id = '" + user_id +"'";
						JOptionPane.showMessageDialog(null,  query);
						
						Statement pst = connection.createStatement();
	
						pst.executeQuery(query);
						
						ResultSet rs = pst.getResultSet();
						
						String result = "";
						
						if (rs != null) {
							while(rs.next()) {
								result += rs.getString(1) +"\n";
							}
						}

						JOptionPane.showMessageDialog(null,  result);
						pst.close();
						rs.close();
						
					} catch(Exception e2) {
						e2.printStackTrace();
					}
					
					
				}
		
			}
		});
	}
	
    public static boolean isNumeric(String str)  
    {  
        if(str.equals("") || str == null)
            return false;
        try {  
          Integer d = Integer.parseInt(str);  
        }  
        catch(NumberFormatException | NullPointerException e) {  
          return false;  
        }  
        return true;  
    }
    
	private void setBusinessQueryString() {
		searchBusiness = true;
		int criteriaCount = 0;

		queryString = "SELECT DISTINCT BUSINESS.business_id, BUSINESS.name, BUSINESS.city, BUSINESS.state, BUSINESS.city, BUSINESS.stars FROM BUSINESS\n";
        
        if((comboBox_checkin_day1.getSelectedIndex() > 0 && comboBox_checkin_hour1.getSelectedIndex() > 0) ||
        		(comboBox_checkin_day2.getSelectedIndex() > 0 && comboBox_checkin_hour2.getSelectedIndex() > 0) ||
        		isNumeric(textField_checkin_value.getText())) {
             	queryString += "JOIN CHECKIN ON BUSINESS.business_id = CHECKIN.business_id " + "\n"; 
        }
        
        if(datePicker_review_from.getEditor().getValue() != null || 
        		datePicker_review_to.getEditor().getValue() != null ||
        		isNumeric(textField_review_value1.getText()) ||
        		isNumeric(textField_review_value2.getText())) {
            queryString += "JOIN REVIEW ON BUSINESS.business_id = REVIEW.business_id " + "\n"; 
        }
        
        for(int i = 0; i < categories.size(); i++) {
            if(categories.get(i).isSelected()) {
                queryString += "JOIN CATEGORY ON BUSINESS.business_id = CATEGORY.business_id " + "\n";
                break;
            }
        }
        
        for(int i = 0; i < subcategories.size(); i++) {
            if(subcategories.get(i).isSelected()) {
                queryString += "JOIN SUBCATEGORY ON Business.business_id = SUBCATEGORY.business_id " + "\n";
                break;
            }
        }
		
        for(int i = 0; i < categories.size(); i++) {
            if(categories.get(i).isSelected()) {
                if(criteriaCount > 0) {
                    queryString += " OR\n ";
                } else {
                    queryString += " WHERE\n ";
                }
                queryString += "CATEGORY.name = '" + categories.get(i).getText() + "'" + "\n";
                criteriaCount++;
            }
        }
        
        for(int i = 0; i < subcategories.size(); i++) {
            if(subcategories.get(i).isSelected()) {
                if(criteriaCount > 0) {
                    queryString += " OR\n ";
                } else {
                    queryString += " WHERE\n ";
                }
                queryString += "SUBCATEGORY.name = '" + subcategories.get(i).getText() + "'" + "\n";
                criteriaCount++;
            }
        }
        
        if(comboBox_checkin_day1.getSelectedIndex() > 0 && comboBox_checkin_day2.getSelectedIndex() > 0 &&
        		comboBox_checkin_hour1.getSelectedIndex() > 0 && comboBox_checkin_hour2.getSelectedIndex() > 0) {
            if(criteriaCount > 0) {
                queryString += " AND \n";
            } else {
                queryString += " WHERE \n";
            }
            
            int from_day = comboBox_checkin_day1.getSelectedIndex() - 1;	//0,1,2,3,4,5,6
            int to_day = comboBox_checkin_day2.getSelectedIndex() - 1;
            int from_hour = comboBox_checkin_hour1.getSelectedIndex() - 1;	// 0,1,2,3,...23
            int to_hour = comboBox_checkin_hour2.getSelectedIndex() - 1;
            
            queryString += "(CHECKIN.time_slot >= " + "'" + from_hour + "-"  + from_day + "'" + " AND CHECKIN.time_slot <= "+ "'" + to_hour + "-"  + to_day + "'" +")" +"\n";
            
            criteriaCount++;
        }
        
        if(isNumeric(textField_checkin_value.getText())) {
            if(criteriaCount > 0)
                queryString += " AND \n";
            else
                queryString += " WHERE \n";
            
            queryString += " CHECKIN.count " + comboBox_checkin_numberofcheckin.getSelectedItem().toString() + textField_checkin_value.getText() +"\n";
            criteriaCount++;
        }
        
        if(datePicker_review_from.getEditor().getValue() != null && 
                datePicker_review_to.getEditor().getValue() != null) {
            if(criteriaCount > 0) {
                queryString += " AND \n";
            } else {
                queryString += " WHERE \n";
            }
            
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            queryString += "(review_date >= '" + formater.format(datePicker_review_from.getDate()) + "' AND " 
                    + "review_date <= '" + formater.format(datePicker_review_to.getDate()) + "')";
            criteriaCount++;
        } else if (datePicker_review_from.getEditor().getValue() != null) {
            if(criteriaCount > 0) {
                queryString += " AND \n";
            }  else {
                queryString += " WHERE \n";
            }
            
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            queryString += "review_date >= '" + formater.format(datePicker_review_from.getDate()) + "'" +"\n";
            criteriaCount++;
        } else if (datePicker_review_to.getEditor().getValue() != null) {
            if(criteriaCount > 0) {
                queryString += " AND \n";
            }  else {
                queryString += " WHERE \n";
            }
            
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            queryString += "review_date <= '" + formater.format(datePicker_review_to.getDate()) + "'" +"\n";
            criteriaCount++;
        }
        
        if(isNumeric(textField_review_value1.getText())) {
            if(criteriaCount > 0) {
                queryString += " AND \n ";
            } else {
                queryString += " WHERE \n";
            }            
            queryString += "REVIEW.stars " + comboBox_review_star.getSelectedItem().toString() + textField_review_value1.getText() + "\n";
            criteriaCount++;
        }
        
        if(isNumeric(textField_review_value2.getText())) {
            if(criteriaCount > 0) {
                queryString += " AND \n ";
            } else {
                queryString += " WHERE \n";
            }            
            queryString += "REVIEW.votes " + comboBox_review_votes.getSelectedItem().toString() + textField_review_value2.getText() + "\n";
            criteriaCount++;
        }
        
		textArea_query.setText(queryString);
		
	}
	
    private void setUserQueryString() {
    	
        int criteriaCount = 0;
        String selector = comboBox_select_andor.getSelectedItem().toString();
        searchBusiness = false;
        queryString = "SELECT * FROM YELPUSER " + "\n";
        
        if(datePicker_user_membersince.getEditor().getValue() != null) {
            SimpleDateFormat formater = new SimpleDateFormat("YYYY-MM");
            queryString += "WHERE yelping_since >= '" + formater.format(datePicker_user_membersince.getDate()) + "'" +"\n";
            criteriaCount++;
        }
        
        if(isNumeric(textField_user_value1.getText())) {
            if(criteriaCount > 0)
                queryString += " " + selector + " " + "\n";
            else
                queryString += "WHERE " + "\n";
            
                queryString += " review_count " + comboBox_user_reviewcount.getSelectedItem().toString() + textField_user_value1.getText() ;
            criteriaCount++;
        }
        
        if(isNumeric(textField_user_value2.getText())) {
            if(criteriaCount > 0)
                queryString += " " + selector + " " + "\n";
            else
                queryString += "WHERE " + "\n";
            
            queryString += " friend_count " + comboBox_user_numberoffriend.getSelectedItem().toString() + textField_user_value2.getText() ;
            criteriaCount++;
        }    
        
        if(isNumeric(textField_user_value3.getText())) {
            if(criteriaCount > 0)
                queryString += " " + selector + " " + "\n";
            else
                queryString += "WHERE " + "\n";
            
            queryString += " average_stars " + comboBox_user_avgstars.getSelectedItem().toString() + textField_user_value3.getText() ;
            criteriaCount++;
        }
        
        textArea_query.setText(queryString);
        subcategories.clear();
    
    }
    
    private void setSubcategory() {
    	panel_subcategory.removeAll();
    	subcategorynames.clear();
  
    	categorynames.clear();
    	subcategorynames.clear();

        String query = null;
       
        try {
        	Statement pst = connection.createStatement();
        } catch(Exception e2) {
        	e2.printStackTrace();
        }
        
        // get category name which is selected
        for(int i = 0; i < categories.size(); i++) {
            if(categories.get(i).isSelected()) {
            	categorynames.add(categories.get(i).getText());
            }
        }
        
        //  get subcategory name
        if (categorynames.size() > 0) {
        	query = "select DISTINCT SUBCATEGORY.name from SUBCATEGORY, CATEGORY \n";
	        for(int i = 0; i < categorynames.size(); i++) {
	        	if (i == 0) {
		        	query += "WHERE ";
	        	} else {
		        	query += "OR ";
	        	}
	        	
	        	query += "CATEGORY.business_id = SUBCATEGORY.business_id AND\n";
	        	query += "CATEGORY.name =" + "'" + categorynames.get(i) + "'" + "\n";
	        }
	        
	        query += "ORDER by SUBCATEGORY.name\n";
	    	//JOptionPane.showMessageDialog(null,  query);
            try {
            	Statement pst = connection.createStatement();
    	    	pst.executeQuery(query);
    	    	ResultSet rs = pst.getResultSet();
    	    	String result = null;
    	    	if (rs != null) {
    	    		while(rs.next()) {
    	    			subcategorynames.add(rs.getString(1));
    	    		}
    	    	}
            } catch(Exception e2) {
            	e2.printStackTrace();
            }
            
            //  add subcategory checkbox
            if (subcategorynames.size() > 0) {
            	for (int i = 0 ; i < subcategorynames.size(); i++) {
            		//String temp = subcategorynames.get(i);
            		//System.out.println(temp);
            		subcategories.add(new JCheckBox(subcategorynames.get(i)));
        			panel_subcategory.add(subcategories.get(i));
        			subcategories.get(i).setBackground(new Color(240, 248, 255));
        			panel_subcategory.revalidate();
        			panel_subcategory.repaint();
       		 	}
            }
            
    		for (int i = 0; i < subcategories.size(); i++) {
    			subcategories.get(i).addActionListener(new ActionListener() {
    				public void actionPerformed(ActionEvent arg0) {
    					setBusinessQueryString();
    				}
    			});
    		}
        }
    }
}