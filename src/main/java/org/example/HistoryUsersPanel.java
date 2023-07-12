package org.example;

import javax.swing.*;
import java.awt.*;

public class HistoryUsersPanel extends JPanel {

    private Id[] lastRequests = new Id[10];

    public HistoryUsersPanel(){
        for(int i = 0 ; i < lastRequests.length ; i++){
            lastRequests[i] = new Id();
        }
        this.setBackground(Color.WHITE);
    }

    public Id[] getLastRequests() {
        return lastRequests;
    }

    public void setLastRequests(Id[] lastRequests) {
        this.lastRequests = lastRequests;
    }

    public void paintComponent(Graphics graphics){

        super.paintComponent(graphics);

        graphics.setFont(new Font(null, Font.PLAIN,20));

        for(int i = 0 ; i < this.lastRequests.length ; i++){
            String name = "null";
            String lastRequest = "null";
            String lastRequestTime = "null";

            if(lastRequests[i] != null){
                name = lastRequests[i].getName();
                lastRequest = lastRequests[i].getLastRequest();
                lastRequestTime = lastRequests[i].getLastRequestT();
            }

            if(i < 5) {
                graphics.drawString("Name: " + name, 10, 20 + i * 70);
                graphics.drawString("Request: " + lastRequest, 10, 40 + i * 70);
                graphics.drawString("Time: " + lastRequestTime, 10, 60 + i * 70);
            }else {
                graphics.drawString("Name: " + name, 210, 20 + (i-5) * 70);
                graphics.drawString("Request: " + lastRequest, 210, 40 + (i-5) * 70);
                graphics.drawString("Time: " + lastRequestTime, 210, 60 + (i-5) * 70);
            }
        }
    }
}
