package br.ufpi.view;

import java.awt.Font;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ErrorScreen {

	private JFrame frmErro;

	/**
	 * Create the application.
	 */
	public ErrorScreen(String message) {
		initialize(message);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String message) {
		frmErro = new JFrame();
		frmErro.setResizable(false);
		frmErro.setTitle("Erro");
		frmErro.setType(Type.POPUP);
		frmErro.setBounds(100, 100, 400, 120);
		frmErro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmErro.getContentPane().setLayout(null);
		
		JLabel lblErroDeConexo = new JLabel(message);
		lblErroDeConexo.setHorizontalAlignment(SwingConstants.CENTER);
		lblErroDeConexo.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblErroDeConexo.setBounds(10, 11, 374, 27);
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
		btnOk.setBounds(146, 49, 99, 31);
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
