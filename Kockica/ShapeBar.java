package Kockica;

import java.awt.Color;

public class ShapeBar extends Shape {

	public ShapeBar() {
		create(Shape.getRandomColor());
	}
	
public void setXY(int x, int y) {
		
		block[0].x = x;
		block[0].y = y;

		block[1].x = block[0].x  - Block.SIZE;
		block[1].y = block[0].y;
		
		block[2].x = block[0].x + Block.SIZE;
		block[2].y = block[0].y;
		
		block[3].x = block[0].x + Block.SIZE * 2;
		block[3].y = block[0].y;
		
	}
	
public void getDirection1() {
		
		temp[0].x = block[0].x;
		temp[0].y = block[0].y;
		temp[1].x = block[0].x - Block.SIZE;
		temp[1].y = block[0].y;
		temp[2].x = block[0].x + Block.SIZE ;
		temp[2].y = block[0].y;
		temp[3].x = block[0].x + Block.SIZE * 2;
		temp[3].y = block[0].y;
		
		updateXY(1);
		
	}
	public void getDirection2() {
		
		temp[0].x = block[0].x;
		temp[0].y = block[0].y;
		temp[1].x = block[0].x;
		temp[1].y = block[0].y - Block.SIZE;
		temp[2].x = block[0].x;
		temp[2].y = block[0].y + Block.SIZE;
		temp[3].x = block[0].x;
		temp[3].y = block[0].y + Block.SIZE*2;
		
		updateXY(2);
		
	}
	public void getDirection3() {
		
		getDirection1();
		
	}
	public void getDirection4() {
	
		getDirection2();
		
	}
	
}
