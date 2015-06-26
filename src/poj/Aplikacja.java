package poj;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.util.*;

import model.AplikacjaModel;
import model.Book;
import model.DB;

class BookInfo extends JFrame {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;
	private DB db;
	
	public BookInfo(DefaultTableModel tableModel) {
		this.db = new DB();
		this.db.setConnection();
		this.db.setStatement();
		
//		this.db.dropTableBookstore();
//		this.db.createTableBookstore();

//		this.db.closeConnection();
		
		this.tableModel =  tableModel;
		initUI();
	}

	private void initUI() {
		setSize(200, 200);
		setTitle("Book Info");
		Container container = getContentPane();

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(6, 2));
		container.add(panel, BorderLayout.CENTER);

		final JTextField titleTextField = new JTextField();
		final JTextField authorTextField = new JTextField();
		final JTextField publisherTextField = new JTextField();
		final JTextField publicationYearTextField = new JTextField();
		final JTextField ownerTextField = new JTextField();
		JButton okButton = new JButton("OK");
		JButton cancelButton = new JButton("Cancel");

		panel.add(new JLabel("Title"));
		panel.add(titleTextField);
		panel.add(new JLabel("Author"));
		panel.add(authorTextField);
		panel.add(new JLabel("Publisher"));
		panel.add(publisherTextField);
		panel.add(new JLabel("Publication Year"));
		panel.add(publicationYearTextField);
		panel.add(new JLabel("Owner"));
		panel.add(ownerTextField);
		panel.add(cancelButton, BorderLayout.WEST);
		panel.add(okButton, BorderLayout.EAST);

		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				close();
			}
		}
		);

		final DB db = this.db;
		
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Integer id;
				String title = titleTextField.getText();
				String author = authorTextField.getText();
				String publisher = publisherTextField.getText();
				String publicationYear = publicationYearTextField.getText();
				String owner = ownerTextField.getText();

				id = db.addRecordToBookstore(title, author, publisher, Integer.parseInt(publicationYear), owner);
				
				tableModel.addRow(new Object[] { id, title, author, publisher,
						publicationYear, owner });

				close();
			}
		});
	}

	private void close() {
		setVisible(false);
		dispose();
	}
}

public class Aplikacja extends JFrame {

	private static final long serialVersionUID = 1L;
	DefaultTableModel tableModel = new DefaultTableModel();
	
	private JTable table;
	private TableRowSorter<TableModel> tableSorter;
	
	public Aplikacja() {
		initUI();
	}

	private void initUI() {
		setTitle("Biblioteczka");
		setSize(800, 400);
		createMenuBar();
		
		this.table = createTable();
		this.tableSorter = new TableRowSorter<TableModel>(this.tableModel);
		
		JTextField searchTextField = this.createSearch();
		
		Vector<Book> bookList = AplikacjaModel.findAll();
		
		for (Book book : bookList) {
			tableModel.addRow(new Object[] { 
				book.getId(), 
				book.getTitle(), 
				book.getAuthor(), 
				book.getPublisher(),	
				book.getPublicationYear(), 
				book.getOwner() 
			});
		}
		
		Container container = getContentPane();
		container.setLayout(new GridLayout(2, 1));

		JScrollPane scrollPane = new JScrollPane(table);
		container.add(searchTextField, BorderLayout.CENTER);
		container.add(scrollPane, BorderLayout.CENTER);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private JTable createTable() {
		JTable table = new JTable(tableModel);
		this.tableSorter = new TableRowSorter<TableModel>(this.tableModel);
		
		tableModel.addColumn("id");
		tableModel.addColumn("title");
		tableModel.addColumn("author");
		tableModel.addColumn("publisher");
		tableModel.addColumn("publication year");
		tableModel.addColumn("owner");
		
		return table;
	}

	private void createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("ADD");
		JMenuItem addBook = new JMenuItem("NOW BOOK");

		addBook.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BookInfo bookform = new BookInfo(tableModel);
				bookform.setVisible(true);
			}
		});

		fileMenu.add(addBook);
		menuBar.add(fileMenu);

		setJMenuBar(menuBar);
	}
	
	private JTextField createSearch() {
		final JTextField searchTextField = new JTextField();
		
	    table.setRowSorter(tableSorter);
	    
	    searchTextField.addKeyListener(new KeyAdapter() {
	    	public void keyReleased(KeyEvent e) {
	    		JTextField textField = (JTextField) e.getSource();
	    		String text = textField.getText();
	    		
	            if (text.length() == 0) {
	            	tableSorter.setRowFilter(null);
	        	} else {
	        		tableSorter.setRowFilter(RowFilter.regexFilter(text));
	        	}
	    	}
	    });
		
		return searchTextField;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				Aplikacja aplikacja = new Aplikacja();
				aplikacja.setVisible(true);
			}
		}

		);
	}

}
