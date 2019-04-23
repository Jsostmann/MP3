/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author jamesostmann
 */
public class Sound {

    private static final String PROJECTILE_SOUND = "res/shoot.wav";
    private static final String ALIEN_HIT_SOUND = "res/invaderkilled.wav";
    private static final String CMD_CENTER_HIT = "res/explosion.wav";
    private static final String SPACESHIP = "res/spaceShip.wav";
    private static final String MOVE = "res/alienMove.wav";
    private static final String MOVE2 = "res/alienMove2.wav";
    private static final String MOVE3 = "res/alienMove3.wav"; 
    private static final String MOVE4 = "res/alienMove4.wav";
    private static Media mediaSound;
    private static MediaPlayer mp;

    public static void playSound(int sound) {

        switch (sound) {
            case 0:
                mediaSound = new Media(new File(PROJECTILE_SOUND).toURI().toString());
                break;
            case 1:
                mediaSound = new Media(new File(ALIEN_HIT_SOUND).toURI().toString());
                break;
            case 2:
                mediaSound = new Media(new File(CMD_CENTER_HIT).toURI().toString());
                break;
            case 3:
                mediaSound = new Media(new File(SPACESHIP).toURI().toString());
                break;
            case 4:
                mediaSound = new Media(new File(MOVE).toURI().toString());
                break;
            case 5:
                mediaSound = new Media(new File(MOVE2).toURI().toString());
                break;
            case 6:
                mediaSound = new Media(new File(MOVE3).toURI().toString());
                break;
            case 7:
                mediaSound = new Media(new File(MOVE4).toURI().toString());
                break;

        }

        mp = new MediaPlayer(mediaSound);
        mp.play();
    }

}
