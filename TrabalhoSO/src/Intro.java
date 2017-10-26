import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Intro extends JFrame{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int x = 0, y = 0;
    Intro() {
        super("Writing on the JFrame");
        this.setBounds(100,100,300,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent me) {
                x = me.getX();
                y = me.getY();
                repaint();
            }
        });
        this.setVisible(true);
    }
    //here is the overridden paint method.
    public void paint(Graphics g) {
        super.paint(g);
        g.drawString("Click the mouse inside the window.",40,45);
        g.drawString("X : " + x,40,60);
        g.drawString("Y : " + y,40,75);
    }
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Intro();
            }
        });
    }
}