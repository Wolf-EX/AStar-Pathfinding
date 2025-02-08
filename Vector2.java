package astar;

public class Vector2<T> {

	T x;
	T y;
	
	
	 Vector2(T x, T y) { 
		 this.x = x; this.y = y; 
	 }

	public T GetX() {
		return x;
	}
	
	public T GetY() {
		return y;
	}
}
