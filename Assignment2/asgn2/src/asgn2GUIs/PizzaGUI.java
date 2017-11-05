package asgn2GUIs;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;

import asgn2Restaurant.PizzaRestaurant;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 * This class is the graphical user interface for the rest of the system.
 * Currently it is a ‘dummy’ class which extends JFrame and implements Runnable
 * and ActionLister. It should contain an instance of an
 * asgn2Restaurant.PizzaRestaurant object which you can use to interact with the
 * rest of the system. You may choose to implement this class as you like,
 * including changing its class signature – as long as it maintains its core
 * responsibility of acting as a GUI for the rest of the system. You can also
 * use this class and asgn2Wizards.PizzaWizard to test your system as a whole
 * 
 * 
 * @author Park Yeongje and Daniel Nguyen
 *
 */
public class PizzaGUI extends javax.swing.JFrame implements Runnable, ActionListener {
	private PizzaRestaurant restaurant = new PizzaRestaurant();

	JTable table1, table2, table3, table4;

	private JFrame frame;
	private JFileChooser choose;

	public File choosedFile;

	private JPanel CustomerDetail;
	private JPanel OrderDetail;
	private JPanel WrongPeople;
	private JPanel WrongPeople2;
	// Tabs
	private JTabbedPane tabbedPane;

	/**
	 * Creates a new Pizza GUI with the specified title
	 * 
	 * @param title
	 *            - The title for the supertype JFrame
	 */
	public PizzaGUI(String title) {
		// TO DO

		// TO create Column for Table
		String[] a1 = { "Customer Name", "Mobile Number", "Customer Type", "X Location", "Y Location",
				"Delivery Distance" };
		String[][] b1 = {};
		DefaultTableModel model1 = new DefaultTableModel(b1, a1);
		table1 = new JTable(model1);
		JScrollPane sc1 = new JScrollPane(table1);

		String[] a2 = { "Pizza Type", "Quantity", "Order Price", "Order Cost", "Order Profit" };
		String[][] b2 = {};
		DefaultTableModel model2 = new DefaultTableModel(b2, a2);
		table2 = new JTable(model2);
		JScrollPane sc2 = new JScrollPane(table2);

		String[] a3 = { "Invaild People For Customer data" };
		String[][] b3 = {};
		DefaultTableModel model3 = new DefaultTableModel(b3, a3);
		table3 = new JTable(model3);
		JScrollPane sc3 = new JScrollPane(table3);

		String[] a4 = { "Invaild People For Pizza data" };
		String[][] b4 = {};
		DefaultTableModel model4 = new DefaultTableModel(b4, a4);
		table4 = new JTable(model4);
		JScrollPane sc4 = new JScrollPane(table4);

		table1.setPreferredScrollableViewportSize(new Dimension(800, 400));
		table2.setPreferredScrollableViewportSize(new Dimension(800, 400));
		table3.setPreferredScrollableViewportSize(new Dimension(800, 400));
		table4.setPreferredScrollableViewportSize(new Dimension(800, 400));

		createMenuBar();
		setTitle(title);
		setSize(1200, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// To create Tap for Customer and Pizza
		CustomerDetail = Create_panel(Color.GRAY);
		OrderDetail = Create_panel(Color.GRAY);
		WrongPeople = Create_panel(Color.GRAY);
		WrongPeople2 = Create_panel(Color.GRAY);

		tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Customer Details", CustomerDetail);
		tabbedPane.addTab("Order Details", OrderDetail);
		tabbedPane.addTab("Invaild People For Customer data", WrongPeople);
		tabbedPane.addTab("Invaild People For Pizza data", WrongPeople2);
		CustomerDetail.add(sc1);
		OrderDetail.add(sc2);
		WrongPeople.add(sc3);
		WrongPeople2.add(sc4);

		this.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		repaint();
		this.setVisible(true);
	}

	// TO draw color
	private JPanel Create_panel(Color C) {
		JPanel panel = new JPanel();
		panel.setBackground(C);
		return panel;
	}

	// To put information into table
	public void Create_Table() throws PizzaException, CustomerException {
		DefaultTableModel m = (DefaultTableModel) table1.getModel();
		DefaultTableModel m2 = (DefaultTableModel) table2.getModel();
		DefaultTableModel m3 = (DefaultTableModel) table3.getModel();
		DefaultTableModel m4 = (DefaultTableModel) table4.getModel();

		String str[] = new String[100];
		String str2[] = new String[100];
		int a = 0;
		int b= 0;
		for (int i = 0; i < restaurant.getNumCustomerOrders(); i++) {
			m.addRow(new Object[] { restaurant.getCustomerByIndex(i).getName(),
					restaurant.getCustomerByIndex(i).getMobileNumber(),
					restaurant.getCustomerByIndex(i).getCustomerType(),
					Integer.toString(restaurant.getCustomerByIndex(i).getLocationX()),
					Integer.toString(restaurant.getCustomerByIndex(i).getLocationY()),
					Double.toString(restaurant.getCustomerByIndex(i).getDeliveryDistance()) });

			if (restaurant.getCustomerByIndex(i).getDeliveryDistance() > 10) {
				str[a] = (restaurant.getCustomerByIndex(i).getName());
				a++;
			}
		}

		for (int i = 0; i < restaurant.getNumPizzaOrders(); i++) {
			m2.addRow(new Object[] { restaurant.getPizzaByIndex(i).getPizzaType(),
					Integer.toString(restaurant.getPizzaByIndex(i).getQuantity()),
					Double.toString(restaurant.getPizzaByIndex(i).getOrderPrice()),
					Double.toString(restaurant.getPizzaByIndex(i).getOrderCost()),
					Double.toString(restaurant.getPizzaByIndex(i).getOrderProfit()) });

			if ((restaurant.getPizzaByIndex(i).getQuantity()) > 9) {
				str2[b] = restaurant.getCustomerByIndex(i).getName();
				b++;
			}
		}
		for (int l = 0; l <= a; l++) {
			m3.addRow(new Object[] { str[l] });
		}
		for (int l = 0; l <= a; l++) {
			m4.addRow(new Object[] { str2[l] });
		}
	}

	// To create Menu for File load and Reset Data
	private void createMenuBar() {
		JMenuBar menubar = new JMenuBar();
		ImageIcon icon = new ImageIcon("exit.png");

		JMenu file = new JMenu("File Load");
		JMenu file2 = new JMenu("Reset Data");

		JMenuItem openOrderMenuItem = new JMenuItem("Choose File", icon);
		openOrderMenuItem.addActionListener((ActionEvent event) -> {
			OpenFile();
			JOptionPane.showMessageDialog(null, "Loaded");
		});

		JMenuItem resetMenuItem = new JMenuItem("Reset Data", icon);
		resetMenuItem.addActionListener((ActionEvent event) -> {
			if (choosedFile != null) {

				DefaultTableModel model1 = (DefaultTableModel) table1.getModel();
				model1.setNumRows(0);
				DefaultTableModel model2 = (DefaultTableModel) table2.getModel();
				model2.setNumRows(0);
				DefaultTableModel model3 = (DefaultTableModel) table3.getModel();
				model3.setNumRows(0);
				DefaultTableModel model4 = (DefaultTableModel) table4.getModel();
				model4.setNumRows(0);


			}
		});

		file.add(openOrderMenuItem);
		file2.add(resetMenuItem);

		menubar.add(file);
		menubar.add(file2);
		setJMenuBar(menubar);

	}

	// To open and read File
	private void OpenFile() {
		choose = new JFileChooser();
		choose.setCurrentDirectory(new File(System.getProperty("user.home")));
		int selection = choose.showOpenDialog(frame);
		if (selection == JFileChooser.APPROVE_OPTION) {
			choosedFile = choose.getSelectedFile();

			try {
				restaurant.processLog(choosedFile.getAbsolutePath());
				Create_Table();
			} catch (CustomerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (PizzaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LogHandlerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Selected file: " + choosedFile.getAbsolutePath());
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new PizzaGUI(""));
	}

	@Override
	public void run() {
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}
