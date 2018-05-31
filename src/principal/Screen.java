package principal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;

public class Screen extends JComponent implements MouseListener, MouseMotionListener
{
	Chess game;
	Control control;
	/*
	 * Estou guardando o tile no qual o mouse esta localizado
	 * 
	 */
	int xTile=0,yTile=0;
	
	public Screen(Chess game) 
	{
		this.game  = game;
		control = game.control;
		
		/*
		 * Para capturar ações do mouse e do teclado, precisamos
		 * dos listeners
		 * 
		 * um listener é um classe que implementa as interfaces e funções
		 * apropriadas.
		 * Esta classe é um listener como voce pode ver la em cima
		 * 
		 * MouseListener nos permite detectar cliques do mouse
		 * e MotionListener o movimento
		 * note que estamos colocando esta classe para ser o listener de 
		 * eventos do mouse neste objeto
		 * Isso quer dizer que se tivermos duas instancias os eventos serao duplicados
		 * tambem quer dizer que temos este priprio objeto como referencia
		 * 
		 * Se colocarmos a classe chess(JFrame) para receber os eventos do mouse
		 * game.addMouseListener(this);
		 * Quando formos verificar a posição do clique, essa posição vai ser relativa a janela
		 * e novamente considerando as barrais laterais e superior.
		 * causando uma imprecisão no clique.
		 * 
		 * Por isso estamos adicionando o listener a propria tela, preenche
		 *  o tamanhdo do conteudo da janela e comeca no 0,0 apropriado
		 * 
		 * Note que outro objeto/classe poderia ser usado como listener
		 * Na verdade isso deixaria as coisas mais organizadas
		 * 
		 */
		addMouseListener(this);
		addMouseMotionListener(this);
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

	/*
	 * Essas funções são so para converter de posiçao pixel a pixel
	 * para localização na matriz do tabuleiro
	 */
	
	private int convertXToTile(int x) {
		return x/Chess.TILE_WIDTH;
	}
	private int convertYToTile(int y) {
		return y/Chess.TILE_HEIGHT;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
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

	/*
	 * Eventos de mouse com frequencia ficam somente quando ele solta, para
	 * dar a chance de cancelar a ação arrastando o mouse para longe e soltando
	 * em outro lugar completamente diferente.
	 * 
	 * Isso nao seria possivel se capturassemos e reagissemos ao mousePress 
	 * 
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		System.out.println("x do mouse: "+arg0.getX());
		System.out.println("y do mouse: "+arg0.getY());
		
		/*
		 * Ao clicar passamos para o controle a matriz do tabuleiro e a posicao
		 * do clique em relação ao tabuleiro
		 */
		control.click(game.getPecas(), convertXToTile(arg0.getX()), convertYToTile( arg0.getY()));
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/*
	 *	Estou guardando a posicao que o mouse esta na matriz
	 * ela é atualizada quando mudo de quadrado (tile) 
	 */
	@Override
	public void mouseMoved(MouseEvent arg0) {

		int xTileN, yTileN;
		xTileN = convertXToTile(arg0.getX());
		yTileN = convertYToTile(arg0.getY());
		/*
		 * Se o tile que o mouse esta agora for diferente do tile no qual
		 * ele estava da ultima vez que movi
		 * atualizo o tile onde ele esta
		 * 
		 */
		if(xTileN != xTile || yTileN != yTile) {
			System.out.println(xTileN+", "+yTileN);
			xTile = xTileN;
			yTile = yTileN;
		}
	}
	
}