package view.login;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import view.FrameUtil;
import view.level.LevelFrame;

import javax.swing.*;
import java.awt.*;


public class LoginFrame extends JFrame {
    private JTextField username;
    private JTextField password;
    private JButton submitBtn;
    private JButton registerBtn;
    private JButton guestmodeBtn;
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
        registerBtn = FrameUtil.createButton(this, "Register", new Point(160, 140), 100, 40);
        guestmodeBtn = FrameUtil.createButton(this, "Guestmode", new Point(80, 200), 120, 40);

        submitBtn.addActionListener(e -> {
            File file=new File("C:\\Users\\Ernest Phang\\IdeaProjects\\Phang Ern Young - Sokoban\\Account.txt");
            ArrayList<String> users=new ArrayList<>();
            ArrayList<String> passwords=new ArrayList<>();
            Scanner f;
            try{
                f = new Scanner(file);
                while (f.hasNext()){
                    users.add(f.next());
                    passwords.add(f.next());}

            }catch (IOException g){
                System.out.println("error");
            }

            String username1 = username.getText();

            if (users.contains(username1)){
                if (passwords.get(users.indexOf(username1)).equals(password.getText())){
                    JOptionPane.showMessageDialog(null, "Login Successful");
                    this.levelFrame.setVisible(true);
                    this.setVisible(false);
                }
                else {
                    JOptionPane.showMessageDialog(null, "User does not exist or wrong Password");
                }
            }

        });

        registerBtn.addActionListener(e -> {
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
                    JOptionPane.showMessageDialog(null, "Registered Successfully");
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

        guestmodeBtn.addActionListener(e -> {
            if (this.levelFrame != null) { //if cannot login this no work)
                this.levelFrame.setVisible(true);
                this.setVisible(false);
            }
        });

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void setLevelFrame(LevelFrame levelFrame) {
        this.levelFrame = levelFrame;
    }

}
