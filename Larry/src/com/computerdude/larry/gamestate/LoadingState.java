package com.computerdude.larry.gamestate;

import java.awt.Graphics2D;
import com.computerdude.larry.tilemap.Background;
import com.sun.glass.events.KeyEvent;

public class LoadingState extends GameState {

	private Background bg;
	
	public LoadingState(GameStateManager sm) {
		this.sm = sm;
		try {
			bg = new Background("/icons/MemLogo.png");
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
		
		/*try {
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}
	
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ESCAPE) {
		sm.setState(0);
		}
	}
	public void keyReleased(int k) {
	}
}
