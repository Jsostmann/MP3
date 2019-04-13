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

    private Timer moveTimer;
    private Timer launchTimer;
    private GamePane gamePanel;
    private Random rand;
    private int randNum;
    
    
    public SpaceShip() {
        
        setSpaceShipViewPort();
        launchTimer = new Timer();
        moveTimer = new Timer();
        rand = new Random();
        randNum = 0;
        
    }
    
    private void setSpaceShipViewPort() {
       try {


            Image image = new Image(new FileInputStream("res/spritesheet.jpg"));
            
            this.setImage(image);   
            
            Rectangle2D view = new Rectangle2D(304,624,156,73);
            this.setViewport(view);
            
            

            setX(-200);
            setY(300);
            setScaleX(.3);
            setScaleY(.3);
           
            

        } catch (FileNotFoundException e) {

            System.err.println("Couldnt find SpriteSheet");
            System.exit(-1);
        }
    
    }
    
    public void startLaunchTimer() {
        
        TimerTask t = new LaunchTimer();
        launchTimer.schedule(t,3000,20); 
        
    
    }
    
    public void stopLaunchTimer() {
        
        launchTimer.cancel();
    }
    
    
    public void startMovementTimer() {
        
       TimerTask t = new MovementTimer();
       moveTimer.schedule(t,3000,20);
    }
    
    public void stopMovementTimer() {
        moveTimer.cancel();
    }
    
    
    
    public int getRandom() {
        this.randNum = rand.nextInt(25000 - 5000) + 5000;
        return randNum;
        
    }
    
    @Override
    public void move() {
        
        this.setX(this.getX() + 5.0); 
        
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
               if(getX() + (getViewport().getWidth() * .3)  >= 542) {
            
                this.cancel();
                
            }
            
            move();
            
            
        }
    
    }
}
