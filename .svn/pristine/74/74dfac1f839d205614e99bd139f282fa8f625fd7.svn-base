package com.risenb.push;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

/**
 * 极光推送工具类
 * @author Administrator
 *
 */
public class Jpush {
	/**
	 * 推送全部用户
	 * @param notification 
	 * @param message
	 * @param production 是否为正式环境
	 * @return
	 */
	public static boolean pushAll(String notification, String message,boolean production) {
		ClientConfig clientConfig = ClientConfig.getInstance();
		JPushClient jpushClient = new JPushClient(PushConstant.J_MASTERSECRET, PushConstant.J_APPID, null, clientConfig);
		boolean b = false;
		PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.all())
				.setNotification(Notification.newBuilder()
						.addPlatformNotification(IosNotification.newBuilder().setAlert(notification).setSound("default")
								// .setBadge(5)
								.setContentAvailable(true).addExtra("from", "JPush").addExtra("content", message)
								.build())
						.addPlatformNotification(AndroidNotification.newBuilder().setAlert(notification).build())
						.build())
				.setMessage(Message.content(message)).setOptions(Options.newBuilder().setApnsProduction(production).build())
				.build();
		try {
			jpushClient.sendPush(payload);
			b = true;
		} catch (APIConnectionException e) {
		} catch (APIRequestException e) {
		}
		return b;
	}
	/**
	 * 根据别名进行推送
	 * @param alias
	 * @param notification
	 * @param message 
	 * @param production  是否为正式环境
	 * @return
	 */
	public static boolean pushSelected(String alias[], String notification, String message,boolean production) {
		ClientConfig clientConfig = ClientConfig.getInstance();
		JPushClient jpushClient = new JPushClient(PushConstant.J_MASTERSECRET, PushConstant.J_APPID, null, clientConfig);
		boolean b = false;
		PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.alias(alias))
				.setNotification(Notification.newBuilder()
						.addPlatformNotification(IosNotification.newBuilder().setAlert(notification).setSound("default")
								// .setBadge(5)
								.setContentAvailable(true).addExtra("from", "JPush").addExtra("content", message)
								.build())
						.addPlatformNotification(AndroidNotification.newBuilder().setAlert(notification).build())
						.build())
				// .setMessage(Message.content(message))
				.setOptions(Options.newBuilder().setApnsProduction(production).build()).build();
		try {
			jpushClient.sendPush(payload);
			b = true;
		} catch (APIConnectionException e) {
		} catch (APIRequestException e) {
		}
		return b;
	}
}
