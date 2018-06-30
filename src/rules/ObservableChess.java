package rules;

import java.util.Observable;

import pecas.Peca;

public abstract class ObservableChess extends Observable{
	public abstract Integer[] getPos();
	public abstract int[][] getPecas();
	public abstract Peca[] getPecasBrancas();
	public abstract Peca[] getPecasPretas();
	public abstract boolean isSelecionada();
	public abstract int getXSelecionada();
	public abstract int getYSelecionada();
}