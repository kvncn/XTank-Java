
public class Tank {
	
	private int xPos;
	private int yPos; 
	private int angle; 
	private String id;
	
	public Tank(int initialX, int initialY, int initialAngle, String id) {
		xPos = initialX;
		yPos = initialY;
		angle = initialAngle;
		this.id = id;
	}
	
	public void move(int xMove, int yMove, int dir) {
		xPos += xMove;
		yPos += yMove;
		angle = dir;
	}
	
	public int getXpos() {
		return xPos;
	}
	
	public int getYpos() {
		return yPos;
	}
	
	public int getAngle() {
		return angle;
	}
	
	public String toString() {
		String ret = "";
		if (angle == 0) {
			ret += ">>";
		} else if (angle == 1) {
			ret += "<<";
		} else if (angle == 2) {
			ret += "/\\";
		} else if (angle == 3) {
			ret += "\\/";
		}
		ret += id;
		return ret;
	}

}
