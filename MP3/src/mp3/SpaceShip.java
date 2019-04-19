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
    private TimerTask moveTask;
    private Random rand;
    private int randNum;
    private boolean moving;
    private Image[] images;
    private Rectangle2D[] viewPorts;
    private final int[] randomPoints;
    private int randomIndex;
    private boolean hit;
    
    public SpaceShip(ActionPane actionPane) {
        
        actionPane.getChildren().add(this); 
        images = new Image[2];
        viewPorts = new Rectangle2D[4];
        randomPoints = new int[3];
        init();
        launchTimer = new Timer();
        moveTimer = new Timer();
        moveTask = new MovementTimer();  
        rand = new Random();
        randNum = 0;
        moving = false;
        hit = false;
        this.setVisible(false); 
        randomIndex = 0;
       
        
    } 
    
    private void init() {
       try {


            Image image = new Image(new FileInputStream("res/spritesheet.jpg"));
            this.setImage(image); 
            images[0] = image;
            
            Rectangle2D view = new Rectangle2D(168,177,42,19);
            viewPorts[0] = view;
            
            
            this.setX(0); 
            this.setY(0);
            setDirection(Movable.EAST);
            setSpeed(3.0);
            
            setPoints();
            this.setImage(images[0]); 
            this.setViewport(viewPorts[0]); 
            
        } catch (FileNotFoundException e) {

            System.err.println("Couldnt find SpriteSheet");
            System.exit(-1);
        }
    
    }
    private void setPoints(){
        try {
        
            randomPoints[0] = 100;
            randomPoints[1] = 200;
            randomPoints[2] = 300;        
             
           Image pointsImage = new Image(new FileInputStream("res/points.png")); 
           images[1] = pointsImage;
            
            viewPorts[1] = new Rectangle2D(102,83,116,67);
           
            viewPorts[2] = new Rectangle2D(110,172,117,67);
            
            viewPorts[3] = new Rectangle2D(107,263,116,67);     
       
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
        
  
        
    
    }
    
    public void setHit(boolean hit) {
        this.hit = hit;
    }
    
    
    public void startMovementTimer() {
        
       this.randomIndex = new Random().nextInt(3); 
       this.toggleShip();
       
       this.setX(-this.getViewport().getWidth()); 
       
       this.setHit(false); 
       this.setVisible(true);
       this.setMoving(true); 
       
      
       moveTimer = new Timer();
       moveTimer.schedule(new MovementTimer(),getRandom(),30);
       
       System.out.println(randNum);
       System.out.println(randomIndex + 1);
       
    }
    
    public int getRandomIndex() {
        return this.randomIndex;
    }
    
   public void togglePoint() {
       
       this.setImage(images[1]);
       this.setViewport(viewPorts[randomIndex + 1]); 
       this.setScaleX(.3);
       this.setScaleY(.3); 
       this.setHit(true);
       
   }
   
   public void toggleShip(){
   
       this.setImage(images[0]); 
       this.setViewport(viewPorts[0]); 
       this.setScaleX(1);
       this.setScaleY(1);
   }
    public void stopMovementTimer() {
        
      this.setHit(false); 
      this.setMoving(false);
      this.setVisible(false);
      moveTimer.cancel();
      
      
    }
    
    
    
    public int getRandom() {
        this.randNum = rand.nextInt(25000 - 5000) + 5000;
        return randNum;
        
    }
    
    @Override
    public void move() {
      /*  
        if(this.getX() <= 0 ) {
        
            setSpeed(-getSpeed());
            this.setY(this.getY() + 5.0);

        }
        if(this.getX() + this.getViewport().getWidth() >= 545) {
        
            setSpeed(-getSpeed());
            this.setY(this.getY() +5.0);
        }
        
        double newX = getX() + getSpeed() * Math.cos(Math.toRadians(getDirection()));
        setX(newX);
        */
      
        double newX = getX() + getSpeed() * Math.cos(Math.toRadians(getDirection()));
        setX(newX);
    }
    
    
   
    class MovementTimer extends TimerTask {
        
        double time = 0;
        
        @Override
       
        public void run() {
            
            if(getX() >= 545) {
                
                time = 0;
                setVisible(false);
                setMoving(false);
                this.cancel();
               
            }
           
           if(!hit) { 
              
               move();
           }
           
           if(hit && time < 3) {
           
               time += .03;
               
           }
           
           if(time >= 3) {
               time = 0;
               setVisible(false);
               setMoving(false);
               this.cancel();
               
           }
             
        }
    
    }
}
