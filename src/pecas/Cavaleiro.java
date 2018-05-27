package pecas;

import java.io.IOException;

import movimento.MCavaleiro;

public class Cavaleiro extends Peca{

	public Cavaleiro(String path) throws IOException {
		super(path);
		pecaMovimento = new MCavaleiro();
	}

}
