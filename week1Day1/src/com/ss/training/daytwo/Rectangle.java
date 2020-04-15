package com.ss.training.daytwo;

public class Rectangle implements Shape{
	int height;
	int width;
	
	public Rectangle(int height,int width){
		this.height = height;
		this.width = width;
	}  

	@Override
	public double calculateArea() {
		double area = this.height * this.width;
		return area;
		
	}

	@Override
	public void display() {
		double area = calculateArea();
		System.out.printf("The area of the rectangle is %.2f", area);
		return;
		
	}
	
	public static void main(String[] args) {
		Rectangle rectangle = new Rectangle(5,6);
		rectangle.display();
	}

}
