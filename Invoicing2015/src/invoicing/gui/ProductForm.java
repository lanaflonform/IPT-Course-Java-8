package invoicing.gui;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;
import invoicing.controller.ItemController;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ProductForm extends JApplet{
	public static final String[] FIELD_NAMES = {
		"ID", "Name", "Price", "Vendor"
	};
	
	private ItemController controller = ItemController.getInstance();
	private JPanel mainPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JButton btnSubmit = new JButton("Submit");
	private JButton btnReset = new JButton("Reset");
	
	private Map<String, JTextField> fields = new HashMap<>();
	
	public void init() {
		controller.init();
		
		JLabel title = new JLabel("Add Product Form");
		add(NORTH, title);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		
		//Form fields setup
		GridBagLayout gridbag = new GridBagLayout();
		gridbag.columnWidths = new int[] {80, 400};
		gridbag.columnWeights = new double[] {0.5, 0.5};
		mainPanel.setLayout(gridbag);
		
		for(String fName: FIELD_NAMES){
			fields.put(fName, makeAField(fName, mainPanel, gridbag));
		}
		
		add(CENTER, mainPanel);
		add(SOUTH, buttonPanel);
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(btnSubmit);
		buttonPanel.add(btnReset);
		
	}

	private JTextField makeAField(String fieldName, Container panel, GridBagLayout gb) {
		JLabel label = new JLabel(fieldName);
		JTextField field = new JTextField();
		GridBagConstraints c = new GridBagConstraints();
		
		// add label
		label.setHorizontalAlignment(SwingConstants.LEFT);
		c.gridwidth = 1;
		c.insets = new Insets(10, 20, 10, 20);
		c.anchor = GridBagConstraints.BASELINE_LEADING;
		gb.addLayoutComponent(label, c);
		panel.add(label);
		
		//add button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.HORIZONTAL;
		gb.addLayoutComponent(field, c);
		panel.add(field);
		
		return field;
	}

	public static void main(String[] args) {
		GuiUtilities.show(new ProductForm(), "Product Form", 600,  400);

	}

}
