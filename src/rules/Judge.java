package rules;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Observer;
import java.util.function.UnaryOperator;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import pecas.Bispo;
import pecas.Cavaleiro;
import pecas.Peao;
import pecas.Peca;
import pecas.Rainha;
import pecas.Rei;
import pecas.Torre;
import principal.Chess;
import principal.SalvarJogo;

public class Judge extends ObservableChess{
	
	Control control;
	
	private enum GameState{
		BRANCAS,
		PRETAS,
		BRANCAS_SELECIONADA,
		PRETAS_SELECIONADA
	}
	
	GameState state;
	int xSelecionada, ySelecionada;
	
	List<Integer> jogadaAnteriorBranca;
	List<Integer> jogadaAnteriorPreta;
	int repeticoes;
	
	/*
	 * 0 = casa vazia
	 * 1 = rei
	 * 2 = rainha
	 * 3 = bispo
	 * 4 = cavaleiro
	 * 5 = torre
	 * 6 = peao
	 * 
	 * negativo = pecas pretas
	 * */
	private int pecas[][] =  new int[][]
			{
				{-5,-4,-3,-2,-1,-3,-4,-5},
				{-6,-6,-6,-6,-6,-6,-6,-6},
				{ 0, 0, 0, 0, 0, 0, 0, 0},
				{ 0, 0, 0, 0, 0, 0, 0, 0},
				{ 0, 0, 0, 0, 0, 0, 0, 0},
				{ 0, 0, 0, 0, 0, 0, 0, 0},
				{ 6, 6, 6, 6, 6, 6, 6, 6},
				{ 5, 4, 3, 2, 1, 3, 4, 5}
			};
	private int pecasEspelho[][];
	
	private Peca pecasBrancas[];
	private Peca pecasPretas[];
	
	//Usado somente na promocao de pecas
	private ImageIcon imgPecasBrancas[];
	private ImageIcon imgPecasPretas[];
	
	private int reiBrancoX;
	private int reiBrancoY;
	private int reiPretoX;
	private int reiPretoY;
			
	
	/*
	 * O vetor pos guarda as posiçoes para onde pode ir a ultima peca selecionada
	 * note que a forma como as posiç[]ões esta guardada é em pares seguidos
	 * [x1,y1,x2,y2,x3,y3,...]
	 * entao para percorrer é preciso avancar de dois em dois
	 * 
	 * nao é preciso se preocupar com o conteudo do vetor quando nenhuma peca esta selecionada
	 * so vamos pedir o vetor quando o estado do jogo indicar que uma peca
	 * esta selecionada
	 */
	List<Integer> pos;
	public Judge(Control control) {
		this.control = control;
		state = GameState.BRANCAS;
		
		pecasEspelho = new int[Chess.QTD_TILES][Chess.QTD_TILES];
		
		try
		{
			pecasBrancas = new Peca[]
			{
				new Rei("/ReiBr.png"),
				new Rainha("/RainhaBr.png"),
				new Bispo("/BispoBr.png"),
				new Cavaleiro("/CavaloBr.png"),
				new Torre("/TorreBr.png"),
				new Peao("/PeaoBr.png")
			};
		
			pecasPretas = new Peca[]
			{
				new Rei("/ReiPr.png"),
				new Rainha("/RainhaPr.png"),
				new Bispo("/BispoPr.png"),
				new Cavaleiro("/CavaloPr.png"),
				new Torre("/TorrePr.png"),
				new Peao("/PeaoPr.png")
			};
		}
		catch(IOException e) 
		{
			e.printStackTrace();
			System.err.println("erro ao ler pecas");
		}
		imgPecasBrancas = new ImageIcon[5];
		imgPecasPretas = new ImageIcon[5];
		for(int i=0; i<5;i++) {
			imgPecasBrancas[i] = new ImageIcon(pecasBrancas[i+1].getImage());
			imgPecasPretas[i] = new ImageIcon(pecasPretas[i+1].getImage());
		}
		reiBrancoX = 4;
		reiBrancoY = 7;
		reiPretoX = 4;
		reiPretoY = 0;
		
		jogadaAnteriorBranca = new ArrayList<Integer>(4);
		jogadaAnteriorPreta = new ArrayList<Integer>(4);
		
		for(int i=0;i<4;i++)
			jogadaAnteriorBranca.add(0);
		for(int i=0;i<4;i++)
			jogadaAnteriorPreta.add(0);
		
		repeticoes = 0;
		
		setChanged();
		notifyObservers();
	}
	
	public void move(int xAtual, int yAtual, int xNovo, int yNovo) {
		pecas[yNovo][xNovo] = pecas[yAtual][xAtual];		
		pecas[yAtual][xAtual] = 0;
		//TODO animacao fica aqui
		
		//System.out.printf("mudei de %d,%d para %d,%d\n",  xAtual, yAtual, xNovo, yNovo);
		setChanged();
		notifyObservers();
	}
	
	public List<Integer> getMovimentoPeca(int x, int y) {
		int peca = pecas[y][x];
		assert(peca!=0);
		if(peca>0)
			return pecasBrancas[peca-1].move(pecas, x, y);
		else
			return pecasPretas[-peca-1].move(pecas, x, y);
	}
	
	public void promove(int x, int y) {
		ImageIcon imgPecas[];
		int opcao=0;
		
		//usar as imagens de acordo com a cor da peca
		if(pecas[y][x]>0)
			imgPecas = imgPecasBrancas;
		else
			imgPecas = imgPecasPretas;
		
		//mostrar janela varias vezes ate que o usuario selecione uma das pecas
		do {
			opcao = JOptionPane.showOptionDialog(control.game, "Para qual peca gostaria que o peão fosse promovido?", "Promoção de Peça", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE, null, imgPecas, null);
		}while(opcao==JOptionPane.CLOSED_OPTION);
		
		//Deslocando o valor de acordo com a cor, o 0 � vazio e nao vamos promover para rei, isso gera um deslocamento
		if(pecas[y][x]<0)
			pecas[y][x] = -(opcao+2);
		else
			pecas[y][x] = opcao+2;

		setChanged();
		notifyObservers();
	}

	/*
	 * funcao auxiliar retorna true se as duas forem da mesma cor
	 */
	private boolean eDaMesmaCor(int pecasTabuleiro[][], int xa, int ya, int xb, int yb) {
		return pecasTabuleiro[ya][xa] * pecasTabuleiro[yb][xb] > 0;
	}
	/*
	 * funcao auxilia, copia o conteudo da matriz a para a matriz b
	 */
	private void copiaMatriz(int ma[][], int mb[][]) {
		for(int i=0; i<ma.length;i++) {
			for(int e=0; e<ma.length;e++) {
				mb[i][e] = ma[i][e];
			}
		}
	}
	/*
	 * recebe uma lista de posicoes, uma posicao pivo, e o id de uma peca.
	 * retorna true se houver uma peca em uma das posicoes da lista
	 * na realidade aqui a posicao pivo nem esta sendo usada
	 * 
	 * esta funcao é usada para verificar se o rei esta sendo ameacado por uma peca determinada
	 * passamos todas as posicoes a partir do rei que correpondem a distancia de uma determinada peca a partir dele
	 * e verificamos se em alguma dessas posicoes existe a tal determinada peca
	 * se houver, entao ela esta ameacando o rei
	 */
	private boolean contemPeca(int pecasTabuleiro[][],List<Integer> posAmeacas, int x, int y, int compara) {
		
		int peca;
		
		int xLista;
		int yLista;
		for(Iterator<Integer> iter = posAmeacas.iterator(); iter.hasNext();) {
			//capturo o par de valores na lista de movimentos possiveis
			xLista = iter.next();
			yLista = iter.next();
			//capturo a peca que esta ali
			peca = pecasTabuleiro[yLista][xLista];
			//se a peca for uma peca do tipo que procuro
			if(peca == compara) {
				System.out.printf("tem uma peca %d em %d,%d mirando no rei em %d,%d\n", peca, xLista, yLista, x, y);
				//estou amecando o rei, retorna true
				return true;
			}
		}
		return false;
	}
	
	/*
	 * verifica se o rei numa determinada posicao esta sendo ameacado por alguma peca,
	 * percorre todos os tipos de peca, e verifica se existe alguma a distancia do rei
	 * utiliza a funcao contemPeca para verifica se alguma ameaca acontece para cada uma das pecas
	 */
	
	private boolean validaCheque(int pecasTabuleiro[][], int x, int y) {
		List<Integer> posAmeacas;
		int compara=0;
		for(int i=0;i<6;i++) {
			if(pecasTabuleiro[y][x]>0) {// se o rei for branco
				//guardamos as posicoes que poderiam enxergar o rei na posicao qu ele esta
				posAmeacas = pecasBrancas[i].move(pecasTabuleiro, x, y);
				//estamos procurando uma peca de cada
				//a compara corresponde a peca que estamos verificando agora
				compara = -(i+1);
			}else {//se o rei for preto
				posAmeacas = pecasPretas[i].move(pecasTabuleiro, x, y);
				//essas operacoes no i, sao relativas a cor e o deslocamente das pecas
				//(tem coisa que comeca no 1, outras comecam no 0..)
				compara = i+1;
			}
			//se houver de fato uma peca que enxergue o rei e que seja correnspodente a este movimento
			//retorna verdadeiro, pos uma peca ameaça o rei
			if(contemPeca(pecasTabuleiro,posAmeacas, x, y, compara)) return true;
		}
				
		return false;
	}
	
	/*
	 * verifica se esta acontecendo um cheque mate
	 * verifica se tem alguma peca que seja da mesma cor que o rei ameacado que possa fazer
	 * algum movimento valido
	 * se nenhuma puder, entao e cheque-mate
	 * se houver alguma que possa,
	 * entao e apenas cheque
	 */
	private boolean validaChequeMate(int pecasTabuleiro[][], int xRei, int yRei) {
		int pecasEncontradas=0;
		//pecorrer todas as pecas no tabuleiro
		for (int i = 0; i < pecas.length; i++) {
			for (int e = 0; e < pecas.length; e++) {
				
				//se a peca for da cor do rei
				if(pecasTabuleiro[i][e]!=0 && eDaMesmaCor(pecas, e, i, xRei, yRei)) {
					pecasEncontradas++;
					//capturamos os movimentos dela
					pos = getMovimentoPeca(e, i);
					//filtramos somente movimentos validos
					if(pecasTabuleiro[yRei][xRei]>0)
						validaMovimentoCheque(e, i, reiBrancoX, reiBrancoY);
					else
						validaMovimentoCheque(e, i, reiPretoX, reiPretoY);
					
					//se houver algum movimento valido
					if(pos.size() > 0) //entao nao e cheque mate
						return false;
					if(pecasEncontradas >=32) return true;
					
				}
				
			}
		}
		
		return true;
	}
	
	/*
	 * Impede o jogador de fazer jogadas invalidas, que colocariam o proprio rei em cheque,
	 *avaliando cada possivel jogada e retirando da lista de posicoes validas
	 */
	private void validaMovimentoCheque(int xPeca, int yPeca, int xRei,int yRei) {
		int xPos, yPos;
		for(ListIterator<Integer> iter = pos.listIterator(); iter.hasNext();) {
			//copia a matriz de pecas para uma matriz temporaria
			//fazer uma avalição de uma jogada possivel, hipotetica, nao realizamos nenhuma jogada ainda
			copiaMatriz(pecas, pecasEspelho);
			//coloco a peca no tabuleiro espelho
			
			//colocamos a peca no lugar, mas fazemos isse nessa matriz temporaria
			xPos = iter.next();
			yPos = iter.next();

			//Caso estejamos de fato movendo o rei, e preciso atualizar o indicador da posicao dele,
			//,as note que como nao estamos de fato fazendo esse movimento, nao atualizamos a variavel global
			//somente esta que criamos para esta funcao
			
			if(pecasEspelho[yPeca][xPeca] == 1 || pecasEspelho[yPeca][xPeca] == -1){
				xRei = xPos;
				yRei = yPos;
			}
			
			pecasEspelho[yPos][xPos] = pecasEspelho[yPeca][xPeca];
			pecasEspelho[yPeca][xPeca] = 0;

			
			System.out.printf("verificando %d,%d \n", xPos, yPos);
			//verificamos se a jogada causa um cheque. Se for o caso, a jogada é invalida, e a retiramos
			
			if(validaCheque(pecasEspelho,xRei,yRei)) {
				System.out.printf("invalido %d,%d \n", xPos, yPos);
				iter.remove();
				iter.previous();
				iter.remove();
			}
			
		}
	}
	
	boolean verificaRepeticao(int pecas[][], int antX, int antY, int newX, int newY){
		List<Integer> jogadaAnterior;
		
		if(pecas[newY][newX]>0) {
			jogadaAnterior = jogadaAnteriorBranca;
		}
		else {
			jogadaAnterior = jogadaAnteriorPreta;
		}
		if(jogadaAnterior.get(0) == newX &&
			jogadaAnterior.get(1) == newY &&
			jogadaAnterior.get(2) == antX &&
			jogadaAnterior.get(3) == antY) {
				repeticoes++;
		}
		
		jogadaAnterior.set(0, antX);
		jogadaAnterior.set(1, antY);
		jogadaAnterior.set(2, newX);
		jogadaAnterior.set(3, newY);
		
		System.out.println("repeticoes: "+repeticoes);
		
		return repeticoes>=6;
	}
	
	public void click(int x, int y) {
		//System.out.println("click");
		int peca;
		switch(state) {
		
		case BRANCAS_SELECIONADA:
			if(validaPos(pos, x, y)) {
				state = GameState.PRETAS;
				move(xSelecionada, ySelecionada, x, y);
				
				//verifica repeticao
				if(verificaRepeticao(pecas, xSelecionada, ySelecionada, x, y)) {
					JOptionPane.showMessageDialog(null, " Tres jogadas repetidas!\n jogo empatou!");
				}
				
				
				//roque
				peca = pecas[y][x]; 
				if(peca == 1 || peca == 5 ||
				peca == -1 || peca == 5) {
					moveRoque(x,y);
				}
				
				//verificando promocao
				if(isPeao(x,y) && y == 0)
					promove(x,y);
				
				//verifica cheque
				if(pecas[y][x] == 1) {
					reiBrancoX = x;
					reiBrancoY = y;
				}
				if(validaCheque(pecas, reiBrancoX,reiBrancoY) || validaCheque(pecas, reiPretoX,reiPretoY)) {
					if(validaChequeMate(pecas, reiPretoX, reiPretoY)) {
						System.out.println("Cheque-Mate!");
						JOptionPane.showMessageDialog(null, "O rei esta em cheque-mate! Fim de Jogo!", "Cheque-Mate!", JOptionPane.WARNING_MESSAGE);
					}
					else {
						System.out.println("Cheque!");
						JOptionPane.showMessageDialog(null, "O rei esta em cheque!", "Cheque", JOptionPane.WARNING_MESSAGE);
					}
				}
				break;
			}
			else if(pecas[y][x] <= 0) {
				state = GameState.BRANCAS;
				break;
			}
			//else continua para o debaixo
		case BRANCAS:
			if(pecas[y][x]>0) {
				//System.out.printf("peca selecionada em %d,%d\n", x,y);				
				state = GameState.BRANCAS_SELECIONADA;
				xSelecionada = x;
				ySelecionada = y;
				pos = getMovimentoPeca(x, y);

				validaMovimentoCheque(x, y, reiBrancoX, reiBrancoY);
			}
			break;
		case PRETAS_SELECIONADA:
			if(validaPos(pos, x, y)) {
				state = GameState.BRANCAS;
				move(xSelecionada, ySelecionada, x, y);
				
				//verifica repeticao
				if(verificaRepeticao(pecas, xSelecionada, ySelecionada, x, y)) {
					JOptionPane.showMessageDialog(null, " Tres jogadas repetidas!\n jogo empatou!");
				}
				
				//roque
				peca = pecas[y][x]; 
				if(peca == 1 || peca == 5 ||
				peca == -1 || peca == 5) {
					moveRoque(x,y);
				}
				
				//verificando promocao
				if(isPeao(x,y) && y==Chess.QTD_TILES-1)
						promove(x,y);
				
				if(pecas[y][x] == -1) {
					reiPretoX = x;
					reiPretoY = y;
				}
				
				if(validaCheque(pecas, reiBrancoX,reiBrancoY) || validaCheque(pecas, reiPretoX,reiPretoY)) {
					if(validaChequeMate(pecas, reiBrancoX, reiBrancoY)) {
						System.out.println("Cheque-Mate!");
						JOptionPane.showMessageDialog(null, "O rei esta em cheque-mate! Fim de Jogo!", "Cheque-Mate!", JOptionPane.WARNING_MESSAGE);
					}
					else {
						System.out.println("Cheque!");
						JOptionPane.showMessageDialog(null, "O rei esta em cheque!", "Cheque", JOptionPane.WARNING_MESSAGE);
					}
				}
				
				break;
			}
			else if(pecas[y][x]>=0){
				state = GameState.PRETAS;
				break;
			}
			//else continua para o debaixo
		case PRETAS:
			if(pecas[y][x]<0) {
				//System.out.printf("peca selecionada em %d,%d\n", x,y);				
				state = GameState.PRETAS_SELECIONADA;
				xSelecionada = x;
				ySelecionada = y;
				pos = getMovimentoPeca(x , y);
				
				validaMovimentoCheque(x, y, reiPretoX, reiPretoY);
			}
			break;
		default:
			break;
		}
		//System.out.println("setChanged");
		setChanged();
		notifyObservers();
	}
	
	/*
	 * Verifica se a possição passada esta no vetor de posicoes
	 */
	private boolean validaPos(List<Integer> pos, int x, int y) {
		int xLista;
		int yLista;
		for(Iterator<Integer> iter = pos.iterator(); iter.hasNext();) {
			xLista = iter.next();
			yLista = iter.next();
			
			if(xLista == x && yLista == y)
				return true;
		}
		return false;
	}

	public void moveRoque(int x, int y) {
		int peca = pecas[y][x];
		if(peca == 1) { //rei branco
			Rei.setTorreBrancaEsquerda(false);
			Rei.setTorreBrancaDireita(false);
			
			if(x==6) {
				pecas[y][x+1] = 0;
				pecas[y][x-1] = 5; 
			}
			if(x==1) {
				pecas[y][x-1] = 0;
				pecas[y][x+1] = 5; 
			}
		}
		if(peca == -1){ //rei preto
			Rei.setTorrePretaEsquerda(false);
			Rei.setTorrePretaDireita(false);
			
			if(x==6) {
				pecas[y][x+1] = 0;
				pecas[y][x-1] = -5; 
			}
			if(x==1) {
				pecas[y][x-1] = 0;
				pecas[y][x+1] = -5; 
			}
		}
		if(peca == 5) { //torre branco
			if(x==0)
				Rei.setTorreBrancaEsquerda(false);
			else
				Rei.setTorreBrancaDireita(false);
		}
		if(peca == -5) { //torre preta
			if(x==0)
				Rei.setTorrePretaEsquerda(false);
			else
				Rei.setTorrePretaDireita(false);
		}
	}
	
	public boolean isSelecionada() {
		return state == GameState.BRANCAS_SELECIONADA || state == GameState.PRETAS_SELECIONADA;
	}

	public boolean isPeao(int x, int y) {
		return Math.abs(pecas[y][x]) == 6;
	}
	
	@Override
	public Integer[] getPos() {
		return pos.toArray(new Integer[0]);
	}

	@Override
	public int[][] getPecas() {
		return pecas;
	}

	@Override
	public Peca[] getPecasBrancas() {
		return pecasBrancas;
	}

	@Override
	public Peca[] getPecasPretas() {
		return pecasPretas;
	}

	@Override
	public int getXSelecionada() {
		return xSelecionada;
	}

	@Override
	public int getYSelecionada() {
		return ySelecionada;
	}

	@Override
	public synchronized void addObserver(Observer o) {
		super.addObserver(o);
		setChanged();
		notifyObservers();
	}

	public void salvar() {
		
		/* 
		 * Boolean branca
		 * Diz a vez do jogador
		 * 
		 * branca = true
		 * preta = false
		 * 
		 * Esta na vez das brancas se for true
		 * Esta na vez das pretas se for false
		 * 
		 * Nao estou guardando se uma peca for selecionada
		 * 
		 */
		boolean branca;
		
		
		branca = (state == GameState.BRANCAS || state == GameState.BRANCAS_SELECIONADA);
		SalvarJogo.salva(pecas, Rei.getTorreBrancaEsquerda(), Rei.getTorreBrancaDireita(), Rei.getTorrePretaEsquerda(), Rei.getTorrePretaDireita(), reiBrancoX, reiBrancoY, reiPretoX, reiPretoY, branca);
	}
	public void carregar() {
		int ret[][] = SalvarJogo.carregar();
		if(ret==null) return;
		pecas = ret;
		
		Rei.setTorreBrancaEsquerda(SalvarJogo.getTorreBrancaEsquerda());
		Rei.setTorreBrancaDireita(SalvarJogo.getTorreBrancaDireita());
		Rei.setTorrePretaEsquerda(SalvarJogo.getTorrePretaEsquerda());
		Rei.setTorrePretaDireita(SalvarJogo.getTorrePretaDireita());
		
		reiBrancoX = SalvarJogo.getReiBrancoX();
		reiBrancoY = SalvarJogo.getReiBrancoY();
		reiPretoX = SalvarJogo.getReiPretoX();
		reiPretoX = SalvarJogo.getReiPretoY();
		
		if(SalvarJogo.getState())
			state = GameState.BRANCAS;
		else
			state = GameState.PRETAS;
	}
}


