package com.computerdude.larry.gamestate;

import java.util.ArrayList;

public class GameStateManager {

	private ArrayList<GameState> gameStates;
	private int currentState;
	
	public int getCurrentState() {
		return currentState;
	}

	public static int CURRENCY;
	public static final int LOADINGSTATE = 0;
	public static final int MENUSTATE = 1;
	public static final int SETTINGSSTATE = 2;
	public static final int LEVEL1STATE = 3;
	
	
	public GameStateManager() {
	
		gameStates = new ArrayList<GameState>();
		
		currentState = LOADINGSTATE;
		gameStates.add(new LoadingState(this));
		gameStates.add(new MenuState(this));
    	gameStates.add(new SettingsState(this));
		gameStates.add(new Level1State(this));
		
		
	}
	
	public void setState(int state) {
		currentState = state;
		gameStates.get(currentState).init();
	}
	
	public void update() {
		gameStates.get(currentState).update();
	}
	
	public void draw(java.awt.Graphics2D g) {
		gameStates.get(currentState).draw(g);
	}
	
	public void keyPressed(int k) {
		gameStates.get(currentState).keyPressed(k);
	}
	
	public void keyReleased(int k) {
		gameStates.get(currentState).keyReleased(k);
	}
}
