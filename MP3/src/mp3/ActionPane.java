/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3;


import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author jamesostmann
 */
public class ActionPane extends Pane {
    private CmdCenter center;
    public Alien[][] horde = new Alien[5][11];
    public int[] collumnStates = new int[11];
    private Animation a;

    private void makeAliens() {

       

        double spaceX = 0.0;
        double spaceY = 0.0;

        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 11; x++) {

                Alien temp;

                if (y <= 2) {

                    temp = new Alien(this, 0, 0);

                } else if (y == 3) {

                    temp = new Alien(this, 1, 0);

                } else {

                    temp = new Alien(this, 2, 0);

                }

                temp.setX(temp.getViewport().getWidth() + spaceX);
                temp.setY(temp.getViewport().getHeight() + spaceY);
                this.getChildren().add(temp);
                horde[y][x] = temp;

                spaceX += 45.0;
            }
            spaceX = 0;
            spaceY += 30.0;
        }

    }

    public ActionPane() {
        
        makeAliens();
        this.setHeight(600.0);
        this.setWidth(550.0);
        this.setBackgroundImage();
        a = new Animation();
        a.start();

    }
    
    public void setCenter(CmdCenter center) {
        this.center = center;
    }

    private void setBackgroundImage() {

        Background background = new Background(new BackgroundFill(Color.BLACK, null, null));
        this.setBackground(background);

    }

    class Animation extends AnimationTimer {

        int count = 0;
        double timerCount = 0.0;
        boolean right = false;
        boolean down = false;
        boolean left = true;
        boolean dead = false;

        @Override
        public void handle(long now) {
            if (timerCount >= 1) {

                if (count == 0) {

                    for (Alien tempArray[] : horde) {
                        for (Alien temp : tempArray) {

                            if (temp.visible) {
                                temp.toggleImage(count);
                            }
                            if (!temp.visible) {
                                temp.toggleBlank();
                            }

                        }

                    }

                    count = 1;
                } else {
                    for (Alien tempArray[] : horde) {
                        for (Alien temp : tempArray) {
                            if (temp.visible) {
                                temp.toggleImage(count);
                            }

                            if (!temp.visible) {
                                temp.toggleBlank();
                            }

                        }

                    }

                    count = 0;
                }

                timerCount = 0;
            }

            for (Alien[] temp : horde) {
                for (Alien t : temp) {
                    if (t != null) {

                        if (t.getX() + t.getViewport().getWidth() >= 545 && t.visible) {

                            right = true;
                            left = false;
                            down = true;

                        }

                        if (t.getX() <= 0 && t.visible) {

                            left = true;
                            right = false;
                            down = true;

                        }
                        
                        if(t.getBoundsInParent().intersects(center.getBoundsInParent()) && t.visible) {
                            dead = true;
                           // System.exit(0);
                        }

                    }
                }
            }

            for (Alien[] temp : horde) {
                for (Alien t : temp) {
                    if (t != null) {

                        if (right) {

                            t.setX(t.getX() - 2.0);

                        }

                        if (left) {

                            t.setX(t.getX() + 2.0);

                        }

                        if (down) {
                            t.setY(t.getY() + 2.0);
                        }
                        
                        if(dead) {
                            Sound.playSound(2);
                            stop();
                            // resetHorde();
                        
                        }

                    }

                }

            }

            down = false;

            timerCount += .03;

        }
    }
}
