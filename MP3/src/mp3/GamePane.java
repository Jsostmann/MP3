/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import scenes.LoadingScreen;

/**
 *
 * @author jamesostmann
 */
public class GamePane extends Application {

    private LoadingScreen ld;
    public static SpaceInvadersIO controller = new SpaceInvadersIO();
    public BorderPane bp;
    private boolean started;
    private StatusPane statusPane;
    private ControlPane controlPane;
    private ActionPane actionPane;
    public Scene s2;
    private GameLoopTimer gameLoopTimer;

    public GamePane() {

        ld = new LoadingScreen();
        bp = new BorderPane();
        
        
        statusPane = new StatusPane(this);
        controlPane = new ControlPane(this);
        actionPane = new ActionPane(this);

        this.started = false;
        this.gameLoopTimer = new GameLoopTimer();

    }

    public void addGameObject(GameObject gameObject) {
        this.actionPane.getChildren().add(gameObject);
    }

    private void stopGameLoop() {
        if (started) {
            gameLoopTimer.stop();
            started = false;
        }
    }

    private void startGameLoop() {
        if (!started) {

            gameLoopTimer.start();
            started = true;

        }
    }

    public StatusPane getStatusPane() {
        return this.statusPane;
    }

    @Override
    public void start(Stage stage) {
        controller.readData();
        statusPane.setHighScoreValueText("High Score: " + String.valueOf(controller.getHighScore()));
        bp.setCenter(this.actionPane);

        Scene scene = new Scene(bp, 550, 700);

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                controller.saveData();
                Platform.exit();
                System.exit(0);
            }
        });
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {

                    case LEFT:
                        actionPane.getCmdCenter().setDirection(Movable.WEST);
                        break;

                    case RIGHT:
                        actionPane.getCmdCenter().setDirection(Movable.EAST);
                        break;

                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {

                    case LEFT:
                        actionPane.getCmdCenter().setDirection(Movable.NOWHERE);
                        break;

                    case RIGHT:
                        actionPane.getCmdCenter().setDirection(Movable.NOWHERE);
                        break;

                    case SPACE:
                        startGameLoop();
                        actionPane.getCmdCenter().getProjectile().setShot(true);

                        break;

                    case P:
                        stopGameLoop();
                        break;
                }
            }
        });

        stage.setScene(scene);
        stage.setTitle("Space Invaders");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    class GameLoopTimer extends AnimationTimer {

     
        @Override
        public void handle(long now) {

            moveTheHord();

            moveSpaceShip();

            moveCmdCenter();

            moveProjectile();

        }

        

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

            actionPane.getHord().initTheHordFromTop();

        } else if (actionPane.getHord().AllDestroyed()) {

            actionPane.getHord().resetTheHord();

        } else {

            actionPane.getHord().move();

        }

    }
}
