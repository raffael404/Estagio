package br.ufpi.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ErrorScreen {

	private JFrame frmErro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ErrorScreen window = new ErrorScreen();
					window.frmErro.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ErrorScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmErro = new JFrame();
		frmErro.setResizable(false);
		frmErro.setTitle("Erro");
		frmErro.setType(Type.POPUP);
		frmErro.setBounds(100, 100, 255, 105);
		frmErro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmErro.getContentPane().setLayout(null);
		
		JLabel lblErroDeConexo = new JLabel("Erro de conex\u00E3o com o banco de dados!");
		lblErroDeConexo.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblErroDeConexo.setBounds(10, 11, 253, 14);
		frmErro.getContentPane().add(lblErroDeConexo);
		
		JButton btnOk = new JButton("OK");
		btnOk.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar() == '\n')
					frmErro.dispose();
			}
		});
		btnOk.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnOk.setBounds(75, 36, 99, 31);
		frmErro.getContentPane().add(btnOk);
		
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frmErro.dispose();
			}
		});
	}
	
	public JFrame getFrame(){
		return frmErro;
	}
}
