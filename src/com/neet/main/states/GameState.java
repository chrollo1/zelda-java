package com.neet.main.states;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.neet.entities.Link;
import com.neet.gfx.*;
import com.neet.main.Game;

public class GameState extends State {
	
	private Link link;

	// constructor
	public GameState(Game game) {
		super(game);
		link = new Link(game, 200, 200);
	}
	
	@Override
	public void tick() {
		link.tick();
	}

	@Override
	public void render(Graphics g) {
		link.render(g);
	}

	@Override
	public void music() {
		
	}
}
