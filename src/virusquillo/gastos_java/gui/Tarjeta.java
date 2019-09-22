package virusquillo.gastos_java.gui;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JCheckBox;
import javax.swing.JTabbedPane;

public class Tarjeta extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JFormattedTextField textField_2;
	private JTextField textField_3;

	/**
	 * Create the panel.
	 */
	public Tarjeta() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JLabel lblNewLabel = new JLabel("TARJETA");
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 10, SpringLayout.WEST, this);
		add(lblNewLabel);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, textField, 6, SpringLayout.EAST, lblNewLabel);
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 3, SpringLayout.NORTH, textField);
		springLayout.putConstraint(SpringLayout.NORTH, textField, 10, SpringLayout.NORTH, this);
		lblNewLabel.setLabelFor(textField);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("NUMERO");
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_1, -307, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.EAST, textField, -6, SpringLayout.WEST, lblNewLabel_1);
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 0, SpringLayout.NORTH, lblNewLabel);
		add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, textField_1, 6, SpringLayout.EAST, lblNewLabel_1);
		springLayout.putConstraint(SpringLayout.EAST, textField_1, -10, SpringLayout.EAST, this);
		lblNewLabel_1.setLabelFor(textField_1);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("FECHA CADUCIDAD");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 10, SpringLayout.SOUTH, textField);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_2, 0, SpringLayout.WEST, lblNewLabel);
		add(lblNewLabel_2);
		
		textField_2 = new JFormattedTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_2, -3, SpringLayout.NORTH, lblNewLabel_2);
		springLayout.putConstraint(SpringLayout.WEST, textField_2, 6, SpringLayout.EAST, lblNewLabel_2);
		springLayout.putConstraint(SpringLayout.EAST, textField_2, 0, SpringLayout.EAST, textField);
		lblNewLabel_2.setLabelFor(textField_2);
		add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("VERSION");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_3, 0, SpringLayout.NORTH, lblNewLabel_2);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_3, 0, SpringLayout.EAST, lblNewLabel_1);
		add(lblNewLabel_3);
		
		JSpinner spinner = new JSpinner();
		springLayout.putConstraint(SpringLayout.NORTH, spinner, 38, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, spinner, 6, SpringLayout.EAST, lblNewLabel_3);
		springLayout.putConstraint(SpringLayout.SOUTH, textField_1, -6, SpringLayout.NORTH, spinner);
		lblNewLabel_3.setLabelFor(spinner);
		add(spinner);
		
		JCheckBox chckbxActiva = new JCheckBox("ACTIVA");
		springLayout.putConstraint(SpringLayout.WEST, chckbxActiva, 374, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, spinner, -6, SpringLayout.WEST, chckbxActiva);
		springLayout.putConstraint(SpringLayout.NORTH, chckbxActiva, -4, SpringLayout.NORTH, lblNewLabel_2);
		add(chckbxActiva);
		
		JLabel lblNotas = new JLabel("NOTAS");
		springLayout.putConstraint(SpringLayout.NORTH, lblNotas, 12, SpringLayout.SOUTH, lblNewLabel_2);
		springLayout.putConstraint(SpringLayout.WEST, lblNotas, 0, SpringLayout.WEST, lblNewLabel);
		add(lblNotas);
		
		textField_3 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_3, 4, SpringLayout.SOUTH, chckbxActiva);
		springLayout.putConstraint(SpringLayout.WEST, textField_3, 17, SpringLayout.EAST, lblNotas);
		springLayout.putConstraint(SpringLayout.EAST, textField_3, 0, SpringLayout.EAST, textField_1);
		add(textField_3);
		textField_3.setColumns(10);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		springLayout.putConstraint(SpringLayout.NORTH, tabbedPane, 6, SpringLayout.SOUTH, textField_3);
		springLayout.putConstraint(SpringLayout.WEST, tabbedPane, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, tabbedPane, 456, SpringLayout.SOUTH, textField_3);
		springLayout.putConstraint(SpringLayout.EAST, tabbedPane, 640, SpringLayout.WEST, this);
		add(tabbedPane);

	}
}
