/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3;

import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 *
 * @author jamesostmann
 */
public class ControlPane extends HBox {
    
    private AnimationTimer t = new ColorTimer();
    private ButtonHandler buttonHandler;
    private Button save;
    private Button exit;
    private Button reset;
    private Button clear;
    private BackButton backButton;
    private GamePane gamePane;
    
    public ControlPane(GamePane gamePane) {
        
        buttonHandler = new ButtonHandler();
        
        //backButton = new BackButton();
        
        save = new Button("Save Game");
        save.setFocusTraversable(false);
        save.setOnAction(buttonHandler);
        
        exit = new Button("Exit Game");
        exit.setFocusTraversable(false);
        exit.setOnAction(buttonHandler);
        
        reset = new Button("Reset High Score");
        reset.setFocusTraversable(false);
        reset.setOnAction(buttonHandler);
        
     
        this.setAlignment(Pos.CENTER); 
        this.setSpacing(15.0); 
        this.setPadding(new Insets(5,0,5,0)); 
        this.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));  
        this.setBorder(new Border(new BorderStroke(Color.LIME,BorderStrokeStyle.SOLID, null ,new BorderWidths(2.0,0,0,0)))); 


        
        this.gamePane = gamePane;
        
        this.getChildren().addAll(save,exit,reset); 
        
        this.gamePane.setBottom(this); 
       // t.start();
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
    class ButtonHandler implements EventHandler<ActionEvent> { 

       
        
        @Override
        public void handle(ActionEvent event) {
            
            Button button = (Button) event.getSource();
            
            if(button == save) {
            
               SpaceInvadersApplication.controller.saveData();
            
            } else if(button == exit) {
            
                System.exit(0); 
            
            } else if(button == reset) {
            
                gamePane.stopGameLoop(); 
                gamePane.getActionPane().getHord().initTheHordFromTop();
                gamePane.getStatusPane().resetLives(gamePane.getActionPane().getCmdCenter()); 
                StatusPane.resetStatus(gamePane.getStatusPane()); 
                
                
            
            } 
            
        }
    
    }
}
