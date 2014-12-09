package br.ufpi.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JButton;

public class LoginScreen {

	private JFrame frmLogin;
	private JTextField textFieldOCSDatabaseUser;
	private JPasswordField passwordFieldOCSDatabasePassword;
	private JTextField textFieldMyDatabaseUser;
	private JPasswordField passwordFieldMyDatabasePassword;
	private JLabel lblOCSDatabaseUser;
	private JLabel lblOCSDatabasePassword;
	private JLabel lblMyDatabasePassword;
	private JLabel lblMyDatabaseUser;
	private JPanel panelOCSDatabase;
	private JPanel panelMyDatabase;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen window = new LoginScreen();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		frmLogin.setResizable(false);
		frmLogin.setBounds(100, 100, 430, 210);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		textFieldOCSDatabaseUser = new JTextField();
		textFieldOCSDatabaseUser.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldOCSDatabaseUser.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textFieldOCSDatabaseUser.setBounds(91, 43, 86, 20);
		frmLogin.getContentPane().add(textFieldOCSDatabaseUser);
		textFieldOCSDatabaseUser.setColumns(10);
		
		passwordFieldOCSDatabasePassword = new JPasswordField();
		passwordFieldOCSDatabasePassword.setHorizontalAlignment(SwingConstants.CENTER);
		passwordFieldOCSDatabasePassword.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		passwordFieldOCSDatabasePassword.setColumns(10);
		passwordFieldOCSDatabasePassword.setBounds(91, 100, 86, 20);
		frmLogin.getContentPane().add(passwordFieldOCSDatabasePassword);
		
		textFieldMyDatabaseUser = new JTextField();
		textFieldMyDatabaseUser.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldMyDatabaseUser.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textFieldMyDatabaseUser.setColumns(10);
		textFieldMyDatabaseUser.setBounds(302, 46, 86, 20);
		frmLogin.getContentPane().add(textFieldMyDatabaseUser);
		
		passwordFieldMyDatabasePassword = new JPasswordField();
		passwordFieldMyDatabasePassword.setHorizontalAlignment(SwingConstants.CENTER);
		passwordFieldMyDatabasePassword.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		passwordFieldMyDatabasePassword.setColumns(10);
		passwordFieldMyDatabasePassword.setBounds(302, 100, 86, 20);
		frmLogin.getContentPane().add(passwordFieldMyDatabasePassword);
		
		lblOCSDatabaseUser = new JLabel("Usu\u00E1rio");
		lblOCSDatabaseUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblOCSDatabaseUser.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblOCSDatabaseUser.setBounds(10, 43, 71, 20);
		frmLogin.getContentPane().add(lblOCSDatabaseUser);
		
		lblOCSDatabasePassword = new JLabel("Senha");
		lblOCSDatabasePassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblOCSDatabasePassword.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblOCSDatabasePassword.setBounds(10, 99, 71, 22);
		frmLogin.getContentPane().add(lblOCSDatabasePassword);
		
		lblMyDatabasePassword = new JLabel("Senha");
		lblMyDatabasePassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblMyDatabasePassword.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblMyDatabasePassword.setBounds(221, 99, 71, 22);
		frmLogin.getContentPane().add(lblMyDatabasePassword);
		
		lblMyDatabaseUser = new JLabel("Usu\u00E1rio");
		lblMyDatabaseUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblMyDatabaseUser.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblMyDatabaseUser.setBounds(221, 43, 71, 20);
		frmLogin.getContentPane().add(lblMyDatabaseUser);
		
		panelOCSDatabase = new JPanel();
		panelOCSDatabase.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Banco de Dados OCS", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelOCSDatabase.setBounds(10, 11, 193, 132);
		frmLogin.getContentPane().add(panelOCSDatabase);
		
		panelMyDatabase = new JPanel();
		panelMyDatabase.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Meu Banco de Dados", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelMyDatabase.setBounds(212, 11, 193, 132);
		frmLogin.getContentPane().add(panelMyDatabase);
		
		JCheckBox chckbxRememberLogin = new JCheckBox("Lembrar dados de login");
		chckbxRememberLogin.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		chckbxRememberLogin.setBounds(20, 150, 173, 23);
		frmLogin.getContentPane().add(chckbxRememberLogin);
		
		JButton btnDoLogin = new JButton("Fazer login");
		btnDoLogin.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnDoLogin.setBounds(245, 149, 130, 23);
		frmLogin.getContentPane().add(btnDoLogin);
		
		btnDoLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String OCSDatabaseUser, OCSDatabasePassword, MyDatabaseUser, MyDatabasePassword;
				OCSDatabaseUser = textFieldOCSDatabaseUser.getText();
				OCSDatabasePassword = passwordFieldOCSDatabasePassword.getPassword().toString();
				MyDatabaseUser = textFieldMyDatabaseUser.getText();
				MyDatabasePassword = passwordFieldMyDatabasePassword.getPassword().toString();
				
			}
		});
		
	}
}
