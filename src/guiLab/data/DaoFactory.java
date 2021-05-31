package guiLab.data;

public class DaoFactory {
    public static CarDao getCarDAO() {
        CarDao carDao = new NewCarDaoTestTxt();
        return carDao;
    }
}
