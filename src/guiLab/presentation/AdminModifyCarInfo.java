package guiLab.presentation;

import guiLab.business.Car;
import guiLab.data.CarDao;
import guiLab.data.DaoFactory;
import guiLab.data.GetMakeAndModelFromFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Objects;

public class AdminModifyCarInfo extends JFrame {
    private JPanel jPanel2;
    private JTextField txtCarNum;

    //initialize the panel
    public AdminModifyCarInfo() {
        JLabel labelCarNum = new JLabel("Enter car ID:");
        txtCarNum = new JTextField();

        JButton btSearch = new JButton("Search");
        btSearch.setMnemonic('S');
        btSearch.setPreferredSize(new Dimension(100, 60));
        JButton btDelete = new JButton("Delete");
        btDelete.setMnemonic('D');
        btDelete.setPreferredSize(new Dimension(100, 60));
        JButton btAdd = new JButton("Add");
        btAdd.setMnemonic('A');
        btAdd.setPreferredSize(new Dimension(100, 60));
        JButton btModify = new JButton("Modify");
        btModify.setMnemonic('M');
        btModify.setPreferredSize(new Dimension(100, 60));

        JPanel jPanel = new JPanel();
        jPanel.setBorder(BorderFactory.createTitledBorder("TOP"));
        jPanel.setLayout(new BorderLayout());
        JPanel buttons = new JPanel(new FlowLayout());
        JPanel top = new JPanel();
        top.add(labelCarNum);
        top.add(txtCarNum);
        txtCarNum.setPreferredSize(new Dimension(100, 50));
        jPanel.add(top, BorderLayout.NORTH);
        jPanel.add(buttons, BorderLayout.CENTER);
        buttons.add(btSearch);
        buttons.add(btDelete);
        buttons.add(btAdd);
        buttons.add(btModify);

        jPanel2 = new JPanel();
        jPanel2.setBorder(BorderFactory.createTitledBorder("BOTTOM"));
        JLabel labelMake = new JLabel("Make");
        GetMakeAndModelFromFile getMakeAndModel = new GetMakeAndModelFromFile();
        String[] make = getMakeAndModel.getMake();
        JComboBox<String> jcbMake = new JComboBox<>(make);
        JLabel labelModel = new JLabel("Model");
        String[] model = getMakeAndModel.getModelByMake(jcbMake.getSelectedItem().toString());
        JComboBox<String> jcbModel = new JComboBox<>(model);
        JLabel labelYear = new JLabel("Year");
        JTextField txtYear = new JTextField(4);
        String[] type = {"SUV", "MPV", "Minivan", "Crossover", "Hatchback", "Coupe", "Sedan", "Roadster", "Cabriolet", "Station"};
        JLabel labelType = new JLabel("Type");
        JComboBox<String> jcbType = new JComboBox<>(type);
        JLabel labelPrice = new JLabel("Price");
        JTextField txtPrice = new JTextField(6);
        JLabel labelDesc = new JLabel("Description");
        JTextArea txtDesc = new JTextArea(100, 4);
        JLabel labelImg = new JLabel("Image Link");
        JTextArea txtImg = new JTextArea(100, 4);
        JLabel labelUrl = new JLabel("URL");
        JTextArea txtUrl = new JTextArea(100, 4);
        JButton btSubmit = new JButton("Submit");
        btSubmit.setMnemonic('u');
        btSubmit.setPreferredSize(new Dimension(100, 40));
        JButton btClear = new JButton("Clear");
        btClear.setMnemonic('l');
        btClear.setPreferredSize(new Dimension(100, 40));
        JButton btCancel = new JButton("Cancel");
        btCancel.setMnemonic('c');
        btCancel.setPreferredSize(new Dimension(100, 40));


        jPanel2.setLayout(new BoxLayout(jPanel2, BoxLayout.Y_AXIS));
        JPanel j1 = new JPanel();
        j1.add(labelMake);
        j1.add(jcbMake);
        j1.add(labelModel);
        j1.add(jcbModel);
        JPanel j2 = new JPanel();
        j2.add(labelYear);
        j2.add(txtYear);
        j2.add(labelType);
        j2.add(jcbType);
        j2.add(labelPrice);
        j2.add(txtPrice);
        jPanel2.add(j1);
        jPanel2.add(j2);
        jPanel2.add(labelDesc);
        jPanel2.add(txtDesc);
        jPanel2.add(labelImg);
        jPanel2.add(txtImg);
        jPanel2.add(labelUrl);
        jPanel2.add(txtUrl);
        JPanel j3 = new JPanel();
        j3.add(btSubmit);
        j3.add(btClear);
        j3.add(btCancel);
        jPanel2.add(j3);

        this.add(jPanel);
        this.add(jPanel2);
        jPanel2.setVisible(false);

        CarDao carDao = DaoFactory.getCarDAO();

        btCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jPanel2.setVisible(false);
                jPanel.setVisible(true);
            }
        });
        //make combo box item listener
        jcbMake.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String[] model = getMakeAndModel.getModelByMake(jcbMake.getSelectedItem().toString());
                jcbModel.removeAllItems();
                for (String s : model)
                    jcbModel.addItem(s);
            }
        });

        // search button handler
        btSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jPanel2.setVisible(false);
                if (Validator.isPresent(txtCarNum, "Car number") && Validator.isInteger(txtCarNum, "Car number")) {
                    try {
                        int carNum = Integer.parseInt(txtCarNum.getText());
                        if (carDao.getCar(carNum) == null)
                            JOptionPane.showMessageDialog(null, "Car does not exist!", "alert", JOptionPane.ERROR_MESSAGE);
                        else {
                            Car searchedCar = carDao.getCar(carNum);
                            String info = "Car id: " + searchedCar.getId() + "\n" +
                                    "Make: " + searchedCar.getMake() + "\n" +
                                    "Model: " + searchedCar.getModel() + "\n" +
                                    "Price: $" + searchedCar.getPrice() + "\n" +
                                    "Description: " + searchedCar.getDescription();
                            JOptionPane.showMessageDialog(null, info,
                                    "Search result", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(null, "Invalid number!", "alert", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        //delete button handler
        btDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jPanel2.setVisible(false);
                if (Validator.isPresent(txtCarNum, "Car number") && Validator.isInteger(txtCarNum, "Car number")) {
                    String num = txtCarNum.getText();
                    int carNum = Integer.parseInt(num);
                    if (carDao.getCar(carNum) == null)
                        JOptionPane.showMessageDialog(null, "Delete failed. Car does not exist!", "alert", JOptionPane.ERROR_MESSAGE);
                    else {
                        Car carToBeDeleted = carDao.getCar(carNum);
                        int reply = JOptionPane.showConfirmDialog(null, "Car will be permanently deleted", "Please confirm", JOptionPane.YES_NO_OPTION);
                        if (reply == JOptionPane.YES_OPTION) {
                            JOptionPane.showMessageDialog(null, "Delete succeed!", "Confirm", JOptionPane.INFORMATION_MESSAGE);
                            carDao.deleteCar(carToBeDeleted);
                        }
                    }
                }
            }
        });

        //modify buttom handler
        btModify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Validator.isPresent(txtCarNum, "Car number") && Validator.isInteger(txtCarNum, "Car number")) {
                    String num = txtCarNum.getText();
                    int carNum = Integer.parseInt(num);
                    if (carDao.getCar(carNum) == null)
                        JOptionPane.showMessageDialog(null, "Modify failed. Car does not exist!", "alert", JOptionPane.ERROR_MESSAGE);
                    else {
                        Car carToBeModified = carDao.getCar(carNum);
                        jPanel2.setVisible(true);
                        jPanel.setVisible(false);
                        jcbMake.setSelectedItem(carToBeModified.getMake());
                        jcbModel.setSelectedItem(carToBeModified.getModel());
                        txtYear.setText(Integer.toString(carToBeModified.getYear()));
                        jcbType.setSelectedItem(carToBeModified.getType());
                        txtDesc.setText(carToBeModified.getDescription());
                        txtUrl.setText(carToBeModified.getUrl());
                        txtImg.setText(carToBeModified.getImg());
                        txtPrice.setText(Double.toString(carToBeModified.getPrice()));

                        for (ActionListener al : btSubmit.getActionListeners())
                            btSubmit.removeActionListener(al);
                        for (ActionListener al : btClear.getActionListeners())
                            btClear.removeActionListener(al);

                        btSubmit.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String make = Objects.requireNonNull(jcbMake.getSelectedItem()).toString();
                                String model = jcbModel.getSelectedItem().toString();
                                if (Validator.isYear(txtYear, "Year") && Validator.isDouble(txtPrice, "Price") && Validator.isPresent(txtDesc, "Description") && Validator.isUrl(txtUrl, "Url") && Validator.isValidImg(txtImg, "Image Url")) {
                                    int year = Integer.parseInt(txtYear.getText());
                                    String type = jcbType.getSelectedItem().toString();
                                    double price = Double.parseDouble(txtPrice.getText());
                                    //System.out.println(price);
                                    String description = txtDesc.getText();
                                    String img = txtImg.getText();
                                    String url = txtUrl.getText();
                                    Car newCar = new Car(carNum, make, model, year, type, description, img, url, price);
                                    carDao.modifyCar(newCar);
                                    jPanel2.setVisible(false);
                                    jPanel.setVisible(true);
                                    JOptionPane.showMessageDialog(null, "Modify succeed.", "confirm", JOptionPane.INFORMATION_MESSAGE);
                                    jcbMake.setSelectedIndex(0);
                                    jcbModel.setSelectedIndex(0);
                                    txtPrice.setText("");
                                    txtDesc.setText("");
                                    txtYear.setText("");
                                    txtImg.setText("");
                                    txtUrl.setText("");
                                    jcbType.setSelectedIndex(0);
                                }
                                for (ActionListener al : btSubmit.getActionListeners())
                                    btSubmit.removeActionListener(al);
                                btSubmit.addActionListener(this);
                            }
                        });

                        //clear button handler
                        btClear.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                jcbMake.setSelectedItem(carToBeModified.getMake());
                                jcbModel.setSelectedItem(carToBeModified.getModel());
                                txtYear.setText(Integer.toString(carToBeModified.getYear()));
                                jcbType.setSelectedItem(carToBeModified);
                                txtPrice.setText(Double.toString(carToBeModified.getPrice()));
                                txtDesc.setText(carToBeModified.getDescription());
                                txtImg.setText(carToBeModified.getImg());
                                txtUrl.setText(carToBeModified.getUrl());
                                for (ActionListener al : btClear.getActionListeners())
                                    btClear.removeActionListener(al);
                                btClear.addActionListener(this);
                            }
                        });
                    }
                }
            }
        });


        // add button handler
        btAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jPanel2.setVisible(false);
                if (Validator.isPresent(txtCarNum, "Car number") && Validator.isInteger(txtCarNum, "Car number")) {
                    try {
                        int carNum = Integer.parseInt(txtCarNum.getText());
                        if (carDao.getCar(carNum) != null)
                            JOptionPane.showMessageDialog(null, "Add failed. Car already exist!", "alert", JOptionPane.ERROR_MESSAGE);
                        else {
                            jPanel2.setVisible(true);
                            jPanel.setVisible(false);
                            jcbMake.setSelectedIndex(0);
                            jcbModel.setSelectedIndex(0);
                            txtPrice.setText("");
                            txtYear.setText("");
                            txtDesc.setText("");
                            txtImg.setText("");
                            txtUrl.setText("");
                            jcbType.setSelectedIndex(0);

                            for (ActionListener al : btSubmit.getActionListeners())
                                btSubmit.removeActionListener(al);

                            btSubmit.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    int id = Integer.parseInt(txtCarNum.getText());
                                    String make = jcbMake.getSelectedItem().toString();
                                    String model = jcbModel.getSelectedItem().toString();
                                    if (Validator.isYear(txtYear, "Year") && Validator.isDouble(txtPrice, "Price") && Validator.isPresent(txtDesc, "Description") && Validator.isUrl(txtUrl, "Url") && Validator.isValidImg(txtImg, "Image Url")) {
                                        int year = Integer.parseInt(txtYear.getText());
                                        String type = jcbType.getSelectedItem().toString();
                                        double price = Double.parseDouble(txtPrice.getText());
                                        //System.out.println(price);
                                        String description = txtDesc.getText();
                                        String img = txtImg.getText();
                                        String url = txtUrl.getText();
                                        if (carDao.getCar(id) == null) {
                                            Car newCar = new Car(id, make, model, year, type, description, img, url, price);
                                            carDao.addCar(newCar);
                                            jPanel2.setVisible(false);
                                            jPanel.setVisible(true);
                                            JOptionPane.showMessageDialog(null, "Add succeed.", "confirm", JOptionPane.INFORMATION_MESSAGE);
                                            jcbMake.setSelectedIndex(0);
                                            jcbModel.setSelectedIndex(0);
                                            txtPrice.setText("");
                                            txtDesc.setText("");
                                            txtYear.setText("");
                                            txtImg.setText("");
                                            txtUrl.setText("");
                                            jcbType.setSelectedIndex(0);

                                        }
                                    }
                                    for (ActionListener al : btSubmit.getActionListeners())
                                        btSubmit.removeActionListener(al);
                                    btSubmit.addActionListener(this);
                                }
                            });

                            btClear.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    jcbMake.setSelectedIndex(0);
                                    jcbModel.setSelectedIndex(0);
                                    txtYear.setText("");
                                    jcbType.setSelectedIndex(0);
                                    txtPrice.setText("");
                                    txtYear.setText("");
                                    txtDesc.setText("");
                                    txtImg.setText("");
                                    txtUrl.setText("");
                                }
                            });
                        }
                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(null, "Invalid number!", "alert", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }


    public static void main(String[] args) {
        AdminModifyCarInfo test = new AdminModifyCarInfo();
        test.setLayout(new GridLayout(2, 1));
        test.setVisible(true);
        test.setSize(600, 600);
        test.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
