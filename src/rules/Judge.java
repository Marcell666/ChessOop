package rules;

public class Judge {
	
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
	/*
	 * O vetor pos guarda as posiçoes para onde pode ir a ultima peca selecionada
	 * note que a forma como as posições esta guardada é em pares seguidos
	 * [x1,y1,x2,y2,x3,y3,...]
	 * entao para percorrer é preciso avancar de dois em dois
	 * 
	 * nao é preciso se preocupar com o conteudo do vetor quando nenhuma peca esta selecionada
	 * so vamos pedir o vetor quando o estado do jogo indicar que uma peca
	 * esta selecionada
	 */
	Integer[] pos;
	
	public Judge(Control control) {
		this.control = control;
		state = GameState.BRANCAS;
	}
	
	public void click(int pecas[][], int x, int y) {
		switch(state) {
		case BRANCAS:
			if(pecas[y][x]>0) {
				//System.out.printf("peca selecionada em %d,%d\n", x,y);				
				state = GameState.BRANCAS_SELECIONADA;
				xSelecionada = x;
				ySelecionada = y;
				pos = control.getMovimentoPeca(x , y);
			}
		case BRANCAS_PROMOCAO:
			break;
		case BRANCAS_SELECIONADA:
			if(pecas[y][x]==0 && validaPos(pos, x, y)) {
				state = GameState.PRETAS;
				
				control.move(xSelecionada, ySelecionada, x, y);
			}
			else {
				state = GameState.BRANCAS;
			}
			break;
		case PRETAS:
			if(pecas[y][x]<0) {
				//System.out.printf("peca selecionada em %d,%d\n", x,y);				
				state = GameState.PRETAS_SELECIONADA;
				xSelecionada = x;
				ySelecionada = y;
				pos = control.getMovimentoPeca(x , y);
			}
			break;
		case PRETAS_PROMOCAO:
			break;
		case PRETAS_SELECIONADA:
			if(pecas[y][x]==0 && validaPos(pos, x, y)) {
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
	
	/*
	 * Verifica se a possição passada esta no vetor de possicoes
	 */
	private boolean validaPos(Integer pos[], int x, int y) {
		for(int i=0; i<pos.length;i+=2) {
			if(pos[i] == x && pos[i+1] == y)
				return true;
		}
		return false;
	}

	public boolean isSelecionada() {
		return state == GameState.BRANCAS_SELECIONADA || state == GameState.PRETAS_SELECIONADA;
	}
}


