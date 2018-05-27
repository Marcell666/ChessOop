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
		g.drawImage(img, x*Chess.TILE_WIDTH, y*Chess.TILE_HEIGHT, null);
	}
	
	public Integer[] move(int pecas[][],int x, int y) {
		return pecaMovimento.move(pecas, x, y);
	}
}
