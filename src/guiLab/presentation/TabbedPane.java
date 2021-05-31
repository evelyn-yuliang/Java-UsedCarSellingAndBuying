package guiLab.presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class TabbedPane extends JPanel {
    private static JFrame frame;
    private static JPanel panel;

    public TabbedPane() {
        initializeUI();
    }

    public static void showFrame() {
        panel = new TabbedPane();
        panel.setOpaque(true);

        frame = new JFrame("Car pricing");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setLocation(50, 50);
    }
	    /*
	    protected ImageIcon createImageIcon(String path,String description) {
	    	java.net.URL imgURL = getClass().getResource(path);
	    	if (imgURL != null) {
	    		return new ImageIcon(imgURL, description);
	    	} else {
	    		System.err.println("Couldn't find file: " + path);
	    		return null;
	    	}
	    }
*/

    private void initializeUI() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setToolTipText("Welcome!");


        BuyACar buyANewCarPanel = new BuyACar();
        //ImageIcon icon = new ImageIcon("tabedpane/buy.jpg", "a pretty but meaningless splat");

        //tabbedPane.addTab("Buy a car",icon, buyANewCarPanel,"Buy a car");
        tabbedPane.addTab("Buy a car", buyANewCarPanel);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_B);

        SellACar SellACarPanel = new SellACar();
        //ImageIcon icon2 = new ImageIcon("tabedpane/sell.jpg", "a pretty but meaningless splat");
        //tabbedPane.addTab("Sell a car", icon2,SellACarPanel,"Sell a car");
        tabbedPane.addTab("Sell a car", SellACarPanel);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_S);

        Admin administratorPanel = new Admin();
        //ImageIcon icon3 = new ImageIcon("tabedpane/admin.jpg", "a pretty but meaningless splat");
        administratorPanel.add(new JLabel("Administrator"));
        //tabbedPane.addTab("Administrator",icon3, administratorPanel,"Adimin");
        tabbedPane.addTab("Administrator", administratorPanel);
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_M);

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(500, 400));
        this.add(tabbedPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        TabbedPane.showFrame();
    }
}
