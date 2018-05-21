package principal;

import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Chess extends JFrame{
	
	ImageIcon ic;
	JLabel l;
	Board board;
	Peca peca;
	
	public Chess() {
		super();
		setBounds(0,0,550,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		//ic = new ImageIcon(Chess.class.getResource("/PA.png"));
		//l = new JLabel(ic);
		//l.setSize(32, 40);
		
		try {
			peca = new Peca("/PB.png", 3, 2);
		}catch(IOException e) {
			e.printStackTrace();
			System.err.println("Erro ao ler imagem");
		}
		
		board = new Board();
		peca.setLayout(null);
		board.setLayout(null);
		board.setBounds(0,0,550,400);
		//b.setSize(550, 400);
		board.add(peca);
		getContentPane().add(board);
	}
	public static void main(String[] args) {
		Chess c = new Chess();
		c.setTitle("Eu sou uma janela!");
		c.setVisible(true);
	}
}