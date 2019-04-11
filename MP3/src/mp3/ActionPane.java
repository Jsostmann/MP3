/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author jamesostmann
 */
public class ActionPane extends Pane {

    public Alien[][] horde = new Alien[5][11];
    private ArrayList<Rectangle> recs;
    private Animation a;

    private void makeAliens() {

        double width = (550.0 / 5.0) - 10.0;
        double height = (600 / 11.0) - 10.0;

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

    private void init() {
        int j = 0;
        for (int i = 1; i < 14; i++) {

            Rectangle r = new Rectangle();

            r = new Rectangle();
            r.setX(i * 10 + j);
            r.setY(30);
            r.setWidth((550.0 / 15.0));
            r.setHeight(50);
            r.setFill(Color.ORANGE);
            this.getChildren().add(r);
            recs.add(r);

            j += 30;
        }
        j = 0;
        for (int i = 1; i < 14; i++) {

            Rectangle r = new Rectangle();

            r = new Rectangle();
            r.setX(i * 10 + j);
            r.setY(90);
            r.setWidth((550.0 / 15.0));
            r.setHeight(50);
            r.setFill(Color.ORANGE);
            this.getChildren().add(r);
            recs.add(r);

            j += 30;
        }
    }

    public ActionPane() {
        makeAliens();
        recs = new ArrayList<>();
        //  init();
        this.setHeight(600.0);
        this.setWidth(550.0);
        this.setBackgroundImage();

        a = new Animation();
        a.start();

    }

    public ArrayList<Rectangle> getR() {

        return this.recs;

    }

    public int recSize() {
        return recs.size();
    }

    public Rectangle getRec(int i) {
        return recs.get(i);
    }

    public void removeRec(int i) {
        recs.remove(recs.get(i));
    }

    private void setBackgroundImage() {
        try {

            FileInputStream input = new FileInputStream("res/bg.jpg");
            Image image = new Image(input);
            BackgroundImage backgroundimage = new BackgroundImage(image,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);

            Background background = new Background(backgroundimage);
            this.setBackground(background);

        } catch (FileNotFoundException e) {

            System.err.println("Couldnt Find Image For Background");
            System.exit(-1);

        }
    }

    class Animation extends AnimationTimer {

        int count = 0;
        double timerCount = 0.0;
        boolean right,left = false;

        @Override
        public void handle(long now) {
            /*  
        for(Rectangle rec: recs) {
            
            rec.setTranslateX(rec.getTranslateX() + 1.0);
            
        }
             */
            /// before animation , for all aliens if alien is off screen set dir 
            // then move all aliens
            if (timerCount >= 1) {

                if (count == 0) {

                    for (Alien tempArray[] : horde) {
                        for (Alien temp : tempArray) {

                            if (temp != null) {
                                temp.setCurrentImage(count);
                            }

                        }

                    }

                    count = 1;
                } else {
                    for (Alien tempArray[] : horde) {
                        for (Alien temp : tempArray) {
                            if (temp != null) {
                                temp.setCurrentImage(count);
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

                        if (t.getX() + t.getViewport().getWidth() >= 545) {
                            right = true;
                            left = false;

                        }
                        
                        if(t.getX() <= 0) {
                            left = true;
                            right = false;
                        }
                    }
                }
            }

          
                for (Alien[] temp : horde) {
                    for (Alien t : temp) {
                        if (t != null) {
                            
                            if(right){
                            
                                t.setX(t.getX() - 1);
                            } 
                            
                            if(left) {
                            
                                t.setX(t.getX() +1); 
                            
                            } 
                            if(!left && !right) {
                                 t.setX(t.getX() +1); 
                            }
                        }

                    }

                }
            

            timerCount += .03;

        }
    }
}
