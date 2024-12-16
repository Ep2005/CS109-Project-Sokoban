package view.login;
import java.io.*;
import java.util.ArrayList;
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
    private JButton resetBtn;
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
        backgroundPanel.setLayout(null);
        this.setContentPane(backgroundPanel);


        JLabel userLabel = FrameUtil.createJLabel(this, new Point(200, 160), 70, 40, "Username:");
        JLabel passLabel = FrameUtil.createJLabel(this, new Point(200, 210), 70, 40, "Password:");
        JLabel checkLabel = FrameUtil.createJLabel(this, new Point(60, 180), 200, 40, "");
        username = FrameUtil.createJTextField(this, new Point(270, 160), 120, 40);
        password = FrameUtil.createJTextField(this, new Point(270, 210), 120, 40);

        submitBtn = FrameUtil.createButton(this, "Confirm", new Point(210, 260), 105, 40);
        registerBtn = FrameUtil.createButton(this, "Register", new Point(340, 260), 105, 40);
        guestmodeBtn = FrameUtil.createButton(this, "Guestmode", new Point(340, 310), 105, 40);
        resetBtn = FrameUtil.createButton(this, "Reset", new Point(210, 310), 105, 40);

//        confirmBtn.addActionListener(e -> {
//            File file=new File("C:\\Users\\Ernest Phang\\IdeaProjects\\Phang Ern Young - Sokoban\\Account.txt");
//            ArrayList<String> users=new ArrayList<>();
//            ArrayList<String> passwords=new ArrayList<>();
//            Scanner f;
//            try{
//                f = new Scanner(file);
//                while (f.hasNext()){
//                    users.add(f.next());
//                    passwords.add(f.next());}
//
//            }catch (IOException g){
//                System.out.println("error");
//            }
//
//            String username1 = username.getText();
//
//            if (users.contains(username1)){
//                if (passwords.get(users.indexOf(username1)).equals(password.getText())){
//                    JOptionPane.showMessageDialog(null, "Login Successful");
//                    this.levelFrame.setVisible(true);
//                    this.setVisible(false);
//                }
//                else {
//                    JOptionPane.showMessageDialog(null, "User does not exist or wrong Password");
//                }
//            }
//
//            else{
//                JOptionPane.showMessageDialog(null, "Username and password cannot be blank");
//            }
//
//        });
//
//        registerBtn.addActionListener(e -> {
//            File file=new File("C:\\Users\\Ernest Phang\\IdeaProjects\\Phang Ern Young - Sokoban\\Account.txt");
//            ArrayList<String> users=new ArrayList<>();
//            Scanner f;
//            try{
//                f=new Scanner(file);
//                while (f.hasNext()){
//                    users.add(f.next());
//                    f.next();}
//
//            }catch (IOException g){
//                System.out.println("error");
//            }
//
//            try{
//                BufferedWriter a=new BufferedWriter(new FileWriter("Account.txt",true));
//                String username1=username.getText();
//                if(!users.contains(username1) && !username1.isEmpty()) {
//                    String password1 = password.getText();
//                    a.write(username1 + " " + password1 + "\n");
//                    JOptionPane.showMessageDialog(null, "Registered Successfully");
//
//                } else if (username1.isEmpty()) {
//                    JOptionPane.showMessageDialog(null, "Username and password cannot be blank");
//                } else{
//                    JOptionPane.showMessageDialog(null, "Username is empty or is already in use");
//                }
//                a.flush();
//                a.close();
//            }
//            catch (IOException ee){
//                System.out.println("error");
//            }
//
//        });

        submitBtn.addActionListener(e -> {
            //File file=new File("C:\\Users\\Ernest Phang\\IdeaProjects\\Phang Ern Young - Sokoban\\Account.txt");
            String USER=username.getText();
            String PASSWORD=password.getText();
            File userfile=new File("./src/data/users.txt");
            File passwordfile=new File("./src/data/password.txt");
            ArrayList<String> users=new ArrayList<>();
            try(BufferedReader userReader=new BufferedReader(new FileReader(userfile))){
                String lines;
                while ((lines = userReader.readLine())!=null){
                    users.add(lines);
                }
            }catch(IOException ex){
                ex.printStackTrace();
            }
            ArrayList<String> passwords=new ArrayList<>();
            try(BufferedReader passwordReader=new BufferedReader(new FileReader(passwordfile))){

                String line;
                while ((line = passwordReader.readLine())!=null){
                    passwords.add(line);
                }
            }catch(IOException ex){
                ex.printStackTrace();
            }

            Boolean flag=false;
            for(int i=0; i<users.size(); i++){
                if(USER.equals(users.get(i))){
                    if(PASSWORD.equals(passwords.get(i))){
                        flag=true;
                    }
                    else flag=false;
                }
            }
            if (this.levelFrame != null && flag) {
                this.levelFrame.setCURRENTUSER(USER);
                //this.levelFrame.repaint();
                this.levelFrame.setVisible(true);
                this.setVisible(false);
            }
            if(!flag){
                checkLabel.setText("Wrong Username or Password");
                checkLabel.setForeground(Color.RED);
                checkLabel.setFont(new Font("Arial", Font.PLAIN, 10));
            }


//            //todo: check login info
        });

        resetBtn.addActionListener(e -> {
            username.setText("");
            password.setText("");
        });

        registerBtn.addActionListener(e -> {
            RegisterFrame regFrame = new RegisterFrame(width, height,this);
            regFrame.setVisible(true);
            this.setVisible(false);
        });

        guestmodeBtn.addActionListener(e -> {
            if (this.levelFrame != null) {
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
