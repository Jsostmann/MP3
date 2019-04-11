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
public interface Movable {
    
    public static final double EAST = 0.0;
    public static final double WEST = 180.0;
    public static final double NORTH = 270.0;
    public static final double SOUTH = 90.0;
    
    public abstract void move();
    
}
