package view.game;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Box extends JComponent {
    private final int value = 10;

    private Image boxImage;

    public Box(int width, int height, String imagePath) {
        this.setSize(width, height);
        this.setLocation(5, 5);

        boxImage = new ImageIcon(imagePath).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (boxImage != null) {
            // Draw the image
            g.drawImage(boxImage, 0, 0, getWidth(), getHeight(), this);
        } else {
            // If the image is null, draw a placeholder rectangle
            g.setColor(Color.RED);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

//    public void paintComponent(Graphics g) {
//        g.setColor(Color.ORANGE);
//        g.fillRect(0, 0, getWidth(), getHeight());
//        Border border = BorderFactory.createLineBorder(Color.black, 1);
//        this.setBorder(border);
//    }

    public int getValue() {
        return value;
    }
}
