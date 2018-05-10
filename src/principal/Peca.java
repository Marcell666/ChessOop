package principal;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Peca extends JPanel{
	BufferedImage img;
	int x, y;
	public Peca(String path, int x, int y) throws IOException{
		img = ImageIO.read(new File(path));
		this.x = x;
		this.y = y;
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(img, x*40, y*40, null);
	}
}
