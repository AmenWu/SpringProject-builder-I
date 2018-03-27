package com.risenb.oss;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.risenb.bean.FilesModel;
import com.risenb.bean.ImgModel;

/**
 *  本地对象存储
 * @author Administrator
 *
 */
public class LocalOss {
	/**
	 * 上传单张图片
	 * @param dir 文件保存路径
	 * @param host 访问主机名
	 * @param request
	 * @param thumb 是否生成缩略图
	 * @param thumbWidth 缩略图宽度 单位像素
	 * @param thumbHeith 缩略图高度 单位像素
	 * @return
	 * @throws IOException 
	 */
	public  static ImgModel upLoadImg(String dir,String host,HttpServletRequest request,boolean thumb,int thumbWidth,int thumbHeith) throws IOException{
		ImgModel img = new ImgModel();
		if(null == dir || "".equals(dir) || null == host || "".equals(host) || null == request){
			img.setSesscess(false);
			img.setErrorMsg("参数错误");
			return img;
		}
		MultipartHttpServletRequest muRequest= null;
		try {
			muRequest = (MultipartHttpServletRequest) request;
		} catch (Exception e1) {
			img.setSesscess(false);
			img.setErrorMsg("没有任何上传文件");
			return img;
		}
		Map<String, MultipartFile> fileMap = muRequest.getFileMap();
		String uploadDir = dir+"upload/";	//上传至绝对路径
		String dirs=new SimpleDateFormat("yyyyMMdd").format(new Date()).toString()+"/";
		File file = new File(uploadDir);
		//如果不存在该路径就创建
		if (!file.exists()) {
			file.mkdir();
		}
		file= new File(uploadDir+dirs);
		if (!file.exists()) {
			file.mkdir();
		}
		String fileName = "";
		for (Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator(); it.hasNext();) {
			Map.Entry<String, MultipartFile> entry = it.next();
			MultipartFile mFile = entry.getValue();
			InputStream inputStream = mFile.getInputStream();
			BufferedImage bi=ImageIO.read(inputStream);
			int width = bi.getWidth();//宽度
			img.setWidth(width);
			int height = bi.getHeight();//高度
			img.setHeight(height);
			fileName = mFile.getOriginalFilename();//文件名字
			img.setFileName(fileName);
			fileName=rename(fileName);
			String newName = uploadDir+dirs + fileName;//文件保存绝对路径
			img.setDir(newName);
			File uploadFile = new File(newName);   
			try {
				FileCopyUtils.copy(mFile.getBytes(), uploadFile);
				img.setSesscess(true);
				img.setCreateTime(new Date());
				File newFile = new File(newName);
				img.setSize(newFile.length());
				img.setUrl(host+"/upload/"+dirs+fileName);
				if(thumb){//需要生成缩略图
					int w=thumbWidth;//缩略图宽度
					int h=thumbHeith;//缩略图高度
					if (width >= height) {
						h = (int) Math.round((height * w * 1.0 / width));
					} else {
						w = (int) Math.round((width * h * 1.0 / height));
					} 
					BufferedImage result = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
				    result.getGraphics().drawImage(bi.getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH), 0, 0, w, h, Color.white, null);
				    String path = uploadDir+dirs;
				    String fileName2 = img.getFileName();//文件原名
				    String rename = rename(fileName2);
				    File f = new File(path + rename); 
				    String fileType = getExtension(rename); 
				    ImageIO.write(result, fileType, f); 
				    img.setThumbHeight(h);
				    img.setThumbWidth(w);
				    img.setThumbUrl(host+"/upload/"+dirs+rename);
				}
			} catch (Exception e) {
				img.setSesscess(false);
				img.setErrorMsg("文件上传失败");
			}
			
		}
		return img;
	}
	/**
	 * 上传多个图片
	 * @param dir 文件保存路径
	 * @param host 访问主机名
	 * @param request
	 * @param thumb 是否生成缩略图
	 * @param thumbWidth 缩略图宽度 单位像素
	 * @param thumbHeith 缩略图高度 单位像素
	 * @return
	 * @throws IOException
	 */
	public  static List<ImgModel> upLoadImgMu(String dir,String host,HttpServletRequest request,boolean thumb,int thumbWidth,int thumbHeith) throws IOException{
		List<ImgModel> list = new ArrayList<ImgModel>();
		if(null == dir || "".equals(dir) || null == host || "".equals(host) || null == request){
			return list;
		}
		MultipartHttpServletRequest muRequest = null;
		try {
			muRequest = (MultipartHttpServletRequest) request;
		} catch (Exception e1) {
			return list;
		}
		Map<String, MultipartFile> fileMap = muRequest.getFileMap();
		String uploadDir = dir+"upload/";	//上传至绝对路径
		String dirs=new SimpleDateFormat("yyyyMMdd").format(new Date()).toString()+"/";
		File file = new File(uploadDir);
		//如果不存在该路径就创建
		if (!file.exists()) {
			file.mkdir();
		}
		file= new File(uploadDir+dirs);
		if (!file.exists()) {
			file.mkdir();
		}
		String fileName = "";
		for (Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator(); it.hasNext();) {
			ImgModel img = new ImgModel();
			Map.Entry<String, MultipartFile> entry = it.next();
			MultipartFile mFile = entry.getValue();
			InputStream inputStream = mFile.getInputStream();
			BufferedImage bi=ImageIO.read(inputStream) ;
			int width = bi.getWidth();//宽度
			img.setWidth(width);
			int height = bi.getHeight();//高度
			img.setHeight(height);
			fileName = mFile.getOriginalFilename();//文件名字
			img.setFileName(fileName);
			fileName=rename(fileName);
			String newName = uploadDir+dirs + fileName;//文件保存绝对路径
			img.setDir(newName);
			File uploadFile = new File(newName);   
			try {
				FileCopyUtils.copy(mFile.getBytes(), uploadFile);
				img.setSesscess(true);
				img.setCreateTime(new Date());
				img.setSize(new File(newName).length());
				img.setUrl(host+"/upload/"+dirs+fileName);
				if(thumb){//需要生成缩略图
					int w=thumbWidth;//缩略图宽度
					int h=thumbHeith;//缩略图高度
					if (width >= height) {
						h = (int) Math.round((height * w * 1.0 / width));
					} else {
						w = (int) Math.round((width * h * 1.0 / height));
					} 
					BufferedImage result = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
				    result.getGraphics().drawImage(bi.getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH), 0, 0, w, h, Color.white, null);
				    String path = uploadDir+dirs;
				    String fileName2 = img.getFileName();//文件原名
				    String rename = rename(fileName2);
				    File f = new File(path + rename); 
				    String fileType = getExtension(rename); 
				    ImageIO.write(result, fileType, f); 
				    img.setThumbHeight(h);
				    img.setThumbWidth(w);
				    img.setThumbUrl(host+"/upload/"+dirs+rename);
				}
			} catch (Exception e) {
				img.setSesscess(false);
				img.setErrorMsg("文件上传失败");
			}
			list.add(img);
		}
		return list;
	}
	/**
	 * 上传单个文件
	 * @param dir 文件保存路径
	 * @param host 访问主机名
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public  static FilesModel upLoadFile(String dir,String host,HttpServletRequest request) throws IOException{
		FilesModel upLoadFile = new FilesModel();
		if(null == dir || "".equals(dir) || null == host || "".equals(host) || null == request){
			upLoadFile.setSesscess(false);
			upLoadFile.setErrorMsg("参数错误");
			return upLoadFile;
		}
		MultipartHttpServletRequest muRequest = null;
		try {
			muRequest = (MultipartHttpServletRequest) request;
		} catch (Exception e1) {
			upLoadFile.setSesscess(false);
			upLoadFile.setErrorMsg("没有任何上传文件");
			return upLoadFile;
		}
		Map<String, MultipartFile> fileMap = muRequest.getFileMap();
		String uploadDir = dir+"upload/";	//上传至绝对路径
		String dirs=new SimpleDateFormat("yyyyMMdd").format(new Date()).toString()+"/";
		File file = new File(uploadDir);
		//如果不存在该路径就创建
		if (!file.exists()) {
			file.mkdir();
		}
		file= new File(uploadDir+dirs);
		if (!file.exists()) {
			file.mkdir();
		}
		String fileName = "";
		for (Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator(); it.hasNext();) {
			Map.Entry<String, MultipartFile> entry = it.next();
			MultipartFile mFile = entry.getValue();
			fileName = mFile.getOriginalFilename();//文件名字
			upLoadFile.setFileName(fileName);
			fileName=rename(fileName);
			String newName = uploadDir+dirs + fileName;//文件保存绝对路径
			upLoadFile.setDir(newName);
			File uploadFile = new File(newName);   
			try {
				FileCopyUtils.copy(mFile.getBytes(), uploadFile);
				upLoadFile.setSesscess(true);
				upLoadFile.setCreateTime(new Date());
				upLoadFile.setSize(new File(newName).length());
				upLoadFile.setUrl(host+"/upload/"+dirs+fileName);
			} catch (Exception e) {
				upLoadFile.setSesscess(false);
				upLoadFile.setErrorMsg("文件上传失败");
			}
			
		}
		return upLoadFile;
	}
	/**
	 * 上传多个文件
	 * @param dir 文件保存路径
	 * @param host 访问主机名
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public  static List<FilesModel> upLoadFileMu(String dir,String host,HttpServletRequest request) throws IOException{
		List<FilesModel> list = new ArrayList<FilesModel>();
		if(null == dir || "".equals(dir) || null == host || "".equals(host) || null == request){
			return list;
		}
		MultipartHttpServletRequest muRequest = null;
		try {
			muRequest = (MultipartHttpServletRequest) request;
		} catch (Exception e1) {
			return list;
		}
		Map<String, MultipartFile> fileMap = muRequest.getFileMap();
		String uploadDir = dir+"upload/";	//上传至绝对路径
		String dirs=new SimpleDateFormat("yyyyMMdd").format(new Date()).toString()+"/";
		File file = new File(uploadDir);
		//如果不存在该路径就创建
		if (!file.exists()) {
			file.mkdir();
		}
		file= new File(uploadDir+dirs);
		if (!file.exists()) {
			file.mkdir();
		}
		String fileName = "";
		for (Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator(); it.hasNext();) {
			FilesModel upLoadFile = new FilesModel();
			Map.Entry<String, MultipartFile> entry = it.next();
			MultipartFile mFile = entry.getValue();
			fileName = mFile.getOriginalFilename();//文件名字
			upLoadFile.setFileName(fileName);
			fileName=rename(fileName);
			String newName = uploadDir+dirs + fileName;//文件保存绝对路径
			upLoadFile.setDir(newName);
			File uploadFile = new File(newName);   
			try {
				FileCopyUtils.copy(mFile.getBytes(), uploadFile);
				upLoadFile.setSesscess(true);
				upLoadFile.setCreateTime(new Date());
				upLoadFile.setSize(new File(newName).length());
				upLoadFile.setUrl(host+"/upload/"+dirs+fileName);
			} catch (Exception e) {
				upLoadFile.setSesscess(false);
				upLoadFile.setErrorMsg("文件上传失败");
			}
			list.add(upLoadFile);
		}
		return list;
	}
	
	/**
	 * 将文件重命名
	 * @param name
	 * @return
	 */
	private static String rename(String name) {
		Long now = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		String fileName = now+ "_" +new Random().nextInt(1000);
		if (name.indexOf(".") != -1) {
			fileName += name.substring(name.lastIndexOf("."));
		}
		return fileName;
	}
	  /** 
     * 返回文件的文件后缀名 
      * @param fileName 
      * @return 
    */  
    public static String getExtension(String fileName) {  
        try {  
            return fileName.split("\\.")[fileName.split("\\.").length - 1];  
        } catch (Exception e) {  
            return null;  
        }  
    }  
}
