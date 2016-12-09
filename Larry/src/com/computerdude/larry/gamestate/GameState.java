package com.computerdude.larry.gamestate;

public abstract class GameState {

	protected GameStateManager sm;
	
	public abstract void init();
	public abstract void update();
	public abstract void draw(java.awt.Graphics2D g);
	public abstract void keyPressed(int k);
	public abstract void keyReleased(int k);
	
	
}
