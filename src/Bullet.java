import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Bullet extends GameObjs {
	
	private Shooter shooter;
	
	private int deltaX;
	
	public Bullet(final Shooter shooter, final int deltaX, final int xPos, final int yPos, final int width, final int height, final String img) {
		this.shooter = shooter;
		this.deltaX = deltaX;
		this.xPos = xPos;
		this.yPos = yPos;
		this.height = height;
		this.width = width;
		this.rect = new Rectangle(xPos, yPos, width, height);
		this.img = getImage(img);
	}
	
	/**
	 * Graphics
	 */
	
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
			shooter.setHealth(shooter.getHealth() - 1);
			main.bullets.remove(this);
		} else if(xPos < 5 || xPos > 845) {
			main.bullets.remove(this);
		} else {
			xPos += 2;
			rect.x += deltaX;
		}
	}
	
	/**
	 * Getters and Setters
	 */
	
	@Override
	Image getImage(String img) {
		return Toolkit.getDefaultToolkit().getImage(img);
	}
	
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
