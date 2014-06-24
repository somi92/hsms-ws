package com.github.somi92.hsms.webservice;

public class HSMS {
	
	private int id;
	private String desc;
	private String number;
	private String price;
	private String organisation;
	private String webPage;
	private int priority;
	private String remark;
	
	public HSMS() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getOrganisation() {
		return organisation;
	}

	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}

	public String getWebPage() {
		return webPage;
	}

	public void setWebPage(String webPage) {
		this.webPage = webPage;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Override
	public String toString() {
		return getId()+" "+getDesc()+" "+getNumber()+" "+getPrice()+" "+getOrganisation()+" "+getWebPage()+" "+getPriority()+" "+getRemark()+'\n';
	}

}
