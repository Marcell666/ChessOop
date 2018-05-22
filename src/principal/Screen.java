package principal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Screen extends JPanel 
{
	Chess game;
	
	public Screen(Chess game) 
	{
		this.game  = game;
	}
	
	@Override
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		boolean casaPreta = false;
		for(int i=0;i<Chess.QTD_TILES;i++) 
		{
			for (int e=0;e<Chess.QTD_TILES;e++) 
			{
				if(casaPreta) 
					g2d.setColor(Color.WHITE);
				else
					g2d.setColor(Color.darkGray);
				
				g2d.fillRect(e*Chess.TILE_WIDTH,i*Chess.TILE_HEIGHT,Chess.TILE_WIDTH,Chess.TILE_HEIGHT);
				casaPreta = !casaPreta;
			}
			casaPreta = !casaPreta;
		}
		
		for(int i=0;i<Chess.QTD_TILES;i++) 
		{
			for(int e=0;e<Chess.QTD_TILES;e++) 
			{
				int id = game.getPecas()[i][e];
				
				if(id > 0)
				{
					game.getPecasBrancas()[id-1].desenha(g, e, i);
				}
				if(id < 0)
				{
					id=-id;
					game.getPecasPretas()[id-1].desenha(g, e, i);
				}
			}
		}
		
	}
	
}