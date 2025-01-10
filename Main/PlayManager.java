package Main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

import Kockica.Block;
import Kockica.ShapeBar;
import Kockica.ShapeL1;
import Kockica.ShapeL2;
import Kockica.ShapeSquare;
import Kockica.ShapeT;
import Kockica.ShapeZ1;
import Kockica.ShapeZ2;

public class PlayManager {

	final int PLAYWIDTH =  384;//384;
	final int PLAYHEIGHT = 640;//640;
	public static int leftX;
	public static int rightX;
	public static int topY;
	public static int bottomY;
	
	Kockica.Shape currentShape;
	final int shapeStartX;
	final int shapeStartY;
	Kockica.Shape nextShape;
	final int NEXTSHAPEX;
	final int NEXTSHAPEY;
	public static ArrayList<Block> staticBlocks = new ArrayList<>();
	
	public static int dropInterval = 60;
	boolean gameOver;
	
	int level = 1;
	int lines;
	int score;
	
	boolean effectsOn;
	int effectCounter;
	ArrayList<Integer> effectY = new ArrayList<Integer>();
	
	public PlayManager() {
		leftX = (GamePanel.SCREENWIDTH/2) - (PLAYWIDTH/2);
		rightX = leftX + PLAYWIDTH;
		topY = 50;
		bottomY = topY + PLAYHEIGHT;
		
		shapeStartX = leftX + (PLAYWIDTH / 2) - Block.SIZE;
		shapeStartY = topY + Block.SIZE;
		
		NEXTSHAPEX = rightX + 175;
		NEXTSHAPEY = topY + 100;
		
		currentShape = pickShape();
		currentShape.setXY(shapeStartX, shapeStartY);
		
		nextShape = pickShape();
		nextShape.setXY(NEXTSHAPEX, NEXTSHAPEY);
		
	}
	
	private Kockica.Shape pickShape() {
		
		Kockica.Shape shape = null;
		
		int i = new Random().nextInt(7);
		
		switch(i) {
		case 0:
			shape = new ShapeL1();
			break;
		case 1:
			shape = new ShapeL2();
			break;
		case 2:
			shape = new ShapeT();
			break;
		case 3:
			shape = new ShapeBar();
			break;
		case 4:
			shape = new ShapeSquare();
			break;
		case 5:
			shape = new ShapeZ1();
			break;
		case 6:
			shape = new ShapeZ2();
			break;
		}
		
		return shape;
		
	}
	
	public void update() {
		
		if(!currentShape.active) {
			staticBlocks.add(currentShape.block[0]);
			staticBlocks.add(currentShape.block[1]);
			staticBlocks.add(currentShape.block[2]);
			staticBlocks.add(currentShape.block[3]);
			
			if(currentShape.block[0].x == shapeStartX && currentShape.block[0].y == shapeStartY) {
				gameOver = true;
			}
			
			currentShape.deactivating = false;
			
			currentShape = nextShape;
			currentShape.setXY(shapeStartX, shapeStartY);
			nextShape = pickShape();
			nextShape.setXY(NEXTSHAPEX, NEXTSHAPEY);
			
			checkDelete();
			
		} else {
			currentShape.update();
		}
		
	}
	
	private void checkDelete() {
		
		int x = leftX;
		int y = topY;
		int blockCount = 0;
		int lineCount = 0;
		
		
		while(x < rightX && y < bottomY) {
			
			for(int i = 0; i < staticBlocks.size(); i++) {
				if(staticBlocks.get(i).x == x && staticBlocks.get(i).y == y) {
					blockCount++;
				}
			}
			
			x += Block.SIZE;
			
			if(x == rightX) {	
				
				if(blockCount == 12) {
					
					 effectsOn = true;
					 effectY.add(y);
					
					for(int i = staticBlocks.size() - 1; i > -1; i--) {
						if(staticBlocks.get(i).y == y) {
							staticBlocks.remove(i);
						}
					}
					
					lineCount++;
					lines++;
					
					if(lines % 10 == 0 && dropInterval > 1) {
						
						level++;
						if(dropInterval > 10) {
							dropInterval -= 10;
						} else {
							dropInterval -= 2;
						}
						
					}
					
					for(int i = 0; i<staticBlocks.size(); i++) {
						if(staticBlocks.get(i).y < y) {
							staticBlocks.get(i).y += Block.SIZE;
						}
					}
					
				}
				
				blockCount = 0;
				x = leftX;
				y += Block.SIZE;
			}
		}
		
		if(lineCount > 0) {
			int singleLineScore = 10 * level;
			score += singleLineScore * lineCount;
		}
		
	}
	
	public void draw(Graphics2D g2) {
		g2.setColor(Color.magenta);
		g2.setStroke(new BasicStroke(4f));
		g2.drawRect(leftX - 4, topY - 4, PLAYWIDTH + 8, PLAYHEIGHT + 8);
		
		g2.drawRect(rightX + 100, topY, 200, 200);
		g2.setFont(new Font("Rubik Vinyl", Font.PLAIN, 50));
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.drawString("Next", rightX + 137, topY + 60);
		
		g2.setFont(new Font("Rubik Vinyl", Font.PLAIN, 120));
		g2.drawString("Jox's",  20, topY + 70);
		g2.drawString("Tetris",  20, topY + 180);
		
		g2.setFont(new Font("Rubik Vinyl", Font.PLAIN, 40));
		g2.drawRect(rightX + 100, topY + 300, 250, 300);
		g2.drawString("Level: " + level, rightX + 120, topY + 350);
		g2.drawString("Lines: " + lines, rightX + 120, topY + 430);
		g2.drawString("Score: " + score, rightX + 120, topY + 510);
		
		if(currentShape != null) {
			currentShape.draw(g2);
		}
		
		nextShape.draw(g2);
		
		for(int i = 0; i < staticBlocks.size(); i++) {
			staticBlocks.get(i).draw(g2);
		}
		
		if(effectsOn) {
			effectCounter++;
			g2.setColor(Color.red); 
			for(int i = 0; i<effectY.size(); i++) {
				g2.fillRect(leftX, effectY.get(i), PLAYWIDTH, Block.SIZE);
			}
			
			if(effectCounter == 10) {
				effectsOn = false;
				effectCounter = 0;
				effectY.clear();
			}
			
		}
		
		if(gameOver) {
			g2.setFont(new Font("Rubik Vinyl", Font.PLAIN, 120));
			g2.setColor(Color.red);
			g2.drawString("GAME OVER", leftX - 170, topY + 320);
		} else if(KeyHandler.pausePressed) {
			g2.setFont(new Font("Rubik Vinyl", Font.PLAIN, 120));
			g2.setColor(Color.green);
			g2.drawString("PAUSED", leftX-60, topY + 320);
		}
		
	}
	
	
}
