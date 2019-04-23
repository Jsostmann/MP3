/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

/**
 *
 * @author jamesostmann
 */
public class SpaceShip extends Invader {

    private double currentSound;
    private boolean atEdge;
    private boolean hit;
    private Random rand;
    private int currentPoint;
    private int randomInterval;
    private int textInterval;
    private Image[] images;
    private Rectangle2D[] viewPorts;
    private double[] randomDirections;
    private int currentTime;
    private ActionPane actionPane;

    public SpaceShip(ActionPane actionPane) {

        this.actionPane = actionPane;

        init();

    }

    private void init() {

        currentSound = 0.0;
        atEdge = false;
        hit = false;
        rand = new Random();
        randomDirections = new double[]{Movable.WEST, Movable.EAST};

        randomInterval = rand.nextInt(25000 - 5000) + 5000;
        currentPoint = rand.nextInt(3);

        textInterval = 0;
        currentTime = 0;
        images = new Image[2];
        viewPorts = new Rectangle2D[4];
        setVisible(false);

        try {

            Image image = new Image(new FileInputStream("res/spritesheet.jpg"));
            this.setImage(image);
            images[0] = image;

            Rectangle2D view = new Rectangle2D(168, 177, 42, 19);
            viewPorts[0] = view;

            setViews();
            this.setImage(images[0]);
            this.setViewport(viewPorts[0]);

            resetLocation();
            setSpeed(1);

        } catch (FileNotFoundException e) {

            System.err.println("Couldnt find SpriteSheet");
            System.exit(-1);
        }

        actionPane.getChildren().add(this);

    }

    private void setViews() {
        try {

            Image pointsImage = new Image(new FileInputStream("res/points.png"));
            images[1] = pointsImage;

            viewPorts[1] = new Rectangle2D(102, 83, 116, 67);

            viewPorts[2] = new Rectangle2D(110, 172, 117, 67);

            viewPorts[3] = new Rectangle2D(107, 263, 116, 67);

        } catch (FileNotFoundException e) {
            System.err.println("Couldnt find SpriteSheet");
            System.exit(-1);

        }

    }

    public void resetLocation() {

        setDirection(randomDirections[rand.nextInt(2)]);

        if (this.getDirection() == randomDirections[0]) {

            this.setX(-this.getViewport().getWidth());

        } else if (this.getDirection() == randomDirections[1]) {

            this.setX(545);

        }

        this.setY(25);
    }

    public void launch() {

        if (canLaunch()) {

            move();

        }

        if (!isShowingDead()) {

            resetSpaceShip();

        }

    }

    public boolean isShowingDead() {

        if (hit) {

            textInterval += 15;
        }

        return this.textInterval <= 2000;

    }

    public boolean canLaunch() {

        currentTime += 15;

        return currentTime >= this.randomInterval && !this.hit;

    }

    public void setHit(boolean hit) {

        this.hit = hit;
    }

    public void resetSpaceShip() {

        currentPoint = rand.nextInt(3);
        randomInterval = rand.nextInt(25000 - 5000) + 5000;
        textInterval = 0;
        currentTime = 0;

        resetLocation();
        toggleShip();

    }

    public boolean isHit() {
        return hit;
    }

    public void togglePoint() {

        this.setHit(true);
        this.setImage(images[1]);
        this.setViewport(viewPorts[currentPoint + 1]);
        this.setScaleX(.3);
        this.setScaleY(.3);

    }

    public void toggleShip() {

        this.setVisible(false);
        this.setHit(false);
        this.setImage(images[0]);
        this.setViewport(viewPorts[0]);
        this.setScaleX(1);
        this.setScaleY(1);
    }

    private boolean isEnteringActionPane() {
        return this.getX() == -this.getViewport().getWidth() || this.getX() == 545;
    }

    private boolean atEdge() {

        return this.getX() <= -this.getViewport().getWidth() && this.getDirection() == randomDirections[1]
                || this.getX() >= 545 && this.getDirection() == randomDirections[0];

    }

    @Override
    public void move() {

        if (isEnteringActionPane()) {
            Sound.playSound(3);
        }

        if (!isVisible()) {
            this.setVisible(true);
        }

        if (atEdge()) {

            resetSpaceShip();

        } else {

            if (getDirection() == randomDirections[0]) {

                double newX = getX() - getSpeed() * Math.cos(Math.toRadians(getDirection()));
                setX(newX);
            }

            if (getDirection() == randomDirections[1]) {

                double newX = getX() - getSpeed() * Math.cos(Math.toRadians(getDirection()));
                setX(newX);
            }

        }

    }

}
