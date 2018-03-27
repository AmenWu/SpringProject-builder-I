package com.risenb.util;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 
 */

/**
 * <pre>
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2010 - 2016 </p>
 * <p>Company: Beijing Risenb Technology Co.,Ltd. </p>
 * <p>Build: 2017年8月10日 下午12:34:10 </p>
 * </pre>
 *
 * @author Chang Yuxin
 * @version 1.0
 */
public class MailTest {
	/**
	 * 使用Transport 静态方法 发送邮件 连接163服务，给多个QQ邮箱发邮件
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// 属性对象
		Properties properties = new Properties();
		// 开启debug调试 ，打印信息
		properties.setProperty("mail.debug", "true");
		// 发送服务器需要身份验证
		properties.setProperty("mail.smtp.auth", "true");
		// 发送服务器端口，可以不设置，默认是25
		properties.setProperty("mail.smtp.port", "25");
		// 发送邮件协议名称
		properties.setProperty("mail.transport.protocol", "smtp");
		// 设置邮件服务器主机名
		properties.setProperty("mail.host", "smtp.jiajiashuaxin.com");
		// 环境信息
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// 在session中设置账户信息，Transport发送邮件时会使用
				return new PasswordAuthentication("laowutong@jiajiashuaxin.com", "Lwt2017&");
			}
		});

		// 创建邮件对象
		Message message = new MimeMessage(session);
		// 设置主题
		message.setSubject("中文主题");
		// 发件人
		message.setFrom(new InternetAddress("laowutong@jiajiashuaxin.com"));
		// 多个收件人
		message.setRecipients(RecipientType.TO, InternetAddress.parse("changyuxin@lzhuogroup.com"));
		// 抄送人
		message.setRecipient(RecipientType.CC, new InternetAddress("changyuxin@lzhuogroup.com"));
		// 暗送人
		message.setRecipient(RecipientType.BCC, new InternetAddress("changyuxin@lzhuogroup.com"));
		// HTML内容
		message.setContent("<span style='color:red'>中文呵呵</span>", "text/html;charset=utf-8");

		// 连接邮件服务器、发送邮件、关闭连接，全做了
		Transport.send(message);

	}

}
