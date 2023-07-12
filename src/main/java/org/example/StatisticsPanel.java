package org.example;

import javax.swing.*;
import java.awt.*;

public class StatisticsPanel extends JPanel {

    private int sumOfRequests;
    private int sumOfUsers;
    private String mostActiveUser;
    private String mostPopularRequest;

    public StatisticsPanel(){
        this.setBackground(Color.WHITE);
    }
    public void paintComponent(Graphics graphics){

        super.paintComponent(graphics);

        graphics.setFont(new Font(null, Font.PLAIN,20));

        graphics.drawString("The sum of requests is: " + this.sumOfRequests , 0 , 50);
        graphics.drawString("The sum of users is: " + this.sumOfUsers , 0 , 120);
        graphics.drawString("The most Active user is: " + this.mostActiveUser , 0 , 190);
        graphics.drawString("The most popular request is: " + this.mostPopularRequest , 0 , 260);

    }

    public int getSumOfRequests() {
        return sumOfRequests;
    }

    public void setSumOfRequests(int sumOfRequests) {
        this.sumOfRequests = sumOfRequests;
    }

    public int getSumOfUsers() {
        return sumOfUsers;
    }

    public void setSumOfUsers(int sumOfUsers) {
        this.sumOfUsers = sumOfUsers;
    }

    public String getMostActiveUser() {
        return mostActiveUser;
    }

    public void setMostActiveUser(String mostActiveUser) {
        this.mostActiveUser = mostActiveUser;
    }

    public String getMostPopularRequest() {
        return mostPopularRequest;
    }

    public void setMostPopularRequest(String mostPopularRequest) {
        this.mostPopularRequest = mostPopularRequest;
    }
}
