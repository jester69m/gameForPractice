package main;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound(){

        soundURL[0] = getClass().getResource("/sound/fon-sound.wav");
        soundURL[1] = getClass().getResource("/sound/powerup-sound.wav");
        soundURL[2] = getClass().getResource("/sound/coin-sound.wav");
        soundURL[3] = getClass().getResource("/sound/door-unlocking-sound.wav");
        soundURL[4] = getClass().getResource("/sound/fanfare.wav");
        soundURL[5] = getClass().getResource("/sound/hit-sound.wav");
        soundURL[6] = getClass().getResource("/sound/damage-receive-sound.wav");
    }

    public void setFile(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        } catch(Exception e){}
    }
    public void play(){ clip.start(); }
    public void loop(){ clip.loop(Clip.LOOP_CONTINUOUSLY); }
    public void stop(){ if(clip!=null) clip.stop(); }
}
