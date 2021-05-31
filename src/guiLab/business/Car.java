package guiLab.business;

public class Car {
    private int id, year, mileage;
    private double price;
    private String make, model, description, type, img, url, color, price1,owner;

    public Car() {
    }

    public Car(int id, String make, String model, int year, String type, String description, String img, String url, double price) {
        this.id = id;
        this.price = price;
        this.make = make;
        this.model = model;
        this.description = description;
        this.year = year;
        this.type = type;
        this.img = img;
        this.url = url;
    }

    public Car(int year, String make, String model, String type, String color, int mileage, String description, String price1,String owner) {
        this.year = year;
        this.make = make;
        this.model = model;
        this.type = type;
        this.color = color;
        this.mileage = mileage;
        this.description = description;
        this.price1 = price1;
        this.owner = owner;


    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getImg() {
        return img;
    }

    public String getUrl() {
        return url;
    }

    public int getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    public int getMileage() {
        return mileage;
    }

    public String getPrice1() {
        return price1;
    }
    
    public String getowner() {
    	return owner;
    }
}

