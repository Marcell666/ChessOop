package pecas;

import java.io.IOException;

import movimento.MCavaleiro;

public class Cavaleiro extends Peca{

	/*
	 * So o que a peca faz e criar e guardar um objeto do movimento correspondente
	 * apenas o peao e um pouco diferente
	 */
	
	public Cavaleiro(String path) throws IOException {
		super(path);
		pecaMovimento = new MCavaleiro();
	}

}
