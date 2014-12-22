package br.ufpi.view;

import javax.swing.JFrame;

import br.ufpi.dao.DatabaseInteraction;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class MainScreen {

	private JFrame frame;
	private JTable tableSearch;
	private DefaultTableModel tableModelSearch = new DefaultTableModel();

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
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 10, 414, 241);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panelSearch = new JPanel();
		tabbedPane.addTab("Busca", null, panelSearch, null);
		
		tableSearch = new JTable(tableModelSearch);
		panelSearch.add(tableSearch);
		tableSearch.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		tableModelSearch.addColumn("TAG");
		tableModelSearch.addColumn("Usuário");
		tableModelSearch.addColumn("IP");
		tableModelSearch.addColumn("Software");
		
		JPanel panelSoftware = new JPanel();
		tabbedPane.addTab("Software", null, panelSoftware, null);
	}
	
	public JFrame getFrame(){
		return this.frame;
	}
}
