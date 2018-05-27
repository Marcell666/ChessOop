package pecas;

import java.io.IOException;

import movimento.MBispo;

public class Bispo extends Peca{

	public Bispo(String path) throws IOException {
		super(path);
		pecaMovimento = new MBispo();
	}

}
