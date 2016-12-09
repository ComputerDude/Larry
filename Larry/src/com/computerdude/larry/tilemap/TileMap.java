package com.computerdude.larry.tilemap;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import com.computerdude.larry.window.GamePanel;

public class TileMap {

	private double x;
	private double y;

	private double xmin;
	private double xmax;
	private double ymin;
	private double ymax;

	private double tween;

	private int[][] map;
	private int tileSize;
	private int rows;
	private int columns;
	private int width;
	private int height;

	private BufferedImage tileSet;
	private int numTilesAcross;
	private Tile[][] tiles;

	private int rowOffset;
	private int colOffset;
	private int numRowsToDraw;
	private int numColsToDraw;

	public TileMap(int tileSize) {
		this.tileSize = tileSize;
		numRowsToDraw = GamePanel.HEIGHT / tileSize + 2;
		numColsToDraw = GamePanel.WIDTH / tileSize + 2;
		tween = 0.07;
	}

	public void loadTiles(String s) {

		try {

			tileSet = ImageIO.read(getClass().getResourceAsStream(s));
			numTilesAcross = tileSet.getWidth() / tileSize;
			tiles = new Tile[2][numTilesAcross];

			BufferedImage subimage;
			for (int col = 0; col < numTilesAcross; col++) {

				subimage = tileSet.getSubimage(col * tileSize, 0, tileSize, tileSize);
				tiles[0][col] = new Tile(subimage, Tile.NORMAL);

				subimage = tileSet.getSubimage(col * tileSize, tileSize, tileSize, tileSize);
				tiles[1][col] = new Tile(subimage, Tile.BLOCKED);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void loadMap(String s) {

		try {

			InputStream in = getClass().getResourceAsStream(s);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			columns = Integer.parseInt(br.readLine());
			rows = Integer.parseInt(br.readLine());
			map = new int[rows][columns];
			width = columns * tileSize;
			height = rows * tileSize;

			String delims = "\\s+";
			for (int row = 0; row < rows; row++) {
				String line = br.readLine();
				String[] tokens = line.split(delims);
				for (int col = 0; col < columns; col++) {
					map[row][col] = Integer.parseInt(tokens[col]);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public int getTileSize() {
		return tileSize;
	}

	public int getx() {
		return (int) x;
	}

	public int gety() {
		return (int) y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getType(int row, int col) {
		int rc = map[row][col];
		int r = rc / numTilesAcross;
		int c = rc / numTilesAcross;
		return tiles[r][c].getType();
	}

	public void setPosition(double x, double y) {
		this.x += (x - this.x) * tween;
		this.y += (x - this.y) * tween;

		fixBounds();

		colOffset = (int) -this.x / tileSize;
		rowOffset = (int) -this.y / tileSize;
	}

	public void fixBounds() {
		if (x < xmin)
			x = xmin;
		if (x < xmax)
			x = xmax;
		if (y < ymin)
			x = ymin;
		if (y < ymax)
			x = ymax;
	}
	
	public void draw(Graphics2D g) {
		
		for(int row = rowOffset;row < (rowOffset + numRowsToDraw);row++) {
			
			if(row >= rows) break;
			
			for(int col = colOffset;col < (colOffset + numColsToDraw);col++) {
				
				if(col >= columns) break;
				
				if(map[row][col] == 0) continue;
				
				int rc = map[row][col];
				int r = rc / numTilesAcross;
				int c = rc / numTilesAcross;
				
				g.drawImage(tiles[r][c].getImage(), (int) x + col * tileSize, (int) y + row * tileSize, null);
				
			}
			
		}
		
	}

}
