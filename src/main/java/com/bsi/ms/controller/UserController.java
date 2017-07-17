package com.bsi.ms.controller;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import com.bsi.ms.model.ProblemWithBLOBs;
import com.bsi.ms.model.User;
import com.bsi.ms.service.UserService;
import com.bsi.ms.utils.Pager;


/**
 * @ClassName: LoginController
 * @Description: 用户信息管理controller文件
 * @author 崔欧阳
 * @date 2017.4.20
 * 
 */
@Controller
public class UserController {
	protected static Logger logger = LogManager.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	//跳转到个人信息界面
	@RequestMapping(value = "/userInfo/list", method = { RequestMethod.GET,RequestMethod.POST })
	public String list(HttpServletRequest request,HttpServletResponse response,Model model) {
		//获取登录者的信息
		User user  = (User) request.getSession().getAttribute("user");
	    String rol = (String)request.getSession().getAttribute("role");
		model.addAttribute("rol", rol);
		model.addAttribute("user", user);
		logger.info("登陆用户信息查询查询result:{}", user);
		return "/userInfo/userInfo";
	}
	//跳转到新增成员界面
	@RequestMapping(value = "/user/addUser", method = { RequestMethod.GET, RequestMethod.POST })
	public String getSeriaNo(HttpServletRequest request, HttpServletResponse response,
			@RequestParam MultiValueMap<String, String> parameters ,ModelMap model) {
		    return  "userInfo/addUser";
	}
	
	// 校验用户id
		@RequestMapping(value = "/user/saveValidate", method = { RequestMethod.GET, RequestMethod.POST })
		@ResponseBody
		public String newItemsvalidate(HttpServletRequest request, HttpServletResponse response,
				@RequestParam(value = "userId") String userId) {
			User user=userService.selectByPrimaryKey(userId);
			String validate="0";
			if(user!=null){
				validate="1";
			}
			logger.info("新增验证结果：",validate );
			return validate;
		}

		// 保存新增的用户信息
		@RequestMapping(value = "/user/save", method = { RequestMethod.GET, RequestMethod.POST })
		public String saveItems(HttpServletRequest request, HttpServletResponse response,
				@RequestParam MultiValueMap<String, String> parameters) {
			logger.info("新增用户parameter:{}",parameters );
			Map<String, String> paramMap = parameters.toSingleValueMap();
		    User user = new User();
			String userId = paramMap.get("userId");
			String name = paramMap.get("name");
			String type = paramMap.get("type");
			String password=paramMap.get("password");
			String sex=paramMap.get("sex");
			user.setName(name);
			user.setPassword(password);
			user.setSex(sex);
			user.setType(Integer.parseInt(type));
			user.setUserId(userId);
			int i=userService.insert(user);
			//成功就到所有用户界面，失败就回到新增界面
			if(i>0){
				return "redirect:/user/list";
				
			}else{
			return  "redirect:/user/addUser";
			}
			
		}
		//查询用户信息
		@RequestMapping(value = "/user/query", method = { RequestMethod.GET, RequestMethod.POST })
		@ResponseBody
		public Pager list(HttpServletRequest request, @RequestParam MultiValueMap<String, String> parameters,
				ModelMap model) {
			Map<String, Object> map = new HashMap<String, Object>();
			Map<String, String> paramMap = parameters.toSingleValueMap();
			//查询参数
			String type = paramMap.get("type");
			String userId = paramMap.get("userId");
			String name = paramMap.get("name");
			map.put("type", type);
			map.put("userId",userId);
			map.put("name", name);
			List<User> list=userService.selectBySelective(map);
			model.addAttribute("list", list);
			int currentPage = paramMap.get("currentPage") == null ? 1 : Integer.parseInt(paramMap.get("currentPage"));
			List<Object> sub = new ArrayList<Object>(list);
			Pager pager = new Pager(sub, currentPage);
			return pager;
		}
		//跳转到所有成员界面
		@RequestMapping(value = "/user/list", method = { RequestMethod.GET, RequestMethod.POST })
		public String getAllUser(HttpServletRequest request, HttpServletResponse response,
				@RequestParam MultiValueMap<String, String> parameters ,ModelMap model) {
			Map<String, Object> map = new HashMap<String, Object>();
			List<User> list=userService.selectBySelective(map);
			model.addAttribute("list", list);
			return  "userInfo/userList";
		}
		
		
		// 编辑用户信息
		@RequestMapping(value = "/user/edit", method = { RequestMethod.GET, RequestMethod.POST })
		public ModelAndView edit(HttpServletRequest request, HttpServletResponse response, String userId, ModelMap model) {
			// 获取到需要编辑的用户的信息
			User users  = (User) request.getSession().getAttribute("user");
			User user = userService.selectByPrimaryKey(userId);
			model.addAttribute("record",user);
			if(users.getUserId().equals(userId)){
				return new ModelAndView("userInfo/editpassword", model);
			}
			return new ModelAndView("userInfo/editUser", model);
		}
        //编辑用户修改对应用户信息
		@RequestMapping(value = "/edit/saveUser", method = { RequestMethod.GET, RequestMethod.POST })
		public String save(HttpServletRequest request, @RequestParam MultiValueMap<String, String> parameters)
				throws Exception {
			logger.info("编辑用户parameter:{}",parameters );
			Map<String, String> paramMap = parameters.toSingleValueMap();
		    User user = new User();
			String userId = paramMap.get("userId");
			String name = paramMap.get("name");
			String type = paramMap.get("type");
			String password=paramMap.get("password");
			String sex=paramMap.get("sex");
			user.setName(name);
			user.setPassword(password);
			user.setSex(sex);
			user.setType(Integer.parseInt(type));
			user.setUserId(userId);
			int i=userService.updateByPrimaryKeySelective(user);
			//成功就到所有用户界面，失败就回到编辑界面
			if(i>0){
				return "redirect:/user/list";
				
			}else{
			return  "redirect:/user/edit?userId="+userId;
			}
		
		}
		//删除用户
		@RequestMapping(value = "/user/del")
		@ResponseBody
		public Object del(@RequestParam(value = "userId") String userId) {
			int count = userService.del(userId);
			return count;
		}
		
		
		/**
		 * @Title:解析用户导入
		 * @param request
		 * @param record
		 * @param model
		 * @param response
		 * @return
		 * @author 崔欧阳
		 */
		@SuppressWarnings("resource")
		@ResponseBody
		@RequestMapping(value = "/user/import", method = { RequestMethod.GET, RequestMethod.POST })
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
			User user;
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
								user = saveMilestone(hssfRow);
								userService.insert(user);
								continue;
						}
					}
				} catch (IOException e) {
					logger.error("导入用户失败:{}", e);
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
							user = saveMilestone( xssfRow);
								// parentIdFirst =
								// service.getParentId(milestoneId);milestoneId=(String)
								// map.get("milestoneId");
							//插入数据
								userService.insert(user);
								continue;
						}
					}
				} catch (IOException e) {
					logger.error("导入用户失败:{}", e);
					return "导入用户失败";
				}

			} else {
				return "导入用户失败";
			}
			return "导入用户成功";
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
		private User saveMilestone(Object object) {
			//定义导入字段
			String userId;
			String name;
			String password;
			String sex;
			Integer type;
		    User user =new User();
			if (object instanceof HSSFRow) {
				//默认密码等于ID
				HSSFRow hssfRow = (HSSFRow) object;
				userId = getHssfCellValue(hssfRow.getCell(0)) ;
				name = getHssfCellValue(hssfRow.getCell(1));
				password = getHssfCellValue(hssfRow.getCell(0));
				sex = getHssfCellValue(hssfRow.getCell(2));
				type = Integer.valueOf(getHssfCellValue(hssfRow.getCell(3)));
			} else {
				XSSFRow xssfRow = (XSSFRow) object;
				userId = getxssfCellValue(xssfRow.getCell(0));
				name = getxssfCellValue(xssfRow.getCell(1));
				password= getxssfCellValue(xssfRow.getCell(0));
				sex = getxssfCellValue(xssfRow.getCell(2));
				type = Integer.valueOf(getxssfCellValue(xssfRow.getCell(3)));
			}
           user.setUserId(userId);
           user.setName(name);
           user.setPassword(password);
           user.setSex(sex);
           user.setType(type);
			return user;
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
