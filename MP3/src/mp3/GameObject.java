/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3;

import javafx.scene.image.ImageView;

/**
 *
 * @author jamesostmann
 */
public abstract class GameObject extends ImageView implements Movable {
    
    private double direction;
    private double speed;
    private double parentWidth;
    private double parentHeight;
    
    public GameObject() {
    
        
        this(0.0,0.0,0.0,0.0);
        
    }
    
    public GameObject(double direction, double speed, double parentWidth, double parentHeight) {
        
        
        this.direction = direction;
        this.speed = speed;
        this.parentWidth = parentWidth;
        this.parentHeight = parentHeight;
    }

    public double getDirection() {
        return direction;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getParentWidth() {
        return parentWidth;
    }

    public void setParentWidth(double parentWidth) {
        this.parentWidth = parentWidth;
        
    }

    public double getParentHeight() {
        return parentHeight;
    }

    public void setParentHeight(double parentHeight) {
        this.parentHeight = parentHeight;
    }
    
    
    
    
}
