import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Rocket extends GameObjs {
	
	private Shooter shooter;
	
	private int deltaX;
	
	public Rocket(final Shooter shooter, final int deltaX, final int xPos, final int yPos, final int width, final int height, final String img) {
		this.shooter = shooter;
		this.deltaX = deltaX;
		this.xPos = xPos;
		this.yPos = yPos;
		this.height = height;
		this.width = width;
		this.rect = new Rectangle(xPos, yPos, width, height);
		this.img = getImage(img);
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(img, xPos, yPos, width, height, null);
	}
	
	/**
	 * Updates
	 */

	@Override
	void tick(final Main main, final int id) {
		if(rect.intersects(shooter.rect)) {
			shooter.setHealth(shooter.getHealth() - 5);
			main.rockets.remove(this);
		} else if(xPos < 5 || xPos > 845) {
			main.rockets.remove(this);
		} else {
			xPos += deltaX;
			rect.x += deltaX;
		}
	}
	
	@Override
	Image getImage(String img) {
		return Toolkit.getDefaultToolkit().getImage(img);
	}
	
	/**
	 * Getters and Setters
	 * @return
	 */

	public int getDeltaX() {
		return deltaX;
	}

	public void setDeltaX(int deltaX) {
		this.deltaX = deltaX;
	}

	public Shooter getShooter() {
		return shooter;
	}

	public void setShooter(Shooter shooter) {
		this.shooter = shooter;
	}
}
