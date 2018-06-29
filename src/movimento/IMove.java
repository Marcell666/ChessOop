package movimento;

import java.util.List;

/*
 * Esta interface so tem uma funcao, que deve ser implementada pelas classe que representam o movimento
 * assim posso chamar de peca a função move(), e dependendo do objeto que instanciei, uma função diferente sera chamada
 */

public interface IMove {
	List<Integer> move(int pecas[][], int x, int y, boolean left, boolean right);
			//Usado exclusivamente para o rei
			//deve haver uma forma de generalizar esta exce��o
								
	List<Integer> move(int pecas[][], int x, int y);
}