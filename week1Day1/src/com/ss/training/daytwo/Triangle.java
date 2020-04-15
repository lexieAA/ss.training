package com.ss.training.daytwo;

public class Triangle implements Shape{
	int height;
	int base;
	
	public Triangle(int height,int base){
		this.height = height;
		this.base = base;
	}
	
	@Override
	public double calculateArea() {
		double area = 0.5 * this.height * this.base;
		return area;
	}

	@Override
	public void display() {
		double area = calculateArea();
		System.out.printf("The area of the triangle is %.2f", area);
		return;
		
	}
	
	public static void main(String[] args) {
		Triangle triangle = new Triangle(5,10);
		triangle.display();

	}
}
