package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;

public class Drinks extends JFrame{
	DrinkCreate drinkCreate;
    ArrayList<Cocktail> cocktail_al = new ArrayList<>();
    Coctails_Tree coctailsTree = new Coctails_Tree();
    File file = new File("Cocktails");

    // UI ELEMENTS
    JPanel edit_panel = new JPanel(new FlowLayout());
    JPanel delete_panel = new JPanel(new FlowLayout());
    JPanel buttons_panel = new JPanel(new FlowLayout());
    
    JButton edit_button = new JButton("Edit");
    JButton delete_button = new JButton("Delete");
    
    DefaultListModel dlm = new DefaultListModel();
    
    JList list = new JList(dlm);
    
    JScrollPane sp = new JScrollPane(list);
    
    Color pink = new Color(240,192,203);
    Color orange = new Color(255,150,0);

    public Drinks(DrinkCreate drinkCreate) {
        super("Drinks");
        this.drinkCreate = drinkCreate;
        bst_create();

        coctailsTree.inorderCreatingArrayList(coctailsTree.getRoot()); 
        System.out.println("Coctails Tree: " + coctailsTree.getCocktailArrayList()); 

        setUI();
        setListeners();
        additionalFunctions();
    }

    void setUI() {
        setLayout(new BorderLayout());
        setSize(400, 500);
        setLocation(640,270);
        edit_panel.add(edit_button);   
        edit_button.setBackground(orange);
        delete_panel.add(delete_button);      
        delete_button.setBackground(orange);
        buttons_panel.add(edit_panel);
        edit_panel.setBackground(pink);
        buttons_panel.add(delete_panel);
        delete_panel.setBackground(pink);
        add(sp, BorderLayout.CENTER);
        add(buttons_panel, BorderLayout.SOUTH);
        buttons_panel.setBackground(pink);    
    }

    void setListeners() {
    	edit_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (list.getSelectedIndex() == -1) {
                    return;
                }
                drinkCreate.clear();
                drinkCreate.setVisible(true);
                drinkCreate.submit_button.setText("Update");
                drinkCreate.setTitle("Edit");

                String element = list.getSelectedValue().toString();
                String splitted[] = element.split(" ");

                drinkCreate.name_txt.setText(splitted[0]);

                if (splitted[4].equals("true")) {
                	drinkCreate.withAlcohol_cb.setSelectedIndex(0);
                } else {
                	drinkCreate.withAlcohol_cb.setSelectedIndex(1);
                }

                drinkCreate.alcoholRate_txt.setText(splitted[3]);
                drinkCreate.fruits_txt.setText(splitted[2]);

                String drinkAroma = splitted[1];

                switch (drinkAroma) {
                    case "Sweet":
                    	drinkCreate.aroma_cb.setSelectedIndex(0);
                        break;
                    case "Sour":
                    	drinkCreate.aroma_cb.setSelectedIndex(1);
                        break;
                    case "Bitter":
                    	drinkCreate.aroma_cb.setSelectedIndex(2);
                        break;
                    case "Salty":
                    	drinkCreate.aroma_cb.setSelectedIndex(3);
                        break;
                    case "Spicy":
                    	drinkCreate.aroma_cb.setSelectedIndex(4);
                        break;
                }

                drinkCreate.price_txt.setText(splitted[5]);

            }
        });

    	delete_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
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

                Cocktail newCocktailToBeRemoved = new Cocktail(splitted[0],splitted[4].equals("true") ? true : false, Integer.parseInt(splitted[3]),fruitListForArrayList,drinkAroma,Double.parseDouble(splitted[5]));

                int index = 0;

                for (int i = 0; i < cocktail_al.size(); i++) {
                    if (cocktail_al.get(i).toString().equals(newCocktailToBeRemoved.toString())) {
                        index = i;
                    }
                }

                cocktail_al.remove(index);
                dlm.remove(list.getSelectedIndex());

                PrintWriter pw = null;
                try {
                    pw = new PrintWriter("Cocktails");
                } catch (FileNotFoundException ex) {
                    System.out.println("exp");
                }
                pw.close();

                for (int i = 0; i < cocktail_al.size(); i++) {
                	drinkCreate.registerNewCocktail(cocktail_al.get(i)); 
                }

                coctailsTree.setCocktailArrayList(cocktail_al);
            }
        });

        drinkCreate.submit_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
        });
    }

    void additionalFunctions() {
        addDlmElements();
        drinkCreate.transferDlmAndList(dlm,list,cocktail_al,coctailsTree);
    }

    public void bst_create() {
                ObjectInputStream ois = null;
                try {
                    if (file.length() != 0) {
                        ois = new ObjectInputStream(new FileInputStream("Cocktails"));
                        while (true) {
                            Cocktail cocktail = (Cocktail) ois.readObject();

                            coctailsTree.insert(cocktail);
                            cocktail_al.add(cocktail);
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

        for (int i = 0; i < coctailsTree.getCocktailArrayList().size(); i++) {
            dlm.addElement(coctailsTree.getCocktailArrayList().get(i));
        }

    }
}
