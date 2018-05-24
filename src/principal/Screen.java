package principal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;

public class Screen extends JComponent implements MouseListener
{
	Chess game;
	Control control;
	
	public Screen(Chess game) 
	{
		this.game  = game;
		control = game.control;
		game.addMouseListener(this);
	}
	
	@Override
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		/*	Desenhando tabuleiro	*/
		boolean casaPreta = false;
		for(int i=0;i<Chess.QTD_TILES;i++) 
		{
			for (int e=0;e<Chess.QTD_TILES;e++) 
			{
				if(casaPreta) 
					g2d.setColor(Color.white);
				else
					g2d.setColor(Color.darkGray);
					//g2d.setColor(Color.decode("0xAA0099"));
				
				g2d.fillRect(e*Chess.TILE_WIDTH,i*Chess.TILE_HEIGHT,Chess.TILE_WIDTH,Chess.TILE_HEIGHT);
				casaPreta = !casaPreta;
			}
			casaPreta = !casaPreta;
		}
		
		/*	Desenhando Pecas	*/
		
		for(int i=0;i<Chess.QTD_TILES;i++) 
		{
			for(int e=0;e<Chess.QTD_TILES;e++) 
			{
				int id = game.getPecas()[i][e];
				
				if(id > 0)
				{
					game.getPecasBrancas()[id-1].desenha(g, e, i);
				}
				else if(id < 0)
				{
					id=-id;
					game.getPecasPretas()[id-1].desenha(g, e, i);
				}
			}
		}
		
	}

	private int convertXToTile(int x) {
		return x/Chess.TILE_WIDTH;
	}
	private int convertYToTile(int y) {
		return y/Chess.TILE_HEIGHT;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println("x do mouse: "+arg0.getX());
		System.out.println("y do mouse: "+arg0.getY());
		
		control.click(game.getPecas(), convertXToTile(arg0.getX()), convertYToTile( arg0.getY()));
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}