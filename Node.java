package pathfinding;

public class Node {
	
	int x;
	int y;
	float startDist;
	float targetDist;
	boolean closed = false;

	Node(int x, int y, float startDist, float targetDist){
		
		this.x = x;
		this.y = y;
		this.startDist = startDist;
		this.targetDist = targetDist;
	}
	
	Node(Vector2 <Integer> pos, float startDist, float targetDist){
		this(pos.GetX(), pos.GetY(), startDist, targetDist);
	}
	
	public int GetX() {
		return x;
	}
	
	public int GetY() {
		return y;
	}
	
	public float GetStartDist() {
		return startDist;
	}
	
	public float GetTargetDist() {
		return targetDist;
	}
	
}
