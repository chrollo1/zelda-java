package com.neet.gfx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MapLoader {
	
	public static BufferedImage[] mapData;
	
	public static void load() throws IOException {
		
		mapData = new BufferedImage[100];
		
		for (int i = 0; i < 100; i++) {
			mapData[i] = ImageIO.read(new File("res/textures/bg/" + "tile " + "(" + i + ")" + ".png"));
		}
		
		
	}

}
