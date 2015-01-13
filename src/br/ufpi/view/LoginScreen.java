package br.ufpi.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import br.ufpi.dao.DatabaseInteraction;
import br.ufpi.dao.LoginData;
import br.ufpi.exception.CommunicationErrorException;
import br.ufpi.model.Login;

public class LoginScreen {

	private JFrame frmLogin;
	
	private JTextField textFieldServer;
	private JTextField textFieldUser;
	private JPasswordField passwordFieldPassword;
	
	private JLabel lblServer;
	private JLabel lblUser;
	private JLabel lblPassword;
	
	private JPanel panelDatabase;
	
	private JCheckBox chckbxRememberLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen window = new LoginScreen();
					window.frmLogin.setVisible(true);
					if (window.chckbxRememberLogin.isSelected())
						window.passwordFieldPassword.requestFocus();
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
		frmLogin.setBounds(100, 100, 220, 235);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		panelDatabase = new JPanel();
		panelDatabase.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Banco de Dados OCS", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelDatabase.setBounds(10, 11, 193, 125);
		frmLogin.getContentPane().add(panelDatabase);
		panelDatabase.setLayout(null);
		
		lblServer = new JLabel("Servidor");
		lblServer.setHorizontalAlignment(SwingConstants.CENTER);
		lblServer.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblServer.setBounds(-1, 23, 71, 20);
		panelDatabase.add(lblServer);
		
		textFieldServer = new JTextField();
		textFieldServer.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldServer.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textFieldServer.setColumns(10);
		textFieldServer.setBounds(80, 23, 86, 20);
		panelDatabase.add(textFieldServer);
		
		textFieldUser = new JTextField();
		textFieldUser.setBounds(79, 54, 86, 20);
		panelDatabase.add(textFieldUser);
		textFieldUser.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldUser.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textFieldUser.setColumns(10);
		
		lblUser = new JLabel("Usu\u00E1rio");
		lblUser.setBounds(-1, 54, 71, 20);
		panelDatabase.add(lblUser);
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		
		lblPassword = new JLabel("Senha");
		lblPassword.setBounds(-1, 85, 71, 22);
		panelDatabase.add(lblPassword);
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		
		passwordFieldPassword = new JPasswordField();
		passwordFieldPassword.setBounds(79, 85, 86, 20);
		panelDatabase.add(passwordFieldPassword);
		passwordFieldPassword.setHorizontalAlignment(SwingConstants.CENTER);
		passwordFieldPassword.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		passwordFieldPassword.setColumns(10);
		
		
		chckbxRememberLogin = new JCheckBox("Lembrar dados de login");
		chckbxRememberLogin.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		chckbxRememberLogin.setBounds(20, 143, 173, 23);
		frmLogin.getContentPane().add(chckbxRememberLogin);
		
		JButton btnDoLogin = new JButton("Fazer login");
		btnDoLogin.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnDoLogin.setBounds(40, 173, 130, 23);
		frmLogin.getContentPane().add(btnDoLogin);
		
		frmLogin.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{textFieldServer, textFieldUser, passwordFieldPassword, chckbxRememberLogin, btnDoLogin}));
		frmLogin.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{textFieldServer, textFieldUser, passwordFieldPassword, chckbxRememberLogin, btnDoLogin}));
		
		btnDoLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		
		
		passwordFieldPassword.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar() == '\n'){
					login();
				}
			}
			
		});
		
		try {
			File file = new File("loginData.tmp");
			if(file.exists()){
				Login login;
				login = LoginData.charge();
				textFieldServer.setText(login.getServer());
				textFieldUser.setText(login.getUser());
				chckbxRememberLogin.setSelected(true);
			}
		} catch (CommunicationErrorException e1) {
			JOptionPane.showMessageDialog(this.frmLogin, e1.getMessage());
		}
		
	}
	
	/**
	 * Performs the authentication
	 */
	public void login(){
		String server, user, password;
		server = textFieldServer.getText();
		user = textFieldUser.getText();
		password = String.valueOf(passwordFieldPassword.getPassword());
		
		try {
			DatabaseInteraction database = new DatabaseInteraction(server, "ocsweb", user, password, server, "software_detection", user, password);
			if (chckbxRememberLogin.isSelected()){
				LoginData.save(new Login(server, user));
			}
			MainScreen main = new MainScreen(database);
			main.getFrame().setVisible(true);
			frmLogin.setVisible(false);
		} catch (CommunicationErrorException e1) {
			JOptionPane.showMessageDialog(this.frmLogin, e1.getMessage());			
		}
	}
}
