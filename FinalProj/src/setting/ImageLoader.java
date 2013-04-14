package setting;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class ImageLoader {
	
	public static BufferedImage loadImageFromLocal(String path){
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}	
	return image;
	}
	
	public static BufferedImage GetImageFromCloud(String url){
		BufferedImage image = null;
		try {
			image = ImageIO.read(new URL(url));
		} catch (IOException e) {
			e.printStackTrace();
		}	
	return image;
	}
}