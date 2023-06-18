package Main;

import javax.swing.*;
import java.awt.*;

public class General_Info_UI extends JFrame{
	JLabel name_label = new JLabel(" Cocktail Name:");
    JLabel nameInfo_label = new JLabel();
    JLabel withAlcohol_label = new JLabel("With Alcohol :");
    JLabel withAlcoholInfo_label = new JLabel();
    JLabel alcoholRate_label = new JLabel("Alcohol Rate:");
    JLabel alcoholRateInfo_label = new JLabel();
    JLabel fruits_label = new JLabel("Fruits:");
    JLabel fruitsInfo_label = new JLabel();
    JLabel aroma_label = new JLabel("Aroma:");
    JLabel aromaInfo_label = new JLabel();
    JLabel price_label = new JLabel("Price:");
    JLabel priceInfo_label = new JLabel();
    JPanel c_panel = new JPanel(new GridLayout(6, 2, 15, 10));

    public General_Info_UI() {
        super("General Info");
        setUI();
    }
    void setUI() {
        setSize(400, 320);
        setLocation(640,270);
        setLayout(new BorderLayout());
        c_panel.add(name_label);
        c_panel.add(nameInfo_label);
        c_panel.add(aroma_label);
        c_panel.add(aromaInfo_label);
        c_panel.add(fruits_label);
        c_panel.add(fruitsInfo_label);
        c_panel.add(withAlcohol_label);
        c_panel.add(withAlcoholInfo_label);
        c_panel.add(alcoholRate_label);
        c_panel.add(alcoholRateInfo_label);
        c_panel.add(price_label);
        c_panel.add(priceInfo_label);
        add(c_panel, BorderLayout.CENTER);
    }
}
