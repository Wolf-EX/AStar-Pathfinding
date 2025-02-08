package astar;

import java.util.ArrayList;
import java.util.List;


// 8 direction grid
public class Grid8 extends Grid{

	public Grid8(int width, int height, boolean[][] blocker){
		super(width, height, blocker);
	}
	
	public Grid8(Vector2 <Integer> size,  boolean[][] blocker){
		this(size.GetX(), size.GetY(), blocker);
	}
	
	List<Vector2<Integer>> GetBorderingEmptySpaces(Node node) {
			
			List<Vector2<Integer>> emptySpaces = new ArrayList<>();
			
	    	if(node.y + 1 < height && nodes[node.x][node.y + 1] == null && !blocker[node.x][node.y + 1])
	    		emptySpaces.add(new Vector2<Integer>(node.x, node.y + 1));
			if(node.y - 1 >= 0 && nodes[node.x][node.y - 1] == null && !blocker[node.x][node.y - 1])
				emptySpaces.add(new Vector2<Integer>(node.x, node.y - 1));
			if(node.x + 1 < width && nodes[node.x + 1][node.y] == null && !blocker[node.x + 1][node.y])
				emptySpaces.add(new Vector2<Integer>(node.x + 1, node.y));
			if(node.x - 1 >= 0 && nodes[node.x - 1][node.y] == null && !blocker[node.x - 1][node.y])
				emptySpaces.add(new Vector2<Integer>(node.x - 1, node.y));
			if(node.x + 1 < width && node.y + 1 < height && nodes[node.x + 1][node.y + 1] == null && !blocker[node.x + 1][node.y + 1])
	    		emptySpaces.add(new Vector2<Integer>(node.x + 1, node.y + 1));
			if(node.x + 1 < width && node.y - 1 >= 0 && nodes[node.x + 1][node.y - 1] == null && !blocker[node.x + 1][node.y - 1])
				emptySpaces.add(new Vector2<Integer>(node.x + 1, node.y - 1));
			if(node.x - 1 >= 0 && node.y + 1 < height && nodes[node.x - 1][node.y + 1] == null && !blocker[node.x - 1][node.y + 1])
				emptySpaces.add(new Vector2<Integer>(node.x - 1, node.y + 1));
			if(node.x - 1 >= 0 && node.y - 1 >= 0 && nodes[node.x - 1][node.y - 1] == null && !blocker[node.x - 1][node.y - 1])
				emptySpaces.add(new Vector2<Integer>(node.x - 1, node.y - 1));
			
			return emptySpaces;
	
		}
		
	List<Node> GetBorderingNodes(Node node) {
		
		List<Node> borderingNode = new ArrayList<>();
		
		if(node.y + 1 < height && nodes[node.x][node.y + 1] != null)
            borderingNode.add(nodes[node.x][node.y + 1]);
        if(node.y - 1 >= 0 && nodes[node.x][node.y - 1] != null)
        	borderingNode.add(nodes[node.x][node.y - 1]);
        if(node.x + 1 < width && nodes[node.x + 1][node.y] != null)
        	borderingNode.add(nodes[node.x + 1][node.y]);
        if(node.x - 1 >= 0 && nodes[node.x - 1][node.y] != null)
        	borderingNode.add(nodes[node.x - 1][node.y]);
        
        if(node.x + 1 < width && node.y + 1 < height && nodes[node.x + 1][node.y + 1] != null)
            borderingNode.add(nodes[node.x + 1][node.y + 1]);
        if(node.x + 1 < width && node.y - 1 >= 0 && nodes[node.x + 1][node.y - 1] != null)
        	borderingNode.add(nodes[node.x + 1][node.y - 1]);
        if(node.x - 1 >= 0 && node.y + 1 < height && nodes[node.x - 1][node.y + 1] != null)
        	borderingNode.add(nodes[node.x - 1][node.y + 1]);
        if(node.x - 1 >= 0 && node.y - 1 >= 0 && nodes[node.x - 1][node.y - 1] != null)
        	borderingNode.add(nodes[node.x - 1][node.y - 1]);
        
        return borderingNode;
	}
	
	List<Node> GetBorderingNodes(int x, int y) {
		
		List<Node> borderingNode = new ArrayList<>();
		
		if(y + 1 < height && nodes[x][y + 1] != null)
            borderingNode.add(nodes[x][y + 1]);
        if(y - 1 >= 0 && nodes[x][y - 1] != null)
        	borderingNode.add(nodes[x][y - 1]);
        if(x + 1 < width && nodes[x + 1][y] != null)
        	borderingNode.add(nodes[x + 1][y]);
        if(x - 1 >= 0 && nodes[x - 1][y] != null)
        	borderingNode.add(nodes[x - 1][y]);
        
        if(x + 1 < width && y + 1 < height && nodes[x + 1][y + 1] != null)
            borderingNode.add(nodes[x + 1][y + 1]);
        if(x + 1 < width && y - 1 >= 0 && nodes[x + 1][y - 1] != null)
        	borderingNode.add(nodes[x + 1][y - 1]);
        if(x - 1 >= 0 && y + 1 < height && nodes[x - 1][y + 1] != null)
        	borderingNode.add(nodes[x - 1][y + 1]);
        if(x - 1 >= 0 && y - 1 >= 0 && nodes[x - 1][y - 1] != null)
        	borderingNode.add(nodes[x - 1][y - 1]);
        
        return borderingNode;
	}
}
