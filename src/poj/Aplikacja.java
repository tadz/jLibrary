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

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.util.*;

import model.AplikacjaModel;
import model.Book;
import model.DB;

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

		this.table = createTable();
		this.tableModel.addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE)
		        {
		            int row = e.getFirstRow();
		            int column = e.getColumn();
		            
	                TableModel model = (TableModel)e.getSource();

	                int id = Integer.parseInt(model.getValueAt(row, 0).toString());
	                String fieldName = model.getColumnName(column).toString();
	                String fieldValue = model.getValueAt(row, column).toString();	
	                
	        		DB db = new DB();
	        		db.setConnection();
	        		db.setStatement();
	                
	        		db.updateRecord(id, fieldName, fieldValue);
		        }
			}
	    });	
		
		createMenuBar();
		fillDataTable();

		Container container = getContentPane();
		container.setLayout(new GridLayout(2, 1));

		JScrollPane scrollPane = new JScrollPane(table);

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
		tableModel.addColumn("isbn");
		tableModel.addColumn("owner");
		
		return table;
	}

	private void createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("ADD");
		JMenuItem addBook = new JMenuItem("NEW BOOK");

		JTextField searchTextField = this.createSearch();

		addBook.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BookInfo bookform = new BookInfo(tableModel);
				bookform.setVisible(true);
			}
		});

		fileMenu.add(addBook);
		menuBar.add(fileMenu);
		menuBar.add(searchTextField);

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

	private void fillDataTable() {
		Vector<Book> bookList = AplikacjaModel.findAll();

		for (Book book : bookList) {
			tableModel.addRow(new Object[] { book.getId(), book.getTitle(),
					book.getAuthor(), book.getPublisher(),
					book.getPublicationYear(), book.getIsbn(), book.getOwner() });
		}

	}

}
