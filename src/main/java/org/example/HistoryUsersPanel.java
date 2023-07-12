package org.example;

import javax.swing.*;
import java.awt.*;

public class HistoryUsersPanel extends JPanel {

    Id[] lastRequests = new Id[10];

    public HistoryUsersPanel(Id[] lastRequests){

        this.lastRequests = lastRequests;

        this.setBackground(Color.WHITE);
    }
    public void paintComponent(Graphics graphics){

        super.paintComponent(graphics);

        graphics.setFont(new Font(null, Font.PLAIN,20));

        for(int i = 0 ; i < this.lastRequests.length ; i++){
            graphics.drawString("Name: " + lastRequests[i].getName() , 10,20 + i*100);
            graphics.drawString("Request: " + lastRequests[i].getLastRequest() , 10,40 + i*100);
            graphics.drawString("Time: " + lastRequests[i].getLastRequestT() , 10,60 + i*100);
        }

    }
}
