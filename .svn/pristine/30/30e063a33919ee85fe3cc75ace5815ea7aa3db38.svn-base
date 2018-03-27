package com.risenb.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ConsumerMessageListener implements MessageListener{
	/**
	 * 接收消息 处理业务
	 */
	@Override
	public void onMessage(Message arg0) {
        TextMessage textMsg = (TextMessage) arg0;  
        try {  
            System.out.println("消息内容是：" + textMsg.getText());  
        } catch (JMSException e) {  
            e.printStackTrace();  
        }  
		
	}  
}
