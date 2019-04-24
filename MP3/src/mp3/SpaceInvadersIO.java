/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author jamesostmann
 */
public class SpaceInvadersIO {
    
    private int highScore;
    private final String fName = "data/data.txt"; 
    //private final File  file = new File(fName);
    
    public void readData() {
        
        try{
        
            Scanner scan = new Scanner(new File(fName)); 
            
            highScore = scan.nextInt(); 
            
            scan.close();
        
        } catch(FileNotFoundException e) {
        
          System.err.println("Couldnt find data file"); 
          System.exit(0);
        
        }
        
    
    }
    public void saveData(){
    
        try{
            
        PrintWriter output = new PrintWriter(new File(fName)); 
        output.print(this); 
        output.close();
        
        
        } catch (IOException e) {
        
            System.err.println("Couldnt find data file for saving");
            System.exit(0);
        }
    
    }
    
    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
    
    public int getHighScore(){
    
        return this.highScore;
    
    }
    
    
    
    @Override
    public String toString(){
        
        return String.valueOf(highScore); 
    }
    
    
    
}
