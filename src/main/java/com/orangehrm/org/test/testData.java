package com.orangehrm.org.test;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;

import com.orangehrm.org.ExcelUtilities;

public class testData {
	
	public static List<String> getTestDataListFromMap(Map<String, List<String>> tdmap, String key){
		
		List<String> value = null;
		try {
			value = tdmap.get(key);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return value;
	}

}
