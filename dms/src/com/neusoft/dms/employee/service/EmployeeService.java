package com.neusoft.dms.employee.service;

import java.util.List;

import com.neusoft.dms.dept.domain.DepartmentVo;
import com.neusoft.dms.domain.PageQuery;
import com.neusoft.dms.domain.Table;
import com.neusoft.dms.employee.domain.Employee;
import com.neusoft.dms.employee.domain.EmployeeVo;
import com.neusoft.dms.role.domain.RoleVo;
import com.neusoft.dms.util.ServiceException;

public interface EmployeeService {
	//用户登录
	public boolean login(String username,String password) throws ServiceException;
	//忘记密码
	public boolean forgetPassword(String username,String question,String answer) throws ServiceException;
	//重置密码
	public boolean resetPassword(String username,String password1,String password2) throws ServiceException;
	//添加新员工
	public boolean addEmployee(Employee emp) throws ServiceException;
	//删除员工
	public void deleteEmployee(int empId) throws ServiceException;
	//管理员修改员工信息
	public void updateEmployee(Employee emp) throws ServiceException;
	//用户修改个人信息
	public boolean updateEmployeeInfo(Employee emp) throws ServiceException;
	//获取用户信息
	public EmployeeVo search(String username) throws ServiceException;
	//员工修改个人密码
	public boolean updatePassword(String username,String password1,String password2) throws ServiceException;
	//管理员再次设置默认密码
	public boolean defaultPassword(String username,String password1,String password2) throws ServiceException;
	public Table searchEmp(PageQuery query) throws ServiceException;
	
	public List<DepartmentVo> getRunningDept() throws ServiceException ;
	public List<RoleVo> getRunningRole() throws ServiceException;
	public List<EmployeeVo> getRunningLeader() throws ServiceException;
	
	//领导查询下属员工
	 public List<EmployeeVo> leaderSearchEmp(int empId) throws ServiceException;
}
