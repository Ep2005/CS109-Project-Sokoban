package view.game;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;

import controller.FrameController;
import controller.GameController;
import model.Direction;
import model.MapMatrix;
import view.FrameUtil;
import view.level.LevelFrame;

public class GameFrame extends JFrame {

    private GameController controller;
    private JButton restartBtn;
    private JButton loadBtn;
    private JButton returnBtn;
    private JButton upBtn;
    private JButton downBtn;
    private JButton rightBtn;
    private JButton leftBtn;

    private static FrameController frameController = new FrameController();

    private JLabel stepLabel;
    private JLabel controlsLabel;
    private GamePanel gamePanel;


    public GameFrame(int width, int height, MapMatrix mapMatrix) {
        this.setTitle("Sokoban");
        this.setLayout(null);
        this.setSize(width + 160, height);
        gamePanel = new GamePanel(mapMatrix);
        gamePanel.setLocation(30, height / 2 - gamePanel.getHeight() / 2);
        this.add(gamePanel);
        this.controller = new GameController(gamePanel, mapMatrix, this);


        this.restartBtn = FrameUtil.createButton(this, "Restart", new Point(gamePanel.getWidth() + 80, 120), 80, 50);
        this.loadBtn = FrameUtil.createButton(this, "Load", new Point(gamePanel.getWidth() + 280, 120), 80, 50);
        this.returnBtn = FrameUtil.createButton(this, "Return", new Point(gamePanel.getWidth() + 180, 120), 80, 50);
        this.upBtn = FrameUtil.createButton(this,"up", new Point(gamePanel.getWidth() + 180,250), 80, 50);
        this.downBtn = FrameUtil.createButton(this, "down", new Point(gamePanel.getWidth() + 180,300),80,50);
        this.rightBtn = FrameUtil.createButton(this,"right",new Point(gamePanel.getWidth() + 260,275),80,50);
        this.leftBtn = FrameUtil.createButton(this, "left",new Point(gamePanel.getWidth() + 100, 275),80,50);
        this.stepLabel = FrameUtil.createJLabel(this, "Start", new Font("serif", Font.ITALIC, 22), new Point(gamePanel.getWidth() + 190, 70), 180, 50);
        this.controlsLabel = FrameUtil.createJLabel(this, "Controls", new Font("serif", Font.ITALIC, 22), new Point(gamePanel.getWidth() + 180, 200), 180, 50);
        gamePanel.setStepLabel(stepLabel);

        this.restartBtn.addActionListener(e -> {
            controller.restartGame();
            gamePanel.requestFocusInWindow();//enable key listener
        });

        this.loadBtn.addActionListener(e -> {
            String string = JOptionPane.showInputDialog(this, "Input path:");
            System.out.println(string);
            gamePanel.requestFocusInWindow();//enable key listener
        });

        this.returnBtn.addActionListener(e -> {
            LevelFrame.getFrameController().returnLevelFrame(this);
            gamePanel.requestFocusInWindow();//enable key listener
        });

        this.upBtn.addActionListener(e ->{
            controller.doMove(gamePanel.getHero().getRow(),gamePanel.getHero().getCol(), Direction.UP);
            gamePanel.afterMove();
            gamePanel.requestFocusInWindow();

        });
        this.downBtn.addActionListener(e ->{
            controller.doMove(gamePanel.getHero().getRow(),gamePanel.getHero().getCol(),Direction.DOWN);
            gamePanel.afterMove();
            gamePanel.requestFocusInWindow();

        });
        this.rightBtn.addActionListener(e ->{
            controller.doMove(gamePanel.getHero().getRow(),gamePanel.getHero().getCol(),Direction.RIGHT);
            gamePanel.afterMove();
            gamePanel.requestFocusInWindow();

        });
        this.leftBtn.addActionListener(e ->{
            controller.doMove(gamePanel.getHero().getRow(),gamePanel.getHero().getCol(),Direction.LEFT);
            gamePanel.afterMove();
            gamePanel.requestFocusInWindow();

        });

        //todo: add other button here
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
