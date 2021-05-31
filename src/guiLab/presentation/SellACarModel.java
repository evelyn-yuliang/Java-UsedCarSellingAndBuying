package guiLab.presentation;

import guiLab.business.Car;
import guiLab.business.Person;
import guiLab.data.CarDao;
import guiLab.data.DBAccess1;
import guiLab.data.PersonDAO;
import guiLab.data.PersonDAOBinary;
import guiLab.data.UsedCarDAOText;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SellACarModel extends JFrame {
    private JPanel textPanel, butPanel, userPanel;
    private JButton butSignin, butSignup, butexit, butclear;
    private JPasswordField txtPasswd;
    private JTextField txtName;
    private String userName;
    

   // private PersonDAO pDAO = new PersonDAOBinary();
   // private CarDao cDAO = new UsedCarDAOText();
    private DBAccess1 dBO = new DBAccess1();

    
    public SellACarModel(String[] usedCarList)throws ClassNotFoundException, SQLException {

        String result =
                "<html>Car year:" + usedCarList[0] +
                        "<br/>Car make:" + usedCarList[1] +
                        "<br/>Car model:" + usedCarList[2] +
                        "<br/>Car bodystyle:" + usedCarList[3] +
                        "<br/>Car color:" + usedCarList[4] +
                        "<br/>Mileages" + usedCarList[5] +
                        "<br/>Condition:" + usedCarList[6] + "</html>";
        String s1 = "Vehicle Information";
        textPanel = new JPanel();
        //textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        textPanel.setLayout(new GridLayout(4, 1));
        textPanel.add(new JLabel(s1, SwingConstants.CENTER));
        textPanel.add(new JLabel(result, SwingConstants.CENTER));
        textPanel.add(new JLabel("Quote: CAD 15,000", SwingConstants.CENTER));
        textPanel.add(new JLabel("please sign in to save to this car to the database! ", SwingConstants.CENTER));
        this.add(textPanel);

        butSignin = new JButton("Sign in");
        butSignin.setMnemonic(KeyEvent.VK_I);
        butSignup = new JButton("Sign up");
        butSignup.setMnemonic(KeyEvent.VK_U);
        butclear = new JButton("Clear");
        butclear.setMnemonic(KeyEvent.VK_C);

        //new panel
        userPanel = new JPanel(new GridLayout(2, 2));

        userPanel.add(new JLabel("User name:"));

        txtName = new JTextField();
        txtName.setToolTipText("Enter user name  with 6 characters to log in");
        userPanel.add(txtName);
        userPanel.add(new JLabel("Password:"));
        txtPasswd = new JPasswordField();
        txtPasswd.setEchoChar('*');
        txtPasswd.setToolTipText("Enter password with less than 6 characters");
        userPanel.add(txtPasswd);

        JPanel mg = new JPanel();
        mg.setLayout(new BoxLayout(mg, BoxLayout.Y_AXIS));
        mg.add(userPanel);


        butPanel = new JPanel(new FlowLayout());
        butPanel.add(butSignin);
        butPanel.add(butSignup);
        butPanel.add(butclear);

        mg.add(butPanel);
        this.add(mg);

        butSignin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                if (isValidData()) {
                    Person person;
					try {
						userName=txtName.getText();
						person = dBO.getUser(txtName.getText(), new String(txtPasswd.getPassword()));
				
						 if (person != null) {
		                        if (saveCar(usedCarList))
		                            JOptionPane.showMessageDialog(null, "Data is saved \n" + result);
		                        
		                    } 
						 else if (!dBO.isValidNewUserName(txtName.getText())){
							 JOptionPane.showMessageDialog(null, "The password is wrong, please try again!");
						 }
						 else {
		                        JOptionPane.showMessageDialog(null, "Please sign up first!");
		                    }
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                   
                }
                

            }
        });

        butSignup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Person person =null;
            	userName = txtName.getText();
		
				if (isValidData()) {
					if (dBO.isValidNewUserName(userName)) 
					{
					    String passWord = new String(txtPasswd.getPassword());
					    person = new Person(userName, passWord);
					    if (dBO.addUser(person))
					      JOptionPane.showMessageDialog(null, "Username and password is saved \n, you can log in!!");
					} else
					    JOptionPane.showMessageDialog(null, userName + " is used by others, please try again", "Invalid Entry", JOptionPane.ERROR_MESSAGE);
				
				}

            }
        });


        butclear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //dispose();
                txtName.setText("");
                txtPasswd.setText("");
            }
        });
    }


    public boolean isValidData() {
        if (!Validator.isUserName(txtName, txtName.getText()))
            return false;
        if (!Validator.isPassword(txtPasswd, txtPasswd.getPassword()))
            return false;
        return true;
    }

    public boolean saveCar(String[] usedCarList) {

        usedCarList[7] = "15,000";
        Car car = new Car(Integer.parseInt(usedCarList[0]), usedCarList[1], usedCarList[2], usedCarList[3], usedCarList[4], Integer.parseInt(usedCarList[5]), usedCarList[6], usedCarList[7],userName);

        if (dBO.addUsedCar(car)) {
            return true;
        }
        return false;

    }


}

