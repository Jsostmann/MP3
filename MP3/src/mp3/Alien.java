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

    private ActionPane actionPane;
    private int currentImage;
    private int type;
    private Rectangle2D[] viewPorts;
    public Rectangle2D destroyed;
    public Rectangle2D blank = new Rectangle2D(230, 353, 29, 17);
    public boolean visible = true;

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

        setSpriteSheet();
        this.currentImage = currentImage;
        this.actionPane = actionPane;
        this.type = type;
        viewPorts = new Rectangle2D[2];

        switch (type) {

            case 0:
                createLargeAlien();
                break;
            case 1:
                createMediumAlien();
                break;
            case 2:
                createSmallAlien();
                break;

        }

        this.setViewport(viewPorts[0]);

    }

    private void createLargeAlien() {
        setPointValue(100);
        viewPorts[0] = new Rectangle2D(7, 414, 31, 18);
        viewPorts[1] = new Rectangle2D(7, 437, 31, 18);
        destroyed = new Rectangle2D(181, 421, 27, 18);

    }

    private void createMediumAlien() {
        setPointValue(50);
        viewPorts[0] = new Rectangle2D(54, 412, 31, 19);
        viewPorts[1] = new Rectangle2D(54, 436, 31, 19);
        destroyed = new Rectangle2D(145, 432, 27, 19);

    }

    private void createSmallAlien() {
        setPointValue(10);
        viewPorts[0] = new Rectangle2D(102, 415, 30, 21);
        viewPorts[1] = new Rectangle2D(102, 435, 30, 21);
        destroyed = new Rectangle2D(145, 413, 30, 18);
    }

    
    public int getCurrentImage() {
        return currentImage;
    }

    public void toggleImage(int currentImage) {

        this.currentImage = currentImage;
        this.setViewport(viewPorts[currentImage]);
    }

    public void toggleDestroyed() {
        this.setViewport(destroyed);

    }

    public void toggleBlank() {
        this.setViewport(blank);
    }

    @Override
    public void move() {

    }

}
