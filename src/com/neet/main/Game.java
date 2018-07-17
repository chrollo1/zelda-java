package com.neet.main;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.neet.gfx.Assets;
import com.neet.gfx.ImageLoader;
import com.neet.gfx.Map;
import com.neet.gfx.MapLoader;
import com.neet.gfx.Minimap;
import com.neet.gfx.SpriteSheet;
import com.neet.main.states.GameState;
import com.neet.main.states.MenuState;
import com.neet.main.states.State;
import com.neet.managers.KeyManager;
import com.sun.javafx.tk.Toolkit;



@SuppressWarnings({ "unused", "serial" })
public class Game extends Canvas implements Runnable {
	
	public static final String TITLE  = "Legend of Zelda";
	public static final int    WIDTH  = 256;
	public static final int    HEIGHT = 240;
	public static final int    SCALE  = 2;
	
	public int x = 0;
	public int y = 0;
	public int xx = 0;
	
	public boolean running = false;
	public int tickCount = 0;
	
	public Minimap map = new Minimap();

	// map
	private Map worldMap;
	public static int worldX = 1;
	public static int worldY = 1;
	
	private Graphics g;
	
	// states
	private State menuState;
	private State gameState;

	// input
	private KeyManager keyManager;
	
	private JFrame frame;
	
	public Game() {
		
		worldMap = new Map();
		
/*		Dimension d = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		setMinimumSize(d);
		setMaximumSize(d);
		setPreferredSize(d);*/
	
		frame = new JFrame(TITLE);
		frame.setSize(WIDTH * SCALE, HEIGHT * SCALE);		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		frame.add(this, BorderLayout.CENTER);
		
		frame.setUndecorated(false);
		frame.setAlwaysOnTop(true);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		setFocusable(false);
		
		// user input
		keyManager = new KeyManager();
		frame.addKeyListener(keyManager);
		
		frame.pack();
	}
		
	/**
	 * @see ex_1.png in project dir for explanation of game updating
	 * @see ex_2.png [...] 							of game state managing
	 */

	public synchronized void start() throws IOException {
		
		running = true;		
		
		// pre-load assets
		Assets.init();
		
		music();
		
		// pre-load maps
		MapLoader.load();
		
		// states
		gameState = new GameState(this);
		menuState = new MenuState(this);
		State.setState(gameState);
		
		new Thread(this).start();
	}
	
	public synchronized void stop() {
		running = false;
	}
	
	/**
	 * Minecraft: Notch's game loop
	 * @link https://stackoverflow.com/questions/18283199/java-main-game-loop
	 */
	
	public void run() {
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000.0 / 60.0;
		
		int ticks = 0;
		int frames = 0;
		
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		
		while (running) {
			
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			
			boolean canRender = true;
			
			while (delta >= 1) {
				ticks++;
				tick();
				delta--;
				canRender = true;
			}
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if (canRender) {
				frames++;
				render();

			}
			
			if (System.currentTimeMillis() - lastTimer > 1000) {
				lastTimer += 1000;
				System.out.println(ticks + " ticks, " + frames + " frames");
				frames = 0;
				ticks = 0;
			}						
		}
		
	}
	
	public void tick() {
		
		worldMap.mapLoad(worldX, worldY);

		keyManager.tick();
		
		// if current state exist, then update the game
		if (State.getState() != null) {
			
			tickCount++;
			State.getState().tick();

		}
		
        // blip (fade in/out) increment
        xx++;

	}
	
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		
		if (bs == null) {
			createBufferStrategy(2);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, getWidth(), getHeight());
		
		/* ALL DRAWING HERE */
			
		//vvvvvvvvvvvvvvv PUT IN GAME STATE CLASS vvvvvvvvvvvvv
		
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 11; j++) {
				g.drawImage(Map.bg_tile[(j * 16) + i], i * 32, j * 32 + 128, 32, 32,null);
			}
		}

		//^^^^^^^^^^^^^^^ PUT IN GAME STATE CLASS ^^^^^^^^^^^^^

		
		// if current state exist, then render		
		if (State.getState() != null) {		
			tickCount++;
			State.getState().render(g);	
		}
		
		// GUI
		
		// map layout in minimap
		map.paint(g, worldX, worldY, xx);	
		g.drawImage(Assets.item_img, 16 * Game.SCALE + 64 * Game.SCALE + 16, 16 * Game.SCALE + 8, 8 * Game.SCALE, 32 * Game.SCALE, null);
		g.drawImage(Assets.lifeText, 16 * Game.SCALE + 64 * Game.SCALE + 160, 16 * Game.SCALE, 52 * Game.SCALE, 12 * Game.SCALE, null);

		/* ALL DRAWING HERE */
		
		g.dispose();
		bs.show();
	}
	
	private void music() {
		
//		AudioClip theme = new AudioClip(this.getClass().getResource("/sounds/zelda_theme.mp3").toString());
//		theme.play();
       
	}
	
	
	public static void main(String[] args) throws IOException {
		Game game = new Game();
		game.start();
	}
	
	// GETTERS AND SETTERS
	
	public KeyManager getKeyManager() {
		return keyManager;
	}

	public static int getWorldX() {
		return worldX;
	}

	public static void setWorldX(int worldX) {
		Game.worldX = worldX;
	}

	public static int getWorldY() {
		return worldY;
	}

	public static void setWorldY(int worldY) {
		Game.worldY = worldY;
	}

}
