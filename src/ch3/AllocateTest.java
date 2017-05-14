package ch3;

/**
 * 
 * <p>Description: JVM的内存分配策略 </p>
 * @author ZhangShenao
 * @date 2017年5月14日 上午11:13:53
 */
public class AllocateTest {
	private static final int _1MB = 1024 * 1024;
	
	/**
	 * JVM参数:-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
	 */
	public void testAllocation(){
		byte[] allocation1 = new byte[2 * _1MB];
		byte[] allocation2 = new byte[2 * _1MB];
		byte[] allocation3 = new byte[2 * _1MB];
		byte[] allocation4 = new byte[4 * _1MB];	//出现一次Minor GC
	}
}
