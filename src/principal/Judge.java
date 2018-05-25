package principal;

public class Judge {
	
	/*   RECEBE UM OBJETO DO TIPO MOVE
	 *   para tratar a movimentacao das pecas*/
	Control control;
	
	private enum GameState{
		BRANCAS,
		PRETAS,
		BRANCAS_SELECIONADA,
		PRETAS_SELECIONADA,
		BRANCAS_PROMOCAO,
		PRETAS_PROMOCAO,
	}
	
	GameState state;
	int xSelecionada, ySelecionada;
	
	public Judge(Control control) {
		this.control = control;
		state = GameState.BRANCAS;
	}
	
	public void click(int pecas[][], int x, int y) {
		switch(state) {
		case BRANCAS:
			if(pecas[y][x]>0) {
				System.out.printf("peca selecionada em %d,%d\n", x,y);				
				state = GameState.BRANCAS_SELECIONADA;
				xSelecionada = x;
				ySelecionada = y;
				//TODO funcao que avisa a tela que uma peca foi selecionada
			}
		case BRANCAS_PROMOCAO:
			break;
		case BRANCAS_SELECIONADA:
			if(pecas[y][x]==0) {
				state = GameState.PRETAS;
				
				control.move(xSelecionada, ySelecionada, x, y);
			}
			else {
				state = GameState.BRANCAS;
				//TODO funcao que avisa a tela que a peca nao esta mais selecionada
			}
			break;
		case PRETAS:
			if(pecas[y][x]<0) {
				System.out.printf("peca selecionada em %d,%d\n", x,y);				
				state = GameState.PRETAS_SELECIONADA;
				xSelecionada = x;
				ySelecionada = y;
			}
			break;
		case PRETAS_PROMOCAO:
			break;
		case PRETAS_SELECIONADA:
			if(pecas[y][x]==0) {
				state = GameState.BRANCAS;
				control.move(xSelecionada, ySelecionada, x, y);
			}
			else {
				state = GameState.PRETAS;
				//TODO funcao que avisa a tela que a peca nao esta mais selecionada
			}
			break;
		default:
			break;
		}
	}

	public boolean isSelecionada() {
		return state == GameState.BRANCAS_SELECIONADA || state == GameState.PRETAS_SELECIONADA;
	}
}


