import java.awt.Color;
import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ScramblePanel extends JPanel implements ActionListener{
	final static int  SCREEN_HEIGHT=600;
	final static int  SCREEN_WIDTH=600;
	 char[] letters = {'A', 'B', 'C', 'D', 'E'};
	 int centerX = 200 + 200 / 2;
	 int centerY = 200 + 200 / 2;
	 int charSpacing = 20;
	 int startX = centerX - (letters.length * charSpacing) / 2;
	 int startY = centerY + 5;


ScramblePanel()
{   this.setLayout(null);
	this.setBackground(Color.DARK_GRAY);
	this.add(button());
}
public Component button()
{
	JButton button = new JButton("X");
	button.setBounds(270, 280, 60, 40);
	button.setBackground(Color.CYAN);
	button.setForeground(Color.WHITE);
	button.setFont(button.getFont().deriveFont(16f));

	button.addActionListener( new ActionListener()
			{
			public void actionPerformed(ActionEvent e) {
				scramble();
			};
			});
	return button;
	
}
public void paintComponent(Graphics g)
{
	super.paintComponent(g);
	draw(g);
	
	
}
public void draw(Graphics g) {
	
    Graphics2D g2 = (Graphics2D) g;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    GradientPaint gradient = new GradientPaint(0, 0, Color.CYAN, getWidth(), getHeight(), Color.GRAY);
    g2.setPaint(gradient);
    g2.fillRect(0, 0, getWidth(), getHeight());
    int circleX = 200;
    int circleY = 200;
    int circleSize = 200;
    int radius = circleSize / 2;

    // Circle center
    int cx = circleX + radius;
    int cy = circleY + radius;

    // Draw the circle
    GradientPaint circleGradient = new GradientPaint(
    	    circleX, circleY, Color.CYAN,
    	    circleX + circleSize, circleY + circleSize, Color.BLUE
    	);
    	g2.setPaint(circleGradient);
    	g2.fillOval(circleX, circleY, circleSize, circleSize);

    // Draw letters along an arc inside the circle
    g2.setColor(Color.BLACK);
    g2.setFont(g2.getFont().deriveFont(20f));
    FontMetrics fm = g2.getFontMetrics();

    double startAngle = -Math.PI / 2; // Start at top
    double angleStep = 2 * Math.PI / letters.length;

    // Reduce the radius so letters are drawn inside
    int textRadius = radius - 40; // 20px inward from circumference

    for (int i = 0; i < letters.length; i++) {
        char ch = letters[i];
        double angle = startAngle + i * angleStep;

        // Position inside circle
        double x = cx + textRadius * Math.cos(angle);
        double y = cy + textRadius * Math.sin(angle);

        g2.translate(x, y);
        g2.rotate(angle + Math.PI / 2);
        g2.drawString(String.valueOf(ch), -fm.charWidth(ch) / 2, 0);
        g2.rotate(-(angle + Math.PI / 2));
        g2.translate(-x, -y);
    }
}
public void scramble()
{
    Random rand = new Random();

    // Fisher–Yates shuffle
    for (int i = letters.length - 1; i > 0; i--) {
        int j = rand.nextInt(i + 1); // random index from 0 to i
        // swap
        char temp = letters[i];
        letters[i] = letters[j];
        letters[j] = temp;
        }
    repaint();
}
@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}


}
