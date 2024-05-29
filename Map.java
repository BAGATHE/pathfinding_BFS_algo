package ia.map;

import ia.algo.Algorithm;
import ia.component.*;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.List;
import java.util.Vector;
import ia.listener.*;
import java.awt.event.*;
import java.io.*;

public class Map extends JPanel implements Runnable{
	private Node[][] nodes;
	private Node start;
	private Node destination;
	private List<Node> fPath;
	public int ORIGINAL_TILE_SIZE = 12;
	public int SCALAR = 2;
	public int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALAR;
	public int ROW = 12;
	public int COL = 17;
	public int WIDTH = COL * TILE_SIZE;
	public int HEIGHT = ROW * TILE_SIZE;
	public Thread thread;

	public Node[][] getNodes(){
		return this.nodes;
	}
	public void setNodes(Node[][] nodes){
		this.nodes = nodes;
	}
	public Node getStart(){
		return this.start;
	}
	public void setStart(int i , int j){
		if(this.getNodes()[i] != null && this.getNodes()[i][j] != null){
			this.getNodes()[i][j].setIsStart(true);
			this.start = this.getNodes()[i][j];
		}
	}
	public Node getDestination(){
		return this.destination;
	}
	public void setDestination(int i , int j){
		if(this.getNodes()[i] != null && this.getNodes()[i][j] != null){
			this.getNodes()[i][j].setIsDestination(true);
			this.destination = this.getNodes()[i][j];
		}
	}
	public void setSolid(int i , int j){
		if(this.getNodes()[i] != null && this.getNodes()[i][j] != null){
			this.getNodes()[i][j].setIsSolid(true);
		}
	}
	public void setVisited(int i , int j){
		if(this.getNodes()[i] != null && this.getNodes()[i][j] != null){
			this.getNodes()[i][j].setIsVisited(true);
		}
	}
	public void setPath(int i , int j){
		if(this.getNodes()[i] != null && this.getNodes()[i][j] != null){
			this.getNodes()[i][j].setIsPath(true);
		}
	}
	public void initWays(){
		List<Node> path = new Vector<Node>();
		Node intermediaire = this.getDestination();
		while (intermediaire != null && !intermediaire.equals(this.getStart())) {
			path.add(intermediaire);
			if(!intermediaire.equals(this.getDestination()) && !intermediaire.equals(this.getStart())){
				intermediaire.setIsPath(true);
			}
			intermediaire = intermediaire.getParent();
		}
		List<Node> reversedPath = new Vector<Node>();
    	this.setFPath(reversedPath);
	}

	public void setFPath(List<Node> path){
		this.fPath = path;
	}

	public Map(){
		try{
			BufferedReader bf = new BufferedReader(new FileReader(new File("./Map.txt")));
			int i = 0;
			int j = 0;
			String row = bf.readLine().split(" ")[0];
			String col = bf.readLine().split(" ")[0];
			ROW = Integer.parseInt(row);
			COL = Integer.parseInt(col);
			WIDTH = COL * TILE_SIZE;
			HEIGHT = ROW * TILE_SIZE;
			this.setNodes(new Node[ROW][COL]);
			String line = "";
			while((line = bf.readLine()) != null){
				String[] nums = line.split(" ");
				for(j = 0 ; j < COL ; j++){
					Node node = new Node(j * TILE_SIZE , i * TILE_SIZE , null);
					String num = nums[j];
					this.getNodes()[i][j] = node;
					if(num.equals("1")){
						node.setIsSolid(true);
					}
					if(num.equals("2")){
						this.setStart(i, j);
					}
					if(num.equals("3")){
						this.setDestination(i, j);
					}
				}
				i++;
			}
			bf.close();
			this.thread = new Thread(this);
			this.thread.start();
			Algorithm.BFS(this);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	public void init(){
		this.setStart(1 , 1);
		this.setDestination(1 , 13);
	}
	public void draw(Graphics g){
		for(int i = 0 ; i < this.getNodes().length ; i++){
			for(int j = 0 ; j < this.getNodes()[i].length ; j++){
				this.getNodes()[i][j].drawRect(g , this);
			}
		}
	}
	public void run(){
		double now = -1;
		double lastTime = System.nanoTime();
		double fps = 60.0;
		double drawTime = 1000000000 / fps;
		double delta = 0;
		while(true){
			now = System.nanoTime();
			delta += (now - lastTime) / drawTime;
			if(delta >= 1){
				this.repaint();
				delta--;
			}
			lastTime = now;
		}
	}
	public void actionPerformed(ActionEvent e){}
}