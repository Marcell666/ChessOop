package movimento;

import java.util.List;

public class MTorre extends MoveBase implements IMove{
	
	
	@Override
	public List<Integer> move(int[][] pecas, int x, int y) {
		pos.clear();
		//norte
		adicionaVetor(pecas, x, y, 0,-1);
		//sul
		adicionaVetor(pecas, x, y, 0, 1);
		//oeste
		adicionaVetor(pecas, x, y, -1, 0);
		//leste
		adicionaVetor(pecas, x, y, 1, 0);
		
		return pos;
	}
	@Override
	public List<Integer> move(int[][] pecas, int x, int y, boolean left, boolean right) {
		return move(pecas, x, y);
	}
}
