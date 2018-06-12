package movimento;

public class MTorre extends MoveBase implements IMove{
	
	
	@Override
	public Integer[] move(int[][] pecas, int x, int y) {
		pos.clear();
		//norte
		adicionaVetor(pecas, x, y, 0,-1);
		//sul
		adicionaVetor(pecas, x, y, 0, 1);
		//oeste
		adicionaVetor(pecas, x, y, -1, 0);
		//leste
		adicionaVetor(pecas, x, y, 0, 1);
		
		return pos.toArray(new Integer[0]);
	}

}
