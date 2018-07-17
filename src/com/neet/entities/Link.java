package com.neet.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.neet.gfx.Animation;
import com.neet.gfx.Assets;
import com.neet.gfx.Map;
import com.neet.main.Game;

public class Link extends Creature {

	private int velX, velY;
	private Game game;
	
	private String dir = "d";
	
	// movement animations
	private Animation upAnimation, downAnimation, leftAnimation, rightAnimation;
	
	private Map map = new Map();
	
	public Link(Game game, float x, float y) {
		super(x, y);
		// initialise game in constuctor to access vars
		this.game = game;

		upAnimation = new Animation(100, Assets.link_up);
		downAnimation = new Animation(100, Assets.link_down);
		leftAnimation = new Animation(100, Assets.link_left);
		rightAnimation = new Animation(100, Assets.link_right);
		
	}
	
	@Override
	public void tick() {
				
		// if moving...
		if (velX != 0 || velY != 0) {
			// update animations
			upAnimation.tick();
			downAnimation.tick();
			leftAnimation.tick();
			rightAnimation.tick();
		}
		
		// movement
//		velX = 0;
//		velY = 0;

		System.out.println(x + " " + y);
		
		// player collision
		tileCollision();
		
		if (velY == 0) {
			if (game.getKeyManager().left) {
				velX = -3;
				dir = "l";
			} else if (!game.getKeyManager().right) {
				velX = 0;
			}
			
			if (game.getKeyManager().right) {
				velX = 3;
				dir = "r";
			} else if (!game.getKeyManager().left) {
				velX = 0;
			}
		}
		

		if (velX == 0) {
			if (game.getKeyManager().up) {
				velY = -3;
				dir = "u";
			}  else if (!game.getKeyManager().down) {
				velY = 0;
			}
			if (game.getKeyManager().down) {
				velY = 3;
				dir = "d";
			} else if (!game.getKeyManager().up) {
				velY = 0;
			}
		}
		
		updateRoom();
		
		System.out.println(Game.worldX + " " + Game.worldY);
		x += velX;
		y += velY;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimFrame(), (int) x, (int) y, null);	
		g.setColor(Color.WHITE);
		g.drawRect(getTileBounds().x, getTileBounds().y, getTileBounds().width, getTileBounds().height);
		g.setColor(Color.RED);
		g.drawRect(getHitBounds().x, getHitBounds().y, getHitBounds().width, getHitBounds().height);
	}
	
	private void updateRoom() {
		
		// in Map
			/**
			 * @tick
			 * 
			 * if player intersects with exit region 
			 * 
			 * @bounds
			 * 
			 * if worldX == ... and worldY == ....
			 * 		new Rectangle
			 * 		new Rectangle
			 * 		...to N exits
			 * 	
			 * in maps folder,
			 * 
			 * 		in .txt 
			 * 			
			 * 			100,100,100,200,1 1
			 * 			 ^   ^   ^   ^   ^
			 * 			 |   |   |   |   |
			 * 			 x1  y1  x2  y2  pointer to map
			 * 			200,200,300,300,2 1
			 * 			... to N exits
			 * 			
			 */
		
	}
	
	private void tileCollision() {
		for (int i = 0; i < 11; i++) {
			
			for (int j = 0; j < 16; j++) {
				if (getTileBounds().intersects(Map.rectangleTiles[16 * i + j]) && !Map.walkable[16 * i + j]) {
					x += velX * -1;
					y += velY * -1;
				} 
				
//				if (getHitBounds().intersects(attack)) {
//					// get hit
//
//				} 
			}			
		}
	}
	
	private BufferedImage getCurrentAnimFrame() {
		
		if (dir == "l") {
			return leftAnimation.getCurrentFrame();
		}
		
		else if (dir == "r") {
			return rightAnimation.getCurrentFrame();
		} 
		
		else if (dir == "u") {
			return upAnimation.getCurrentFrame();
		}
		
		else {
			return downAnimation.getCurrentFrame();
		} 
	}
	
	public Rectangle getTileBounds() {
		return new Rectangle((int) x + 36, (int) y + 48, 16, 12);	
	}
	
	public Rectangle getHitBounds() {
		return new Rectangle((int) x + 28, (int) y + 26, 32, 32);	
	}
	
	

}
