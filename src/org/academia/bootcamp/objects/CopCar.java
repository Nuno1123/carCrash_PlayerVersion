package org.academia.bootcamp.objects;


import org.academia.bootcamp.RandomGenerator;
import org.academia.bootcamp.field.Direction;
import org.academia.bootcamp.field.Field;
import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * Created by codecadet on 02/02/16.
 */
public class CopCar extends Car {

    private Coupe coupe;
    private int persuitCounter;
    private static final int PERSUIT_MAX = 20;
    private static final int MAXRANDOM = 100;
    private int randomPersuit = 0;


    public CopCar() {
        super(CarType.COPCAR, 8);

        String[] paths = new String[] {"copCar_north.png",
                "drunkCopCar_north.png",
                "copCar_south.png",
                "drunkCopCar_south.png",
                "copCar_west.png",
                "drunkCopCar_west.png",
                "copCar_east.png",
                "drunkCopCar_east.png",
                "rip.png",
                "busted.png"
        };

        Picture[] pictures = new Picture[] {

                new Picture(getRep().getX(), getRep().getY(), paths[0]),
                new Picture(getRep().getX(), getRep().getY(), paths[1]),
                new Picture(getRep().getX(), getRep().getY(), paths[2]),
                new Picture(getRep().getX(), getRep().getY(), paths[3]),
                new Picture(getRep().getX(), getRep().getY(), paths[4]),
                new Picture(getRep().getX(), getRep().getY(), paths[5]),
                new Picture(getRep().getX(), getRep().getY(), paths[6]),
                new Picture(getRep().getX(), getRep().getY(), paths[7]),
                new Picture(getRep().getX(), getRep().getY(), paths[8]),
                new Picture(getRep().getX(), getRep().getY(), paths[9])
        };

        getRep().setPictures(pictures);
        setDirection(Direction.WEST);
        getRep().setCurrentPicture(pictures[4]);
        getRep().getCurrentPicture().draw();
        setPos();
    }

    @Override
    public void setPos() {

        int x = 10 + Field.width - getRep().getCurrentPicture().getWidth();
        int y = RandomGenerator.generateRandom(Field.height/2, Field.height - getRep().getCurrentPicture().getHeight());

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