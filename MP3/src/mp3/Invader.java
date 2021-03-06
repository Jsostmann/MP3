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
public abstract class Invader extends GameObject {

    private int pointValue;

    public Invader() {

        this(0.0, 0.0, 0.0, 0.0, 0);

    }

    public Invader(int pointValue) {

        this(0.0, 0.0, 0.0, 0.0, pointValue);
    }

    public Invader(double direction, double speed, double parentWidth, double parentHeight, int pointValue) {

        super(direction, speed, parentWidth, parentHeight);
        this.pointValue = pointValue;
    }

    public int getPointValue() {
        return pointValue;
    }

    public void setPointValue(int pointValue) {
        this.pointValue = pointValue;
    }

}
