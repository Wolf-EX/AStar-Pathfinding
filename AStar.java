package pathfinding;

import java.util.Comparator;
import java.util.List;

public class AStar {
	
	//private final float dist = (float)Math.sqrt(2);
	
	public boolean StartPathing(Grid grid, int startX, int startY, int targetX, int targetY) {
					
		grid.nodes = new Node[grid.width][grid.height];
		grid.nodes[startX][startY] = new Node(startX, startY, 0, GetDistToTarget(startX, startY, targetX, targetY));
		
		grid.openNodes.add(grid.nodes[startX][startY]);
		
		Node currentNode;
		List<Vector2<Integer>> emptySpaces;
		List<Node> shortestNodes;
		Node shortestNode;
		
		while(!grid.openNodes.isEmpty() && grid.targetHit == false) {
			

			currentNode = grid.openNodes.poll();
			currentNode.closed = true;
			
			if(currentNode.x == targetX && currentNode.y == targetY) {
	            grid.targetHit = true;
	            return true;
	        } else {
	        	
	        	emptySpaces = grid.GetBorderingEmptySpaces(currentNode);
	        	float dist;
	        	for(Vector2<Integer> space : emptySpaces) {
	        		dist = 1.0f;
	        		shortestNodes = grid.GetBorderingNodes((int)space.GetX(), (int)space.GetY());
	        		shortestNodes.sort(new SortByDistanceFromStart());
	        		shortestNode = shortestNodes.get(0);
	        		if(shortestNode.GetX() != space.GetX() && shortestNode.GetY() != space.GetY()){
	        			dist = 1.41f;
	        			//dist = this.dist;
	        		}
	        		
	        		Node node = new Node((int)space.GetX(), (int)space.GetY(), shortestNode.startDist + dist, GetDistToTarget((int)space.GetX(), (int)space.GetY(), targetX, targetY));
	        		grid.openNodes.add(node);
	        		grid.nodes[(int)space.GetX()][(int)space.GetY()] = node;
	        	}
	        }
		}
		return false;
	}
	
	public Node PathingNext(Grid grid, int startX, int startY, int targetX, int targetY) {
		
		if(grid.nodes == null) {
			grid.nodes = new Node[grid.width][grid.height];
			grid.nodes[startX][startY] = new Node(startX, startY, 0, GetDistToTarget(startX, startY, targetX, targetY));
			
			grid.openNodes.add(grid.nodes[startX][startY]);
		}

		Node currentNode = grid.openNodes.poll();
		System.out.println(currentNode.GetStartDist());
		List<Vector2<Integer>> emptySpaces = grid.GetBorderingEmptySpaces(currentNode);
		if(currentNode != null) {
			currentNode.closed = true;
			if(currentNode.x == targetX && currentNode.y == targetY) {
	            grid.targetHit = true;
	            return currentNode;
	        }
			if(!emptySpaces.isEmpty()) {
				
		        	float dist;
		        	for(Vector2<Integer> space : emptySpaces) {
		        		dist = 1.0f;
		        		List<Node> shortestNodes = grid.GetBorderingNodes((int)space.GetX(), (int)space.GetY());
		        		shortestNodes.sort(new SortByDistanceFromStart());
		        		Node shortestNode = shortestNodes.get(0);
		        		if(shortestNode.GetX() != space.GetX() && shortestNode.GetY() != space.GetY()) {
		        			dist = 1.41f;
		        			//dist = this.dist;
		        		}
		        		Node node = new Node((int)space.GetX(), (int)space.GetY(), shortestNode.startDist + dist, GetDistToTarget((int)space.GetX(), (int)space.GetY(), targetX, targetY));
		        		grid.openNodes.add(node);
		        		grid.nodes[(int)space.GetX()][(int)space.GetY()] = node;
		        	}
			}
		}
		return currentNode;
	}
	
	public boolean FindPathToTarget(Grid grid, int targetX, int targetY){

		if(grid.targetHit) {
	        Node currentPath = grid.nodes[targetX][targetY];
	        List<Node> borderingNode;
	
	        grid.path.add(currentPath);
	        while(currentPath.startDist > 0){
	        	borderingNode = grid.GetBorderingNodes(currentPath);
	            borderingNode.sort(new SortByDistanceFromStart());
	            currentPath = borderingNode.get(0);
	            System.out.println(currentPath.GetX() + " - " + currentPath.GetY());
	            grid.path.add(currentPath);
	            borderingNode.clear(); //Needed?
	        }
	        return true;
		} else {
			return false;
		}
    }
	
	public int GetPathSize(Grid grid) {
		return grid.GetPathSize();
	}
	
	public List<Node> GetPathNodes(Grid grid){
		return grid.path;
	}
	
	public Vector2<Integer> GetPathVector(Grid grid, int index) {
		return grid.GetPathVector(index);
	}
	
	float GetDistToTarget(int currentX, int currentY, int targetX, int targetY) {
        return (float) Math.sqrt(Math.pow(Math.abs(currentX - targetX), 2) + Math.pow(Math.abs(currentY - targetY), 2));
   }
}

class SortByDistanceFromStart implements Comparator<Node>{

    public int compare(Node a, Node b){

int startDist;
		
		if(a.startDist < b.startDist)
			startDist = -1;
		else if(a.startDist > b.startDist)
			startDist = 1;
		else startDist = 0;
		
		return startDist;
    }
}

class SortByEstimatedDistanceToTarget implements Comparator<Node>{
	
	public int compare(Node a, Node b) {
		
		int totalDist;
		
		if(a.targetDist < b.targetDist)
			totalDist = -1;
		else if(a.targetDist > b.targetDist)
			totalDist = 1;
		else totalDist = 0;
		
		return totalDist;
	}
}

class SortByEstimatedTotalDistanceOfPath implements Comparator<Node>{

    public int compare(Node a, Node b){

    	int totalDist;
    	int targetDist;
    	
    	if((a.startDist + a.targetDist) < (b.startDist + b.targetDist))
    		totalDist = -1;
    	else if((a.startDist + a.targetDist) > (b.startDist + b.targetDist))
    		totalDist = 1;
    	else totalDist = 0;
    	
    	if(a.targetDist < b.targetDist)
    		targetDist = -1;
    	else if(a.targetDist > b.targetDist)
    		targetDist = 1;
    	else targetDist = 0;
    	
    	return (totalDist == 0) ? targetDist : totalDist;
    	
    }
}
