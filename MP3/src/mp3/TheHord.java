/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3;

/**
 *
 * @author jamesostmann
 */
public class TheHord {

    private double spaceX;
    private double spaceY;
    private int currentImageCount;
    private boolean right, left;
    private Alien[][] aliens;
    private double direction;
    private double lastDirection;
    private int numLiving;
    private boolean atEdge;
    private ActionPane actionPane;
    private int[] collumnStates;

    public TheHord(ActionPane actionPane) {
        this.spaceX = 0.0;
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

        this.spaceY = 40.0;

        double tempSpaceY = this.spaceY;

        this.spaceX = 0.0;

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
            spaceX = 0;

            tempSpaceY += 30.0;
        }

    }

    public void initTheHordFromTop() {

        clearColumnStates();
        this.numLiving = 55;

        this.spaceX = 0.0;
        this.spaceY = 40;

        double tempSpaceY = this.spaceY;

        for (int y = 0; y < aliens.length; y++) {
            for (int x = 0; x < aliens[y].length; x++) {

                Alien tempAlien = aliens[y][x];

                tempAlien.setSpeed(.5);
                tempAlien.setAlive(true);
                tempAlien.setX(tempAlien.getViewport().getWidth() + spaceX);
                tempAlien.setY(tempAlien.getViewport().getHeight() + tempSpaceY);

                aliens[y][x] = tempAlien;
                spaceX += 45.0;

            }

            spaceX = 0;
            tempSpaceY += 30;

        }

    }

    public boolean hitBottom() {

        for (int y = 0; y < aliens.length; y++) {
            for (int x = 0; x < aliens[y].length; x++) {

                if (aliens[y][x].isAlive() && aliens[y][x].getY() > 549) {

                    return true;

                }

            }
        }

        return false;
    }

    public void resetTheHord() {

        clearColumnStates();
        this.numLiving = 55;

        this.spaceX = 0.0;
        this.spaceY += 40;

        double tempSpaceY = this.spaceY;

        for (int y = 0; y < aliens.length; y++) {
            for (int x = 0; x < aliens[y].length; x++) {

                Alien tempAlien = aliens[y][x];
                tempAlien.setSpeed(.5);
                tempAlien.setAlive(true);
                tempAlien.setX(tempAlien.getViewport().getWidth() + spaceX);
                tempAlien.setY(tempAlien.getViewport().getHeight() + tempSpaceY);
                tempAlien.toggleImage(0); 
                aliens[y][x] = tempAlien;

                spaceX += 45.0;
            }

            spaceX = 0;
            tempSpaceY += 30.0;
        }

    }

    public void changeDirection() {

        /*
        if x >= 545 
        set prev to east
        set dir to south
      
        if x<= 0 
        set prev to west
        set dir to south
      
         */
 /*
      if east and prev is south 
        set prev to east
        set dir to south
        
      
      if west and prev is south 
        set prev to west
        set dir to south
      
      if south and prev is east
        set prev to south
        set dir to west
      
      if south and prev is west 
        set prev to south
        set dir to east
        
         */
    }

    public boolean AllDestroyed() {

        return this.numLiving == 0;
    }

    public void checkMove() {

        for (Alien[] alienHorde : aliens) {
            for (Alien alien : alienHorde) {

                if (alien.getX() <= 0 && alien.isAlive()) {
                    left = true;
                    right = false;

                }

                if (alien.getX() + alien.getViewport().getWidth() >= 545 && alien.isAlive()) {

                    right = true;
                    left = false;

                }

            }
        }

    }

    public void move() {

        checkMove();

        for (Alien alienHorde[] : aliens) {
            for (Alien alien : alienHorde) {

                if (left || right) {

                    alien.setSpeed(-alien.getSpeed());
                    alien.setY(alien.getY() + 5.0);
                }

                //alien.setX(alien.getX() + alien.getSpeed() * Math.cos(Math.toRadians(alien.getDirection())));
                alien.move();
            }
        }

        right = false;
        left = false;

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

                    alien.setSpeed(alien.getSpeed() - 0.005);
                } else {

                    alien.setSpeed(alien.getSpeed() + 0.005);

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
