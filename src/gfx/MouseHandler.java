package gfx;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import principal.Chess;
import rules.Control;

class MenuContextoBotao implements ActionListener{
	Control control;
	JMenuItem source;
	
	public MenuContextoBotao(Control control) {
		this.control = control;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		source = (JMenuItem) e.getSource();
		System.out.println(e.getSource()==null);
		switch(source.getName()) {
		case "itemSalvar":
			System.out.println("Salvar");
			control.salvar();
			break;
		case "itemCarregar":
			System.out.println("Carregar");
			control.carregar();
			break;
		default:
			break;
		}
	}
}
class MenuContexto extends JPopupMenu {
    JMenuItem itemSalvar;
    JMenuItem itemCarregar;
    MenuContextoBotao respondeBotao;
    public MenuContexto(Control control){
        itemSalvar = new JMenuItem("Salvar Jogo");
        itemCarregar = new JMenuItem("Carregar Jogo");
        itemSalvar.setName("itemSalvar");
        itemCarregar.setName("itemCarregar");
        
        respondeBotao = new MenuContextoBotao(control);
        
        itemSalvar.addActionListener(respondeBotao);
        itemCarregar.addActionListener(respondeBotao);
        add(itemSalvar);
        add(itemCarregar);
    }
}

public class MouseHandler implements MouseListener, MouseMotionListener {

	MenuContexto menu;
	Screen screen;
	Control control;
	int xTile=0,yTile=0;
	
	public MouseHandler(Screen screen, Control control) {
		this.screen = screen;
		this.control = control;
        menu = new MenuContexto(control);
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
	public void mousePressed(MouseEvent e) {
		if (e.isPopupTrigger()) {
        	menu.show(e.getComponent(), e.getX(), e.getY());
        }
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.isPopupTrigger()) {
        	menu.show(e.getComponent(), e.getX(), e.getY());
        }
		else {
			//System.out.println("x do mouse: "+arg0.getX());
			//System.out.println("y do mouse: "+arg0.getY());
			control.click(convertXToTile(e.getX()), convertYToTile( e.getY()));
			screen.repaint();
		}
	}

}
