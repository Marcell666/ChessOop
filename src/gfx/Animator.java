package gfx;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import pecas.Peca;
import principal.Chess;

public class Animator implements ActionListener{
	Peca peca;
	int xInicio, yInicio, xAtual, yAtual, xFinal, yFinal;
	int xPasso, yPasso;
	int passos;
	boolean animando;
	Chess game;
	Screen screen;
	Timer timer;
	
	public Animator(Chess game) {
		this.game = game;
		animando = false;
		timer = new Timer(50, this);
	}
	public void setScreen(Screen screen) {
		this.screen = screen;
	}
	
	public boolean isAnimating() {
		return animando;
	}

	public void desenha(Graphics g) {
		peca.desenhaPx(g, xAtual, yAtual);
	}
	public void anima(Peca peca, int xInicio, int yInicio, int xFinal,int yFinal) throws InterruptedException {
		this.peca = peca;
		this.xInicio = xInicio*Chess.TILE_WIDTH;
		this.yInicio = yInicio*Chess.TILE_HEIGHT;
		xAtual = xInicio*Chess.TILE_WIDTH;
		yAtual = yInicio*Chess.TILE_HEIGHT;
		this.xFinal = xFinal;
		this.yFinal = yFinal;
		xPasso = (xFinal*Chess.TILE_WIDTH-xInicio*Chess.TILE_WIDTH)/20;
		yPasso = (yFinal*Chess.TILE_HEIGHT-yInicio*Chess.TILE_HEIGHT)/20;
		passos = 0;
		animando = true;
		
		System.out.printf("%d,%d-%d, %d\n",xFinal*Chess.TILE_HEIGHT, yInicio*Chess.TILE_HEIGHT, xPasso, yPasso);
		
		timer.start();
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(passos>=20) {
			timer.stop();
			animando = false;
			game.terminaAnimacao(xFinal,yFinal);
		}		
		xAtual += xPasso;
		yAtual += yPasso;
		System.out.printf("%d,%d\n", xAtual, yAtual);
		screen.repaint();
		passos++;
	}
}