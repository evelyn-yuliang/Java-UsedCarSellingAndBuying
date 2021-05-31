package guiLab.presentation;

import javax.swing.*;

import guiLab.data.DBAccess1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BuyAUsedCarSearch extends JFrame {

    //read from the file
    //show the info filtered.
    private JPanel jPanel;
    private JScrollPane sp;
    private boolean notFound = true;
    private ResultSet rs = null;
    private DBAccess1 dBO = new DBAccess1();
    
    public BuyAUsedCarSearch(String[] search)throws ClassNotFoundException, SQLException {
        //ArrayList<String> carArray = getCar();
        jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        this.getCar(jPanel, search);
        sp = new JScrollPane(jPanel);
        this.add(sp);
    }

    public void getCar(JPanel jPanel, String[] search) {
       // BufferedReader in = null;
       // String FIELD_SEP = "\t";

        try {
            rs = dBO.getUsedCar();
            //in = new BufferedReader(new FileReader("UsedCarFile"));
            JLabel jspace = new JLabel(" ");
            //String line = in.readLine();
            if(rs!=null) {
            	
            	 String title1= "<html>" +  "carID" + "   " + "carYear"+ "   " 
                         + "carMake" + "   " + "carModel" + "   " + "carColor" +
                         "   " + "condition" + "   " + "mileages" +  "   " + "updateTime" +"</html>" ;
            	 jPanel.add(new JLabel(title1, SwingConstants.CENTER));
            	 jPanel.add(new JLabel("  ",SwingConstants.CENTER));
            	while(rs.next()) {
            		
            		//no column for bodystyle
                if (
                        (search[0].equals("--select--") && search[1].equals(rs.getString(3)) &&
                                search[2].equals(rs.getString(4)) && search[3].equals("--select--")) ||
                        (search[0].equals("--select--") && search[1].equals(rs.getString(3)) &&
                                search[2].equals("--select--") && search[3].equals("--select--")) ||
                        (search[0].equals(rs.getString(2)) && search[1].equals(rs.getString(3)) &&
                                search[2].equals(rs.getString(4)) && search[3].equals("--select--"))) {
                	 JLabel jp = new JLabel(" ");
                	
                	String time = rs.getString(8);
                	String result ="<html>" +  rs.getString(1) + " "+ rs.getString(2)+" "+" "
                                    + rs.getString(3) + "  	 " + rs.getString(4) + " 	  " + rs.getString(5) +
                                    "  	 " + rs.getString(6) + "   	" + rs.getString(7) +  "  	 " + time.substring(0,10)+"</html>";
                	
                     
                	System.out.println(result);
                    jPanel.add(new JLabel(result, SwingConstants.CENTER));
                    jPanel.add(jp);
                    jPanel.add(jp);
                    notFound = false;
                }
                //line = in.readLine();
            }
            	
            	
            }

            
            if (notFound == true) {
                jPanel.add(new JLabel("<html>No data found</html>", SwingConstants.CENTER));
            }
          
            jPanel.add(jspace);
            jPanel.add(jspace);
            jPanel.add(jspace);
            String contact = "<html>Contact 416-444-1234 to schedule a Test-drive</html>";
           
            jPanel.add(new JLabel(contact, SwingConstants.CENTER));
            //return persons;
        } catch (Exception e) {
            System.err.println("There is an error during reading data of persons");

        }
//		finally {
//			in.close();
//		}

        
    }


}
