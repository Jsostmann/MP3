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
public class Alien extends Invader{
    private ActionPane actionPane;
    private int currentImage;
    private int type;
    private Rectangle2D[] viewPorts;
    
    
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
        
        switch(type) {
            
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
    
    private void createLargeAlien(){
        
        viewPorts[0] = new Rectangle2D(7,414,31,18);
        viewPorts[1] = new Rectangle2D(7,437,31,18);
       
        
    
    }
    private void createMediumAlien(){
        
        viewPorts[0] = new Rectangle2D(54,412,31,19);
        viewPorts[1] = new Rectangle2D(54,436,31,19);
    }
    private void createSmallAlien(){
        
        viewPorts[0] = new Rectangle2D(102,415,30,21);
        viewPorts[1] = new Rectangle2D(102,435,30,21);
    }

    public ActionPane getActionPane() {
        return actionPane;
    }

    public void setActionPane(ActionPane actionPane) {
        this.actionPane = actionPane;
    }

    public int getCurrentImage() {
        return currentImage;
    }

    public void setCurrentImage(int currentImage) {
        
        this.currentImage = currentImage;
        this.setViewport(viewPorts[currentImage]); 
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Rectangle2D[] getViewPorts() {
        return viewPorts;
    }

    public void setViewPorts(Rectangle2D[] viewPorts) {
        this.viewPorts = viewPorts;
    }

    
    @Override
    public void move() {
        
        
        
    }
    
}
