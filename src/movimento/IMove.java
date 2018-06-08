package movimento;

/*
 * Esta interface so tem uma funcao, que deve ser implementada pelas classe que representam o movimento
 * assim posso chamar de peca a função move(), e dependendo do objeto que instanciei, uma função diferente sera chamada
 */

public interface IMove {
	Integer[] move(int pecas[][], int x, int y);
}