package pecas;

import java.io.IOException;

import movimento.MRei;

public class Rei extends Peca{

	public Rei(String path) throws IOException {
		super(path);
		pecaMovimento = new MRei();
	}

}
