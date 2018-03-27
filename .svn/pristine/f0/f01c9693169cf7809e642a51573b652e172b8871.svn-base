package com.risenb.controller.manage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.risenb.annotation.Log;
import com.risenb.bean.Error;
import com.risenb.bean.Result;
import com.risenb.common.CommonControllor;

/**
 * <pre>
 * <p> Title:错误信息控制类 </p>
 * <p> Description: </p>
 * </pre>
 * @version 1.0
 */
@Controller
@RequestMapping(value = "error")
public class ErrorController extends CommonControllor{
	/**
	 * 没有权限跳转页面
	 * @param request
	 * @param response
	 * @return
	 */
	@Log(isLog = false)
	@RequestMapping(value = "authority")
	public String authority(){
		request().setAttribute("code", "没有权限");
		request().setAttribute("errorTitle", "没有权限！");
		request().setAttribute("errorMsg", "抱歉，您好像没有这个权限啊");
		return "common/error";
	}
	/**
	 * 资源未找到
	 * @param request
	 * @param response
	 * @return
	 */
	@Log(isLog = false)
	@RequestMapping(value = "notFound")
	public String notFound(){
		request().setAttribute("code", "404");
		request().setAttribute("errorTitle", "资源未找到！");
		request().setAttribute("errorMsg", " 抱歉，页面好像去火星了~");
		return "common/error";
	}
	/**
	 * 服务器内部错误
	 * @param request
	 * @param response
	 * @return
	 */
	@Log(isLog = false)
	@RequestMapping(value = "innerError")
	public String innerError(){
		request().setAttribute("code", "500");
		request().setAttribute("errorTitle", "服务器内部错误");
		request().setAttribute("errorMsg", " 服务器好像出错了...");
		return "common/error";
	}
	
	/**
	 * 异步提示没有权限
	 */
	@Log(isLog = false)
	@RequestMapping(value = "ajaxAuthority")
	public void ajaxAuthority(){
		returnJson(Result.failure(Error._200105));
	}
}
