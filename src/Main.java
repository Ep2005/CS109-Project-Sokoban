import view.level.LevelFrame;
import view.login.LoginFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame(500,400);
            loginFrame.setVisible(true);
            LevelFrame levelFrame = new LevelFrame(500,400);
            levelFrame.setVisible(false);
            loginFrame.setLevelFrame(levelFrame);

            Sound sound = new Sound();
            sound.setFile(0);
            sound.play();
        });
    }
}
