package br.ufpi.view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import br.ufpi.dao.DatabaseInteraction;

public class MainScreen {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public MainScreen(DatabaseInteraction database) {
		initialize(database);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(DatabaseInteraction database) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public JFrame getFrame(){
		return this.frame;
	}
}
