package com.wechat.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class ConfigUtil {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		get_cfg_properties("wechat.properties");
	}
	
	public static Map<String, String> get_cfg_properties(String cfgFilePath){
		Map<String, String> map = new HashMap<String, String>();
		try {
			String os = System.getProperty("os.name");  
	        if (os != null && os.startsWith("Windows")) {  
	        	cfgFilePath = "D:/" + cfgFilePath;
	        }else{
	        	cfgFilePath = "/home/chaosheng.zhang/conf/" + cfgFilePath;
	        }
			Configuration config = new PropertiesConfiguration(cfgFilePath);
			Iterator<String> iter = config.getKeys();
			while(iter.hasNext()){
				String k = iter.next();
//				System.out.println(k + "=" + config.getString(k));
				map.put(k, config.getString(k));
			}
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map;
	}

	/**
	 * 优点是：可以在非Web应用中读取配置资源信息，可以读取任意的资源文件信息
	 * 缺点：只能加载类classes下面的资源文件。
	 * @param cfgFilePath
	 * @return
	 */
	public Properties get_cfg_properties_from_classes_path(String cfgFilePath) {
		ConfigUtil.class.getClassLoader();
		InputStream is = ClassLoader.getSystemResourceAsStream(cfgFilePath);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		Properties props = new Properties();

		try {
			props.load(br);
			for (Object s : props.keySet())
				System.out.println(s + "=" + props.getProperty(s.toString()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}
}
