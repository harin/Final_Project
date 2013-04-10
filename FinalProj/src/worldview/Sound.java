package worldview;

import java.applet.AudioClip;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.*;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;



//import AudioappVA.Sound;

public class Sound // Holds one audio file
{
 Clip clip;

 Sound(String filename)
 {         
         try {
          URL url = this.getClass().getClassLoader().getResource(filename);
          AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
    clip = AudioSystem.getClip();
          clip.open(audioInputStream);
          clip.start();
   } catch (LineUnavailableException e) {
    e.printStackTrace();
   } catch (UnsupportedAudioFileException e) {
    e.printStackTrace();
   } catch (IOException e) {
    e.printStackTrace();
   }
 }

 public void playSound()
 {
  //AudioClip audioClip = (AudioClip) clip;
  clip.loop(Clip.LOOP_CONTINUOUSLY); // Play continuously
 }
 public void stopSound()
 {
  clip.stop(); // Stop
 }
 public void playSoundOnce()
 {
  AudioClip audioClip = (AudioClip) clip;
  audioClip.play(); // Play only once
 }
 public void decrease()//decrease volume
 {
     FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
     gainControl.setValue(10.0f); // Reduce volume by 10 decibels.
 }
 public void increase()//increase volume
 {
     FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
     gainControl.setValue(-10.0f); // Reduce volume by 10 decibels.
 }
 
 public void init(){
  Sound testsong;
    testsong =new Sound("sound/BGSong.wav");
  testsong.playSound();
  
 }
}