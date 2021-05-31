package guiLab.presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class BuyACar extends JPanel {
    private JButton butNewCar, butUsedCar;

    public BuyACar() {
        this.setLayout(new BorderLayout());
        JLabel headerLabel = new JLabel("Click on your choice:");
        headerLabel.setPreferredSize(new Dimension(40, 100));
        this.add(headerLabel, BorderLayout.NORTH);

        JPanel buttons = new JPanel(new FlowLayout());
        this.add(buttons, BorderLayout.CENTER);

        butNewCar = new JButton("Buy a new car");
        butNewCar.setPreferredSize(new Dimension(200, 200));
        butNewCar.setMnemonic(KeyEvent.VK_N);
        butUsedCar = new JButton("Buy a used car");
        butUsedCar.setPreferredSize(new Dimension(200, 200));
        butUsedCar.setMnemonic(KeyEvent.VK_U);
        buttons.add(butNewCar);
        buttons.add(butUsedCar);

        this.setToolTipText("Search for a new or second-hand car");

        butNewCar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BuyANewCar test = new BuyANewCar();
                test.setVisible(true);
                test.setSize(500, 400);
                test.setLocation(300, 50);
                test.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


            }

        });
        butUsedCar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BuyAUsedCar test = new BuyAUsedCar();
                test.setVisible(true);
                test.setSize(500, 400);
                test.setLocation(400, 50);
                test.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }

        });
    }

    public static void main(String[] args) {
        BuyACar b = new BuyACar();
        JFrame frame = new JFrame();
        frame.add(b);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }


}
