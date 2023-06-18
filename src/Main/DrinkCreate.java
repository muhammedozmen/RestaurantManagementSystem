package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

/* Menu view is made as default list model. If there are too many options, it can be scrolled down.
 * Default list model and combo box are used together because that's how it should be. We should have done them together.
 * Fruits are also split and detected with "-". */

public class DrinkCreate extends JFrame {
	private String fileName = "Cocktails";
    Coctails_Tree coctails_tree;
    
    File file = new File(fileName);

    // UI ELEMENTS
    JPanel c_panel = new JPanel(new GridLayout(6, 2, 15, 10));
    JPanel s_panel = new JPanel(new FlowLayout());
    
    JLabel name_label = new JLabel("Name: (No space)");
    JLabel withAlcohol_label = new JLabel("With Alcohol:");
    JLabel alcoholRate_label = new JLabel("Alcohol Rate:");
    JLabel fruits_label = new JLabel("Fruits: (fr1-fr2-fr3)");
    JLabel aroma_label = new JLabel("Aroma:");
    JLabel price_label = new JLabel("Price:");
    
    JTextField name_txt = new JTextField();
    JTextField alcoholRate_txt = new JTextField();
    JTextField fruits_txt = new JTextField();
    JTextField price_txt = new JTextField();
    
    JButton cancel_button = new JButton("Cancel");
    JButton clear_button = new JButton("Clear");
    JButton submit_button = new JButton("Submit");
    
    Color pink = new Color(240,192,203);
    Color orange = new Color(255,150,0);

    static Drink_Aroma aromas[] = Drink_Aroma.values();
    JComboBox<Drink_Aroma> aroma_cb = new JComboBox<>(aromas);

    String withAlcohol[] = {"true", "false"};
    JComboBox<String> withAlcohol_cb = new JComboBox<>(withAlcohol);

    public DrinkCreate() {
        super("Create Drink");
        setUI();
        setListeners();
    }

    void setUI() {
        setSize(400, 320);
        setLocation(640,270);
        setLayout(new BorderLayout());       
        c_panel.add(name_label);
        c_panel.add(name_txt);
        c_panel.add(aroma_label);
        c_panel.add(aroma_cb);
        c_panel.add(fruits_label);
        c_panel.add(fruits_txt);
        c_panel.add(withAlcohol_label);
        c_panel.add(withAlcohol_cb);
        c_panel.add(alcoholRate_label);
        c_panel.add(alcoholRate_txt);
        c_panel.add(price_label);
        c_panel.add(price_txt);
        add(c_panel, BorderLayout.CENTER);
        c_panel.setBackground(pink);
        s_panel.add(cancel_button);
        cancel_button.setBackground(orange);
        s_panel.add(clear_button);
        clear_button.setBackground(orange);
        s_panel.add(submit_button);
        submit_button.setBackground(orange);
        add(s_panel, BorderLayout.SOUTH);
        s_panel.setBackground(pink);
    }

    void setListeners() {	
    	submit_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!name_txt.getText().matches("[\\w]+")) {
                    JOptionPane.showMessageDialog(submit_button,
                            "If the name of the drink has more than one word, write it without a space in between.");  
                } else if (!(fruits_txt.getText().matches("[A-Za-z]+") || fruits_txt.getText().matches("[A-Za-z]+[-][A-Za-z]*") || fruits_txt.getText().matches("[A-Za-z]+[-][A-Za-z]*[-][A-Za-z]*"))) {
                    JOptionPane.showMessageDialog(submit_button,
                            "Fruit names must be separated by (-) and cannot contain any non-letter characters." +
                            "Additionally, you should only utilize English letters.");
                } else if (!(alcoholRate_txt.getText().matches("[\\d]{1,3}"))) {
                    JOptionPane.showMessageDialog(submit_button,
                            "Write no more than three integer numbers in the alcohol rate section.");
                } else if (withAlcohol_cb.getSelectedIndex() == 0 && alcoholRate_label.getText().equals("0")) {
                    JOptionPane.showMessageDialog(submit_button,
                            "Cocktails with alcohol must have an alcohol content greater than zero.");
                } else if (!(price_txt.getText().matches("[\\d]{1,3}"))) {
                    JOptionPane.showMessageDialog(submit_button,
                            "Write no more than three integer values in the pricing section.");
                } else {
                    ArrayList fruitList = new ArrayList();
                    String fruitArray[] = fruits_txt.getText().split("-");
                    boolean containsAlcohol = false;

                    for (int i = 0; i < fruitArray.length; i++) {
                        fruitList.add(fruitArray[i]);
                    }

                    if (withAlcohol_cb.getSelectedItem().equals("false")) {
                        containsAlcohol = false;
                    } else if (withAlcohol_cb.getSelectedItem().equals("true")) {
                        containsAlcohol = true;
                    }

                    Cocktail newCocktail = new Cocktail(name_txt.getText().trim(), containsAlcohol, Integer.parseInt(alcoholRate_txt.getText()), fruitList, (Drink_Aroma) aroma_cb.getSelectedItem(), Double.parseDouble(price_txt.getText()));

                    System.out.println(newCocktail);
                    if (submit_button.getText().equals("Submit")) {
                        registerNewCocktail(newCocktail);

                        cocktailArrayList.add(newCocktail);
                        dlm.clear();

                        int cocktailArrayLength = cocktailArrayList.size();
                        Cocktail cocktailArray[] = new Cocktail[cocktailArrayLength];

                        for (int i = 0; i < cocktailArrayList.size(); i++) {
                            cocktailArray[i] = cocktailArrayList.get(i);
                        }


                        for (int i = 0; i < cocktailArray.length; i++) {
                            for (int j = 0; j < cocktailArray.length; j++) {

                                if (j < cocktailArray.length - 1) {

                                    if (cocktailArray[j].getCoctailName().compareTo(cocktailArray[j + 1].getCoctailName()) > 0) {
                                        Cocktail temp = cocktailArray[j];
                                        cocktailArray[j] = cocktailArray[j + 1];
                                        cocktailArray[j + 1] = temp;
                                    }
                                }
                            }
                        }

                        cocktailArrayList.clear();

                        for (int i = 0; i < cocktailArray.length; i++) {
                            cocktailArrayList.add(cocktailArray[i]);
                            dlm.addElement(cocktailArray[i]);
                        }

                    } else if (submit_button.getText().equals("Update")) {
                        String element = jlist.getSelectedValue().toString().trim();
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

                        Cocktail cocktailToBeRemoved = new Cocktail(splitted[0], splitted[4].equals("true") ? true : false, Integer.parseInt(splitted[3]), fruitListForArrayList, drinkAroma, Double.parseDouble(splitted[5]));


                        int index = 0;

                        for (int i = 0; i < cocktailArrayList.size(); i++) {
                            if (cocktailArrayList.get(i).toString().equals(cocktailToBeRemoved.toString())) {
                                index = i;
                            }
                        }

                        cocktailArrayList.remove(index);  
                        cocktailArrayList.add(newCocktail);

                        PrintWriter pw = null;  
                        try {
                            pw = new PrintWriter(fileName);
                        } catch (FileNotFoundException ex) {
                            System.out.println("exp");
                        }
                        pw.close();

                        for (int i = 0; i < cocktailArrayList.size(); i++) {
                            registerNewCocktail(cocktailArrayList.get(i)); 
                        }
                        dlm.clear();


                        int cocktailArrayLength = cocktailArrayList.size();
                        Cocktail cocktailArray[] = new Cocktail[cocktailArrayLength];

                        for (int i = 0; i < cocktailArrayList.size(); i++) {
                            cocktailArray[i] = cocktailArrayList.get(i);
                        }


                        for (int i = 0; i < cocktailArray.length; i++) {
                            for (int j = 0; j < cocktailArray.length; j++) {

                                if (j < cocktailArray.length - 1) {

                                    if (cocktailArray[j].getCoctailName().compareTo(cocktailArray[j + 1].getCoctailName()) > 0) {
                                        Cocktail temp = cocktailArray[j];
                                        cocktailArray[j] = cocktailArray[j + 1];
                                        cocktailArray[j + 1] = temp;
                                    }
                                }
                            }
                        }

                        cocktailArrayList.clear();

                        for (int i = 0; i < cocktailArray.length; i++) {
                            cocktailArrayList.add(cocktailArray[i]);
                            dlm.addElement(cocktailArray[i]);
                        }

                        coctails_tree.setCocktailArrayList(cocktailArrayList);

                        System.out.println("Final Tree: " + coctails_tree.getCocktailArrayList());

                    }
                }
            }
        });
    	
    	clear_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });

    	alcoholRate_txt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (withAlcohol_cb.getSelectedIndex() == 0) {
                	alcoholRate_txt.setEnabled(true);
                } else {
                	alcoholRate_txt.setText("0");
                	alcoholRate_txt.setEnabled(false);
                }
            }
        });
    	
    	cancel_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear();
                setVisible(false);
            }
        });
    }

    DefaultListModel<Cocktail> dlm;
    JList jlist;
    ArrayList<Cocktail> cocktailArrayList;

    public void transferDlmAndList(DefaultListModel<Cocktail> dlm, JList jlist, ArrayList<Cocktail> cocktailArrayList, Coctails_Tree coctails_tree) {
        this.dlm = dlm;
        this.jlist = jlist;
        this.cocktailArrayList = cocktailArrayList;
        this.coctails_tree = coctails_tree;
    }

    void registerNewCocktail(Cocktail newCocktail) {
        ObjectOutputStream oos;

        try {
            if (file.length() != 0) {
                AppendingObjectOutputStream aoos;
                aoos = new AppendingObjectOutputStream(new FileOutputStream(fileName, true));
                aoos.writeObject(newCocktail);
                aoos.close();
            } else {
                oos = new ObjectOutputStream(new FileOutputStream(fileName));
                oos.writeObject(newCocktail);
                oos.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    void clear() {
    	name_txt.setText("");
    	aroma_cb.setSelectedIndex(0);
    	fruits_txt.setText("");	
    	withAlcohol_cb.setSelectedIndex(0);
    	alcoholRate_txt.setText("");
    	price_txt.setText("");
    }
}

class AppendingObjectOutputStream extends ObjectOutputStream {
    public AppendingObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }
    @Override
    protected void writeStreamHeader() throws IOException {
        reset();
    }
}
