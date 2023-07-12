package org.example;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.sql.Time;

public class Panel extends JPanel implements ActionListener {

    FirstPanel firstPanel = new FirstPanel();
    StatisticsPanel statisticsPanel = new StatisticsPanel();
    HistoryUsersPanel historyUsersPanel = new HistoryUsersPanel();
    Graph graph = new Graph(new int[]{0,0,0});
    JButton submit = new JButton("Submit");
    Bot bot;
    Image image;
    boolean start = false;

    public Panel(){

        submit.setBackground(Color.WHITE);
        submit.setSize(50,25);
        submit.addActionListener(this);

        this.setBackground(Color.WHITE);
        this.add(firstPanel);
        this.add(submit);
        this.add(statisticsPanel);
        this.add(historyUsersPanel);

        UI_Loop();
    }
    public void paintComponent(Graphics graphics){

        super.paintComponent(graphics);

        graphics.setFont(new Font(null, Font.PLAIN,20));

        this.firstPanel.setBounds(0,0,getWidth(),getHeight()/2);

        this.submit.setLocation(370,500);

        if(this.start){
            this.statisticsPanel.setBounds(getWidth()/2 , 0 , getWidth()/2 , getHeight()/2);
            this.historyUsersPanel.setBounds(0,0, getWidth()/2 , getHeight()/2);
            try {
                URL url = new URL(graph.getChart().getUrl());
                Image image = ImageIO.read(url);
                graphics.drawImage(image,0,getHeight()/2, null);
            } catch (IOException e) {

            }

        }

    }
    public void UI_Loop(){
        new Thread(() ->{
            while (true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if(this.start) {
                    this.statisticsPanel.setSumOfRequests(bot.getSumOfRequests());
                    this.statisticsPanel.setSumOfUsers(bot.getSumOfUsers());
                    this.statisticsPanel.setMostActiveUser(bot.mostActiveUser());
                    this.statisticsPanel.setMostPopularRequest(bot.mostPopularRequest());

                    this.historyUsersPanel.setLastRequests(bot.getLastRequests());

                    this.graph = new Graph(this.bot.getRequestsLastDays());

                    if(new Time(System.currentTimeMillis()).toString().equals("00:00:00")){
                        this.bot.dayPass();
                    }
                }
                repaint();
            }
        }).start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submit){
            if(this.firstPanel.getSum() == 3) {
                this.bot = new Bot(this.firstPanel.getIsOptions());
                try {
                    TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
                    api.registerBot(bot);
                } catch (TelegramApiException ex) {
                    throw new RuntimeException(ex);
                }
                this.submit.setVisible(false);
                this.firstPanel.setVisible(false);
                this.start = true;
            }
        }
        repaint();
    }
}
