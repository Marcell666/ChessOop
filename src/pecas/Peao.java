package pecas;

import java.io.IOException;

import movimento.MNorte;
import movimento.MSul;

public class Peao extends Peca {
	
	/*
	 * Note que eu alterei o construtor, o peao precisa saber qual a cor dele
	 * pois os peaos de cores diferentes so se movem em direções opostas
	 * 
	 * saber a posição inicial tambem poderia resolver.
	 */
	
	public Peao(String path, boolean corDaPeca) throws IOException {
		super(path);
		if(corDaPeca == BRANCA)
			pecaMovimento = new MNorte();
		else
			pecaMovimento = new MSul();
	}

}
