package com.goldenweb.beetl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.lang3.math.NumberUtils;

 
public class ConfigUtils {
  private final static URL classPathUrl = ConfigUtils.class.getResource("/");
  private final static String classPath = new File(classPathUrl.getFile()).getPath();
  private static String configPath = "/conf/";
  private final static Map<String, String> configMap = new HashMap<String, String>();
  
  private static Map<String,String> sysParamMap = null;
  
  static {
    setConfigMap();
  }
	 
	public synchronized static String getParamValueByParam(String param){
		if (sysParamMap == null) {
			initSmsParam();
		}
		String result = (String)sysParamMap.get(param.toUpperCase());
		return  result;
	}
	
	private ConfigUtils(){}
	
	
	private static void initSmsParam() {
		String fileName = classPathUrl + "system.properties";
		int n = fileName.lastIndexOf("/");
		InputStream in = ConfigUtils.class.getResourceAsStream(fileName.substring(n));
		Properties pps = new Properties();
		
		try {
			pps.load(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Object[] obj = pps.keySet().toArray();

		sysParamMap = new Hashtable<String, String>();
		for(int i=0;i<obj.length;i++){
			String key = (String)obj[i];
			String value = (String)pps.get(obj[i]);
			sysParamMap.put(key.toUpperCase(),value);
		}
	}
	public static int getToInt(String key) {
    String val = getStr(key);
    return NumberUtils.toInt(val);
  }

  public static long getToLong(String key) {
    String val = getStr(key);
    return NumberUtils.toLong(val);
  }
  
  public static double getToDbl(String key) {
    String val = getStr(key);
    return NumberUtils.toDouble(val);
  }

  public static boolean getToBoolean(String key) {
    String val = getStr(key);
    try {
      return Boolean.valueOf(val);
    } catch (Exception e) {
      return false;
    }
  }
	
	public static String getStr(String key) {
    if (configMap.size() < 0) {
      return null;
    }
    return configMap.get(key);
  }
	
  private static void setConfigMap() {
    String filePath = classPath + configPath;
    List<String> list = findFiles(filePath);
    for (String configName : list) {
      Properties props = getProperties(filePath + configName);
      Iterator<Entry<Object, Object>> it = props.entrySet().iterator();
      while (it.hasNext()) {
        Entry<Object, Object> entry = it.next();
        String key = String.valueOf(entry.getKey());
        String value = String.valueOf(entry.getValue());
        if (!configMap.containsKey(key)) {
          configMap.put(key, value);
        } else {
          System.err.println("CONFIG EEOR:key(" + key + ") is exist;");
        }
      }
    }
  }
  
  /**
   * @Description 获取Properties文件
   * @Title getProperties
   * @param fileName
   * @return Properties
   * @author Lee
   * @time 2016-5-9 下午3:56:01
   */
  private static Properties getProperties(String fileName) {
    Properties props = new Properties();
    try {
      java.io.InputStream propFile = new FileInputStream(fileName);
      props.load(propFile);
    } catch (IOException e) {
      System.err.println("file read fail:" + fileName);
      e.printStackTrace();
    }
    return props;
  }

  
	/**
	 * @Description 查找当前文件下所有properties文件
	 * @Title findFiles
	 * @param baseDirName
	 * @return List<String>
	 * @author Lee
	 * @time 2016-5-9 下午3:55:34
	 */
	private static List<String> findFiles(String baseDirName) {
    List<String> configFiles = new ArrayList<String>();
    File baseDir = new File(baseDirName);
    if (!baseDir.exists() || !baseDir.isDirectory()) {
      System.err.println("search error：" + baseDirName + "is not a dir！");
    } else {
      String[] filelist = baseDir.list();
      for (String fileName : filelist) {
        if (fileName.endsWith("properties")) {
          configFiles.add(fileName);
        }
      }
    }
    return configFiles;
  }
}
