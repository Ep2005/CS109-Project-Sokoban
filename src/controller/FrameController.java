package controller;

import view.level.LevelFrame;

import javax.swing.*;

public class FrameController {
    private LevelFrame levelFrame;

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
