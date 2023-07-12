package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstPanel extends JPanel implements ActionListener {

    private JButton[] options = new JButton[5];
    private boolean[] isOptions = new boolean[5];
    private int sum = 0;

    public FirstPanel(){

        this.setBackground(Color.WHITE);

        options[0] = new JButton("Jokes");
        options[1] = new JButton("Numbers Fact");
        options[2] = new JButton("Cat Fact");
        options[3] = new JButton("Country Information");
        options[4] = new JButton("Quotes");


        for (JButton option : options) {
            option.setSize(50, 25);
            option.addActionListener(this);
            this.add(option);
        }

    }
    public void paintComponent(Graphics graphics){

        super.paintComponent(graphics);

        graphics.setFont(new Font(null, Font.PLAIN,20));

        for (int i = 0 ; i < options.length ; i++){
            if(isOptions[i]){
                options[i].setBackground(Color.GREEN);
            }else {
                options[i].setBackground(Color.WHITE);
            }
            options[i].setLocation(40 , i*60 + 80);
        }

        if(sum == 3){
            for (int i = 0 ; i < options.length ; i++){
                options[i].setEnabled(isOptions[i]);
            }
        }else {
            for (JButton option : options) {
                option.setEnabled(true);
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0 ; i < options.length ; i++){
            if(e.getSource() == options[i]){
                if(isOptions[i]){
                    isOptions[i] = false;
                    sum--;
                }else {
                    isOptions[i] = true;
                    sum++;
                }
            }
        }
        repaint();
    }

    public boolean[] getIsOptions() {
        return isOptions;
    }

    public void setIsOptions(boolean[] isOptions) {
        this.isOptions = isOptions;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
