/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3;

import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 *
 * @author jamesostmann
 */
public class ControlPane extends HBox {
    private AnimationTimer t = new ColorTimer();
    
    private Button save;
    private Button exit;
    private Button reset;
    private Button clear;
    private GamePane gamePane;
    
    public ControlPane(GamePane gamePane) {
        
        
        
        save = new Button("Save Game");
        save.setFocusTraversable(false);
        
        exit = new Button("Exit Game");
        exit.setFocusTraversable(false);
        
        reset = new Button("Reset Game");
        reset.setFocusTraversable(false);
        
        clear = new Button("Clear HighScore");
        clear.setFocusTraversable(false);
        
        
        this.setAlignment(Pos.CENTER); 
        this.setSpacing(15.0); 
        this.setPadding(new Insets(5,0,5,0)); 
        this.setBackground(new Background(new BackgroundFill(Color.GREEN,null,null)));  
        
        this.gamePane = gamePane;
        
        this.getChildren().addAll(save,exit,reset,clear); 
        
        addtoGamePane(gamePane);
        
       // t.start();
    }
    
    private void addtoGamePane(GamePane p){
    
        p.bp.setBottom(this); 
        
    }
    
    public void setBackgroundColor(int r, int g, int b){
    
     this.setBackground(new Background(new BackgroundFill(Color.rgb(r, g, b),null,null)));  

    
    }
    
    //inner class
    class ColorTimer extends AnimationTimer {

        double time = 0.0;
        Random rand = new Random();
        
        @Override
        public void handle(long now) {
            
            if(time >= 1) {
            
                int r = rand.nextInt(256);
                int g = rand.nextInt(256);
                int b = rand.nextInt(256); 
                
                setBackgroundColor(r,g,b);
                
                
                
              time = 0.0;
            }
            
            time += .5;
        }
    
    }
}
