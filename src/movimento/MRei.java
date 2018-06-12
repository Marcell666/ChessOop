package movimento;

public class MRei extends MoveBase implements IMove {
	
	
	@Override
	public Integer[] move(int[][] pecas, int x, int y) {
		pos.clear();

		adicionaVetor(pecas, x, y, -1,-1, 1);
		adicionaVetor(pecas, x, y, -1, 1, 1);
		adicionaVetor(pecas, x, y, -1,+1, 1);
		adicionaVetor(pecas, x, y,  1,-1, 1);
		adicionaVetor(pecas, x, y,  1,+1, 1);
		adicionaVetor(pecas, x, y, +1,-1, 1);
		adicionaVetor(pecas, x, y, +1, 1, 1);
		adicionaVetor(pecas, x, y, +1,-1, 1);
		
		return pos.toArray(new Integer[0]);
	}

}
