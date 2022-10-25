import java.util.ArrayList;
import java.util.List;

public class XTankModel {
	private List<Tank> tanks;
	private List<Bullet> shots;
	private XTankUI ui;
	
	public XTankModel() {
		tanks = new ArrayList<Tank>();
		shots = new ArrayList<Bullet>();
	}
	
	public void addTank(Tank newTank) {
		tanks.add(newTank);
	}
	
	public void moveTank(Tank tank, int xMove, int yMove, int dir) {
		tank.move(xMove, yMove, dir);
		System.out.println(tanks);
		ui.updateUI();
	}
	
	public void addShot(Tank tank, int angle) {
		Bullet shot = new Bullet(tank);
		shots.add(shot);
	}
	
	public void moveBullets() {
		for (Bullet shot : shots) {
			shot.move();
		}
		//System.out.println(shots);
		ui.updateUI();
	}
	
	public void regHits() {
		for (Bullet shot : shots) {
			for (Tank tank : tanks) {
				if (shot.getShooter().equals(tank)) continue;
				if (shot.getXpos() >= tank.getXpos() - 10 && shot.getXpos() <= tank.getXpos() + 10) {
					if (shot.getYpos() >= tank.getYpos() - 10 && shot.getYpos() <= tank.getYpos() + 10)
					System.out.println("SHOT REGISTERED!!!!!!");
					tanks.remove(tank);
					shots.remove(shot);
					return;
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
	
	public void setUI(XTankUI ui) { 
		this.ui = ui;
	}

}
