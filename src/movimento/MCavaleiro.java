package movimento;

import java.util.ArrayList;
import java.util.List;

import principal.Chess;

public class MCavaleiro implements IMove {

	/*
	 * TODO
	 * 		Mover essa funcao para outro lugar
	 * 		Essa função de validacao esta sendo usada em algumas partes do codigo
	 * 		talvez devesse ser um função auxiliar statica em alguma dessas classes
	 * 
	 * 
	 * */
	
	private boolean valida(int x,int y) {
		return x>=0 && x<Chess.QTD_TILES &&
				y>=0 && y<Chess.QTD_TILES;
	}
	
	@Override
	public Integer[] move(int[][] pecas, int x, int y) {

		/*
		 * Eu devo melhorar essas funçoes depois
		 * 
		 * A variavel pos é uma arrayList de Integer,
		 * ArrayList é um vetor com alguns recursos extra
		 * E Integer é um inteiro com alguns recursos extra
		 * 
		 * O vetor posicoes contem a localizacao de todos as casas para as quais o cavalo iria se movimentar
		 * se estivesse na coordenada recebida
		 */
		List<Integer> pos = new ArrayList<Integer>();
		Integer posicoes[] = new Integer[]{
				x-2, y-1,
				x-2, y+1,
				x-1, y-2,
				x-1, y+2,
				x+1, y-2,
				x+1, y+2,
				x+2, y-1,
				x+2, y+1,
		};		

		/*
		 * Percorremos as coordenadas inicialmente validas
		 * e verificamos so o movimento é de fato possivel
		 * pode ser que tenha alguem no caminho, tenha uma 
		 * peca da mesma cor la, ou que esteja fora do tabuleiro
		 * Se estiver tudo ok, entao colocamos aquela posicao na ArrayList
		 */
		
		for(int i=0; i<posicoes.length;i+=2) {
			if(valida(posicoes[i], posicoes[i+1]) && pecas[posicoes[i+1]][posicoes[i]] == 0) {
				pos.add(posicoes[i]);
				pos.add(posicoes[i+1]);
			}
		}
		
		/*
		 * Um dos recursos que a ArrayList tem é a de criar um vetor e retornar com o mesmo conteudo
		 * Mas é preciso dizer o tipo do conteudo, e por isso passamos um vetor com zero posicoes deste mesmo tipo
		 * Se passarmos um vetor com espaco suficiente, ele sera preenchido pela funcao
		 */
		return pos.toArray(new Integer[0]);
	}

}
