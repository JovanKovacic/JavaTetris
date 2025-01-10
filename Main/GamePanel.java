package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

	public static final int SCREENWIDTH = 1280;
	public static final int SCREENHEIGHT = 720;
	public int FPS = 60; //update se vrsi 60 puta u sekundi
	Thread gameThread;
	PlayManager pm;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(SCREENWIDTH, SCREENHEIGHT));
		this.setBackground(Color.DARK_GRAY);
		this.setLayout(null);
		pm = new PlayManager();
		this.addKeyListener(new KeyHandler());
		this.setFocusable(true);
	}

	public void launchGame() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		//GameLoop
		double drawInterval = 1000000000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta +=(currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				repaint();
				delta--;
			} 
			
		}
		
	}
	
	private void update() {
		if(!KeyHandler.pausePressed && !pm.gameOver) {
			pm.update();
		} 
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		pm.draw((Graphics2D) g); // kastovanje g komponente u 2D g komponentu
	}
	
}
