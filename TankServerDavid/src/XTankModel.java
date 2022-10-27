import java.util.ArrayList;
import java.util.List;

public class XTankModel {
	private List<Tank> tanks;
	private List<Bullet> shots;
	
	public XTankModel() {
		tanks = new ArrayList<Tank>();
		shots = new ArrayList<Bullet>();
	}
	
	public void addTank(Tank newTank) {
		tanks.add(newTank);
	}
	
	public int moveTank(Tank tank, int xMove, int yMove, int dir) {
		tank.move(xMove, yMove, dir);
		System.out.println(tanks);
		return 1;
	}
	
	public void addShot(Tank tank, int angle) {
		Bullet shot = new Bullet(tank);
		shots.add(shot);
	}
	
	public int moveBullets() {
		for (Bullet shot : shots) {
			Boolean remove = shot.move();
			if (remove) {
				shots.remove(shot);
				return 2;
			}
		}
		//System.out.println(shots);
		return 2;
	}
	
	public void regHits() {
		for (Bullet shot : shots) {
			for (Tank tank : tanks) {
				if (shot.getShooter().equals(tank)) continue;
				if (shot.getXpos() == tank.getXpos() && shot.getXpos() == tank.getXpos()) {
					if (shot.getYpos() == tank.getYpos() && shot.getYpos() == tank.getYpos()) {
						System.out.println("SHOT REGISTERED!!!!!!");
						tanks.remove(tank);
						shots.remove(shot);
						return;
					}
				}
			}
		}
	}
	
	public List<Tank> getTanks() {
		return tanks;
	}
	
	public List<Bullet> getShots() {
		return shots;
	}

}
