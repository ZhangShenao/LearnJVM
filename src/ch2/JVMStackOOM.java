package ch2;

/**
 * 
 * <p>Description:测试JVM的栈的OutOfMemoryError,只有在创建多个线程的情况下才会出现次异常</p>
 * <p>JVM参数:-Xss2M</p>
 * @author ZhangShenao
 * @date 2017年5月8日
 */
public class JVMStackOOM {
	/**
	 * 由与多线程导致的栈OutOfMemoryError
	 */
	public void stackLeakByMultiThread(){
		Thread t = null;
		for (;;){
			t = new Thread(){
				@Override
				public void run() {
					for (;;){
						
					}
				}
			};
			t.start();
		}
	}
	
	public static void main(String[] args) {
		JVMStackOOM jvmStackOOM = new JVMStackOOM();
		jvmStackOOM.stackLeakByMultiThread();
	}
}
