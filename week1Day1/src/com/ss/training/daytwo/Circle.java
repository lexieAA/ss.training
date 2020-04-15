package com.ss.training.daytwo;

public class Circle implements Shape{
	int radius;
	
	public Circle(int radius){
		this.radius = radius;
	}  

	@Override
	public double calculateArea() {
		double area = Math.PI * this.radius * this.radius;
		return area;
		
	}

	@Override
	public void display() {
		double area = calculateArea();
		System.out.printf("The area of the circle is %.2f", area);
		return;
		
	}
	
	public static void main(String[] args) {
		Circle circle = new Circle(3);
		circle.display();
	}

}
