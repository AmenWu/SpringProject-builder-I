package com.risenb.queue;

import java.util.Hashtable;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
/**
 * 队列工具类
 * @author Administrator
 *
 */
public class QueueUtil {
	/**
	 * 队列集合 key值为相同业务数据 例：可以用商品id作为key值
	 */
	private static Hashtable<String, ConcurrentLinkedQueue<Object>> QUENE_MAP = new Hashtable<String, ConcurrentLinkedQueue<Object>>();

	public QueueUtil() {

	}

	/**
	 * 队列生产法方法
	 * 
	 * @param key
	 * @param e
	 * @return
	 */
	public  static boolean put(String key, Object e) {
		ConcurrentLinkedQueue<Object> queue = QUENE_MAP.get(key);
		if (null == queue) {
			queue = new ConcurrentLinkedQueue<Object>();
		}
		QUENE_MAP.put(key, queue);
		boolean offer = queue.offer(e);
		if (offer) {
			//以下方法可直接消费队列 根据业务操作
			//OutQueue outq = new OutQueue(key);
			//new Thread(outq).start();

		}
		return offer;
	}

	/**
	 * 队列消费方法 需要重写此方法做业务
	 * 
	 * @return
	 * @throws InterruptedException 
	 */
	public   static Object get(String key) throws InterruptedException {
		if (QUENE_MAP.isEmpty()) {
			return null;
		}
		ConcurrentLinkedQueue<Object> queue = QUENE_MAP.get(key);
		if(null == queue ||queue.isEmpty()){
			return null;
		}
		return queue.poll();
	}
	/**
	 * 获取指定队列长度
	 * @param key
	 * @return
	 */
	public  static  int getSize(String key){
		if (QUENE_MAP.isEmpty()) {
			return 0;
		}
		Queue<Object> queue = QUENE_MAP.get(key);
		if(null == queue ||queue.isEmpty()){
			return 0;
		}
		return queue.size();
	}
}
