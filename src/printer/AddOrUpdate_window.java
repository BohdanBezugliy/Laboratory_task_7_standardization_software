package printer;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddOrUpdate_window {

	protected JFrame frame;
	Printer printerChange = new Printer();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddOrUpdate_window window = new AddOrUpdate_window(new JTable(), false);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private static Object[] RowForAdd;
	/**
	 * Create the application.
	 */
	public AddOrUpdate_window(JTable tableDesk,Boolean isChange) {
		initialize(tableDesk, isChange);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JTable tableDesk, Boolean isChange) {
	    frame = new JFrame();
	    frame.setBounds(100, 100, 379, 507);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    // Create labels and input fields
	    JLabel nameLabel = new JLabel("Назва:"); // Name
	    nameLabel.setBounds(18, 1, 47, 49);
	    JTextField nameField = new JTextField(20);
	    nameField.setBounds(66, 14, 300, 21);
	    JLabel typeLabel = new JLabel("Тип:"); // Type
	    typeLabel.setBounds(18, 55, 35, 49);
	    JTextField typeField = new JTextField(20);
	    typeField.setBounds(55, 68, 308, 21);
	    JLabel producerLabel = new JLabel("Виробник:"); // Producer
	    producerLabel.setBounds(18, 109, 71, 49);
	    JTextField producerField = new JTextField(20);
	    producerField.setBounds(101, 122, 265, 21);
	    JLabel printFormatLabel = new JLabel("Формат друку:"); // Print Format
	    printFormatLabel.setBounds(18, 163, 101, 49);
	    JTextField printFormatField = new JTextField(20);
	    printFormatField.setBounds(115, 176, 251, 21);
	    JLabel colorPrintLabel = new JLabel("Кольоровий друк:"); // Color Print
	    colorPrintLabel.setBounds(18, 224, 130, 35);
	    JCheckBox colorPrintBox = new JCheckBox();
	    colorPrintBox.setBounds(144, 224, 35, 35);
	    JLabel speedPrintLabel = new JLabel("Швидкість (ст./хв.):"); // Speed (ppm)
	    speedPrintLabel.setBounds(18, 271, 137, 49);
	    JSpinner speedPrintSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
	    speedPrintSpinner.setBounds(155, 284, 78, 21);
	    JLabel typeConnectLabel = new JLabel("Тип підключення:"); // Connection Type
	    typeConnectLabel.setBounds(18, 325, 124, 49);
	    JTextField typeConnectField = new JTextField(20);
	    typeConnectField.setBounds(144, 338, 222, 21);
	    JLabel priceLabel = new JLabel("Ціна:");
	    priceLabel.setBounds(18, 383, 124, 49);
	    JTextField priceField = new JTextField(20);
	    priceField.setBounds(66, 394, 300, 21);
	    if(isChange) {
	    	int i = tableDesk.getSelectedRow();


	    	printerChange.setName((String) tableDesk.getValueAt(i, 0));
	    	printerChange.setType((String) tableDesk.getValueAt(i, 1));
	    	printerChange.setProducer((String) tableDesk.getValueAt(i, 2));
	    	printerChange.setPrintFormat((String) tableDesk.getValueAt(i, 3));
	    	printerChange.setTypeConnect((String) tableDesk.getValueAt(i, 4));
	    	try {
	    	  printerChange.setPrice((float)tableDesk.getValueAt(i, 5)); 
	    	  printerChange.setSpeedPrint((int)tableDesk.getValueAt(i, 6));
	    	} catch (Exception e) {
	    		JOptionPane.showMessageDialog(null, e.getMessage());
	    	}
	    	printerChange.setColorPrint(((String) tableDesk.getValueAt(i, 7)).equals("Кольоровий"));

	    	nameField.setText(printerChange.getName());
	    	typeField.setText(printerChange.getType());
	    	producerField.setText(printerChange.getProducer());
	    	printFormatField.setText(printerChange.getPrintFormat());
	    	typeConnectField.setText(printerChange.getTypeConnect());
	    	priceField.setText(String.valueOf(printerChange.getPrice()));
	    	speedPrintSpinner.setValue(printerChange.getSpeedPrint());
	    	colorPrintBox.setSelected(printerChange.isColorPrint());
	    }
	    // Create buttons
	    JButton saveButton = new JButton("Зберегти");
	    saveButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if(!isChange) {
	    			RowForAdd = new Object[8];
	    			RowForAdd[0] = nameField.getText(); // Name
	    			RowForAdd[1] = typeField.getText(); // Type
	    			RowForAdd[2] = producerField.getText(); // Producer
	    			RowForAdd[3] = printFormatField.getText(); // Print Format
	    			RowForAdd[4] = typeConnectField.getText(); // Connection Type
	    			if (priceField != null) {
	    		    	try {
	    		    		printerChange.setPrice(Float.parseFloat(priceField.getText()));
	    		    		printerChange.setSpeedPrint((int)speedPrintSpinner.getValue());
	    		    		RowForAdd[5] = Float.parseFloat(priceField.getText());
	    		    		RowForAdd[6] = speedPrintSpinner.getValue();
	    		    		RowForAdd[7] = colorPrintBox.isSelected() ? "Кольоровий" : "Чорно-білий";
	    		    		((DefaultTableModel) tableDesk.getModel()).addRow(RowForAdd);
	    		    	} catch (Exception err) {
	    		       	 JOptionPane.showMessageDialog(null, err.getMessage());
	    		    	}
	    			}
	    			
	    		}else {
	    			int i = tableDesk.getSelectedRow();
	    			((DefaultTableModel) tableDesk.getModel()).setValueAt(nameField.getText(), i, 0);
	    			((DefaultTableModel) tableDesk.getModel()).setValueAt(typeField.getText(), i, 1);
	    			((DefaultTableModel) tableDesk.getModel()).setValueAt(producerField.getText(), i, 2);
	    			((DefaultTableModel) tableDesk.getModel()).setValueAt(printFormatField.getText(), i, 3);
	    			((DefaultTableModel) tableDesk.getModel()).setValueAt(typeConnectField.getText(), i, 4);
	    			if (priceField != null) {
	    		    	try {
	    		    		printerChange.setPrice(Float.parseFloat(priceField.getText()));
    		    			printerChange.setSpeedPrint((int)speedPrintSpinner.getValue());
	    		    		((DefaultTableModel) tableDesk.getModel()).setValueAt(Double.parseDouble(priceField.getText()), i, 5);
	    		    		((DefaultTableModel) tableDesk.getModel()).setValueAt(speedPrintSpinner.getValue(), i, 6);
	    		    	} catch (Exception err) {
	    		       	 JOptionPane.showMessageDialog(null, err.getMessage());
	    		    	}
	    			}
	    			
	    			((DefaultTableModel) tableDesk.getModel()).setValueAt(colorPrintBox.isSelected() ? "Кольоровий" : "Чорно-білий", i, 7);
	    		}
	    	}
	    });
	    JButton cancelButton = new JButton("Відмінити");
	    cancelButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		frame.setVisible(false);
	    	}
	    });

	    // Layout components
	    JPanel inputPanel = new JPanel();
	    inputPanel.setBounds(0, 0, 379, 441);
	    inputPanel.setLayout(null);
	    inputPanel.add(nameLabel);
	    inputPanel.add(nameField);
	    inputPanel.add(typeLabel);
	    inputPanel.add(typeField);
	    inputPanel.add(producerLabel);
	    inputPanel.add(producerField);
	    inputPanel.add(printFormatLabel);
	    inputPanel.add(printFormatField);
	    inputPanel.add(colorPrintLabel);
	    inputPanel.add(colorPrintBox);
	    inputPanel.add(speedPrintLabel);
	    inputPanel.add(speedPrintSpinner);
	    inputPanel.add(typeConnectLabel);
	    inputPanel.add(typeConnectField);
	    inputPanel.add(priceLabel);
	    inputPanel.add(priceField);
	    
	    JPanel buttonPanel = new JPanel();
	    buttonPanel.setBounds(0, 439, 379, 39);
	    buttonPanel.add(saveButton);
	    buttonPanel.add(cancelButton);
	    frame.getContentPane().setLayout(null);
	    frame.getContentPane().add(inputPanel);
	    
	    
	    frame.getContentPane().add(buttonPanel);
	}
}
