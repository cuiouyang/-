package com.bsi.ms.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.bsi.ms.model.Course;
import com.bsi.ms.model.ProblemWithBLOBs;
import com.bsi.ms.model.User;
import com.bsi.ms.service.ProblemService;
import com.bsi.ms.service.UserService;
import com.bsi.ms.utils.ExportExcel;
import com.bsi.ms.utils.Pager;

/**
 * @ClassName: LoginController
 * @Description: 试题管理controller文件
 * @author 崔欧阳
 * @date 2017.4.21
 * 
 */
@Controller
public class QuestionsController {
	protected static Logger logger = LogManager.getLogger(QuestionsController.class);
	@Autowired
	private ProblemService problemService;
	//跳转到试题管理界面
		@RequestMapping(value = "/questions/manage", method = { RequestMethod.GET,RequestMethod.POST })
		public String List(HttpServletRequest request,HttpServletResponse response,Model model) {
			//查询出所有试题
			Map<String, Object> map = new HashMap<String, Object>();
			//获取所有的考试科目名字
			List<Course> course=problemService.selectByAllCourse();
			map.put("problemTitle",null);
			map.put("problemType" ,null);
			map.put("courseName", null);
			List<ProblemWithBLOBs> list=problemService.selectByAll(map);
			//放入session域中。为导出做准备
			request.getSession().setAttribute("currentQuestions", list);
			model.addAttribute("course", course);
			model.addAttribute("list", list);
			logger.info("所有试题result:{}", list);
			return "/problem/list";
		}
		
		//跳转到新增试题界面
		@RequestMapping(value = "/questions/add", method = { RequestMethod.GET, RequestMethod.POST })
		public String addQuestions(HttpServletRequest request, HttpServletResponse response,
				@RequestParam MultiValueMap<String, String> parameters ,ModelMap model) {
			    List<Course> course=problemService.selectByAllCourse();
			    model.addAttribute("course", course);
			    return  "problem/add";
		}
		
			// 保存新增的试题信息
			@RequestMapping(value = "/questions/save", method = { RequestMethod.GET, RequestMethod.POST })
			public String saveQuestions(HttpServletRequest request, HttpServletResponse response,
					@RequestParam MultiValueMap<String, String> parameters) {
				logger.info("新增试题parameter:{}",parameters );
				Map<String, String> paramMap = parameters.toSingleValueMap();
				    ProblemWithBLOBs pw = new ProblemWithBLOBs();
					String problemType = paramMap.get("problemType");
					String courseName = paramMap.get("courseName");
					String problemTitle=paramMap.get("problemTitle");
					String keyA=paramMap.get("keyA");
					String keyB=paramMap.get("keyB");
					String keyC=paramMap.get("keyC");
					String keyD=paramMap.get("keyD");
					String solution=paramMap.get("solution");
					Integer difficulty =Integer.parseInt(paramMap.get("difficulty"));
					pw.setCourseName(courseName);
					pw.setKeyA(keyA);
					pw.setKeyB(keyB);
					pw.setKeyC(keyC);
					pw.setKeyD(keyD);
					pw.setProblemTitle(problemTitle);
					pw.setSolution(solution);
					pw.setDifficulty(difficulty);
					pw.setProblemType(Integer.parseInt(problemType));
					int i =problemService.insertSelective(pw);
					//成功就到所有试题界面，失败就回到新增界面
					if(i>0){
						return "redirect:/questions/manage";
						
					}else{
					return  "redirect:/questions/add";
					}
				
				
			}
			//查询试题信息
			@RequestMapping(value = "/questions/query", method = { RequestMethod.GET, RequestMethod.POST })
			@ResponseBody
			public Pager list(HttpServletRequest request, @RequestParam MultiValueMap<String, String> parameters,
					ModelMap model) {
				Map<String, Object> map = new HashMap<String, Object>();
				Map<String, String> paramMap = parameters.toSingleValueMap();
				//查询参数
				String problemTitle = paramMap.get("problemTitle");
				String problemType = paramMap.get("problemType");
				String courseName = paramMap.get("courseName");
				map.put("problemTitle", problemTitle);
				map.put("problemType", problemType);
				map.put("courseName", courseName);
				//获取条件试题
				List<ProblemWithBLOBs> list=problemService.selectByAll(map);
				model.addAttribute("list", list);
				//放入session域中。为导出做准备
				request.getSession().setAttribute("currentQuestions", list);
				//分页
				int currentPage = paramMap.get("currentPage") == null ? 1 : Integer.parseInt(paramMap.get("currentPage"));
				List<Object> sub = new ArrayList<Object>(list);
				Pager pager = new Pager(sub, currentPage);
				return pager;
			}
			
			
			// 编辑试题信息
			@RequestMapping(value = "/questions/edit", method = { RequestMethod.GET, RequestMethod.POST })
			public ModelAndView edit(HttpServletRequest request, HttpServletResponse response, String problemId, ModelMap model) {
				// 获取到需要编辑的试题的信息
				ProblemWithBLOBs  pw =problemService.selectByPrimaryKey(Integer.parseInt(problemId));
				model.addAttribute("record",pw);
				List<Course> course=problemService.selectByAllCourse();
			    model.addAttribute("course", course);
				return new ModelAndView("problem/edit", model);
			}
	        //编辑试题修改对应试题信息
			@RequestMapping(value = "/edit/updata", method = { RequestMethod.GET, RequestMethod.POST })
			public String save(HttpServletRequest request, @RequestParam MultiValueMap<String, String> parameters)
					throws Exception {
				logger.info("编辑用户parameter:{}",parameters );
				Map<String, String> paramMap = parameters.toSingleValueMap();
				 	ProblemWithBLOBs pw = new ProblemWithBLOBs();
				 	String problemId = paramMap.get("problemId");
					String problemType = paramMap.get("problemType");
					String courseName = paramMap.get("courseName");
					String problemTitle=paramMap.get("problemTitle");
					String keyA=paramMap.get("keyA");
					String keyB=paramMap.get("keyB");
					String keyC=paramMap.get("keyC");
					String keyD=paramMap.get("keyD");
					String solution=paramMap.get("solution");
					Integer difficulty =Integer.parseInt(paramMap.get("difficulty"));
					pw.setProblemId(Integer.parseInt(problemId));
					pw.setCourseName(courseName);
					pw.setKeyA(keyA);
					pw.setKeyB(keyB);
					pw.setKeyC(keyC);
					pw.setKeyD(keyD);
					pw.setProblemTitle(problemTitle);
					pw.setSolution(solution);
					pw.setProblemType(Integer.parseInt(problemType));
					pw.setDifficulty(difficulty);
					int i=problemService.updateByPrimaryKeySelective(pw);
				//成功就到所有试题界面，失败就回到编辑界面
				if(i>0){
					return "redirect:/questions/manage";
					
				}else{
				return  "redirect:/questions/add?problemId="+problemId;
				}
				
			
			}
			//删除试题
			@RequestMapping(value = "/questions/del")
			@ResponseBody
			public int del(@RequestParam(value = "problemId") Integer  problemId) {
				int count = problemService.deleteByPrimaryKey(problemId);
				return count;
			}
			
			
			//导出试题、
			@SuppressWarnings("unchecked")
			@RequestMapping(value = "/questions/export", method = { RequestMethod.GET, RequestMethod.POST })
			public void exportWorkItem( HttpServletRequest request,HttpServletResponse response) {
				//获取到session中的最新的试题集合
				List<ProblemWithBLOBs> results =(List<ProblemWithBLOBs>) request.getSession().getAttribute("currentQuestions");
				/*List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				HashMap<String, Object> map =new HashMap<>();
				for(ProblemWithBLOBs PW :results){
					map.put("problemId", PW.getProblemId());
					if(PW.getProblemType().equals(1)){
						map.put("problemType", "单选题");
					}else if(PW.getProblemType().equals(2)){
						map.put("problemType", "多选题");
					}else if(PW.getProblemType().equals(3)){
						map.put("problemType", "判断题");
					}else if(PW.getProblemType().equals(4)){
						map.put("problemType", "简答题");
					}else{
						map.put("problemType", "");
					}
					map.put("courseName", PW.getCourseName());
					map.put("problemTitle", PW.getProblemTitle());
					map.put("keyB", PW.getKeyB());
					map.put("keyC", PW.getKeyC());
					map.put("keyD", PW.getKeyD());
					map.put("keyA", PW.getKeyA());
					map.put("solution", PW.getSolution());
					list.add(map);
				}*/
					List<String> header =new ArrayList<String>();
					header.add("试题ID");
					header.add("试题类型(1:单选  2：多选 3：判断 4：简答)");
					header.add("所属科目");
					header.add("试题内容");
					header.add("选项A");
					header.add("选项B");
					header.add("选项C");
					header.add("选项D");
					header.add("参考答案");
					header.add("难度分");
				    //表头对应的字段
					List<String> property =new ArrayList<String>();
					property.add("problemId");
					property.add("problemType");
					property.add("courseName");
					property.add("problemTitle");
					property.add("keyA");
					property.add("keyB");
					property.add("keyC");
					property.add("keyD");
					property.add("solution");
					property.add("difficulty");
					response.reset();
					//获取当前时间做表文件名
					java.util.Date date=new java.util.Date(); 
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
					String title=sdf.format(date);
		            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"); 
		            try {
						response.setHeader("Content-Disposition", "attachment;filename="+ new String((title + ".xls").getBytes(), "iso-8859-1"));
					} catch (UnsupportedEncodingException e1) {
						
						e1.printStackTrace();
					}
					ServletOutputStream outputStream = null;
					try {
						outputStream = response.getOutputStream();
						ExportExcel.exportOneSheetExcel("企业成员工作项", header, property, results, outputStream, "yyyy-MM-dd HH:mm:ss");
					} catch (IOException e) {
						
						e.printStackTrace();
					}
					
				  
			}
			
			/**
			 * @Title:解析试题导入
			 * @param request
			 * @param record
			 * @param model
			 * @param response
			 * @return
			 * @author 崔欧阳
			 */
			@SuppressWarnings("resource")
			@ResponseBody
			@RequestMapping(value = "/questions/import", method = { RequestMethod.GET, RequestMethod.POST })
			public String resolveMilestoneExcel(HttpServletRequest request,  Model model,
					HttpServletResponse response) {
				
				//拿到选取文件
				String fileType = request.getParameter("fileType") == null ? "xlsx" : request.getParameter("fileType");
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			   //Spring 的文件上传
				MultipartFile file = multipartRequest.getFile("uploadMilestone");
				//得到上传时的文件名
				String fileName = file.getOriginalFilename();
				if (fileName.lastIndexOf(".") > 0) {
					fileName = fileName.substring(0, fileName.lastIndexOf("."));
				}
				if (file == null || file.isEmpty()) {
					return "上传文件失败";
				}

				
				logger.info("开始解析:{}", fileName);
				// HSSF提供读写Microsoft Excel XLS格式档案的功能。
				// XSSF提供读写Microsoft Excel OOXML XLSX格式档案的功能
				// Excel的文档对象
				ProblemWithBLOBs  PW;
				if (fileType.equalsIgnoreCase("xls")) {
					HSSFWorkbook hssfWorkbook = null;
					try {
						hssfWorkbook = new HSSFWorkbook(file.getInputStream());
						HSSFSheet st = hssfWorkbook.getSheetAt(0);
						logger.info("有效行:{}", st.getLastRowNum());

						// 取有效行
						for (int rowNumOfSheet = 1; rowNumOfSheet <= st.getLastRowNum(); rowNumOfSheet++) {
							if (st.getRow(rowNumOfSheet) != null) {
								HSSFRow hssfRow = st.getRow(rowNumOfSheet);
								// 用来存储每一行的信息
									PW = saveMilestone(hssfRow);
									problemService.insertSelective(PW);
									continue;
							}
						}
					} catch (IOException e) {
						logger.error("导入试题失败:{}", e);
					}

				} else if (fileType.equalsIgnoreCase("xlsx")) {
					XSSFWorkbook xssfWorkbook = null;
					try {
						xssfWorkbook = new XSSFWorkbook(file.getInputStream());
						XSSFSheet at = xssfWorkbook.getSheetAt(0);
						logger.info("有效行:{}", at.getLastRowNum());
						for (int rowNumOfSheet = 1; rowNumOfSheet <= at.getLastRowNum(); rowNumOfSheet++) {
							if (at.getRow(rowNumOfSheet) != null) {
								XSSFRow xssfRow = at.getRow(rowNumOfSheet);
								// 用来存储每一行的信息
									PW = saveMilestone( xssfRow);
									// parentIdFirst =
									// service.getParentId(milestoneId);milestoneId=(String)
									// map.get("milestoneId");
								//插入数据
									problemService.insertSelective(PW);
									continue;
							}
						}
					} catch (IOException e) {
						logger.error("导入试题失败:{}", e);
						return "导入失败";
					}

				} else {
					return "导入失败";
				}
				return "导入成功";
			}

			/**
			 * 生成UUID
			 * 
			 * @return
			 */
			private String getMilestoneId() {
				return UUID.randomUUID().toString().replaceAll("-", "");
			}
			
			/**
			 * 将导入的数据新增到试题表中去
			 * 
			 * @param projectId
			 * @param object
			 * @param rowNum
			 * @param milestoneId
			 * @param parentId
			 * @author 崔欧阳
			 * 
			 */
			private ProblemWithBLOBs saveMilestone(Object object) {
				//定义导入字段
				Integer problemType;
				String courseName;
				String problemTitle;
				String keyA;
				String keyB;
				String keyC;
				String keyD;
				String solution;
				Integer difficulty;
			   ProblemWithBLOBs PW =new ProblemWithBLOBs();
				if (object instanceof HSSFRow) {
					HSSFRow hssfRow = (HSSFRow) object;
					problemType = Integer.valueOf(getHssfCellValue(hssfRow.getCell(0))) ;
					courseName = getHssfCellValue(hssfRow.getCell(1));
					problemTitle = getHssfCellValue(hssfRow.getCell(2));
					keyA = getHssfCellValue(hssfRow.getCell(3));
					keyB = getHssfCellValue(hssfRow.getCell(4));
					keyC = getHssfCellValue(hssfRow.getCell(5));
					keyD = getHssfCellValue(hssfRow.getCell(6));
					solution = getHssfCellValue(hssfRow.getCell(7));
					difficulty = Integer.valueOf(getHssfCellValue(hssfRow.getCell(8))) ;

				} else {
					XSSFRow xssfRow = (XSSFRow) object;
					problemType = Integer.valueOf(getxssfCellValue(xssfRow.getCell(0))) ;
					courseName = getxssfCellValue(xssfRow.getCell(1));
					problemTitle = getxssfCellValue(xssfRow.getCell(2));
					keyA = getxssfCellValue(xssfRow.getCell(3));
					keyB = getxssfCellValue(xssfRow.getCell(4));
					keyC = getxssfCellValue(xssfRow.getCell(5));
					keyD = getxssfCellValue(xssfRow.getCell(6));
					solution = getxssfCellValue(xssfRow.getCell(7));
					difficulty = Integer.valueOf(getxssfCellValue(xssfRow.getCell(8))) ;
				}
                PW.setCourseName(courseName);
                PW.setKeyA(keyA);
                PW.setKeyB(keyB);
                PW.setKeyC(keyC);
                PW.setKeyD(keyD);
                PW.setProblemTitle(problemTitle);
                PW.setProblemType(problemType);
                PW.setSolution(solution);
                PW.setDifficulty(difficulty);
				return PW;
			}

	
			/**
			 * xls格式，根据单元格不同属性返回字符串数值
			 * 
			 * @param hssfCell
			 * 
			 * @return
			 * 
			 * @author 崔欧阳
			 */
			public String getHssfCellValue(HSSFCell hssfCell) {
				SimpleDateFormat smd = new SimpleDateFormat("yyyy-MM-dd");
				DecimalFormat f = new DecimalFormat();
				if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
					return String.valueOf(hssfCell.getBooleanCellValue());

				} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
					if (HSSFDateUtil.isCellDateFormatted(hssfCell)) {
						return smd.format(hssfCell.getDateCellValue());
					}
					return f.format(hssfCell.getNumericCellValue());
				} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_STRING) {
					return String.valueOf(hssfCell.getStringCellValue());
				} else {
					return "";
				}
			}
			
			/**
			 * xlsx格式，根据单元格不同属性返回字符串数值
			 * 
			 * @param xssfCell
			 * 
			 * @return
			 * 
			 * @author 崔欧阳
			 */
			@SuppressWarnings("static-access")
			public String getxssfCellValue(XSSFCell xssfCell) {
				SimpleDateFormat smd = new SimpleDateFormat("yyyy-MM-dd");
				DecimalFormat f = new DecimalFormat();
				if (xssfCell.getCellType() == xssfCell.CELL_TYPE_BOOLEAN) {
					return String.valueOf(xssfCell.getBooleanCellValue());
				} else if (xssfCell.getCellType() == xssfCell.CELL_TYPE_NUMERIC) {
					if (HSSFDateUtil.isCellDateFormatted(xssfCell)) {
						return smd.format(xssfCell.getDateCellValue());
					}
					return f.format(xssfCell.getNumericCellValue());
				} else if (xssfCell.getCellType() == xssfCell.CELL_TYPE_STRING) {
					return String.valueOf(xssfCell.getStringCellValue());
				} else {
					return "";
				}
			}
			
}
