package com.neet.gfx;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Map {
	public static BufferedReader br = null;
	public static BufferedImage[] bg_tile = new BufferedImage[176];
	public BufferedImage image;
	public static int temp;
	public static boolean[] walkable = new boolean[176];
	public static Rectangle[] rectangleTiles = new Rectangle[176];
	
	/**
	 * @description:
	 * 		this class reads a comma seperated map .txt file and parses it to an int where the computer finds an .gif with that int 
	 * @param mapX
	 * 		from Game.java for x position in world
	 * @param mapY
	 * 		from Game.java for y position in world
	 */
	
	public void mapLoad(int mapX, int mapY) {		
		try {
			br = new BufferedReader(new FileReader("res/maps/" + mapX + " " + mapY + ".txt"));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < 11; i++) {			
			try {
				String s = br.readLine();
				String[] nums = s.split(",");
				
				for (int j = 0; j < 16; j++) {
					temp = Integer.parseInt(nums[j]);
					
					// cols * y + x can give exact position with respect to its position in the 2D array
					
					if (temp == 0) {
						bg_tile[16 * i + j] = MapLoader.mapData[temp];
					} else {
						bg_tile[16 * i + j] = MapLoader.mapData[temp + 1];
					}
					
					rectangleTiles[16 * i + j] = new Rectangle(j * 32, i * 32 + 128, 32, 32);

					
					// if on ground...
					if(temp == 0) {
						// can walk on that spot
						walkable[16 * i + j] = true;
					}
					else {
						// cannot walk on that spot
						walkable[16 * i + j] = false;
					}
				}
				 
			
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
	}
	
/*	public int getColTile(float x) {
		int col = (int) Math.floor(x / 32);
		System.out.println(col);
		return col;
	}
	
	public int getRowTile(float y) {
		int row = (int) Math.floor(y / 32);
		System.out.println(row);
		return row;
	}

	public boolean isBlocked(float x, float y) {	
		
		int row = (int) Math.floor(x / 32);
		int col = (int) Math.floor(y / 32);
				
		return walkable[16 * col + row];
	}*/		
}
