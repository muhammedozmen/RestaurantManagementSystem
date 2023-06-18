package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame{
	// Reference Classes
	DrinkCreate drinkCreate = new DrinkCreate();
	Drinks drinks = new Drinks(drinkCreate);
	
	// UI ELEMENTS
	JPanel allPanel = new JPanel();
    JPanel buttons_panel = new JPanel(new GridLayout(1, 2, 10, 10));
    
    JButton new_button = new JButton("Add New");
    JButton allSeen_button = new JButton("See All");
    
    Color pink = new Color(240,192,203);
    Color orange = new Color(255,150,0);

    public MainMenu() {
        super("Menu Content");
        setUI();
        setListeners();
    }

    void setUI() {
        setSize(350, 125);
        setLocation(600, 340);
        add(allPanel);
        allPanel.setBackground(pink);
        allPanel.add(buttons_panel);
        buttons_panel.setBackground(pink);
        buttons_panel.add(new_button);
        new_button.setBackground(orange);
        buttons_panel.add(allSeen_button);
        allSeen_button.setBackground(orange); 
    }

    void setListeners() {
    	new_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	drinkCreate.submit_button.setText("Submit");
            	drinkCreate.setTitle("New Cocktail");
            	drinkCreate.clear();
            	drinkCreate.setVisible(true);
                setVisible(false);
            }
        });

    	allSeen_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	drinks.setVisible(true);
                setVisible(false);
            }
        });
    }
}
