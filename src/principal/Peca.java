package principal;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Peca
{	
	/*
	 * Peca guarda a imagem que sera desenhada no tabuleiro
	 */
	BufferedImage img;
	
	/*
	 * peca recebe o caminho relativo da imagem que deve guardar
	 * ler arquivos pode gerar um erro de Entrada e Saida (E/S) 
	 * 
	 */
	public Peca(String path) throws IOException
	{
		/*
		 * Eu geralmente uso a propria classe (no caso Peca), 
		 * mas poderia ser qualquer outra
		 * 
		 * A função getResource trata oo path para uma URL (outro caminho)
		 * que a funçao read consegue usar
		 */
		img = ImageIO.read(Peca.class.getResource(path));
	}
	
	/*
	 * Note que nao estamos usando paintComponent
	 * Essa classo nao extende de nenhuma outra
	 * Isso é porque queremos passar a localização da peca e a funcao
	 * paintComponent nao aceita mais argumentos.
	 * 
	 * Além disso, a peca que tem apenas uma instancia representar tantas no
	 * tabuleiro ja significa que essa classenão funciona como um componente
	 */
	public void desenha(Graphics g, int x, int y) 
	{
		/*
		 * mesmo assim usamos a funçao de desenhar de graphics que recebemos
		 * de screen
		 * 
		 * recebemos a posicao em tiles da matriz, por isso precisamos
		 * multiplicar para obter o tamanho em pixels
		 */
		g.drawImage(img, x*Chess.TILE_WIDTH ,y*Chess.TILE_HEIGHT , null);
	}
}
