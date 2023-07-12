package org.example;

import javax.swing.*;

public class Frame extends JFrame{

    int Width = 800;
    int Height = 800;

    Panel panel = new Panel();
    public Frame(){

        this.setResizable(false);
        this.setSize(Width,Height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.add(panel);

    }
    public void showFrame(){
        this.setVisible(true);
    }
}