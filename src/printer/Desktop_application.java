package printer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class Desktop_application {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Desktop_application window = new Desktop_application();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the application.
	 */
	public Desktop_application() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1143, 609);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		table = new JTable();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		frame.getContentPane().setLayout(null);
		model.addColumn("Назва");
		model.addColumn("Тип");
		model.addColumn("Виробник");
		model.addColumn("Формат друку");
		model.addColumn("Тип підключення");
		model.addColumn("Ціна");
		model.addColumn("Швидкість (ст./хв.):");
		model.addColumn("Кольоровий друк:");
		
		Printer printer = new Printer();

		// Set the printer properties
		printer.setName("Принтер HP LaserJet 1018");
		printer.setType("Лазерний");
		printer.setProducer("HP");
		printer.setPrintFormat("A4");
		printer.setTypeConnect("USB 2.0");
		try {
		  printer.setPrice(1500);  // Assuming price is in your currency
		  printer.setSpeedPrint(21);
		} catch (Exception e) {
		  // Handle exception if price or speed is negative (unlikely in this case)
		  e.printStackTrace();
		}

		printer.setColorPrint(true);

		// Add printer information to the model (assuming model is a JTable)
		model.addRow(new Object[]{
		  printer.getName(),
		  printer.getType(),
		  printer.getProducer(),
		  printer.getPrintFormat(),
		  printer.getTypeConnect(),
		  printer.getPrice(),
		  printer.getSpeedPrint(),
		  printer.isColorPrint() ? "Кольоровий" : "Чорно-білий" // Convert colorPrint to "Кольоровий" or "Чорно-білий"
		});
		JButton btnAdd = new JButton("Додати запис");
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddOrUpdate_window addOrUpdate_window = new AddOrUpdate_window(table, false);
				addOrUpdate_window.frame.setVisible(true);
			}
		});
		btnAdd.setBounds(6, 6, 117, 29);
		frame.getContentPane().add(btnAdd);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 36, 1131, 539);
		frame.getContentPane().add(scrollPane);
		
		
		scrollPane.setViewportView(table);
		
		table.setFillsViewportHeight(true);
		
		JButton btnChange = new JButton("Змінити запис");
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1) {
					AddOrUpdate_window addOrUpdate_window = new AddOrUpdate_window(table, true);
					addOrUpdate_window.frame.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Будь ласка, оберіть рядок для зміни!");
				}
			}
		});
		btnChange.setBounds(126, 6, 117, 29);
		frame.getContentPane().add(btnChange);
		
		JButton btnDelete = new JButton("Видалити запис");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();

		        if (selectedRow != -1) {
		            DefaultTableModel model = (DefaultTableModel) table.getModel();
		            model.removeRow(selectedRow);
		        } else {
		            JOptionPane.showMessageDialog(null, "Будь ласка, оберіть рядок для видалення!");
		        }
			}
		});
		btnDelete.setBounds(247, 6, 131, 29);
		frame.getContentPane().add(btnDelete);
		
		JButton btnExit = new JButton("Вихід");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(1045, 6, 92, 29);
		frame.getContentPane().add(btnExit);
		
		JButton btnClearTable = new JButton("Очистити таблицю");
		btnClearTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = model.getRowCount() - 1; i >= 0; i--) {
				    model.removeRow(i);
				}
			}
		});
		btnClearTable.setBounds(379, 6, 165, 29);
		frame.getContentPane().add(btnClearTable);
	}
}
