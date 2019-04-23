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
public class Projectile extends GameObject {

    private boolean shot;

    public Projectile() {
        
        super(Movable.NORTH,5.0,0.0,0.0);
        
        this.shot = false;
        setProjectileImage();

    }

    private void setProjectileImage() {

        try {

            Image image = new Image(new FileInputStream("res/spritesheet.jpg"));
            this.setImage(image);
            Rectangle2D viewportRect = new Rectangle2D(147, 354, 2, 10);
            this.setViewport(viewportRect);
            this.setScaleX(2);
            this.setScaleY(2);

        } catch (FileNotFoundException e) {

            System.err.println("Couldnt find SpriteSheet");
            System.exit(-1);
        }
    }

    public void setShot(boolean shot) {
        this.shot = shot;
    }

    public boolean isShot() {
        return this.shot;
    }

    @Override
    public void move() {

        double newY = this.getY() + getSpeed() * Math.sin(Math.toRadians(getDirection()));
        this.setY(newY);

    }

}
