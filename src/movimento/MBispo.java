package movimento;

import java.util.List;

public class MBispo extends MoveBase implements IMove {
		
	@Override
	public List<Integer> move(int[][] pecas, int x, int y) {
		pos.clear();
		
		//Nordeste
		adicionaVetor(pecas, x, y, 1,-1);
		//Noroeste
		adicionaVetor(pecas, x, y, -1,-1);
		//Sudoeste
		adicionaVetor(pecas, x, y, -1, 1);
		//leste
		adicionaVetor(pecas, x, y, 1,1);
		
		return pos;
	}

	@Override
	public List<Integer> move(int[][] pecas, int x, int y, boolean left, boolean right) {
		return move(pecas, x, y);
	}

}
