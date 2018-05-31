package principal;

public class Control {
	
	Chess game;
	Judge judge;
	
	public Control(Chess game) {
		this.game = game;	
		judge = new Judge(this);
		
	}
	
	/*
	 * As duas funções abaixo simplesmente repassam os parametros de chess/screen para judge
	 * e de judge para chess/screen
	 * 
	 * A funçao click é chamada por screen quando clicamos no tabuleiro
	 * A função move é chamada por judge quando clicamos numa peca que queremos mover
	 */
	
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
}
