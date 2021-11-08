package com.vtiger.comcast.genericUtilty;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
/**
 * This class is used to read the properties file
 * @author Chandan
 *
 */
public class FileUtility {

	/**
	 * This method will give you the value from the data properties file
	 * @param key
	 * @return String
	 * @throws IOException
	 */
	public String propertyFile(String key) throws IOException
	{
	FileInputStream fis = new FileInputStream("./src/test/resources/data.properties");
	Properties pobj = new Properties();
	pobj.load(fis);
	String value = pobj.getProperty(key);
	return value;
	}
	
}