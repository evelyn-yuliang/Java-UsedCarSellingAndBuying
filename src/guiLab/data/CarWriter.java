package guiLab.data;

import guiLab.business.Car;

public interface CarWriter {
    boolean addCar(Car car);

    boolean deleteCar(Car car);

    boolean modifyCar(Car car);
}
