package principal;

import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Chess extends JFrame{
	
	ImageIcon ic;
	JLabel l;
	Board b;
	Peca p;
	
	public Chess() {
		setBounds(0,0,550,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		ic = new ImageIcon(Chess.class.getResource("/PA.png"));
		l = new JLabel(ic);
		l.setSize(32, 40);
		try {
			p = new Peca("/home/sibeli/Documents/Bruce/eclipse-workspace/ChessOop/res/PB.png", 3, 2);
		}catch(IOException e) {
			e.printStackTrace();
			System.err.println("Erro ao ler imagem");
		}
		
		b = new Board();
		p.setLayout(null);
		b.setLayout(null);
		b.setBounds(0,0,550,400);
		//b.setSize(550, 400);
		b.add(p);
		getContentPane().add(b);
	}
	public static void main(String[] args) {
		Chess c = new Chess();
		c.setTitle("Eu sou uma janela!");
		c.setVisible(true);
	}
}