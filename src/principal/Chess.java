package principal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Chess extends JFrame{
	
	public static final int QTD_TILES = 8;
	public static final int TILE_WIDTH = 40;
	public static final int TILE_HEIGHT = 40;
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
	 * 
	 * negativo = pecas pretas
	 * */
	
	int pecas[][] =  new int[][]{
		{-5,-4,-3,-2,-1,-3,-4,-5},
		{-6,-6,-6,-6,-6,-6,-6,-6},
		{ 0, 0, 0, 0, 0, 0, 0, 0},
		{ 0, 0, 0, 0, 0, 0, 0, 0},
		{ 0, 0, 0, 0, 0, 0, 0, 0},
		{ 0, 0, 0, 0, 0, 0, 0, 0},
		{ 6, 6, 6, 6, 6, 6, 6, 6},
		{ 5, 4, 3, 2, 1, 3, 4, 5}
	};
	
	public Chess() {
		setBounds(0,0,550,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
		
		pecasBrancas = new Peca[]{
				new Peca("/PA.png", 0, 0),
				new Peca("/PB.png", 0, 0),
				new Peca("/PC.png", 0, 0),
				new Peca("/PD.png", 0, 0),
				new Peca("/PE.png", 0, 0),
				new Peca("/PF.png", 0, 0)
		};
		
		pecasPretas = new Peca[]{
				new Peca("/PG.png", 0, 0),
				new Peca("/PH.png", 0, 0),
				new Peca("/PI.png", 0, 0),
				new Peca("/PJ.png", 0, 0),
				new Peca("/PK.png", 0, 0),
				new Peca("/PL.png", 0, 0)
		};
		}
		catch(IOException e) {
			e.printStackTrace();
			System.err.println("erro ao ler pecas");
		}
		
		board = new Screen(this);
		board.setLayout(null);
		board.setBounds(0,0,550,400);
		getContentPane().add(board);
	}
	
	Peca[] getPecasBrancas() {
		return pecasBrancas;
	}
	Peca[] getPecasPretas() {
		return pecasPretas;
	}
	int[][] getPecas() {
		return pecas;
	}
	
	public static void main(String[] args) {
		Chess c = new Chess();
		c.setTitle("Eu sou uma janela!");
		c.setVisible(true);
	}
}