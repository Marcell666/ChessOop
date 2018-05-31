package principal;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;

public class Chess extends JFrame
{
	/*
	 * Essa é uma das formas de definir constantes
	 * Uma outra seria definir um enum propriamente
	 * 
	 * */
	public static final int WIDTH = 560;
	public static final int HEIGHT = 560;
	public static final int QTD_TILES = 8;
	public static final int TILE_WIDTH = 70;
	public static final int TILE_HEIGHT = 70;
	
	/*
	 * Esses vetores só vao ter 6 posicoes e 
	 * vão conter uma instancia de cada peca
	 * 
	 * */
	Peca pecasBrancas[];
	Peca pecasPretas[];
	
	/*
	 * A screen controla o que vai ser desenhado na tela
	 * */
	Screen screen;
	
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
	
	int pecas[][] =  new int[][]
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
	
	public Chess(String name) 
	{
		/*
		 * A classe chess extende de JFrame
		 * Passamos o nome que vai aparecer no topo da tela
		 * 
		 * */
		super(name);
	
		/*
		 * A classe JFrame define constantes(semelhantes as 
		 * que estão sendo definidas no topo desta classe),
		 *Usamos uma constante definida na propria classe
		 *Jframe.
		 *Esatamos definindo que ao clicar no x,
		 * a janela sera fechada.
		 * 
		 */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/* 
		 * A criação de uma peca pode gerar um erro de leitura de arquivo.
		 * Pois ela procura a imagem que passamos o caminho.
		 * 
		 * Estamos inicializando o vetor com as instancias das pecas.
		 */
		try
		{
			pecasBrancas = new Peca[]
			{
				/*
				 * ########## IMPORTANTE #########
				 * 
				 * O caminho abaixo é relativo a pasta 'bin'.
				 * 
				 * Um arquivo executavel procura caminhos relativos a partir
				 * do diretorio onde se encontra.
				 * No eclipse, os caminhos relativos comecam na pasta bin,
				 * para onde o executavel seria exportado.
				 * 
				 * As imagens são copiadas para a pasta bin quando executamos 
				 * o projeto.
				 * 
				 * Este codigo esta num package (que é uma pasta) chamado 'principal',
				 * e nao na pasta 'bin'. Entao ele iria procurar a partir da pasta 'principal'.
				 * Por isso usamos essa barra '/' no inicio, isso significa que queremos 
				 * procurar a partir do diretorio raiz, como esta definido no classpath 
				 * 
				 * Se nao entender, pergunte porque isso da MUITO bug
				 */
				new Peca("/ReiBr.png"),
				new Peca("/RainhaBr.png"),
				new Peca("/BispoBr.png"),
				new Peca("/CavaloBr.png"),
				new Peca("/TorreBr.png"),
				new Peca("/PeaoBr.png")
			};
		
			pecasPretas = new Peca[]
			{
				new Peca("/ReiPr.png"),
				new Peca("/RainhaPr.png"),
				new Peca("/BispoPr.png"),
				new Peca("/CavaloPr.png"),
				new Peca("/TorrePr.png"),
				new Peca("/PeaoPr.png")
			};
		}
		catch(IOException e) 
		{
			/* 
			 * Se der algum erro de leitura
			 * Ou algum erro que extenda dele
			 * 
			 * vamos imprimir a mensagem padrao, alem de uma mensagem nossa
			 * 
			 */
			
			e.printStackTrace();
			System.err.println("erro ao ler pecas");
		}
		
		/*
		 * Inicializando screen e passando este objeto (da classe chess) como parametro.
		 * Se screen possuir uma referencia para este objeto, vai ser mais facil para ele chama-lo.
		 * 
		 */
		screen = new Screen(this);
		/*
		 * Layout nulo quer dizer que vamos colocar as coisas na tela manualmente
		 * Se nao o java colocar o borderlayout como default, o que pode
		 * atrapalhar no posicionamento
		 * 
		 */
		screen.setLayout(null);
		
		/*
		 * Localização relativa ao componente que contem a tela,
		 * que é o frame. No caso este frame aqui mesmo, da classe chess
		 * 
		 * Tamanho da screen
		 * 
		 * na verdade esses dois comandos nao servem para muita coisa
		 * ja que nao temos outros componentes
		 * 
		 */
		screen.setBounds(0,0,WIDTH,HEIGHT);
		/*
		 * ####### IMPORTANTE ######
		 * 
		 * 
		 * getContentPane é uma função da classe jframe que nos da o objeto 
		 * que representa o espaço de conteudo que apresentamos ao usuario
		 * 
		 * Note que essa é uma função que apenas retorna, e nao cria.
		 * Assim nao tem problema chamar duas vezes. 
		 * Mas poderiamos nomear uma variavel conforme a preferencia.
		 * 
		 * Este tamanho que colocamos vai de fato determinar o tamanho da janela
		 * Ele é levado em consideração quando usamos pack()
		 * 
		 * Ao colocar tamanho no conteudo e nao na propria janela,
		 * nao consideramos as barras laterais nem do topo.
		 * Tendo mais precisao ao determinar o tamanho.
		 * 
		 * Essa classe dimension é só para passar tamanhos as vezes.
		 * 
		 */
		getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
		/*
		 * precisamos adicionar a tela como componente filho da janela.
		 * Os eventos de interface que fazem a janela seja redesenhada 
		 * precisam fazer com que a tela também seja.
		 * 
		 * Se nao adicionarmos, a função paintComponent de screen nao sera chamada.
		 */
		getContentPane().add(screen);
		
		/*
		 * Essa função tenta colocar todos os componentes nos tamanhos apropriados
		 * chamamos ela apos ajustar o tamanhao do conteudo pois ela considera 
		 * o valor guardado
		 * 
		 * É preciso tomar cuidado com ela, se colocarmos tamanhos conflitantes
		 * nos componentes, ela pode nao saber o que fazer.
		 */
		pack();
		/*
		 * O comando abaixo coloca um componente proximo a outro
		 * Quando passamos null ele fica no meio da tela
		 */
		setLocationRelativeTo(null);
		/*
		 * finalmente, deixamos a janela visivel =)
		 */
		setVisible(true);
	}
	
	Peca[] getPecasBrancas() 
	{
		return pecasBrancas;
	}
	
	Peca[] getPecasPretas() 
	{
		return pecasPretas;
	}
	
	int[][] getPecas() 
	{
		return pecas;
	}
	
	/* 
	 * A main é uma função estática chamada para iniciar o programa.
	 * Ela não precisa de uma classe exclusiva, voce pode colocá-la
	 * junto de outra classe.
	 * 
	 * */
	public static void main(String[] args) 
	{
		/* 
		 * Essa é a forma anonima de se instanciar um objeto
		 * Este objeto da classe xadrez que estou instanciando,
		 * não sera chamado novamente
		 * por isso nao preciso dar um nome a ele.
		 * 
		 * */
		new Chess("XADREZ");
	}
}