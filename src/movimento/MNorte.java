package movimento;

import java.util.ArrayList;
import java.util.List;

import principal.Chess;

public class MNorte implements IMove{

	@Override
	public Integer[] move(int[][] pecas, int x, int y) {
		int xPos = x;
		int yPos = y-1;
		int distancia = 1;
		List<Integer> pos = new ArrayList<Integer>();
		if(y == Chess.QTD_TILES - 2)
			distancia = 2;
		/*		Se eu estiver na primeira casa do peao branco
		 * 	Entao talvez possa mover duas casas
		 */
		
		for(int i = 0; i<distancia; i++, yPos--) {
			System.out.printf("Checando posicao %d,%d\n", xPos,yPos);
			
			if(pecas[yPos][xPos]==0) {
				System.out.printf("Adicionando posicao %d,%d\n", xPos,yPos);
				pos.add(xPos);
				pos.add(yPos);
			}
			else break;
		}
		return pos.toArray(new Integer[0]);
	}
}