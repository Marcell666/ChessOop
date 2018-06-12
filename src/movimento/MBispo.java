package movimento;

public class MBispo extends MoveBase implements IMove {
		
	@Override
	public Integer[] move(int[][] pecas, int x, int y) {
		pos.clear();
		
		//Nordeste
		adicionaVetor(pecas, x, y, 1,-1);
		//Noroeste
		adicionaVetor(pecas, x, y, -1,-1);
		//Sudoeste
		adicionaVetor(pecas, x, y, -1, 1);
		//leste
		adicionaVetor(pecas, x, y, 1,1);
		
		return pos.toArray(new Integer[0]);
	}

}
