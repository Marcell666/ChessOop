package movimento;

public class MRainha implements IMove{

	@Override
	public Integer[] move(int[][] pecas, int x, int y) {
		Integer moveCruz[] = new MTorre().move(pecas, x, y);
		Integer moveX[] = new MBispo().move(pecas, x, y);
		Integer ret[] = new Integer[moveCruz.length+moveX.length];
		int i;
		for(i=0;i<moveCruz.length;i++)
			ret[i] = moveCruz[i];
		for(int e=0; e<moveX.length;e++)
			ret[i+e] = moveX[e];
		return ret;
	}
	
}