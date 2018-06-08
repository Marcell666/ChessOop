package movimento;

public class MRainha implements IMove{

	@Override
	public Integer[] move(int[][] pecas, int x, int y) {
		/*
		 * O movimento da rainha é o movimento da torre e do bispo juntos
		 * entao podemos usar as classe que ja escrevemos e concatenar os resultados
		 */
		Integer moveCruz[] = new MTorre().move(pecas, x, y);
			//guardo o movimento da torre para esta casa
		Integer moveX[] = new MBispo().move(pecas, x, y);
			//guardo o movimento do bispo para essa casa
		Integer ret[] = new Integer[moveCruz.length+moveX.length];
			//crio um vetor com o tamanho correto
		int i;
		//concateno
		for(i=0;i<moveCruz.length;i++)
			ret[i] = moveCruz[i];
		for(int e=0; e<moveX.length;e++)
			ret[i+e] = moveX[e];
		
		return ret;
	}
	
}