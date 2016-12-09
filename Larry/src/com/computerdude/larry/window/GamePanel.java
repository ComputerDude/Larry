package com.computerdude.larry.window;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;

import com.computerdude.larry.gamestate.GameStateManager;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener {

	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;

	private Thread thread;
	private boolean running;
	private int FPS = 60;
	private long targetTime = 1000 / FPS;

	private BufferedImage image;
	private Graphics2D g;

	private GameStateManager sm;

	public GamePanel() {
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
	}
	
	public void addNotify() {
		super.addNotify();
		if (thread == null) {
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
		}
	}

	private void init() {

		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();

		running = true;

		sm = new GameStateManager();

	}

	public void run() {

		init();

		long start;
		long elapsed;
		long wait;

		while (running) {
			
			start = System.nanoTime();

			update();
			
			draw();
			drawToScreen();
			
			if (sm.getCurrentState() == 0) {
				try {
					TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				sm.setState(GameStateManager.MENUSTATE);
			}

			elapsed = System.nanoTime() - start;			
			wait = targetTime - elapsed / 10000000;
			
			if (wait < 0) {
				wait = 0;
			}

			try {
				Thread.sleep(wait);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	private void update() {
		sm.update();
	}

	private void draw() {
		sm.draw(g);
	}

	private void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		sm.keyPressed(e.getKeyCode());
	}

	public void keyReleased(KeyEvent e) {
		sm.keyReleased(e.getKeyCode());
	}

}
