package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class Customer_Panel extends JFrame{
	// Reference Classes
	General_Info_UI general_Info_UI = new General_Info_UI();
	Coctails_Tree coctails_Tree = new Coctails_Tree();
	
    File file = new File("Cocktails");

    // UI ELEMENTS
    JPanel generalInfo_panel = new JPanel(new FlowLayout());
    JPanel addToChart_panel = new JPanel(new FlowLayout());
    JPanel south_panel = new JPanel(new FlowLayout());
    
    JButton generalInfo_button = new JButton("See Information");
    JButton addToChart_button = new JButton("Add to order list");
    
    DefaultListModel dlm = new DefaultListModel();
    
    JList list = new JList(dlm);
    
    JScrollPane sp = new JScrollPane(list);
    
    Color pink = new Color(240,192,203);
    Color orange = new Color(255,150,0);

    public Customer_Panel() {
        super("Menu");
        BST_Create();
        coctails_Tree.inorderCreatingArrayList(coctails_Tree.getRoot());      
        setUI();
        setListener();
        addDlmElements();
    }

    void setUI() {
        setLayout(new BorderLayout());
        setSize(400, 500);
        setLocation(640,270);
        
        add(sp, BorderLayout.CENTER);
        south_panel.add(generalInfo_panel);
        south_panel.add(addToChart_panel);
        add(south_panel, BorderLayout.SOUTH);
        
        generalInfo_panel.setBackground(pink);
        addToChart_panel.setBackground(pink);
        south_panel.setBackground(pink);
        
        generalInfo_panel.add(generalInfo_button);
        generalInfo_button.setBackground(orange);
        addToChart_panel.add(addToChart_button);
        addToChart_button.setBackground(orange);   
    }

    void setListener() {
    	generalInfo_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (list.getSelectedIndex() == -1) {
                    return;
                }
                String element = list.getSelectedValue().toString();
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
                general_Info_UI.nameInfo_label.setText(splitted[0]);
                general_Info_UI.withAlcoholInfo_label.setText(splitted[4]);
                general_Info_UI.alcoholRateInfo_label.setText(splitted[3]);
                general_Info_UI.fruitsInfo_label.setText(fruitListForArrayList.toString());
                general_Info_UI.aromaInfo_label.setText(drinkAroma.toString());
                general_Info_UI.priceInfo_label.setText(splitted[5]);
                general_Info_UI.setVisible(true);
            }
        });
    }

    public void BST_Create() {
        ObjectInputStream ois = null;
        try {
            if (file.length() != 0) {
                ois = new ObjectInputStream(new FileInputStream("Cocktails"));
                while (true) {
                    Cocktail cocktail = (Cocktail) ois.readObject();
                    coctails_Tree.insert(cocktail);
                }
            }

        } catch (FileNotFoundException e) { 
            e.printStackTrace();
        } catch (EOFException e) { 
            return;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void addDlmElements() {
        for (int i = 0; i < coctails_Tree.getCocktailArrayList().size(); i++) {
            dlm.addElement(coctails_Tree.getCocktailArrayList().get(i));
        }
    }
}
