package view.login;

import view.FrameUtil;
import view.level.LevelFrame;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RegisterFrame extends javax.swing.JFrame{
    private JTextField username;
    private JTextField password;
    private JTextField confirmPassword;
    private JButton submitBtn;
    private JButton resetBtn;
    private LevelFrame levelFrame;
    private String savePath="./src/savings/";
    private Image backgroundImg;

    public RegisterFrame(int width, int height,LoginFrame loginFrame) {
        this.setTitle("Register Frame");
        this.setLayout(null);
        this.setSize(width + 100, height + 100);

        backgroundImg = new ImageIcon("src/view/sokobanBg.png").getImage();

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImg, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null);
        this.setContentPane(backgroundPanel);

        JLabel userLabel = FrameUtil.createJLabel(this, new Point(220, 210), 70, 40, "Username:");
        JLabel passLabel = FrameUtil.createJLabel(this, new Point(220, 270), 70, 40, "Password:");
        JLabel confirmLabel = FrameUtil.createJLabel(this, new Point(220, 330), 70, 40, "Confirm:");
        JLabel checkLabel = FrameUtil.createJLabel(this, new Point(140, 280), 200, 40, "");
        username = FrameUtil.createJTextField(this, new Point(300, 210), 120, 40);
        password = FrameUtil.createJTextField(this, new Point(300, 270), 120, 40);
        confirmPassword = FrameUtil.createJTextField(this, new Point(300, 330), 120, 40);
        submitBtn = FrameUtil.createButton(this, "Confirm", new Point(240, 400), 100, 40);
        resetBtn = FrameUtil.createButton(this, "Reset", new Point(380, 400), 100, 40);



        submitBtn.addActionListener(e -> {
//            System.out.println("Username = " + username.getText());
//            System.out.println("Password = " + password.getText());
            String USER=username.getText();
            String PASSWORD=password.getText();
            String CONFIRMPASSWORD=confirmPassword.getText();
            File userfile=new File("./src/data/users.txt");
            File passwordfile=new File("./src/data/password.txt");
            java.util.List<String> users=new ArrayList<String>();
            try(BufferedReader userReader=new BufferedReader(new FileReader(userfile))){

                String line;
                while ((line = userReader.readLine())!=null){
                    users.add(line);
                }
                userReader.close();
            }catch(IOException ex){
                ex.printStackTrace();
            }
            List<String> passwords=new ArrayList<String>();
            try(BufferedReader passwordReader=new BufferedReader(new FileReader(passwordfile))){

                String line;
                while ((line = passwordReader.readLine())!=null){
                    passwords.add(line);
                }
                passwordReader.close();
            }catch(IOException ex){
                ex.printStackTrace();
            }
            Boolean flag=true;
            for(int i=0; i<users.size(); i++){
                if(USER.equals(users.get(i))){
                    flag=false;
                }
            }
            if(flag){
                if(PASSWORD.equals(CONFIRMPASSWORD)){
                    passwords.add(CONFIRMPASSWORD);
                    users.add(USER);
                    try(BufferedWriter userWriter=new BufferedWriter(new FileWriter(userfile))){
                        for(int i=0; i<users.size(); i++){
                            userWriter.write(users.get(i)+"\n");
                        }
                        userWriter.flush();
                        userWriter.close();
                    }catch(IOException ex){
                        ex.printStackTrace();
                    }
                    try(BufferedWriter passwordWriter=new BufferedWriter(new FileWriter(passwordfile))){
                        for(int i=0; i<passwords.size(); i++){
                            passwordWriter.write(passwords.get(i)+"\n");
                        }
                        passwordWriter.flush();
                        passwordWriter.close();
                    }catch (IOException ex){
                        ex.printStackTrace();
                    }
                    File newSave=new File(savePath+USER);
                    if(!newSave.exists()){
                        newSave.mkdir();
                    }
                    JOptionPane.showMessageDialog(this,"Success!");
                    this.setVisible(false);
                    this.dispose();
                    loginFrame.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(this, "Different Password Inputs");
                }
            }
            if(!flag){
                JOptionPane.showMessageDialog(this, "UserNameExisted!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            //todo: check login info

        });
        resetBtn.addActionListener(e -> {
            username.setText("");
            password.setText("");
            confirmPassword.setText("");
        });

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void setLevelFrame(LevelFrame levelFrame) {
        this.levelFrame = levelFrame;
    }


}
