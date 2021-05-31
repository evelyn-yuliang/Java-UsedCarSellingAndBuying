package guiLab.presentation;

import guiLab.data.UsedCarRandomAccess;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class SellACar extends JPanel{
	
	private JLabel lbtitle,lbyear,lbmake,lbmodel,lbbodystyle,lbcolor,lbkm,lbcon,lbcondition;
	private JComboBox<String> cbyear,cbmake,cbmodel,cbodystyle,ccolor,cbcon;
	private JTextField txtkm;
	private JButton butSearch,butReset;
	private JRadioButton jcexcellent,jcgood,jcfair;
	private ButtonGroup bg;
	private String[] usedCarList;
	public SellACar() {
		initialize();
		
	}
	
	private void initialize() {
		lbtitle = new JLabel("Enter your vehicle information");
		lbyear = new JLabel("Year");
		
		String[] years = { "2000","2001", "2002","2003","2004","2005","2006","2007","2008", "2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019"};
		cbyear = new JComboBox<String>(years);
		
		lbmake = new JLabel("Make");
		//String[] makes = {"Acura", "Alfa", "Romeo","Aston","Martin","Audi","Bentley","BMW","Honda"};
		ArrayList<String> makes = new ArrayList<String>();
		UsedCarRandomAccess ra = new UsedCarRandomAccess();
		makes = ra.getMake();
		String[] array = makes.toArray(new String[makes.size()]);
		cbmake = new JComboBox<String>(array);
		
		lbmodel = new JLabel("Model");
		String[] models = {"-select--","Accord","CR-V","Civic","Fit","Odyssey" };
		cbmodel = new JComboBox<String>(models);
		
		lbbodystyle = new JLabel("Bodystyle");
		String[] bodystyles = {"SUV","Crossover","Sedan","Truck","Hatchback"};
		cbodystyle = new JComboBox<String>(bodystyles);
		
		lbcolor = new JLabel("Color");
		String[] color = {"Black","White","Red","Blue","Grey"};
		ccolor = new JComboBox<String>(color);
		
		lbkm = new JLabel("Mileages");
		txtkm = new JTextField();
		
		lbcondition = new JLabel("Condition");
		jcexcellent = new JRadioButton ("Excellent",true);
		jcgood = new JRadioButton ("Good");
		jcfair =  new JRadioButton ("Fair");
		
		JPanel rdoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		rdoPanel.add(jcexcellent);
		rdoPanel.add(jcgood);
		rdoPanel.add(jcfair);
	
		
		bg = new ButtonGroup();
		bg.add(jcexcellent);
		bg.add(jcgood);
		bg.add(jcfair);

		
		butSearch = new JButton("Get a Quote");
		butSearch.setMnemonic(KeyEvent.VK_S);
		butReset = new JButton("Reset");
		butReset.setMnemonic(KeyEvent.VK_R);

		

		this.setLayout(new GridLayout(9,2));
		this.add(lbyear);
		this.add(cbyear);
		this.add(lbmake);
		this.add(cbmake);
		this.add(lbmodel);
		this.add(cbmodel);
		this.add(lbbodystyle);
		this.add(cbodystyle);
		this.add(lbcolor);
		this.add(ccolor);
		this.add(lbkm);
		this.add(txtkm);	
		this.add(lbcondition);
		this.add(rdoPanel);
		
		this.add(butSearch);
		this.add(butReset);
		this.setToolTipText("Search for quotes for your car");
		 
		cbmake.addActionListener (new ActionListener () {
			  
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				 int selectedIndex = 0;
				  selectedIndex = cbmake.getSelectedIndex();
				 ArrayList<String> models = new ArrayList<String>();
				 models = ra.getModel(selectedIndex);
				 String[] array = models.toArray(new String[models.size()]);
				 cbmodel.removeAllItems();
				 for(int i=1;i<array.length;i++)
				 cbmodel.addItem(array[i]);
				//System.out.print(selectedIndex);
			}
		});
	
	butSearch.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			usedCarList = new String[8];
			usedCarList[0]=cbyear.getSelectedItem().toString(); 
			usedCarList[1]=cbmake.getSelectedItem().toString();
			usedCarList[2]=cbmodel.getSelectedItem().toString();
			usedCarList[3]=cbodystyle.getSelectedItem().toString();
			usedCarList[4]=ccolor.getSelectedItem().toString();
			usedCarList[5]=txtkm.getText();
			String j;
			if(jcexcellent.isSelected()) 
				 j = jcexcellent.getText();
			else if(jcgood.isSelected()) 
				j=jcgood.getText();
			else
				j=jcfair.getText();
			usedCarList[6]= j;
				
			if (isValidData()) {
				SellACarModel u1;
				try {
					u1 = new SellACarModel(usedCarList);
					u1.setLayout(new GridLayout(2,1));
					//u1.setLayout(new BoxLayout(u1, BoxLayout.Y_AXIS));
				u1.setVisible(true);
				u1.setSize(500, 400);
				u1.setLocation(400, 50);
				u1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			
			}
			
		}
		
	});
	
	butReset.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			 cbyear.setSelectedIndex(0);
			 cbmake.setSelectedIndex(0);
			 cbmodel.setSelectedIndex(0);
			 cbodystyle.setSelectedIndex(0);
			 ccolor.setSelectedIndex(0);
			 jcexcellent.setSelected(true);
			 txtkm.setText("");
			 
		}
	});

	}
	
	public boolean isValidData() {
		if (!Validator.isInteger(txtkm, "Mileage")) {
				return false;
		}
		if(!Validator.isModel(cbmodel.getSelectedItem().toString(),"Model")) {
			return false;
	}
		return true;
	}
}