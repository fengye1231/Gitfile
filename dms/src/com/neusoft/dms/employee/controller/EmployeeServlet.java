package com.neusoft.dms.employee.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neusoft.dms.domain.PageQuery;
import com.neusoft.dms.domain.Table;
import com.neusoft.dms.employee.domain.Employee;
import com.neusoft.dms.employee.domain.EmployeeVo;
import com.neusoft.dms.employee.service.EmployeeService;
import com.neusoft.dms.employee.service.EmployeeServiceImpl;

import com.neusoft.dms.util.Constant;
import com.neusoft.dms.util.InvaliedParamException;
import com.neusoft.dms.util.ParameterUtil;
import com.neusoft.dms.util.PasswordHash;
import com.neusoft.dms.util.ServiceException;
import com.neusoft.dms.util.ServletUtil;
import com.neusoft.dms.util.State;

public class EmployeeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	    this.doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取用户所请求的服务器
		String service =  request.getParameter("service");
		if("addEmployee".equals(service)){                
			addEmployee(request,response);
		}
		else if("deleteEmployee".equals(service)){          
			deleteEmployee(request,response);                                                
		}
		else if("modifyEmployee".equals(service)){
			modifyEmployee(request,response);
		}
		else if("updateEmployee".equals(service)){                      
			updateEmployee(request,response);                                                                                       
		}     
		else if ("lookEmployee".equals(service)) {
			lookEmployee(request, response);
		}
		else if("updateEmployeeInfo".equals(service)){                      
			updateEmployeeInfo(request,response);          
		}
		else if("updatePassword".equals(service)){
			updatePassword(request, response);
		}
		else if("searchEmp".equals(service)){
			searchEmp(request,response);
		}
		else if("defaultPassword".equals(service)){
			defaultPassword(request, response);
		}else if("changePassword".equals(service)){
			changePassword(request,response);
		}
	}              
	//添加新员工                    
	public void addEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {              
	    	/*获取请求报文的信息*/
	   	    String username = request.getParameter("username");
	   	    String empName = request.getParameter("empName");                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
	   	    String password = request.getParameter("password");
	   	    Date joinDate = ParameterUtil.getDateFloor(request, "joinDate");
	   	    String email = request.getParameter("email");
	   	    int isleader =  ParameterUtil.getInt(request,"isleader");
	   	    int superiorId = ParameterUtil.getInt(request, "superiorId");
	     	int roleId = ParameterUtil.getInt(request, "roleId");
	     	int deptId = ParameterUtil.getInt(request, "deptId");
	     	String remark = request.getParameter("remark");
	     	String sex = request.getParameter("sex");
	     	
	   	    Employee emp = new Employee();
	   	    emp.setUsername(username);
	   	    emp.setEmpName(empName);
	   	    String hash = PasswordHash.createHash(password);
	   	    emp.setPassword(hash);
	   	    emp.setEmail(email);
	   	    emp.setJoinDate(joinDate);
	   	    emp.setIsleader(isleader);
	   	    emp.setSuperiorId(superiorId);
	   	    emp.setRoleId(roleId);
	   	    emp.setDeptId(deptId);
	   	    emp.setRemark(remark);
	   	    emp.setSex(sex);
	     	
	   	    /*调用service层方法处理请求*/
	   	    EmployeeService emps = EmployeeServiceImpl.getInstance();
	   	    boolean isSuccess = emps.addEmployee(emp);
	   	    
	   	    /*生成响应*/
	   	    if(isSuccess){
	   	    	   request.setAttribute("tip", "添加成功");
	               request.getRequestDispatcher("/info/employee/index.jsp").forward(request, response);
	   	    }
	   	    else{
	   	    	request.getRequestDispatcher("/info/employee/index.jsp").forward(request, response);
	   	    }
			
		} catch (ServiceException e){
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/error");
			rd.forward(request, response);
		} catch (InvaliedParamException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {	
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {			
			e.printStackTrace();
		}
	}
	//删除员工
	public void deleteEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = ParameterUtil.get(request, "username");
		
		String[] usernameStrings = username.split(",");
		List<String> usernames = new ArrayList<String>();
		for(int i=0;i<usernameStrings.length;i++){
			usernames.add((usernameStrings[i]));

		}
		try{
		     for(String u:usernames){			
			     EmployeeService emps = EmployeeServiceImpl.getInstance();
			     EmployeeVo emp = emps.search(u);
			     int empId = emp.getEmpId();
			     emps.deleteEmployee(empId);			
			 }
		     ServletUtil.stateJson(response, State.OK);
		}catch (ServiceException e) {
				e.printStackTrace();
				ServletUtil.stateJson(response, State.FAILED);
		}
		
	
	}
	//管理员选择修改信息
	public void modifyEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			/*获取请求报文的信息*/
	   	    String username = request.getParameter("username");
	   	   
	   	    EmployeeService emps = EmployeeServiceImpl.getInstance();
	    	EmployeeVo emp = emps.search(username);
	    	
	    	request.setAttribute("employee", emp);
	    	request.getRequestDispatcher("/info/employee/modifyinfo/index.jsp").forward(request, response);
	    	//response.sendRedirect(response.encodeRedirectURL("/dms/info/employee/modifyinfo/index.jsp"));
		} catch (ServiceException e){
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/error");
			rd.forward(request, response);
		}	
		
	}
	//修改员工信息
	
	public void updateEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
	    	/*获取请求报文的信息*/
			String username = request.getParameter("username");
	   	    String empName = request.getParameter("empName");
	   	    String email = request.getParameter("email");
	    	int isleader =  ParameterUtil.getInt(request, "isleader");
	   	    int superiorId = ParameterUtil.getInt(request, "superiorId");
	     	int roleId = ParameterUtil.getInt(request, "roleId");
	     	System.out.println(roleId);
	     	int deptId = ParameterUtil.getInt(request, "deptId");
	     	String remark = request.getParameter("remark");
	     	String sex = request.getParameter("sex");
	     	
	     		     	   	    
	   	    EmployeeVo emp = new EmployeeVo();
	   	   
	   	    emp.setUsername(username);
	   	    emp.setEmpName(empName);
	   	    emp.setEmail(email);
	   	    emp.setIsleader(isleader);
	   	    emp.setSuperiorId(superiorId);
	   	    emp.setRoleId(roleId);
	   	    emp.setDeptId(deptId);
	   	    emp.setRemark(remark);
	   	    emp.setSex(sex);
	   	         	
	   	    /*调用service层方法处理请求*/
	   	    EmployeeService emps = EmployeeServiceImpl.getInstance();
	   	    emps.updateEmployee(emp);
	        
	   	    EmployeeVo emp1 = emps.search(username);
	        
	   	    request.setAttribute("employee", emp1);
	        request.getRequestDispatcher("/info/employee/modifyinfo/index.jsp").forward(request, response);
	   	    //response.sendRedirect(response.encodeRedirectURL("/dms/info/employee/modifyinfo/index.jsp"));
	   	    
		} catch (ServiceException e){
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/error");
			rd.forward(request, response);
		} catch (InvaliedParamException e) {
			e.printStackTrace();
		}
	}
    //管理员查看员工基本信息
	public void lookEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			/*获取请求报文的信息*/
	   	    String username = request.getParameter("username");
	   	   
	   	    EmployeeService emps = EmployeeServiceImpl.getInstance();
	    	EmployeeVo emp = emps.search(username);
	    	
	    	request.setAttribute("employee", emp);
	    	request.getRequestDispatcher("/info/employee/lookemployee/index.jsp").forward(request, response);
		} catch (ServiceException e){
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/error");
			rd.forward(request, response);
		}
	}
	//员工修改个人信息
	public void updateEmployeeInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
	    	/*获取请求报文的信息*/
	   	    
	   	    HttpSession session = request.getSession();
	   	    EmployeeVo emp  = (EmployeeVo) session.getAttribute("employee");
	   	    
	   	    String englishName = request.getParameter("englishName");
	   	    Date birth = ParameterUtil.getDateFloor(request, "birth");
	    	String nativePlace = request.getParameter("nativePlace");
	    	String phone = request.getParameter("phone");
	    	String qq = request.getParameter("qq");
	    	String fax = request.getParameter("fax");
	    	String homeTel = request.getParameter("homeTel");
	    	String address = request.getParameter("address");
	    	String school = request.getParameter("school");
	    	Date graduateDate = ParameterUtil.getDateFloor(request, "graduateDate");
	    	String question = request.getParameter("question");
	    	String answer = request.getParameter("answer");
	    	String remark = request.getParameter("remark");
	    	
	   	    	    
	   	    emp.setEnglishName(englishName);
	   	    if(birth==null){
	   	    	emp.setBirth(null);
	   	    }
	   	    else{
	   	     emp.setBirth(birth);
	   	    }
	   	    //emp.setBirth(birth);
	   	    emp.setNativePlace(nativePlace);
	   	    emp.setPhone(phone);  
	   	    emp.setQq(qq);
	   	    emp.setFax(fax);
	   	    emp.setHomeTel(homeTel);
	   	    emp.setAddress(address);
	   	    emp.setSchool(school);
	   	    if(graduateDate.equals(null)){
	   	    	emp.setGraduateDate(null);
	   	    }
	   	    else{
	   	    	emp.setGraduateDate(graduateDate); 
	   	    }
	   	    //emp.setGraduateDate(graduateDate);   	    
	   	    emp.setQuestion(question);
	   	    emp.setAnswer(answer);
	   	    emp.setRemark(remark);
	   	    
	   	
	   	 
	   	    /*调用service层方法处理请求*/
	   	    EmployeeService emps = EmployeeServiceImpl.getInstance();
	   	    boolean isSuccess = emps.updateEmployeeInfo(emp);
	   	    
	   	    /*生成响应*/
	   	    if(isSuccess){
	   	    	   request.setAttribute("username", emp.getUsername());
	               request.getRequestDispatcher("/info/user/index.jsp").forward(request, response);
	   	    }
	   	    else{
	   	    	request.getRequestDispatcher("/info/user/index.jsp").forward(request, response);
	   	    }
			
		} catch (ServiceException e){
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/error");
			rd.forward(request, response);
		} catch (InvaliedParamException e) {
			e.printStackTrace();
		}
	}
	
	//员工修改个人密码
	public void updatePassword(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{

			try {
				HttpSession session = request.getSession();
		   	    EmployeeVo emp  = (EmployeeVo) session.getAttribute("employee");
		   	    
		   	    String username = emp.getUsername();
			   	//System.out.println(username);
		    	/*获取请求报文的信息*/
		   	    String password1 = request.getParameter("password1");
		   	    String password2 = request.getParameter("password2");
		   	    //System.out.println(password1);	  
		   	    //System.out.println(password2);
		   	    /*调用service层方法处理请求*/
		   	    EmployeeService emps = EmployeeServiceImpl.getInstance();
		   	    boolean isSuccess = emps.updatePassword(username,password1,password2);
		   	    	   	    
		   	    /*生成响应*/
		   	    if(isSuccess){
		   	    	   request.setAttribute("username",username);
		               request.getRequestDispatcher("/info/user/index.jsp").forward(request, response);
		   	    }
		   	    else{
		   	    	request.getRequestDispatcher("/info/user/modifypassword/index.jsp").forward(request, response);
		   	    }
				
			} catch (ServiceException e){
				e.printStackTrace();
				request.setAttribute("error", e.getMessage());
				RequestDispatcher rd = request.getRequestDispatcher("/error");
				rd.forward(request, response);
			}
	}
	private void changePassword(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		request.setAttribute("username", username);
		request.getRequestDispatcher("/info/employee/modifyinfo/defaultpassword/index.jsp").forward(request, response);
	}
	//管理员再次设置默认密码
	public void defaultPassword(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		try {  	    
	   	    String username = request.getParameter("username");
	    	/*获取请求报文的信息*/
	   	    String password1 = request.getParameter("password1");
	   	    String password2 = request.getParameter("password2");
	   	    	   	    	   	    
	   	    /*调用service层方法处理请求*/
	   	    EmployeeService emps = EmployeeServiceImpl.getInstance();
	   	    boolean isSuccess = emps.updatePassword(username,password1,password2);
	   	    EmployeeVo emp = emps.search(username);
	   	  
	   	    
	   	    /*生成响应*/
	   	    if(isSuccess){
	   	    	   request.setAttribute("employee", emp);
	               request.getRequestDispatcher("/info/employee/modifyinfo/index.jsp").forward(request, response);
	   	    }
	   	    else{
	   	    	request.getRequestDispatcher("/info/employee/modifyinfo/defaultpassword/index.jsp").forward(request, response);
	   	    }
			
		} catch (ServiceException e){
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/error");
			rd.forward(request, response);
		}	
	}
	//员工查询
    public void searchEmp(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
    	PageQuery query;
		try {
		
			query = getQuery(request);
	
			Table table = EmployeeServiceImpl.getInstance().searchEmp(query);
			ServletUtil.output(response, table);

		} catch (InvaliedParamException e) {
			e.printStackTrace();
			ServletUtil.stateJson(response, State.BAD_REQUEST);
		} catch (ServiceException e) {
			e.printStackTrace();
			ServletUtil.stateJson(response, State.INTERNAL_SERVER_ERROR);
		}
    }
    //封装请求参数
    private PageQuery getQuery(HttpServletRequest request) throws ServletException, IOException, InvaliedParamException {

    	String empName = ParameterUtil.get(request, "empName", null, ParameterUtil.EMPTY);
    	int deptId = ParameterUtil.getInt(request, "deptId",0);
    	String username = ParameterUtil.get(request, "username", null, ParameterUtil.EMPTY);
		int pageNum = ParameterUtil.getInt(request, "pageNum", 1);
		int pageSize = ParameterUtil.getInt(request, "pageSize", Constant.PAGE_SIZE);
		Date joinDate1 = ParameterUtil.getDateFloor(request, "joinDate1",null);
		Date joinDate2 = ParameterUtil.getDateFloor(request, "joinDate2",null);

		if(joinDate1 != null && joinDate2 != null){
		    if (joinDate2.before(joinDate1)) {
			    throw new InvaliedParamException("开始日期不能晚于结束日期");
		    }
        }
		if(empName != null) empName = URLDecoder.decode(empName, "UTF-8");
		if(username != null) username = URLDecoder.decode(username, "UTF-8");
		PageQuery query = new PageQuery(pageNum, pageSize)
		    .setParam("empName", empName)
		    .setParam("deptId", deptId)
		    .setParam("username", username)
			.setParam("joinDate1", joinDate1)
			.setParam("joinDate2", joinDate2);
			
		return query;
	}
}
