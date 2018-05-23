package principal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Chess extends JFrame
{
	public static final int WIDTH = 575;
	public static final int HEIGHT = 600;
	public static final int QTD_TILES = 8;
	public static final int TILE_WIDTH = 70;
	public static final int TILE_HEIGHT = 70;
	
	Peca pecasBrancas[];
	Peca pecasPretas[];
	
	Screen board;
	
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
	
	public Chess() 
	{
		setBounds(0,0,WIDTH,HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try
		{
			pecasBrancas = new Peca[]
			{
				new Peca("/ReiBr.png", 0, 0),
				new Peca("/RainhaBr.png", 0, 0),
				new Peca("/BispoBr.png", 0, 0),
				new Peca("/CavaloBr.png", 0, 0),
				new Peca("/TorreBr.png", 0, 0),
				new Peca("/PeaoBr.png", 0, 0)
			};
		
			pecasPretas = new Peca[]
			{
				new Peca("/ReiPr.png", 0, 0),
				new Peca("/RainhaPr.png", 0, 0),
				new Peca("/BispoPr.png", 0, 0),
				new Peca("/CavaloPr.png", 0, 0),
				new Peca("/TorrePr.png", 0, 0),
				new Peca("/PeaoPr.png", 0, 0)
			};
		}
		catch(IOException e) 
		{
			e.printStackTrace();
			System.err.println("erro ao ler pecas");
		}
		
		board = new Screen(this);
		board.setLayout(null);
		board.setBounds(0,0,WIDTH,HEIGHT);
		getContentPane().add(board);
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
		Chess c = new Chess();
		c.setTitle("XADREZ");
		c.setVisible(true);
	}
}