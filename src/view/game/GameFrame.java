package view.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JButton saveBtn;

    private static FrameController frameController = new FrameController();

    private JLabel stepLabel;
    private JLabel controlsLabel;
    private JLabel timeLabel;
    private GamePanel gamePanel;

    private Timer timer;
    private int elapsedTime;

    public GameFrame(int width, int height, MapMatrix mapMatrix) {
        this.setTitle("Sokoban");
        this.setLayout(null);
        this.setSize(width + 160, height);
        gamePanel = new GamePanel(mapMatrix);
        gamePanel.setLocation(30, height / 2 - gamePanel.getHeight() / 2);
        this.add(gamePanel);
        this.controller = new GameController(gamePanel, mapMatrix, this);

        this.restartBtn = FrameUtil.createButton(this, "Restart", new Point(gamePanel.getWidth() + 120, 90), 80, 50);
        this.returnBtn = FrameUtil.createButton(this, "Return", new Point(gamePanel.getWidth() + 220, 90), 80, 50);
        this.loadBtn = FrameUtil.createButton(this, "Load", new Point(gamePanel.getWidth() + 120, 150), 80, 50);
        this.saveBtn = FrameUtil.createButton(this, "Save",new Point(gamePanel.getWidth() + 220, 150),80,50);

        this.upBtn = FrameUtil.createButton(this,"up", new Point(gamePanel.getWidth() + 180,250), 80, 50);
        this.downBtn = FrameUtil.createButton(this, "down", new Point(gamePanel.getWidth() + 180,300),80,50);
        this.rightBtn = FrameUtil.createButton(this,"right",new Point(gamePanel.getWidth() + 260,275),80,50);
        this.leftBtn = FrameUtil.createButton(this, "left",new Point(gamePanel.getWidth() + 100, 275),80,50);


        this.stepLabel = FrameUtil.createJLabel(this, "Start", new Font("serif", Font.ITALIC, 22), new Point(gamePanel.getWidth() + 190, 50), 180, 50);
        this.controlsLabel = FrameUtil.createJLabel(this, "Controls", new Font("serif", Font.ITALIC, 22), new Point(gamePanel.getWidth() + 180, 200), 180, 50);
        gamePanel.setStepLabel(stepLabel);
        this.timeLabel = FrameUtil.createJLabel(this,"00:00:00", new Font("serif", Font.ITALIC,22), new Point(gamePanel.getWidth() + 180, 20), 180, 50);

        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                elapsedTime += 1000;
                updateTimeLabel();
            }
        });
        timer.start();

        this.restartBtn.addActionListener(e -> {
            timer.stop();
            elapsedTime = 0;
            updateTimeLabel();
            controller.restartGame();
            gamePanel.requestFocusInWindow();//enable key listener
            timer.start();
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

        this.saveBtn.addActionListener(e ->{
            timer.stop();
            String path = JOptionPane.showInputDialog(this, "Save path:");
            controller.saveGame(path);
            gamePanel.requestFocusInWindow();

        });

        this.loadBtn.addActionListener(e -> {
            String path = JOptionPane.showInputDialog(this, "Input path:");
            LevelFrame.getFrameController().loadGame(path,this);
            timer.start();
            gamePanel.requestFocusInWindow();//enable key listener
        });

        //todo: add other button here
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public JLabel getStepLabel() {
        return stepLabel;
    }

    public void setStepLabel(JLabel stepLabel) {
        this.stepLabel = stepLabel;
    }

    private void updateTimeLabel() {
        int hours = elapsedTime / 3600000;
        int minutes = (elapsedTime % 3600000) / 60000;
        int seconds = (elapsedTime % 60000) / 1000;
        String time = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        timeLabel.setText(time);
    }

    public int getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(int elapsedTime) {
        this.elapsedTime = elapsedTime;
    }
}
