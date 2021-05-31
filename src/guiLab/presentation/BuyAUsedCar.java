package guiLab.presentation;

import guiLab.data.UsedCarRandomAccess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;

//todo need update!

public class BuyAUsedCar extends JFrame {

    private JLabel lbtitle, lbyear, lbmake, lbmodel, lbbodystyle, lbcolor, lbkm, lbcon, lbcondition;
    private JComboBox<String> cbyear, cbmake, cbmodel, cbodystyle, ccolor, cbcon;
    private JTextField txtkm;
    private JButton butSearch, butReset;
    private JRadioButton jcexcellent, jcgood, jcfair;
    private ButtonGroup bg;
    private String[] usedCarList;

    public BuyAUsedCar() {
        initialize();

    }

    private void initialize() {
        lbtitle = new JLabel("Enter your vehicle information");
        lbyear = new JLabel("Year");
        String[] years = {"--select--", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019"};
        cbyear = new JComboBox<String>(years);

        lbmake = new JLabel("Make");
        //String[] makes = {"--select--","Honda", "Toyota", "Mazada"};
        ArrayList<String> makes = new ArrayList<String>();
        UsedCarRandomAccess ra = new UsedCarRandomAccess();
        makes = ra.getMake();
        String[] array = makes.toArray(new String[makes.size()]);
        cbmake = new JComboBox<String>(array);

        lbmodel = new JLabel("Model");

        ArrayList<String> models = new ArrayList<String>();
        models = ra.getAllModel();
        models.add(0, "--select--");
        String[] array1 = models.toArray(new String[models.size()]);
        cbmodel = new JComboBox<String>(array1);

        lbbodystyle = new JLabel("Bodystyle");
        String[] bodystyles = {"--select--", "SUV", "Crossover", "Sedan", "Truck", "Hatchback"};
        cbodystyle = new JComboBox<String>(bodystyles);


        butSearch = new JButton("Search");
        butSearch.setMnemonic(KeyEvent.VK_S);
        butReset = new JButton("Close");
        butReset.setMnemonic(KeyEvent.VK_R);

        this.setLayout(new GridLayout(9, 2));
        this.add(lbyear);
        this.add(cbyear);
        this.add(lbmake);
        this.add(cbmake);
        this.add(lbmodel);
        this.add(cbmodel);
        this.add(lbbodystyle);
        this.add(cbodystyle);

        this.add(butSearch);
        this.add(butReset);
        //this.setToolTipText("Search for quotes for your car");
        butReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();

            }
        });

        butSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] search = new String[4];
                search[0] = cbyear.getSelectedItem().toString();
                search[1] = cbmake.getSelectedItem().toString();
                search[2] = cbmodel.getSelectedItem().toString();
                search[3] = cbodystyle.getSelectedItem().toString();

                //IF FOUND, IF NOT FOUND
                BuyAUsedCarSearch test;
				try {
					test = new BuyAUsedCarSearch(search);
					 test.setVisible(true);
		                test.setSize(500, 400);
		                test.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                //test.setLayout(new GridLayout(1,1));
               

            }
        });

        cbmake.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                int selectedIndex = 0;
                selectedIndex = cbmake.getSelectedIndex();
                ArrayList<String> models = new ArrayList<String>();
                models = ra.getModel(selectedIndex);
                String[] array = models.toArray(new String[models.size()]);
                cbmodel.removeAllItems();
                cbmodel.addItem("--select--");
                for (int i = 1; i < array.length; i++)
                    cbmodel.addItem(array[i]);
                //System.out.print(selectedIndex);
            }
        });

    }


}
