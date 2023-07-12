import javax.swing.*;
import java.awt.*;

public class IdPanel extends JPanel {

    private TextField T_Name = new TextField();
    private TextField T_LastName = new TextField();
    private TextField T_Age = new TextField();

    public IdPanel(){

        T_Name.setPreferredSize(new Dimension(50,25));
        T_LastName.setPreferredSize(new Dimension(50,25));
        T_Age.setPreferredSize(new Dimension(50,25));

        this.setBackground(Color.GRAY);

        this.add(T_Name);
        this.add(T_LastName);
        this.add(T_Age);

        repaint();

    }
    public void paintComponent(Graphics graphics){

        super.paintComponent(graphics);

        graphics.drawRect(0,0,getWidth()-1,getHeight()-1);

        graphics.setFont(new Font(null, Font.PLAIN,25));

        graphics.drawString("Name: " , 10 , 40);
        graphics.drawString("Last Name: " , 10 , 80);
        graphics.drawString("Age: " , 10 , 120);

        T_Name.setLocation(100,20);
        T_LastName.setLocation(150,60);
        T_Age.setLocation(80,100);
    }
    public String getT_Name() {
        return T_Name.getText();
    }
    public String getT_LastName() {
        return T_LastName.getText();
    }
    public String getT_Age() {
        return T_Age.getText();
    }
}
