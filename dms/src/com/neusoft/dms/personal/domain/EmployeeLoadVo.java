package com.neusoft.dms.personal.domain;

public class EmployeeLoadVo extends Employee {
	  
    private int Empid;  
    private String Empname;
    private int dailyNum;	//有效日报数
	private float load;	//有效工作时间
	private float extraLoad;
    
    
    
    /**
     * 
     * @param id
     * @param name
     */
	public EmployeeLoadVo(int Empid, String Empname,int dailyNum, float load, float extraLoad) {
		this.Empid = Empid;
		this.Empname = Empname;
		this.setDailyNum(dailyNum);
		this.setLoad(load);
		this.setExtraLoad(extraLoad);
	}
	
	public EmployeeLoadVo() {}
	
	public int getEmpId() {
		return Empid;
	}
	public void setEmpId(int Empid) {
		this.Empid = Empid;
	}
	public String getEmpName() {
		return Empname;
	}
	public void setEmpName(String Empname) {
		this.Empname = Empname;
	}

	public void setDailyNum(int dailyNum) {
		this.dailyNum = dailyNum;
	}

	public int getDailyNum() {
		return dailyNum;
	}

	public void setLoad(float load) {
		this.load = load;
	}

	public float getLoad() {
		return load;
	}

	public void setExtraLoad(float extraLoad) {
		this.extraLoad = extraLoad;
	}

	public float getExtraLoad() {
		return extraLoad;
	} 

    
     
}
