package pecas;

import java.io.IOException;

import movimento.MPeao;

public class Peao extends Peca {
	
	/*
	 * Note que eu alterei o construtor, o peao precisa saber qual a cor dele
	 * pois os peaos de cores diferentes so se movem em direções opostas
	 * 
	 * saber a posição inicial tambem poderia resolver.
	 */
	
	public Peao(String path) throws IOException {
		super(path);
		pecaMovimento = new MPeao();
	}

}
