package pecas;

import java.io.IOException;

import movimento.MNorte;
import movimento.MSul;

public class Peao extends Peca {
	
	public Peao(String path, boolean corDaPeca) throws IOException {
		super(path);
		if(corDaPeca == BRANCA)
			pecaMovimento = new MNorte();
		else
			pecaMovimento = new MSul();
	}

}
