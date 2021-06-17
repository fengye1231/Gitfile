package com.neusoft.dms.employee.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

import com.neusoft.dms.dept.domain.DepartmentVo;
import com.neusoft.dms.domain.Page;
import com.neusoft.dms.domain.PageQuery;
import com.neusoft.dms.domain.Table;
import com.neusoft.dms.employee.dao.EmployeeDao;
import com.neusoft.dms.employee.dao.EmployeeDaoImpl;
import com.neusoft.dms.employee.domain.Employee;
import com.neusoft.dms.employee.domain.EmployeeVo;
import com.neusoft.dms.role.domain.RoleVo;
import com.neusoft.dms.util.DBUtil;
import com.neusoft.dms.util.DaoException;
import com.neusoft.dms.util.PasswordHash;
import com.neusoft.dms.util.ServiceException;

public class EmployeeServiceImpl implements EmployeeService{
	private static EmployeeService employeeService = new EmployeeServiceImpl();
	private EmployeeServiceImpl() { }
	
	public static EmployeeService getInstance() {
		return employeeService;
	}
	
	//登录
	public boolean login(String username,String password) throws ServiceException{
		boolean isSuccess = false;
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			//判断用户是否存在
			EmployeeDao empdao = new EmployeeDaoImpl(con);
			Employee emp = empdao.getEmployeeByName(username);
			//用户存在
			if(emp!=null){
				//判断密码是否正确
				if(PasswordHash.validatePassword(password, emp.getPassword())==true){
					isSuccess = true;
					//System.out.println("pas correct");
				}
			}
		} catch (DaoException e) {
			throw new ServiceException("用户登录错误",e);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}finally {
			try {
				DBUtil.close(con);
			} catch (DaoException e) {}
		}		
		return isSuccess;
	}

	//忘记密码
	public boolean forgetPassword(String username,String question,String answer) throws ServiceException{
		boolean isSuccess = false;
		Connection con=null;
		try {
			con = DBUtil.getConnection();
			//判断用户是否存在
			EmployeeDao empdao = new EmployeeDaoImpl(con);
			Employee emp = empdao.getEmployeeByName(username);
			if(emp!=null){
				if(question.equals(emp.getQuestion())&&answer.equals(emp.getAnswer())){
					isSuccess = true;				
				}
			}
		} catch (DaoException e) {
			throw new ServiceException("用户找回密码错误",e);
		}finally {
			try {
				DBUtil.close(con);
			} catch (DaoException e) {}
		}
		return isSuccess;
	}
	
	//重置密码
	public boolean resetPassword(String username,String password1,String password2) throws ServiceException {
		boolean isSuccess = false;
		Connection con=null;
		try {
			con = DBUtil.getConnection();
			EmployeeDao empdao = new EmployeeDaoImpl(con);
			Employee emp = empdao.getEmployeeByName(username);
			if(emp!=null)
			     if(password1.equals(password2)){
			    	String hash = PasswordHash.createHash(password1);
			    	empdao.updatePassword(username,hash);
					isSuccess = true;	
			     }
		} catch (DaoException e) {
			throw new ServiceException("重置密码错误",e);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}finally {
			try {
				DBUtil.close(con);
			} catch (DaoException e) {}
		}
		return isSuccess;
	}
	
	//添加新员工
	public boolean addEmployee(Employee employee) throws ServiceException{
		boolean isSuccess = false;
		Connection con=null;
		try {			
			con = DBUtil.getConnection();	
			//判断用户名是否存在
			EmployeeDao empdao = new EmployeeDaoImpl(con);
			Employee emp = empdao.getEmployeeByName(employee.getUsername());
			//用户不存在
			if(emp==null){		
				empdao.addEmployee(employee); 
				isSuccess = true;					
			}
		} catch (DaoException e) {
			throw new ServiceException("添加新员工错误",e);
		}finally {
			try {
				DBUtil.close(con);
			} catch (DaoException e) {}
		}
		return isSuccess;
	}
	//修改员工信息
	public void updateEmployee(Employee employee) throws ServiceException{
		Connection con = null;
			try {
				con = DBUtil.getConnection();
				EmployeeDao empdao = new EmployeeDaoImpl(con);			
				empdao.updateEmployee(employee); 	
			} catch (DaoException e) {
				e.printStackTrace();
				throw new ServiceException("修改员工信息错误",e);
			}finally {
				try {
					DBUtil.close(con);
				} catch (DaoException e) {}
			}			
	}	
    //删除员工
	public void deleteEmployee(int empId) throws ServiceException{
		Connection con = null;
			try {
				con = DBUtil.getConnection();
				EmployeeDao empdao = new EmployeeDaoImpl(con);			
				empdao.deleteEmployee(empId);		
			} catch (DaoException e) {
				e.printStackTrace();
				throw new ServiceException("删除员工错误",e);
			}finally {
				try {
					DBUtil.close(con);
				} catch (DaoException e) {}
			}
			
	}

	//员工修改个人信息
		public boolean updateEmployeeInfo(Employee emp) throws ServiceException{
			boolean isSuccess = false;
			Connection con=null;
				try {
					con = DBUtil.getConnection();
					EmployeeDao empdao = new EmployeeDaoImpl(con);		
					
					empdao.updateEmployeeInfo(emp); 
					isSuccess = true;													
				} catch (DaoException e) {
					throw new ServiceException("员工个人信息修改错误",e);
				}finally {
					try {
						DBUtil.close(con);
					} catch (DaoException e) {}
				}
				return isSuccess;
		}
	
	 public EmployeeVo search(String username) throws ServiceException{
		 Connection con=null;
			EmployeeVo emp = null;
			try {
				con = DBUtil.getConnection();
				EmployeeDao empdao = new EmployeeDaoImpl(con);				
				emp = (EmployeeVo)empdao.getEmployeeByName(username);					 
				} catch (DaoException e) {
					throw new ServiceException("查询信息错误",e);
				}finally {
					try {
						DBUtil.close(con);
					} catch (DaoException e) {}
				}
			return emp;
	 }
	 
		//员工修改个人密码
		public boolean updatePassword(String username,String password1,String password2) throws ServiceException{

			boolean isSuccess = false;
			Connection con=null;
			try {
				con = DBUtil.getConnection();
				EmployeeDao empdao = new EmployeeDaoImpl(con);
				Employee emp = empdao.getEmployeeByName(username);
				if(emp!=null)
				     if(password1.equals(password2)){
				    	String hash = PasswordHash.createHash(password1);
				    	empdao.updatePassword(username,hash);
						isSuccess = true;	
				     }
			} catch (DaoException e) {
				throw new ServiceException("员工修改个人密码错误",e);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				e.printStackTrace();
			}finally {
				try {
					DBUtil.close(con);
				} catch (DaoException e) {}
			}
			return isSuccess;
		}
		//管理员再次设置默认密码
		public boolean defaultPassword(String username,String password1,String password2) throws ServiceException{
			boolean isSuccess = false;
			Connection con=null;
			try {
				con = DBUtil.getConnection();
				EmployeeDao empdao = new EmployeeDaoImpl(con);
				Employee emp = empdao.getEmployeeByName(username);
				if(emp!=null)
				     if(password1.equals(password2)){
				    	String hash = PasswordHash.createHash(password1);
				    	empdao.updatePassword(username,hash);
						isSuccess = true;	
				     }
			} catch (DaoException e) {
				throw new ServiceException("管理员再次设置默认密码错误",e);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				e.printStackTrace();
			}finally {
				try {
					DBUtil.close(con);
				} catch (DaoException e) {}
			}
			return isSuccess;
		}
	 
	//多条件查询员工
		public Table searchEmp(PageQuery query) throws ServiceException {
		 Connection con = null;
			try {
				int pageNum = query.getPageNum();
				int pageSize = query.getPageSize();
				String empName = (String)query.getParam("empName");
				int deptId = (Integer) query.getParam("deptId");
				String username = (String)query.getParam("username");
				Date joinDate1 = (Date) query.getParam("joinDate1");
				Date joinDate2 = (Date) query.getParam("joinDate2");
				
				con = DBUtil.getConnection();
				EmployeeDao empDao = new EmployeeDaoImpl(con);
				
				Page empPage = empDao.getEmployees(empName, deptId, username, joinDate1, joinDate2, pageNum, pageSize);
				Table result = new Table(empPage);
				return result;
			} catch (DaoException e) {
				e.printStackTrace();
				throw new ServiceException(e);
			}finally {
				try { DBUtil.close(con); } 
				catch (DaoException e) { e.printStackTrace();}
				}
		}
	    //获得部门列表
		public List<DepartmentVo> getRunningDept() throws ServiceException {
			try {
				Connection con = DBUtil.getConnection();
				EmployeeDao empDao = new EmployeeDaoImpl(con);
				List<DepartmentVo> depts = empDao.getRunningDept();
				
				DBUtil.close(con);

				return depts;
			} catch (DaoException e) {
				e.printStackTrace();
				throw new ServiceException(e);
			}
		}
		//获得角色列表
		public List<RoleVo> getRunningRole() throws ServiceException {
			try {
				Connection con = DBUtil.getConnection();
				EmployeeDao empDao = new EmployeeDaoImpl(con);
				List<RoleVo> roles = empDao.getRunningRole();
				
				DBUtil.close(con);

				return roles;
			} catch (DaoException e) {
				e.printStackTrace();
				throw new ServiceException(e);
			}
		}
		//获得领导列表
		public List<EmployeeVo> getRunningLeader() throws ServiceException {
			try {
				Connection con = DBUtil.getConnection();
				EmployeeDao empDao = new EmployeeDaoImpl(con);
				List<EmployeeVo> leaders = empDao.getRunningLeader();
				
				DBUtil.close(con);

				return leaders;
			} catch (DaoException e) {
				e.printStackTrace();
				throw new ServiceException(e);
			}
		}
	 //领导查询下属员工
	 public List<EmployeeVo> leaderSearchEmp(int empId) throws ServiceException{
		 try {
				Connection con = DBUtil.getConnection();
				EmployeeDao empDao = new EmployeeDaoImpl(con);
				List<EmployeeVo> emps = empDao.leaderSearchEmp(empId);
				
				DBUtil.close(con);

				return emps;
			} catch (DaoException e) {
				e.printStackTrace();
				throw new ServiceException(e);
			}
	 }
	 
}
