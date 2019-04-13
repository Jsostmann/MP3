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
private static Media mediaSound;
private static MediaPlayer mp;


public static void playSound(int sound) {

    switch(sound){
        case 0:
           mediaSound = new Media(new File(PROJECTILE_SOUND).toURI().toString());
           break;
        case 1:
           mediaSound = new Media(new File(ALIEN_HIT_SOUND).toURI().toString());
           break;
        case 2:
           mediaSound = new Media(new File(CMD_CENTER_HIT).toURI().toString());
           break;
           
    }
    
   mp = new MediaPlayer(mediaSound);
   mp.play();

}


}
