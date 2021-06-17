package com.neusoft.dms.proj.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.neusoft.dms.domain.Load;
import com.neusoft.dms.domain.Page;
import com.neusoft.dms.proj.domain.DepartmentLoad;
import com.neusoft.dms.proj.domain.DepartmentPrpLoad;
import com.neusoft.dms.proj.domain.PrpLoad;
import com.neusoft.dms.proj.domain.ProjectVo;
import com.neusoft.dms.util.DaoException;

public class ProjectLoadDaoImplForTest implements ProjectLoadDao {

	@SuppressWarnings("unused")
	private Connection con;
	
	public ProjectLoadDaoImplForTest(Connection con) {
		this.con = con;
	}

	@Override
	public Load getDepartmentLoadSummary(int projectId, Date startDate, Date endDate) throws DaoException {
		
		// TODO 数据库
		
		Load load = new Load(60, 48);
		return load;
	}

	@Override
	public Page getDepartmentLoads(int projectId, Date startDate, Date endDate,
			int pageNum, int pageSize) throws DaoException {
		
		// TODO 数据库
		
		List<DepartmentLoad> loads = new ArrayList<DepartmentLoad>();
		
		int totalNum = 4;
		int totalPage = totalNum / pageSize + totalNum % pageSize;
		int pageNumber = Math.min(totalPage, pageNum);
		int offset = (pageNumber - 1) * pageSize;
		int end = Math.min(totalNum, offset + pageSize);
		
		for(int i = offset; i < end; i++) {
			loads.add(new DepartmentLoad(i, "部门" + i, 15, 12));
		}
		
		Page page = new Page(loads, totalNum, pageNumber, totalPage, pageSize);
		
		return page;
	}

	@Override
	public Load getPrpLoadSummary(int projectId, Date startDate,
			Date endDate) throws DaoException {
		
		// TODO 数据库
		
		Load load = new Load(60, 48);
		return load;
	}

	@Override
	public Page getPrpLoads(int projectId, Date startDate, Date endDate,
			int pageNum, int pageSize) throws DaoException {
		
		// TODO 数据库
		
		List<PrpLoad> loads = new ArrayList<PrpLoad>();
		
		int totalNum = 3;
		int totalPage = totalNum / pageSize + totalNum % pageSize;
		int pageNumber = Math.min(totalPage, pageNum);
		int offset = (pageNumber - 1) * pageSize;
		int end = Math.min(totalNum, offset + pageSize);
		
		for(int i = offset; i < end; i++) {
			loads.add(new PrpLoad(i, "阶段" + i, 20, 16));
		}
		
		Page page = new Page(loads, totalNum, pageNumber, totalPage, pageSize);
		
		return page;
	}

	@Override
	public Page getDepartmentPrpLoads(int projectId, Date startDate,
			Date endDate, int pageNum, int pageSize) throws DaoException {
		
		// TODO 数据库
		
		List<DepartmentPrpLoad> loads = new ArrayList<DepartmentPrpLoad>();
		
		int totalNum = 4;
		int totalPage = totalNum / pageSize + totalNum % pageSize;
		int pageNumber = Math.min(totalPage, pageNum);
		int offset = (pageNumber - 1) * pageSize;
		int end = Math.min(totalNum, offset + pageSize);
		
		for(int i = offset; i < end; i++) {
			List<PrpLoad> milestoneLoads = new ArrayList<PrpLoad>();
			milestoneLoads.add(new PrpLoad(1, "项目阶段1", 5, 4));
			milestoneLoads.add(new PrpLoad(2, "项目阶段2", 5, 4));
			milestoneLoads.add(new PrpLoad(3, "项目阶段3", 5, 4));
			loads.add(new DepartmentPrpLoad(i, "部门" + i, milestoneLoads));
		}
		
		Page page = new Page(loads, totalNum, pageNumber, totalPage, pageSize);
		
		return page;
	}

	@Override
	public List<PrpLoad> getPrpLoads(int projectId, Date startDate,
			Date endDate) throws DaoException {
		
		// TODO 数据库
		
		List<PrpLoad> prpLoads = new ArrayList<PrpLoad>();
		prpLoads.add(new PrpLoad(1, "项目阶段1", 20, 16));
		prpLoads.add(new PrpLoad(2, "项目阶段2", 20, 16));
		prpLoads.add(new PrpLoad(3, "项目阶段3", 20, 16));
		
		return prpLoads;
	}

	@Override
	public List<ProjectVo> getRunningProjects() throws DaoException {
		
		// TODO 数据库
		
		List<ProjectVo> projects = new ArrayList<ProjectVo>();
		projects.add(new ProjectVo(1, "项目1"));
		projects.add(new ProjectVo(2, "项目2"));
		
		return projects;
	}

}
