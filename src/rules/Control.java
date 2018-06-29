package rules;

import java.util.Observable;
import java.util.Observer;

import principal.Chess;

public class Control {
	
	private static Control control;
	Chess game;
	private static Judge judge;
	
	private Control() {};
	
	public static Control getInstance() {
		if(control == null) {
			control = new Control();
			if(judge == null)
				judge = new Judge(control);
		}
		
		return control;
	}
	
	public Control setGame(Chess game) {
		this.game = game;
		return this;
	}
	
	public void registra(Observer o) {
		judge.addObserver(o);
		judge.notifyObservers();
	}
	
	/*
	 * Recebe x e y como posicao na matriz
	 * nao como posicao em pixels
	 * 
	 * Avisa ao judge que o jogador clicou numa posição
	 * */
	public void click(int x, int y) {
		judge.click(x, y);
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
		return judge.pos.toArray(new Integer[0]);
	}

	public void salvar() {
		judge.salvar();
	}
	public void carregar() {
		judge.carregar();
	}
	
}
