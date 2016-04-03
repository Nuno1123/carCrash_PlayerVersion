package org.academia.bootcamp;


import org.academia.bootcamp.detectors.CollisionDetector;
import org.academia.bootcamp.detectors.PoliceRadar;
import org.academia.bootcamp.detectors.PowerUpDetector;

import org.academia.bootcamp.field.Field;
import org.academia.bootcamp.objects.*;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Game {

    public static final int MANUFACTURED_CARS = 10;
    public static final int POWERUPS = 16;
    public static final long GAME_OVER_DELAY = 1500;
    private CarFactory carFactory;

    private List<Car> cars = new ArrayList<>();
    private List<PowerUp> powerUps = new ArrayList<>();


    /**
     * Animation delay
     */
    int delay;


    /**
     * Game Constructor
     * @param x
     * @param y
     * @param delay
     */
    public Game(int x, int y, int delay) {

        Field.init(x, y);
        this.delay = delay;
    }


    /**
     * Creates a bunch of objects and randomly puts them in the field
     */
    public void init() {

        cars.add(new Coupe());
        cars.add(new CopCar());
        cars.add(new Jazz());
        cars.add(new Jazz());
        powerUps.add(new Bottle());
        powerUps.add(new Bottle());
        powerUps.add(new Bottle());
        powerUps.add(new Bottle());
        powerUps.add(new Bottle());
        powerUps.add(new Bottle());
        powerUps.add(new Guronsan());
        powerUps.add(new Guronsan());
        powerUps.add(new Guronsan());
        powerUps.add(new SpeedBooster());
        powerUps.add(new SpeedBooster());
        powerUps.add(new SpeedBooster());

        CollisionDetector collisionDetector = new CollisionDetector(cars);
        PoliceRadar policeRadar = new PoliceRadar(cars);
        PowerUpDetector powerUpDetector = new PowerUpDetector(cars, powerUps);


        Iterator<Car> itCar = cars.iterator();


        while (itCar.hasNext()) {

            Car c = itCar.next();

            c.setCollisionDetector(collisionDetector);
            c.setPoliceRadar(policeRadar);
            c.setPowerUpDetector(powerUpDetector);
        }


/*
        for (int i = 0; i < powerUps.length; i++) {
            powerUps[i] = PowerUpFactory.getNewPowerUpType();
        }
        */
    }

    /**
     * Starts the animation
     */
    public void start() throws InterruptedException {

        while (true) {

            // Pause for a while
            Thread.sleep(delay);

            Iterator<Car> it = cars.iterator();

            boolean allCarsCaput = false;

            // Move all objects
            while (it.hasNext()) {

                Car c = it.next();



                if ((c instanceof Coupe) && ((c.isCrashed()) || (c.isBusted()))) {
                    allCarsCaput = true;
                }

                if (!allCarsCaput && !c.isCrashed() && (!c.isBusted())) {
                    c.moveCar();
                }

            }

            if (allCarsCaput){
                break;
            }
        }

        Picture gameOver = new Picture(10, 10, "Resources/gameover.png");
        Thread.sleep(GAME_OVER_DELAY);
        gameOver.draw();
    }

    @Override
    public String toString() {
        return "Game{" +
                "carFactory=" + carFactory +
                "carType = " + CarType.values() +
                ", cars=" + cars +
                ", powerUps=" + powerUps +
                ", delay=" + delay +
                '}';
    }
}
