package movimento;

import java.util.ArrayList;
import java.util.List;

import principal.Chess;

public class MRei implements IMove {
	/*
	 * TODO/
	 * 		Mover essa funcao para outro lugar
	 * 
	 * */
	
	private boolean valida(int x,int y) {
		return x>=0 && x<Chess.QTD_TILES &&
				y>=0 && y<Chess.QTD_TILES;
	}
	
	@Override
	public Integer[] move(int[][] pecas, int x, int y) {

		List<Integer> pos = new ArrayList<Integer>();
		Integer posicoes[] = new Integer[]{
				x-1, y-1,
				x-1, y,
				x-1, y+1,
				x, y-1,
				x, y+1,
				x+1, y-1,
				x+1, y,
				x+1, y+1,
		};		

		for(int i=0; i<posicoes.length;i+=2) {
			if(valida(posicoes[i], posicoes[i+1]) && pecas[posicoes[i+1]][posicoes[i]] == 0) {
				pos.add(posicoes[i]);
				pos.add(posicoes[i+1]);
			}
		}
		
		return pos.toArray(new Integer[0]);
	}

}
