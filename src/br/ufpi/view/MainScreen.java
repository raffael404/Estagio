package br.ufpi.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;

import br.ufpi.dao.DatabaseInteraction;
import br.ufpi.exception.CommunicationErrorException;
import br.ufpi.model.Register;

import javax.swing.DefaultListModel;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;

public class MainScreen {

	private JFrame frmOcsSoftwareDetection;
	private JTable tableSearch;
	private DefaultTableModel tableModelSearch = new DefaultTableModel();
	private DatabaseInteraction database;
	private JTextField textFieldInsertSoftware;
	private JList<String> listSoftware;
	private DefaultListModel<String> listModelSoftware;
	
	/**
	 * Create the application.
	 */
	public MainScreen(DatabaseInteraction database) {
		this.database = database;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmOcsSoftwareDetection = new JFrame();
		frmOcsSoftwareDetection.setTitle("OCS Software Detection");
		frmOcsSoftwareDetection.setBounds(100, 100, 500, 400);
		frmOcsSoftwareDetection.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmOcsSoftwareDetection.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 10, 464, 340);
		frmOcsSoftwareDetection.getContentPane().add(tabbedPane);
		
		JPanel panelSearch = new JPanel();
		tabbedPane.addTab("Busca", null, panelSearch, null);
		tableModelSearch.addColumn("TAG");
		tableModelSearch.addColumn("Usuário");
		tableModelSearch.addColumn("IP");
		tableModelSearch.addColumn("Software");
		panelSearch.setLayout(null);
		
		JScrollPane scrollPaneSearch = new JScrollPane();
		scrollPaneSearch.setBounds(10, 46, 439, 255);
		panelSearch.add(scrollPaneSearch);
		
		tableSearch = new JTable(tableModelSearch);
		tableSearch.setBounds(183, 16, 0, 0);
		tableSearch.setEnabled(false);
		tableSearch.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		scrollPaneSearch.setViewportView(tableSearch);
		
		JButton btnStartSearch = new JButton("Iniciar busca");
		btnStartSearch.setBounds(10, 11, 93, 23);
		panelSearch.add(btnStartSearch);
		
		JButton btnExit = new JButton("Sair");
		btnExit.setBounds(360, 11, 89, 23);
		panelSearch.add(btnExit);
		
		JPanel panelSoftware = new JPanel();
		tabbedPane.addTab("Software", null, panelSoftware, null);
		panelSoftware.setLayout(null);
		
		JScrollPane scrollPaneSoftware = new JScrollPane();
		scrollPaneSoftware.setBounds(10, 45, 439, 256);
		panelSoftware.add(scrollPaneSoftware);
		
		JButton btnListSofware = new JButton("Listar");
		btnListSofware.setBounds(10, 11, 89, 23);
		panelSoftware.add(btnListSofware);
		
		listModelSoftware = new DefaultListModel<String>();
		
		listSoftware = new JList<String>(listModelSoftware);
		scrollPaneSoftware.setViewportView(listSoftware);
		
		JButton btnDeleteSoftware = new JButton("Apagar");
		btnDeleteSoftware.setBounds(360, 11, 89, 23);
		panelSoftware.add(btnDeleteSoftware);
		
		textFieldInsertSoftware = new JTextField();
		textFieldInsertSoftware.setBounds(109, 12, 142, 20);
		panelSoftware.add(textFieldInsertSoftware);
		textFieldInsertSoftware.setColumns(10);
		
		JButton btnInsertSoftware = new JButton("Inserir");
		btnInsertSoftware.setBounds(261, 11, 89, 23);
		panelSoftware.add(btnInsertSoftware);
		
		btnStartSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					List<Register> registers = database.getRegisters();
					for (Register register : registers) {
						tableModelSearch.addRow(new Object[]{register.getTag(), register.getUser(), register.getIp(), register.getSoftware()});
					}
				} catch (CommunicationErrorException e1) {
					error(e1.getMessage());
					e1.printStackTrace();
				}
				
			}
		});
		
		btnListSofware.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					updateSoftwareList();
				} catch (CommunicationErrorException e1) {
					error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		
		btnInsertSoftware.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (textFieldInsertSoftware.getText() != null) {
					try {
						if(!database.getSoftwares().contains(textFieldInsertSoftware.getText())){
							database.insertSoftware(textFieldInsertSoftware.getText());
							updateSoftwareList();
						}else error("Software já existe!");
					} catch (CommunicationErrorException e1) {
						error(e1.getMessage());
						e1.printStackTrace();
					}
				}else error("Campo vazio!");
			}
		});
		
		btnDeleteSoftware.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Verificar como fica para strings compostas
				try {
					String software = listSoftware.getSelectedValue();
					database.deleteSoftware(software);
					updateSoftwareList();
				} catch (CommunicationErrorException e1) {
					error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		
		btnExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					database.closeConnection();
					frmOcsSoftwareDetection.dispose();
					System.exit(0);
				} catch (CommunicationErrorException e1) {
					error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
	}
	
	public JFrame getFrame(){
		return this.frmOcsSoftwareDetection;
	}
	
	public void error(String message){
		ErrorScreen error = new ErrorScreen(message);
		error.getFrame().setVisible(true);
	}
	
	public void updateSoftwareList() throws CommunicationErrorException{
		listModelSoftware.clear();
		List<String> softwares = database.getSoftwares();
		for (String software : softwares) {
			listModelSoftware.addElement(software);
		}
	}
}
