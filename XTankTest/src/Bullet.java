
public class Bullet {
	private int xPos;
	private int yPos;
	private int angle;
	private Tank shooter;
	
	public Bullet(Tank shooter) {
		xPos = shooter.getXpos();
		yPos = shooter.getYpos();
		angle = shooter.getAngle();
		this.shooter = shooter;
	}
	
	public void move() {
		if (angle == 0) {
			if (xPos < 900) xPos += 1;
		} else if (angle == 1){
			if (xPos > 0) xPos -= 1;
		} else if (angle == 2){
			if (yPos > 0) yPos -= 1;
		} else if (angle == 3){
			if (yPos < 900) yPos += 1;
		}
	}
	
	public int getXpos() {
		return xPos;
	}
	
	public int getYpos() {
		return yPos;
	}
	
	public Tank getShooter() {
		return shooter;
	}
	
	public String toString() {
		return "SHOT: x=" + xPos + ", y=" + yPos;
 	}
}
