package com.neet.main.states;

import java.awt.Graphics;

import com.neet.main.Game;

public abstract class State {

	private static State currState = null;
	
	// setter
	public static void setState(State state) {
		currState = state;
	}
	
	// getter
	public static State getState() {
		return currState;
	}
	
	protected Game game;
	
	public State(Game game) {
		this.game = game;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public abstract void music();
		
}
