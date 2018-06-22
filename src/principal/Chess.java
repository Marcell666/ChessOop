package principal;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import gfx.Screen;
import pecas.Bispo;
import pecas.Cavaleiro;
import pecas.Peao;
import pecas.Peca;
import pecas.Rainha;
import pecas.Rei;
import pecas.Torre;
import rules.Control;

/*
 * 11/06/2018
 * Fiz a clase abstrata MoveBase que serve de base base para as classe que implementao a interface IMove
 * Ela nao implementa IMove, cabe a cada subclasse fazer isso
 * Juntei os movimentos de peao: norte e sul na mesma classe
 * Retirei as contstantes de cor que estavam na classe peca
 * 
 * 
 * 
 */


public class Chess extends JFrame
{
	public static final int WIDTH = 560;
	public static final int HEIGHT = 560;
	public static final int QTD_TILES = 8;
	public static final int TILE_WIDTH = 70;
	public static final int TILE_HEIGHT = 70;
	
	private Peca pecasBrancas[];
	private Peca pecasPretas[];
	
	public Screen screen;
	public Control control;
	
	private int pecaId;
	
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
		{ 6, 6, 6, 6, 6, 6, -6, 6},
		{ 5, 4, 3, 2, 1, 3, 4, 5}
	};
	
	public Chess(String name) 
	{
		super(name);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		try
		{
			pecasBrancas = new Peca[]
			{
				new Rei("/ReiBr.png"),
				new Rainha("/RainhaBr.png"),
				new Bispo("/BispoBr.png"),
				new Cavaleiro("/CavaloBr.png"),
				new Torre("/TorreBr.png"),
				new Peao("/PeaoBr.png")
			};
		
			pecasPretas = new Peca[]
			{
				new Rei("/ReiPr.png"),
				new Rainha("/RainhaPr.png"),
				new Bispo("/BispoPr.png"),
				new Cavaleiro("/CavaloPr.png"),
				new Torre("/TorrePr.png"),
				new Peao("/PeaoPr.png")
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
	
	public Peca[] getPecasBrancas() 
	{
		return pecasBrancas;
	}
	
	public Peca[] getPecasPretas() 
	{
		return pecasPretas;
	}
	
	public int[][] getPecas() 
	{
		return pecas;
	}
	
	public void movePeca(int xAtual, int yAtual, int xNovo, int yNovo) {
		pecas[yNovo][xNovo] = pecas[yAtual][xAtual];		
		pecas[yAtual][xAtual] = 0;
		
		
		
		//System.out.printf("mudei de %d,%d para %d,%d\n",  xAtual, yAtual, xNovo, yNovo);
		
		repaint();
	}
	
	public Integer[] getMovimentoPeca(int x, int y) {
		int peca = pecas[y][x];
		assert(peca!=0);
		if(peca>0)
			return pecasBrancas[peca-1].move(pecas, x, y);
		else
			return pecasPretas[-peca-1].move(pecas, x, y);
	}
	
	public static void main(String[] args) 
	{
		new Chess("XADREZ");
	}

	public boolean isPeao(int x, int y) {
		return Math.abs(pecas[y][x]) == 6;
	}
	
	public void promove(int x, int y) {

		ImageIcon imgPecas[] = new ImageIcon[5];
		int opcao=0;
		
		for(int i=0; i<5;i++) {
			if(pecas[y][x]<0)
				imgPecas[i] = new ImageIcon(pecasPretas[i+1].getImage());
			else
				imgPecas[i] = new ImageIcon(pecasBrancas[i+1].getImage());
		}
		do {
			opcao = JOptionPane.showOptionDialog(this, "Para qual peça gostaria que o peão fosse promovido?", "Promoção de Peça", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, imgPecas, "rainha");
		}while(opcao==JOptionPane.CLOSED_OPTION);
		
		System.out.println("peca selecionada foi: " + opcao);
		if(pecas[y][x]<0)
			pecas[y][x] = -(opcao+2);
		else
			pecas[y][x] = opcao+2;		
	}
}