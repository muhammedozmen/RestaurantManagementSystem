package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoginMenu extends JFrame{
	// Reference Classes
	Management_UI managementUI = new Management_UI();
    Customer_UI customerUI = new Customer_UI();
	
	// UI ELEMENTS
	JLabel title_label = new JLabel("           Bar Management System");
	
    JButton manager_button = new JButton("Manager");
    JButton customer_button = new JButton("Customer");
    
    JPanel allUI_panel = new JPanel(new GridLayout(2, 1));
    JPanel managerButton_panel = new JPanel(new FlowLayout());
    JPanel customerButton_panel = new JPanel(new FlowLayout());
    JPanel title_panel = new JPanel(new BorderLayout());
    JPanel buttons_panel = new JPanel(new GridLayout(1,2));
    
    Color pink = new Color(240,192,203);
    Color orange = new Color(255,150,0);
    
    IndividualTable_OBJ table_1 = new IndividualTable_OBJ();
    IndividualTable_OBJ table_2 = new IndividualTable_OBJ();
    IndividualTable_OBJ table_3 = new IndividualTable_OBJ();
    IndividualTable_OBJ table_4 = new IndividualTable_OBJ();
    IndividualTable_OBJ table_5 = new IndividualTable_OBJ();
    IndividualTable_OBJ table_6 = new IndividualTable_OBJ();
    IndividualTable_OBJ table_7 = new IndividualTable_OBJ();
    IndividualTable_OBJ table_8 = new IndividualTable_OBJ();
    IndividualTable_OBJ table_9 = new IndividualTable_OBJ();

    public static void main(String[] args) {
        new LoginMenu().setVisible(true);
    }
    
    
    public LoginMenu(){
        super("Login Menu");
        setUI();
        setListeners();
    }


    void setUI() {
        setLayout(new BorderLayout());
        setSize(600,300);
        setLocation(640,270);  
        setResizable(false);
        managerButton_panel.add(manager_button);
        manager_button.setBackground(orange);
        customerButton_panel.add(customer_button);
        customer_button.setBackground(orange);
        buttons_panel.add(managerButton_panel);
        managerButton_panel.setBackground(pink);
        buttons_panel.add(customerButton_panel);
        customerButton_panel.setBackground(pink);
        title_panel.add(title_label, BorderLayout.CENTER);
        allUI_panel.add(title_panel);
        title_panel.setBackground(pink);
        allUI_panel.add(buttons_panel);
        add(allUI_panel, BorderLayout.CENTER);
        title_panel.setBorder(BorderFactory.createEmptyBorder(0,35,20,0));
        title_label.setFont(new Font("SansSerif.bold",Font.PLAIN,30));
    }

    void setListeners() {
    	manager_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	managementUI.setVisible(true);
            }
        });

    	customer_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	customerUI.setVisible(true);
            }
        });

    	customerUI.order_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String successfulOperationMessage = customerUI.tables_cb.getSelectedItem().toString() + "\n";

                successfulOperationMessage += "Successful operation:\n";

                for (int i = 0; i < customerUI.cocktailQueue_al.size(); i++) {
                    successfulOperationMessage += (i + 1) + ":";
                    successfulOperationMessage += customerUI.cocktailQueue_al.get(i);
                    successfulOperationMessage += "\n";
                }

                successfulOperationMessage += "Given tip: " + customerUI.getGivenTip() + "\n";
                successfulOperationMessage += "Total bill: " + customerUI.getBill();

                JOptionPane.showMessageDialog(customerUI.pay_button,
                        successfulOperationMessage);

                ArrayList<Cocktail> cocktailArrayListforTableObject = new ArrayList<>();

                for (int i = 0; i < customerUI.cocktailQueue_al.size(); i++) {
                    cocktailArrayListforTableObject.add(customerUI.cocktailQueue_al.get(i));
                    managementUI.allOrders_UI.cocktailLinkedQueue.addQueue(customerUI.cocktailQueue_al.get(i));
                    managementUI.allOrders_UI.Queue_dlm.addElement(customerUI.tables_cb.getSelectedItem().toString() + ": " + customerUI.cocktailQueue_al.get(i));
                }

                IndividualTable_OBJ table = new IndividualTable_OBJ(customerUI.tables_cb.getSelectedItem().toString(), cocktailArrayListforTableObject);

                switch (table.getTableName()) {
                    case "Table_1":
                        table_1 = table;
                        changeButtonColorForNewOrder(managementUI.table1_button,table_1);
                        break;
                    case "Table_2":
                        table_2 = table;
                        changeButtonColorForNewOrder(managementUI.table2_button,table_2);
                        break;
                    case "Table_3":
                        table_3 = table;
                        changeButtonColorForNewOrder(managementUI.table3_button,table_3);
                        break;
                    case "Table_4":
                        table_4 = table;
                        changeButtonColorForNewOrder(managementUI.table4_button,table_4);
                        break;
                    case "Table_5":
                        table_5 = table;
                        changeButtonColorForNewOrder(managementUI.table5_button,table_5);
                        break;
                    case "Table_6":
                        table_6 = table;
                        changeButtonColorForNewOrder(managementUI.table6_button,table_6);
                        break;
                    case "Table_7":
                        table_7 = table;
                        changeButtonColorForNewOrder(managementUI.table7_button,table_7);
                        break;
                    case "Table_8":
                        table_8 = table;
                        changeButtonColorForNewOrder(managementUI.table8_button,table_8);
                        break;
                    case "Table_9":
                        table_9 = table;
                        changeButtonColorForNewOrder(managementUI.table9_button,table_9);
                        break;
                }

                customerUI.clear();
                System.out.println(table);

            }
        });

    	managementUI.order_UI.complete_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String lblInformationContent[] = managementUI.order_UI.orderInfo_label.getText().split(" ");
                String tableName = lblInformationContent[2];

                managementUI.order_UI.orderInfo_dlm.clear();

                switch (tableName) {
                    case "Table_1":
                        changeButtonColorToDefault(managementUI.table1_button);
                        table_1.getTableCocktailArraylist().clear();
                        break;
                    case "Table_2":
                        changeButtonColorToDefault(managementUI.table2_button);
                        table_2.getTableCocktailArraylist().clear();
                        break;
                    case "Table_3":
                        changeButtonColorToDefault(managementUI.table3_button);
                        table_3.getTableCocktailArraylist().clear();
                        break;
                    case "Table_4":
                        changeButtonColorToDefault(managementUI.table4_button);
                        table_4.getTableCocktailArraylist().clear();
                        break;
                    case "Table_5":
                        changeButtonColorToDefault(managementUI.table5_button);
                        table_5.getTableCocktailArraylist().clear();
                        break;
                    case "Table_6":
                        changeButtonColorToDefault(managementUI.table6_button);
                        table_6.getTableCocktailArraylist().clear();
                        break;
                    case "Table_7":
                        changeButtonColorToDefault(managementUI.table7_button);
                        table_7.getTableCocktailArraylist().clear();
                        break;
                    case "Table_8":
                        changeButtonColorToDefault(managementUI.table8_button);
                        table_8.getTableCocktailArraylist().clear();
                        break;
                    case "Table_9":
                        changeButtonColorToDefault(managementUI.table9_button);
                        table_9.getTableCocktailArraylist().clear();
                        break;
                }

            }
        });

        setActionListenersForButtons();
    }

    void setActionListenersForButtons() {

    	managementUI.table1_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeLblOrderInformation(managementUI.table1_button);
                managementUI.order_UI.orderInfo_dlm.clear();
                addElementsToOrderPanel(table_1);
                managementUI.order_UI.setVisible(true);

            }
        });

    	managementUI.table2_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeLblOrderInformation(managementUI.table2_button);
                managementUI.order_UI.orderInfo_dlm.clear();
                addElementsToOrderPanel(table_2);
                managementUI.order_UI.setVisible(true);
            }
        });

    	managementUI.table3_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeLblOrderInformation(managementUI.table3_button);
                managementUI.order_UI.orderInfo_dlm.clear();
                addElementsToOrderPanel(table_3);
                managementUI.order_UI.setVisible(true);
            }
        });

    	managementUI.table4_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeLblOrderInformation(managementUI.table4_button);
                managementUI.order_UI.orderInfo_dlm.clear();
                addElementsToOrderPanel(table_4);
                managementUI.order_UI.setVisible(true);
            }
        });

    	managementUI.table5_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeLblOrderInformation(managementUI.table5_button);
                managementUI.order_UI.orderInfo_dlm.clear();
                addElementsToOrderPanel(table_5);
                managementUI.order_UI.setVisible(true);
            }
        });

    	managementUI.table6_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeLblOrderInformation(managementUI.table6_button);
                managementUI.order_UI.orderInfo_dlm.clear();
                addElementsToOrderPanel(table_6);
                managementUI.order_UI.setVisible(true);
            }
        });

    	managementUI.table7_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeLblOrderInformation(managementUI.table7_button);
                managementUI.order_UI.orderInfo_dlm.clear();
                addElementsToOrderPanel(table_7);
                managementUI.order_UI.setVisible(true);
            }
        });

    	managementUI.table8_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeLblOrderInformation(managementUI.table8_button);
                managementUI.order_UI.orderInfo_dlm.clear();
                addElementsToOrderPanel(table_8);
                managementUI.order_UI.setVisible(true);
            }
        });

    	managementUI.table9_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeLblOrderInformation(managementUI.table9_button);
                managementUI.order_UI.orderInfo_dlm.clear();
                addElementsToOrderPanel(table_9);
                managementUI.order_UI.setVisible(true);
            }
        });
    }

    void changeLblOrderInformation(JButton button) {
    	managementUI.order_UI.orderInfo_label.setText("Order Information " + button.getText());
    }

    

    void changeButtonColorForNewOrder(JButton button, IndividualTable_OBJ tableX) {
        if (tableX.getTableCocktailArraylist().size() != 0) {
            button.setBackground(Color.GREEN);
        } else {
            button.setBackground(Color.YELLOW);
        }
    }

    void changeButtonColorToDefault(JButton button) {
        button.setBackground(null);
    }
    
    void addElementsToOrderPanel(IndividualTable_OBJ tableX) {

        for (int i = 0; i < tableX.getTableCocktailArraylist().size(); i++) {
        	managementUI.order_UI.orderInfo_dlm.addElement(tableX.getTableCocktailArraylist().get(i));
        }
    }

    
}
