package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AllOrders_UI extends JFrame{
	Queue cocktailLinkedQueue = new Queue();

	// UI ELEMENTS
    JPanel NPanel = new JPanel();
    JPanel CPanel = new JPanel();
    JPanel SPanel = new JPanel(new FlowLayout());
    JPanel SendOrderPanel = new JPanel();
    
    JLabel Title_Label = new JLabel("All Orders");
    
    JButton SendOrder_Button = new JButton("Send Order");
    
    DefaultListModel Queue_dlm = new DefaultListModel();
    
    JList OrderQueue_List = new JList(Queue_dlm);
    
    JScrollPane OrderQueueInfo_SP = new JScrollPane(OrderQueue_List);
    
    Color pink = new Color(240,192,203);
    Color orange = new Color(255,150,0);


    public AllOrders_UI() {
        super("All Orders Menu");
        setLayout(new BorderLayout());
        setSize(500,340);
        setLocation(640,270);
        setUI();
        setListener();
    }

    void setUI() {
    	NPanel.add(Title_Label);
        add(NPanel, BorderLayout.NORTH);
        NPanel.setBackground(pink);
        
        CPanel.add(OrderQueueInfo_SP);
        add(CPanel, BorderLayout.CENTER);
        CPanel.setBackground(pink);
        
        SendOrderPanel.add(SendOrder_Button);
        SendOrder_Button.setBackground(orange);
        
        SPanel.add(SendOrderPanel);
        add(SPanel, BorderLayout.SOUTH);
        SPanel.setBackground(pink);
        SendOrderPanel.setBackground(pink);     
    }

    void setListener() {
    	SendOrder_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cocktailLinkedQueue.isEmpty() == false) {
                	Queue_dlm.remove(0);
                    cocktailLinkedQueue.deleteQueue();
                } else {
                    return;
                }
            }
        });
    }
}
