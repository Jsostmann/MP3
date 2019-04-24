/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author jamesostmann
 */
public class StatusPane extends HBox {
    
    public static StatusPane s;
    private Label scoreValue;
    private Label highScoreValue;
    private Label livesText;
    private Live[] lives;
    private GamePane gamePane;
    
    public StatusPane(GamePane gamePane) {
        
        this.gamePane = gamePane;
        
        s = this;
        
        lives = new Live[3];
        livesText = new Label("Lives");
        
        livesText.setFont(new Font(10));
        livesText.setTextFill(Color.LIME); 
        livesText.setEffect(new DropShadow(5.0,Color.LIMEGREEN)); 
        
        highScoreValue = new Label("High Score: 0");
        highScoreValue.setFont(new Font(10));
        highScoreValue.setTextFill(Color.LIME); 
        highScoreValue.setEffect(new DropShadow(5.0,Color.LIMEGREEN)); 
        
        scoreValue = new Label("Points: 0");
        scoreValue.setFont(new Font(10));
        scoreValue.setTextFill(Color.LIME);  
        scoreValue.setEffect(new DropShadow(5.0,Color.LIMEGREEN));
        
        this.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));  
        
        this.setPadding(new Insets(5.0,0,5.0,0)); 
        this.setSpacing(50.0); 
        this.setAlignment(Pos.CENTER); 
        
        this.setBorder(new Border(new BorderStroke(Color.LIME,BorderStrokeStyle.SOLID, null ,new BorderWidths(0,0,2.0,0)))); 
       
        this.getChildren().addAll(scoreValue,highScoreValue,livesText); 
        
        createLives();
         
        gamePane.setTop(this); 
        
        
        
    }
    
    
    public void resetLives(CmdCenter cmdCenter){
    
        for(int i = 0; i < lives.length; i++) {
        
            lives[i].setVisible(true); 
        
        }
        
        cmdCenter.setNumLives(3);
        int newPoints = cmdCenter.resetPoints();
        updateStatus(cmdCenter,newPoints);
        
        
    }
    
    public void takeAwayLife(CmdCenter cmdCenter){
    
        if(cmdCenter.getNumLives() - 1 > 0) {
        
            cmdCenter.removeLife();
            
            this.lives[this.lives.length - 1  - cmdCenter.getNumLives()].setVisibility(false); 
            
        } else {
           
           gamePane.setGameOver(true); 
        }
        
    }
    
    public void setscoreValueText(String text) {
    
        this.scoreValue.setText(text); 
    
    }
    
    public void setHighScoreValueText(String text) {
        this.highScoreValue.setText(text);
    }
    
    private void createLives(){
    
        for(int i = 0; i < lives.length; i++){
        
            lives[i] = new Live();
            this.getChildren().add(lives[i]);
        }
        
        
    
    }
    
    public static void ResetHighScore() {
        
        resetStatus(s);
    }
    
    public static void resetStatus(StatusPane statusPane){
        
        statusPane.setHighScoreValueText("High Score: " + SpaceInvadersApplication.controller.getHighScore()); 
        statusPane.setscoreValueText("Points: " + 0); 
        
        
    }
    public static void updateStatus(CmdCenter cmdCenter, int points) {
        
        if(cmdCenter.getCmdCenterPoints() > SpaceInvadersApplication.controller.getHighScore()) {
            
           SpaceInvadersApplication.controller.setHighScore(cmdCenter.getCmdCenterPoints()); 
            
            cmdCenter.getActionPane().getGamePane().getStatusPane().setHighScoreValueText("High Score: " + String.valueOf(SpaceInvadersApplication.controller.getHighScore()));
            
            
        
        }
        
        cmdCenter.getActionPane().getGamePane().getStatusPane().setscoreValueText("Points: " + String.valueOf(points));
  
    }
    
}
