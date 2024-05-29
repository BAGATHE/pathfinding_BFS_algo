package ia.algo;

import ia.map.*;
import java.util.Vector;
import java.util.List;
import ia.component.*;

public class Algorithm{

	public static void BFS(Map map){
		map.getStart().setIsVisited(true);
		List<Node> file = new Vector<Node>();
		enfiler(file , map.getStart());
		int j = 0;
		while(!file.isEmpty()){
			Node last = defiler(file);
			for(int i = 0 ; i < last.getNeighbor(map).size() ; i++){
				Node u = last.getNeighbor(map).get(i);
				if(!u.isSolid()){
					if(!u.isVisited()){
						u.setParent(last);
						u.setIsVisited(true);
						enfiler(file , u);
						if(u.equals(map.getDestination())){
							map.initWays();
							return;
						}
					}
				}
			}
		}
	}

	public static void enfiler(List<Node> file , Node node){
		file.add(node);
	}
	public static Node defiler(List<Node> file){
		Node node = file.get(file.size() - 1); 
		file.remove(node);
		return node;
	}

}