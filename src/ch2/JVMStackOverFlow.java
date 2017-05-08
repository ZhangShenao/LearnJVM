package ch2;

/**
 * 
 * <p>Description:测试JVM栈StackOverflowError。
 * 在单线程的情况下,无论是栈帧太大还是虚拟机分配的内存太小,都只会抛出StackOverflowError,而不会抛出OutOfMemeryError</p>
 * <p>JVM参数:-Xss128K</p>
 * @author ZhangShenao
 * @date 2017年5月8日
 */
public class JVMStackOverFlow {
	private static int stackLength = 1;
	
	public void stackLeak(){
		++stackLength;
		stackLeak();
	}
	
	public static void main(String[] args) {
		JVMStackOverFlow jvmStackOverFlow = new JVMStackOverFlow();
		try {
			jvmStackOverFlow.stackLeak();
		}catch (StackOverflowError e){
			System.out.println("当前栈深度: " + stackLength);
			throw e;
		}
	}
}
