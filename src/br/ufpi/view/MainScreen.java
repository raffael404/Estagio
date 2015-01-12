package br.ufpi.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import br.ufpi.dao.DatabaseInteraction;
import br.ufpi.exception.CommunicationErrorException;
import br.ufpi.model.Register;

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
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		JPanel panelSearch = new JPanel();
		tabbedPane.addTab("Busca", null, panelSearch, null);
		tableModelSearch.addColumn("TAG");
		tableModelSearch.addColumn("Usuário");
		tableModelSearch.addColumn("IP");
		tableModelSearch.addColumn("Software");
		
		JScrollPane scrollPaneSearch = new JScrollPane();
		
		tableSearch = new JTable(tableModelSearch);
		tableSearch.setBounds(183, 16, 0, 0);
		tableSearch.setEnabled(false);
		tableSearch.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		scrollPaneSearch.setViewportView(tableSearch);
		
		JButton btnStartSearch = new JButton("Buscar");
		
		JButton btnExit = new JButton("Sair");
		
		JButton btnExport = new JButton("Exportar");
		GroupLayout gl_panelSearch = new GroupLayout(panelSearch);
		gl_panelSearch.setHorizontalGroup(
			gl_panelSearch.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSearch.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panelSearch.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelSearch.createSequentialGroup()
							.addComponent(btnStartSearch, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnExport, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
							.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPaneSearch, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE))
					.addGap(10))
		);
		gl_panelSearch.setVerticalGroup(
			gl_panelSearch.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSearch.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_panelSearch.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panelSearch.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnStartSearch)
							.addComponent(btnExport))
						.addComponent(btnExit))
					.addGap(12)
					.addComponent(scrollPaneSearch, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
					.addGap(11))
		);
		panelSearch.setLayout(gl_panelSearch);
		
		JPanel panelSoftware = new JPanel();
		tabbedPane.addTab("Software", null, panelSoftware, null);
		
		JScrollPane scrollPaneSoftware = new JScrollPane();
		
		JButton btnListSofware = new JButton("Listar");
		
		listModelSoftware = new DefaultListModel<String>();
		
		listSoftware = new JList<String>(listModelSoftware);
		scrollPaneSoftware.setViewportView(listSoftware);
		
		JButton btnDeleteSoftware = new JButton("Apagar");
		
		textFieldInsertSoftware = new JTextField();
		textFieldInsertSoftware.setColumns(10);
		
		JButton btnInsertSoftware = new JButton("Inserir");
		GroupLayout gl_panelSoftware = new GroupLayout(panelSoftware);
		gl_panelSoftware.setHorizontalGroup(
			gl_panelSoftware.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSoftware.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panelSoftware.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelSoftware.createSequentialGroup()
							.addComponent(btnListSofware, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(textFieldInsertSoftware, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnInsertSoftware, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(btnDeleteSoftware, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPaneSoftware, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE))
					.addGap(10))
		);
		gl_panelSoftware.setVerticalGroup(
			gl_panelSoftware.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSoftware.createSequentialGroup()
					.addGroup(gl_panelSoftware.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelSoftware.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelSoftware.createParallelGroup(Alignment.LEADING)
								.addComponent(btnListSofware)
								.addComponent(btnInsertSoftware)
								.addComponent(btnDeleteSoftware)))
						.addGroup(Alignment.TRAILING, gl_panelSoftware.createSequentialGroup()
							.addContainerGap()
							.addComponent(textFieldInsertSoftware, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)))
					.addGap(11)
					.addComponent(scrollPaneSoftware, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
					.addGap(11))
		);
		panelSoftware.setLayout(gl_panelSoftware);
		GroupLayout groupLayout = new GroupLayout(frmOcsSoftwareDetection.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(tabbedPane)
					.addGap(10))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(tabbedPane)
					.addGap(11))
		);
		groupLayout.setHonorsVisibility(false);
		frmOcsSoftwareDetection.getContentPane().setLayout(groupLayout);
		
		btnStartSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					tableModelSearch.setRowCount(0);
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
				insertSoftware();
			}
		});
		
		btnDeleteSoftware.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		
		btnExport.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fileChooser.setFileFilter(new FileNameExtensionFilter("Planilha do Excel", "csv"));
				int i = fileChooser.showSaveDialog(null);
				if(i != 1){
					File exportFile = fileChooser.getSelectedFile();
					FileWriter fileWriter;
					try {
						fileWriter = new FileWriter(exportFile.getAbsolutePath() + ".csv");
						fileWriter.write("TAG;Usuário;IP;Software;\n");
						for (int j = 0; j < tableModelSearch.getRowCount(); j++) {
							for (int k = 0; k < tableModelSearch.getColumnCount(); k++) {
								fileWriter.write(tableModelSearch.getValueAt(j, k) + ";");							
							}
							fileWriter.write("\n");
						}
						fileWriter.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
			}
		});
		
		textFieldInsertSoftware.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == '\n') {
					insertSoftware();
				}
			}
		});
		
	}
	
	public JFrame getFrame(){
		return this.frmOcsSoftwareDetection;
	}
	
	public void error(String message){
		JOptionPane.showMessageDialog(this.frmOcsSoftwareDetection, message);
//		ErrorScreen error = new ErrorScreen(message);
//		error.getFrame().setVisible(true);
	}
	
	public void updateSoftwareList() throws CommunicationErrorException{
		listModelSoftware.clear();
		List<String> softwares = database.getSoftwares();
		for (String software : softwares) {
			listModelSoftware.addElement(software);
		}
	}
	
	public void insertSoftware(){
		if (textFieldInsertSoftware.getText() != null && textFieldInsertSoftware.getText().length() > 0) {
			try {
				if(!database.getSoftwares().contains(textFieldInsertSoftware.getText())){
					database.insertSoftware(textFieldInsertSoftware.getText());
					updateSoftwareList();
					textFieldInsertSoftware.setText(null);
				}else error("Software já existe!");
			} catch (CommunicationErrorException e1) {
				error(e1.getMessage());
				e1.printStackTrace();
			}
		}else error("Campo vazio!");
	}
}
