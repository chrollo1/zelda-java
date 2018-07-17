package com.neet.gfx;

import java.awt.Color;
import java.awt.Graphics;

import com.neet.main.Game;

import sun.applet.Main;

public class Minimap {

	private final int scale = Game.SCALE;
	private final int miniWidth  = 64 * scale;
	private final int miniHeight = 32 * scale;
	
	/**
	 * @param g
	 * 		Graphics from Game.java
	 * @param worldX
	 * 		x position in world
	 * @param worldY
	 * 		y position in world
	 * @param xx
	 * 		x variable that increments per tick to get alpha of blip
	 */
	
	public void paint(Graphics g, int worldX, int worldY, int xx) {
		
		// bg of map
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 256 * scale, 128);
		g.setColor(new Color(116, 116, 116));
		g.fillRect(16 * scale, 16 * scale + 8, miniWidth, miniHeight);
		
		// init var
		int alpha = 0;
		
		// blip on map
		alpha = (int) (127.5 * Math.cos(1.0/16.0 * xx) + 127.5);
		
		g.setColor(new Color(184, 248, 24, alpha));	
		
		// temp (will hold worldX, worldY with position on grid)
		g.fillRect(32 * scale + 3 * 4 * worldX + 16, 32 * scale + 8 + 4 * worldY, 3 * scale, 3 * scale);
	}
	
}
