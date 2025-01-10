package Kockica;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import Main.KeyHandler;
import Main.PlayManager;

//SUPER KLASA
public class Shape { 

	public Block block[] = new Block[4];
	public Block temp[] = new Block[4];
	int autoDropCounter = 0;
	public int direction = 1;
	boolean leftCollision;
	boolean rightCollision;
	boolean bottomCollision;
	public boolean active = true;
	public boolean deactivating;
	int deactiveCounter = 0;
	
	public void create(Color c) {
		block[0] = new Block(c);
		block[1] = new Block(c); 
		block[2] = new Block(c);
		block[3] = new Block(c);
		temp[0] = new Block(c);
		temp[1] = new Block(c);
		temp[2] = new Block(c);
		temp[3] = new Block(c);
	}
	
	public static Color getRandomColor() {
		
		Color c = null;
		Random r = new Random();
		
		switch(r.nextInt(8)) {
			case 0:
				c = Color.red;
				break;
			case 1:
				c = Color.green;
				break;
			case 2:
				c = Color.yellow;
				break;
			case 3:
				c = Color.magenta;
				break;
			case 4:
				c = Color.orange;
				break;
			case 5:
				c = Color.cyan;
				break;
			case 6:
				c = Color.blue;
				break;
			case 7:
				c = Color.pink;
				break;
		}
		return c;
	}
	
	public void setXY(int x, int y) {
		
	}
	
	public void updateXY(int direction) {
		
		checkRotationCollision();
		
		if(!leftCollision && !rightCollision && !bottomCollision) {
			this.direction = direction;
			block[0].x = temp[0].x;
			block[0].y = temp[0].y;
			block[1].x = temp[1].x;
			block[1].y = temp[1].y;
			block[2].x = temp[2].x;
			block[2].y = temp[2].y;
			block[3].x = temp[3].x;
			block[3].y = temp[3].y;
		}

	}
	
	public void getDirection1() {
		
	}
	public void getDirection2() {
		
	}
	public void getDirection3() {
	
	}
	public void getDirection4() {
	
	}
	
	public void checkMovementCollision() {
		leftCollision = false;
		rightCollision = false;
		bottomCollision = false;
		
		checkStaticBlockCollision();
		
		for(int i=0; i<block.length; i++) {
			if(block[i].x == PlayManager.leftX) {
				leftCollision = true;
			}
		}
		for(int i=0; i<block.length; i++) {
			if(block[i].x + Block.SIZE == PlayManager.rightX) {
				rightCollision = true;
			}
		}
		
		for(int i=0; i<block.length; i++) {
			if(block[i].y + Block.SIZE == PlayManager.bottomY) {
				bottomCollision = true;
			}
		}
	}
	
	public void checkRotationCollision() {
		
		leftCollision = false;
		rightCollision = false;
		bottomCollision = false;
		
		checkStaticBlockCollision();
		
		for(int i=0; i<block.length; i++) {
			if(temp[i].x < PlayManager.leftX) {
				leftCollision = true;
			}
		}
		for(int i=0; i<block.length; i++) {
			if(temp[i].x + Block.SIZE > PlayManager.rightX) {
				rightCollision = true;
			}
		}
		
		for(int i=0; i<block.length; i++) {
			if(temp[i].y + Block.SIZE > PlayManager.bottomY) {
				bottomCollision = true;
			}
		}
		
	}
	
	private void checkStaticBlockCollision() {
		
		for(int i = 0; i<PlayManager.staticBlocks.size(); i++) {
			int targetX = PlayManager.staticBlocks.get(i).x;
			int targetY = PlayManager.staticBlocks.get(i).y;
			
			for(int j = 0; j<block.length; j++) {
				if(block[j].y + Block.SIZE == targetY && block[j].x == targetX) {
					bottomCollision = true;
				}
			}
			
			for(int j = 0; j<block.length; j++) {
				if(block[j].x - Block.SIZE == targetX && block[j].y == targetY) {
					leftCollision = true;
				}
			}
			
			for(int j = 0; j<block.length; j++) {
				if(block[j].x + Block.SIZE == targetX && block[j].y == targetY) {
					rightCollision = true;
				}
			}
			
		}
		
	}
	
	public void update() {
		
		if(deactivating) {
			deactivating();
		}
		
		if(KeyHandler.upPressed) {
			
			switch(direction) {
			case 1:
				getDirection2();
				break;
			case 2:
				getDirection3();
				break;
			case 3:
				getDirection4();
				break;
			case 4:
				getDirection1();
				break;
			}
			KeyHandler.upPressed = false;
		}
		
		checkMovementCollision();
		
		if(KeyHandler.downPressed) {
			
			if(bottomCollision == false) {
				block[0].y += Block.SIZE;
				block[1].y += Block.SIZE; 
				block[2].y += Block.SIZE;
				block[3].y += Block.SIZE;
				autoDropCounter = 0;
			}
			KeyHandler.downPressed = false;
		}

		if(KeyHandler.leftPressed) {
			
			if(leftCollision == false) {
				block[0].x -= Block.SIZE;
				block[1].x -= Block.SIZE; 
				block[2].x -= Block.SIZE;
				block[3].x -= Block.SIZE;
				//autoDropCounter = 0;
				KeyHandler.leftPressed = false;
			}
		}
		
		if(KeyHandler.rightPressed) {
			
			if(rightCollision == false) {
				block[0].x += Block.SIZE;
				block[1].x += Block.SIZE; 
				block[2].x += Block.SIZE;
				block[3].x += Block.SIZE;
				//autoDropCounter = 0;
				KeyHandler.rightPressed = false;
			}
		}
		
		if(bottomCollision) {
			deactivating = true;
			
		} else {
			
			autoDropCounter++;
			
			if(autoDropCounter == PlayManager.dropInterval) {
				block[0].y += Block.SIZE;
				block[1].y += Block.SIZE; 
				block[2].y += Block.SIZE;
				block[3].y += Block.SIZE;
				autoDropCounter = 0;
			}
			
		}
		
	}
	
	private void deactivating() {
		
		deactiveCounter++;
		
		if(deactiveCounter == 15) {
			deactiveCounter = 0;
			checkMovementCollision(); 
			
			if(bottomCollision) {
				active = false;
			}
		}
		
	}
	
	public void draw(Graphics2D g2) {
		
		g2.setColor(block[0].color);
		g2.fillRect(block[0].x, block[0].y, Block.SIZE, Block.SIZE);
		g2.fillRect(block[1].x, block[1].y, Block.SIZE, Block.SIZE);
		g2.fillRect(block[2].x, block[2].y, Block.SIZE, Block.SIZE);
		g2.fillRect(block[3].x, block[3].y, Block.SIZE, Block.SIZE);
		
		
		g2.setColor(Color.black);
		g2.drawRect(block[0].x, block[0].y, Block.SIZE, Block.SIZE);
		g2.drawRect(block[1].x, block[1].y, Block.SIZE, Block.SIZE);
		g2.drawRect(block[2].x, block[2].y, Block.SIZE, Block.SIZE);
		g2.drawRect(block[3].x, block[3].y, Block.SIZE, Block.SIZE);
		
		
		
		
	}
	
}
