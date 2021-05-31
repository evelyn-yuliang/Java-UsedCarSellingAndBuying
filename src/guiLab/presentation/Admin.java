package guiLab.presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Admin extends JPanel {
    private JTextField txtName;
    private JPasswordField txtPasswd;
    private JLabel labelName, labelPasswd, labelHint;
    private JButton btLogin, btClear;


    public void initialize() {

        labelName = new JLabel("User name:");
        this.add(labelName);

        txtName = new JTextField();
        txtName.setToolTipText("Enter user name  with 6 characters to log in");
        this.add(txtName);

        labelPasswd = new JLabel("Password:");
        this.add(labelPasswd);

        txtPasswd = new JPasswordField();
        txtPasswd.setEchoChar('*');
        txtPasswd.setToolTipText("Enter password with less than 6 characters");
        this.add(txtPasswd);

        btLogin = new JButton("Login");
        btLogin.setMnemonic(KeyEvent.VK_L);
        this.add(btLogin);

        //login button handler
        btLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

                if (isValidData()) {
                    labelHint.setText("");
                    AdminModifyCarInfo modify = new AdminModifyCarInfo();
                    modify.setLayout(new GridLayout(2, 1));
                    modify.setVisible(true);
                    modify.setSize(600, 700);
                    modify.setLocation(700, 350);
                    modify.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                }

            }
        });

        btClear = new JButton("Clear");
        btClear.setMnemonic(KeyEvent.VK_C);
        this.add(btClear);

        //clear button handler
        btClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                txtName.setText("");
                txtPasswd.setText("");
            }

        });

        labelHint = new JLabel();
        this.add(labelHint);
    }

    public Admin() {
        this.setLayout(new GridLayout(5, 2));
        this.initialize();
    }

    public boolean isValidData() {
        if (!Validator.isUserName(txtName, txtName.getText()))
            return false;
        if (!Validator.isPassword(txtPasswd, txtPasswd.getPassword()))
            return false;
        return true;
    }


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        JFrame t = new JFrame();
        Admin test = new Admin();
        t.add(test);
        t.setLayout(new GridLayout(2, 1));
        t.setVisible(true);
        t.setSize(600, 600);
        t.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }


}
