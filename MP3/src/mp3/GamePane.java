/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.BorderPane;


/**
 *
 * @author jamesostmann
 */
public class GamePane extends BorderPane {

  
    private boolean gameOver;
    private StatusPane statusPane;
    private ControlPane controlPane;
    private ActionPane actionPane;
    private GameLoopTimer gameLoopTimer;
    private boolean started;

    public GamePane() {
        
        gameOver = false;
        statusPane = new StatusPane(this);
        controlPane = new ControlPane(this);
        actionPane = new ActionPane(this);

        this.started = false;
        this.gameLoopTimer = new GameLoopTimer();
      
        setInitialHighScore();
       

    }

    public void addGameObject(GameObject gameObject) {
        this.actionPane.getChildren().add(gameObject);
    }

    public  void setInitialHighScore(){
        System.out.println(SpaceInvadersApplication.controller.getHighScore());
        this.statusPane.setHighScoreValueText("High Score: " + SpaceInvadersApplication.controller.getHighScore());
    }
    
    public void toggleGameLoop() {

        if (started) {
            stopGameLoop();
        } else {
            startGameLoop();
        }

    }

    public void stopGameLoop() {

        if (started) {
            gameLoopTimer.stop();
            started = false;
        }
    }

    
   
    
    public void startGameLoop() {

        if (!started) {
           
            gameLoopTimer.start();
            started = true;

        }
    }

    public StatusPane getStatusPane() {
        return this.statusPane;
    }

    public ActionPane getActionPane() {
        return this.actionPane;
    }

    public ControlPane getControlPane() {
        return this.controlPane;
    }

    public boolean getGameStatus() {
        return gameOver;
    }
    
    public void setGameOver(boolean gameOver) {
        
        this.gameOver = gameOver;
    }
    class GameLoopTimer extends AnimationTimer {

        @Override
        public void handle(long now) {
            
          if(!gameOver) { 
            moveTheHord();

            moveSpaceShip();

            moveCmdCenter();

            moveProjectile();

        } else {
          
              resetGame();
              
        }
        }

    }
    
    private void resetGame() {
        
       this.stopGameLoop();
       actionPane.getHord().initTheHordFromTop();
       actionPane.getSpaceShip().resetSpaceShip();
       statusPane.resetLives(actionPane.getCmdCenter()); 
       gameOver = false;
        
    }

    private void moveProjectile() {

        CmdCenter c = actionPane.getCmdCenter();

        if (c.getProjectile().isShot()) {
            c.fireProjectile();
        }

    }

    private void moveSpaceShip() {

        actionPane.getSpaceShip().launch();

    }

    private void moveCmdCenter() {

        actionPane.getCmdCenter().move();
    }

    private void moveTheHord() {

        
        if (actionPane.getHord().hitBottom()) {
            
        actionPane.getGamePane().getStatusPane().takeAwayLife(actionPane.getCmdCenter()); 
                    
        if(actionPane.getCmdCenter().getNumLives() == 0) {
                        
                        actionPane.getGamePane().setGameOver(true); 
                    }
           actionPane.getHord().initTheHordFromTop();
           actionPane.getSpaceShip().resetSpaceShip();      


        } else if (actionPane.getHord().AllDestroyed()) {

            actionPane.getHord().resetTheHord();

        } else {

            actionPane.getHord().move();

        }

    }
}
