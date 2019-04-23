/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
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
    
    private Label scoreValue;
    private Label highScoreValue;
    
    public StatusPane(GamePane gamePane) {
        
        highScoreValue = new Label("High Score: 0");
        highScoreValue.setFont(new Font(25));
        highScoreValue.setTextFill(Color.RED); 
        
        scoreValue = new Label("Points: 0");
        scoreValue.setFont(new Font(25));
        scoreValue.setTextFill(Color.RED);  
        
        this.setBorder(new Border(new BorderStroke(Color.GREEN,BorderStrokeStyle.SOLID,null,new BorderWidths(0,0,2,0),null))); 
        this.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));  
        
        this.setSpacing(50.0); 
        this.setAlignment(Pos.CENTER); 
        
        this.getChildren().addAll(scoreValue,highScoreValue); 
        
       
        gamePane.bp.setTop(this);  
        
        
    }
    
    public void setscoreValueText(String text) {
    
        this.scoreValue.setText(text); 
    
    }
    
    public void setHighScoreValueText(String text) {
        this.highScoreValue.setText(text);
    }
    
    
    public static void updateStatus(CmdCenter cmdCenter, int points) {
        
        if(cmdCenter.getCmdCenterPoints() > GamePane.controller.getHighScore()) {
            
            GamePane.controller.setHighScore(cmdCenter.getCmdCenterPoints()); 
            
            cmdCenter.getActionPane().getGamePane().getStatusPane().setHighScoreValueText("High Score: " + String.valueOf(GamePane.controller.getHighScore()));
            
            
        
        }
        
        cmdCenter.getActionPane().getGamePane().getStatusPane().setscoreValueText("Points: " + String.valueOf(points));
  
    }
    
}
