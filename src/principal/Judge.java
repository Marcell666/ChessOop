package principal;

public class Judge {
	
	
	Control control;
	
	/*
	 * Um enumeravel é a forma mais correta de se criar constantes
	 * Esta representa os diferentes estados do jogo
	 * Assim podemos tomar uma decisão baseado no que o usuario fez
	 * e no estado atual do jogo (isso é, o que o usuario vem fazendo)
	 * 
	 */
	private enum GameState{
		BRANCAS,
		PRETAS,
		BRANCAS_SELECIONADA,
		PRETAS_SELECIONADA,
		BRANCAS_PROMOCAO,
		PRETAS_PROMOCAO
	}
	
	GameState state;
	
	/*
	 * Essas variaveis guardam quem foi a ultima peca clicada, e portanto selecionada
	 */
	int xSelecionada, ySelecionada;
	
	public Judge(Control control) {
		this.control = control;
		state = GameState.BRANCAS;
	}
	
	public void click(int pecas[][], int x, int y) {
		
		/*
		 * cada case do switch representa um estado do jogo e sua devida
		 * reacao ao clique
		 * 
		 * verificamos na matriz de pecas, que chegou ate de
		 * screen-> pediu de game->control->judge
		 * qual é a peca que esta la, e se faz sentido clicar nela agora
		 */
		
		
		switch(state) {
		case BRANCAS:
			if(pecas[y][x]>0) {

				/*
				 * se esta na vez das brancas e clicamos numa peca branca
				 * guardamos a posicao e mudamos o estado, ainda é a vez das brancas
				 * mas agora uma peca foi selecionada
				 */
				
				System.out.printf("peca selecionada em %d,%d\n", x,y);				
				state = GameState.BRANCAS_SELECIONADA;
				xSelecionada = x;
				ySelecionada = y;
			}
			break;
		case BRANCAS_PROMOCAO:
			break;
		case BRANCAS_SELECIONADA:
			if(pecas[y][x]==0) {
				/*
				 * Se uma peca foi selecionada e clicamos agora num lugar vazio
				 * voltamos ao estado no qual o jogador seleciona uma peca
				 * 
				 * e pedimos ao controle para informar ao jogo que uma peca deve ser movida
				 */
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


