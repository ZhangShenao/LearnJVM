package ch7;

/**
 * 
 * <p>Description:JVM会保证一个类的clinit()方法在多线程环境下被正确地加锁、同步,
 * 如果有多个线程同时去初始化一个类,那么只会有一个线程去执行这个类的clinit方法,其他线程都会阻塞等待。</p>
 * @author ZhangShenao
 * @date 2017年5月23日
 */
public class DeadLoopClass {
	private static class MyClass{
		static {
			if (true){
				System.out.println("线程: " + Thread.currentThread().getName() + " 正在初始化MyClass类");
				while (true){
					
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new Thread(new MyTask(),"T1").start();
		new Thread(new MyTask(),"T2").start();
	}
	
	private static class MyTask implements Runnable{
		@Override
		public void run() {
			System.out.println("线程: " + Thread.currentThread().getName() + " 启动");
			MyClass myClass = new MyClass();
			System.out.println("线程: " + Thread.currentThread().getName() + " 结束");
		}
	}
}
