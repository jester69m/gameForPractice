package main;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[30];
    FloatControl fc;
    int volumeScale = 3;
    float volume;


    public Sound(){

        soundURL[0] = getClass().getResource("/sound/fon-sound.wav");
        soundURL[1] = getClass().getResource("/sound/powerup-sound.wav");
        soundURL[2] = getClass().getResource("/sound/coin-sound.wav");
        soundURL[3] = getClass().getResource("/sound/door-unlocking-sound.wav");
        soundURL[4] = getClass().getResource("/sound/fanfare.wav");
        soundURL[5] = getClass().getResource("/sound/hit-sound.wav");
        soundURL[6] = getClass().getResource("/sound/damage-receive-sound.wav");
        soundURL[7] = getClass().getResource("/sound/burning.wav");
        soundURL[8] = getClass().getResource("/sound/cuttree.wav");
        soundURL[9] = getClass().getResource("/sound/gameover-sound.wav");
    }

    public void setFile(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip(     );
            clip.open(ais);
            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();

        } catch(Exception e){}
    }
    public void play(){ clip.start(); }
    public void loop(){ clip.loop(Clip.LOOP_CONTINUOUSLY); }
    public void stop(){ if(clip!=null) clip.stop(); }

    public void checkVolume(){
        switch (volumeScale){
            case 0: volume = -80f; break;
            case 1: volume = -20f; break;
            case 2: volume = -12f; break;
            case 3: volume = -5f; break;
            case 4: volume = 1f; break;
            case 5: volume = 6f; break;
        }
        fc.setValue(volume);
    }
}
