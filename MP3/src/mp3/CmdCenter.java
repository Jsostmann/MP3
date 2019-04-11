/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author jamesostmann
 */
public class CmdCenter extends GameObject {

    private Projectile projectile;
    private ActionPane actionPane;
    private CmdCenterAnimation t;

    public CmdCenter() {

        this(null);
    }

    public CmdCenter(ActionPane actionPane) {

        this.projectile = null;
        //this.t = new CmdCenterAnimation();
        this.actionPane = actionPane;
        this.setSpeed(10.0);
        setCmdViewPort();

        this.setParentWidth(actionPane.getPrefWidth());
        this.setParentHeight(actionPane.getPrefHeight());

        actionPane.getChildren().addAll(this);

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
    }

    public void fireProjectile() {

        /* if(!actionPane.getChildren().contains(projectile)) {
           
        projectile = new Projectile();
        projectile.setX(getX() + getViewport().getWidth() / 2.0);
        projectile.setY(getY() + 2.0);
        actionPane.getChildren().add(projectile);
       
       }
         
         t.start();

         */
        t = new CmdCenterAnimation();

        t.start();

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

        if (GamePane.isgoEast() && this.getX() < 551.0 - 104.0) {

            this.setDirection(EAST);
            newX += getSpeed() * Math.cos(Math.toRadians(getDirection()));

        }

        if (GamePane.isGoWest() && this.getX() > -1.0) {

            this.setDirection(WEST);
            newX -= getSpeed() * -Math.cos(Math.toRadians(getDirection()));

        }

        this.setX(newX);

    }

    // Inner Class
    class CmdCenterAnimation extends AnimationTimer {

        public long previous = 0;

        private Projectile projectile;

        public CmdCenterAnimation() {

            projectile = new Projectile();
            projectile.setX(getX() + getViewport().getWidth() / 2.0);
            projectile.setY(getY() + 2.0);
            actionPane.getChildren().add(projectile);

        }

        @Override
        public void handle(long now) {

            if (now - previous >= 200000) {

                if (this.projectile.getY() >= -1) {

                    for (int y = 0; y < actionPane.horde.length; y++) {
                        for (int x = 0; x < actionPane.horde[y].length; x++) {
                            if(actionPane.horde[y][x] != null)
                            if (this.projectile.getBoundsInParent().intersects(actionPane.horde[y][x].getBoundsInParent())) {

                                actionPane.getChildren().remove(actionPane.horde[y][x]);
                                actionPane.horde[y][x] = null;
                                actionPane.getChildren().remove(projectile);
                                stop();

                            }
                        }
                    }

                    this.projectile.move();

                } else {

                    actionPane.getChildren().remove(projectile);

                }

            }

            previous = now;
        }
    }

}
