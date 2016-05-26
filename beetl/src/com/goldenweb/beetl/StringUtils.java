package com.goldenweb.beetl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * @Description 字符串处理
 * @author Lee 
 * @date 2014-2-11 上午10:50:09
 */
public class StringUtils {
	public static Double toDouble(String s) {
		try {
			double d = Double.parseDouble(s);
			return new Double(d);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static Float toFloat(String s) {
		try {
			float f = Float.parseFloat(s);
			return new Float(f);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static Long toLong(String s) {
		try {
			long l = Long.parseLong(s);
			return new Long(l);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static Integer toInteger(String s) {
		try {
			int i = Integer.parseInt(s);
			return new Integer(i);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static Byte toByte(String s) {
		try {
			byte b = Byte.parseByte(s);
			return new Byte(b);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String StringFilter(String str) throws PatternSyntaxException {
        String regEx= ConfigUtils.getParamValueByParam("regString");
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }
	
	/**
	 * @Description 首字母大写 ,其余不变
	 * @Title toUpperCaseFirst
	 * @param str
	 * @return String
	 * @author Lee
	 * @time 2016-5-9 下午3:45:59
	 */
	public static String toUpperCaseFirst(String str){
	  if(str==null)
	    return null;
	  if(str.length()==0)
	    return str;
	  String pre = String.valueOf(str.charAt(0));
	  return str.replaceFirst(pre, str.toUpperCase());
	}
	
	/**
	 * @Description 首字母小写 ,其余不变
	 * @Title toLowerCaseFirst
	 * @param str
	 * @return String
	 * @author Lee
	 * @time 2016-5-9 下午3:47:08
	 */
	public static String toLowerCaseFirst(String str){
    if(str==null)
      return null;
    if(str.length()==0)
      return str;
    String pre = String.valueOf(str.charAt(0));
    return str.replaceFirst(pre, str.toLowerCase());
  }
	
	/**
	 * @Description 为空
	 * @Title isEmpty
	 * @param str
	 * @return boolean
	 * @author Lee
	 * @time 2016-5-9 下午4:02:43
	 */
	public static boolean isEmpty(String str) {
    return str == null || "".equals(str);
  }
	
	/**
	 * @Description 不为空
	 * @Title isNotEmpty
	 * @param str
	 * @return boolean
	 * @author Lee
	 * @time 2016-5-9 下午4:03:00
	 */
	public static boolean isNotEmpty(String str) {
    return !isEmpty(str);
  }
	
}
