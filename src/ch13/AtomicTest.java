package ch13;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * <p>Description:Atomic变量自增运算测试</p>
 * @author ZhangShenao
 * @date 2017年5月26日
 */
public class AtomicTest {
	private static AtomicInteger count = new AtomicInteger(0);
	private static final int THREAD_NUM = 20;
	
	public static void main(String[] args) throws InterruptedException {
		Thread t = null;
		for (int i = 0;i < THREAD_NUM;i++){
			t = new Thread(){
				@Override
				public void run() {
					for (int i = 0;i < 1000;i++){
						count.incrementAndGet();
					}
				}
			};
			t.start();
		}
		
		//等待所有线程累加结束
		while (Thread.activeCount() > 1){
			Thread.yield();
		}
		
		System.out.println("count= " + count.get());
	}
}
