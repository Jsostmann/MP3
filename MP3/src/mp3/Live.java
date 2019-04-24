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
import javafx.scene.image.ImageView;

/**
 *
 * @author James Ostmann
 */
public class Live extends ImageView {
    
    public Live(){
    
        setImage();
        setVisible(true);
    }
    
    
    public void setImage(){
     try {
           
            Image image = new Image(new FileInputStream("res/spritesheet.jpg"));
            this.setImage(image);
            this.setScaleX(1);
            this.setScaleY(1);
            Rectangle2D viewPort = new Rectangle2D(220,914,22,17);
            this.setViewport(viewPort);

        } catch (FileNotFoundException e) {

            System.err.println("Couldnt find SpriteSheet");
            System.exit(-1);
        }
    }
    public void setVisibility(boolean visibile){

        this.setVisible(visibile); 

    }
}
