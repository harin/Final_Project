package worldview;

 import java.applet.*;

import java.net.*;
import javax.swing.JApplet;
import javax.sound.sampled.*;

public class Audioapp extends JApplet{
	//public class Sound{// Holds one audio file
		private Clip song;// Sound player
		 private URL songPath;// Sound path
		AudioClip audioClip;
		Audioapp(String filename){
			try{
				songPath = this.getClass().getClassLoader().getResource(filename);// Get the Sound URL
		        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(songPath);
				song = AudioSystem.getClip();
		        song.open(audioInputStream);
		        song.start();
				}catch(Exception e){
									}// Satisfy the catch
		}
		public void playSound(){
			//audioClip = (AudioClip) song;
			song.loop(Clip.LOOP_CONTINUOUSLY);// Play
		}
		public void stopSound(){
			song.stop();// Stop
		}
		public void playSoundOnce(){
			audioClip = (AudioClip) song;
			audioClip.play();// Play only once
		}
		public void decreaseSound(){
			FloatControl gainControl = (FloatControl) song.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(-10.0f);// Reduce volume by 10 decibels.
		}
		public void increaseSound(){
			FloatControl gainControl = (FloatControl) song.getControl(FloatControl.Type.MASTER_GAIN);
	    		gainControl.setValue(+10.0f);// Reduce volume by 10 decibels.
		}
	}	

	/*public void init(){
		Sound testsong = new Sound("sound/BGSong.wav");
		testsong.playSound();
	}*/
//}




