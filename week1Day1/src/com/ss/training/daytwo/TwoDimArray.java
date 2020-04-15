package com.ss.training.daytwo;

public class TwoDimArray {
	
	//return array with max of 2D array at index 0, and position in index 1 and 2
	// [max, row, column]
	public static int[] getMax(int[][] twoDim){
		int[] maxInfo = new int[3];
		for (int j = 0; j < twoDim.length; j++) {
            for (int i = 0; i < twoDim[j].length; i++) {
                if (twoDim[j][i] > maxInfo[0]) {
                	maxInfo[0] = twoDim[j][i];
                    maxInfo[1] = j;
                    maxInfo[2] = i;
                }
            }
        }
            return maxInfo;
	}

	public static void main(String[] args) {
		int[][] twoDim  = {
			      {14, 2, 4}, 
			      {26, 99, 72}, 
			      {1884,999}, 
			};
		System.out.print( "Max: "+ getMax(twoDim)[0]+ "\n");
		System.out.print( "Location of the max in array: "+ getMax(twoDim)[1] + "," + getMax(twoDim)[2]);

	}

}
