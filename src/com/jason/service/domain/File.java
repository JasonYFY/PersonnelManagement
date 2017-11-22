package com.jason.service.domain;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;

import com.jason.domain.User;

public class File implements Serializable{
	private Integer id;
	private String title;
	private String fileName;
	private String remark;
	private Date createDate;
	private InputStream fileData;
	private User user;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public InputStream getFileData() {
		return fileData;
	}
	public void setFileData(InputStream fileData) {
		this.fileData = fileData;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "File [id=" + id + ", title=" + title + ", fileName=" + fileName + ", remark=" + remark + ", createDate="
				+ createDate + ", fileData=" + fileData + ", user=" + user + "]";
	}
	

}
