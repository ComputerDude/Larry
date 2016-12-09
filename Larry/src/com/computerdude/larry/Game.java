package com.computerdude.larry;

import java.awt.Toolkit;

import javax.swing.JFrame;

import com.computerdude.larry.window.GamePanel;

public class Game {

	public static String VERSION = "DEV";

	public static void main(String[] args) {
		System.out.println("Larry v" + VERSION + " Starting...");
		JFrame window = new JFrame("Larry " + VERSION);
		window.setContentPane(new GamePanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setIconImage(Toolkit.getDefaultToolkit().getImage(Game.class.getClassLoader().getResource("icons/WindowIcon.png")));
		window.setResizable(false);
		window.pack();
		window.setVisible(true);

	}
}
