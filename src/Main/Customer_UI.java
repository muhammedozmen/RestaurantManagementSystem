package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Customer_UI extends JFrame{
	// Reference Classes
	Customer_Panel customerPanel = new Customer_Panel();

	// UI ELEMENTS
    JLabel selectTable_label = new JLabel("Selected table:");
    JLabel bill_label = new JLabel("Bill($):                         ");
    
    JButton menu_button = new JButton("Menu");
    JButton tip_button = new JButton("Give 5$ tip");
    JButton clearOrders_button = new JButton("Clear Orders");
    JButton order_button = new JButton("Order");
    JButton pay_button = new JButton("Pay");
    
    Color pink = new Color(240,192,203);
    Color orange = new Color(255,150,0);

    String tables[] = {"Table_1", "Table_2", "Table_3", "Table_4", "Table_5", "Table_6", "Table_7", "Table_8", "Table_9"};
    JComboBox<String> tables_cb = new JComboBox<>(tables);

    DefaultListModel orders_dlm = new DefaultListModel();
    
    JList orders_list = new JList(orders_dlm);
    
    JScrollPane spOrders = new JScrollPane(orders_list);

    JPanel rightCenter_panel = new JPanel(new GridLayout(3, 2,10,10));
    JPanel leftCenter_panel = new JPanel();
    JPanel center_panel = new JPanel(new FlowLayout());
    JPanel south_panel = new JPanel(new FlowLayout());

    ArrayList<Cocktail> cocktailQueue_al = new ArrayList<>();
    private double bill = 0;
    private int givenTip = 0;

    IndividualTable_OBJ table;

    public Customer_UI() {
        super("Customer Menu");
        setUI();
        setListeners();
    }

    void setUI() {
        setLayout(new BorderLayout());
        setSize(480,420);
        setLocation(600,240);    
        rightCenter_panel.setBackground(pink);
        rightCenter_panel.add(selectTable_label);
        rightCenter_panel.add(tables_cb);
        rightCenter_panel.add(menu_button);
        menu_button.setBackground(orange);
        rightCenter_panel.add(new JLabel(""));
        rightCenter_panel.add(tip_button);
        tip_button.setBackground(orange);
        rightCenter_panel.add(clearOrders_button);
        clearOrders_button.setBackground(orange);
        leftCenter_panel.add(spOrders);
        center_panel.setBackground(pink);
        center_panel.add(leftCenter_panel);
        leftCenter_panel.setBackground(pink);
        center_panel.add(rightCenter_panel);
        add(center_panel, BorderLayout.CENTER);
        south_panel.add(bill_label);
        south_panel.add(pay_button);
        pay_button.setBackground(orange);
        south_panel.add(order_button);
        order_button.setBackground(orange);
        add(south_panel, BorderLayout.SOUTH);
        south_panel.setBackground(pink);
        order_button.setEnabled(false);
    }

    void setListeners() {
    	menu_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	customerPanel.setVisible(true);
            }
        });

    	customerPanel.addToChart_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (customerPanel.list.getSelectedIndex() == -1) {
                    return;
                }

                String element = customerPanel.list.getSelectedValue().toString();
                String splitted[] = element.split(" ");

                Drink_Aroma drinkAroma = null;

                switch (splitted[1]) {
                    case "Sweet":
                    	drinkAroma = Drink_Aroma.SWEET;
                        break;
                    case "Sour":
                    	drinkAroma = Drink_Aroma.SOUR;
                        break;
                    case "Bitter":
                    	drinkAroma = Drink_Aroma.BITTER;
                        break;
                    case "Salty":
                    	drinkAroma = Drink_Aroma.SALTY;
                        break;
                    case "Spicy":
                    	drinkAroma = Drink_Aroma.SPICY;
                        break;
                }

                String fruitArrayForArrayList[] = splitted[2].split("-");
                ArrayList fruitListForArrayList = new ArrayList();

                for (int i = 0; i < fruitArrayForArrayList.length; i++) {
                    fruitListForArrayList.add(fruitArrayForArrayList[i]);
                }
                Cocktail cocktailToBeAddedToOrderList = new Cocktail(splitted[0],splitted[4].equals("true") ? true : false, Integer.parseInt(splitted[3]),fruitListForArrayList,drinkAroma,Double.parseDouble(splitted[5]));
                bill += cocktailToBeAddedToOrderList.getPrice();
                orders_dlm.addElement(cocktailToBeAddedToOrderList);
                cocktailQueue_al.add(cocktailToBeAddedToOrderList);
                bill_label.setText("Bill($):         " + bill);
            }
        });

    	tip_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bill +=5;
                givenTip += 5;
                bill_label.setText("Bill($):         " + bill);
            }
        });

    	pay_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (bill > 0) {
                	order_button.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(pay_button,
                            "Add something to list or just give a tip.");
                }
            }
        });
    	
    	clearOrders_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });
    }

    public double getBill() {
        return bill;
    }

    public int getGivenTip() {
        return givenTip;
    }

    void clear() {
    	orders_dlm.clear();
        bill = 0;
        givenTip = 0;
        bill_label.setText("Bill($):                         ");
        order_button.setEnabled(false);
        cocktailQueue_al.clear();
    }

}
