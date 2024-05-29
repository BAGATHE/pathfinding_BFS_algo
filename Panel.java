package ia.panel;

import ia.map.*;
import ia.panel.*;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Panel extends JPanel{
	private Map map;

	public Map getMap(){
		return this.map;
	}
	public void setMap(Map map){
		this.map = map;
	}
	public Panel(){
		this.setMap(new Map());
		this.setPreferredSize(new Dimension((int)this.getMap().WIDTH , (int)this.getMap().HEIGHT));
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.getMap().draw(g);
	}
}