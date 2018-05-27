package rules;

import principal.Chess;

public class Control {
	
	Chess game;
	private Judge judge;
	
	public Control(Chess game) {
		this.game = game;	
		judge = new Judge(this);
		
	}
	
	/**
	 * @param x e do tile
	 * @param y e do tile
	 * 		Recebe x e y como posicao na matriz
	 * nao como posicao em pixels
	 * */
	public void click(int pecas[][],int x, int y) {
		judge.click(pecas, x, y);
	}

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
}
