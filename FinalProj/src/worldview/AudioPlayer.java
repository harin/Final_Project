package worldview;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;
   
// To play sound using Clip, the process need to be alive.
// Hence, we use a Swing application.
public class AudioPlayer {  
   // Constructor
   Clip clip;


    
   public AudioPlayer(String filename) {
         try {
        // Open an audio input stream.
        URL url = this.getClass().getClassLoader().getResource(filename);
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
        // Get a sound clip resource.
        clip = AudioSystem.getClip();         
        // Open audio clip and load samples from the audio input stream.
         clip.open(audioIn);
         //clip.start();//
      } catch (UnsupportedAudioFileException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (LineUnavailableException e) {
         e.printStackTrace();
      }
   }
   
   public void playLoop(){
	clip.loop(Clip.LOOP_CONTINUOUSLY);
   }
   
   public void playOnce(){
	   clip.start();
   }
   
   public void stop(){
	   clip.stop();
   }
   
   public void increase(){
	   FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(+8.0f);// Reduce volume by 10 decibels.
   }
   
   public void decrease(){
	   FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(-8.0f);// Reduce volume by 10 decibels.
   }
   
   

}