package guiLab.data;

import guiLab.business.Car;

import java.io.*;

public class UsedCarDAOText implements CarDao {
    File carFile = null;

    public UsedCarDAOText() {
        carFile = new File("UsedCarFile");
    }

    @Override
    public Car getCar(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean addCar(Car car) {
        // TODO Auto-generated method stub
        PrintWriter out = null;
        String FIELD_SEP = "\t";

        if (!carFile.exists()) {
            try {
                carFile.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        try {

            carFile = new File("UsedCarFile");
            if (!carFile.exists()) {
                carFile.createNewFile();
            }
            out = new PrintWriter(new BufferedWriter(new FileWriter(carFile, true)));
            out.print(car.getYear() + FIELD_SEP);
            out.print(car.getMake() + FIELD_SEP);
            out.print(car.getModel() + FIELD_SEP);
            out.print(car.getType() + FIELD_SEP);
            out.print(car.getColor() + FIELD_SEP);
            out.print(car.getMileage() + FIELD_SEP);
            out.print(car.getDescription() + FIELD_SEP);
            out.println(car.getPrice1());

        } catch (IOException ioe) {
            ioe.printStackTrace();
            return false;
        } finally {
            out.close();
        }
        return true;


    }

    @Override
    public boolean deleteCar(Car car) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean modifyCar(Car car) {
        // TODO Auto-generated method stub
        return false;
    }

}
