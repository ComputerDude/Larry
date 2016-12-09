package com.computerdude.larry.tilemap;

import java.awt.image.BufferedImage;

public class Tile {

	private BufferedImage image;
	private int type;

	public static final int NORMAL = 0;
	public static final int BLOCKED = 1;

	public Tile(BufferedImage img, int type) {
		this.image = img;
		this.type = type;
	}

	public BufferedImage getImage() {
		return image;
	}

	public int getType() {
		return type;
	}

}
