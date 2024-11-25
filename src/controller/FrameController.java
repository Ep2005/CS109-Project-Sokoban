package controller;

import view.game.GameFrame;
import view.level.LevelFrame;

import javax.swing.*;

public class FrameController {
    private LevelFrame levelFrame;
    private GameFrame gameFrame;

    public LevelFrame getLevelFrame() {
        return levelFrame;
    }

    public void setLevelFrame(LevelFrame levelFrame) {
        this.levelFrame = levelFrame;
    }

    public void returnLevelFrame(JFrame frame) {
        frame.dispose();
        levelFrame.setVisible(true);
    }

}
