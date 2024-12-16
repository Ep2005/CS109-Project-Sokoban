package controller;

import Util.FileUtil;
import model.MapMatrix;
import view.game.GameFrame;
import view.game.GamePanel;
import view.level.LevelFrame;

import javax.swing.*;
import java.util.List;

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

    public void loadGame(String path ,JFrame frame){
        System.out.println(path);
        List<String> lines = FileUtil.readFileToList(path);
        int[][] map = new int[lines.size()-1][];
        for (int i=0; i< lines.size()-1; i++){
            String[] elements = lines.get(i).split(" ");
            map[i] = new int[elements.length];
            for (int j=0; j<elements.length; j++){
                map[i][j] = Integer.parseInt(elements[j]);
            }
        }
        int steps = Integer.parseInt(lines.get(lines.size()-1));

        frame.dispose();
        MapMatrix mapMatrix = new MapMatrix(map);
        GameFrame gameFrame = new GameFrame(600,450,mapMatrix);
        GamePanel gamePanel = gameFrame.getGamePanel();
        gamePanel.setSteps(steps);
        gamePanel.getStepLabel().setText(String.format("Step: %d", steps));
        gameFrame.setVisible(true);
    }

}
