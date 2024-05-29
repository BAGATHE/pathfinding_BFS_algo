package ia.component;

import ia.math.Vecteur;
import java.util.List;
import java.io.*;
import ia.map.*;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.*;
import java.util.Vector;

public class Node{
	private Node parent;
	private Vecteur position;
	private List<Node> neighbor;
	private ImageIcon image;
	private boolean isDestination;
	private boolean isStart;
	private boolean isVisited;
	private boolean isSolid;
	private boolean isPath;

	public Node getParent(){
		return this.parent;
	}
	public void setParent(Node parent){
		this.parent = parent;
	}
	public Vecteur getPosition(){
		return this.position;
	}
	public void setPosition(Vecteur position){
		this.position = position;
	}
	public List<Node> getNeighbor(Map map){
		List<Node> neighbor = new Vector<Node>();
		int i = (int)(this.getPosition().getY() / map.TILE_SIZE);
		int j = (int)(this.getPosition().getX() / map.TILE_SIZE);
		if(j - 1 >= 0 && map.getNodes()[i][j - 1] != null){
			neighbor.add(map.getNodes()[i][j - 1]);
		}
		if(j + 1 < map.getNodes()[i].length && map.getNodes()[i][j + 1] != null){
			neighbor.add(map.getNodes()[i][j + 1]);
		}
		if(i - 1 >= 0 && map.getNodes()[i - 1][j] != null){
			neighbor.add(map.getNodes()[i - 1][j]);
		}
		if(i + 1 < map.getNodes().length && map.getNodes()[i + 1][j] != null){
			neighbor.add(map.getNodes()[i + 1][j]);
		}
		return neighbor;
	}
	public void setNeighbor(List<Node> neighbor){
		this.neighbor = neighbor;
	}
	public ImageIcon getImage(){
		return this.image;
	}
	public void setImage(ImageIcon image){
		this.image = image;
	}
	public boolean isDestination(){
		return this.isDestination;
	}
	public void setIsDestination(boolean destination){
		this.isDestination = destination;
	}
	public boolean isStart(){
		return this.isStart;
	}
	public void setIsStart(boolean start){
		this.isStart = start;
	}
	public boolean isVisited(){
		return this.isVisited;
	}
	public void setIsVisited(boolean visited){
		this.isVisited = visited;
	}
	public boolean isSolid(){
		return this.isSolid;
	}
	public void setIsSolid(boolean solid){
		this.isSolid = solid;
	}
	public boolean isPath(){
		return this.isPath;
	}
	public void setIsPath(boolean path){
		this.isPath	= path;
	}

	public Node(double x , double y , ImageIcon image){
		this.setPosition(new Vecteur(x , y));
		this.setImage(image);
		this.setIsVisited(false);
	}
	public void drawRect(Graphics g , Map map){
		if(this.isVisited()){
			g.setColor(Color.ORANGE);
		}
		if(this.isSolid()){
			g.setColor(Color.BLACK);
		}
		if(this.isDestination()){
			g.setColor(Color.BLUE);
		}
		if(this.isStart()){
			g.setColor(Color.YELLOW);
		}
		if(this.isPath()){
			g.setColor(Color.GREEN);
		}
		g.fillRect((int)this.getPosition().getX(), (int)this.getPosition().getY() ,map.TILE_SIZE , map.TILE_SIZE);
		g.setColor(Color.WHITE);
		g.drawRect((int)this.getPosition().getX(), (int)this.getPosition().getY() ,map.TILE_SIZE , map.TILE_SIZE);
	}
}