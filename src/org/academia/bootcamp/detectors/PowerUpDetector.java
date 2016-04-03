package org.academia.bootcamp.detectors;

import org.academia.bootcamp.field.Direction;
import org.academia.bootcamp.objects.*;

import java.util.List;

/**
 * Created by dora on 13-02-2016.
 */
public class PowerUpDetector {

    private List<Car> cars;
    private List<PowerUp> powerUps;

    /**
     * Cars constructor
     * @param cars
     * @param powerUps
     */
    public PowerUpDetector(List<Car> cars, List<PowerUp> powerUps) {
        this.cars = cars;
        this.powerUps = powerUps;
    }


    /**
     * Checks PowerUps aquisition by the Cars
     * @param car
     */
    public void checkPowerUpsAquisition(Car car) {

        for (Car c : cars) {

            for (PowerUp p : powerUps) {

                //if the Power Up is a Bottle
                if (p instanceof Bottle) {

                    //if the Car is sober and the PowerUp was not already used
                    if ((c instanceof Coupe) && (c.isSober()) && (p.isUsed() == false)) {

                        //compares the Car and the PowerUp Postions
                        if (c.comparePositionPowerUp(p)) {
                            c.setStrengthDrink(70);
                            p.getRep().getCurrentPicture().delete();
                            ((Coupe) c).setCurrentDirection(Direction.SOUTH);
                            c.getRep().changePicture(3);
                            p.setUsed();
                        }
                    }
                }

                //if the Power Up is Guronsan
                else if (p instanceof Guronsan) {

                    //if the Car is drunk and the Power Up was not already used
                    if ((c instanceof Coupe) && (c.isDrunk()) && (p.isUsed() == false)) {

                        //compares the Car and the PowerUp Postions
                        if (c.comparePositionPowerUp(p)) {
                            c.setStrengthDrink(0);
                            p.getRep().getCurrentPicture().delete();
                            ((Coupe) c).setCurrentDirection(Direction.SOUTH);
                            c.getRep().changePicture(2);
                            p.setUsed();
                        }
                    }

                //if the Power Up is a SpeedBooster
                } else if (p instanceof SpeedBooster) {

                    //if the Car's Speed is lower than 11 and the Power Up was not already used
                    if ((c instanceof Coupe) && (p.isUsed() == false)) {

                        //compares the Car and the PowerUp Postions
                        if (c.comparePositionPowerUp(p)) {
                            c.speedBooster();
                            p.getRep().getCurrentPicture().delete();
                            p.setUsed();
                        }
                    }
                }
            }
        }
    }
}


