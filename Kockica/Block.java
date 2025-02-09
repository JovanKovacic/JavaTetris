package Kockica;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Block extends Rectangle {

	 public int x;
	 public int y;
	 public static final int SIZE = 32;
	 public Color color;
	 
	 public Block(Color color) {
		 this.color = color;
	 }
	
	 public void draw(Graphics2D g2) {
		 int margine = 3;
		 
		 g2.setColor(color);
		 g2.fillRect(x, y, SIZE, SIZE);
		 g2.setColor(Color.black);
		 g2.drawRect(x, y, SIZE, SIZE);
		 
	 }
	 
}
