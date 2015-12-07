import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JFrame;

public class Main extends JFrame implements KeyListener {

	private static final long serialVersionUID = 3206847208968227199L;
	
	private Shooter player1;
	private Shooter player2;
	private Image image;
	private Graphics graphics;
	
	public boolean player1Up = false;
	public boolean player1Down = false;
	public boolean player2Up = false;
	public boolean player2Down = false;
	
	CopyOnWriteArrayList<Bullet> bullets = new CopyOnWriteArrayList<Bullet>();
	CopyOnWriteArrayList<Rocket> rockets = new CopyOnWriteArrayList<Rocket>();
	
	public Main() {
		setTitle("Shoot the other player");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(850, 567);
		setBackground(Color.RED);
		setResizable(false);
		addKeyListener(this);
		setVisible(true);
		
		player1 = new Shooter(10, 216, 20, 90, 10, "res/player1.png");
		player2 = new Shooter(820, 216, 20, 90, 10, "res/player2.png");
	}
	
	/**
	 * Graphics
	 */
	
	public void paint(Graphics g) {
		image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics();
		
		g.fillRect(0, 0, getHeight(), getWidth());
		
		paintComponent(graphics);
		g.drawImage(image, 0, 0, null);
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		if(player1.getHealth() > 0 && player2.getHealth() > 0) {
			for(Bullet bullet : bullets) {
				bullet.draw(g);
				bullet.tick(this, 0);
			}
		} else if(player1.getHealth() == 0) {
			g.setColor(Color.BLACK);
			g.drawString("Player 2 won", 375, 275);
		} else if(player2.getHealth() == 0) {
			g.setColor(Color.BLACK);
			g.drawString("Player one one", 375, 275);
		}
		
		if(player1.getHealth() > 0 && player2.getHealth() > 0) {
			for(Rocket rocket : rockets) {
				rocket.draw(g);
				rocket.tick(this, 0);
			}
		} else if(player1.getHealth() == 0) {
			g.setColor(Color.BLACK);
			g.drawString("Player 2 won", 375, 275);
		} else if(player2.getHealth() == 0) {
			g.setColor(Color.BLACK);
			g.drawString("Player one one", 375, 275);
		}
		
		player1.draw(g);
		player1.tick(this, 1);
		player2.draw(g);
		player2.tick(this, 2);
	}
	
	/**
	 * Key Events
	 */
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			player2Up = true;
		} else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player2Down = true;
		} else if(e.getKeyCode() == KeyEvent.VK_W) {
			player1Up = true;
		} else if(e.getKeyCode() == KeyEvent.VK_S) {
			player1Down = true;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			player2Up = false;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			player2Down = false;
		} else if (e.getKeyCode() == KeyEvent.VK_W) {
			player1Up = false;
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			player1Down = false;
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			Bullet player1Bullet = new Bullet(player2, 1, player1.getxPos() + 20, player1.getyPos() + 40, 4, 4, "res/bullet.png");
			bullets.add(player1Bullet);
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			Bullet player2Bullet = new Bullet(player1, -1, player2.getxPos() - 40, player2.getyPos() + 40, 4, 4, "res/bullet.png");
			bullets.add(player2Bullet);
		} else if (e.getKeyCode() == KeyEvent.VK_V) {
			Rocket player1Rocket = new Rocket(player2, 1, player1.getxPos() + 20, player1.getyPos() + 40, 4, 4, "res/rocket.png");
			rockets.add(player1Rocket);
		} else if (e.getKeyCode() == KeyEvent.VK_P) {
			Rocket player2Rocket = new Rocket(player1, -1, player2.getxPos() - 4, player2.getyPos() + 40, 4, 4, "res/rocket.png");
			rockets.add(player2Rocket);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	public static void main(String[] args) {
		new Main();
	}
	
	/**
	 * Getters and Setters
	 * @return
	 */
	
	public Shooter getPlayer1() {
		return player1;
	}

	public void setPlayer1(Shooter player1) {
		this.player1 = player1;
	}

	public Shooter getPlayer2() {
		return player2;
	}

	public void setPlayer2(Shooter player2) {
		this.player2 = player2;
	}
}
