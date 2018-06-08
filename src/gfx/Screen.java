package gfx;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

import principal.Chess;
import rules.Control;



public class Screen extends JComponent{
	
	Color casaBranca = Color.WHITE;
	Color casaPreta = Color.DARK_GRAY;
	Color casaDestacada = new Color(0x30A030);
		//onde o mouse esta
	Color casaSelecionada = Color.decode("0x3030A0");
		//ultima casa clicada
	Color casaMovimento = new Color(0x3030A0);
		//casas para a onde a peca selecionada pode se mover;
	
	/*
	 * Podemos usar uma cor para aonde o mouse esta localizado no tabuleiro
	 * Uma cor para a casa na qual esta a peca selecionada, se houver alguma
	 * E uma cor para cada uma das casas para onde o peao pode se mover
	 * 
	 * Talvez uma cor diferente para as casas que estão sendo atacadas.
	 * Mas por enquanto so estamos usando as tres primeiras
	 * E note que  a cor da casa selecionada é a mesma das casas que mostram o 
	 * movimento de uma peca
	 * 
	 * Note as duas formas de especificar uma cor
	 * Existem outras alem dessas
	 * 
	 */
	
	Chess game;
	Control control;
	MouseHandler mHandler;
	
	/*
	 * A classe screen cuida do que é desenhado na tela,
	 * ela extende de jComponent e é adicionada ao jframe que é o jogo
	 * 
	 * A classe mouseHandler cuida dos eventos do mouse que sao repassados ao control.
	 * É importante que mouseHandler seja adicionado a tela e nao ao jogo(Jframe),
	 * para nao considerarmos as barras lateirais e de menu na janela na hora de 
	 * calcular a posição do mouse
	 */
		
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
		
		/*
		 * Para este projeto, nao precisamos fazer uma sistema de layers(camadas)
		 * mais robusto.
		 * Mas note que figuras serão desenhadas na ordem que o codigo for lido
		 * Entao uma maneira bem simples de simular essas camadas é colocar o desenho
		 * na ordem desejada
		 * 
		 * Nao temos muito controle de fora da tela sobre o que sera desenhado
		 * So o que podemos fazer é pedir screen.repaint()
		 * Isso é bom porque tudo fica aqui, no mesmo lugar.
		 * E podemos deicidir facilmente a ordem das coisas também.
		 * Mas é ruim porque temos que solicitar a cada objeto informações a respeito
		 * do que devemos desenhar
		 * 
		 * A maioria dos trechos de codigo abaixo, faz uma pequena verificação e desenha
		 * um ou mais quadrados no tabuleiro
		 */
		
		/*	Desenhando tabuleiro	*/
		boolean corCasa = false;
		for(int i=0;i<Chess.QTD_TILES;i++) 
		{
			for (int e=0;e<Chess.QTD_TILES;e++) 
			{
				if(corCasa) 
					g2d.setColor(casaBranca);
				else
					g2d.setColor(casaPreta);
					//g2d.setColor(Color.decode("0xAA0099"));
				
				g2d.fillRect(e*Chess.TILE_WIDTH,i*Chess.TILE_HEIGHT,Chess.TILE_WIDTH,Chess.TILE_HEIGHT);
				corCasa = !corCasa;
			}
			corCasa = !corCasa;
		}
		
		
		/*	Desenhando Casa Selecionada	*/
		if(control.isSelecionada()) {
			int x = control.getXSelecionada();
			int y = control.getYSelecionada();
			if(x>=0 && x<Chess.QTD_TILES &&
					y>=0 && y<Chess.QTD_TILES) {
				g2d.setColor(casaSelecionada);
				g2d.fillRect(x*Chess.TILE_WIDTH, y*Chess.TILE_HEIGHT,Chess.TILE_WIDTH,Chess.TILE_HEIGHT);
			}
		}
		
		/*	Desenhando casas possiveis	
		 * 
		 * Quando uma peca é selecionada, o controle guarda as casas para onde ela
		 * pode ir.
		 * Esse conjunto de casas pode ser retornado pela funcao getPos
		 * Esta classe chama essa função para destacar as casas para onde
		 * a peca selecionada pode se mover
		 * */
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
		
	}
	
}