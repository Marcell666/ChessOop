package gfx;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

import principal.Chess;
import rules.Control;



public class Screen extends JComponent{
	
	Color casaDestacada = new Color(0x30A030);
		//onde o mouse esta
	Color casaSelecionada = new Color(0x3030A0);
		//ultima casa clicada
	Color casaMovimento = new Color(0x3030A0);
		//casas para a onde a peca selecionada pode se mover;
	
	Chess game;
	Animator animator;
	Control control;
	MouseHandler mHandler;
	
	
	
	public Screen(Chess game) 
	{
		this.game  = game;
		control = game.control;
		animator = game.animator;
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
		
		
		/*	Desenhando Peca Selecionada	*/
		if(control.isSelecionada()) {
			int x = control.getXSelecionada();
			int y = control.getYSelecionada();
			if(x>=0 && x<Chess.QTD_TILES &&
					y>=0 && y<Chess.QTD_TILES) {
				g2d.setColor(casaSelecionada);
				g2d.fillRect(x*Chess.TILE_WIDTH, y*Chess.TILE_HEIGHT,Chess.TILE_WIDTH,Chess.TILE_HEIGHT);
			}
		}
		
		/*	Desenhando casas possiveis	*/
		if(control.isSelecionada()) {
			
			Integer pos[] = control.getPos();
			for(int i=0; i<pos.length;i+=2) {
				g2d.setColor(casaMovimento);
				g2d.fillRect(pos[i]*Chess.TILE_WIDTH+10, pos[i+1]*Chess.TILE_HEIGHT+10,Chess.TILE_WIDTH-20,Chess.TILE_HEIGHT-20);
				
			}
		}
		
		/*	Desenhando	Casa destacada parte	*/
		// a.k.a. a casa onde o mouse esta
		if(mHandler.xTile>=0 && mHandler.xTile<Chess.QTD_TILES &&
				mHandler.yTile>=0 && mHandler.yTile<Chess.QTD_TILES) {
			g2d.setColor(casaDestacada);
			g2d.fillRect(mHandler.xTile*Chess.TILE_WIDTH,mHandler.yTile*Chess.TILE_HEIGHT,Chess.TILE_WIDTH,Chess.TILE_HEIGHT);
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
		

		System.out.println("desenhando "+animator.isAnimating());
		if(animator.isAnimating()) {
			System.out.println("desenhando");
			animator.desenha(g);
		}
	}
	
	public void dafuq() {
		System.out.println("dafuq");
	}
	
}