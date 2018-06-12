package rules;

import principal.Chess;

public class Control {
	
	Chess game;
	private Judge judge;
	
	public Control(Chess game) {
		this.game = game;	
		judge = new Judge(this);
		
	}
	
	/*
	 * Recebe x e y como posicao na matriz
	 * nao como posicao em pixels
	 * 
	 * Avisa ao judge que o jogador clicou numa posição
	 * */
	public void click(int pecas[][],int x, int y) {
		judge.click(pecas, x, y);
	}

	/*
	 * resposta do judge, uma peca deve ser movida de uma posição para outra
	 */
	public void move(int xSelecionada, int ySelecionada, int x, int y) {
		game.movePeca(xSelecionada, ySelecionada, x, y);
	}

	public boolean isSelecionada() {
		return judge.isSelecionada();
	}

	public int getXSelecionada() {
		return judge.xSelecionada;
	}
	
	public int getYSelecionada() {
		return judge.ySelecionada;
	}
	
	public Integer[] getPos() {
		return judge.pos;
	}

	public Integer[] getMovimentoPeca(int x, int y) {
		// TODO Auto-generated method stub
		return game.getMovimentoPeca(x, y);
	}

	public boolean isPeao(int x, int y) {
		return game.isPeao(x,y);
	}

	public void promove(int x, int y) {
		game.promove(x,y);
	}
	
}
