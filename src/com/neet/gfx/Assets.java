package com.neet.gfx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Assets {

	public static int width = 82;
	public static int height = 79;
	
	public static BufferedImage[] 	link_up, 
					link_down, 
					link_left, 
					link_right;

	public static BufferedImage[] 	link_attack_up, 
				   	link_attack_down, 
				        link_attack_left, 
				        link_attack_right;
	
	public static BufferedImage 	item_img;
	public static BufferedImage     lifeText;
	
	/**
	 * @description 
	 * 		loads a sprite sheet and crops the image as it stores into BufferedImage array
	 */

	public static void init() throws IOException {

		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));

		// walk sprites
		link_up = new BufferedImage[2];
		link_down = new BufferedImage[2];
		link_left = new BufferedImage[2];
		link_right = new BufferedImage[2];

		// attack sprites
		link_attack_up = new BufferedImage[2];
		link_attack_down = new BufferedImage[2];
		link_attack_left = new BufferedImage[2];
		link_attack_right = new BufferedImage[2];
		
		// bg tiles
		item_img = ImageIO.read(new File("res/textures/items.png"));
		lifeText = ImageIO.read(new File("res/textures/lifeText.png"));

		/* walk sprites */

		// up
		link_up[0] = sheet.crop(width, height, 0, 0);
		link_up[1] = sheet.crop(width, height, width*1, 0);

		// down
		link_down[0] = sheet.crop(width, height, width*4, 0);
		link_down[1] = sheet.crop(width, height, width*0, height*1);

		// left
		link_left[0] = sheet.crop(width, height, width*1, height*1);
		link_left[1] = sheet.crop(width, height, width*2, height*1);

		// right
		link_right[0] = sheet.crop(width, height, width*2, 0);
		link_right[1] = sheet.crop(width, width, width*3, 0);

		/* attack sprites */

		// up
		link_attack_up[0] = sheet.crop(width, height, width*3, height*1);
		link_attack_up[1] = sheet.crop(width, height, width*4, height*1);

		// down
		link_attack_down[0] = sheet.crop(width, height, width*2, height*2);
		link_attack_down[1] = sheet.crop(width, height, width*3, height*2);

		// left
		link_attack_left[0] = sheet.crop(width, height, width*4, height*2);
		link_attack_left[1] = sheet.crop(width, height, 0, height*3);

		// right
		link_attack_right[0] = sheet.crop(width, height, 0, height*2);
		link_attack_right[1] = sheet.crop(width, height, width*1, height*2);
				
	}
}
