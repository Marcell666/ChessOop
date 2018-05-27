package gfx;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import principal.Chess;
import rules.Control;

public class MouseHandler implements MouseListener,MouseMotionListener {

	Screen screen;
	Control control;
	int xTile=0,yTile=0;
	
	public MouseHandler(Screen screen, Control control) {
		this.screen = screen;
		this.control = control;
		screen.addMouseListener(this);
		screen.addMouseMotionListener(this);
	}
	
	
	private int convertXToTile(int x) {
		return x/Chess.TILE_WIDTH;
	}
	private int convertYToTile(int y) {
		return y/Chess.TILE_HEIGHT;
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseMoved(MouseEvent arg0) {
		int xTileN, yTileN;
		xTileN = convertXToTile(arg0.getX());
		yTileN = convertYToTile(arg0.getY());
		if(xTileN != xTile || yTileN != yTile) {
			//System.out.println(xTileN+", "+yTileN);
			xTile = xTileN;
			yTile = yTileN;
			screen.repaint();
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	//	System.out.println("x do mouse: "+arg0.getX());
	//	System.out.println("y do mouse: "+arg0.getY());
		
		control.click(screen.game.getPecas(), convertXToTile(arg0.getX()), convertYToTile( arg0.getY()));
		screen.repaint();
	}

}
