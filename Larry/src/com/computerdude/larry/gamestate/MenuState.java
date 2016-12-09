package com.computerdude.larry.gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.computerdude.larry.Game;
import com.computerdude.larry.tilemap.Background;
import com.sun.glass.events.KeyEvent;

public class MenuState extends GameState {

	private Background bg;
	
	private int currentChoice = 0;
	private String[] options = { "Start", "Settings", "Quit" };
	
	private Font font;
	private Font cFont;
	private Font vFont;
	
	public MenuState(GameStateManager sm) {
		this.sm = sm;
		try {
			bg = new Background("/Background/TitleScreen.png");
			
			font = new Font("Joystix Monospace", Font.PLAIN, 36);
			cFont = new Font("Joystix Monospace", Font.PLAIN, 16);
			vFont = new Font("Joystix Monospace", Font.PLAIN, 12);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void init() {
	}

	public void update() {
	}

	public void draw(Graphics2D g) {
		bg.draw(g);
		
		g.setFont(font);
		for(int i = 0; i < options.length; i++) {
			if(i == currentChoice) {
				g.setColor(Color.WHITE);
			} else {
				
				g.setColor(Color.BLACK);
			}
			g.drawString(options[i], 200, 320 + i * 30);
		}
		
		g.setFont(cFont);
		g.setColor(Color.WHITE);
		g.drawString("Gold: " + GameStateManager.CURRENCY, 10, 15);
		g.drawString(Game.VERSION, 570, 15);
		g.setFont(vFont);
		g.setColor(Color.WHITE);
		g.drawString("v", 559, 15);
		
	}

	
	
	private void select() {
		if(currentChoice == 0) {
			sm.setState(GameStateManager.LEVEL1STATE);
		} else if(currentChoice == 1) {
			sm.setState(GameStateManager.SETTINGSSTATE);
		} else if(currentChoice == 2){
			System.out.println("Larry v" + Game.VERSION + " Closing...");
			System.exit(0);
		}
	}
	
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER) {
			select();
		}
		if(k == KeyEvent.VK_UP) {
		currentChoice--;	
		if(currentChoice == -1) {
			currentChoice = options.length - 1;
		}
		}
		if(k == KeyEvent.VK_DOWN) {
			currentChoice++;
			if(currentChoice == options.length) {
				currentChoice = 0;
			}
		}
		if(k == KeyEvent.VK_P) {
			if(GameStateManager.CURRENCY < 1) {
			GameStateManager.CURRENCY = 1;
			} else {
				GameStateManager.CURRENCY = GameStateManager.CURRENCY + 1;
				if(GameStateManager.CURRENCY <= 1000000) {
					//TODO Stuff
				}
			}
		}
		if(k == KeyEvent.VK_O) {
			GameStateManager.CURRENCY = GameStateManager.CURRENCY - 1;
			if(GameStateManager.CURRENCY < 0) {
				GameStateManager.CURRENCY = 0;
			}
		}
	}

	public void keyReleased(int k) {
	}

}
