package controller.api;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;


import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.render.JsonRender;
import com.jfinal.upload.UploadFile;
import com.xiaheng.core.jfinal.BaseController;

import utils.linUtils.Common;
import utils.linUtils.GsonUtils;

public class FileController extends BaseController {
	@Override
	protected String route() {
		// TODO Auto-generated method stub
		return "api/upload";
	}
	public void index() {

		HashMap<String, Object> resultHm = new HashMap<String, Object>();

		DateTime nowDt = DateTime.now();

		String path = Common.PATH + "upload/"
				+ nowDt.toString(DateTimeFormat.forPattern("yyyyMM")) + "/";

		//System.out.println("path:" + path);

		UploadFile file = getFile("file", path, 1024 * 1024 * 1024, "UTF-8");

		File srcFile = file.getFile();

		if (srcFile.isFile()) {

			String srcName = srcFile.getName();
			String extStr = "";
			int dot = srcName.lastIndexOf('.');
			if ((dot > -1) && (dot < (srcName.length() - 1))) {
				extStr = srcName.substring(dot);
			}
			String destName = nowDt.toString(DateTimeFormat
					.forPattern("yyyyMMddHHmmssSSS")) + extStr;

			File destFile = new File(path + destName);

			file.getFile().renameTo(destFile);

			String filePath = PropKit.get("path_prefix") + "upload/"
					+ nowDt.toString(DateTimeFormat.forPattern("yyyyMM")) + "/"
					+ destName;

			resultHm.put("statusCode", 200);
			resultHm.put("message", "上传成功");
			resultHm.put("filename", filePath);

		} else {
			resultHm.put("statusCode", "301");
		}
		renderJson(GsonUtils.toJson(resultHm));
	}

	public void uploadimg() {

		HashMap<String, Object> resultHm = new HashMap<String, Object>();

		DateTime nowDt = DateTime.now();

		String path = Common.PATH + "upload/"
				+ nowDt.toString(DateTimeFormat.forPattern("yyyyMM")) + "/";

		//System.out.println("path:" + path);

		UploadFile file = getFile("upfile", path, 200 * 1024 * 1024, "UTF-8");

		File srcFile = file.getFile();

		if (srcFile.isFile()) {

			String srcName = srcFile.getName();
			String extStr = "";
			int dot = srcName.lastIndexOf('.');
			if ((dot > -1) && (dot < (srcName.length() - 1))) {
				extStr = srcName.substring(dot);
			}
			String destName = nowDt.toString(DateTimeFormat
					.forPattern("yyyyMMddHHmmssSSS")) + extStr;

			File destFile = new File(path + destName);

			file.getFile().renameTo(destFile);

			String filePath = PropKit.get("path_prefix") + "upload/"
					+ nowDt.toString(DateTimeFormat.forPattern("yyyyMM")) + "/"
					+ destName;

			resultHm.put("name", srcName);
			resultHm.put("originalName", srcName);
			resultHm.put("size", srcFile.length());
			resultHm.put("state", "SUCCESS");
			resultHm.put("type", extStr);
			resultHm.put("url", filePath);

		} else {
			resultHm.put("state", "FAIL");
		}
		renderJson(GsonUtils.toJson(resultHm));
	}

	public void uploadvideo() {

	}

	/**
	 * 文件上传
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public void upload() throws Exception {
		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

		Map<String, Object> result = new HashMap<String, Object>();
		String dirName = getPara("dir") == null ? "image" : getPara("dir");
		String realpath = getRequest().getRealPath("/upload");
		UploadFile uf = getFile("imgFile", realpath);
		String affix_id = "";
		String affix_name = "";
		if (uf != null) {
			affix_name = uf.getFile().getName();
			File file = uf.getFile();
			// 检查扩展名
			String fileExt = affix_name.substring(
					affix_name.lastIndexOf(".") + 1).toLowerCase();
			if (!Arrays.<String> asList(extMap.get(dirName).split(","))
					.contains(fileExt)) {
				result.put("error", 1);
				result.put("message",
						"上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。");
				file.delete();
			} else {
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				affix_id = df.format(new Date()) + "_"
						+ new Random().nextInt(1000) + "." + fileExt;
				File savefile = new File(new File(realpath), affix_id);
				FileUtils.copyFile(file, savefile);
				if (file.exists()) {
					file.delete();
				}
				result.put("error", 0);
				result.put("url", "/" + PropKit.get("path_prefix") + "/upload/"
						+ affix_id);
			}
		} else {
			result.put("error", 1);
			result.put("message", "请选择文件");
		}

		render(new JsonRender(GsonUtils.toJson(result)).forIE());
	}

	/**
	 * 文件管理
	 */
	@SuppressWarnings("deprecation")
	public void manageImg() {
		String[] fileTypes = new String[] { "gif", "jpg", "jpeg", "png", "bmp" };
		String currentPath = getRequest().getRealPath("/uploadfile") + "/";
		File currentPathFile = new File(currentPath);
		List<Hashtable<String, Object>> fileList = new ArrayList<Hashtable<String, Object>>();
		if (currentPathFile.listFiles() != null) {
			for (File file : currentPathFile.listFiles()) {
				Hashtable<String, Object> hash = new Hashtable<String, Object>();
				String fileName = file.getName();
				if (file.isDirectory()) {
					hash.put("is_dir", true);
					hash.put("has_file", (file.listFiles() != null));
					hash.put("filesize", 0L);
					hash.put("is_photo", false);
					hash.put("filetype", "");
				} else if (file.isFile()) {
					String fileExt = fileName.substring(
							fileName.lastIndexOf(".") + 1).toLowerCase();
					hash.put("is_dir", false);
					hash.put("has_file", false);
					hash.put("filesize", file.length());
					hash.put("is_photo", Arrays.<String> asList(fileTypes)
							.contains(fileExt));
					hash.put("filetype", fileExt);
				}
				hash.put("filename", fileName);
				hash.put("datetime",
						new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file
								.lastModified()));
				fileList.add(hash);
			}
		}
		// 排序形式，name or size or type
		String order = getPara("order") != null ? getPara("order")
				.toLowerCase() : "name";

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("moveup_dir_path", "");
		result.put("current_dir_path", "");
		result.put("current_url", JFinal.me().getContextPath() + "/upload/");
		result.put("total_count", fileList.size());
		result.put("file_list", fileList);
		renderJson(GsonUtils.toJson(result));
	}
}
