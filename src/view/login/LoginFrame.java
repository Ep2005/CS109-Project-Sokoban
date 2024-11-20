package view.login;
import java.util.ArrayList;
import java.util.Scanner;
import view.FrameUtil;
import view.level.LevelFrame;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;


public class LoginFrame extends JFrame {
    private JTextField username;
    private JTextField password;
    private JButton submitBtn;
    private JButton regBtn;
    private LevelFrame levelFrame;


    public LoginFrame(int width, int height) {
        this.setTitle("Login Frame");
        this.setLayout(null);
        this.setSize(width, height);
        JLabel userLabel = FrameUtil.createJLabel(this, new Point(50, 20), 70, 40, "username:");
        JLabel passLabel = FrameUtil.createJLabel(this, new Point(50, 80), 70, 40, "password:");
        username = FrameUtil.createJTextField(this, new Point(120, 20), 120, 40);
        password = FrameUtil.createJTextField(this, new Point(120, 80), 120, 40);

        submitBtn = FrameUtil.createButton(this, "Confirm", new Point(40, 140), 100, 40);
        regBtn = FrameUtil.createButton(this, "register", new Point(160, 140), 100, 40);

        submitBtn.addActionListener(e -> {
            File file=new File("C:\\Users\\Ernest Phang\\IdeaProjects\\Phang Ern Young - Sokoban\\Account.txt");
            ArrayList<String> users=new ArrayList<>();
            ArrayList<String> password=new ArrayList<>();
            Scanner f;
            try{
                f=new Scanner(file);
                while (f.hasNext()){
                    users.add(f.next());
                    f.next();}

            }catch (IOException g){
                System.out.println("error");
            }

            try{
                BufferedWriter a=new BufferedWriter(new FileWriter("Account.txt",true));
                String username1=username.getText();
                if(!users.contains(username1)) {


                }
                else{
                    JOptionPane.showMessageDialog(null, "Username is already in use");
                }
                a.flush();
                a.close();
            }
            catch (IOException ee){
                System.out.println("error");
            }

            if (this.levelFrame != null) { //if cannot login this no work)
                this.levelFrame.setVisible(true);
                this.setVisible(false);
            }
            //todo: check login info

        });
        regBtn.addActionListener(e -> {
            File file=new File("C:\\Users\\Ernest Phang\\IdeaProjects\\Phang Ern Young - Sokoban\\Account.txt");
            ArrayList<String> users=new ArrayList<>();
            Scanner f;
            try{
                f=new Scanner(file);
                while (f.hasNext()){
                    users.add(f.next());
                    f.next();}

            }catch (IOException g){
                System.out.println("error");
            }

            try{
                BufferedWriter a=new BufferedWriter(new FileWriter("Account.txt",true));
                String username1=username.getText();
                if(!users.contains(username1)) {


                    String password1 = password.getText();
                    a.write(username1 + " " + password1 + "\n");
                }
                else{

                    JOptionPane.showMessageDialog(null, "Username is already in use");
                }
                a.flush();
                a.close();
            }
            catch (IOException ee){
                System.out.println("error");
            }

        });

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void setLevelFrame(LevelFrame levelFrame) {
        this.levelFrame = levelFrame;
    }
}
