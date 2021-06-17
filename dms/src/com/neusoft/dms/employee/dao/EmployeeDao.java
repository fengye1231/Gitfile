package com.neusoft.dms.employee.dao;

import java.util.Date;
import java.util.List;

import com.neusoft.dms.dept.domain.DepartmentVo;
import com.neusoft.dms.domain.Load;
import com.neusoft.dms.domain.Page;
import com.neusoft.dms.employee.domain.Employee;
import com.neusoft.dms.employee.domain.EmployeeVo;
import com.neusoft.dms.role.domain.RoleVo;
import com.neusoft.dms.util.DaoException;
import com.neusoft.dms.util.ServiceException;


public interface EmployeeDao {
	
	//根据用户名查询用户信息
	public Employee getEmployeeByName(String name) throws DaoException ;
		
	//根据员工编号查询用户信息
	public EmployeeVo getEmployeeByEmpId(int empId) throws DaoException;
	
	//添加新员工
	public void addEmployee(Employee emp) throws DaoException;
			
	//删除员工
	public void deleteEmployee(int empId) throws DaoException;
	//public void deleteprj_emp(int empId)throws DaoException;
	//public void setSuperiorNull(int empId)throws DaoException;
	//public void delete_daily(int empId)throws DaoException;
	//public void delete_warning(int empId)throws DaoException;
		
	//管理员修改员工信息
    public void updateEmployee(Employee emp) throws DaoException;
    
    //员工修改自己基本信息
    public void updateEmployeeInfo(Employee emp) throws DaoException;
    //根据员工编号查询员工名字
//  public String changeIdToEmpName(Long empId) throws DaoException;	
    
    //重置密码
    public void updatePassword(String username,String password) throws DaoException;
		
    public Page getEmployees(String empName, int deptId, String username,Date joinDate1, Date joinDate2,
			int pageNum, int pageSize) throws DaoException;
	public List<DepartmentVo> getRunningDept() throws DaoException;
	public List<RoleVo> getRunningRole() throws DaoException;
	public List<EmployeeVo> getRunningLeader() throws DaoException;
	
	//领导查询下属员工
    public List<EmployeeVo> leaderSearchEmp(int empId) throws DaoException;
}
