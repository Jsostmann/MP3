/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author jamesostmann
 */
public class LoadingScreen extends VBox {
    
   
    private Image bgImage;
    private Label[] labels;
    private ImageView[] imageViews;
    
    private Button[] buttons;
    private HBox buttonRow;
    private HBox[] alienRows;
    
    
    public LoadingScreen(){
        
        
        
        imageViews = new ImageView[4];
        createImageViews();
        
        
        alienRows = new HBox[4];
        createAlienRows();
        
        
        labels = new Label[4];
        createLabels();
        
        
        buttons = new Button[2];
        buttonRow = new HBox();
        createButtonRow();
        
        setPadding(new Insets(115,0,0,0));   
        setSpacing(50.0);
        setPrefSize(550,700); 
        BackgroundSize backgroundSize = new BackgroundSize(550,700,false,false,false,false);
        BackgroundImage backgroundImage = new BackgroundImage(bgImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,backgroundSize);
        setBackground(new Background(backgroundImage)); 
        
       
    }
    
    private void createButtonRow(){
    
        buttonRow.setSpacing(50);
        buttonRow.setAlignment(Pos.CENTER); 
        
        for(int i = 0; i < buttons.length; i++) {
            
            buttons[i] = new Button();
            buttonRow.getChildren().add(buttons[i]);
        }
        
        buttons[0].setText("Start Game"); 
        buttons[1].setText("Exit Game");
        
        this.getChildren().add(buttonRow);
        
    }
    private void createLabels(){
        
        labels[0] = new Label("Small Alien - 10 Pts");
        labels[1] = new Label("Medium Alien - 20 Pts");
        labels[2] = new Label("Large Alien - 40 Pts");
        labels[3] = new Label("Space Ship - ? Pts");
        
        for(int i = 0; i < labels.length; i++) {
        
            labels[i].setFont(new Font(25));
            labels[i].setTextFill(Color.WHITE); 
            alienRows[i].getChildren().add(labels[i]);
        }
        
    }
    private void createImageViews(){
    
        
        imageViews[0] = new ImageView();
        
        imageViews[1] = new ImageView();
        
        imageViews[2] = new ImageView();
        
        imageViews[3] = new ImageView();
        
        
        
        setImages();
        
        imageViews[0].setViewport(new Rectangle2D(102,415,30,21)); 
        imageViews[1].setViewport(new Rectangle2D(54,412,31,19)); 
        imageViews[2].setViewport(new Rectangle2D(7,414,31,18)); 
        imageViews[3].setViewport(new Rectangle2D(168,177,42,19)); 
        
        
        
    }
    private void createAlienRows(){
        
        
        
        
        for(int i = 0; i < alienRows.length; i++) {
        
            alienRows[i] = new HBox(imageViews[i]); 
            alienRows[i].setAlignment(Pos.CENTER); 
            alienRows[i].setSpacing(50.0);
            
            this.getChildren().add(alienRows[i]); 
            
        }
        
        
    
    }
    private void setImages(){
       try {
           
            bgImage = new Image(new FileInputStream("res/loadingBg.jpg")); 
            Image image = new Image(new FileInputStream("res/spritesheet.jpg"));
            
            for(int i = 0; i < imageViews.length; i++) {
            
                imageViews[i].setImage(image);  
            
            }
            

        } catch (FileNotFoundException e) {

            System.err.println("Couldnt find SpriteSheet");
            System.exit(-1);
        }
    }
    
}
