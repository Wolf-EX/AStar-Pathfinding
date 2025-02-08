package pathfinding;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Grid {

	int width;
	int height;


	boolean[][] blocker; //2d array of blocked nodes, true is blocked;
	Node[][] nodes;
	public PriorityQueue<Node> openNodes = new PriorityQueue<Node>(new SortByEstimatedTotalDistanceOfPath());
;
	List<Node> path = new ArrayList<>();
	
	boolean targetHit = false;
	
	public Grid(int width, int height, boolean[][] blocker){
		
		this.width = width;
		this.height = height;

		this.blocker = blocker;
	}
	
	public Grid(Vector2<Integer> size,  boolean[][] blocker){
		
		this(size.GetX(), size.GetY(), blocker);
	}
	
	public void SetBlocker(int x, int y, boolean blocking){
		
		if(x >= 0 && x < width && y >= 0 && y < height) {
			blocker[x][y] = blocking;
		}
	}
	
	public void SetBlocker(int x, int y) {
		
		SetBlocker(x, y, true);
	}
	
	public boolean GetBlocker(int x, int y) {
		return blocker[x][y];
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
        
        return borderingNode;
	}
	
	public List<Vector2<Integer>> GetBorderingNodeVectors(Node node) {
		List<Vector2<Integer>> borderingVectors = new ArrayList<>();
		for(Node borderNode : openNodes) {
			borderingVectors.add(new Vector2<Integer>(borderNode.GetX(), borderNode.GetY()));
		}
		return borderingVectors;
	}
	
	public ArrayList<Node> GetNodesList(){
		if(this.nodes != null) {
		ArrayList<Node> nodes = new ArrayList<>();
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				if(this.nodes[x][y] != null)
					nodes.add(this.nodes[x][y]);
			}
		}
		return nodes;
		}
		else return null;
	}
	
	public Node GetNode(int x, int y) {
			return nodes != null ? nodes[x][y] : null;
	}
	
	public boolean GetTargetHit() {
		return targetHit;
	}
	
	int GetPathSize() {
		return path.size();
	}
	
	Vector2<Integer> GetPathVector(int index) {
		return new Vector2<Integer>(path.get(index).x, path.get(index).y);
	}
	
//	 int GetDistToTarget(int currentX, int currentY, int targetX, int targetY) {
//	        return Math.abs(currentX - targetX) + Math.abs(currentY - targetY);
//	   }
}