package com.bsi.ms.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.bsi.ms.model.Course;
import com.bsi.ms.model.ProblemWithBLOBs;
import com.bsi.ms.model.Results;
import com.bsi.ms.model.ResultsKey;
import com.bsi.ms.model.Resultss;
import com.bsi.ms.model.Test;
import com.bsi.ms.model.TestCorrect;
import com.bsi.ms.model.TestKey;
import com.bsi.ms.model.TestSet;
import com.bsi.ms.model.User;
import com.bsi.ms.model.UserStates;
import com.bsi.ms.model.UserStatesKey;
import com.bsi.ms.model.shortSubjectWithBLOBs;
import com.bsi.ms.service.ProblemService;
import com.bsi.ms.service.ResultsService;
import com.bsi.ms.service.ShortSubjectService;
import com.bsi.ms.service.TestService;
import com.bsi.ms.service.TestSetService;
import com.bsi.ms.service.UserService;
import com.bsi.ms.service.UserStatesService;
import com.bsi.ms.utils.ExportExcel;
import com.bsi.ms.utils.Pager;

/**
 * @ClassName: 
 * @Description: 试卷信息设置controller文件
 * @author 崔欧阳
 * @date 2017.4.22
 * 
 */
@Controller
public class TestSetController {
	protected static Logger logger = LogManager.getLogger(TestSetController.class);
	@Autowired
	private ProblemService problemService;
	@Autowired
	private UserService userService;
	@Autowired
	private TestSetService testSetService;
	@Autowired
	private  ShortSubjectService  shortSubjectService;
	@Autowired
	private TestService testService;
	@Autowired
	private ResultsService resultsService;
	@Autowired
	private UserStatesService userStatesService;
	//跳到试卷设置页面
	@RequestMapping(value = "/papers/setPapers", method = { RequestMethod.GET,RequestMethod.POST })
	public String List(HttpServletRequest request,HttpServletResponse response,Model model) {
		 List<Course> course=problemService.selectByAllCourse();
		 model.addAttribute("course", course);
		return "/papers/setPapers";
	}
	
 // 保存试卷设置信息
	@RequestMapping(value = "/papers/setSave", method = { RequestMethod.GET, RequestMethod.POST })
	public String savePapers(HttpServletRequest request, HttpServletResponse response,
			 TestSet testSet ,Model model ) {
		String courseName=testSet.getCourseName();
		TestSet course=testSetService.selectByPrimaryKey(courseName);
		int i=0;
		if(null==course){
			 i = testSetService.insert(testSet);	
		}else{
			
			 i=testSetService.updateByPrimaryKeySelective(testSet);
		}
	
			//成功就到科目简答题信息界面，失败就回到试卷设置界面
			if(i>0){
				//拿到简答题个数， 生成的简答题在重新设置之前不会发生改变
				int shortAnswer=testSet.getShortAnswer();
				//判断有没有简答，没有直接跳转
				if(shortAnswer>0){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("courseName", courseName);
				map.put("problemType", 4);
				//获取到所有这个科目的简答题
				List<ProblemWithBLOBs> list = problemService.selectByAll(map);
				int length=list.size();
				int[] a =randomSubject(length, shortAnswer);
				//拿到随机生成的题的难度分总分 和设置的总分要求去比较
				int DifficultPoints=0;
				int answerDifficultyScore=testSet.getAnswerDifficultyScore();
				for(int b=0;b<a.length;b++){
					ProblemWithBLOBs pw=list.get(a[b]);
					DifficultPoints += pw.getDifficulty();
				}
				while(DifficultPoints != answerDifficultyScore){
					//重新初始化值
					 DifficultPoints=0;
					//重新获取随机数数组  再次进行统计
					 a = randomSubject(length, shortAnswer);
					 for(int b=0;b<a.length;b++){
						ProblemWithBLOBs pw=list.get(a[b]);
						DifficultPoints+=pw.getDifficulty();
					}
				}
				//将简答题放入试卷的简答题表中
				shortSubjectWithBLOBs ssw = new shortSubjectWithBLOBs();
				List<shortSubjectWithBLOBs> shortSubjectList=shortSubjectService.selectByCourseName(courseName);
				//如果第一次设置本科目，就直接插入表格中
				if(null==shortSubjectList){
					for(int b=0;b<a.length;b++){
						ProblemWithBLOBs pw=list.get(a[b]);
						ssw.setProblemId(pw.getProblemId());
						ssw.setCourseName(pw.getCourseName());
						ssw.setProblemTitle(pw.getProblemTitle());
						ssw.setSolution(pw.getSolution());
						ssw.setDifficulty(pw.getDifficulty());
						shortSubjectService.insertSelective(ssw);
					}
				}else{
					//不是第一次设置，就清除该科目试卷简答题 在进行添加
					int d=shortSubjectService.deleteByCourseName(courseName);
					if(d>0){
						for(int b=0;b<a.length;b++){
							ProblemWithBLOBs pw=list.get(a[b]);
							ssw.setProblemId(pw.getProblemId());
							ssw.setCourseName(pw.getCourseName());
							ssw.setProblemTitle(pw.getProblemTitle());
							ssw.setSolution(pw.getSolution());
							ssw.setDifficulty(pw.getDifficulty());
							shortSubjectService.insertSelective(ssw);
						}	
					}
					
				}
				
				}
				
				return "/papers/correctssss";
				
			}else{
			return  "redirect:/papers/setPapers";
			}
	
	}
	
	    //跳到考试科目选择界面
		@RequestMapping(value = "/subject/choice", method = { RequestMethod.GET,RequestMethod.POST })
		public String subject(HttpServletRequest request,HttpServletResponse response,Model model) {
			//所有科目
			 List<Course> course=problemService.selectByAllCourse();
			 model.addAttribute("course", course);
			return "/papers/chooseCourse";
		}
		
		
		  //生成该考生的试卷信息
				@RequestMapping(value = "/subject/papers", method = { RequestMethod.GET,RequestMethod.POST })
				public ModelAndView papers(HttpServletRequest request,HttpServletResponse response,ModelMap model
						) {
				//获取到选择的是哪一科的考试
				String courseName=request.getParameter("courseName");
				/*//定义一个试卷的Map
				HashMap<String, List > papersMap = new HashMap<>();*/
				//拿到该科目的试卷设置信息
				TestSet  testSet=testSetService.selectByPrimaryKey(courseName);
				//随机生成单选题,先判断老师有没有设置题目
				if(null==testSet){
				model.addAttribute("messg", "老师没有设置该科试题，请咨询老师");
				//所有科目
				 List<Course> course=problemService.selectByAllCourse();
				 model.addAttribute("course", course);
				 return new ModelAndView("/papers/chooseCourse", model);
				}
				
				
				try{
				int radio=testSet.getRadio();
				int choice=testSet.getChoice();
				int judge=testSet.getJudge();
				if(radio>0){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("courseName", courseName);
				map.put("problemType", 1);
				List<ProblemWithBLOBs> list=problemService.selectByAll(map);
				//题库所有的单选题个数
				int length=list.size();
				int[] a =randomSubject(length, radio);
				//拿到随机生成的题的难度分总分 和设置的总分要求去比较
				int DifficultPoints=0;
				int radioDifficultyScore=testSet.getRadioDifficultyScore();
				for(int b=0;b<a.length;b++){
					ProblemWithBLOBs pw=list.get(a[b]);
					DifficultPoints += pw.getDifficulty();
				}
				while(DifficultPoints != radioDifficultyScore){
					//重新初始化值
					 DifficultPoints=0;
					//重新获取随机数数组  再次进行统计
					 a = randomSubject(length, radio);
					 for(int b=0;b<a.length;b++){
						ProblemWithBLOBs pw=list.get(a[b]);
						DifficultPoints+=pw.getDifficulty();
					}
				}
				//将生成好的单选试题list添加到Map中
				List<ProblemWithBLOBs> radioList = new ArrayList<>();
				for(int b=0;b<a.length;b++){
					ProblemWithBLOBs pw=list.get(a[b]);
					radioList.add(pw);
					
				}
				/*papersMap.put("radioList",radioList);*/
				model.addAttribute("radioList", radioList);
				}
				
				//随机组卷————多选题
				if(choice>0){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("courseName", courseName);
					map.put("problemType", 2);
					List<ProblemWithBLOBs> list=problemService.selectByAll(map);
					//题库所有的单选题个数
					int length=list.size();
					int[] a =randomSubject(length, choice);
					//拿到随机生成的题的难度分总分 和设置的总分要求去比较
					int DifficultPoints=0;
					int choiceDifficultyScore=testSet.getChoiceDifficultyScore();
					for(int b=0;b<a.length;b++){
						ProblemWithBLOBs pw=list.get(a[b]);
						DifficultPoints += pw.getDifficulty();
					}
					while(DifficultPoints != choiceDifficultyScore){
						//重新初始化值
						 DifficultPoints=0;
						//重新获取随机数数组  再次进行统计
						 a = randomSubject(length, choice);
						 for(int b=0;b<a.length;b++){
							ProblemWithBLOBs pw=list.get(a[b]);
							DifficultPoints+=pw.getDifficulty();
						}
					}
					
					
					//将生成好的多选试题list添加到Map中
					List<ProblemWithBLOBs> choiceList = new ArrayList<>();
					for(int b=0;b<a.length;b++){
						ProblemWithBLOBs pw=list.get(a[b]);
						choiceList.add(pw);
						
					}
					//papersMap.put("choiceList",choiceList);
					model.addAttribute("choiceList",choiceList);
				}
				
				
				//随机组卷————判断题
				if(judge>0){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("courseName", courseName);
					map.put("problemType", 3);
					List<ProblemWithBLOBs> list=problemService.selectByAll(map);
					//题库所有的判断题个数
					int length=list.size();
					int[] a =randomSubject(length, judge);
					//拿到随机生成的题的难度分总分 和设置的总分要求去比较
					int DifficultPoints=0;
					int judgeDifficultyScore=testSet.getJudgeDifficultyScore();
					for(int b=0;b<a.length;b++){
						ProblemWithBLOBs pw=list.get(a[b]);
						DifficultPoints += pw.getDifficulty();
					}
					while(DifficultPoints != judgeDifficultyScore){
						//重新初始化值
						 DifficultPoints=0;
						//重新获取随机数数组  再次进行统计
						 a = randomSubject(length, judge);
						 for(int b=0;b<a.length;b++){
							ProblemWithBLOBs pw=list.get(a[b]);
							DifficultPoints+=pw.getDifficulty();
						}
					}
					
					
					//将生成好的判断题list添加到Map中
					List<ProblemWithBLOBs> judgeList = new ArrayList<>();
					for(int b=0;b<a.length;b++){
						ProblemWithBLOBs pw=list.get(a[b]);
						judgeList.add(pw);
						
					}
					//papersMap.put("judgeList",judgeList);
					model.addAttribute("judgeList",judgeList);
					
				}
				
				
				
				//获取随机组卷的该科目的简答题
				List<shortSubjectWithBLOBs> shortSubjectList=shortSubjectService.selectByCourseName(courseName);
				//papersMap.put("shortSubjectList", shortSubjectList);
				model.addAttribute("shortSubjectList",shortSubjectList);
				model.addAttribute("testSet", testSet);
				
				//判断考试的人员，如果是学生就把他加入到用户状态表中
				User user  = (User) request.getSession().getAttribute("user");
				if(user.getType()==1){
				//将用户加入到用户声明状态为未提交
				String userId=user.getUserId();
				UserStatesKey USK = new UserStatesKey();
				USK.setCourseName(courseName);
				USK.setUserId(userId);
				UserStates US = new UserStates();
				US.setCourseName(courseName);
				US.setStates(0);
				US.setUserId(userId);
				if(null== userStatesService.selectByPrimaryKey(USK)){
					userStatesService.insertSelective(US);
				}else{
					userStatesService.updateByPrimaryKeySelective(US);
				}
				}
				
				}catch (Exception e) {
					//所有科目
				List<Course> course=problemService.selectByAllCourse();
				model.addAttribute("course", course);	
				model.addAttribute("mess", "试题生成失败，题库中该科目题目数量不符合老师设置要求，请咨询老师");	
				return new ModelAndView("/papers/chooseCourse", model);	
				}
			   
				//跳转试卷显示界面，携带试卷内容
				return  new ModelAndView("/papers/show", model);
				
				
				}
				
				
    //生成随机数组		
	private int[] randomSubject(int n,int m ){
		//根据试题库中的总个数 来取出设置个数的随机不重复的试题
		int[] v = new int[m];
		  boolean[] b = new boolean[n];
		  Random r = new Random();
		  do{
		   int x = r.nextInt(n);
		   if(!b[x]){
		    v[--m]=x;
		    b[x]=true;
		   }
		  }
		  while(m>0);
		  return v;
		/*Random r = new Random();
		 int a[] = new int[n];
	        for (int j = 0; j < a.length; j++) {
	            a[j] = r.nextInt(sum-1);
	            for (int k = 1; k < j; k++) {
	                while (a[j] == a[k]) {//如果重复，退回去重新生成随机数
	                    k--;
	                }
	            }
	        }
	        
	        return a;*/
	}
	
	        //系统批改试卷客观题
		@RequestMapping(value = "/papers/mark", method = { RequestMethod.GET,RequestMethod.POST })
		@ResponseBody
		public float mark(HttpServletRequest request,HttpServletResponse response,ModelMap model) throws UnsupportedEncodingException {
			//拿到考生的答案
			Map<String, String[]> parameterMap = request.getParameterMap();
			String problemId;
			//拿到考生的Id,考试科目,本次考试的设置信息
			User user  = (User) request.getSession().getAttribute("user");
			String userId=user.getUserId();
			String[] userSolution;
			String courseName=request.getParameter("courseName");
			TestSet testSet = testSetService.selectByPrimaryKey(courseName);
			//定义一个试卷  来保存试题信息
			Test test = new Test();
			//分数
			float score = 0;
			float radioGrade = 0;
			float selectGrade=0;
			float judgeGrade=0;
			float shortAnswerGrade=0;
			float theScore=0;
			float  scoreRadio=testSet.getRadioScore()/testSet.getRadio();
			float  scorechoice=testSet.getChoiceScore()/testSet.getChoice();
			float  scoreJudge=testSet.getJudgeScore()/testSet.getJudge();
			String selectSolution =" ";
			TestKey TK = new TestKey();
			TK.setUserId(userId);
			test.setScore(theScore);
			for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
				 problemId = entry.getKey();
				 userSolution =entry.getValue();
				 if(problemId.equals("courseName") ){
					continue; 
				 }
				ProblemWithBLOBs pw = problemService.selectByPrimaryKey(Integer.parseInt(problemId));
				if(pw.getProblemType()==1){
				 if(pw.getSolution().equals(userSolution[0])){
					 radioGrade+=scoreRadio;
					 score+=scoreRadio;
					 test.setScore(scoreRadio);
				 }
				 }
				if(pw.getProblemType()==2){
					for(int n=0;n<userSolution.length;n++){
						selectSolution+=userSolution[n];	
					}
					 if(pw.getSolution().equals(selectSolution)){
						 selectGrade+=scorechoice;
						 score+=scorechoice;
						 test.setScore(scorechoice);
					 }
					 }
				if(pw.getProblemType()==3){
					 if(pw.getSolution().equals(userSolution[0])){
						 judgeGrade+=scoreJudge;
						 score+=scoreJudge;
						 test.setScore(scoreJudge);
					 }
					 }
				//将该题放入试卷表
				TK.setProblemId(Integer.parseInt(problemId));
				test.setCourseName(courseName);
				test.setProblemId(Integer.parseInt(problemId));
				test.setProblemType(pw.getProblemType());
				test.setUserId(userId);
				if(pw.getProblemType()==2){
				test.setUserSolution(selectSolution);
				//如果是多选题，就把拼接的答案设置进去，然后把设置完test之后，清空selectSoution
				selectSolution="";
				}else{
				test.setUserSolution(userSolution[0]);
				}
				if(null==testService.selectByPrimaryKey(TK)){
					testService.insertSelective(test);				
				}else{
					testService.updateByPrimaryKey(test);
				}
				//当计算完一题，就把test里面的该题得分清空  方便下次再加新的分数
				test.setScore(theScore);	
				
		}
			//将该考生的客观题成绩录入成绩表；
			
			Results results = new Results();
			results.setCourseName(courseName);
			results.setJudgeGrade(judgeGrade);
			results.setRadioGrade(radioGrade);
			results.setSelectGrade(selectGrade);
			results.setShortAnswerGrade(shortAnswerGrade);
			results.setScore(score);
			//将该学生的成绩批改状态变为未批改
			results.setWhetherGrade(0);
			results.setUserId(userId);
			ResultsKey  RK = new ResultsKey();
			RK.setCourseName(courseName);
			RK.setUserId(userId);
			//判断数据库中该考生的成绩是否存在，存在则修改，不存在就插入
			if(null==resultsService.selectByPrimaryKey(RK)){
				resultsService.insertSelective(results);
			}else{
			resultsService.updateByPrimaryKeySelective(results);
			}
			//修改用户状态为已提交
			if(user.getType()==1){
				//将用户加入到用户声明状态为未提交
			    userId=user.getUserId();
				UserStatesKey USK = new UserStatesKey();
				USK.setCourseName(courseName);
				USK.setUserId(userId);
				UserStates US = new UserStates();
				US.setCourseName(courseName);
				US.setStates(1);
				US.setUserId(userId);
				if(null== userStatesService.selectByPrimaryKey(USK)){
					userStatesService.insertSelective(US);
				}else{
					userStatesService.updateByPrimaryKeySelective(US);
				}
				}
			 //统计客观题分数，返回到页面
			float scoress=radioGrade+judgeGrade+selectGrade;
			return scoress;
		}
		
		
		
		
		//查询考生考生结果信息
		@RequestMapping(value = "/results/query", method = { RequestMethod.GET, RequestMethod.POST })
		@ResponseBody
		public Pager list(HttpServletRequest request, @RequestParam MultiValueMap<String, String> parameters,
				ModelMap model) {
			Map<String, Object> map = new HashMap<String, Object>();
			Map<String, String> paramMap = parameters.toSingleValueMap();
			//查询参数
			String courseName = paramMap.get("courseName");
			String userId = paramMap.get("userId");
			String whetherGrade = paramMap.get("whetherGrade");
			map.put("courseName", courseName);
			map.put("userId",userId);
			map.put("whetherGrade", whetherGrade);
			List<Results> list = resultsService.selectByMap(map);
			model.addAttribute("list", list);
			int currentPage = paramMap.get("currentPage") == null ? 1 : Integer.parseInt(paramMap.get("currentPage"));
			List<Object> sub = new ArrayList<Object>(list);
			Pager pager = new Pager(sub, currentPage);
			return pager;
		}
		
		
		
		//跳转到学生的简答题页面，供老师批改
		@RequestMapping(value = "/correct/paper", method = { RequestMethod.GET,RequestMethod.POST })
		public String corrt(HttpServletRequest request,HttpServletResponse response,ModelMap model) {
		    ArrayList<Object> list = new ArrayList<>();
		   
		    //拿到批改的学生的id和科目
			String userId = request.getParameter("user");
			String courseName = request.getParameter("course");
			//拿到该科目的设置信息，防止老师改卷错误
			TestSet testSet=testSetService.selectByPrimaryKey(courseName);
			model.addAttribute("testSet", testSet);
			//拿到该考生的该门科目的所有简答题信息
			
			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("courseName", courseName);
			maps.put("userId", userId);
			maps.put("problemType", '4');
			List<Test> lists = testService.selectByMap(maps);
			Integer problemId;
			for(Test test : lists){
				TestCorrect TC = new TestCorrect();
				TC.setCourseName(courseName);
				TC.setUserId(userId);
				problemId = test.getProblemId();
				ProblemWithBLOBs PW = problemService.selectByPrimaryKey(problemId);
				TC.setProblemTitle(PW.getProblemTitle());
				TC.setSolution(PW.getSolution());
				TC.setUserSolution(test.getUserSolution());
				TC.setDifficulty(PW.getDifficulty());
				TC.setProblemId(problemId);
				list.add(TC);
			}
			model.addAttribute("list",list);
			return  "/papers/correct";
		}
		
		
		
		 // 保存老师试卷批改分数
		@RequestMapping(value = "/papers/save", method = { RequestMethod.GET, RequestMethod.POST })
		public String ShortSave(HttpServletRequest request, HttpServletResponse response,
				 TestSet testSet ,Model model ) {
			//拿到老师的评分
			Map<String, String[]> parameterMap = request.getParameterMap();
			String problemId;
			String[] userSolution;
			Test test = new Test();
			Results results = new Results();
			String courseName = request.getParameter("courseName");
			String userId = request.getParameter("userId");
			//修改总成绩表
			results.setUserId(userId);
			results.setCourseName(courseName);
			results.setWhetherGrade(1);
			//修改试卷表
			test.setUserId(userId);
			//定义一个分数 统计一下简答题分数
			float shortScore=0;
			for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
				 problemId = entry.getKey();
				 userSolution =entry.getValue();
				 if(problemId.equals("courseName") ){
					continue; 
				 }
			     if(problemId.equals("userId")){
			    	 continue; 
			    	 }
			     shortScore+=Float.valueOf(userSolution[0]);
			     test.setProblemId(Integer.parseInt(problemId));
			     test.setScore(Float.valueOf(userSolution[0]));
			     testService.updateByPrimaryKeySelective(test);
			     }
			
			 results.setShortAnswerGrade(shortScore);
			 ResultsKey  RK = new ResultsKey();
			 RK.setCourseName(courseName);
			 RK.setUserId(userId);
			 Results re = resultsService.selectByPrimaryKey(RK);
			 results.setScore(re.getRadioGrade()+re.getSelectGrade()+re.getJudgeGrade()+shortScore);
			 resultsService.updateByPrimaryKeySelective(results);
			return"redirect:/papers/correctssss";
			
		}
		
		//跳到试卷批改界面
		@RequestMapping(value = "/papers/correctssss", method = { RequestMethod.GET,RequestMethod.POST })
		public String correctList(HttpServletRequest request,HttpServletResponse response,Model model) {
			List<Course> course=problemService.selectByAllCourse();
			model.addAttribute("course", course);
			Map<String, Object> map = new HashMap<String, Object>();
			List<Results> list = resultsService.selectByMap(map);
			request.getSession().setAttribute("chengji", list);
			model.addAttribute("list", list);
			return "/papers/check";
		}
		
		//查看自己成绩和批改状态
		@RequestMapping(value = "/look/query", method = { RequestMethod.GET,RequestMethod.POST })
		public String look(HttpServletRequest request,HttpServletResponse response,Model model) {
			Map<String, Object> map = new HashMap<String, Object>();
			User user = (User) request.getSession().getAttribute("user");
			String userId=user.getUserId();
			map.put("userId",userId);
			List<Results> list = resultsService.selectByMap(map);
			model.addAttribute("list", list);
			return "/grade/query";
		}
		
		//导出试题、
		@SuppressWarnings({ "unchecked" })
		@RequestMapping(value = "/mark/export", method = { RequestMethod.GET, RequestMethod.POST })
		public void exportWorkItem( HttpServletRequest request,HttpServletResponse response) {
			//获取到session中的最新的试题集合
			    List<Results> list=(List<Results>) request.getSession().getAttribute("chengji");
			    List<Resultss> results = new ArrayList<>();
				for(Results rl : list){
					User user = userService.selectByPrimaryKey(rl.getUserId());
					//如果该考生信息已经被删除，则不导出该考生信息
					if(user==null){
					continue;	
					}else{
					Resultss resultss = new Resultss();
					resultss.setUserId(rl.getUserId());
					resultss.setName(user.getName());
					resultss.setCourseName(rl.getCourseName());
					resultss.setRadioGrade(rl.getRadioGrade());
					resultss.setSelectGrade(rl.getSelectGrade());
					resultss.setJudgeGrade(rl.getJudgeGrade());
					resultss.setShortAnswerGrade(rl.getShortAnswerGrade());
					resultss.setScore(rl.getScore());
					resultss.setWhetherGrade(rl.getWhetherGrade());
					results.add(resultss);
					}
				}
				List<String> header =new ArrayList<String>();
				header.add("考生ID");
				header.add("考生姓名");
				header.add("所属科目");
				header.add("单选题得分");
				header.add("多选题得分");
				header.add("判断题得分");
				header.add("简答题得分");
				header.add("总分");
				header.add("是否批改");
				header.add("备注");
			    //表头对应的字段
				List<String> property =new ArrayList<String>();
				property.add("userId");
				property.add("name");
				property.add("courseName");
				property.add("radioGrade");
				property.add("selectGrade");
				property.add("judgeGrade");
				property.add("shortAnswerGrade");
				property.add("score");
				property.add("whetherGrade");
				property.add(" ");
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
					ExportExcel.exportOneSheetExcel("考生成绩表", header, property, results, outputStream, "yyyy-MM-dd HH:mm:ss");
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				
			  
		}
		
}
