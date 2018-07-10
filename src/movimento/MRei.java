package movimento;

import java.util.List;

public class MRei extends MoveBase implements IMove {
	
	boolean validaRoqueDireita(int pecas[][], int x, int y) {
		return  pecas[y][x+1] == 0 && pecas[y][x+2] == 0;
	}
	boolean validaRoqueEsquerda(int pecas[][], int x, int y) {
		return  pecas[y][x-1] == 0 && pecas[y][x-2] == 0 && pecas[y][x-3] == 0;
	}
	
	@Override
	public List<Integer> move(int[][] pecas, int x, int y) {
		return move(pecas, x, y, false, false);
	}
	
	@Override
	public List<Integer> move(int[][] pecas, int x, int y, boolean left, boolean right) {
		inicializa();

		adicionaVetor(pecas, x, y, -1,-1, 1);
		adicionaVetor(pecas, x, y, -1, 0, 1);
		adicionaVetor(pecas, x, y, -1,+1, 1);
		adicionaVetor(pecas, x, y,  0,-1, 1);
		adicionaVetor(pecas, x, y,  0,+1, 1);
		adicionaVetor(pecas, x, y, +1,-1, 1);
		adicionaVetor(pecas, x, y, +1, 0, 1);
		adicionaVetor(pecas, x, y, +1,+1, 1);
		if(left && validaRoqueEsquerda(pecas, x, y)) {
			pos.add(x-3);
			pos.add(y);
		}
		if(right && validaRoqueDireita(pecas, x, y)) {
			pos.add(x+2);
			pos.add(y);
		}
		return finaliza();
	}

}
