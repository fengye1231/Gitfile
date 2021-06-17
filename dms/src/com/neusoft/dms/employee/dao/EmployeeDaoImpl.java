package com.neusoft.dms.employee.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




import com.neusoft.dms.dept.dao.DeptDaoImpl;
import com.neusoft.dms.dept.domain.DepartmentVo;
import com.neusoft.dms.domain.Load;
import com.neusoft.dms.domain.Page;
import com.neusoft.dms.employee.domain.Employee;
import com.neusoft.dms.employee.domain.EmployeeVo;

import com.neusoft.dms.prj.domain.PrjVo;
import com.neusoft.dms.proj.domain.DepartmentLoad;

import com.neusoft.dms.role.dao.RoleDaoImpl;
import com.neusoft.dms.role.domain.RoleVo;
import com.neusoft.dms.util.DBUtil;
import com.neusoft.dms.util.DaoException;



public class EmployeeDaoImpl implements EmployeeDao {
    private Connection con;
	
	public EmployeeDaoImpl(Connection con){
		this.con = con;
	}
	
	//根据用户名查询用户信息
	public EmployeeVo getEmployeeByName(String username) throws DaoException {
		EmployeeVo emp = null;
		DeptDaoImpl dept = new DeptDaoImpl();
		EmployeeDaoImpl employee = new EmployeeDaoImpl(con);
		RoleDaoImpl role = new RoleDaoImpl(con);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	     	     String sql = "select * from employee where username=?";
			try {
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, username);
				rs = pstmt.executeQuery();
				if(rs.next()){
					emp = new EmployeeVo();
					emp.setEmpId(rs.getInt("EMPID"));
					emp.setUsername(rs.getString("USERNAME"));
					emp.setPassword(rs.getString("PASSWORD"));
					emp.setRoleId(rs.getInt("ROLEID"));
					emp.setEmpName(rs.getString("EMPNAME"));
					emp.setDeptId(rs.getInt("DEPTID"));
					emp.setSex(rs.getString("SEX"));
					emp.setJoinDate(rs.getDate("JOINDATE"));
					emp.setEnglishName(rs.getString("ENGLISHNAME"));
					emp.setBirth(rs.getDate("BIRTH"));
					emp.setNativePlace(rs.getString("NATIVEPLACE"));
					emp.setPhone(rs.getString("PHONE"));
					emp.setEmail(rs.getString("EMAIL"));
					emp.setQq(rs.getString("QQ"));
					emp.setFax(rs.getString("FAX"));
					emp.setHomeTel(rs.getString("HOMETEL"));
					emp.setAddress(rs.getString("ADDRESS"));
					emp.setSchool(rs.getString("SCHOOL"));
					emp.setGraduateDate(rs.getDate("GRADUATEDATE"));
					emp.setRemark(rs.getString("REMARK"));
					emp.setSuperiorId(rs.getInt("SUPERIORID"));
					emp.setIsleader(rs.getInt("ISLEADER"));
					emp.setQuestion(rs.getString("Question"));
					emp.setAnswer(rs.getString("ANSWER"));
					
					emp.setDeptName(dept.getDeptById(emp.getDeptId()).getDeptName());
					
					int sId = emp.getSuperiorId();	
					if(sId!=0){
						String superiorName = (employee.getEmployeeByEmpId(sId)).getUsername();
						emp.setSuperiorName(superiorName);
					}
					
					emp.setRoleName(role.getRoleById(emp.getRoleId()).getRoleName());
					
					
				}
			} catch (SQLException e) {
				throw new DaoException("根据用户名查询用户信息错误",e);
			}  finally {
				DBUtil.close(pstmt,rs);
			}
		return emp;
	}
	
	//根据编号查询用户信息
	public EmployeeVo getEmployeeByEmpId(int empId) throws DaoException {
		EmployeeVo emp = null;
		DeptDaoImpl dept = new DeptDaoImpl();
		EmployeeDaoImpl employee = new EmployeeDaoImpl(con);
		RoleDaoImpl role = new RoleDaoImpl(con);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
			String sql = "select * from employee where empId=?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1,empId);
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					emp = new EmployeeVo();
					emp.setEmpId(rs.getInt("EMPID"));
					emp.setUsername(rs.getString("USERNAME"));
					emp.setPassword(rs.getString("PASSWORD"));
					emp.setRoleId(rs.getInt("ROLEID"));
					emp.setEmpName(rs.getString("EMPNAME"));
					emp.setDeptId(rs.getInt("DEPTID"));
					emp.setSex(rs.getString("SEX"));
					emp.setJoinDate(rs.getDate("JOINDATE"));
					emp.setEnglishName(rs.getString("ENGLISHNAME"));
					emp.setBirth(rs.getDate("BIRTH"));
					emp.setNativePlace(rs.getString("NATIVEPLACE"));
					emp.setPhone(rs.getString("PHONE"));
					emp.setEmail(rs.getString("EMAIL"));
					emp.setQq(rs.getString("QQ"));
					emp.setFax(rs.getString("FAX"));
					emp.setHomeTel(rs.getString("HOMETEL"));
					emp.setAddress(rs.getString("ADDRESS"));
					emp.setSchool(rs.getString("SCHOOL"));
					emp.setGraduateDate(rs.getDate("GRADUATEDATE"));
					emp.setRemark(rs.getString("REMARK"));
					emp.setSuperiorId(rs.getInt("SUPERIORID"));
					emp.setIsleader(rs.getInt("ISLEADER"));
					emp.setQuestion(rs.getString("Question"));
					emp.setAnswer(rs.getString("ANSWER"));	
					
					//emp.setDeptName(dept.getDeptById(emp.getDeptId()).getDeptName());
					//int sId = emp.getSuperiorId();	
					//if(sId!=0){
						//String superiorName = (employee.getEmployeeByEmpId(sId)).getUsername();
						//emp.setSuperiorName(superiorName);
					//}
					
					emp.setRoleName(role.getRoleById(emp.getRoleId()).getRoleName());
					
				}
			} catch (SQLException e) {
				throw new DaoException("根据员工编号查询用户信息错误",e);
			}  finally {
				DBUtil.close(pstmt,rs);
			}
		return emp;
	}
	
	//添加新员工
	public void addEmployee(Employee emp) throws DaoException{
		PreparedStatement pstmt = null;
		
		try {
			String sql = "insert into employee(username,empName,password,email,isleader,roleId,superiorId,deptId,remark,joinDate,sex) values(?,?,?,?,?,?,?,?,?,?,?) ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,emp.getUsername());
			pstmt.setString(2, emp.getEmpName());
			pstmt.setString(3, emp.getPassword());
			pstmt.setString(4, emp.getEmail());
			pstmt.setInt(5, emp.getIsleader());
			pstmt.setInt(6, emp.getRoleId());
			pstmt.setInt(7, emp.getSuperiorId());
			pstmt.setInt(8, emp.getDeptId());
			pstmt.setString(9, emp.getRemark());
			pstmt.setDate(10, new java.sql.Date(emp.getJoinDate().getTime()));
			pstmt.setString(11, emp.getSex());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, null);
		}
	}
	
	//删除员工
	public void deleteEmployee(int empId) throws DaoException{
		PreparedStatement pstmt = null;
		
		try {
			//System.out.println(empId);
			String sql = "delete from employee where empId=?";
			pstmt = con.prepareStatement(sql);			
			pstmt.setInt(1,empId);		
			pstmt.executeUpdate();		
		} catch (SQLException e) {
			if("23000".equals(e.getSQLState())){
			throw new DaoException(e);
			}
		} finally {
			DBUtil.close(pstmt, null);
		}
	}

	//管理员修改员工信息
    public void updateEmployee(Employee emp) throws DaoException{
    	PreparedStatement pstmt = null;
    	
		try {
			String sql = "update employee set empName=?,email=?,isleader=?,roleId=?,superiorId=?,deptId=?,remark=?, sex=? where username=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,emp.getEmpName());	
			pstmt.setString(2, emp.getEmail());
			pstmt.setInt(3, emp.getIsleader());
			pstmt.setInt(4, emp.getRoleId());
			pstmt.setInt(5, emp.getSuperiorId());
			pstmt.setInt(6, emp.getDeptId());
			pstmt.setString(7, emp.getRemark());
			pstmt.setString(8, emp.getSex());
			pstmt.setString(9, emp.getUsername());
			
			pstmt.executeUpdate();		
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, null);
		}
	}
    
    //员工修改自己基本信息
    public void updateEmployeeInfo(Employee emp) throws DaoException{
    	PreparedStatement pstmt = null;
    	
		try {
			String sql = "update employee set englishName=?,birth=?," +
					"nativePlace=?,phone=?,qq=?,fax=?,homeTel=?,address=?,school=?,graduateDate=?," +
					"remark=?,question=?,answer=? where username=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, emp.getEnglishName());
			if(emp.getBirth()!=null){pstmt.setDate(2, new java.sql.Date(emp.getBirth().getTime()));}
			pstmt.setString(3, emp.getNativePlace());
			pstmt.setString(4, emp.getPhone());
			pstmt.setString(5, emp.getQq());
			pstmt.setString(6, emp.getFax());
			pstmt.setString(7, emp.getHomeTel());
			pstmt.setString(8, emp.getAddress());
			pstmt.setString(9, emp.getSchool());
			if(emp.getGraduateDate()!=null){pstmt.setDate(10, new java.sql.Date(emp.getGraduateDate().getTime()));}
			pstmt.setString(11, emp.getRemark());
			pstmt.setString(12, emp.getQuestion());
			pstmt.setString(13, emp.getAnswer());
			pstmt.setString(14, emp.getUsername());
			
			pstmt.executeUpdate();		
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, null);
		}
    }
    
	//修改密码
    public void updatePassword(String username,String password) throws DaoException{
    	PreparedStatement pstmt = null;
    	
    	try {
			String sql = "update employee set password=? where username=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, password);
			pstmt.setString(2,username);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		}finally {
			DBUtil.close(pstmt, null);
		}
    }
    
	@Override
	public Page getEmployees(String empName, int deptId, String username,Date joinDate1, Date joinDate2,
			int pageNum, int pageSize) throws DaoException {

		List<EmployeeVo> emps = new ArrayList<EmployeeVo>();
		EmployeeVo emp = null;
		PreparedStatement pstmt = null, pagePstmt = null;
		ResultSet rs = null;
		StringBuffer sqlBuffer = new StringBuffer();
		try {
			int index = 1;
			String filter = " WHERE TRUE "
					+ (empName != null? " AND empName LIKE ? ": "")
					+ (deptId !=0 ? "AND employee.deptId = ? ":"")
					+ (username != null? " AND username LIKE ? ": "")
					+ (joinDate1 != null && joinDate2 != null? " AND (joinDate BETWEEN ? AND ?) ": "")
					+ (joinDate1 != null && joinDate2 == null? " AND joinDate >= ? ": "")
					+ (joinDate1 == null && joinDate2 != null? " AND joinDate <= ? ": "");

			String pageSql = "SELECT COUNT(*) FROM EMPLOYEE join DEPT on EMPLOYEE.deptId = DEPT.deptId" + filter;
			pagePstmt = con.prepareStatement(pageSql);
            
			if (empName != null) pagePstmt.setString(index++, "%" + empName+ "%");
			if (deptId != 0) pagePstmt.setInt(index++, deptId); 
			if (username != null) pagePstmt.setString(index++, "%" + username+ "%");
			if (joinDate1 != null) pagePstmt.setDate(index++, new java.sql.Date(joinDate1.getTime()));
			if (joinDate2 != null) pagePstmt.setDate(index++, new java.sql.Date(joinDate2.getTime()));
			//System.out.println(filter);
			Page page = getPageInfo(pagePstmt, pageNum, pageSize);
			//System.out.println(page.getTotalNum());	
			if (page.getTotalNum() > 0) {
				//System.out.println("总数大于0");
				String sql = "select * from EMPLOYEE join DEPT on EMPLOYEE.deptId = DEPT.deptId" + filter + " LIMIT ?, ? ";
				pstmt = con.prepareStatement(sql);

				index = 1;
				if (empName != null) pstmt.setString(index++, "%" + empName+ "%");
				if (deptId != 0) pstmt.setInt(index++, deptId); 
				if (username != null) pstmt.setString(index++, "%" + username+ "%");
				if (joinDate1 != null) pstmt.setDate(index++, new java.sql.Date(joinDate1.getTime()));
				if (joinDate2 != null) pstmt.setDate(index++, new java.sql.Date(joinDate2.getTime()));
		
				pstmt.setInt(index++, (page.getPageNum() - 1) * page.getPageSize());
				pstmt.setInt(index++, page.getPageSize());
				rs = pstmt.executeQuery();
				while (rs.next()) {
					emp = new EmployeeVo();
					emp.setEmpName(rs.getString("EMPNAME"));
					emp.setDeptName(rs.getString("DEPTNAME"));
					emp.setUsername(rs.getString(3));
					emp.setSex(rs.getString("SEX"));
					if(rs.getDate(6)!=null){
						emp.setJoinDate(new Date(rs.getDate(6).getTime()));	
					}
					emps.add(emp);
				}
			}
			page.setList(emps);
			return page;
			
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, rs);
		}
	}
    
    
    @Override
	public List<DepartmentVo> getRunningDept() throws DaoException {
		List<DepartmentVo> depts = new ArrayList<DepartmentVo>();		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT deptId, deptName "
					   + "FROM dept ";
					  
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				depts.add(new DepartmentVo(rs.getInt("deptId"), rs.getString("deptName")));
			}
			return depts;
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, rs);
		}
	}
    @Override
	public List<RoleVo> getRunningRole() throws DaoException {
    	List<RoleVo> roles = new ArrayList<RoleVo>();		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT roleId, roleName "
					   + "FROM role ";
					  
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				roles.add(new RoleVo(rs.getInt("roleId"), rs.getString("roleName")));
			}
			return roles;
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, rs);
		}
	}
    @Override
	public List<EmployeeVo> getRunningLeader() throws DaoException {
    	List<EmployeeVo> leaders = new ArrayList<EmployeeVo>();		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT empId, username "
					   + "FROM employee " 
					   + "WHERE isleader='1'";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				leaders.add(new EmployeeVo(rs.getInt("empId"), rs.getString("username")));
			}
			return leaders;
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, rs);
		}
	}
    
    /**
	 * 根据数据库里的总条数获取分页信息
	 * @param pstmt
	 * @param pageNum
	 * @param pageSize
	 * @return Page
	 * @throws DaoException
	 */
    private Page getPageInfo(PreparedStatement pstmt, int pageNum, int pageSize) throws DaoException {
		ResultSet rs = null;
		try {
			rs = pstmt.executeQuery();
			if (rs.next()) {
				pageSize = Math.max(1, pageSize);
				int totalNum = rs.getInt(1);
				int totalPage = (int) Math.ceil((double)totalNum / pageSize);
				pageNum = Math.max(Math.min(totalPage, pageNum), totalNum == 0? 0: 1);
				
				return new Page(null, totalNum, pageNum, totalPage, pageSize);
			}
			throw new DaoException("分页信息获取失败");
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, rs);
		}
	}
    //领导查询下属员工
    public List<EmployeeVo> leaderSearchEmp(int empId) throws DaoException{
    	List<EmployeeVo> emps = new ArrayList<EmployeeVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from employee where superiorId=?";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1,empId);		
            rs = pstmt.executeQuery();
	        
            while (rs.next()) {
				emps.add(new EmployeeVo(rs.getInt("empId"), rs.getString("empname")));
			}
			return emps;
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, null);
		}
	}
}
