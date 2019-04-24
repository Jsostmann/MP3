
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
            setImage(image);
            images[0] = image;

            Rectangle2D view = new Rectangle2D(168, 177, 42, 19);
            viewPorts[0] = view;

            setViews();
            setImage(images[0]);
            setViewport(viewPorts[0]);

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

        if (getDirection() == randomDirections[0]) {

            setX(-getViewport().getWidth());

        } else if (getDirection() == randomDirections[1]) {

            setX(545);

        }

        setY(25);
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

        return textInterval <= 2000;

    }
    public boolean canLaunch() {

        currentTime += 15;

        return currentTime >= randomInterval && !hit;

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

        setHit(true);
        setImage(images[1]);
        setViewport(viewPorts[currentPoint + 1]);
        setScaleX(.3);
        setScaleY(.3);

    }
    public void toggleShip() {

        setVisible(false);
        setHit(false);
        setImage(images[0]);
        setViewport(viewPorts[0]);
        setScaleX(1);
        setScaleY(1);
    }
    private boolean isEnteringActionPane() {
        return getX() == -getViewport().getWidth() || getX() == 545;
    }
    private boolean atEdge() {

        return getX() <= -getViewport().getWidth() && getDirection() == randomDirections[1]
                || getX() >= 545 && getDirection() == randomDirections[0];

    }
    public int getCurrentPoint(){
        return currentPoint;
    }

    
    @Override
    public void move() {

        if (isEnteringActionPane()) {
            Sound.playSound(3);
        }

        if (!isVisible()) {
            setVisible(true);
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
