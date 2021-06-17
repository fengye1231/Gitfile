package com.neusoft.dms.daily.entry.domain;

public class DailyVo extends Daily{
	private String submitD;
	private String reviewD;
	public String getSubmitD() {
		return submitD;
	}
	public void setSubmitD(String submitD) {
		this.submitD = submitD;
	}
	public String getReviewD() {
		return reviewD;
	}
	public void setReviewD(String reviewD) {
		this.reviewD = reviewD;
	}
	private String empName;
	private String prjName;
	private String prpName;
	private String reviewEmpName;
	public String getEmpName() {
		return empName;
	}
	public String getPrjName() {
		return prjName;
	}
	public String getPrpName() {
		return prpName;
	}
	public String getReviewEmpName() {
		return reviewEmpName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public void setPrjName(String prjName) {
		this.prjName = prjName;
	}
	public void setPrpName(String prpName) {
		this.prpName = prpName;
	}
	public void setReviewEmpName(String reviewEmpName) {
		this.reviewEmpName = reviewEmpName;
	}
//	public void setNameById(DailyVo daily) {
//		Connection con;
//		try {
//			con = DBUtil.getConnection();
//			DailyDao dailyDao = new DailyDaoImpl(con);
//			dailyDao.getNameById(daily);
//		} catch (DaoException e) {
//			e.printStackTrace();
//		}
//	}
}
