package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Management_UI extends JFrame{
	// Reference Classes
	Order_UI order_UI = new Order_UI();
	MainMenu mainMenu = new MainMenu();
	AllOrders_UI allOrders_UI = new AllOrders_UI();

	// UI ELEMENTS
    JPanel tables_labelPanel = new JPanel(new FlowLayout());
    JPanel tables_panel = new JPanel(new GridLayout(3, 3));
    JPanel sUI_panel = new JPanel(new FlowLayout());
    JPanel n_panel = new JPanel(new GridLayout(2, 1));
    JPanel s_panel = new JPanel(new BorderLayout());
    
    JButton table1_button = new JButton("Table_1");
    JButton table2_button = new JButton("Table_2");
    JButton table3_button = new JButton("Table_3");
    JButton table4_button = new JButton("Table_4");
    JButton table5_button = new JButton("Table_5");
    JButton table6_button = new JButton("Table_6");
    JButton table7_button = new JButton("Table_7");
    JButton table8_button = new JButton("Table_8");
    JButton table9_button = new JButton("Table_9");
    JButton orderQueue_button = new JButton("All Orders");
    JButton menuManagement_button = new JButton("Menu Content");
    
    JLabel tables_label = new JLabel("Tables");
    
    Color pink = new Color(240,192,203);
    Color orange = new Color(255,150,0);

    public Management_UI() {
        super("Manager Menu");
        setUI();
        setListeners();
        allOrders_UI.cocktailLinkedQueue.initializeQueue();                                                           
    }

    void setUI() {
        setSize(500, 400);
        setLocation(640,270);
        tables_labelPanel.add(tables_label);
        tables_panel.add(table1_button);
        tables_panel.add(table2_button);
        tables_panel.add(table3_button);
        tables_panel.add(table4_button);
        tables_panel.add(table5_button);
        tables_panel.add(table6_button);
        tables_panel.add(table7_button);
        tables_panel.add(table8_button);
        tables_panel.add(table9_button);
        n_panel.add(tables_labelPanel);
        tables_labelPanel.setBackground(pink);
        n_panel.add(tables_panel);
        add(n_panel, BorderLayout.NORTH);
        sUI_panel.add(orderQueue_button);
        orderQueue_button.setBackground(orange);
        sUI_panel.add(menuManagement_button);
        menuManagement_button.setBackground(orange);
        s_panel.add(sUI_panel, BorderLayout.CENTER);
        sUI_panel.setBackground(pink);
        add(s_panel, BorderLayout.CENTER);
        tables_labelPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
        tables_label.setFont(new Font("Dialog.bold", Font.PLAIN, 15));
    }

    void setListeners() {
    	menuManagement_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	mainMenu.setVisible(true);
            }
        });

    	orderQueue_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	allOrders_UI.setVisible(true);
            }
        });
    }
}
