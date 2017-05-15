package ch4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * <p>Description:使用jconsole工具监控JVM进程</p>
 * @author ZhangShenao
 * @date 2017年5月15日
 */
public class MonitoringTest {
	/**
	 * 
	 * <p>Description:内存占位符对象,一个OOMObject大约占64k内存</p>
	 * @author ZhangShenao
	 * @date 2017年5月15日
	 */
	private static class OOMObject{
		@SuppressWarnings("unused")
		public byte[] placeholder = new byte[64 * 1024];
	}
	
	public void fillHeap(int num){
		List<OOMObject> list = new ArrayList<>();
		
		for (int i = 0;i < num;i++){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			list.add(new OOMObject());
		}
		
		System.gc();
	}
	
	/**
	 * 监控Java内存
	 */
	public void testHeap(){
		fillHeap(1000);
	}
	
	/**
	 * 测试死循环
	 */
	public static void createBusyThread(){
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true){
					
				}
			}
		},"Busy Thread");
		t.start();
	}
	
	/**
	 * 测试线程等待锁
	 */
	public static void createLockThread(final Object lock){
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				synchronized (lock) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "Create Lock");
		t.start();
	}
	
	private static class DeadLockThread extends Thread{
		private int a;
		private int b;
		
		public DeadLockThread(String name,int a, int b) {
			super(name);
			this.a = a;
			this.b = b;
		}
		
		@Override
		public void run() {
			synchronized (Integer.valueOf(a)) {
				synchronized (Integer.valueOf(b)) {
					System.out.println("sum= " + (a + b));
				}
			}
		}
		
	}
	
	/**
	 * 测试死锁
	 */
	public static void testDeadLock(){
		DeadLockThread t1 = null;
		DeadLockThread t2 = null;
		for (int i = 0;i < 100;i++){
			t1 = new DeadLockThread("DeadLock1", 1, 2);
			t2 = new DeadLockThread("DeadLock2", 2, 1);
			t1.start();
			t2.start();
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		in.readLine();
		
		createBusyThread();
		
		createLockThread(new Object());
//		testDeadLock();
	}
}
