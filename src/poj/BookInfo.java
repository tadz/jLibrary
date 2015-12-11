package poj;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.DB;

class BookInfo extends JFrame {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;
	private DB db;

	public BookInfo(DefaultTableModel tableModel) {
		this.db = new DB();
		this.db.setConnection();
		this.db.setStatement();

//		 this.db.dropTableBookstore();
//		 this.db.createTableBookstore();

		// this.db.closeConnection();

		this.tableModel = tableModel;
		initUI();
	}

	private void initUI() {
		setSize(200, 200);
		setTitle("Book Info");
		Container container = getContentPane();

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(8, 2));
		container.add(panel, BorderLayout.CENTER);

		final JTextField titleTextField = new JTextField();
		final JTextField authorTextField = new JTextField();
		final JTextField publisherTextField = new JTextField();
		final JTextField publicationYearTextField = new JTextField();
		final JTextField ownerTextField = new JTextField();
		final JTextField isbnTextField = new JTextField();
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
		panel.add(new JLabel("ISBN"));
		panel.add(isbnTextField);
		panel.add(new JLabel("Owner"));
		panel.add(ownerTextField);
		panel.add(cancelButton, BorderLayout.WEST);
		panel.add(okButton, BorderLayout.EAST);

		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});

		final DB db = this.db;

		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Integer id;
				String title = titleTextField.getText();
				String author = authorTextField.getText();
				String publisher = publisherTextField.getText();
				String publicationYear = publicationYearTextField.getText();
				String isbn = isbnTextField.getText();
				String owner = ownerTextField.getText();

				id = db.addRecordToBookstore(title, author, publisher,
						Integer.parseInt(publicationYear), isbn, owner);

				tableModel.addRow(new Object[] { id, title, author, publisher,
						publicationYear, isbn, owner });

				close();
			}
		});
		
		panel.getRootPane().setDefaultButton(okButton);
	}

	private void close() {
		setVisible(false);
		dispose();
	}
}
