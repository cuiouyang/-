package com.bsi.ms.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.LoggerFactory;
import com.bsi.ms.controller.QuestionsController;

/**
 * 
 * @author zhangqing
 * 
 */
public class ExportExcel {
    
    private static Logger logger = LogManager.getLogger(QuestionsController.class);
    /**
     * 私有构造方法
     */
    private ExportExcel() {
    }

    /**
     * 导出无表头的EXCEL文件
     * 
     * @Description: 导出无表头的EXCEL文件
     * @param title
     *            第一个sheet页名称
     * @param dataset
     *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
     *            javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
     * @param out
     *            输出流response.getOutputStream()
     */
    public static <T> void exportExcel(String title, Collection<T> dataset,
	    OutputStream out) {
	// 声明一个工作薄
	HSSFWorkbook workbook = new HSSFWorkbook();
	if (null != dataset) {
	    List<String> properties = new ArrayList<String>();
	    Iterator<T> it = dataset.iterator();
	    T t = (T) it.next();
	    // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
	    Field[] fields = t.getClass().getDeclaredFields();
	    // 参数
	    for (Field field : fields) {
		properties.add(field.getName());
	    }
	    generateMutiSheet(workbook, title, null, properties, dataset,
		    "yyyy-MM-dd");
	    try {
		workbook.write(out);
	    } catch (IOException e) {
		logger.error(e.getMessage(), e);
	    }
	}
    }

    /**
     * 导出带表头的EXCEL文件
     * 
     * @Description: 导出无表头的EXCEL文件
     * @param title
     *            第一个sheet页名称
     * @param headers
     *            表头集合
     * @param dataset
     *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
     *            javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
     * @param out
     *            输出流response.getOutputStream()
     */
    public static <T> void exportExcel(String title, List<String> headers,
	    Collection<T> dataset, OutputStream out) {
	// 声明一个工作薄
	HSSFWorkbook workbook = new HSSFWorkbook();
	if (null != dataset) {
	    List<String> properties = new ArrayList<String>();
	    Iterator<T> it = dataset.iterator();
	    T t = (T) it.next();
	    // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
	    Field[] fields = t.getClass().getDeclaredFields();
	    // 参数
	    for (Field field : fields) {
		properties.add(field.getName());
	    }
	    generateMutiSheet(workbook, null, headers, properties, dataset,
		    "yyyy-MM-dd");
	    try {
		workbook.write(out);
	    } catch (IOException e) {
		logger.error(e.getMessage(), e);
	    }
	}
    }

    /**
     * 导出带表头的EXCEL文件
     * 
     * @Description: 导出无表头的EXCEL文件
     * @param title
     *            第一个sheet页名称
     * @param headers
     *            表头集合
     * @param dataset
     *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
     *            javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
     * @param out
     *            输出流response.getOutputStream()
     * @param pattern
     *            时间格式
     */
    public static <T> void exportExcel(List<String> headers,
	    Collection<T> dataset, OutputStream out, String pattern) {
	// 声明一个工作薄
	HSSFWorkbook workbook = new HSSFWorkbook();
	if (null != dataset) {
	    List<String> properties = new ArrayList<String>();
	    Iterator<T> it = dataset.iterator();
	    T t = (T) it.next();
	    // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
	    Field[] fields = t.getClass().getDeclaredFields();
	    // 参数
	    for (Field field : fields) {
		properties.add(field.getName());
	    }
	    generateMutiSheet(workbook, null, headers, properties, dataset,
		    pattern);
	    try {
		workbook.write(out);
	    } catch (IOException e) {
		logger.error(e.getMessage(), e);
	    }
	}
    }

    /**
     * 导出一个sheet页的EXCEL文件
     * 
     * @Description: 导出一个sheet页的EXCEL文件
     * @param title
     *            sheet页的页名
     * @param headers
     *            excel第一行表头显示的内容
     * @param properties
     *            excel正文中每一列对应的数据集对象中的属性。
     * @param dataset
     *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
     *            javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
     * @param out
     *            输出流response.getOutputStream()
     * @param pattern
     *            导出的内容中有时间类型的，在EXCEL中填入的时间的格式
     * @return OutputStream 返回输出流，下载文件
     * @throws
     */
    public static <T> OutputStream exportOneSheetExcel(String title,
	    List<String> headers, List<String> properties,
	    Collection<T> dataset, OutputStream out, String pattern) {
	// 声明一个工作薄
	HSSFWorkbook workbook = new HSSFWorkbook();
	// 生成一个表格
	generateMutiSheet(workbook, title, headers, properties, dataset,
		pattern);
	try {
	    workbook.write(out);
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	}
	return out;
    }

    /**
     * 导出包含多个sheet页的一个EXCEL文件
     * 
     * @Description: 导出包含多个sheet页的一个EXCEL文件
     * @param title
     *            sheet页的页名集合
     * @param headers
     *            每一个SHEET页excel第一行表头显示的内容集合
     * @param properties
     *            每一个SHEET页excel正文中每一列对应的数据集对象中的属性集合。
     * @param dataset
     *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
     *            javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
     * @param out
     *            输出流response.getOutputStream()
     * @param pattern
     *            导出的内容中有时间类型的，在EXCEL中填入的时间的格式
     * @return OutputStream 返回输出流，下载文件
     * @throws
     */
    public static <T> OutputStream exportMutiSheetExcel(List<String> title,
	    List<List<String>> headers, List<List<String>> properties,
	    OutputStream out, String pattern, Collection<T>... dataset) {
	if (title != null && !title.isEmpty()) {
	    // 声明一个工作薄
	    HSSFWorkbook workbook = new HSSFWorkbook();
	    // 生成多个Sheet页表格
	    for (int i = 0; i < title.size(); i++) {
		generateMutiSheet(workbook, title.get(i), headers.get(i),
			properties.get(i), dataset[i], pattern);
	    }
	    try {
		workbook.write(out);
	    } catch (IOException e) {
		logger.error(e.getMessage(), e);
	    }
	}
	return out;
    }

    /**
     * 一个数据导出包含多个sheet页的一个EXCEL文件
     * 
     * @Description: 导出包含多个sheet页的一个EXCEL文件
     * @param title
     *            sheet页的页名集合
     * @param headers
     *            每一个SHEET页excel第一行表头显示的内容集合
     * @param properties
     *            每一个SHEET页excel正文中每一列对应的数据集对象中的属性集合。
     * @param dataset
     *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
     *            javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
     * @param out
     *            输出流response.getOutputStream()
     * @param pattern
     *            导出的内容中有时间类型的，在EXCEL中填入的时间的格式
     * @return OutputStream 返回输出流，下载文件
     * @throws
     */
    public static <T> OutputStream exportOneDateSetExcel(String title,
	    List<String> headers, List<String> properties, OutputStream out,
	    String pattern, Collection<T> dataset) {
	// 声明一个工作薄
	HSSFWorkbook workbook = new HSSFWorkbook();
	generateMutiSheet(workbook, title, headers, properties, dataset,
		pattern);
	try {
	    workbook.write(out);
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	}
	return out;
    }

    public static <T> void exportSheets(List<String> title,
	    List<List<String>> headers, List<List<String>> properties,
	    OutputStream out, String pattern, Collection<?>... dataset) {
	if (title != null && !title.isEmpty()) {
	    // 声明一个工作薄
	    HSSFWorkbook workbook = new HSSFWorkbook();
	    // 生成多个Sheet页表格
	    for (int i = 0; i < title.size(); i++) {
		generateMutiSheet(workbook, title.get(i), headers.get(i),
			properties.get(i), dataset[i], pattern);
	    }
	    try {
	     
		workbook.write(out);
		 
	    } catch (IOException e) {
		logger.error(e.getMessage(), e);
	    }
	}
    }

    @SuppressWarnings("deprecation")
    private static <T> void generateMutiSheet(HSSFWorkbook workbook,
	    String title, List<String> headers, List<String> properties,
	    Collection<T> dataset, String pattern) {
	HSSFSheet sheet = null;
	if (StringUtils.isEmpty(title)) {
	    sheet = workbook.createSheet();
	} else {
	    sheet = workbook.createSheet(title);
	}
	// 生成一个表格
	// 设置表格默认列宽度为15个字节
	sheet.setDefaultColumnWidth((short) 15);
	// 声明一个画图的顶级管理器
	HSSFPatriarch patriarch = sheet.createDrawingPatriarch();

	// 产生行
	HSSFRow row = null;
	if (null != headers && !headers.isEmpty()) {
	    row = sheet.createRow(0);
	    row.setHeightInPoints(20);
	    for (short i = 0; i < headers.size(); i++) {
		HSSFCell cell = row.createCell(i);
		HSSFRichTextString text = new HSSFRichTextString(headers.get(i));
		cell.setCellValue(text);
	    }
	}
	// 遍历集合数据，产生数据行
	Iterator<T> it = dataset.iterator();
	int index = 0;
	Object value = null;

	while (it.hasNext()) {
	    // 如果当个SHEET页的数据记录条数超过2000行，则新建一个sheet页
	    if (index == 3000) {
		index = 0;
		sheet = workbook.createSheet();
	    }
	    if (null != headers && !headers.isEmpty()) {
		index++;
		row = sheet.createRow(index);
	    } else {
		row = sheet.createRow(index);
		index++;
	    }
	    row.setHeightInPoints(15);
	    T t = (T) it.next();
	    // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
	    //Field[] fields = t.getClass().getDeclaredFields();
	    for (int i = 0; i < properties.size(); i++) {
		HSSFCell cell = row.createCell(i);
		value = getCollectionValue(properties.get(i), t);
		try {
		    // 判断值的类型后进行强制类型转换
		    String textValue = null;
		    if (value != null) {
			if (value instanceof Date) {
			    Date date = (Date) value;
			    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			    textValue = sdf.format(date);
			} else if (value instanceof byte[]) {
			    // 有图片时，设置行高为60px;
			    row.setHeightInPoints(60);
			    // 设置图片所在列宽度为80px,注意这里单位的一个换算
			    sheet.setColumnWidth(i, (short) (35.7 * 80));
			    // sheet.autoSizeColumn(i);
			    byte[] bsValue = (byte[]) value;
			    HSSFClientAnchor anchor = new HSSFClientAnchor(0,
				    0, 1023, 255, (short) 6, index, (short) 6,
				    index);
			    anchor.setAnchorType(2);
			    patriarch.createPicture(anchor, workbook
				    .addPicture(bsValue,
					    HSSFWorkbook.PICTURE_TYPE_JPEG));
			} else {
			    // 其它数据类型都当作字符串简单处理
			    textValue = value.toString();
			}
		    }
		    // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
		    if (textValue != null) {
			Pattern p = Pattern.compile("^//d+(//.//d+)?$");
			Matcher matcher = p.matcher(textValue);
			if (matcher.matches()) {
			    // 是数字当作double处理
			    cell.setCellValue(Double.parseDouble(textValue));
			} else {
			    HSSFRichTextString richString = new HSSFRichTextString(
				    textValue);
			    cell.setCellValue(richString);
			}
		    }
		} catch (SecurityException e) {
		    logger.error(e.getMessage(), e);
		} catch (IllegalArgumentException e) {
		    logger.error(e.getMessage(), e);
		} finally {
		    // 清理资源
		}
	    }
	}
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static <T> Object getCollectionValue(String pro, T t) {
	if (pro.indexOf('.') != -1) {
	    String[] childField = pro.split("\\.");
	    String getMethodName = "get"
		    + childField[0].substring(0, 1).toUpperCase()
		    + childField[0].substring(1);
	    String getMethodName1 = "get"
		    + childField[1].substring(0, 1).toUpperCase()
		    + childField[1].substring(1);
	    Class tCls = t.getClass();
	    try {
		Method getMethod = tCls
			.getMethod(getMethodName, new Class[] {});
		Object obj = getMethod.invoke(t, new Object[] {});

		Class child = obj.getClass();
		Method getMethod1 = child.getMethod(getMethodName1,
			new Class[] {});
		return getMethod1.invoke(obj, new Object[] {});
	    } catch (SecurityException e) {
		logger.error(e.getMessage(), e);
	    } catch (IllegalArgumentException e) {
		logger.error(e.getMessage(), e);
	    } catch (NoSuchMethodException e) {
		logger.error(e.getMessage(), e);
	    } catch (IllegalAccessException e) {
		logger.error(e.getMessage(), e);
	    } catch (InvocationTargetException e) {
		logger.error(e.getMessage(), e);
	    }
	} else {
	    String getMethodName = "get" + pro.substring(0, 1).toUpperCase()
		    + pro.substring(1);

	    Class tCls = t.getClass();
	    try {
		Method getMethod = tCls
			.getMethod(getMethodName, new Class[] {});
		return getMethod.invoke(t, new Object[] {});
	    } catch (SecurityException e) {
		logger.error(e.getMessage(), e);
	    } catch (IllegalArgumentException e) {
		logger.error(e.getMessage(), e);
	    } catch (NoSuchMethodException e) {
		logger.error(e.getMessage(), e);
	    } catch (IllegalAccessException e) {
		logger.error(e.getMessage(), e);
	    } catch (InvocationTargetException e) {
		logger.error(e.getMessage(), e);
	    }
	}
	return null;
    }

    // private static HSSFCellStyle setSheetStyle(HSSFWorkbook workbook){
    // // 生成一个样式
    // HSSFCellStyle style = workbook.createCellStyle();
    // // 设置这些样式
    // style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
    // style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    // style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    // style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    // style.setBorderRight(HSSFCellStyle.BORDER_THIN);
    // style.setBorderTop(HSSFCellStyle.BORDER_THIN);
    // style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    // // 生成一个字体
    // HSSFFont font = workbook.createFont();
    // font.setFontHeightInPoints((short) 12);
    // font.setFontName(HSSFFont.FONT_ARIAL);
    // font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
    // // 把字体应用到当前的样式
    // style.setFont(font);
    // return style;
    // }
    /**
     * 导出包含一个数据库数据的EXCEL文件 及附件文件压缩文件，附件在压缩文件中的attachment文件夹下 由于JDK
     * 1.6ZipEntry不支持中文件，因此压缩文件中的文件名由字母和数字组成
     * 
     * @Description: 导出包含多个sheet页的一个EXCEL文件
     * @param title
     *            sheet页的页名集合
     * @param headers
     *            每一个SHEET页excel第一行表头显示的内容集合
     * @param properties
     *            每一个SHEET页excel正文中每一列对应的数据集对象中的属性集合。
     * @param dataset
     *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
     *            javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
     * @param out
     *            输出流response.getOutputStream()
     * @param files
     *            要导出的附件文件集合
     * @param pattern
     *            导出的内容中有时间类型的，在EXCEL中填入的时间的格式
     * @return OutputStream 返回输出流，下载文件
     * @throws IOException
     *             文件不存在异常
     */
    public static <T> void exportMutiSheetExcelZip(List<String> title,
	    List<List<String>> headers, List<List<String>> properties,
	    OutputStream out, String pattern, List<File> files,
	    Collection<T>... dataset) throws IOException {
	ZipOutputStream zipSteam = new ZipOutputStream(out);
	if (dataset != null) {
	    ZipEntry entry = new ZipEntry(UUID.randomUUID().toString() + ".xls");
	    zipSteam.putNextEntry(entry);
	    exportMutiSheetExcel(title, headers, properties, zipSteam,
		    "yyyy-MM-dd", dataset);
	    zipSteam.flush();
	}
	if (null != files) {
	    zipSteam.putNextEntry(new ZipEntry("attachment/"));
	    for (int i = 0; i < files.size(); i++) {
		File file = files.get(i);
		ZipEntry entry = new ZipEntry("attachment/" + file.getName());
		zipSteam.putNextEntry(entry);
		FileInputStream in = new FileInputStream(file);
		try {
		    byte[] bb = new byte[10240];
		    int aa = 0;
		    while ((aa = in.read(bb)) != -1) {
			zipSteam.write(bb, 0, aa);
		    }
		} catch (Exception e) {
		    logger.error(e.getMessage(), e);
		} finally {
		    in.close();
		    zipSteam.flush();
		}
	    }
	}
	zipSteam.close();
    }

    
    public static <T> void exportSheet(List<String> name,List<String> title,
    	    OutputStream out, String pattern, List<List<?>>dataset) {
    	if (title != null && !title.isEmpty()) {
    	    // 声明一个工作薄
    	    HSSFWorkbook workbook = new HSSFWorkbook();
    	    HSSFSheet sheet = workbook.createSheet(); 
    	    int index = 0;
    	    // 生成多个Sheet页表格
    	    for (int i = 0; i < dataset.size(); i++) {
    	    	// if (null != dataset) {
    	    		    List<String> properties = new ArrayList<String>();
    	    		//    Iterator<T> it = dataset[i].iterator();
    	    		  //  T t = (T) it.next();
    	    		    // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
    	    		    Field[] fields = dataset.get(i).iterator().next().getClass().getDeclaredFields();
    	    		    // 参数
    	    		    for (Field field : fields) {
    	    		    if(!field.getName().contains("_")&&!field.getName().contentEquals("serialVersionUID")&&!field.getName().contains("xmProject")&&!field.getName().contains("xmInvestmentInfos")){
    	    		       	properties.add(""+field.getName()+"");
    	    		       	System.out.println(field.getName());
    	    		      }
    	    		    }System.out.println();
//    	    		    generateMutiSheet(workbook, title.get(i), null,
//    	    	     			properties, dataset[i], pattern);
//    	}
    	    generateOneSheet(name.get(i).toString(),index,workbook,sheet,null, null,
	     			properties, dataset.get(i), pattern);
    	    index = sheet.getLastRowNum()+1;
    	    }	
	    try {
	    	workbook.write(out);
	    	 
	    } catch (IOException e) {
		logger.error(e.getMessage(), e);
	    }
    	}
    	 
        }
    
    public static <T> void exportExcelZip(List<String> name,List<String> title,
    	    OutputStream out, String pattern, List<File> files,
    	    List<List<?>>dataset) throws IOException {
    	ZipOutputStream zipSteam = new ZipOutputStream(out);
    	if (dataset != null) {
    	    ZipEntry entry = new ZipEntry(UUID.randomUUID().toString()  + ".xls");
    	    zipSteam.putNextEntry(entry);
    	    exportSheet(name,title, zipSteam,
    		    "yyyy-MM-dd", dataset);
    	    zipSteam.flush();
    	}
    	if (null != files) {
    	    zipSteam.putNextEntry(new ZipEntry("attachment/"));
    	    for (int i = 0; i < files.size(); i++) {
    		File file = files.get(i);
    		if(file.exists()){
    		ZipEntry entry = new ZipEntry("attachment/" + file.getName());
    		zipSteam.putNextEntry(entry);
 
    		FileInputStream in = new FileInputStream(file);
    	 
    		try {
    		    byte[] bb = new byte[10240];
    		    int aa = 0;
    		    while ((aa = in.read(bb)) != -1) {
    			zipSteam.write(bb, 0, aa);
    		    }
    		} catch (Exception e) {
    		    logger.error(e.getMessage(), e);
    		} finally {
    		    in.close();
    		    zipSteam.flush();
    		}
    		}
    	    }
    	}
    	zipSteam.close();
        }
    
 
    /**
     * 导出包含一个数据库数据的EXCEL文件 及附件文件压缩文件，附件在压缩文件中的attachment文件夹下 由于JDK
     * 1.6ZipEntry不支持中文件，因此压缩文件中的文件名由字母和数字组成
     * 
     * @Description: 导出包含多个sheet页的一个EXCEL文件
     * @param title
     *            sheet页的页名集合
     * @param headers
     *            每一个SHEET页excel第一行表头显示的内容集合
     * @param properties
     *            每一个SHEET页excel正文中每一列对应的数据集对象中的属性集合。
     * @param dataset
     *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
     *            javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
     * @param out
     *            输出流response.getOutputStream()
     * @param files
     *            要导出的附件文件集合
     * @param pattern
     *            导出的内容中有时间类型的，在EXCEL中填入的时间的格式
     * @return OutputStream 返回输出流，下载文件
     * @throws IOException
     *             文件不存在异常
     */
    public static <T> void exportExcelZip(String title, List<String> headers,
	    List<String> properties, OutputStream out, String pattern,
	    List<File> files, Collection<T> dataset) throws IOException {
	ZipOutputStream zipSteam = new ZipOutputStream(out);
	if (dataset != null) {
	    ZipEntry entry = new ZipEntry(UUID.randomUUID().toString()  + ".xls");
	    zipSteam.putNextEntry(entry);
	    exportOneDateSetExcel(title, headers, properties, zipSteam,
		    "yyyy-MM-dd", dataset);
	    zipSteam.flush();
	}
	if (null != files && !files.isEmpty()) {
	    zipSteam.putNextEntry(new ZipEntry("attachment/"));
	    for (int i = 0; i < files.size(); i++) {
		File file = files.get(i);
		ZipEntry entry = new ZipEntry("attachment/" + file.getName());
		zipSteam.putNextEntry(entry);

		FileInputStream in = new FileInputStream(file);
		try {
		    byte[] bb = new byte[10240];
		    int aa = 0;
		    while ((aa = in.read(bb)) != -1) {
			zipSteam.write(bb, 0, aa);
		    }
		} catch (Exception e) {
		    logger.error(e.getMessage(), e);
		} finally {
		    in.close();
		    zipSteam.flush();
		}
	    }
	}
	zipSteam.close();
    }

    /**
     * 解析指定目录文件的EXCEL文件所有的Sheet页
     * 
     * @Description: 解析指定目录文件的EXCEL文件所有的Sheet页
     * @param filePath
     *            文件路径
     * @return List<Sheet> 返回Excel所有的Sheet页
     * @throws IOException
     *             文件不存在异常
     */
    public static List<Sheet> initWorkBook(String filePath) throws IOException {
	File file = new File(filePath);
	InputStream is = new FileInputStream(file);
	Workbook workbook = null;
	if (filePath.endsWith(".xls")) {
	    workbook = new HSSFWorkbook(is);
	}
	return parseWorkbook(workbook);
    }

    /**
     * 根据压缩文件解析压缩文件中一个ZipEntry name 为EXCEL文件的所有的Sheet页
     * 
     * @Description: 根据压缩文件解析压缩文件中一个ZipEntry name 为EXCEL文件的所有的Sheet页
     * @param zipFile
     *            压缩文件对象
     * @param entry
     *            压缩文件对象中的文件ZipEntry对象
     * @return List<Sheet> 返回Excel所有的Sheet页
     * @throws IOException
     *             文件不存在异常
     */
    public static List<Sheet> initWorkBookByZipFile(ZipFile zipFile,
	    ZipEntry entry) throws IOException {
	Workbook workbook = null;
	if (entry.getName().endsWith(".xls")) {
	    workbook = new HSSFWorkbook(zipFile.getInputStream(entry));
	}
	return parseWorkbook(workbook);
    }

    /**
     * 获取Excel workbook工作区间所有的sheet页集合
     * 
     * @Description: 获取Excel workbook工作区间所有的sheet页集合
     * @param workbook
     *            Excel workbook工作区间
     * @return List<Sheet> 返回Excel workbook工作区间所有的sheet页集合
     */
    public static List<Sheet> parseWorkbook(Workbook workbook) {
	List<Sheet> sheets = new ArrayList<Sheet>();
	if (null != workbook) {
	    int sheetNum = workbook.getNumberOfSheets();
	    for (int i = 0; i < sheetNum; i++) {
		sheets.add(workbook.getSheetAt(i));
	    }
	}
	return sheets;
    }

    /**
     * 根据压缩文件解析压缩文件中一个ZipEntry name 为EXCEL文件的所有的Sheet页中的内容
     * 
     * @Description: 根据压缩文件解析压缩文件中一个ZipEntry name 为EXCEL文件的所有的Sheet页中的内容
     * @param zipFile
     *            压缩文件对象
     * @param entry
     *            压缩文件对象中的文件ZipEntry对象
     * @return List<T> 返回解析文件中内容对应的BEAN的集合
     * @throws IOException
     *             文件不存在异常
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> initWorkBookByZipFile(ZipFile zipFile,
	    ZipEntry entry, Class<?> tclass, List<String> properties,
	    String pattern){
	Workbook workbook = null;
	if (entry.getName().endsWith(".xls")) {
	    try {
			workbook = new HSSFWorkbook(zipFile.getInputStream(entry));
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
	}
	List<Sheet> lists = parseWorkbook(workbook);
	List<T> tlists = new ArrayList<T>();
	for (Sheet sheet : lists) {
	    try {
			tlists.addAll((Collection<? extends T>) parseSheet(sheet,
			    properties, tclass, pattern));
		} catch (InstantiationException e) {
			logger.error(e.getMessage(),e);
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage(),e);
		}
	}
	return tlists;
    }

    /**
     * 解析EXCEL指定一个SHEET页的数据赋值到一个POJO对象集合
     * 
     * @Description: 解析EXCEL指定一个SHEET页的数据赋值到一个POJO对象集合
     * @param sheet
     *            excel的sheet页
     * @param properties
     *            EXCEL行中每一列对应的POJO中的属性名称
     * @param t
     *            sheet页数据对应的POJO对象
     * @param pattern
     *            若有时间类型，EXCEL中时间类型的格式
     * @return List<T> 返回表格数据对应的bean集合
     * @throws InstantiationException
     *             对象初始化异常
     * @throws IllegalAccessException
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> parseSheet(Sheet sheet, List<String> properties,
	    Class<?> tclass, String pattern) throws InstantiationException,
	    IllegalAccessException {
	List<T> lists = new ArrayList<T>();
	int rowNum = sheet.getLastRowNum();
	if (rowNum > 0) {
	    for (int i = 0; i <= rowNum; i++) {
		T t = (T) tclass.newInstance();
		lists.add(parseRow(sheet.getRow(i), properties, t, pattern));
	    }
	}
	return lists;
    }
    
    @SuppressWarnings("unchecked")
    public static <T> List<T> parseSheets(List<?>dataset,Sheet sheet,
	    Class<?> tclass, String pattern) throws InstantiationException,
	    IllegalAccessException {
	List<T> lists = new ArrayList<T>();
	 List<String> properties = new ArrayList<String>();
	  
	  
	    // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
	    Field[] fields = dataset.iterator().next().getClass().getDeclaredFields();
	    // 参数
	    for (Field field : fields) {
	    if(!field.getName().equals("serialVersionUID")&&!field.getName().equals("xmTemporaryId")){
		properties.add(field.getName());
		System.out.println(field.getName());
		}
	    }
	    System.out.println();
	int rowNum = sheet.getLastRowNum();
	if (rowNum > 0) {
	    for (int i = 0; i <= rowNum; i++) {
		T tt = (T) tclass.newInstance();
		lists.add(parseRow(sheet.getRow(i), properties, tt, pattern));
	    }
	}
	return lists;
    }
    /**
     * 将EXCEL每一行数据插入到实体POJO对象
     * 
     * @Description: 将EXCEL每一行数据插入到实体POJO对象
     * @param row
     *            EXCEL行对象
     * @param properties
     *            EXCEL行中每一列对应的POJO中的属性名称
     * @param t
     *            要插入的实体POJO对象
     * @param pattern
     *            若有时间类型，EXCEL中时间类型的格式
     * @return T 返回赋上ROW中值的POJO对象
     * @throws
     */
    public static <T> T parseRow(Row row, List<String> properties, T t,
	    String pattern) {
	Cell cell = null;
	for (int i = 0; i < properties.size(); i++) {
	    cell = row.getCell(i);
	    if (null != cell
		    && StringUtils.isNotEmpty(cell.getStringCellValue())) {
		cell.setCellType(Cell.CELL_TYPE_STRING);
		String setMethodName = "set"
			+ properties.get(i).substring(0, 1).toUpperCase()
			+ properties.get(i).substring(1);

		Class<? extends Object> tCls = t.getClass();
		Method[] methods = tCls.getMethods();
		try {
		    for (Method method : methods) {
			if (setMethodName.equals(method.getName())) {
			    Class<?>[] clazz = method.getParameterTypes();
			    String type = clazz[0].getName();
			    if ("java.lang.String".equals(type)) {
				method.invoke(t, cell.getStringCellValue());
			    } else if ("java.util.Date".equals(type)) {
				SimpleDateFormat sdf = new SimpleDateFormat(
					pattern);
				if (StringUtils.isNotEmpty(cell
					.getStringCellValue())) {
				    method.invoke(t, sdf.parse(cell
					    .getStringCellValue()));
				}
			    } else if ("java.lang.Integer".equals(type)
				    || "int".equals(type)) {
				if (StringUtils.isNotEmpty(cell
					.getStringCellValue())) {
				    method.invoke(t,  Integer.valueOf(cell .getStringCellValue()));
				}
			    } else if ("java.lang.Long".equals(type)
				    || "long".equals(type)) {
				if (StringUtils.isNotEmpty(cell
					.getStringCellValue())) {
				    method.invoke(t,
					    new Long(cell.getStringCellValue()));
				}
			    } else if ("java.math.BigDecimal".equals(type)) {
				if (StringUtils.isNotEmpty(cell
					.getStringCellValue())) {
				    method.invoke(t, BigDecimal
					    .valueOf(new Double(cell
						    .getStringCellValue())));
				}
			    } else if ("java.lang.Boolean".equals(type)
				    || "boolean".equals(type)) {
				if (StringUtils.isNotEmpty(cell.getStringCellValue())) {
				    method.invoke(t, Boolean.parseBoolean(cell.getStringCellValue()));
				}
			    } else if ("java.lang.Short".equals(type) || "short".equals(type)&&StringUtils.isNotEmpty(cell.getStringCellValue())) {
				    method.invoke( t,Short.valueOf(cell.getStringCellValue()));
			    }
			    break;
			}
		    }
		} catch (ParseException e) {
		    logger.error(e.getMessage(), e);
		} catch (SecurityException e) {
		    logger.error(e.getMessage(), e);
		} catch (IllegalArgumentException e) {
		    logger.error(e.getMessage(), e);
		} catch (IllegalAccessException e) {
		    logger.error(e.getMessage(), e);
		} catch (InvocationTargetException e) {
		    logger.error(e.getMessage(), e);
		}
	    }
	}
	return t;
    }
    
    @SuppressWarnings("deprecation")
    private static <T> void generateOneSheet(String name,int index,HSSFWorkbook workbook,HSSFSheet sheet,
	    String title, List<String> headers,  List<String> properties,
	    Collection<T>dataset, String pattern) {
//	HSSFSheet sheet = null;
//	if (StringUtils.isEmpty(title)) {
//	    sheet = workbook.createSheet();
//	} else {
//	    sheet = workbook.createSheet(title);
//	}
	// 生成一个表格
	// 设置表格默认列宽度为15个字节
	sheet.setDefaultColumnWidth((short) 15);
	// 声明一个画图的顶级管理器
	HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
	 
	
	// 遍历集合数据，产生数据行
	Iterator<T> it = dataset.iterator();
	//int index = 0;
	// index = sheet.getLastRowNum()+1;
	// 产生行
		HSSFRow row = null;
		if (null != headers && !headers.isEmpty()) {
		    row = sheet.createRow(0);
		    row.setHeightInPoints(20);
		    for (short i = 0; i < headers.size(); i++) {
			HSSFCell cell = row.createCell(i);
			HSSFRichTextString text = new HSSFRichTextString(headers.get(i));
			cell.setCellValue(text);
		    }
		}
	Object value = null;

	while (it.hasNext()) {
	    // 如果当个SHEET页的数据记录条数超过2000行，则新建一个sheet页
//	    if (index == 3000) {
//		index = 0;
//		sheet = workbook.createSheet();
//	    }
	    if (null != headers && !headers.isEmpty()) {
		index++;
		row = sheet.createRow(index);
	    } else {
		row = sheet.createRow(index);
		index++;
	    }
	    row.setHeightInPoints(15);
	    T t = (T) it.next();
	    // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
	    // Field[] fields = t.getClass().getDeclaredFields();
	//    for (int i = 0; i < properties.size(); i++) {
	    for (int j = properties.size(),i=0; j>0; --j,++i) {
		HSSFCell cell = row.createCell(i);
		if(j==properties.size()){
			value = name;
			 
		}else{
		value = getCollectionValue(properties.get(j), t);
		}
		try {
		    // 判断值的类型后进行强制类型转换
		    String textValue = null;
		    if (value != null) {
			if (value instanceof Date) {
			    Date date = (Date) value;
			    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			    textValue = sdf.format(date);
			} else if (value instanceof byte[]) {
			    // 有图片时，设置行高为60px;
			    row.setHeightInPoints(60);
			    // 设置图片所在列宽度为80px,注意这里单位的一个换算
			    sheet.setColumnWidth(i, (short) (35.7 * 80));
			    // sheet.autoSizeColumn(i);
			    byte[] bsValue = (byte[]) value;
			    HSSFClientAnchor anchor = new HSSFClientAnchor(0,
				    0, 1023, 255, (short) 6, index, (short) 6,
				    index);
			    anchor.setAnchorType(2);
			    patriarch.createPicture(anchor, workbook
				    .addPicture(bsValue,
					    HSSFWorkbook.PICTURE_TYPE_JPEG));
			} else {
			    // 其它数据类型都当作字符串简单处理
			    textValue = value.toString();
			}
		    }
		    // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
		    if (textValue != null) {
			Pattern p = Pattern.compile("^//d+(//.//d+)?$");
			Matcher matcher = p.matcher(textValue);
			if (matcher.matches()) {
			    // 是数字当作double处理
			    cell.setCellValue(Double.parseDouble(textValue));
			} else {
			    HSSFRichTextString richString = new HSSFRichTextString(
				    textValue);
			    cell.setCellValue(richString);
			}
		    }
		} catch (SecurityException e) {
		    logger.error(e.getMessage(), e);
		} catch (IllegalArgumentException e) {
		    logger.error(e.getMessage(), e);
		} finally {
		    // 清理资源
		}
	    }
	}
    }
}

