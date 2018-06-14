package pay.wxpay.web.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import net.sf.json.JSONObject;

public class getCode {
	/**
	 * 创建临时带参二维码
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param sceneId
	 *            场景ID
	 * @return ticket
	 */
	public static String createTempQRCode(String accessToken, int sceneId) {
		String ticket = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 需要提交的json数据
		String jsonMsg = "{\"expire_seconds\": 1800,\"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": %d}}}";
		// 创建永久带参二维码
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST",
				String.format(jsonMsg, sceneId));
		// System.out.println("jsonObject="+jsonObject);
		if (null != jsonObject) {
			try {
				ticket = jsonObject.getString("ticket");
				// log.info("创建永久带参二维码成功 ticket:{}", ticket);
			} catch (Exception e) {

				// log.error("创建永久带参二维码失败 errcode:{} errmsg:{}", errorCode,
				// errorMsg);
			}
		}
		return ticket;
	}

	/**
	 * 创建永久带参二维码
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param sceneId
	 *            场景ID
	 * @return ticket
	 */

	public static String createPermanentQRCode(String accessToken, int sceneId) {
		String ticket = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 需要提交的json数据
		String jsonMsg = "{\"action_name\": \"QR_LIMIT_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": %d}}}";
		// 创建永久带参二维码
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST",
				String.format(jsonMsg, sceneId));
		// System.out.println("jsonObject="+jsonObject);
		if (null != jsonObject) {
			try {
				ticket = jsonObject.getString("ticket");
				// log.info("创建永久带参二维码成功 ticket:{}", ticket);
			} catch (Exception e) {

			}
		}
		return ticket;
	}

	/**
	 * 根据ticket换取二维码
	 * 
	 * @param ticket
	 *            二维码ticket
	 * @param savePath
	 *            保存路径
	 */
	public static String getQRCode(String ticket, String savePath) {
		String filePath = null;
		// 拼接请求地址
		String requestUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
		requestUrl = requestUrl.replace("TICKET",
				CommonUtil.urlEncodeUTF8(ticket));
		try {
			// System.out.println(requestUrl);
			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");

			if (!savePath.endsWith("/")) {
				savePath += "/";
			}
			// 将ticket作为文件名
			filePath = savePath + ticket + ".jpg";
			// System.out.println(requestUrl);
			// erweima
			// 将微信服务器返回的输入流写入文件
			BufferedInputStream bis = new BufferedInputStream(
					conn.getInputStream());
			FileOutputStream fos = new FileOutputStream(new File(filePath));
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1)
				fos.write(buf, 0, size);

			fos.close();
			bis.close();

			conn.disconnect();
			// log.info("根据ticket换取二维码成功，filePath=" + filePath);
		} catch (Exception e) {
			filePath = null;
			// log.error("根据ticket换取二维码失败：{}", e);
		}
		return requestUrl;
	}
}
