package com.orangehrm.org;

import java.util.LinkedList;
import java.util.List;

public class ArrayUtilities {
	
	public static List<String> retrieveInList(String[][] arrayvalue){
		
		List<String> listStr = null;
		
		try {
			
			listStr = new LinkedList<String>();			
			
			for (int rowindex = 0; rowindex < arrayvalue.length; rowindex++) {
				
				for (int columnindex = 0; columnindex < arrayvalue[rowindex].length; columnindex++) {
						
					System.out.println(arrayvalue[rowindex][columnindex]);
					listStr.add(arrayvalue[rowindex][columnindex]);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listStr;
		
	}

}
