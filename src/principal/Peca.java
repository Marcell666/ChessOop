package principal;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Peca
{	
	BufferedImage img;
	
	public Peca(String path) throws IOException
	{
		img = ImageIO.read(Peca.class.getResource(path));
	}
	
	public void desenha(Graphics g, int x, int y) 
	{
		g.drawImage(img, x*Chess.TILE_WIDTH ,y*Chess.TILE_HEIGHT , null);
	}
}
