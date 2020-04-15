package com.ss.training.day1;

public class Format {
	
	void newLine() {
		System.out.print("\n\n"); 
	}
	
	static String[] getBunnyType(int type) {
		String[] bunny = new String[2];
		switch(type) {
		   case 2 :
			   bunny[0] = "\n (O.O)\t";
			   bunny[1] = "\n!(___)";
		      break;
		   
		   case 3 :
			   bunny[0] = "\n (-x-)\t";
			   bunny[1] = "\no(___)";
		      break;
		   
		   case 4 :
			   bunny[0] = "\n (^x^)\t";
			   bunny[1] = "\no(___)";
		      break;
		      
		   case 5 :
			   bunny[0] = "\n ='.'=\t";
			   bunny[1] = "\n (___)o";
		      break;
		      
		   default :
			   bunny[0] = "\n ('.')\t";
			   bunny[1] = "\no(___)";
		}
		return bunny;
	}
	
	// splits the rest of the text between line 2 and 3 of the bunny
	static int halfIndex(String[] array) {
		int answer= array.length -1; //we don't want to count the first word
		if (answer == 0) {
			return -1;
		}
		return answer/2;
	}
	
	/* The a bunny is made up of three lines and text after the bunny.
	 * The first lines only has one word after the bunny.
	 * The second and third line divide the rest of the text.  
     */
	void setBunnyText(String text, int type) {
		String bunnyFace = getBunnyType(type)[0];
		String bunnyBody = getBunnyType(type)[1];
		
		String[] tempArray = text.split(" ");
		String newText = " (\\_/)\t"+ tempArray[0] + bunnyFace;
		int count = halfIndex(tempArray);
		int index = 0;
		
		//adding text if needed to line 2
		if ( count == -1) {
			newText += bunnyBody;
			System.out.print(newText); 
			return;
		}
		else if ( count == 0) {
			newText += tempArray[1] + bunnyBody;
			System.out.print(newText); 
			return;
		}
		else {
			while(index != count) {
				newText += tempArray[index + 1] + " ";
				index += 1;
			}
		}
		
		newText += bunnyBody + "\t";
		index += 1;
		//adding text if needed to line three
		while(tempArray.length != index) {
			newText += tempArray[index] + " ";
			index += 1;
		}
		System.out.print(newText);
		return;
	}
	public static void main(String[] args) {

	}

}
