package pay.wxpay.app.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;



import pay.wxpay.web.services.MD5Util;
public class Sign {

	// 生成签名
		@SuppressWarnings("rawtypes")
		public static String createSign(String characterEncoding,
				SortedMap<Object, Object> parameters) {
			StringBuffer sb = new StringBuffer();
			Set es = parameters.entrySet();
			Iterator it = es.iterator();
			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				String k = (String) entry.getKey();
				Object v = entry.getValue();
				if ((v == null) || ("".equals(v)) || ("sign".equals(k))
						|| ("key".equals(k)))
					continue;
				sb.append(k + "=" + v + "&");
			}
			sb.append("key=" + ConstantUtil.PARTNER_KEY);
			//System.out.println("sign："+sb.toString());
			String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
					
			return sign;
		}
		
		
		
		@SuppressWarnings("rawtypes")
		public static String addSign(String characterEncoding,
				SortedMap<Object, Object> parameters) {
			StringBuffer sb = new StringBuffer();
			Set es = parameters.entrySet();
			Iterator it = es.iterator();
			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				String k = (String) entry.getKey();
				Object v = entry.getValue();
				if ((v == null) || ("".equals(v)) || ("sign".equals(k))
						|| ("key".equals(k)))
					continue;
				sb.append(k + "=" + v + "&");
			}
			sb.append("key=" + ConstantUtil.PARTNER_KEY);
			System.out.println("sign："+sb.toString());
			String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding)
					.toUpperCase();
			return sign;
		}

}
