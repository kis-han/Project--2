/*
Stuart Reges and Marty Stepp
February 24, 2007
Some modifications by Tom Bylander in 2010

The DrawingPanel class provides a simple interface for drawing persistent
images using a Graphics object.  An internal BufferedImage object is used
to keep track of what has been drawn.  A client of the class simply
constructs a DrawingPanel of a particular size and then draws on it with
the Graphics object, setting the background color if they so choose.

To ensure that the image is always displayed, a timer calls repaint at
regular intervals.
*/

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.event.*;

public class DrawingPanel implements ActionListener {
    private static final int DELAY = 100;  // delay between repaints in millis
    private static final boolean PRETTY = false;  // true to anti-alias

    private int width, height;    // dimensions of window frame
    private JFrame frame;         // overall window frame
    private JPanel panel;         // overall drawing surface
    private BufferedImage image;  // remembers drawing commands
    private Graphics2D g2;        // graphics context for painting
    private JLabel statusBar;     // status bar showing mouse position

    // construct a drawing panel of given width and height enclosed in a window
    public DrawingPanel(int width, int height) {
        this.width = width;
        this.height = height;
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        statusBar = new JLabel(" ");
        statusBar.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(width, height));
        panel.add(new JLabel(new ImageIcon(image)));

        // listen to mouse movement
        MouseInputAdapter listener = new MouseInputAdapter() {
            public void mouseMoved(MouseEvent e) {
                statusBar.setText("(" + e.getX() + ", " + e.getY() + ")");
            }

            public void mouseExited(MouseEvent e) {
                statusBar.setText(" ");
            }
        };
        panel.addMouseListener(listener);
        panel.addMouseMotionListener(listener);

        g2 = (Graphics2D)image.getGraphics();
        g2.setColor(Color.BLACK);
        if (PRETTY) {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setStroke(new BasicStroke(1.1f));
        }

        frame = new JFrame("Drawing Panel");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.getContentPane().add(statusBar, "South");
        frame.pack();
        frame.setVisible(true);
        toFront();

        // repaint timer so that the screen will update
        new Timer(DELAY, this).start();
    }

    // used for an internal timer that keeps repainting
    public void actionPerformed(ActionEvent e) {
        panel.repaint();
    }

    // obtain the Graphics object to draw on the panel
    public Graphics2D getGraphics() {
        return g2;
    }

    // set the background color of the drawing panel
    public void setBackground(Color c) {
        panel.setBackground(c);
    }

    // show or hide the drawing panel on the screen
    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    // makes the program pause for the given amount of time,
    // allowing for animation
    public void sleep(int millis) {
        panel.repaint();
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {}
    }

    // makes drawing panel become the frontmost window on the screen
    public void toFront() {
        frame.toFront();
    }
}
