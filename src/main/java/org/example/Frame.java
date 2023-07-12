import javax.swing.*;

public class Frame extends JFrame{

    int Width = 600;
    int Height = 800;

    Panel panel = new Panel(Width,Height);

    public Frame(){

        this.setResizable(false);
        this.setSize(Width,Height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Body Stats");

        this.add(panel);
    }
    public void showFrame(){
        this.setVisible(true);
    }
}