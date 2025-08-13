import java.awt.*;

import javax.swing.*;

public class ScrambleFrame extends JFrame  {
ScrambleFrame(){
this.setTitle("scrambler");
this.setBackground(Color.black);
this.setVisible(true);
this.setResizable(true);
this.setSize(600,600);
this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
this.setLocationRelativeTo(null);
this.add(new ScramblePanel());

}
}
