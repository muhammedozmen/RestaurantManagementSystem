package Main;

import javax.swing.*;
import java.awt.*;

public class Order_UI extends JFrame{
	
	//UI ELEMENTS
	
	JPanel n_panel = new JPanel();
    JPanel c_panel = new JPanel();
    JPanel s_panel = new JPanel(new FlowLayout());
    JPanel send_panel = new JPanel();
    
    JLabel orderInfo_label = new JLabel("Order Information Table #");
    
    DefaultListModel orderInfo_dlm = new DefaultListModel();
    
    JList orderInfo_list = new JList(orderInfo_dlm);
    
    JScrollPane orderInfo_sp = new JScrollPane(orderInfo_list);
    
    JButton complete_button = new JButton("Complete Order");
    
    Color pink = new Color(240,192,203);
    Color orange = new Color(255,150,0);

    public Order_UI() {
        super("Order Panel");
        setLayout(new BorderLayout());
        setSize(350,270);
        setLocation(640,270);
        setUI();
    }

    void setUI() {
    	n_panel.add(orderInfo_label);
        add(n_panel, BorderLayout.NORTH);
        n_panel.setBackground(pink);
        c_panel.add(orderInfo_sp);
        add(c_panel, BorderLayout.CENTER);
        c_panel.setBackground(pink);
        send_panel.add(complete_button);
        complete_button.setBackground(orange);
        s_panel.add(send_panel);
        send_panel.setBackground(pink);
        add(s_panel, BorderLayout.SOUTH);
        s_panel.setBackground(pink);   
    }
}
