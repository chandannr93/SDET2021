package com.vtiger.comcast.genericUtilty;

import java.util.Date;
import java.util.Random;

/**
 * this class contains Java specific Libraries(methods)
 * @author Chandan
 *
 */
public class JavaUtility {

	/**
	 * this method is used to generate random integer number with in the boundary of 0 to 10000
	 * @return int_Data  
	 */
		public int random()
		{
		Random random = new Random();
		int randomNum = random.nextInt(10000);
		return randomNum;
		}
		
		/**
		 * this method is used to generate SystemDate in YYYY-MM-DD format
		 * @return string_Data
		 */
		
		public String systemdate_YYYY_MM_DD()
		{
			Date date = new Date();
			String[] systemDateTime = date.toString().split(" ");
			String DD = systemDateTime[2];
			String YYYY = systemDateTime[5];
			int MM = date.getMonth()+1;
			String finalFormat = YYYY+"-"+MM+"-"+DD;
			return finalFormat;
		}
		
	

}