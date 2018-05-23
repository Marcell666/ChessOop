package principal;

import java.io.IOException;

import javax.swing.JFrame;

public class Chess extends JFrame
{
	public static final int WIDTH = 575;
	public static final int HEIGHT = 600;
	public static final int QTD_TILES = 8;
	public static final int TILE_WIDTH = 70;
	public static final int TILE_HEIGHT = 70;
	
	Peca pecasBrancas[];
	Peca pecasPretas[];
	
	Screen screen;
	
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
	
	int pecas[][] =  new int[][]
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
		setBounds(0,0,WIDTH,HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
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
		
		screen = new Screen(this);
		screen.setLayout(null);
		screen.setBounds(0,0,WIDTH,HEIGHT);
		getContentPane().add(screen);
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
	
	public static void main(String[] args) 
	{
		new Chess("XADREZ");
	}
}