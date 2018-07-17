package com.neet.gfx;

import java.awt.image.BufferedImage;

public class Animation {

	private int rate, index;
	private long lastTime, timer;
	private BufferedImage[] frames;
	
	public Animation(int rate, BufferedImage[] frames) {
		this.rate = rate;
		this.frames = frames;
		
		index = 0;
		// ms passed since start of program
		lastTime = System.currentTimeMillis();
	}
	
	public void tick() {
		// time passed since current tick method and previously called tick method
		timer += System.currentTimeMillis() - lastTime;
		// reset
		lastTime = System.currentTimeMillis();
			
		if (timer > rate) {
			index++;
			timer = 0;
				
			if (index >= frames.length) {
				index = 0;
			}
		}
		
	}
	
	// getter: 
	public BufferedImage getCurrentFrame() {
		return frames[index];
	}
	
}
