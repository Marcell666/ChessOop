package pecas;

import java.io.IOException;
import java.util.List;

import movimento.MRei;

public class Rei extends Peca{

	static private boolean torreBrancaEsquerda;
	static private boolean torreBrancaDireita;
	static private boolean torrePretaEsquerda;
	static private boolean torrePretaDireita;
	
	public Rei(String path) throws IOException {
		super(path);
		torreBrancaEsquerda = true;
		torreBrancaDireita = true;
		torrePretaEsquerda = true;
		torrePretaDireita = true;
		pecaMovimento = new MRei();
	}
	
	/*
	 * Avisando que uma peca rei foi mudada de lugar, atualizar as flags.
	 * */
	public static void setTorreBrancaEsquerda(boolean b) {
		torreBrancaEsquerda = b;
	}
	public static void setTorreBrancaDireita(boolean b) {
		torreBrancaDireita = b;			
	}
	public static void setTorrePretaEsquerda(boolean b) {
		torrePretaEsquerda = b;
	}
	public static void setTorrePretaDireita(boolean b) {
		torrePretaDireita= b;
	}
	
	public static boolean getTorreBrancaEsquerda() {
		return torreBrancaEsquerda;
	}
	public static boolean getTorreBrancaDireita() {
		return torreBrancaDireita;			
	}
	public static boolean getTorrePretaEsquerda() {
		return torrePretaEsquerda;
	}
	public static boolean getTorrePretaDireita() {
		return torrePretaDireita;
	}
	
	@Override
	public List<Integer> move(int[][] pecas, int x, int y) {
		if(pecas[y][x]>0)
			return pecaMovimento.move(pecas, x, y, torreBrancaEsquerda, torreBrancaDireita);
		else
			return pecaMovimento.move(pecas, x, y, torrePretaEsquerda, torrePretaDireita);
	}
}