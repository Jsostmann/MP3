/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

/**
 *
 * @author jamesostmann
 */
public class SpaceShip extends Invader {
    
    private final double[] direction = {Movable.EAST,Movable.WEST};
    
    private Timer moveTimer;
    private Timer launchTimer;
    private GamePane gamePanel;
    private Random rand;
    private int randNum;
    private boolean moving;
    
    public SpaceShip() {
        
        setSpaceShipViewPort();
        launchTimer = new Timer();
        moveTimer = new Timer();
        rand = new Random();
        randNum = 0;
        moving = false;
        this.setX(-this.getViewport().getWidth()); 
        this.setY(getViewport().getHeight() / 2.0);

        
    }
    
    private void setSpaceShipViewPort() {
       try {


            Image image = new Image(new FileInputStream("res/spritesheet.jpg"));
            this.setImage(image); 
            Rectangle2D view = new Rectangle2D(168,177,42,19);
            setViewport(view);

        } catch (FileNotFoundException e) {

            System.err.println("Couldnt find SpriteSheet");
            System.exit(-1);
        }
    
    }
    
    public boolean isMoving() {
        return moving;
    }
    public void setMoving(boolean moving) {
        this.moving = moving;
    }
    public void startLaunchTimer() {
        
        TimerTask t = new LaunchTimer();
        launchTimer.schedule(t,3000,20); 
        
    
    }
    
    public void stopLaunchTimer() {
        
        launchTimer.cancel();
    }
    
    
    public void startMovementTimer() {
      
       resetShip();
       TimerTask t = new MovementTimer();
       moveTimer = new Timer();
       moveTimer.schedule(t,3000,20);
       
    }
    
   
    public void stopMovementTimer() {
        
    
      moveTimer.purge();
      
      
    }
    private void resetShip(){
        
        setVisible(true);
        moving = true;
    }
    
    
    public int getRandom() {
        this.randNum = rand.nextInt(25000 - 5000) + 5000;
        return randNum;
        
    }
    
    @Override
    public void move() {
        double h = direction[0];
        this.setX(this.getX() + 1.0); 
    }
    
    
    // Inner Class 
    class LaunchTimer extends TimerTask {

        @Override
        public void run() {
            
         
        }
    
    }
    class MovementTimer extends TimerTask {

        @Override
        public void run() {
            
             
        }
    
    }
}
