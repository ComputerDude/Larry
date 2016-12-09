package com.computerdude.larry.gamestate;

import java.awt.Color;
import java.awt.Graphics2D;

import com.computerdude.larry.tilemap.TileMap;
import com.computerdude.larry.window.GamePanel;

public class Level1State extends GameState {

	private TileMap tm;
	
	
	public Level1State(GameStateManager sm) {
		this.sm = sm;
	}

	public void init() {

		tm = new TileMap(30);
		tm.loadTiles("/Tilesets/grasstileset.gif");
		tm.loadMap("/Maps/level1-1.map");
		tm.setPosition(0, 0);
	}

	public void update() {

	}

	public void draw(Graphics2D g) {

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		tm.draw(g);
		
	}

	public void keyPressed(int k) {

	}

	public void keyReleased(int k) {

	}

}
