/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3;

import java.util.Timer;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author jamesostmann
 */
public class GamePane extends Application {
  public long previous = 0;
  private static boolean goNorth, goSouth, goWest, goEast, running;
    
    private ActionPane actionPane;
    private CmdCenter cmdCenter; // add to actionpane
    private SpaceShip spaceShip; // add to actionpane
    private Timer gameTimer;
    private TheHord theHord;

    public GamePane() {
        
        this.actionPane = new ActionPane();
        this.cmdCenter = new CmdCenter(actionPane);
        actionPane.setCenter(cmdCenter);
        this.spaceShip = new SpaceShip();
        actionPane.getChildren().add(spaceShip);
        this.spaceShip.startMovementTimer();
        this.gameTimer = null;
        this.theHord = null;

    }

    public void addGameObject(GameObject gameObject) {
        this.actionPane.getChildren().add(gameObject); 
    }

    public static boolean isgoEast(){
    
        return goEast;
    
    }
    
    public static void setRunning(boolean running) {
     
        GamePane.running = running;
        
    }
    public static boolean isGoWest(){
        return goWest;
    }
    
    public static boolean isRunning() {
        return running;
    }
    @Override
    public void start(Stage stage) throws Exception {

        BorderPane window = new BorderPane();
        window.setCenter(actionPane);

       
        
        
        
        Scene scene = new Scene(window, 550, 600);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
    @Override
    public void handle(WindowEvent t) {
        Platform.exit();
        System.exit(0);
    }
});
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {

                    case LEFT:
                        goWest = true;
                        break;

                    case RIGHT:
                        goEast = true;
                        break;
                        
                    case SPACE:
                      
                        break;

                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {

                    case LEFT:
                        goWest = false;
                        break;

                    case RIGHT:
                        goEast = false;
                        break;
                        
                    case SPACE:
                        
                        cmdCenter.fireProjectile();
                        break;

                }
            }
        });

        AnimationTimer timer = new AnimationTimer() {
            
         
            
            @Override
            public void handle(long now) {
            
                
               
                cmdCenter.move();
            
            
                
            }
        };
        
        timer.start();

        stage.setScene(scene);
        stage.setTitle("Space Invaders");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    
    
    /*
        
    */
    

}
