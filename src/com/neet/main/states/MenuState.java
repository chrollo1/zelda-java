package com.neet.main.states;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.neet.gfx.*;
import com.neet.main.Game;

public class MenuState extends State {

	// constructor
	public MenuState(Game game) {
		super(game);
	}
	
	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics g) {
		ImageIcon titleScreen = new ImageIcon("title.gif");
		g.drawImage(titleScreen.getImage(), -64, 0, titleScreen.getIconWidth() , titleScreen.getIconHeight()  , null);	
	}

	@Override
	public void music() {
		
	}
}
