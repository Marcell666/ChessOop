package pecas;

import java.io.IOException;

import movimento.MTorre;

public class Torre extends Peca{

	public Torre(String path) throws IOException {
		super(path);
		pecaMovimento = new MTorre();
	}

}
