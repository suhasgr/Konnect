package com.konnectcore.login;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;


import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;


import com.konnectcore.bean.userInformation;
import com.konnectcore.misc.FilesUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;

public class FileUploadAction extends ActionSupport implements ServletContextAware {
	private Map session;
	private File image;
	private String imageContentType;
	private String imageFileName;
	private String imagePath;
	private ServletContext servletContext;
	
	public File getImage() {
		return image;
	}


	public Map getSession() {
		return session;
	}


	public void setSession(Map session) {
		this.session = session;
	}


	public ServletContext getServletContext() {
		return servletContext;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}


	public void setImage(File image) {
		this.image = image;
	}


	

	public String getImageContentType() {
		return imageContentType;
	}


	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}


	public String getImageFileName() {
		return imageFileName;
	}


	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}


	public String getImagePath() {
		return imagePath;
	}


	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	
	@Override
	public String execute()
	{
		System.out.println("File Name is:"+getImageFileName());
        System.out.println("File ContentType is:"+getImageContentType());
        System.out.println("Files Directory is:"+getImagePath());
        try {
            String targetPath = FilesUtil.saveFile(getImage(), getImageFileName(), servletContext.getRealPath("") + File.separator + getImagePath());
            byte[] fileInBytes = FilesUtil.convertToByteArray(targetPath);
            
            session = ActionContext.getContext().getSession();
            session.put("imageInBytes", fileInBytes);
            return "fileInBytes";
        }
        catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
        
	}
	
	

	

}