package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class GenericProperties {
	
	public String getProperties(String key) throws IOException
	{
		FileInputStream fis = new FileInputStream("./src/test/resources/properties/test.properties");
		Properties p = new Properties();
		p.load(fis);
		String value = p.getProperty(key);
		return value ;	
	}
	
	public void setProperties( String key, String value ) throws IOException
	{
		FileOutputStream fis = new FileOutputStream("./src/test/resources/properties/out.properties",true);
		Properties p = new Properties();
		p.setProperty(key, value);
			p.store(fis, "data is written");
	}
	
	
	public String getOutPropertie(String key) throws IOException
	{
		FileInputStream fis = new FileInputStream("./src/test/resources/properties/out.properties");
		Properties p = new Properties();
		p.load(fis);
		String value = p.getProperty(key);
		return value ;	
	}


}
