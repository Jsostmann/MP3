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
public class CmdCenter extends GameObject {

    private int cmdCenterPoints;
    private Projectile projectile;
    private ActionPane actionPane;
    

    public CmdCenter() {

        this(null);
    }

    public CmdCenter(ActionPane actionPane) {
        
        super(Movable.NOWHERE,5.0,actionPane.getPrefWidth(),actionPane.getPrefHeight());
       

        cmdCenterPoints = 0;
        this.actionPane = actionPane;
       
        setCmdViewPort();
        initProjectile();
        
        this.actionPane.getChildren().add(projectile);
    }

    private void initProjectile() {
        
        projectile = new Projectile();
        projectile.setX(getX() + getViewport().getWidth() / 2.0);
        projectile.setY(getY() + 1.0);
        projectile.setVisible(false);
        projectile.setShot(false);
    }

    private void setCmdViewPort() {
        try {

            Image image = new Image(new FileInputStream("res/spritesheet.jpg"));

            this.setImage(image);

            Rectangle2D view = new Rectangle2D(10, 78, 104, 67);
            this.setViewport(view);

            this.setX(210.0);
            this.setY(550.0);
            this.setScaleX(.5);
            this.setScaleY(.5);

        } catch (FileNotFoundException e) {

            System.err.println("Couldnt find SpriteSheet");
            System.exit(-1);
        }
        
        actionPane.getChildren().add(this);
    }

    public void fireProjectile() {

        if (!projectile.isVisible()) {

            projectile.setX(getX() + getViewport().getWidth() / 2.0);
            projectile.setY(getY() + 1.0);

            projectile.setVisible(true);
            
            Sound.playSound(0);

        }
        
        if (projectile.isVisible() && projectile.getY() >= 15) {

           
                checkForHitSpaceShip();

                checkForHitAlien();

                projectile.move();

        } else {

                projectile.setShot(false);
                projectile.setVisible(false);
            }
        }

    


    public void checkForHitSpaceShip() {

        SpaceShip s = actionPane.getSpaceShip();

        if (s.isVisible() && !s.isHit()) {

            if (projectile.intersects(s.getBoundsInParent())) {
                Sound.playSound(1); 
                s.togglePoint();
                projectile.setVisible(false);
                projectile.setShot(false);
            }

        }

    }

    public void checkForHitAlien() {

        for (int y = 0; y < actionPane.getHord().getHordSize(); y++) {

            for (int x = 0; x < actionPane.getHord().getHordRowSize(y); x++) {

                boolean lowestCollumn = actionPane.getHord().getColumnStateValue(x) == actionPane.getHord().getHordSize() - y - 1;

                if (lowestCollumn) {

                    Alien tempAlien = actionPane.getHord().getAlien(y, x);

                    if (projectile.getBoundsInParent().intersects(tempAlien.getBoundsInParent()) && tempAlien.isAlive()) {
                        tempAlien.setAlive(false);
                        Sound.playSound(1);
                        tempAlien.toggleDestroyed();
                        projectile.setVisible(false);
                        projectile.setShot(false);
                        cmdCenterPoints += tempAlien.getPointValue();
                        actionPane.getHord().upateNumLiving();
                        updatePoints(cmdCenterPoints);
                        System.out.println(actionPane.getHord().getNumLiving());
                        actionPane.getHord().updateColumnState(x);

                    }
                }
            }
        }
    }

    public Projectile getProjectile() {
        return projectile;
    }

    public void setProjectile(Projectile projectile) {
        this.projectile = projectile;
    }

    @Override
    public void move() {

        double newX = this.getX();

        if (getDirection() != NOWHERE) {
            if (getDirection() == EAST && this.getX() + this.getViewport().getWidth() <= 545) {

                this.setDirection(EAST);
                newX += getSpeed() * Math.cos(Math.toRadians(getDirection()));

            }

            if (getDirection() == WEST && this.getX() >= 0) {

                this.setDirection(WEST);
                newX -= getSpeed() * -Math.cos(Math.toRadians(getDirection()));

            }

            this.setX(newX);
        }
    }

    public ActionPane getActionPane() {
        return this.actionPane;
    }

    public int getCmdCenterPoints() {
        return this.cmdCenterPoints;
    }

    public void updatePoints(int pts) {
        StatusPane.updateStatus(CmdCenter.this, pts);
    }

    // Inner Class
    
}

