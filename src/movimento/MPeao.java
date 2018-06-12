package movimento;

import principal.Chess;

public class MPeao extends MoveBase implements IMove{
	
	@Override
	protected void adicionaVetor(int pecas[][], int x, int y, int xDelta, int yDelta, int distancia){
		int xPos = x+xDelta;
		int yPos = y+yDelta;
		for(int i = 1; i<=distancia; i++) {
			/*
			 * TODO generalizar todas as malditas exceções do peao
			 * O peao nao captura para frente, por isso essa funcao esta copiada
			 */			
			
			if(valida(xPos, yPos) && pecas[yPos][xPos]==0) {
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
	
	@Override
	public Integer[] move(int[][] pecas, int x, int y) {
		int xPos;
		int yPos;
		int distancia = 1;
		pos.clear();		
		/*		Se eu estiver na primeira casa do peao branco
		 * 	Entao talvez possa mover duas casas
		 */
		if(getCor(pecas, x, y) && y == Chess.QTD_TILES - 2)
			distancia = 2;
		else if(!getCor(pecas, x, y) && y == 1)
			distancia = 2;
		
		adicionaVetor(pecas, x, y, 0, getCor(pecas, x, y) ? -1 : 1, distancia);
		//O peao nao captura para frente...
		
		/*
		 * 
		 * O pecao se move nas diagonais uma casa, se houver uma peca la que ele possa capturar
		 * 
		 * 	  X |    |  X  
		 * 	--------------  
		 *      | P  | 
		 *  --------------  
		 *    X |    |  X
		 *    
		 * O peao branco pode atacar os locais acima dele
		 * E o preto os abaixo dele.
		 * Nenhum pode atacar para tras, somente para frente
		 * 
		 * Entao se o peao for preto se precisamos mudar o y e manter o x
		 * Fazermos isso nas tres linhas abaixo
		 */
		
		yPos = y+1;
		if(getCor(pecas, x, y))
			yPos = y-1;
		
		/*
		 * Entao verificamos a casa a esquerda e direita, nao importa o y
		 */
		
		xPos = x-1;
		if(valida(xPos, yPos) && pecas[yPos][xPos] != 0 && validaCor(pecas[y][x], pecas[yPos][xPos]))
		{
			pos.add(xPos);
			pos.add(yPos);
		}
		xPos = x+1;
		if(valida(xPos, yPos) && pecas[yPos][xPos] != 0 && validaCor(pecas[y][x], pecas[yPos][xPos]))
		{
			pos.add(xPos);
			pos.add(yPos);
		}
		
		return pos.toArray(new Integer[0]);
	}
}