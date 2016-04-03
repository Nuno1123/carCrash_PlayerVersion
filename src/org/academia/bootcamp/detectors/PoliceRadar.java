package org.academia.bootcamp.detectors;

import org.academia.bootcamp.objects.Car;
import org.academia.bootcamp.objects.CopCar;
import org.academia.bootcamp.objects.Coupe;

import java.util.List;

/**
 * Created by codecadet on 05/02/16.
 */
public class PoliceRadar {

    private List<Car> cars;


    /**
     * Police Radar Constructor
     * @param cars
     */
    public PoliceRadar(List<Car> cars) {
        this.cars = cars;
    }


    /**
     * Checks Cars inside the Police Radar
     * @param car
     */
    public void checkRadar(Car car) {

        for (Car c : cars) {

            //if Car is a CopCar
            if (c instanceof Coupe) {

                //if the Car is being compared with itself
                if (car == c) {
                    continue;
                }

                //if the CopCar is sober and its radar is catching a drunk car, except another CopCar
                if ((car instanceof CopCar) && (car.comparePositionRadar(c))==true) {
                    c.setBusted(true);
                }
            }
        }
    }

}
