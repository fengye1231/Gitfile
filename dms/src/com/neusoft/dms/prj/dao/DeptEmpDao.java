package com.neusoft.dms.prj.dao;

import com.neusoft.dms.domain.Page;
import com.neusoft.dms.util.DaoException;

public interface DeptEmpDao {

	//根据部门id查找该部门下没有工作的人员
	public Page getNullEmpByDept(int deptID, int pageNum, int pageSize) throws DaoException;

}
