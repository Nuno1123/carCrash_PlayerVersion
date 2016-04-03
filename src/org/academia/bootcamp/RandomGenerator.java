package org.academia.bootcamp;

import org.academia.bootcamp.field.Direction;

/**
 * Created by Nuno1123 on 14/02/16.
 */


/**
 * Random Generator Classes
 */
public class RandomGenerator {

    public static int generateRandom(int min, int max) {
        return ((int) (Math.random() * (max - min + 1) + min));
    }

    public static int generateRandom2() {
        return (int) (Math.random() * (Direction.values().length));
    }

    public static Direction getRandomDirection() {
        int index = generateRandom2();

        return Direction.values()[index];
    }

}
