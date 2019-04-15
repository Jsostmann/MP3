/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3;

import javafx.scene.image.Image;

/**
 *
 * @author jamesostmann
 */
public class TheHord {
    
    private Alien[][] aliens;
    private double direction;
    private double lastDirection;
    private int numLiving;
    private boolean atEdge;
    private ActionPane actionPane;
    private Image alienSprites;
    private int[] collumnStates;
    
    public TheHord(ActionPane actionPane) {
        
       this.aliens = new Alien[5][11];
       this.direction = Movable.EAST;
       this.lastDirection = Movable.WEST;
       this.actionPane = actionPane;
       this.numLiving = 55;
       this.atEdge = false;
       this.actionPane = actionPane;
       this.alienSprites = null;
       this.collumnStates = new int[11];
    }
    
    public TheHord() {
        
         this(null);
    }
    
    public void initTheHord() {
        
      double spaceX = 0.0;
      double spaceY = 40.0;

        for (int y = 0; y < aliens.length; y++) {
            for (int x = 0; x < aliens[y].length; x++) {

                Alien tempAlien = null;

                if (y <= 2) {

                    tempAlien = new Alien(this.actionPane, 0, 0);

                } else if (y == 3) {

                    tempAlien = new Alien(this.actionPane, 1, 0);

                } else {

                    tempAlien = new Alien(this.actionPane, 2, 0);

                }

                tempAlien.setX(tempAlien.getViewport().getWidth() + spaceX);
                tempAlien.setY(tempAlien.getViewport().getHeight() + spaceY);
                this.actionPane.getChildren().add(tempAlien);
                aliens[y][x] = tempAlien;

                spaceX += 45.0;
            }
            spaceX = 0;
            spaceY += 30.0;
        }
        
        
    }
    public void resetTheHord() {
    
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
    public Alien getAlien(int row, int column) {
       boolean inArraySize = (row > -1 && row < aliens.length - 1) && (column > -1 && column < aliens[row].length -1);
        
       if(inArraySize) {
           return this.aliens[row][column];
       }
       
       return null;
    
    }
    
}
