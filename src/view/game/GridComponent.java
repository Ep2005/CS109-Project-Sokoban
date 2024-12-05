package view.game;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GridComponent extends JComponent {
    private int row;
    private int col;
    private final int id; // represents the units digit value. It cannot be changed during one game.

    private Hero hero;
    private Box box;
    static Color color = new Color(246, 246, 229);

    private Image grassImage;
    private Image wallImage;

    public GridComponent(int row, int col, int id, int gridSize) {
        this.setSize(gridSize, gridSize);
        this.row = row;
        this.col = col;
        this.id = id;

        grassImage = new ImageIcon("src/view/game/images/grassTile.png").getImage();
        wallImage = new ImageIcon("src/view/game/images/wall.jpg").getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.printComponents(g);
        Color borderColor = null;
        switch (id % 10) {
            case 1:
//                g.setColor(Color.LIGHT_GRAY);
//                g.fillRect(0, 0, getWidth(), getHeight());
//                borderColor = Color.DARK_GRAY;
//                break;
                if (wallImage != null) {
                    // Draw the image
                    g.drawImage(wallImage, 0, 0, getWidth(), getHeight(), this);
                } else {
                    // If the image is null, draw a placeholder rectangle
                    g.setColor(Color.RED);
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
                break;
            case 0:
//                g.setColor(Color.WHITE);
//                g.fillRect(0, 0, getWidth(), getHeight());
//                break;
                if (grassImage != null) {
                    // Draw the image
                    g.drawImage(grassImage, 0, 0, getWidth(), getHeight(), this);
                } else {
                    // If the image is null, draw a placeholder rectangle
                    g.setColor(Color.RED);
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
                break;
            case 2:
//                g.setColor(Color.WHITE);
//                g.fillRect(0, 0, getWidth(), getHeight());
                g.setColor(Color.GREEN);
                if (grassImage != null) {
                    // Draw the image
                    g.drawImage(grassImage, 0, 0, getWidth(), getHeight(), this);
                } else {
                    // If the image is null, draw a placeholder rectangle
                    g.setColor(Color.RED);
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
                int[] xPoints = {getWidth() / 2, getWidth(), getWidth() / 2, 0};
                int[] yPoints = {0, getHeight() / 2, getHeight(), getHeight() / 2};
                g.fillPolygon(xPoints, yPoints, 4);
                g.setColor(Color.BLACK);
                g.drawPolygon(xPoints, yPoints, 4);
                break;
        }
        Border border = BorderFactory.createLineBorder(borderColor, 0);
        this.setBorder(border);
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

    public int getId() {
        return id;
    }

    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    //When adding a hero in this grid, invoking this method.
    public void setHeroInGrid(Hero hero) {
        this.hero = hero;
        this.add(hero);
    }

    //When adding a box in this grid, invoking this method.
    public void setBoxInGrid(Box box) {
        this.box = box;
        this.add(box);
    }
    //When removing hero from this grid, invoking this method
    public Hero removeHeroFromGrid() {
        this.remove(this.hero);//remove hero component from grid component
        Hero h = this.hero;
        this.hero = null;//set the hero attribute to null
        this.revalidate();//Update component painting in real time
        this.repaint();
        return h;
    }
    //When removing box from this grid, invoking this method
    public Box removeBoxFromGrid() {
        this.remove(this.box);//remove box component from grid component
        Box b = this.box;
        this.box = null;//set the hero attribute to null
        this.revalidate();//Update component painting in real time
        this.repaint();
        return b;
    }
}
