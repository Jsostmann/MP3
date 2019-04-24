
package mp3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

/**
 *
 * @author jamesostmann
 */
public class Missile extends Projectile{
    
    public Missile() {
        
      setVisible(false); 
      setSpeed(2.0);  
      setDirection(Movable.NORTH);  
      setShot(false); 
      
      setMissileImage();
        
       
    }
        
    private void setMissileImage(){
        
       try {

            Image image = new Image(new FileInputStream("res/spritesheet.jpg"));
            setImage(image);
            Rectangle2D viewportRect = new Rectangle2D(173, 1194, 3, 6);
            setViewport(viewportRect);
            setScaleX(2);
            setScaleY(2);

        } catch (FileNotFoundException e) {

            System.err.println("Couldnt find SpriteSheet");
            System.exit(-1);
        }
    
    }
    
}
