package controller.admin.excel;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.Record;




public class ExcelUtils{
	
		public static void main(String[] args) {
				List<Record> result = new ArrayList<Record>();
					Record record1 = new Record().set("ss", 1).set("ww", 1).set("dd", 1);
					Record record2 = new Record().set("ss", 2).set("ww", 2).set("dd", 2);
					Record record3 = new Record().set("ss", 3).set("ww", 3).set("dd", 3);
					Record record4 = new Record().set("ss", 4).set("ww", 4).set("dd", 4);
					Record record5 = new Record().set("ss", 5).set("ww", 5).set("dd", 5);
					result.add(record1);
					result.add(record2);
					result.add(record3);
					result.add(record4);
					result.add(record5);
				ExcelUtils.test( result,"mt_user");
		}
		

	 public static void test(List<Record> result , String name ){
		
		 	FileOutputStream fos = null;
		
		 	HSSFWorkbook wb =new HSSFWorkbook();
		 	
			HSSFSheet  sheet = wb.createSheet(name);
			
			HSSFCellStyle style = wb.createCellStyle();
			
			HSSFCellStyle note = wb.createCellStyle();
			
			HSSFFont font = wb.createFont();
			//设置字体居中
			/*style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			
			note.setAlignment(HSSFCellStyle.ALIGN_CENTER);*/
			
			//设置字体大小
			font.setFontHeightInPoints((short)14);
			
			font.setFontName("宋体");  
			
			style.setFont(font);
			
			HSSFRow  row = sheet.createRow(0);
			
			String[] arr = result.get(1).getColumnNames();

			for (int c =0 ; c< arr.length ;c++) {
				HSSFCell cell = row.createCell((short)c);
				try {
					cell.setCellValue(arr[c]);					
					cell.setCellStyle(style);
					
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
			for(int  d = 1; d<result.size()+1; d++){
				HSSFRow  row2 = sheet.createRow(d);
				Map<String, Object> arr2 = result.get(d-1).getColumns();
				String[] brr = result.get(1).getColumnNames();
				for (int c =0 ; c< brr.length ;c++) {
					HSSFCell cell2 = row2.createCell((short)c);
					try {
						cell2.setCellValue(arr2.get(brr[c]).toString());
						cell2.setCellStyle(note);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
			
			
			 try {
				//公网ip
				 String interIP = "http://47.96.31.120:80/xqwl";
				 //本地IP
				 String localIP = "http://192.168.1.15:8080/xqwl";
				 String internet = "/file/"+new Date().getTime()+".xls";
				 
				// MtExcal me = new MtExcal();
				// me.saveFile(name,interIP+internet );
				 String newFileName = PathKit.getWebRootPath() +internet ;
				fos = new FileOutputStream(newFileName);
				wb.write(fos);
				fos.flush();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	 }
	 
}
