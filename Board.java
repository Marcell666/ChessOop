package principal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;




@SuppressWarnings("serial")
public class Board extends JPanel {
private static final int QTD_TILES = 8;
private static final int TILE_WIDTH = 40;
private static final int TILE_HEIGHT = 40;
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		boolean casaPreta = true;
		for(int i=0;i<QTD_TILES;i++) {
			
			for (int e=0;e<QTD_TILES;e++) {
				if(casaPreta) 
					g2d.setColor(Color.WHITE);
				else
					g2d.setColor(Color.BLACK);
				g2d.fillRect(e*TILE_WIDTH,i*TILE_HEIGHT,TILE_WIDTH,TILE_HEIGHT);
				casaPreta = !casaPreta;
			}
			casaPreta = !casaPreta;
		}
	}
	
}
