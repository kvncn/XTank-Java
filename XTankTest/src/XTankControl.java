public class XTankControl {
	private XTankModel model;
	
	public XTankControl(XTankModel md) {
		model = md;
	}
	
	public void addTank(Tank newTank) {
		model.addTank(newTank);
	}
	
	public void moveTank(Tank tank, int xMove, int yMove, int dir) {
		model.moveTank(tank, xMove, yMove, dir);
	}
	
	public void addShot(Tank tank) {
		model.addShot(tank, tank.getAngle());
	}
	
	public void moveBullets() {
		model.moveBullets();
	}
	
	public void regHits() {
		model.regHits();
	}
}
