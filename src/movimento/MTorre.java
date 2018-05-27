package movimento;

import java.util.ArrayList;
import java.util.List;

import principal.Chess;

public class MTorre implements IMove{
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
		int xPos = x;
		int yPos = y;
		int distancia = Chess.QTD_TILES;
		List<Integer> pos = new ArrayList<Integer>();
		/*		Se eu estiver na primeira casa do peao preto
		 * 	Entao talvez possa mover duas casas
		 */
		//norte
		xPos = x;
		yPos=y-1;
		for(int i = 1; i<distancia; i++, yPos--) {
			if(valida(xPos, yPos) && pecas[yPos][xPos]==0) {
				pos.add(xPos);
				pos.add(yPos);
			}
			else break;
		}
		
		//sul
		xPos = x;
		yPos=y+1;
		for(int i = 1; i<distancia; i++, yPos++) {
			if(valida(xPos, yPos) && pecas[yPos][xPos]==0) {
				pos.add(xPos);
				pos.add(yPos);
			}
			else break;
		}
		
		//oeste
		xPos=x-1;
		yPos = y;
		for(int i = 1; i<distancia; i++, xPos--) {
			if(valida(xPos, yPos) && pecas[yPos][xPos]==0) {
				pos.add(xPos);
				pos.add(yPos);
			}
			else break;
		}

		//leste
		xPos=x+1;
		yPos = y;
		for(int i = 1; i<distancia; i++, xPos++) {
			if(valida(xPos, yPos) && pecas[yPos][xPos]==0) {
				pos.add(xPos);
				pos.add(yPos);
			}
			else break;
		}
		return pos.toArray(new Integer[0]);
	}

}
