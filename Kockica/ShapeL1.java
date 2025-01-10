package Kockica;

import java.awt.Color;

public class ShapeL1 extends Shape {

	public ShapeL1() {
		create(Shape.getRandomColor());
	}
	
	public void setXY(int x, int y) {
		
		// o 1. element
		// o 0. element
		// o 2. element // o 3. element
		
		block[0].x = x;
		block[0].y = y;
		
		block[1].x = block[0].x;
		block[1].y = block[0].y - Block.SIZE;
		
		block[2].x = block[0].x;
		block[2].y = block[0].y + Block.SIZE;
		
		block[3].x = block[0].x + Block.SIZE;
		block[3].y = block[0].y + Block.SIZE;
		
	}
	
	public void getDirection1() {
		temp[0].x = block[0].x;
		temp[0].y = block[0].y;
		temp[1].x = block[0].x;
		temp[1].y = block[0].y - Block.SIZE;
		temp[2].x = block[0].x;
		temp[2].y = block[0].y + Block.SIZE;
		temp[3].x = block[0].x + Block.SIZE;
		temp[3].y = block[0].y + Block.SIZE;
		
		updateXY(1);
		
	}
	public void getDirection2() {
		
		// o o o
		// o
		
		temp[0].x = block[0].x;
		temp[0].y = block[0].y;
		
		temp[1].x = block[0].x + Block.SIZE;
		temp[1].y = block[0].y;
		
		temp[2].x = block[0].x - Block.SIZE;
		temp[2].y = block[0].y;
		
		temp[3].x = block[0].x - Block.SIZE;
		temp[3].y = block[0].y + Block.SIZE;
		
		updateXY(2);
		
	}
	public void getDirection3() {
		 
		//o o
		//  o
		//  o
		
		temp[0].x = block[0].x;
		temp[0].y = block[0].y;
		
		temp[1].x = block[0].x;
		temp[1].y = block[0].y + Block.SIZE;
		
		temp[2].x = block[0].x;
		temp[2].y = block[0].y - Block.SIZE;
		
		temp[3].x = block[0].x - Block.SIZE;
		temp[3].y = block[0].y - Block.SIZE;
		
		updateXY(3);
		
	}
	public void getDirection4() {
	
		temp[0].x = block[0].x;
		temp[0].y = block[0].y;
		
		temp[1].x = block[0].x - Block.SIZE;
		temp[1].y = block[0].y;
		
		temp[2].x = block[0].x + Block.SIZE;
		temp[2].y = block[0].y;
		
		temp[3].x = block[0].x + Block.SIZE;
		temp[3].y = block[0].y - Block.SIZE;
		
		updateXY(4);
		
	}
	
}
