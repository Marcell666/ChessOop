package pecas;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import movimento.IMove;
import principal.Chess;

public abstract class Peca
{	
	public static final boolean BRANCA = true;
	public static final boolean PRETA = false;
	
	BufferedImage img;
	IMove pecaMovimento;
	
	public Peca(String path) throws IOException
	{
		img = ImageIO.read(Peca.class.getResource(path));
	}
	
	public void desenha(Graphics g, int x, int y) 
	{
		/*
		 * Essa classe deveria usar as constantes de Chess
		 * ou eu deveria deixar para a Screen fazer isso?
		 * 
		 * Se eu retirar essa constante daqui so vou precisar de uma funcao de desenhar
		 * 
		 * */
		desenhaPx(g, x*Chess.TILE_WIDTH, y*Chess.TILE_HEIGHT);
	}
	
	public void desenhaPx(Graphics g, int x, int y) 
	{
		g.drawImage(img, x, y, null);
	}
	
	public Integer[] move(int pecas[][],int x, int y) {
		return pecaMovimento.move(pecas, x, y);
	}
}
