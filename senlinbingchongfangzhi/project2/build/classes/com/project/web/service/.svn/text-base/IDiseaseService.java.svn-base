package com.project.web.service;

import com.project.web.bean.CutPageBean;
import com.project.web.bean.DiseaseBean;

/**
 * 病害业务接口
 * 
 * @author 袁福祥
 *
 */
public interface IDiseaseService {
	/**
	 * 分页显示条数
	 */
	static final int EACH_PAGE_NUM = 5;

	/**
	 * 添加新病害
	 * 
	 * @param bean 病害对象
	 *            
	 */
	public void addDisease(DiseaseBean bean);

	/**
	 * 查看病害信息
	 * 
	 * @param id 病害的id
	 *            
	 * @return 病害对象
	 */
	public DiseaseBean findById(int id);

	/**
	 * 显示所有病害信息，并且也可按需查询
	 * 
	 * @param num 查询的页数
	 *            
	 * @param diseaseName 病害的名称
	 *            
	 * @param sysmptom  发病症状
	 *           
	 * @return 分页对象
	 */
	public CutPageBean<DiseaseBean> showAllDisease(int num, String diseaseName, String sysmptom);

}
