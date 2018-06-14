package utils.linUtils;

import java.io.BufferedReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import com.jfinal.plugin.activerecord.Record;

import pay.wxpay.web.services.MD5Util;


/**
 * 通用工具类
 * 
 * @author xuj 2013.5.7
 * 
 */
public class Utils {
	
	
	
	/**
	* @方法名: 计算时间差 ！  分钟
	* @参数:  end_time 结束时间      nowTime 当前时间
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2017年12月20 7:59:56
	* @修改:
	*/ 
	public static Integer countTimeMinute(String endTime  ,String nowTime){
		long i = Utils.getDateFromStrTwo(nowTime).getTime();
		long j  = Utils.getDateFromStrTwo(endTime).getTime();
		Integer d  = 0;
		if(i>j){
			long a 	= i-j;
			double c= Double.valueOf(a);
			double b  = c/60/1000;
			d =(int)Math.ceil(b);
		}
		return d;
	}
	
	public static String string2MD5(String inStr){
		MessageDigest md5 = null;
		try{
			md5 = MessageDigest.getInstance("MD5");
		}catch (Exception e){
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];
		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++){
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}
  
  /**
	* @方法名: 数据加密 按位亦或/两次亦或还原
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年1月8 5:11:06
	* @修改:
	*/ 
	public static String convertMD5Two(String inStr){
		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++){
			a[i] = (char) (a[i] ^ 'a');
		}
		String s = new String(a);
		return s;
	}

	
	public static String convertMD5(String inStr){
		return inStr;
	}

	
	

	
	
	/**
	* @方法名: 计算天数以内的时间长度
	* @参数: 开始时间  start_time    结束时间：end_time  
	* @输出:list: get(0):不优惠时间长度        get(1)优惠时间长度
	* @备注: 优惠时间是 晚上 9点到早上7点   10个小时
	* @作者: 林
	* @时间: 2018年1月18 7:45:06
	* @修改:
	*/ 
	public static List<Long> getDiscountTime(String start_time ,String end_time){
		//String start_time = "2018-01-17 01:00:00";
		//String end_time = "2018-01-18 24:00:00";
		int old_long = Utils.countTimeHour(start_time, end_time); 
		//System.out.println(start_time.substring(11)+":"+Utils.getDateFromStrFour(start_time.substring(11)).getTime());
		//System.out.println(end_time.substring(11)+":"+Utils.getDateFromStrFour(end_time.substring(11)).getTime());
		List<Long> list= new ArrayList<Long>();
		if(start_time.substring(0,10).equals(end_time.substring(0,10))){
			long day = old_long;
			long  o = Utils.countTimeHour(start_time.substring(0,10)+" 00:00:00", start_time);
			long  i =  Utils.countTimeHour(end_time, end_time.substring(0,10)+" 24:00:00");
			long taday = 24-i-o;
			long open  = old_long; 
			long close = 0;
			if(o<7){
				close = close + Utils.countTimeHour( start_time , start_time.substring(0,10)+" 07:00:00");
				open = open - close;
			}
			if(i<3){
				close = close + Utils.countTimeHour(  end_time.substring(0,10)+" 21:00:00", end_time );
				open = open - close;
			}
			if(old_long == 1){
				if(Integer.valueOf(start_time.substring(11,13))>=21 ||Integer.valueOf(start_time.substring(11,13)) < 7 ){
					open  = 0 ; 
					close = 1 ;
				}else{
					open  = 1 ; 
					close = 0 ;
				}
				
			}
			list.add(open);
			list.add(close);
		}else{
		
    	double time = Utils.getDateFromStrFour(start_time.substring(11)).getTime();
    	int day = 0;
    	if(time>Utils.getDateFromStrFour(end_time.substring(11)).getTime()){
    		day	= old_long/24;    	
    	}else{
    		day = old_long/24-1;
    	}
    	//不优惠时间
    	long open  =  day*14;
    	//优惠时间
    	long close  = day*10;
    	
    	long begin_time =  	Utils.countTimeHourTwo(start_time, start_time.substring(0,10)+" 24:00:00");
    	long to_end  =	Utils.countTimeHourTwo(end_time.substring(0,10)+" 00:00:00", end_time);
    	/*if(begin_time+to_end>24){
    		to_end--;
    	}*/
    	//(start2-start)/1000/60/60;
    	// (end-end2)/1000/60/60;
    	if(begin_time < 3){
    		close = close+ begin_time;
    	}else{
    		close = close + 3;
    		open = open +begin_time-3;
    	}
    	if(to_end > 7){
    		close = close+ 7;
    		open = open +to_end -7; 
    	}else{
    		close = close + to_end;
    	}		
    	list.add(open);
		list.add(close);
    	//System.out.println("open:"+open+" , close:"+close);
		//System.out.println("old_time:" + old_long);
		}
	return list;
	}
	
	/**
	* @方法名: 计算时间差 ！  时  向上取整
	* @参数:  end_time 结束时间      nowTime 当前时间
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2017年12月20 7:59:56
	* @修改:
	*/ 
	public static Integer countTimeHour(String start_time  ,String end_time){
		long j  = Utils.getDateFromStrTwo(start_time).getTime();
		long i = Utils.getDateFromStrTwo(end_time).getTime();
		Integer d  = 0;
		if(i>j){
			long a 	= i-j;
			double c= Double.valueOf(a);
			double b  = c/60/1000/60;
			d =(int)Math.ceil(b);
		}
		return d;
	}
	
	
	/**
	* @方法名: 计算时间差 ！  时 四舍五入
	* @参数:  end_time 结束时间      nowTime 当前时间
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2017年12月20 7:59:56
	* @修改:
	*/ 
	public static Integer countTimeHourTwo(String start_time  ,String end_time){
		long j  = Utils.getDateFromStrTwo(start_time).getTime();
		long i = Utils.getDateFromStrTwo(end_time).getTime();
		Integer d  = 0;
		if(i>j){
			long a 	= i-j;
			double c= Double.valueOf(a);
			double b  = c/60/1000/60;
			d =(int)Math.round(b);
		}
		return d;
	}
	
	
	/**
	* @方法名:
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2017年12月27 3:37:48
	* @修改:
	*/ 
	public static String getPage(String page){
		Integer p= Integer.valueOf(page);
			p = p*10;
			int pagesize = p-10;
		return pagesize+","+p;
	}
	
	/**
	 * @方法名:获取服务器地址
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2016年12月28 3:48:18
	 * @修改:
	 */
	public static String getLoaclURL(HttpServletRequest request) {

		String path_now = request.getContextPath();
		return request.getScheme() + "://" + request.getServerName() + ":"
				+ request.getServerPort() + path_now + "/";
	}
	/**获取取随意长度的（数字）或（数字+字符）的验证码*/
	public static String getValidateCode(boolean isNumber,int leng){
		String validateCode="";
		String  validateString=isNumber?"1234567890":"1234567890ABCDEFGHIJKLMNPQRSTUVWXWZ";
		boolean isTure=true;
		while(isTure){
			int count=0;
			for(int i=0;i<leng;i++){
				double dbR=Math.random()*validateString.length();
				int intR=(int)Math.floor(dbR);
				char c=validateString.charAt(intR);
				if(('0'<=c) && (c<='9')){
					count++;
				}
				validateCode+=validateString.charAt(intR);
			}
			if(count>=2){
				isTure=false;
			}
		}
		return validateCode;
	}
	
	
	
	
	/**
	* @方法名: 高德地图两点经纬度计算距离
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2017年12月22 11:44:56
	* @修改:
	*/ 
	public static float calculateLineDistance(double latitude1, double longitude1, double latitude2, double longitude2) {
        double var2 = 0.01745329251994329D;
        double var4 = longitude1;
        double var6 = latitude1;
        double var8 = longitude2;
        double var10 = latitude2;
        var4 *= 0.01745329251994329D;
        var6 *= 0.01745329251994329D;
        var8 *= 0.01745329251994329D;
        var10 *= 0.01745329251994329D;
        double var12 = Math.sin(var4);
        double var14 = Math.sin(var6);
        double var16 = Math.cos(var4);
        double var18 = Math.cos(var6);
        double var20 = Math.sin(var8);
        double var22 = Math.sin(var10);
        double var24 = Math.cos(var8);
        double var26 = Math.cos(var10);
        double[] var28 = new double[3];
        double[] var29 = new double[3];
        var28[0] = var18 * var16;
        var28[1] = var18 * var12;
        var28[2] = var14;
        var29[0] = var26 * var24;
        var29[1] = var26 * var20;
        var29[2] = var22;
        double var30 = Math.sqrt((var28[0] - var29[0]) * (var28[0] - var29[0]) + (var28[1] - var29[1]) * (var28[1] -
                var29[1]) + (var28[2] - var29[2]) * (var28[2] - var29[2]));
        return (float) (Math.asin(var30 / 2.0D) * 1.27420015798544E7D);
    }
	
	
	/**
     * 判断是否为String转换的Unicode还是只是String
	 * @日期:2017-11-07
	 * @作者:林連根
     * @param s
     * @return
     */
    public static boolean isUnicodeString(String s) {
        //-u6211-u7684-u624b-ud83d-ude16-ud83d-ude0f-ud83d-ude14
        //先判断String长度能否被6整除
        if (s.length() % 6 == 0) {
            int count = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '-') {
                    count++;
                }
            }
            //判断-出现的次数是否等于s的长度除以6
            if (count == s.length() / 6) {
                //再判断-的右边是否是u
                boolean isUnicodeString = true;
                char[] s_char = s.toCharArray();
                for (int i = 0; i < s.length(); i++) {
                    if (s_char[s.indexOf('-') + 1] != 'u') {
                        isUnicodeString = false;
                    }
                }  
                return isUnicodeString;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    
    /**
     * @方法名:格式化金额
     * @参数:
     * @输出:
     * @备注:
     * @作者: Lin
     * @时间: 2017年7月28 9:26:15
     * @修改:
     */
    public static String formatMoney(String money) {
	double result = 0.00f;
	try {
	    result = Double.parseDouble(money);
	} catch (Exception e) {
	    return "0.00";
	}

	DecimalFormat df = new DecimalFormat("######0.00");
	return df.format(result);
    }
    
    /**
     * @方法名:格式化金额 double
     * @参数:
     * @输出:
     * @备注:
     * @作者: Lin
     * @时间: 2017年7月28 9:26:15
     * @修改:
     */
    public static double formatMoney(double money) {
	double result = 0.00f;
	try {
	    result = money;
	} catch (Exception e) {
	    return 0.00;
	}

	DecimalFormat df = new DecimalFormat("######0.00");
	double ppp = 	Double.valueOf(df.format(result));
	return ppp;
    }
    
    public static void main(String[] args) {
    	
}
    
    /**
     * @方法名:格式化数据保留6位小数
     * @参数:
     * @输出:
     * @备注:
     * @作者: 徐磊
     * @时间: 2017年7月28 9:26:15
     * @修改:
     */
    public static String formatNumberTo6(String money) {
	double result = 0.000000f;
	try {
	    result = Double.parseDouble(money);
	} catch (Exception e) {
	    return "0.000000";
	}

	DecimalFormat df = new DecimalFormat("######0.000000");
	return df.format(result);
    }
	/**
	 * @日期:2017-11-07
	 * @作者:林連根
     * 字符串转换unicode
     */
    public static String string2Unicode(String string) {

        StringBuffer unicode = new StringBuffer();

        for (int i = 0; i < string.length(); i++) {

            // 取出每一个字符
            char c = string.charAt(i);
            if(c < 256)//ASC11表中的字符码值不够4位,补00
            {
                unicode.append("\\u00");
            }
            else
            {
                unicode.append("\\u");
            }
            // 转换为unicode
            unicode.append(Integer.toHexString(c));
        }

        return unicode.toString();
    }
	
    
	/**
	 * @日期：2017-11-07
	 * @作者：林連根
     * unicode 转字符串
     */
    public static String unicode2String(String s) {
        StringBuffer string = new StringBuffer();
        String[] hex = s.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);
            // 追加成string
            string.append((char) data);
        }
        return string.toString();
    }
    
	public static String getStrVal(Object o) {
		String result = (o != null) ? o.toString() : "";
		return result;
	}

	public static String getStrDefaultVal(Object o, String defaultVal) {
		String result = (o != null) ? o.toString() : defaultVal;
		return result;
	}

	public static int getIntVal(Object o, int defaultVal) {
		String resultStr = (o != null) ? o.toString() : "";
		int result = isNum(resultStr) ? Integer.parseInt(resultStr)
				: defaultVal;
		return result;
	}

	public static long getLongVal(Object o, long defaultVal) {
		String resultStr = (o != null) ? o.toString() : "";
		long result = isNum(resultStr) ? Long.parseLong(resultStr) : defaultVal;
		return result;
	}
	/**
	 * 生成随机字符串   传入串串长度
	 * @param length
	 * @return
	 */
	public static String CreateNoncestr(int length) {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String res = "";
		for (int i = 0; i < length; i++) {
			Random rd = new Random();
			res += chars.indexOf(rd.nextInt(chars.length() - 1));
		}
		return res;
	}
/**
 * 生成随机字符串  16位
 * @return
 */
	public static String CreateNoncestr() {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String res = "";
		for (int i = 0; i < 16; i++) {
			Random rd = new Random();
			res += chars.charAt(rd.nextInt(chars.length() - 1));
		}
		return res;
	}
	public static Float getFloatVal(Object o, Float defaultVal) {
		String resultStr = (o != null) ? o.toString() : "";
		Float result = isDecimal(resultStr) ? Float.parseFloat(resultStr)
				: defaultVal;
		return result;
	}

	public static Double getDoubleVal(Object o, Double defaultVal) {
		String resultStr = (o != null) ? o.toString() : "";
		Double result = isDecimal(resultStr) ? Double.parseDouble(resultStr)
				: defaultVal;
		return result;
	}
	
	/**
	 * 格式：yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public static String getStrFormDate(Date date) {
		SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	
	
	public static Date getDateFromStr(String date) {
		SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.getMessage();
		}
		return null ;
	}
	
	
	public static Date getDateFromStrFour(String date) {
		SimpleDateFormat sdf  = new SimpleDateFormat("HH:mm:ss");
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.getMessage();
		}
		return null ;
	}
	
	/**
	 * 格式：yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String getStrFormDateTwo(Date date) {
		SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	public static Date getDateFromStrTwo(String date) {
		SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.getMessage();
		}
		return null ;
	}
	
	
	public static String getStrFormDateThree(Date date) {
		SimpleDateFormat sdf  = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(date);
	}
	/**
	* @方法名: yyyy-MM-DD
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2017年12月27 5:12:40
	* @修改:
	*/ 
	public static String getStrFormDateYYYYMMDD(Date date) {
		SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-DD");
		return sdf.format(date);
	}
	
	
	public static Date getDateFromStrThree(String date) {
		SimpleDateFormat sdf  = new SimpleDateFormat("HH:mm:ss");
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.getMessage();
		}
		return null ;
	}
	public static Boolean getBooleanVal(Object o, Boolean defaultVal) {
		String resultStr = (o != null) ? o.toString() : "";
		if (StringUtils.equals(resultStr, "true")) {
			return true;
		} else if (StringUtils.equals(resultStr, "false")) {
			return false;
		} else {
			return defaultVal;
		}
	}
	
	public static boolean isNum(String str) {
		if (str == null || "".equals(str.trim())) {
			return false;
		}
		Pattern pattern = Pattern.compile("^\\-?[0-9]+$");
		return pattern.matcher(str).matches();
	}

	public static boolean isDecimal(String str) {
		if (str == null || "".equals(str))
			return false;
		Pattern pattern = Pattern.compile("\\-?[0-9]+(\\.\\d+)?");
		return pattern.matcher(str).matches();
	}

	public static synchronized String getNowStr(String pattern) {

		String str = DateTime.now()
				.toString(DateTimeFormat.forPattern(pattern));
		return str;
	}

	/**
	 * 验证邮箱地址是否正确
	 * 
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email) {
		boolean flag = false;
		try {
			String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}

		return flag;
	}

	/**
	 * 验证手机号码
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNO(String mobiles) {
		boolean flag = false;
		try {
			Pattern p = Pattern
					.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
			Matcher m = p.matcher(mobiles);
			flag = m.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	public static String numFormat(int len, int num) {
		NumberFormat formatter = NumberFormat.getNumberInstance();
		formatter.setMinimumIntegerDigits(len);
		formatter.setGroupingUsed(false);
		String numStr = formatter.format(num);
		return numStr;
	}

	public static String toJson(Object obj) {
		String jsonStr = "{}";
		if (obj != null) {
			Gson gson = new Gson();
			jsonStr = gson.toJson(obj);
			jsonStr = jsonStr.replace("\\u003d", "=").replace("\\u0026", "&")
					.replace("\\u0027", "'");
		}
		return jsonStr;
	}

	/*// 解密
	public static String decryptResult(byte[] req) {
		String decryptStr = "";
		try {
//			decryptStr = RSAUtils.decryptByPrivateKey(requestStr,
//					Common.PRIVATE_KEY);
			decryptStr = AESUtils.decryptByte(Common.AES_KEY, req);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return decryptStr;
	}*/

	/*// 加密
	public static byte[] encryptResult(String responseStr) {
		byte[] encryptedData = null;
		try {
			encryptedData = AESUtils.encryptByte(Common.AES_KEY, responseStr);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return encryptedData;
	}
*/
	// 获取解密参数
	@SuppressWarnings("unchecked")
	public static HashMap<String, String> judgeParams(String jsonStr) {
		HashMap<String, String> paramsHm = null;
//		String decryptStr = Utils.decryptResult(jsonStr);
//		if (!"".equals(decryptStr)) {
//			paramsHm = GsonUtils.fromJson(decryptStr, HashMap.class);
//		}
		return paramsHm;
	}

	public static int getRandom() {
		Random random = new Random();
		int x = random.nextInt(899999) + 100000;
		return x;
	}

	public static String clearHtml(String content) {
		content = content.replaceAll(".*?<body.*?>(.*?)<\\/body>", "$1"); // 读出body内里所有内容
		content = content.replaceAll("</?[^/?(br)|(p)|(img)][^><]*>", "");// 保留br、p、img标签
		content = content.replaceAll("style=\".*?\"", "");
		return content;
	}

	private static double EARTH_RADIUS = 6378.137;
	
	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}
	/**
	 * @作用 通过经纬度获取距离(单位：km)
	 * 
	 * @param lat1 纬度
	 * @param lng1 经度
	 * @param lat2 纬度
	 * @param lng2 经度
	 * @return
	 */
	public static double getDistance(double lat1, double lng1, double lat2,
			double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000d) / 10000d;
		return s;
	}
	
	public static String removeHtmlTag(String inputString) {
		if (inputString == null)
			return null;
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;
		java.util.regex.Pattern p_special;
		java.util.regex.Matcher m_special;
		try {
			// 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
			// 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
			// 定义HTML标签的正则表达式
			String regEx_html = "<[^>]+>";
			// 定义一些特殊字符的正则表达式 如：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			String regEx_special = "\\&[a-zA-Z]{1,10};";
			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签
			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签
			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签
			p_special = Pattern
					.compile(regEx_special, Pattern.CASE_INSENSITIVE);
			m_special = p_special.matcher(htmlStr);
			htmlStr = m_special.replaceAll(""); // 过滤特殊标签
			textStr = htmlStr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return textStr;// 返回文本字符串
	}
	
	public static String getUUID() {
		String s = UUID.randomUUID().toString();
		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18)
				+ s.substring(19, 23) + s.substring(24);
	}
	
	

	  /**
     * 向指定URL发送GET方法的请求
     *
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return String 所代表远程资源的响应结果
     */
    public static String get(String url,String param){
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            //System.out.println(urlNameString);
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            /*for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }*/
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    
   private final static String ENCODE = "UTF-8"; 
    /**
     * URL 解码
     *
     * @return String
     * @author lifq
     * @date 2015-3-17 下午04:09:51
     */
    public static String getURLDecoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLDecoder.decode(str, ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
    
}
