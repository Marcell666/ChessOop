package movimento;

import java.util.LinkedList;
import java.util.List;

import principal.Chess;

public abstract class MoveBase{
	
	/*
	 * Usada para guardar as posicoes que seram retornar pela funcao move
	 * Essa variavel sera usada divesas vezes pelas diferentes classe filha, entao nao queremos cria-la mais de uma vez em uma funçao (1)
	 *  mas nao sera usada mais de uma ao mesmo tempo. (2)
	 * E os elementos vao ser trocados a cada vez que uma peca se mover. (3)
	 * Acesso aleatorio nao é necessario, uma vez que o juiz precise percorrer todos as posicoes para as quais uma peca pode se mover (4)
	 * 
	 * Portanto ela deve criada aqui (1) deve ser static (2) e alocada como linked list(3 e 4)
	 * 
	 */
	protected static List<Integer> pos = new LinkedList<Integer>();
	
	/**
	 * Verifica se a posicao indicada esta dentro dos limites do tabuleiro
	 * Utiliza as constantes definidas em Chess
	 * 
	 * @return true se a posicao esta dentro do tabuleiro
	 * 
	 */
	protected boolean valida(int x,int y) {
		return x>=0 && x<Chess.QTD_TILES &&
				y>=0 && y<Chess.QTD_TILES;
	}
	/**
	 * <p>Para fazer a tomada de pecas, é preciso que a peca seja capaz de andar ate uma peca inimiga, mas nao passar dela.<br>
	 * Para descobrir a cor da peca que esta se movendo, vamos ate ela no tabuleiro e verificamos o sinal dela.<br>
	 * Uma peca <b>a</b> é do mesmo time <b>b</b> se o sinal de <b>a*b > 0</b><br>
	 * Se forem de times diferentes então o <b>a*b < 0</b>
	 * 
	 * @return true se as pecas A e B sao de cores diferentes
	 * <br>true se em um dos lugares nao ha uma peca
	 * <br>false se as pecas sao da mesma cor
	 */
	protected boolean validaCor(int pecaA, int pecaB) {
		return pecaA*pecaB<=0;
	}
	
	/**
	 * <p>Adiciona uma linha/coluna/diagonal de posições a lista fornecida
	 * Para de adicionar quando encontra uma peca inimiga e aidiona a peca
	 * Ou quando encontra uma peca amiga e nao adiciona a peca
	 * Ou quando passa do tabuleiro<br>
	 * 
	 * <p>A ideia e semelhante a de um vetor. Comeca em uma posicao e avanca conforme xDelta e yDelta fornecidos
	 * 
	 * @param pos Lista as posiçoes serao adicionadas
	 * @param pecas matriz com as pecas em suas devidas posicoes
	 * @param x posicao na matriz onde se deve comecar
	 * @param y posicao na matriz onde se deve comecar
	 * @param xDelta coeficiente x do vetor que avanca no tabuleiro 
	 * @param yDelta coeficiente x do vetor que avanca no tabuleiro
	 */
	/**
	 * @param pecas tabuleiro onde ficam as pecas
	 * @param y posicao da peca no tabuleiro
	 * @param x posicao da peca no tabuleiro
	 * @return true se a peca for branca
	 */
	protected boolean getCor(int pecas[][], int x, int y) {
		return pecas[y][x] > 0;
	}
	
	protected void adicionaVetor(int pecas[][], int x, int y, int xDelta, int yDelta) {
		adicionaVetor(pecas, x, y, xDelta, yDelta, Chess.QTD_TILES);
	}
	
	protected void adicionaVetor(int pecas[][], int x, int y, int xDelta, int yDelta, int distancia){
		int xPos = x+xDelta;
		int yPos = y+yDelta;
		for(int i = 1; i<=distancia; i++) {
			/*
			 * Se esta dentro do tabuleiro
			 * 		E esta é um lugar vazio
			 * 			=> entao ok
			 * 		Ou tem uma peca inimiga la
			 * 			=> entao ok
			 * 		Se de fato tem um peca inimiga la
			 * 			=> sai do loop
			 */
			
			if(valida(xPos, yPos) && (pecas[yPos][xPos]==0 || validaCor(pecas[y][x],pecas[yPos][xPos]))) {
				pos.add(xPos);
				pos.add(yPos);
				if(pecas[yPos][xPos]!=0 && validaCor(pecas[y][x],pecas[yPos][xPos]))
					return;
			}
			else return;
			xPos+=xDelta;
			yPos+=yDelta;
		}
	}
	protected void inicializa() {
		pos.clear();
	}
	protected List<Integer> finaliza() {
		return new LinkedList<Integer>(pos);
	}
}
