package movimento;

import java.util.ArrayList;
import java.util.List;

public class MSul implements IMove{

	@Override
	public Integer[] move(int[][] pecas, int x, int y) {
		int xPos = x;
		int yPos = y+1;
		//Esse é o peao que se move para baixo, entao começo uma casa abaixo
		//nao preciso avaliar a casa onde ele ja comeca
		int distancia = 1;
		List<Integer> pos = new ArrayList<Integer>();
		if(y == 1)
			distancia = 2;
		/*		Se eu estiver na primeira casa do peao preto
		 * 	Entao talvez possa mover duas casas
		 */
		
		for(int i = 0; i<distancia; i++, yPos++) {
			System.out.printf("Checando posicao %d,%d\n", x,y);
			if(pecas[yPos][xPos]==0) {
				System.out.printf("Adicionando posicao %d,%d\n", x,y);
				pos.add(xPos);
				pos.add(yPos);
			}
			else break;
		}
		/*
		 * TODO
		 * Para a tomada de pecas, sera necessario colocar aqui
		 * uma verificacao das casas diagonais adjacentes 
		 */
		
		return pos.toArray(new Integer[0]);
	}
}