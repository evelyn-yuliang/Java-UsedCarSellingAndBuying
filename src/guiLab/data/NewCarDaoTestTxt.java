package guiLab.data;

import guiLab.business.Car;

import java.io.*;
import java.util.Scanner;

public class NewCarDaoTestTxt implements CarDao {
    private File file;

    public NewCarDaoTestTxt() {
        file = new File(FILE_NAME_TEXT);
    }

    private void checkFile() {
        try {
            if (!file.exists())
                file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Car getCar(int searchId) {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String info[] = scanner.nextLine().split("\t");
                int id = Integer.parseInt(info[0]);
                String makeAndModel[] = info[1].split(" ");
                String make = makeAndModel[0];
                String model = makeAndModel[1];
                //System.out.println(id + "kkkkk" + make + ' ' + model);
                int year = Integer.parseInt(info[2]);
                String description = info[3];
                String type = info[4];
                String img = info[5];
                String url = info[6];
                Double price = Double.parseDouble(info[7]);
                //System.out.println(id + make + year + desc + type + img + url + price);
                if (id == searchId) {
                    //System.out.println(id);
                    return new Car(id, make, model, year, type, description, img, url, price);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            return null;
        }
        System.out.println("Car not found.");
        return null;
    }


    @Override
    public boolean addCar(Car car) {
        checkFile();
        String fieldSeparator = "\t";
        String fieldSeparatorByMakeAndModel = " ";

        try (PrintWriter output = new PrintWriter(new FileOutputStream(file, true))) {
            //output.print(fieldSeparatorByMakeAndModel);
            output.print(car.getId());
            output.print(fieldSeparator);
            output.print(car.getMake());
            output.print(fieldSeparatorByMakeAndModel);
            output.print(car.getModel());
            output.print(fieldSeparator);
            output.print(car.getYear());
            output.print(fieldSeparator);
            output.print(car.getDescription());
            output.print(fieldSeparator);
            output.print(car.getType());
            output.print(fieldSeparator);
            output.print(car.getImg());
            output.print(fieldSeparator);
            output.print(car.getUrl());
            output.print(fieldSeparator);
            output.print(car.getPrice());
            output.println();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteCar(Car car) {
        String fieldSeparator = "\t";
        String fieldSeparatorByMakeAndModel = " ";
        checkFile();
        Scanner dIn;
        PrintWriter output;
        File temp = new File("temp.txt");
        try {
            dIn = new Scanner(file);
            output = new PrintWriter(new FileOutputStream(temp, true));
            while (dIn.hasNext()) {
                String info[] = dIn.nextLine().split("\t");
                int id = Integer.parseInt(info[0]);
                String makeAndModel[] = info[1].split(" ");
                String make = makeAndModel[0];
                String model = makeAndModel[1];
                int year = Integer.parseInt(info[2]);
                String description = info[3];
                String type = info[4];
                String img = info[5];
                String url = info[6];
                Double price = Double.parseDouble(info[7]);

                if (id != car.getId()) {
                    //System.out.println(car.getId());
                    output.print(id);
                    output.print(fieldSeparator);
                    output.print(make);
                    output.print(fieldSeparatorByMakeAndModel);
                    output.print(model);
                    output.print(fieldSeparator);
                    output.print(year);
                    output.print(fieldSeparator);
                    output.print(description);
                    output.print(fieldSeparator);
                    output.print(type);
                    output.print(fieldSeparator);
                    output.print(img);
                    output.print(fieldSeparator);
                    output.print(url);
                    output.print(fieldSeparator);
                    output.print(price);
                    output.println();
                }
            }
            dIn.close();
            output.close();
            //Delete the original file
            if (!file.delete()) {
                System.out.println("Could not delete file");
                return false;
            }
            //Rename the new file to the filename the original file had.
            if (!temp.renameTo(file)) {
                System.out.println("Could not rename file");
                return false;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return true;
    }

    @Override
    public boolean modifyCar(Car car) {
        String fieldSeparator = "\t";
        String fieldSeparatorByMakeAndModel = " ";
        checkFile();
        Scanner dIn;
        PrintWriter output;
        File temp = new File("temp.txt");
        try {
            dIn = new Scanner(file);
            output = new PrintWriter(new FileOutputStream(temp, true));
            while (dIn.hasNext()) {
                String info[] = dIn.nextLine().split("\t");
                int id = Integer.parseInt(info[0]);
                String makeAndModel[] = info[1].split(" ");
                String make = makeAndModel[0];
                String model = makeAndModel[1];
                int year = Integer.parseInt(info[2]);
                String description = info[3];
                String type = info[4];
                String img = info[5];
                String url = info[6];
                Double price = Double.parseDouble(info[7]);

                if (id != car.getId()) {
                    output.print(id);
                    output.print(fieldSeparator);
                    output.print(make);
                    output.print(fieldSeparatorByMakeAndModel);
                    output.print(model);
                    output.print(fieldSeparator);
                    output.print(year);
                    output.print(fieldSeparator);
                    output.print(description);
                    output.print(fieldSeparator);
                    output.print(type);
                    output.print(fieldSeparator);
                    output.print(img);
                    output.print(fieldSeparator);
                    output.print(url);
                    output.print(fieldSeparator);
                    output.print(price);
                    output.println();
                } else {
                    output.print(car.getId());
                    output.print(fieldSeparator);
                    output.print(car.getMake());
                    output.print(fieldSeparatorByMakeAndModel);
                    output.print(car.getModel());
                    output.print(fieldSeparator);
                    output.print(car.getYear());
                    output.print(fieldSeparator);
                    output.print(car.getDescription());
                    output.print(fieldSeparator);
                    output.print(car.getType());
                    output.print(fieldSeparator);
                    output.print(car.getImg());
                    output.print(fieldSeparator);
                    output.print(car.getUrl());
                    output.print(fieldSeparator);
                    output.print(car.getPrice());
                    output.println();
                }
            }
            dIn.close();
            output.close();
            //Delete the original file
            if (!file.delete()) {
                System.out.println("Could not delete file");
                return false;
            }
            //Rename the new file to the filename the original file had.
            if (!temp.renameTo(file)) {
                System.out.println("Could not rename file");
                return false;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return true;
    }

    public static void main(String[] args) {
        NewCarDaoTestTxt test = new NewCarDaoTestTxt();
        test.getCar(76);
    }
}
