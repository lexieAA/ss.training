package com.ss.training.day1;

public class Patterns {
	static int created = 1;
	
	
	public static void downPyramid(boolean isfull) {
		int space = 2; //only needed if full pyramid 
		String pattern = created + ")\n";
		created += 1;
		pattern += repeatChar('-', 10) + "\n";
		if(isfull == false) {
			for(int i = 4; i >= 0 ; i--) {
				pattern += repeatChar('*', i) + "\n";
			}
		}
		else {
			int tempNum;
			for(int i = 4; i > 0 ; i--) {
				tempNum = i - 1;
				pattern += repeatChar(' ', space);
				space += 1;
				pattern += repeatChar('*', tempNum + i) + "\n";
			}
		}
		System.out.print(pattern);
		return;
	}
	
	public static void pyramid(boolean isfull) {
		int space = 5; //only needed if full pyramid 
		String pattern = created + ")\n";
		created += 1;
		if(isfull == false) {
			for(int i = 1; i <= 4 ; i++) {
				pattern += repeatChar('*', i) + "\n";
			}
		}
		else {
			int tempNum;
			for(int i = 1; i <= 4 ; i++) {
				tempNum = i - 1;
				pattern += repeatChar(' ', space);
				space -= 1;
				pattern += repeatChar('*', tempNum + i) + "\n";
			}		
		}
		pattern += repeatChar('-', 10) + "\n\n";
		System.out.print(pattern);
		return;
	}
	
	/*give a char (symbol) will return a string repeating that chat
	 * char has many as the int given (times)
	 */
	private static String repeatChar(char symbol, int times) {
		String text = "";
		while(times != 0) {
			text += symbol;
			times -= 1;
		}
		return text;
	}
	
	public static void main(String[] args) {
		pyramid(false);
		downPyramid(false);
		pyramid(true);
		downPyramid(true);
	}

}
