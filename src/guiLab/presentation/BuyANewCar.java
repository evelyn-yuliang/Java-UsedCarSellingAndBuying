package guiLab.presentation;

import guiLab.business.Car;
import guiLab.data.CarConstants;
import guiLab.data.GetMakeAndModelFromFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BuyANewCar extends JFrame implements CarConstants {
    private File file;
    private Car newCar;//the car that meets the search condition
    private ArrayList<Car> carArrayList;//contains all cars that meet search condition
    private JPanel searchPanel;

    GetMakeAndModelFromFile getMakeAndModel = new GetMakeAndModelFromFile();
    public String[] make = getMakeAndModel.getMake();
    public String[] catagory = {"Any", "SUV", "MPV", "Minivan", "Crossover", "Hatchback", "Coupe", "Sedan", "Roadster", "Cabriolet", "Station"};
    public Integer[] priceRangeMin = {1000, 5000, 10000, 15000, 20000, 25000, 30000, 35000, 40000, 45000, 50000, 55000, 60000};
    public Integer[] priceRangeMax = {5000, 10000, 15000, 20000, 25000, 30000, 35000, 40000, 45000, 50000, 55000, 60000, Integer.MAX_VALUE};

    private JPanel comboJPanel;
    private JComboBox<String> jcbCatagory = new JComboBox<String>(catagory);
    private JComboBox<String> jcbPriceRangeMin = new JComboBox<String>(changeToString(priceRangeMin));
    private JComboBox<String> jcbPriceRangeMax = new JComboBox<String>(changeToString(priceRangeMax));
    private JComboBox<String> jcbMake = new JComboBox<String>(make);

    private JButton jbtSearch = new JButton("Search");
    private JButton jbtClear = new JButton("Clear");

    public ArrayList<Car> getCarArrayList() {
        return carArrayList;
    }

    //change price range of "unlimited" to an integer
    private static String[] changeToString(Integer[] integerArray) {
        String[] stringArray = new String[integerArray.length];
        for (int i = 0; i < integerArray.length; i++) {
            if (integerArray[i] != Integer.MAX_VALUE)
                stringArray[i] = integerArray[i].toString();
            else
                stringArray[i] = "unlimited";
        }
        return stringArray;
    }

    private void printCarArrayList(ArrayList<Car> carArrayList) {
        for (Car car : carArrayList) {
            System.out.print(car.getId() + " " + car.getMake() + " " + car.getType() + " " + car.getModel() + " " + car.getDescription() + " " + car.getPrice());
            System.out.println();
        }
    }

    private void clearSearchPanel(JPanel searchPanel) {
        System.out.println("remove");
        searchPanel.removeAll();
    }

    //add cars from car array list into search panel
    private void getCarsFromArrayList(ArrayList<Car> carArrayList, JPanel searchPanel) {
        //printCarArrayList(carArrayList);
        try {
            //create table from search result
            //table header
            StringBuffer result = new StringBuffer();
            result.append(
                    "<html><body><table border=1>" +
                            "<tr>" +
                            "<td>Price</td><td>Make</td><td>Model</td><td>Type</td><td>Year</td><td>Description</td><td>Image</td>" +
                            "</tr>");
            for (int i = 0; i < carArrayList.size(); i++) {
                //add each car's information into table
                System.out.println("index: " + i);
                Double price = carArrayList.get(i).getPrice();
                String make = carArrayList.get(i).getMake();
                String model = carArrayList.get(i).getModel();
                int year = carArrayList.get(i).getYear();
                String description = carArrayList.get(i).getDescription();
                String type = carArrayList.get(i).getType();
                String imgLink = carArrayList.get(i).getImg();
                String url = carArrayList.get(i).getUrl();

                result.append("<tr><td>$").append(price).append("</td><td>").append(make).append("</td><td>").append(model).append("</td><td>").append(type).append("</td><td>").append(year).append("</td><td>").append(description).append("</td>");
                result.append("<td ><img src='").append(imgLink).append("'></td></tr><br>");
                result.append("<tr><td colspan =7><a href='").append(url).append("'>'").append(url).append("'</a></td></tr><br>");
            }
            result.append("</table></body></html>");
            searchPanel.add(new JLabel(result.toString()));

        } catch (RuntimeException exp) {
            System.out.println("Runtime error");
        } catch (Exception e) {
            System.out.println("Invalid url");
        }
    }

    //create a panel containing search result for specific condition from carArrayList
    private void showSearchResult() {
        JFrame subFrame = new JFrame();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JPanel sortPanel = new JPanel();
        searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
        mainPanel.add(sortPanel);

        JButton but1 = new JButton("Sort by model");
        but1.addActionListener(new sortByModelButtonHandler());
        JButton but3 = new JButton("Sort by price");
        but3.addActionListener(new sortByPriceButtonHandler());

        sortPanel.add(but1);
        sortPanel.add(but3);
        if (carArrayList.size() == 0)
            JOptionPane.showMessageDialog(null, "No result.", "Alter", JOptionPane.INFORMATION_MESSAGE);
        else {
            getCarsFromArrayList(carArrayList, searchPanel);
            JScrollPane sp = new JScrollPane(searchPanel);
            mainPanel.add(sp);
            subFrame.add(mainPanel);
            subFrame.setVisible(true);
            subFrame.setSize(800, 700);
            subFrame.setLocation(200, 50);
            subFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }

    //initialize the panel containing 3 search conditions
    public BuyANewCar() {
        comboJPanel = new JPanel();
        comboJPanel.setLayout(new GridLayout(4, 3));
        comboJPanel.add(new JLabel("Search by catagory:"));
        comboJPanel.add(jcbCatagory);
        comboJPanel.add(new JLabel());
        comboJPanel.add(new JLabel("Search by price:"));
        comboJPanel.add(jcbPriceRangeMin);
        comboJPanel.add(jcbPriceRangeMax);
        jcbPriceRangeMax.setSelectedIndex(jcbPriceRangeMax.getItemCount() - 1);
        comboJPanel.add(new JLabel("Search by make:"));
        jcbMake.addItem("Any");
        comboJPanel.add(jcbMake);
        jcbMake.setSelectedIndex(jcbMake.getItemCount() - 1);
        comboJPanel.add(new JLabel());
        comboJPanel.add(new JLabel());

        comboJPanel.add(jbtSearch);
        comboJPanel.add(jbtClear);
        jbtSearch.setMnemonic('s');
        jbtClear.setMnemonic('c');

        file = new File(FILE_NAME_TEXT);

        /*
        search button handler
        reads user's choice of conditions,
        then rad from file to see if the car meets this condition.
        if meets, add the car into an arrayList of search-result-cars
        finally call showSearchResult function to print all results into a new panel.
         */
        jbtSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carArrayList = new ArrayList<>();
                String searchType = jcbCatagory.getSelectedItem().toString();
                String searchMake = jcbMake.getSelectedItem().toString();
                Double minPrice = Double.parseDouble(jcbPriceRangeMin.getSelectedItem().toString());
                Double maxPrice = 0.0;
                if (jcbPriceRangeMax.getSelectedItem().toString().equals("unlimited"))
                    maxPrice = Double.MAX_VALUE;
                else
                    maxPrice = Double.parseDouble(jcbPriceRangeMax.getSelectedItem().toString());

                try {
                    Scanner scanner = new Scanner(file);
                    while (scanner.hasNext()) {
                        String info[] = scanner.nextLine().split("\t");
                        int id = Integer.parseInt(info[0]);
                        String makeAndModel[] = info[1].split(" ");
                        String carMake = makeAndModel[0];
                        String model = makeAndModel[1];
                        int year = Integer.parseInt(info[2]);
                        String description = info[3];
                        String carType = info[4];
                        String img = info[5];
                        String url = info[6];
                        Double price = Double.parseDouble(info[7]);
                        if (searchMake.equals("Any") && searchType.equals("Any")) {
                            if (price >= minPrice && price <= maxPrice) {
                                newCar = new Car(id, carMake, model, year, carType, description, img, url, price);
                                carArrayList.add(newCar);
                            }
                        } else if (!searchMake.equals("Any") && searchType.equals("Any")) {
                            if (carMake.equals(searchMake) && price >= minPrice && price <= maxPrice) {
                                newCar = new Car(id, carMake, model, year, carType, description, img, url, price);
                                carArrayList.add(newCar);
                            }

                        } else if (searchMake.equals("Any") && !searchType.equals("Any")) {
                            if (carType.equals(searchType) && price >= minPrice && price <= maxPrice) {
                                newCar = new Car(id, carMake, model, year, carType, description, img, url, price);
                                carArrayList.add(newCar);
                            }
                        } else {
                            if (carType.equals(searchType) && carMake.equals(searchMake) && price >= minPrice && price <= maxPrice) {
                                newCar = new Car(id, carMake, model, year, carType, description, img, url, price);
                                carArrayList.add(newCar);
                            }
                        }
                    }
                    //show a new panel include search result
                    showSearchResult();
                } catch (FileNotFoundException ex) {
                    System.out.println("File not found.");
                }
            }
        });

        //clear button handler

        jbtClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jcbCatagory.setSelectedIndex(0);
                jcbMake.setSelectedIndex(jcbMake.getItemCount() - 1);
                jcbPriceRangeMax.setSelectedIndex(jcbPriceRangeMax.getItemCount() - 1);
                jcbPriceRangeMin.setSelectedIndex(0);
            }
        });
        this.add(comboJPanel);
    }

    class sortByPriceButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            sortByPrice(carArrayList);
            searchPanel.removeAll();
            getCarsFromArrayList(carArrayList, searchPanel);
            searchPanel.revalidate();
            searchPanel.repaint();
        }

        public void sortByPrice(ArrayList<Car> a) { // Sort a[] into increasing order.
            int N = a.size();
            int h = 1;
            while (h < N / 3) h = 3 * h + 1; // 1, 4, 13, 40, 121, 364, 1093, ...
            while (h >= 1) { // h-sort the array.
                for (int i = h; i < N; i++) { // Insert a[i] among a[i-h], a[i-2*h], a[i-3*h]... .
                    for (int j = i; j >= h && lessPrice(a.get(j), a.get(j - h)); j -= h)
                        exch(a, j, j - h);
                }
                h = h / 3;
            }
        }

        boolean lessPrice(Car v, Car w) {
            return v.getPrice() < w.getPrice();
        }

        void exch(ArrayList<Car> a, int i, int j) {
            Car t = a.get(i);
            a.set(i, a.get(j));
            a.set(j, t);
        }
    }

    class sortByModelButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            sortByModel(carArrayList);
            searchPanel.removeAll();
            getCarsFromArrayList(carArrayList, searchPanel);
            searchPanel.revalidate();
            searchPanel.repaint();
        }

        public void sortByModel(ArrayList<Car> a) { // Sort a[] into increasing order.
            int N = a.size();
            int h = 1;
            while (h < N / 3) h = 3 * h + 1; // 1, 4, 13, 40, 121, 364, 1093, ...
            while (h >= 1) { // h-sort the array.
                for (int i = h; i < N; i++) { // Insert a[i] among a[i-h], a[i-2*h], a[i-3*h]... .
                    for (int j = i; j >= h && lessModel(a.get(j), a.get(j - h)); j -= h)
                        exch(a, j, j - h);
                }
                h = h / 3;
            }
        }

        boolean lessModel(Car v, Car w) {
            return v.getModel().compareTo(w.getModel()) < 0;
        }

        void exch(ArrayList<Car> a, int i, int j) {
            Car t = a.get(i);
            a.set(i, a.get(j));
            a.set(j, t);
        }
    }


    public static void main(String[] args) {
        BuyANewCar test = new BuyANewCar();
        test.setVisible(true);
        test.setSize(500, 400);
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
