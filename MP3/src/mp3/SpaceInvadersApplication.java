
package mp3;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import scenes.LoadingScreen;

/**
 *
 * @author James Ostmann
 */
public class SpaceInvadersApplication extends Application {

    private static Stage application;
    public static final SpaceInvadersIO controller = new SpaceInvadersIO();
    
    private static Scene loadingScene; 
    private static  Scene gameScene;
    
    @Override
    public void start(Stage application) {
        
        SpaceInvadersApplication.application = application;
        
        controller.readData();
        
        loadingScene = new Scene(new LoadingScreen(),550,700);
        gameScene = new Scene(new GamePane(),550,700);   
        
        
        
       
        application.setOnCloseRequest(new WindowCloseHandler());
        setGameSceneEvents();
         
       
         //printData();
         
         
        application.setTitle("Space Invaders");
        application.centerOnScreen();
        application.setScene(loadingScene); 
        application.setResizable(false);
        application.show();
    }
    
    public static void main(String[] args) {
        
        Application.launch(SpaceInvadersApplication.class);  
    }
    
    public static void SwitchScene(Scene s){
    
        application.setScene(s); 
    
    
    }
    public static Scene getLoadingScene(){
        return loadingScene;
    }
    public static Scene getGameScene(){
        return gameScene;
    }
    public static void printData() {
  
    }
    private void setGameSceneEvents(){
    
        gameScene.setOnKeyPressed(new GameSceneKeyPressHandler());
        gameScene.setOnKeyReleased(new GameSceneKeyReleasedHandler());
    
    }
    
    /*
    
    
   ***************************************** INNER CLASSES **************************
    
    
    */
    
    
    class WindowCloseHandler implements EventHandler<WindowEvent>{

        @Override
        public void handle(WindowEvent event) {
            
            controller.saveData();
            System.exit(0); 
            
        }
    }
    class GameSceneKeyPressHandler implements EventHandler<KeyEvent> {

        @Override
        public void handle(KeyEvent event) {
            
             GamePane gamePane = (GamePane) gameScene.getRoot();
                 
             switch (event.getCode()) {
                 
                    case LEFT:
                        gamePane.getActionPane().getCmdCenter().setDirection(Movable.WEST);
                        break;

                    case RIGHT:
                        gamePane.getActionPane().getCmdCenter().setDirection(Movable.EAST);
                        break;
                }
        }
    
    
    }
    class GameSceneKeyReleasedHandler implements EventHandler<KeyEvent>{

        @Override
        public void handle(KeyEvent event) {
            
            GamePane gamePane = (GamePane)gameScene.getRoot();
            
            switch(event.getCode()){
                
                  case LEFT:
                        gamePane.getActionPane().getCmdCenter().setDirection(Movable.NOWHERE);
                        break;

                    case RIGHT:
                        gamePane.getActionPane().getCmdCenter().setDirection(Movable.NOWHERE);
                        break;

                    case SPACE:
                        gamePane.startGameLoop();
                        gamePane.getActionPane().getCmdCenter().getProjectile().setShot(true); 
                        break;
                        
                    case P: 
                        gamePane.toggleGameLoop();
                        break;
            }
        }
    
    
    }
}
