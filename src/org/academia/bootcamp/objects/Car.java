package org.academia.bootcamp.objects;

//import org.academiadecodigo.carcrash.*;
import org.academia.bootcamp.RandomGenerator;
import org.academia.bootcamp.detectors.CollisionDetector;
import org.academia.bootcamp.detectors.PoliceRadar;
import org.academia.bootcamp.detectors.PowerUpDetector;
import org.academia.bootcamp.field.Direction;
import org.academia.bootcamp.field.Field;
import org.academia.bootcamp.field.Representation;

//TODO: objects are born in a specific place
//TODO: set timer to create cars every min - 1 copcar and 2 cars (see André's code)
//TODO: 1 copcar and 3 cars to start the game...create copcars from police station, cars from carpark and player from home
//TODO: if car/player/copcar !isExit, direction = East/West/West
//TODO: if car/player/copcar isBorn, cannot go to the park/home/station positions (create an additional "edge")
//TODO: 1 copcar, 1 car and 1 player at first second, and then each car 10 seconds later until all cars are born
//TODO: Set timer to SpeedBooster Power Up to 20 seconds
//TODO: Get game time and display as score!
//TODO: Add Initialize Menu with start option key ("RETURN")
//TODO: Add Restart option key ("SPACE BAR")
//TODO: Add music and soundFX

abstract public class Car {

    /**
     * The position of the car on the grid
     */
    private CarType carType;
    private Representation rep = null;
    private Direction direction = null;
    private boolean crashed;
    private CollisionDetector collisionDetector;
    private PowerUpDetector powerUpDetector;
    private PoliceRadar policeRadar;
    private double speed;
    private double strengthDrink;
    private boolean busted = false;
    private double moveCounter = 0;
    private static final int MAXRANDOM = 100;
    private static final int MINCOLLISIONRADIUS = 45;
    private static final int MINBUSTNESSRADIUS = 140;
    private static final double MAXSPEED = 14;
    private static final double INCREMENT = 2;
    private static final double INCREMENTALCHOOL = 3;
    private static final double SPEEDBOOSTER = 3;



    /**
     * Car Constructor
     */
    public Car(CarType carType, double speed) {
        rep = new Representation();
        this.carType = carType;
        direction = RandomGenerator.getRandomDirection();    // initializes a direction to begin with
        setSpeed(speed);
        setMoveCounter(speed);
        setDrinkEffect();

    }


    /**
     * Setters and Getters
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getMoveCounter() {

        return moveCounter;
    }

    public void setMoveCounter(double moveCounter) {
        this.moveCounter = moveCounter;
    }

    public double getSpeed() {
        return speed;
    }



    /**
     * Representation and Position
     */
    public Representation getRep() {
        return rep;
    }

    public void setPos() {

        int x = RandomGenerator.generateRandom(10, Field.width - rep.getCurrentPicture().getWidth());
        int y =RandomGenerator.generateRandom(10, Field.height - rep.getCurrentPicture().getHeight());

        rep.setX(x);
        rep.setY(y);
        chooseDirection();
        this.getRep().translate(x, y);

    }

    public boolean isEdge() {
        return rep.getX() == 0 || rep.getX() == Field.width - rep.getCurrentPicture().getWidth() ||
                rep.getY() == 0 || rep.getY() == Field.height - rep.getCurrentPicture().getHeight();
    }


    /**
     * Collisions
     */
    public void setCollisionDetector(CollisionDetector collisionDetector) {
        this.collisionDetector = collisionDetector;
    }

    public boolean comparePosition(Car car) {
        return collisionRadius(car) < MINCOLLISIONRADIUS;

    }

    public double collisionRadius(Car car) {
        double collisionXc = car.getRep().getCurrentPicture().getX() + (car.getRep().getCurrentPicture().getWidth())/2;
        double collisionYc = car.getRep().getCurrentPicture().getY() + (car.getRep().getCurrentPicture().getHeight()/2);
        double collisionXC = this.getRep().getCurrentPicture().getX() + (this.getRep().getCurrentPicture().getWidth()/2);
        double collisionYC = this.getRep().getCurrentPicture().getY() + (this.getRep().getCurrentPicture().getHeight()/2);

        double positionDifX = collisionXc - collisionXC;
        double positionDifY = collisionYc - collisionYC;

        return Math.sqrt(Math.pow(positionDifX,2) + Math.pow(positionDifY,2));
    }

    public boolean isCrashed() {

        return crashed;
    }

    public void setCrashed(boolean crashed) {
        this.crashed = crashed;
        if (isCrashed()) {
            rep.die();
        }
    }


    /**
     * Arrests
     */
    public void setPoliceRadar(PoliceRadar policeRadar) {
        this.policeRadar = policeRadar;
    }

    public boolean comparePositionRadar(Car car) {

        return collisionRadius(car) < MINBUSTNESSRADIUS;
    }

    public boolean isBusted() {
        return busted;
    }

    public void setBusted(boolean busted) {
        this.busted = busted;
        if (isBusted()) {
            rep.busted();
        }
    }


    /**
     * PowerUps
     */
    public void setPowerUpDetector(PowerUpDetector powerUpDetector) {
        this.powerUpDetector = powerUpDetector;
    }

    public boolean comparePositionPowerUp(PowerUp powerUp) {

        return collisionPowerUpRadius(powerUp, this) < MINCOLLISIONRADIUS;
    }

    public double collisionPowerUpRadius(PowerUp powerUp, Car car) {
        double collisionXp = powerUp.getRep().getCurrentPicture().getX() + (powerUp.getRep().getCurrentPicture().getWidth())/2;
        double collisionYp = powerUp.getRep().getCurrentPicture().getY() + (powerUp.getRep().getCurrentPicture().getHeight()/2);
        double collisionXC = car.getRep().getCurrentPicture().getX() + (car.getRep().getCurrentPicture().getWidth()/2);
        double collisionYC = car.getRep().getCurrentPicture().getY() + (car.getRep().getCurrentPicture().getHeight()/2);

        double positionDifX = collisionXp - collisionXC;
        double positionDifY = collisionYp - collisionYC;

        return Math.sqrt(Math.pow(positionDifX,2) + Math.pow(positionDifY,2));
    }

    public void speedBooster() {
        this.speed += SPEEDBOOSTER;
    }


    /**
     * Drunkness
     */
    public boolean isDrunk() {
        return strengthDrink > 50;
    }

    public boolean isSober() {
        return strengthDrink <= 50;
    }

    public void setDrinkEffect() {
        strengthDrink = RandomGenerator.generateRandom(0, MAXRANDOM);
        if (strengthDrink > 50){
            increaseSpeed();
        }
    }

    public void setStrengthDrink(double strengthDrink) {
        this.strengthDrink = strengthDrink;
    }

    public void increaseSpeed() {
        this.speed += INCREMENTALCHOOL;

    }


    /**
     * Car Move
     */
    public boolean isTimeToMove() {
        return moveCounter >= MAXSPEED;
    }


    public void moveCar() {

        if (collisionDetector != null) {
            collisionDetector.checkCollision(this);
            policeRadar.checkRadar(this);
            powerUpDetector.checkPowerUpsAquisition(this);
        }

        if (isTimeToMove()) {

            changeDirection();
            rep.update(direction);
            setMoveCounter(speed);

        } else {

            setMoveCounter(getMoveCounter() + INCREMENT);
        }



    }


    /**
     * Direction
     */
    public void changeDirection() {
        double directionChangeProbability;

        if (isEdge()) {
            chooseDirection();
        } else {
            directionChangeProbability = 1;

            if (RandomGenerator.generateRandom(0, MAXRANDOM) < directionChangeProbability) {
                chooseDirection();
            }
        }
    }

    public void chooseOppositeDirection(){
        switch (direction){
            case NORTH:
                setDirection(direction.SOUTH);
                getRep().update(direction);
                getRep().changePicture(2);
                break;
            case SOUTH:
                setDirection(direction.NORTH);
                getRep().update(direction);
                getRep().changePicture(0);
                break;
            case WEST:
                setDirection(direction.EAST);
                getRep().update(direction);
                getRep().changePicture(6);
                break;
            case EAST:
                setDirection(direction.WEST);
                getRep().update(direction);
                getRep().changePicture(4);
                break;
            default:
                getRep().changePicture(0);
        }
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void chooseDirection(){
        direction = RandomGenerator.getRandomDirection();
            switch (direction){
                case NORTH:
                    getRep().changePicture(0);
                    break;
                case SOUTH:
                    getRep().changePicture(2);
                    break;
                case WEST:
                    getRep().changePicture(4);
                    break;
                case EAST:
                    getRep().changePicture(6);
                    break;
                default:
                    getRep().changePicture(0);
            }
    }


    @Override
    public String toString() {
        return carType.getSymbol();
    }
}

