package poj;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import javax.swing.table.DefaultTableModel;

import model.DB;

class BookInfo extends JFrame {

	private static final long serialVersionUID = 1L;
	DefaultTableModel tableModel;
	private DB db;
	
	public BookInfo(DefaultTableModel tableModel) {
		this.db = new DB();
		this.db.setConnection();
		this.db.setStatement();
		
//		this.db.dropTableBookstore();
//		this.db.createTableBookstore();

//		db.closeConnection();
		
		this.tableModel = tableModel;
		initUI();
	}

	private void initUI() {
		setSize(200, 200);
		setTitle("Book Info");
		Container container = getContentPane();

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 2));
		container.add(panel, BorderLayout.CENTER);

		final JTextField idTextField = new JTextField();
		final JTextField nameTextField = new JTextField();
		final JTextField authorTextField = new JTextField();
		final JTextField publisherTextField = new JTextField();
		final JTextField publicationYearTextField = new JTextField();
		JButton okButton = new JButton("OK");
		JButton cancelButton = new JButton("Cancel");

		panel.add(new JLabel("name"));
		panel.add(nameTextField);
		panel.add(new JLabel("Author"));
		panel.add(authorTextField);
		panel.add(new JLabel("Publisher"));
		panel.add(publisherTextField);
		panel.add(new JLabel("Publication_Year"));
		panel.add(publicationYearTextField);
		panel.add(cancelButton, BorderLayout.WEST);
		panel.add(okButton, BorderLayout.EAST);

		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});

		DB db = this.db;
		
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String id = idTextField.getText();
				String name = nameTextField.getText();
				String author = authorTextField.getText();
				String publisher = publisherTextField.getText();
				String publicationYear = publicationYearTextField.getText();

				db.addRecordToBookstore(name, author, publisher, Integer.parseInt(publicationYear));
				
				tableModel.addRow(new Object[] { id, name, author, publisher,
						publicationYear });

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
	
	public Aplikacja() {
		initUI();
	}

	private void initUI() {
		setTitle("Biblioteczka");
		setSize(800, 400);
		createMenuBar();

		JTable table = createTable();

		Container container = getContentPane();

		JScrollPane scrollPane = new JScrollPane(table);
		container.add(scrollPane, BorderLayout.CENTER);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private JTable createTable() {
		JTable table = new JTable(tableModel);
		tableModel.addColumn("id");
		tableModel.addColumn("name");
		tableModel.addColumn("author");
		tableModel.addColumn("publisher");
		tableModel.addColumn("publicationYear");
		return table;
	}

	private void createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("Dodaj");
		JMenuItem addBook = new JMenuItem("Nowa Ksi¹¿ka");

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
