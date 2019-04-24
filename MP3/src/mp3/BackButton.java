
package mp3;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/**
 *
 * @author jamesostmann
 */
public class BackButton extends Pane{ 
    
 
    private Circle c;
    private Line l1, l2;
    
    public BackButton() {
        
        this.setMaxSize(25.0, 25.0);
   
        l1 = new Line(0.0 ,this.getMaxHeight() / 2.0 , this.getMaxWidth() ,0.0);
        l1.setFill(Color.WHITE);
        l1.setStrokeWidth(2.0);
        
        
        l2 = new Line(0.0, this.getMaxHeight() / 2.0, this.getMaxWidth(), this.getMaxHeight());
        l2.setFill(Color.WHITE);
        l2.setStrokeWidth(2.0);
        
        c = new Circle();
        c.setFill(Color.LIME); 
        c.setRadius(25.0 - 1.0);
        c.setCenterX(25.0); 
        c.setCenterY(25.0);
         
        
        
        this.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, null ,new BorderWidths(1.0,1.0,1.0,1.0)))); 

        setBackground(new Background(new BackgroundFill(Color.LIME,null,null))); 
        getChildren().addAll(l1,l2); 
        
    }
    
}
