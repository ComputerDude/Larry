package com.computerdude.larry.gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.computerdude.larry.Game;
import com.computerdude.larry.tilemap.Background;
import com.sun.glass.events.KeyEvent;

public class SettingsState extends GameState {

	private Background bg;

	private int currentChoice = 0;
	private String[] settings = { "  Test", "< Back" };

	private Font font;
	private Font sFont;
	private Font cFont;
	private Font vFont;
	
	public SettingsState(GameStateManager sm) {
      this.sm = sm;		
      
      bg = new Background("/Background/SettingsScreen.png");
      font = new Font("Joystix Monospace", Font.PLAIN, 36);
      sFont = new Font("Joystix Monospace", Font.PLAIN, 42);
      cFont = new Font("Joystix Monospace", Font.PLAIN, 16);
   	  vFont = new Font("Joystix Monospace", Font.PLAIN, 12);

      try {
    	  
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
		for(int i = 0; i < settings.length; i++) {
			if(i == currentChoice) {
				g.setColor(Color.WHITE);
			} else {
				
				g.setColor(Color.BLACK);
			}
			g.drawString(settings[i], 200, 290 + i * 30);
		}

		g.setFont(cFont);
		g.setColor(Color.WHITE);
		g.drawString("Gold: " + GameStateManager.CURRENCY, 10, 15);
		g.drawString(Game.VERSION, 570, 15);
		g.setFont(vFont);
		g.setColor(Color.WHITE);
		g.drawString("v", 559, 15);
		g.setFont(sFont);
		g.setColor(Color.WHITE);
		g.drawString("Settings", 195, 135);
		
	}
	
	private void select() {
		if(currentChoice == 0) {
			//Stuff
		} else if(currentChoice == 1) {
			sm.setState(GameStateManager.MENUSTATE);
		}
	}
	
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER) {
			select();
		}
		if(k == KeyEvent.VK_UP) {
		currentChoice--;	
		if(currentChoice == -1) {
			currentChoice = settings.length - 1;
		}
		}
		if(k == KeyEvent.VK_DOWN) {
			currentChoice++;
			if(currentChoice == settings.length) {
				currentChoice = 0;
			}
		}
		if(k == KeyEvent.VK_P) {
			if(GameStateManager.CURRENCY < 1) {
			GameStateManager.CURRENCY = 1;
			} else {
				GameStateManager.CURRENCY = GameStateManager.CURRENCY + 1;
			}
		}
	}
	
	public void keyReleased(int k) {
	}
	
}
