package com.risenb.bean;

import java.util.Date;
/**
 * 上传文件实体
 * @author Administrator
 */
public class FilesModel {
	
	private Boolean sesscess;//是否上传成功
	
	private String errorMsg;//上传错误信息
	
	private String url;//文件访问
	
	private String dir;//文件存放路径
	
	private String fileName;//文件原名字
	
	private Long size;//文件大小
	
	private Date createTime;//上传时间
	
	

	public Boolean getSesscess() {
		return sesscess;
	}

	public void setSesscess(Boolean sesscess) {
		this.sesscess = sesscess;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
