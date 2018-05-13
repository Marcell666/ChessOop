package principal;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Peca extends JPanel{
	BufferedImage img;
	public Peca(String path, int x, int y) throws IOException{
		img = ImageIO.read(new File(path));
		setBounds(x*40 + 4,y*40,40,40);
		setOpaque(false);
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(img, 0, 0, null);
	}
}
