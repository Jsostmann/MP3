/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3;

import java.util.Random;

/**
 *
 * @author jamesostmann
 */
public class TheHord {

    private double imageChangeInterval;
    private double spaceX;
    private double spaceY;
    private int currentImageCount;
    private Alien[][] aliens;
    private double direction;
    private double lastDirection;
    private int numLiving;
    private boolean atEdge;
    private ActionPane actionPane;
    private int[] collumnStates;
    private int currentSound;
    private boolean shot;
    private Random rand;
    private Alien attackingAlien;

    public TheHord(ActionPane actionPane) {
        rand = new Random();
        attackingAlien = null;
        shot = false;
        currentSound = 3;
        imageChangeInterval = 0.0;
        this.spaceX = 2.0;
        this.spaceY = 40.0;
        this.currentImageCount = 0;
        this.aliens = new Alien[5][11];
        this.direction = Movable.EAST;
        this.lastDirection = Movable.WEST;
        this.actionPane = actionPane;
        this.numLiving = 55;
        this.atEdge = false;
        this.actionPane = actionPane;
        this.collumnStates = new int[11];
        initTheHord();

    }

    public TheHord() {

        this(null);
    }

    public void initTheHord() {

        double tempSpaceY = this.spaceY;

        for (int y = 0; y < aliens.length; y++) {
            for (int x = 0; x < aliens[y].length; x++) {

                Alien tempAlien;

                if (y == 0) {

                    tempAlien = new Alien(this.actionPane, 2, 0);

                } else if (y <= 2) {

                    tempAlien = new Alien(this.actionPane, 1, 0);

                } else {

                    tempAlien = new Alien(this.actionPane, 0, 0);

                }

                tempAlien.setX(tempAlien.getViewport().getWidth() + spaceX);
                tempAlien.setY(tempAlien.getViewport().getHeight() + tempSpaceY);
                tempAlien.setAlive(true);
                aliens[y][x] = tempAlien;
                this.actionPane.getChildren().add(aliens[y][x]);

                spaceX += 45.0;
            }
            spaceX = 0.0;

            tempSpaceY += 30.0;
        }

    }

    public void initTheHordFromTop() {

        clearColumnStates();
        this.numLiving = 55;

        this.spaceX = 2.0;
        this.spaceY = 40;

        double tempSpaceY = this.spaceY;

        for (int y = 0; y < aliens.length; y++) {
            for (int x = 0; x < aliens[y].length; x++) {

                Alien tempAlien = aliens[y][x];
                tempAlien.toggleImage(0);
                tempAlien.setAlive(true);

                tempAlien.setX(tempAlien.getViewport().getWidth() + spaceX);
                tempAlien.setY(tempAlien.getViewport().getHeight() + tempSpaceY);

                spaceX += 45.0;

            }

            spaceX = 0.0;
            tempSpaceY += 30;

        }

    }

    public boolean hitBottom() {

        for (int y = 0; y < aliens.length; y++) {
            for (int x = 0; x < aliens[y].length; x++) {

                if (aliens[y][x].isAlive() && aliens[y][x].getY() >= 549) {
                    this.spaceY = 40.0;

                    return true;

                }

            }
        }

        return false;
    }

    public void resetTheHord() {

        clearColumnStates();
        this.numLiving = 55;

        this.spaceX = 2.0;
        this.spaceY += 40;

        double tempSpaceY = this.spaceY;

        for (int y = 0; y < aliens.length; y++) {
            for (int x = 0; x < aliens[y].length; x++) {

                Alien tempAlien = aliens[y][x];

                tempAlien.setAlive(true);
                tempAlien.setX(tempAlien.getViewport().getWidth() + spaceX);
                tempAlien.setY(tempAlien.getViewport().getHeight() + tempSpaceY);
                tempAlien.toggleImage();

                spaceX += 45.0;
            }

            spaceX = 0.0;
            tempSpaceY += 30.0;
        }

    }

    public void checkChangeDirection() {

        if (atEdge) {

            if (this.lastDirection == Movable.EAST) {
                this.direction = Movable.WEST;
                this.lastDirection = Movable.SOUTH;

            } else if (this.lastDirection == Movable.WEST) {
                this.direction = Movable.EAST;
                this.lastDirection = Movable.SOUTH;

            }

            atEdge = false;
            return;
        }

        for (int y = 0; y < aliens.length; y++) {
            for (int x = 0; x < aliens[y].length; x++) {

                Alien temp = aliens[y][x];

                if (temp.getX() + temp.getViewport().getWidth() >= 545 && temp.isAlive()) {
                    this.lastDirection = Movable.EAST;
                    this.direction = Movable.SOUTH;
                    atEdge = true;
                    break;
                }

                if (temp.getX() <= -1 && temp.isAlive()) {

                    this.lastDirection = Movable.WEST;
                    this.direction = Movable.SOUTH;
                    atEdge = true;
                    break;
                }

            }
        }

    }

    public boolean AllDestroyed() {

        return this.numLiving == 0;
    }

    public void playSound() {

        currentSound++;

        if (currentSound > 7) {

            currentSound = 4;
        }

        Sound.playSound(currentSound);

    }

    public void move() {

        checkChangeDirection();
        fireRandomMissle();

        for (int y = 0; y < aliens.length; y++) {
            for (int x = 0; x < aliens[y].length; x++) {

                Alien temp = aliens[y][x];
                temp.setDirection(direction);
                temp.move();
             /*  
                if(this.imageChangeInterval >= 1) {
                    if(temp.isAlive()) {
                        temp.toggleImage();
                    } else {
                        temp.toggleBlank();
                    }
                   
                }
                */
            }

        }
      /*  
        if(this.imageChangeInterval >=1) {
            this.imageChangeInterval = 0;
        }
        this.imageChangeInterval += .03;
        
        */

        
        if (this.imageChangeInterval >= 1) {

            // playSound();
            this.changeHordImages(); 
            this.imageChangeInterval = 0.0;
        }

        this.imageChangeInterval += .03;

    }

    public void switchImage(){
    
        
        
    }
    
    public void setShot(boolean shot) {
        this.shot = shot;
    }

    public boolean isShot() {
        return this.shot;
    }

    public void fireRandomMissle() {

        if (!shot) {

            int y = rand.nextInt(5);
            int x = rand.nextInt(11);
            int chance = rand.nextInt(10);
            Alien random = aliens[y][x];
            
            if (chance == 5) {
                
                if (collumnStates[x] == aliens.length - y - 1) {

                    if (!random.getMissle().isVisible()) {

                        attackingAlien = random;
                        shot = true;
                        attackingAlien.setMissle();

                    }

                }

            }
        } 

        if (shot) {

            attackingAlien.fireMissle();

        }

    }

    public void upateNumLiving() {

        this.numLiving--;

    }

    public int getNumLiving() {
        return this.numLiving;
    }

    public void speedUp() {

        for (Alien[] horde : aliens) {
            for (Alien alien : horde) {

                if (alien.getSpeed() < 0) {

                    alien.setSpeed(alien.getSpeed() - 0.05);

                } else {

                    alien.setSpeed(alien.getSpeed() + 0.05);

                }
            }

        }
    }

    public Alien getAlien(int row, int column) {

        boolean inArraySize = (row > -1 && row < aliens.length) && (column > -1 && column < aliens[row].length);

        if (inArraySize) {

            return this.aliens[row][column];
        }

        return null;

    }

    private void clearColumnStates() {

        for (int i = 0; i < this.collumnStates.length; i++) {

            this.collumnStates[i] = 0;
        }
    }

    public int getHordSize() {
        return this.aliens.length;
    }

    public int getHordRowSize(int y) {
        return this.aliens[y].length;
    }

    public void updateColumnState(int column) {

        this.collumnStates[column]++;

    }

    public int getColumnStateValue(int column) {
        return collumnStates[column];
    }
    

         public void changeHordImages() {

        for (int y = 0; y < aliens.length; y++) {
            for (int x = 0; x < aliens[y].length; x++) {

                Alien temp = aliens[y][x];

                if (temp.isAlive()) {

                    temp.toggleImage(this.currentImageCount);

                } else {

                    temp.toggleBlank();

                }

            }
        }
        switch (this.currentImageCount) {

            case 0:
                currentImageCount = 1;
                break;

            case 1:
                currentImageCount = 0;
                break;

        }

    } 
}

