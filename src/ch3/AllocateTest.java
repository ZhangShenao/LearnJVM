package ch3;

import org.junit.Test;

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
	@Test
	public void testAllocation(){
		byte[] allocation1 = new byte[2 * _1MB];
		byte[] allocation2 = new byte[2 * _1MB];
		byte[] allocation3 = new byte[2 * _1MB];
		byte[] allocation4 = new byte[4 * _1MB];	//出现一次Minor GC
	}
	
	/**
	 * JVM参数:-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
	 * -XX:PretenureSizeThreshold=3145728
	 */
	@Test
	public void testPretenureSizeThreshold(){
		//大对象直接在老年代中分配
		//尽量避免创建大量的"短命大对象"
		byte[] allocation = new byte[4 * _1MB];
	}
	
	/**
	 * JVM参数:-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
	 * -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
	 */
	@Test
	public void testMaxTenuringThreshold(){
		byte[] allocation1 = new byte[_1MB / 4];
		byte[] allocation2 = new byte[4 * _1MB];
		byte[] allocation3 = new byte[4 * _1MB];
		allocation3 = null;
		allocation3 = new byte[4 * _1MB];
	}
	
	/**
	 * JVM参数:-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
	 * -XX:MaxTenuringThreshold=15 -XX:+PrintTenuringDistribution
	 */
	@Test
	public void testMaxTenuringThreshold1(){
		byte[] allocation1 = new byte[_1MB / 4];
		byte[] allocation2 = new byte[_1MB / 4];
		byte[] allocation3 = new byte[4 * _1MB];
		byte[] allocation4 = new byte[4 * _1MB];
		allocation4 = null;
		allocation4 = new byte[4 * _1MB];
	}
}
