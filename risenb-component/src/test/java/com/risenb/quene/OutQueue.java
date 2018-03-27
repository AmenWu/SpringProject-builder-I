package com.risenb.quene;

import com.risenb.queue.QueueUtil;

/**
 * 消费者
 * 
 * @author Administrator
 *
 */
public class OutQueue implements Runnable {
	private String key;

	public OutQueue(String key) {
		this.key = key;
	}

	@Override
	public void run() {
		boolean flag = true;
		while (flag) {
			String outValue = null;
			try {
				outValue = (String) QueueUtil.get(key);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (outValue != null) {
				{
					if (outValue.startsWith("4")) {
						System.out.println(outValue);
					}
				}
			} else {
				// flag =false;
			}
		}
	}
}
