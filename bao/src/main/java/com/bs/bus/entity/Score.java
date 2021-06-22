package com.bs.bus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lzb
 * @since 2020-04-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_score")
public class Score implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String testid;

    private String testname;

    private Integer deptid;

    private Integer userid;

    private String username;

    private Integer chinesescore;

    private Integer mathscore;

    private Integer englishscore;

    private Integer totlescore;

    private String teacherwords;

    private String deptname;
    
    public String getTestname() {
		return testname;
	}

	public void setTestname(String testname) {
		this.testname = testname;
	}
	
    public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	
    public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
    public Integer getChinesescore() {
		return chinesescore;
	}

	public void setChinesescore(Integer chinesescore) {
		this.chinesescore = chinesescore;
	}
	
	
    public Integer getMathscore() {
		return mathscore;
	}

	public void setMathscore(Integer mathscore) {
		this.mathscore = mathscore;
	}

    public Integer getEnglishscore() {
		return englishscore;
	}

	public void setEnglishscore(Integer englishscore) {
		this.englishscore = englishscore;
	}

	
    public String getTeacherwords() {
		return teacherwords;
	}

	public void setTeacherwords(String teacherwords) {
		this.teacherwords = teacherwords;
	}

	
	
    public Integer getTotlescore() {
		return totlescore;
	}

	public void setTotlescore(Integer totlescore) {
		this.totlescore = totlescore;
	}
	

    public String getTestid() {
		return testid;
	}

	public void setTestid(String testid) {
		this.testid = testid;
	}
	
	
    public Integer getDeptid() {
		return deptid;
	}

	public void setDeptid(Integer deptid) {
		this.deptid = deptid;
	}

	
	
}
