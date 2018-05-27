package pecas;

import java.io.IOException;

import movimento.MRainha;

public class Rainha extends Peca{

	public Rainha(String path) throws IOException {
		super(path);
		pecaMovimento = new MRainha();
	}

}
