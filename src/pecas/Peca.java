package pecas;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import movimento.IMove;
import principal.Chess;

public abstract class Peca
{		
	BufferedImage img;
	
	/*
	 * Objeto que contem a funcao de movimento que essa peca vai usar
	 */
	IMove pecaMovimento;
	
	/*
	 * Design Pattern: STRATEGY
	 * 
	 * A peca é uma classe abstrata, ela nao pode ser instanciada
	 * Ao inves disso vamos instanciar as filhas dela que vao ser
	 * cada uma das pecas do jogo.
	 * 
	 * 
	 * Cada peca possui uma instancia de um objeto que implementa 
	 * a interface imove.
	 * Cada classe que implementa a interface imove representa uma forma de
	 * se mover no tabuleiro
	 * 
	 * Assim, ao inves de polimorfismo para determinar o movimento das pecas, 
	 * estamos usando composição.
	 * 
	 * Ex:
	 * Eu instancio um cavaleiro
	 * Cavaleiro extende de peca,
	 * ele cria uma instancia de MCavaleiro e a guarda consigo.
	 * MCavaleiro implementa Imove
	 * portanto tem um metodo chamado move que foi implementado
	 * Esse metodo e chamado aqui quando um peca cavaleiro tiver que se mover
	 * da o metodo move de pecaMovimento e chamadado
	 * que por acaso é o metodo move de MCavaleiro
	 * 
	 * 
	 * 
	 * Veja o IMove e o Cavaleiro/MCavaleiro
	 * Veja o Peao/MSul/MNorte e a MRainha também
	 */
	
	
	public Peca(String path) throws IOException
	{
		img = ImageIO.read(Peca.class.getResource(path));
	}
	
	public void desenha(Graphics g, int x, int y) 
	{
		g.drawImage(img, x*Chess.TILE_WIDTH, y*Chess.TILE_HEIGHT, null);
	}
	/*
	 * retorna para quais lugares uma peca como esta poderia se mover
	 * 
	 * note que mesmo na classe abstrata, eu ja estou implementando a funcao
	 * movimento.
	 * Eu nao estou deixando uma assinatura em branco que para ser sobreescrita
	 * por uma classe filha. Isso seria polimorfismo.
	 * 
	 * Eu ja posso chamar o movimento aqui porque quem vai cuidar da implementacao
	 * do dele é objeto pecaMovimento
	 * 
	 * Note que eu usei uma classe Wrapper no retorno
	 * Isto é, estou retornando Integer[] e nao int[]
	 * Isso foi para deixar compativel com algumas funcoes la dentro
	 * Devo mexer nisso depois
	 * 
	 * Quando tiver que lidar com classes Wrapper tome cuidado com as conversões
	 */
	public Integer[] move(int pecas[][],int x, int y) {
		return pecaMovimento.move(pecas, x, y);
	}
}
