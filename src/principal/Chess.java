package principal;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;

public class Chess extends JFrame
{
	public static final int WIDTH = 560;
	public static final int HEIGHT = 560;
	public static final int QTD_TILES = 8;
	public static final int TILE_WIDTH = 70;
	public static final int TILE_HEIGHT = 70;
	
	Peca pecasBrancas[];
	Peca pecasPretas[];
	
	Screen screen;
	/*
	 * Control vai fazer ser o intermediario entre as classes chess e judge
	 */
	Control control;
	
	/*
	 * 0 = casa vazia
	 * 1 = rei
	 * 2 = rainha
	 * 3 = bispo
	 * 4 = cavaleiro
	 * 5 = torre
	 * 6 = peao
	 * 
	 * negativo = pecas pretas
	 * */
	
	private int pecas[][] =  new int[][]
	{
		{-5,-4,-3,-2,-1,-3,-4,-5},
		{-6,-6,-6,-6,-6,-6,-6,-6},
		{ 0, 0, 0, 0, 0, 0, 0, 0},
		{ 0, 0, 0, 0, 0, 0, 0, 0},
		{ 0, 0, 0, 0, 0, 0, 0, 0},
		{ 0, 0, 0, 0, 0, 0, 0, 0},
		{ 6, 6, 6, 6, 6, 6, 6, 6},
		{ 5, 4, 3, 2, 1, 3, 4, 5}
	};
	
	public Chess(String name) 
	{
		super(name);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*
		 * Nao queremos que o usuario seja capaz de mudar as dimensoes da tela
		 * assim ele nao vai conseguir aumentar a tela e clicar fora do
		 * tabuleiro por exemplo
		 */
		setResizable(false);
		
		try
		{
			pecasBrancas = new Peca[]
			{
				new Peca("/ReiBr.png"),
				new Peca("/RainhaBr.png"),
				new Peca("/BispoBr.png"),
				new Peca("/CavaloBr.png"),
				new Peca("/TorreBr.png"),
				new Peca("/PeaoBr.png")
			};
		
			pecasPretas = new Peca[]
			{
				new Peca("/ReiPr.png"),
				new Peca("/RainhaPr.png"),
				new Peca("/BispoPr.png"),
				new Peca("/CavaloPr.png"),
				new Peca("/TorrePr.png"),
				new Peca("/PeaoPr.png")
			};
		}
		catch(IOException e) 
		{
			e.printStackTrace();
			System.err.println("erro ao ler pecas");
		}
		control = new Control(this);
		screen = new Screen(this);
		screen.setLayout(null);
		screen.setBounds(0,0,WIDTH,HEIGHT);
		
		getContentPane().add(screen);
		getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	Peca[] getPecasBrancas() 
	{
		return pecasBrancas;
	}
	
	Peca[] getPecasPretas() 
	{
		return pecasPretas;
	}
	
	int[][] getPecas() 
	{
		return pecas;
	}
	
	/*
	 * Essa função é chamada por control, quando ele recebe uma confirmação
	 * de judge de que uma peca deve ser movida para outro lugar
	 * 
	 * Ela recebe localizado no tabuleiro da peca que deve se mover,
	 * e a nova localização dela no tabuleiro
	 */
	void movePeca(int xAtual, int yAtual, int xNovo, int yNovo) {
		pecas[yNovo][xNovo] = pecas[yAtual][xAtual];
		pecas[yAtual][xAtual] = 0;
		
		System.out.printf("mudei de %d,%d para %d,%d\n",  xAtual, yAtual, xNovo, yNovo);
		
		/*
		 * chamamos repaint para atualizar a posiçao da peça
		 * a função repaint chamada a função paintComponent no componente e
		 * em seus componentes filhos da forma apropriada
		 * 
		 */
		repaint();
	}
	
	public static void main(String[] args) 
	{
		new Chess("XADREZ");
	}
}