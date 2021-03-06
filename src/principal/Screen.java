package principal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class Screen extends JComponent{
	Chess game;
	Control control;
	MouseHandler mHandler;
	
	
	
	public Screen(Chess game) 
	{
		this.game  = game;
		control = game.control;
		mHandler = new MouseHandler(this, control);
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
		/*	Desenhando	Casa destacada parte 1/2	*/
		// a.k.a. a casa onde o mouse esta
		if(mHandler.xTile>=0 && mHandler.xTile<Chess.QTD_TILES &&
				mHandler.yTile>=0 && mHandler.yTile<Chess.QTD_TILES) {
			g2d.setColor(new Color(0x00A000));
			g2d.fillRect(mHandler.xTile*Chess.TILE_WIDTH,mHandler.yTile*Chess.TILE_HEIGHT,Chess.TILE_WIDTH,Chess.TILE_HEIGHT);
		}
		
		/*	Desenhando Peca Selecionada	*/
		if(control.isSelecionada()) {
			int x = control.getXSelecionada();
			int y = control.getYSelecionada();
			if(x>=0 && x<Chess.QTD_TILES &&
					y>=0 && y<Chess.QTD_TILES) {
				g2d.setColor(new Color(0x303080));
				g2d.fillRect(x*Chess.TILE_WIDTH, y*Chess.TILE_HEIGHT,Chess.TILE_WIDTH,Chess.TILE_HEIGHT);
			}
		}
		
		/*	Desenhando casas possiveis	*/
		
		/*	acessar array de control com as posiçoes que devem ser destacadas?*/
		
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
	
}