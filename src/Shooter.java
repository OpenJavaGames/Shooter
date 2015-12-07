import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Shooter extends GameObjs {
	
	public Shooter(final int xPos, final int yPos, final int width, final int height, final int health, final String img) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.height = height;
		this.width = width;
		this.rect = new Rectangle(xPos, yPos, width, height);
		this.health = health;
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
	void tick(final Main main, int id) {
		if(id == 1) {
			if(main.player1Up) {
				if(!(yPos < 26)) {
					yPos--;
					rect.y--;
				}
			} else if(main.player1Down) {
				if(!(yPos > main.getHeight() - 96)) {
					yPos++;
					rect.y++;
				}
			}
			
		} else if(id == 2) {
			if(main.player2Up) {
				if(!(yPos < 26)) {
					yPos--;
					rect.y--;
				}
			} else if(main.player2Down) {
				if(!(yPos > main.getHeight() - 96)) {
					yPos++;
					rect.y++;
				}
			}
		}
	}
	
	/**
	 * Getters and Setters
	 */

	@Override
	Image getImage(String img) {
		return Toolkit.getDefaultToolkit().getImage(img);
	}
}
