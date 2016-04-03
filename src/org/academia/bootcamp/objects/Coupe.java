package org.academia.bootcamp.objects;

import org.academia.bootcamp.RandomGenerator;
import org.academia.bootcamp.detectors.CollisionDetector;
import org.academia.bootcamp.detectors.PoliceRadar;
import org.academia.bootcamp.detectors.PowerUpDetector;
import org.academia.bootcamp.field.Direction;

import org.academia.bootcamp.field.Field;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * Created by codecadet on 02/02/16.
 */

public class Coupe extends Car implements KeyboardHandler{

    private Direction currentDirection;
    private CollisionDetector collisionDetector;
    private PoliceRadar policeRadar;
    private PowerUpDetector powerUpDetector;
    private static final double INCREMENT = 2;


    public Coupe() {
        super(CarType.COUPE, 8);

        String[] paths = new String[]{"coupe_north.png",
                "drunkCoupe_north.png",
                "coupe_south.png",
                "drunkCoupe_south.png",
                "coupe_west.png",
                "drunkCoupe_west.png",
                "coupe_east.png",
                "drunkCoupe_east.png",
                "rip.png",
                "busted.png"};

        Picture[] pictures = new Picture[10];

        pictures[0] = new Picture(getRep().getX(), getRep().getY(), paths[0]);
        pictures[1] = new Picture(getRep().getX(), getRep().getY(), paths[1]);
        pictures[2] = new Picture(getRep().getX(), getRep().getY(), paths[2]);
        pictures[3] = new Picture(getRep().getX(), getRep().getY(), paths[3]);
        pictures[4] = new Picture(getRep().getX(), getRep().getY(), paths[4]);
        pictures[5] = new Picture(getRep().getX(), getRep().getY(), paths[5]);
        pictures[6] = new Picture(getRep().getX(), getRep().getY(), paths[6]);
        pictures[7] = new Picture(getRep().getX(), getRep().getY(), paths[7]);
        pictures[8] = new Picture(getRep().getX(), getRep().getY(), paths[8]);
        pictures[9] = new Picture(getRep().getX(), getRep().getY(), paths[9]);

        getRep().setPictures(pictures);
        setCurrentDirection(Direction.EAST);
        getRep().setCurrentPicture(pictures[6]);
        keySetup();
        getRep().getCurrentPicture().draw();
        setPos();


    }

    @Override
    public void setPos() {

        int x = 10;
        int y = RandomGenerator.generateRandom(10, Field.height - getRep().getCurrentPicture().getHeight());

        getRep().setX(x);
        getRep().setY(y);
        setCurrentDirection(Direction.EAST);
        this.getRep().translate(x, y);

    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    public void keySetup() {
        Keyboard k = new Keyboard(this);
        KeyboardEvent north = new KeyboardEvent();
        north.setKey(KeyboardEvent.KEY_UP);
        north.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(north);
        KeyboardEvent south = new KeyboardEvent();
        south.setKey(KeyboardEvent.KEY_DOWN);
        south.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(south);
        KeyboardEvent west = new KeyboardEvent();
        west.setKey(KeyboardEvent.KEY_LEFT);
        west.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(west);
        KeyboardEvent east = new KeyboardEvent();
        east.setKey(KeyboardEvent.KEY_RIGHT);
        east.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(east);
    }


    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if (!isCrashed() && !isBusted()){
            switch (keyboardEvent.getKey()) {
                case KeyboardEvent.KEY_UP:
                    if (isSober()) {
                        this.setCurrentDirection(Direction.NORTH);
                        getRep().changePicture(0);
                    } else {
                        this.setCurrentDirection(Direction.SOUTH);
                        getRep().changePicture(3);
                    }
                    break;

                case KeyboardEvent.KEY_DOWN:
                    if (isSober()) {
                        this.setCurrentDirection(Direction.SOUTH);
                        getRep().changePicture(2);
                    }
                    else {
                        this.setCurrentDirection(Direction.NORTH);
                        getRep().changePicture(1);
                    }
                    break;

                case KeyboardEvent.KEY_LEFT:
                    if(isSober()){
                        this.setCurrentDirection(Direction.WEST);
                        getRep().changePicture(4);
                    } else {
                        this.setCurrentDirection(Direction.EAST);
                        getRep().changePicture(7);
                    }
                    break;

                case KeyboardEvent.KEY_RIGHT:
                    if(isSober()){
                        this.setCurrentDirection(Direction.EAST);
                        getRep().changePicture(6);
                    } else {
                        this.setCurrentDirection(Direction.WEST);
                        getRep().changePicture(5);
                    }
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
    }


    @Override
    public void moveCar() {

        if (collisionDetector != null) {
            collisionDetector.checkCollision(this);
            policeRadar.checkRadar(this);
            powerUpDetector.checkPowerUpsAquisition(this);
        }

        if (isTimeToMove()) {

            getCurrentDirection();
            getRep().update(getCurrentDirection());
            setMoveCounter(getSpeed());

        } else {

            setMoveCounter(getMoveCounter() + INCREMENT);
        }
    }

    @Override
    public void setDrinkEffect() {
    }

    /*
    public class Fiat extends Car implements KeyboardHandler {

 public static int getFiat(GameObject[] gameObjects) {
    for (int i = 0; i < gameObjects.length; i++) {
        if (gameObjects[i] instanceof Fiat) {
            return i;
            }
       }
       return -1;
   }
*/

}
