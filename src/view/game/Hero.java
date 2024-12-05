//package view.game;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class Hero extends JComponent {
//    private int row;
//    private int col;
//
//    private final int value = 20;
//
//    private static Color color = new Color(87, 171, 220);
//
//    private ImageIcon heroImage;
//
//    public Hero(int width, int height, int row, int col, String imagePath) {
//        this.row = row;
//        this.col = col;
//        this.setSize(width, height);
//        this.setLocation(8, 8);
//        heroImage = new ImageIcon("C:\\Users\\Ernest Phang\\IdeaProjects\\Phang Ern Young - Sokoban\\src\\view\\game\\mario.png").getImage();
//    }
//
//    public void paintComponent(Graphics g) {
//      g.setColor(Color.BLACK);
//      g.fillOval(0, 0, getWidth(), getHeight());
//      g.setColor(color);
//       g.fillOval(1,1,getWidth()-2,getHeight()-2);
//    }
//

//    public int getValue() {
//        return value;
//    }
//
//    public int getRow() {
//        return row;
//    }
//
//    public void setRow(int row) {
//        this.row = row;
//    }
//
//    public int getCol() {
//        return col;
//    }
//
//    public void setCol(int col) {
//        this.col = col;
//    }
//}

package view.game;

import javax.swing.*;
import java.awt.*;

public class Hero extends JComponent {
    private int row;
    private int col;
    private final int value = 20;

    private Image heroImage; // The image for the Hero

    public Hero(int width, int height, int row, int col, String imagePath) {
        this.row = row;
        this.col = col;
        this.setSize(width, height);
        this.setLocation(8, 8);

        // Load the image from the specified path
        heroImage = new ImageIcon(imagePath).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (heroImage != null) {
            // Draw the image
            g.drawImage(heroImage, 0, 0, getWidth(), getHeight(), this);
        } else {
            // If the image is null, draw a placeholder rectangle
            g.setColor(Color.RED);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    public int getValue() {
        return value;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}

