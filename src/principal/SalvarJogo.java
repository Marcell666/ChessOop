package principal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class SalvarJogo {

	
	private static boolean torreBrancaEsquerda;
	private static boolean torreBrancaDireita;
	private static boolean torrePretaEsquerda;
	private static boolean torrePretaDireita;
	
	private static int reiBrancoX;
	private static int reiBrancoY;
	private static int reiPretoX;
	private static int reiPretoY;
	
	private static boolean state;
	
	
	public static void salva(int[][] pecas, boolean torreBrancaEsquerda, boolean torreBrancaDireita,
			boolean torrePretaEsquerda, boolean torrePretaDireita, int reiBrancoX, int reiBrancoY, int reiPretoX, int reiPretoY, boolean state) {
		PrintWriter out;
		JFileChooser chooser = new JFileChooser();
		String filename;
	    //FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
	    //chooser.setFileFilter(filter);
		try {
		    int returnVal = chooser.showSaveDialog(null);
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		    	
		    	filename = chooser.getSelectedFile().getPath();
		    	
		    	if(!filename.endsWith(".txt")) {
		    		filename.concat(".txt");
		    	}
		    	
		       System.out.println("voce quer salvar em: " + filename);
		       
		       out = new PrintWriter(new File(filename));
		       for (int i = 0; i < pecas.length; i++) {
		    	   for(int e =0;e<pecas[i].length; e++) {				
		    		   out.write(pecas[i][e]+"\n");
		    	   }
		       }
		       out.write(torreBrancaEsquerda+"\n");
		       out.write(torreBrancaDireita+"\n");
		       out.write(torrePretaEsquerda+"\n");
		       out.write(torrePretaDireita+"\n");
		       
		       out.write(reiBrancoX+"\n");
		       out.write(reiBrancoY+"\n");
		       out.write(reiPretoX+"\n");
		       out.write(reiPretoY+"\n");
		       
		       out.write(state+"\n");

		       out.flush();
		       out.close();
		    }
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public static int[][] carregar() {
		int pecas[][] = new int[Chess.QTD_TILES][Chess.QTD_TILES];
		JFileChooser chooser = new JFileChooser();
		  int returnVal = chooser.showOpenDialog(null);
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    System.out.println("Voce quer abrir este arquivo: " + chooser.getSelectedFile().getName());
		    	try(Scanner in= new Scanner(chooser.getSelectedFile())) {
					//System.out.println(in.nextLine());
		    		for (int i = 0; i < pecas.length; i++) {
			    	   for(int e =0;e<pecas[i].length; e++) {				
			    		   if(in.hasNextInt()) {
			    			   pecas[i][e] = in.nextInt();
			    			   //System.out.println(pecas[i][e]+ ", ");
			    		   }
			    	   }
			       }
		    		
		    		torreBrancaEsquerda = in.nextBoolean();
			    	torreBrancaDireita = in.nextBoolean();
			    	torrePretaEsquerda = in.nextBoolean();
			    	torrePretaDireita = in.nextBoolean();
			    	
			    	reiBrancoX = in.nextInt();
			    	reiBrancoY = in.nextInt();
			    	reiPretoX = in.nextInt();
			    	reiPretoY = in.nextInt();
			    	
			    	state = in.nextBoolean();
			    	
		    	}catch(FileNotFoundException e) {
		    		e.printStackTrace();
		    	}
		    }
		    else return null;
		return pecas;
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

	public static int getReiBrancoX() {
		return reiBrancoX;
	}
	public static int getReiBrancoY() {
		return reiBrancoY;
	}
	public static int getReiPretoX() {
		return reiPretoX;
	}

	public static int getReiPretoY() {
		return reiPretoY;
	}

	public static boolean getState() {
		return state;
	}

}
