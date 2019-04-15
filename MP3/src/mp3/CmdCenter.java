/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;


/**
 *
 * @author jamesostmann
 */
public class CmdCenter extends GameObject {
    private int cmdCenterPoints = 0;
    private Projectile projectile;
    private ActionPane actionPane;
    private CmdCenterAnimation t;

    public CmdCenter() {

        this(null);
    }

    public CmdCenter(ActionPane actionPane) {

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

        /*
         this.projectile = new Projectile();
         projectile.setX(getX() + getViewport().getWidth() / 2.0);
         projectile.setY(getY() + 2.0);
         actionPane.getChildren().add(projectile);
      

        t.start();
         */
        t = new CmdCenterAnimation();
        t.start();
        Sound.playSound(0); 

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

        if (GamePane.isgoEast() && this.getX() + this.getViewport().getWidth() <= 545) {

            this.setDirection(EAST);
            newX += getSpeed() * Math.cos(Math.toRadians(getDirection()));

        }

        if (GamePane.isGoWest() && this.getX() >= 0) {

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

            if (now - previous >= 20000) {

                if (projectile.getY() >= -1) {

                    for (int y = 0; y < actionPane.horde.length; y++) {
                        for (int x = 0; x < actionPane.horde[y].length; x++) {
                            boolean lowestCollumn = actionPane.collumnStates[x] == actionPane.horde.length - y - 1;
                            if(lowestCollumn){
                            if (projectile.getBoundsInParent().intersects(actionPane.horde[y][x].getBoundsInParent()) && actionPane.horde[y][x].visible == true) {
                                Sound.playSound(1);
                                actionPane.horde[y][x].toggleDestroyed();
                                actionPane.getChildren().remove(projectile);
                                actionPane.horde[y][x].visible = false;
                                cmdCenterPoints += actionPane.horde[y][x].getPointValue();
                                System.out.println(cmdCenterPoints);
                                actionPane.collumnStates[x]++;
                                stop();

                            }
                        }
                        }
                    }
                    
                    for(int i = 0; i < actionPane.getChildren().size(); i++) {
                     
                        if(actionPane.getChildren().get(i) instanceof SpaceShip) {
                        
                            
                            SpaceShip s = (SpaceShip) actionPane.getChildren().get(i); 
                            
                            if(s.getBoundsInParent().intersects(projectile.getBoundsInParent())) {
                                
                                actionPane.getChildren().remove(projectile);
                                s.setVisible(false);
                                s.setMoving(false);
                                
                                
                                
                            }
                            
                            
                            
                        
                            
                        }
                    }
                  

                    projectile.move();

                } else {

                    actionPane.getChildren().remove(projectile);

                }

            }

            previous = now;
        }
    }

}







/*
 for(int i = actionPane.getChildren().size() - 1; i > -1; i--) {
                                if(actionPane.getChildren().get(i) instanceof Projectile) {
                                   Projectile p = (Projectile) actionPane.getChildren().get(i);
                                   p.move();
                                }

 for(int i = actionPane.getChildren().size() - 1; i > -1; i--) {
                                if(actionPane.getChildren().get(i) instanceof Projectile) {
                                    actionPane.getChildren().removeAll(actionPane.getChildren().get(i)); 
                                }
                                
                            }
*/
