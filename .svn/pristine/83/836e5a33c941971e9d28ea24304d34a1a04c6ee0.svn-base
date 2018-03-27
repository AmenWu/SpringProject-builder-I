package com.risenb.controller.manage;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.risenb.annotation.Log;
import com.risenb.bean.Error;
import com.risenb.bean.ImgModel;
import com.risenb.common.CommonControllor;
import com.risenb.oss.LocalOss;
import com.risenb.util.PropertyUtil;

/**
 * 文件上传
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("upload")
public class UploadController extends CommonControllor {
	/**
	 * 上传图片
	 * 
	 * @throws IOException
	 */
	@Log(isLog = false)
	@RequestMapping("uploadImg")
	public void uploadImg() throws IOException {
		HttpServletRequest request = request();
		// 本地文件保存路径 配置在system-config.properties
		String path = PropertyUtil.getString("OSS.LOCAL.PATH");
		// 本地文件访问url 配置在system-config.properties
		String host = PropertyUtil.getString("OSS.LOCAL.HOST");
		if (null == path || "".equals(path)) {// 文件保存路径未设置 读取本地路径并重置文件访问路径
			path = session().getServletContext().getRealPath(File.separator) + File.separator;
			host = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		}
		ImgModel upLoadImg = LocalOss.upLoadImg(path, host, request, false, 100, 100);
		if (null != upLoadImg) {
			returnJson(upLoadImg);
		} else {// 上传失败
			ImgModel temp = new ImgModel();
			temp.setSesscess(false);
			temp.setErrorMsg(Error._200212.getErrorMsg());
			returnJson(temp);
		}
	}
}
