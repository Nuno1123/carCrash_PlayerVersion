package org.academia.bootcamp.objects;

public class CarFactory {

    private CarType carType;
    private int index;
    private static int copCarCounter = 0;
    private static int coupeCounter = 0;
    private static int jazzCounter = 0;

    /**
     * CarFactory Constructor
     */
    public CarFactory(CarType carType) {
        this.carType = carType;
    }


    private static int generateRandom() {
        return (int) (Math.random() * (CarType.values().length));
    }


    /**
     * Create Car Types
     */
    public CarType getCarType() {

        return CarType.values()[index];
    }

    public Car getNewCar(int index) {
        this.index = index;
        Car car = null;

        if (index == 0){
            car = new Coupe();
        }
        else if (index == 1){
            car = new CopCar();
        }
        else if (index == 2){
            car = new Jazz();
        }

        return car;
    }
}