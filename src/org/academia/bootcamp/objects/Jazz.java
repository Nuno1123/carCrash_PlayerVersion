package org.academia.bootcamp.objects;

import org.academia.bootcamp.detectors.CollisionDetector;
import org.academia.bootcamp.RandomGenerator;
import org.academia.bootcamp.field.Direction;
import org.academia.bootcamp.field.Field;
import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * Created by codecadet on 02/02/16.
 */
public class Jazz extends Car {

    private Direction direction;
    private static final int MAXRANDOM = 100;
    private CollisionDetector collisionDetector;
    private int copCarPersuitDirectionCounter = 0;
    private static final double INCREMENT = 2;


    public Jazz() {
        super(CarType.JAZZ, 4);

        String[] paths = new String[]{"jazz_north.png",
                "drunkJazz_north.png",
                "jazz_south.png",
                "drunkJazz_south.png",
                "jazz_west.png",
                "drunkJazz_west.png",
                "jazz_east.png",
                "drunkJazz_east.png",
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
        setDirection(Direction.WEST);
        getRep().setCurrentPicture(pictures[4]);
        getRep().getCurrentPicture().draw();
        setPos();
    }

    @Override
    public void setPos() {

        int x = 10 + Field.width - getRep().getCurrentPicture().getWidth();
        int y = RandomGenerator.generateRandom(10, (Field.height/2) - getRep().getCurrentPicture().getHeight());

        getRep().setX(x);
        getRep().setY(y);
        setDirection(Direction.WEST);
        this.getRep().translate(x, y);

    }

    @Override
    public boolean isBusted() {
        return false;
    }

    @Override
    public boolean isCrashed() {
        return false;
    }

    @Override
    public boolean isDrunk() {
        return false;
    }

    @Override
    public void setDrinkEffect() {
    }


}


