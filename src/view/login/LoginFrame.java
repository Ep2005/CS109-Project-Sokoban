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
    private JButton confirmBtn;
    private JButton registerBtn;
    private JButton guestmodeBtn;
    private LevelFrame levelFrame;
    private Image backgroundImg;


    public LoginFrame(int width, int height) {
        this.setTitle("Login Frame");
        this.setLayout(null);
        this.setSize(width, height);

        backgroundImg = new ImageIcon("src/view/sokobanBg.png").getImage();

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImg, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null); // Use absolute layout
        this.setContentPane(backgroundPanel);


        JLabel userLabel = FrameUtil.createJLabel(this, new Point(200, 160), 70, 40, "username:");
        JLabel passLabel = FrameUtil.createJLabel(this, new Point(200, 210), 70, 40, "password:");
        username = FrameUtil.createJTextField(this, new Point(270, 160), 120, 40);
        password = FrameUtil.createJTextField(this, new Point(270, 210), 120, 40);

        confirmBtn = FrameUtil.createButton(this, "Confirm", new Point(210, 260), 100, 40);
        registerBtn = FrameUtil.createButton(this, "Register", new Point(340, 260), 100, 40);
        guestmodeBtn = FrameUtil.createButton(this, "Guestmode", new Point(260, 310), 120, 40);

        confirmBtn.addActionListener(e -> {
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

            else{
                JOptionPane.showMessageDialog(null, "Username and password cannot be blank");
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
