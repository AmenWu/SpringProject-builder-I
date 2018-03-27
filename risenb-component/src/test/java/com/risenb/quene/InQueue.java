package com.risenb.quene;

import java.util.Random;

import com.risenb.queue.QueueUtil;

/**
 * 生产者
 * 
 * @author Administrator
 *
 */
public class InQueue implements Runnable {
	
	private String key;
	
	public InQueue() {
		
	}
	public InQueue(String key) {
		this.key=key;
	}

	@Override
	public void run() {
		int i = 0;
		while (true) {
			if (QueueUtil.put(key, key+"------"+String.valueOf(i))) {
				//System.out.println(i + "长度"+QueueUtil.getSize(key));
			} else {
				System.out.println(Thread.currentThread().getName() + "放入失败");
			}
			i++;
			try {
				Random t = new Random();
				Thread.sleep(t.nextInt(10));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		InQueue in = new InQueue("4");
		in.run();
	
	}

}
