/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

/**
 *
 * @author jamesostmann
 */
public class Alien extends Invader {

    private Projectile missle;
    private ActionPane actionPane;
    private int currentImage;
    private int type;
    private boolean alive;
    private Rectangle2D[] viewPorts;
    public Rectangle2D destroyed;
    public Rectangle2D blank = new Rectangle2D(230, 353, 29, 17);

    private void setSpriteSheet() {
        try {

            Image image = new Image(new FileInputStream("res/spritesheet.jpg"));
            this.setImage(image);
            this.setScaleX(1);
            this.setScaleY(1);

        } catch (FileNotFoundException e) {

            System.err.println("Couldnt find SpriteSheet");
            System.exit(-1);
        }
    }

    public Alien(ActionPane actionPane, int type, int currentImage) {

        super(0.0, 1.0, 0.0, 0.0, 0);

        setSpriteSheet();
        this.actionPane = actionPane;
        this.missle = new Projectile();
        this.missle.setVisible(false);
        this.actionPane.getChildren().add(missle);
        this.currentImage = currentImage;

        this.type = type;
        viewPorts = new Rectangle2D[2];
        alive = true;

        switch (type) {

            case 0:
                createSmallAlien();
                break;
            case 1:
                createMediumAlien();
                break;
            case 2:
                createLargeAlien();
                break;

        }

        this.setViewport(viewPorts[0]);

    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() {
        return this.alive;
    }

    public void setMissle() {

        this.missle.setDirection(Movable.SOUTH);
        this.missle.setX(getX() + this.getViewport().getWidth() / 2.0);
        this.missle.setY(getY() + this.getViewport().getWidth() - 5.0);
        this.missle.setVisible(true);

    }

    public Projectile getMissle() {
        return this.missle;
    }

    public void fireMissle() {
    
            
            if (this.missle.getY() >= 600) {
                
                this.missle.setVisible(false); 
                this.actionPane.getHord().setShot(false); 
                
            } else {
                
                this.missle.move();
            
        }

    }

    private void createLargeAlien() {

        setPointValue(40);
        viewPorts[0] = new Rectangle2D(102, 415, 30, 21);
        viewPorts[1] = new Rectangle2D(102, 435, 30, 21);
        destroyed = new Rectangle2D(145, 413, 30, 18);

    }

    private void createMediumAlien() {
        setPointValue(20);
        viewPorts[0] = new Rectangle2D(54, 412, 31, 19);
        viewPorts[1] = new Rectangle2D(54, 436, 31, 19);
        destroyed = new Rectangle2D(145, 432, 27, 19);

    }

    private void createSmallAlien() {
        setPointValue(10);
        viewPorts[0] = new Rectangle2D(7, 414, 31, 18);
        viewPorts[1] = new Rectangle2D(7, 437, 31, 18);
        destroyed = new Rectangle2D(181, 421, 27, 18);
    }

    public int getCurrentImage() {
        return currentImage;
    }

    public void toggleImage() {
        
        this.setViewport(viewPorts[currentImage]); 
        
        switch(currentImage) {
            case 0:
                currentImage = 1;
                break;
            case 1: 
                currentImage = 0;
                break;
        }
        
        
    }
    public void toggleImage(int currentImage) {

        this.currentImage = currentImage;
        this.setViewport(viewPorts[currentImage]); 
        this.setVisible(true);
    }

    public void toggleDestroyed() {
        this.setViewport(destroyed);

    }

    public void toggleBlank() {
        
        this.setVisible(false); 
    }

    private void moveXY() {

        if (this.getDirection() != Movable.SOUTH) {

            double newX = getX() + getSpeed() * Math.cos(Math.toRadians(getDirection()));

            setX(newX);

        } else {

            double newY = getY() + 5.0;

            setY(newY);

        }

    }

    @Override
    public void move() {

        moveXY();

    }

}
