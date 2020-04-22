package com.ss.training.sort;

import java.util.Arrays;

public class SortStrings {
	public static String[] sortShortestLen(String[] myArray) {
		Arrays.sort(myArray, (strOne,strTwo)->strOne.length() - strTwo.length());
		return myArray;
		
	}
	
	public static String[] sortLongestLen(String[] myArray) {
		Arrays.sort(myArray, (strOne,strTwo)->strTwo.length() - strOne.length());
		return myArray;
		
	}

	public static String[] sortAlphabet(String[] myArray) {
		Arrays.sort(myArray, (strOne,strTwo)->strOne.charAt(0) - strTwo.charAt(0));
		return myArray;
		
	}
	
	public static String[] eFirst(String[] myArray) {
		String[] listE = Arrays.stream(myArray)
				.filter(item -> item.startsWith("E")).toArray(String[]::new);
		String[] listWithoutE = Arrays.stream(myArray)
				.filter(item -> !item.startsWith("E")).toArray(String[]::new);
				
		String[] result = combineStrArrays(listE, listWithoutE);
	
		return result;
		
	}
	
	public static String[] combineStrArrays(String[] strOne, String[] strTwo){
        int length = strOne.length + strTwo.length;
        String[] result = new String[length];
        System.arraycopy(strOne, 0, result, 0, strOne.length);
        System.arraycopy(strTwo, 0, result, strOne.length, strTwo.length);
        return result;
    }
	
	public static String[] eFirstVersionTwo(String[] myArray) {
				
		Arrays.sort(myArray, (strOne,strTwo) -> eStrFirst(strOne,strTwo));
	
		return myArray;
		
	}
	
	public static int eStrFirst(String strOne, String strTwo){
		if(strTwo.startsWith("E")) {
			return strOne.charAt(0) - strTwo.charAt(0);
			
		}else {
			return strTwo.charAt(0) - strOne.charAt(0);
		}
	}
	
	public static String[] onlyA(String[] myArray) {
		String[] result = Arrays.stream(myArray)
				.filter(item -> item.startsWith("a") && item.length() == 3).toArray(String[]::new);
	
		return result;
		
	}
}
