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

import org.eclipse.wb.swing.FocusTraversalOnArray;

import br.ufpi.dao.DatabaseInteraction;
import br.ufpi.exception.DatabaseConnectionException;

import java.awt.Component;

public class LoginScreen {

	private JFrame frmLogin;
	private JTextField textFieldOCSUser;
	private JPasswordField passwordFieldOCSPassword;
	private JTextField textFieldMyUser;
	private JPasswordField passwordFieldMyPassword;
	private JLabel lblOCSUser;
	private JLabel lblOCSPassword;
	private JLabel lblMyPassword;
	private JLabel lblMyUser;
	private JPanel panelOCSDatabase;
	private JPanel panelMyDatabase;
	private JLabel lblOCSServer;
	private JTextField textFieldOCSServer;
	private JTextField textFieldMyServer;
	private JLabel lblMyServer;

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
		frmLogin.setBounds(100, 100, 430, 260);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		passwordFieldOCSPassword = new JPasswordField();
		passwordFieldOCSPassword.setHorizontalAlignment(SwingConstants.CENTER);
		passwordFieldOCSPassword.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		passwordFieldOCSPassword.setColumns(10);
		passwordFieldOCSPassword.setBounds(91, 156, 86, 20);
		frmLogin.getContentPane().add(passwordFieldOCSPassword);
		
		textFieldMyUser = new JTextField();
		textFieldMyUser.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldMyUser.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textFieldMyUser.setColumns(10);
		textFieldMyUser.setBounds(302, 102, 86, 20);
		frmLogin.getContentPane().add(textFieldMyUser);
		
		passwordFieldMyPassword = new JPasswordField();
		passwordFieldMyPassword.setHorizontalAlignment(SwingConstants.CENTER);
		passwordFieldMyPassword.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		passwordFieldMyPassword.setColumns(10);
		passwordFieldMyPassword.setBounds(302, 156, 86, 20);
		frmLogin.getContentPane().add(passwordFieldMyPassword);
		
		lblOCSUser = new JLabel("Usu\u00E1rio");
		lblOCSUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblOCSUser.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblOCSUser.setBounds(10, 99, 71, 20);
		frmLogin.getContentPane().add(lblOCSUser);
		
		lblOCSPassword = new JLabel("Senha");
		lblOCSPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblOCSPassword.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblOCSPassword.setBounds(10, 155, 71, 22);
		frmLogin.getContentPane().add(lblOCSPassword);
		
		lblMyPassword = new JLabel("Senha");
		lblMyPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblMyPassword.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblMyPassword.setBounds(221, 155, 71, 22);
		frmLogin.getContentPane().add(lblMyPassword);
		
		lblMyUser = new JLabel("Usu\u00E1rio");
		lblMyUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblMyUser.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblMyUser.setBounds(221, 99, 71, 20);
		frmLogin.getContentPane().add(lblMyUser);
		
		panelOCSDatabase = new JPanel();
		panelOCSDatabase.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Banco de Dados OCS", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelOCSDatabase.setBounds(10, 11, 193, 183);
		frmLogin.getContentPane().add(panelOCSDatabase);
		panelOCSDatabase.setLayout(null);
		
		lblOCSServer = new JLabel("Servidor");
		lblOCSServer.setHorizontalAlignment(SwingConstants.CENTER);
		lblOCSServer.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblOCSServer.setBounds(0, 35, 71, 20);
		panelOCSDatabase.add(lblOCSServer);
		
		textFieldOCSServer = new JTextField();
		textFieldOCSServer.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldOCSServer.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textFieldOCSServer.setColumns(10);
		textFieldOCSServer.setBounds(80, 35, 86, 20);
		panelOCSDatabase.add(textFieldOCSServer);
		
		textFieldOCSUser = new JTextField();
		textFieldOCSUser.setBounds(80, 88, 86, 20);
		panelOCSDatabase.add(textFieldOCSUser);
		textFieldOCSUser.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldOCSUser.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textFieldOCSUser.setColumns(10);
		
		panelMyDatabase = new JPanel();
		panelMyDatabase.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Meu Banco de Dados", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelMyDatabase.setBounds(212, 11, 193, 183);
		frmLogin.getContentPane().add(panelMyDatabase);
		panelMyDatabase.setLayout(null);
		
		textFieldMyServer = new JTextField();
		textFieldMyServer.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldMyServer.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textFieldMyServer.setColumns(10);
		textFieldMyServer.setBounds(90, 34, 86, 20);
		panelMyDatabase.add(textFieldMyServer);
		
		lblMyServer = new JLabel("Servidor");
		lblMyServer.setHorizontalAlignment(SwingConstants.CENTER);
		lblMyServer.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblMyServer.setBounds(10, 34, 71, 20);
		panelMyDatabase.add(lblMyServer);
		
		JCheckBox chckbxRememberLogin = new JCheckBox("Lembrar dados de login");
		chckbxRememberLogin.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		chckbxRememberLogin.setBounds(21, 201, 173, 23);
		frmLogin.getContentPane().add(chckbxRememberLogin);
		
		JButton btnDoLogin = new JButton("Fazer login");
		btnDoLogin.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnDoLogin.setBounds(246, 200, 130, 23);
		frmLogin.getContentPane().add(btnDoLogin);
		frmLogin.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{textFieldOCSServer, textFieldOCSUser, passwordFieldOCSPassword, textFieldMyServer, textFieldMyUser, passwordFieldMyPassword, chckbxRememberLogin, btnDoLogin}));
		
		btnDoLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String OCSServer, OCSUser, OCSPassword, MyServer, MyUser, MyPassword;
				OCSServer = textFieldOCSServer.getText();
				OCSUser = textFieldOCSUser.getText();
				OCSPassword = passwordFieldOCSPassword.getPassword().toString();
				MyServer = textFieldMyServer.getText();
				MyUser = textFieldMyUser.getText();
				MyPassword = passwordFieldMyPassword.getPassword().toString();
				
				try {
					DatabaseInteraction database = new DatabaseInteraction(OCSServer, "ocsweb", OCSUser, OCSPassword, MyServer, "software_detection", MyUser, MyPassword);
					MainScreen main = new MainScreen(database);
					main.getFrame().setVisible(true);
					frmLogin.setVisible(false);
				} catch (DatabaseConnectionException e1) {
					ErrorScreen error = new ErrorScreen();
					error.getFrame().setVisible(true);
					e1.printStackTrace();
				}
				
				
				
			}
		});
		
	}
}
