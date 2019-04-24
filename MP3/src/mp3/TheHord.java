package mp3;

import java.util.Random;

/**
 *
 * @author jamesostmann
 */
public class TheHord {

    private int speedCounter;
    private double incrementedHord;
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

        incrementedHord = 0.0;
        rand = new Random();
        speedCounter = 0;
        attackingAlien = null;
        shot = false;
        currentSound = 3;
        imageChangeInterval = 0.0;
        spaceX = 2.0;
        spaceY = 40.0;
        currentImageCount = 0;
        aliens = new Alien[5][11];
        direction = Movable.EAST;
        lastDirection = Movable.WEST;
        this.actionPane = actionPane;
        numLiving = 55;
        atEdge = false;
        collumnStates = new int[11];
        initTheHord();

    }

    public TheHord() {

        this(null);
    }


    
    public void initTheHord() {

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
                tempAlien.setY(tempAlien.getViewport().getHeight() + spaceY);
                tempAlien.setAlive(true);
                aliens[y][x] = tempAlien;
                actionPane.getChildren().add(aliens[y][x]);

                spaceX += 45.0;
            }
            spaceX = 0.0;

            spaceY += 30.0;
        }

    }
    public void initTheHordFromTop() {
        
        clearColumnStates();
        actionPane.getCmdCenter().resetProjetile();
        actionPane.getSpaceShip().resetSpaceShip();
        spaceX = 2.0;
        spaceY = 40.0;

        for (int y = 0; y < aliens.length; y++) {
            for (int x = 0; x < aliens[y].length; x++) {

                Alien tempAlien = aliens[y][x];
                tempAlien.setAlive(true); 
                tempAlien.toggleImage(0);
                tempAlien.resetMissile();
                tempAlien.setX(tempAlien.getViewport().getWidth() + spaceX);
                tempAlien.setY(tempAlien.getViewport().getHeight() + spaceY);

                spaceX += 45.0;

            }

            spaceX = 0.0;
            spaceY += 30;

        }

    }
    public boolean hitBottom() {

        for (int y = 0; y < aliens.length; y++) {
            for (int x = 0; x < aliens[y].length; x++) {

                if (aliens[y][x].isAlive() && aliens[y][x].getY() >= 549) {

                    return true;
                }
            }
        }

        return false;
    }
    public Alien getAttackingAlien() {
        return attackingAlien;
    }
    public void resetTheHord() {

        if(spaceY <= 500) {
        
        clearColumnStates();
        numLiving = 55;
        incrementedHord += 40.0;
        spaceX = 2.0;
        spaceY = 40.0 + incrementedHord;

        for (int y = 0; y < aliens.length; y++) {
            for (int x = 0; x < aliens[y].length; x++) {

                Alien tempAlien = aliens[y][x];

                tempAlien.setAlive(true);
                tempAlien.setX(tempAlien.getViewport().getWidth() + spaceX);
                tempAlien.setY(tempAlien.getViewport().getHeight() + spaceY);
                tempAlien.toggleImage(0);

                spaceX += 45.0;
            }

            spaceX = 0.0;
            spaceY += 30.0;
        }
      } else {
       
            actionPane.getGamePane().setGameOver(true); 
        
       }

    }
    public void checkChangeDirection() {

        if (atEdge) {

            if (lastDirection == Movable.EAST) {
                direction = Movable.WEST;
                lastDirection = Movable.SOUTH;

            } else if (this.lastDirection == Movable.WEST) {
                direction = Movable.EAST;
                lastDirection = Movable.SOUTH;

            }

            atEdge = false;
            return;
        }

        for (int y = 0; y < aliens.length; y++) {
            for (int x = 0; x < aliens[y].length; x++) {

                Alien temp = aliens[y][x];

                if (temp.getX() + temp.getViewport().getWidth() >= 545 && temp.isAlive()) {
                    lastDirection = Movable.EAST;
                    direction = Movable.SOUTH;
                    atEdge = true;
                    break;
                }

                if (temp.getX() <= -1 && temp.isAlive()) {

                    lastDirection = Movable.WEST;
                    direction = Movable.SOUTH;
                    atEdge = true;
                    break;
                }

            }
        }

    }
    public boolean AllDestroyed() {

        return numLiving == 0;
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
        speedUp();

        for (int y = 0; y < aliens.length; y++) {
            for (int x = 0; x < aliens[y].length; x++) {

                Alien temp = aliens[y][x];
                temp.setDirection(direction);
                temp.move();

                if (this.imageChangeInterval >= 1) {
                    if (temp.isAlive()) {
                        temp.toggleImage();
                    } else {
                        temp.toggleBlank();
                    }

                }

            }

        }

        if (imageChangeInterval >= 1) {
            playSound();
            imageChangeInterval = 0;
        }

        imageChangeInterval += .03;

        /*    
        if (this.imageChangeInterval >= 1) {

            playSound();
            this.changeHordImages(); 
            this.imageChangeInterval = 0.0;
        }

        this.imageChangeInterval += .03;
         */
    }
    public void setShot(boolean shot) {
        this.shot = shot;
    }
    public boolean isShot() {
        return shot;
    }
    public void fireRandomMissle() {

        if (!shot) {

            int y = rand.nextInt(5);
            int x = rand.nextInt(11);
            int chance = rand.nextInt(10);
            Alien random = aliens[y][x];

            if (chance == 5) {

                if (collumnStates[x] == aliens.length - y - 1) {

                    if (!random.getMissile().isVisible()) {

                        attackingAlien = random;
                        shot = true;
                        attackingAlien.setMissile();

                    }

                }

            }
        }

        if (shot) {

            attackingAlien.fireMissile();

        }

    }
    public void upateNumLiving() {

        numLiving--;

    }
    public int getNumLiving() {
        return numLiving;
    }
    public void speedUp() {
        
        if (speedCounter == 5) {

            for (Alien[] horde : aliens) {
                for (Alien alien : horde) {

                    alien.setSpeed(alien.getSpeed() + 0.0005);

                }

            }
            speedCounter = 0;
        }

        speedCounter++;
    }
    public Alien getAlien(int row, int column) {

        boolean inArraySize = (row > -1 && row < aliens.length) && (column > -1 && column < aliens[row].length);

        if (inArraySize) {

            return aliens[row][column];
        }

        return null;

    }
    public void checkForHitMissile() {

        for (int y = 0; y < actionPane.getHord().getHordSize(); y++) {
            for (int x = 0; x < actionPane.getHord().getHordRowSize(y); x++) {

                Alien tempAlien = actionPane.getHord().getAlien(y, x);
                Missile tempMissile = tempAlien.getMissile();
                CmdCenter cmdCenter = actionPane.getCmdCenter();

                if (tempMissile.isVisible() && tempMissile.getBoundsInParent().intersects(cmdCenter.getBoundsInParent())) {
                    tempAlien.resetMissile();
                    actionPane.getGamePane().getStatusPane().takeAwayLife(cmdCenter);
                    shot = false;
                    
                   

                }

            }
        }

    }
    private void clearColumnStates() {

        for (int i = 0; i < collumnStates.length; i++) {

            collumnStates[i] = 0;
        }
    }
    public int getHordSize() {
        return aliens.length;
    }
    public int getHordRowSize(int y) {
        return aliens[y].length;
    }
    public void updateColumnState(int column) {

        collumnStates[column]++;

    }
    public int getColumnStateValue(int column) {
        return collumnStates[column];
    }
    public void changeHordImages() {

        for (int y = 0; y < aliens.length; y++) {
            for (int x = 0; x < aliens[y].length; x++) {

                Alien temp = aliens[y][x];

                if (temp.isAlive()) {

                    temp.toggleImage(currentImageCount);

                } else {

                    temp.toggleBlank();

                }

            }
        }
        switch (currentImageCount) {

            case 0:
                currentImageCount = 1;
                break;

            case 1:
                currentImageCount = 0;
                break;

        }

    }
}
