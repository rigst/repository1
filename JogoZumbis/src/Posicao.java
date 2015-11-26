
public class Posicao {
	public int x, y;
	
	public Posicao(int x, int y){ this.x = x; this.y = y;}
	
	public /*@ pure helper @*/ int getX() { return x; }
	public void setX(int x) { this.x = x; }
	
	public /*@ pure helper @*/ int getY() { return y; }
	public void setY(int y) { this.y = y;}
	
	public String toString(){ return "(" + x + ", " + y + ")"; }
}