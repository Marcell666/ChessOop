package movimento;

import java.util.List;

public class MRainha extends MoveBase implements IMove{

	@Override
	public List<Integer> move(int[][] pecas, int x, int y) {
		inicializa();
		List<Integer> lstTorre;
		List<Integer> lstBispo;
		/*
		 * O movimento da rainha é o movimento da torre e do bispo juntos
		 * entao podemos usar as classe que ja escrevemos e concatenar os resultados
		 */
		
		lstTorre = new MTorre().move(pecas, x, y);
		lstBispo = new MBispo().move(pecas, x, y);
		pos.addAll(lstTorre);
		pos.addAll(lstBispo);
	//	pos = new MBispo().move(pecas, x, y);
		return finaliza();
	}
	@Override
	public List<Integer> move(int[][] pecas, int x, int y, boolean left, boolean right) {
		return move(pecas, x, y);
	}
}