package ch12;

/**
 * 
 * <p>Description:volatile变量自增测试</p>
 * <p>volatile可以保证变量对于所有线程的可见性,但是无法保证操作的原子性</p>
 * @author ZhangShenao
 * @date 2017年5月26日
 */
public class VolatileTest {
	private static volatile int count = 0;
	private static final int THREAD_NUM = 20;
	
	public static void main(String[] args) throws InterruptedException {
		Thread t = null;
		for (int i = 0;i < THREAD_NUM;i++){
			t = new Thread(){
				@Override
				public void run() {
					for (int i = 0;i < 1000;i++){
						++count;
					}
				}
			};
			t.start();
		}
		
		//等待所有线程累加结束
		while (Thread.activeCount() > 1){
			Thread.yield();
		}
		
		System.out.println("count= " + count);
	}
}
