/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *
 * @author jamesostmann
 */
public class ActionPane extends Pane {

    private TheHord horde;
    private CmdCenter cmdCenter;
    private SpaceShip spaceShip;
    private GamePane gamePane;
   



    public ActionPane(GamePane gamePane) {
            
        spaceShip = new SpaceShip(this);
        horde = new TheHord(this);
        cmdCenter = new CmdCenter(this); 
        this.gamePane = gamePane;
        this.setHeight(600.0);
        this.setWidth(550.0);
        this.setBackgroundImage();
       
      

    }
    public GamePane getGamePane() {
    
        return this.gamePane;
    }
    public CmdCenter getCmdCenter() {
        return this.cmdCenter;
    }
    public void setCmdCenter(CmdCenter cmdCenter) {
        this.cmdCenter = cmdCenter;
    }
    public TheHord getHord() {
        return this.horde;
    }
    public SpaceShip getSpaceShip(){
        return this.spaceShip;
    }
    private void setBackgroundImage() {

        Background background = new Background(new BackgroundFill(Color.BLACK, null, null));
        this.setBackground(background);

    }

   
}
