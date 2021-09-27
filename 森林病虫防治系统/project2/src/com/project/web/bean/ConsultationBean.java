package com.project.web.bean;

import java.sql.Date;

/**
 * 专家会商实体类
 * @author GF
 *
 */
public class ConsultationBean {
	/**
	 * 会商id
	 */
	private int id;
	/**会商内容*/
	private String content;
	/**会商结果*/
	private String result;
	/**会商事件bean*/
	private EventBean eventBean;
	/**会商日期*/
	private Date date;
	
	public ConsultationBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ConsultationBean(String content, String result, EventBean eventBean, Date date) {
		super();
		this.content = content;
		this.result = result;
		this.eventBean = eventBean;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public EventBean getEventBean() {
		return eventBean;
	}

	public void setEventBean(EventBean eventBean) {
		this.eventBean = eventBean;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "ConsultationBean [id=" + id + ", content=" + content + ", result=" + result + ", eventBean=" + eventBean
				+ ", date=" + date + "]";
	}
	
}
