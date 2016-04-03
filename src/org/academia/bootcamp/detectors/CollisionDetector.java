package org.academia.bootcamp.detectors;

import org.academia.bootcamp.objects.Car;
import org.academia.bootcamp.objects.Coupe;

import java.util.Iterator;
import java.util.List;

/**
 * Created by codecadet on 05/02/16.
 */
public class CollisionDetector {

    private List<Car> cars;


    /**
     * Collision Detector Constructor
     *
     * @param cars
     */
    public CollisionDetector(List<Car> cars) {
        this.cars = cars;
    }


    /**
     * Checks if Cars are colliding
     *
     * @param car
     */
    public void checkCollision(Car car) {

        Iterator<Car> it = cars.iterator();

        Car carToCheckCrash;

        while (it.hasNext()) {

            carToCheckCrash = it.next();

                if (carToCheckCrash instanceof Coupe) {

                    //if the Car is being compared with itself
                    if (carToCheckCrash == car) {
                        continue;
                    }

                    //if the Car crashes with another Car
                    if (carToCheckCrash.comparePosition(car)) {
                        carToCheckCrash.setCrashed(true);
                    }
                }

                //if is another Car
                else {

                    //if the Car is being compared with itself
                    if (carToCheckCrash == car) {
                        continue;
                    }

                    //if the Car crashes with another Car
                    if (carToCheckCrash.comparePosition(car)) {
                        if (!(car instanceof Coupe)) {
                            car.chooseOppositeDirection();
                            carToCheckCrash.chooseOppositeDirection();
                        }
                    }

                }
            }

        }


}
