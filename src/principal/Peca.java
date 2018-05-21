package principal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class Peca extends JComponent{
	BufferedImage img;
	public Peca(String path, int x, int y) throws IOException{
		img = ImageIO.read(Peca.class.getResource(path));
		setBounds(x*40 + 4,y*40,550,400);
		setOpaque(false);
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(img, 0, 0, null);
	}
	
	public void desenha(Graphics g, int x, int y) {
		g.drawImage(img, x*40 + 4,y*40, null);
		
	}
}
