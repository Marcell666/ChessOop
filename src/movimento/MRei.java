package movimento;

import java.util.List;

public class MRei extends MoveBase implements IMove {
	
	private boolean eDaMesmaCor(int pecasTabuleiro[][], int xa, int ya, int xb, int yb) {
		return pecasTabuleiro[ya][xa] * pecasTabuleiro[yb][xb] > 0;
	}
	
	boolean validaRoqueDireita(int pecas[][], int x, int y) {
		if (!valida(x+1,y)) return false;
		if (!valida(x+2,y)) return false;
		if (!valida(x+3,y)) return false;
		return  pecas[y][x+1] == 0 && pecas[y][x+2] == 0 && eDaMesmaCor(pecas, x, y, x+3,y);
	}
	boolean validaRoqueEsquerda(int pecas[][], int x, int y) {
		if (!valida(x-1,y)) return false;
		if (!valida(x-2,y)) return false;
		if (!valida(x-3,y)) return false;
		if (!valida(x-4,y)) return false;
		return  pecas[y][x-1] == 0 && pecas[y][x-2] == 0 && pecas[y][x-3] == 0 && eDaMesmaCor(pecas, x, y, x-4, y);
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
