package principal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class Screen extends JComponent
{
	/*
	 * screen extende de jcomponente para que seja desenhada junto com a tela
	 * pelo paintComponent
	 * 
	 * guarda uma referencia para o jogo
	 */
	
	Chess game;
	
	public Screen(Chess game) 
	{
		this.game  = game;
	}
	
	@Override
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		
		/* 
		 * graphics2d extende de graphics
		 * na verdade os graphics sao todos graphics2d,
		 * entao podemos sempre fazer essa conversão,
		 * mas eles sao passados como graphics para ter contibilidade
		 * com versoes mais antigas do java
		 * 
		 * graphics 2d tem funções de desenho que vamos precisar
		 */
		Graphics2D g2d = (Graphics2D) g;
		
		/*
		 * Aqui estamos so desenhando os quadrados, alternando as cores
		 * pulando a cada iteraão a distancia de uma casa, e a cada certo
		 * numero de iteraçao pulando para a fileira de baixo
		 * 
		 */
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
		
		/*
		 * Agora desenhamos as pecas de acordo com a matriz 
		 * de inteiros na qual elas estao guardadas
		 */
		
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