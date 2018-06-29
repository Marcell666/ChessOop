package principal;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import gfx.Screen;
import rules.Control;

/*
 * 11/06/2018
 * Fiz a clase abstrata MoveBase que serve de base base para as classe que implementao a interface IMove
 * Ela nao implementa IMove, cabe a cada subclasse fazer isso
 * Juntei os movimentos de peao: norte e sul na mesma classe
 * Retirei as constantes de cor que estavam na classe peca
 * 
 * 
 * 
 * mHandler -> control -> judge -> (self) getMovimentoPeca -> Peca.move -> pecaMovimento.move
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
	
	public Screen screen;
	public Control control;
	
	public Chess(String name) 
	{
		super(name);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		control = Control.getInstance();
		screen = new Screen(this);
		screen.setLayout(null);
		screen.setBounds(0,0,WIDTH,HEIGHT);
		
		getContentPane().add(screen);
		getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		control.registra(screen);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String[] args) 
	{
		new Chess("XADREZ");
	}
	
	public int promove(ImageIcon imgPecas[]) {
		int opcao=0;
		
		System.out.println("peca selecionada foi: " + opcao);
		
		return opcao;
	}
}