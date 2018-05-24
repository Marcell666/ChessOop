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
		PRETAS_PROMOCAO
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
			System.out.printf("peca selecionada em %d,%d\n", x,y);
			if(pecas[y][x]>0) {
				
				state = GameState.BRANCAS_SELECIONADA;
				xSelecionada = x;
				ySelecionada = y;
			}
			break;
		case BRANCAS_PROMOCAO:
			break;
		case BRANCAS_SELECIONADA:
			if(pecas[y][x]==0) {
				state = GameState.BRANCAS;
				control.move(xSelecionada, ySelecionada, x, y);
				
			}
			break;
		case PRETAS:
			break;
		case PRETAS_PROMOCAO:
			break;
		case PRETAS_SELECIONADA:
			break;
		default:
			break;
		}
	}
}


