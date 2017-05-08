package ch2;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

/**
 * 
 * <p>Description:直接内存溢出</p>
 * <p>JVM参数:-XX:MaxDirectMemorySize</p>
 * @author ZhangShenao
 * @date 2017年5月8日
 */
public class DirectMemoryOOM {
	private static final long _1MB = 1024 * 1024;
	
	public static void main(String[] args) throws Exception {
		Field field = Unsafe.class.getDeclaredFields()[0];
		field.setAccessible(true);
		Unsafe unsafe = (Unsafe) field.get(null);
		
		for (;;){
			//使用Unsafe类分配本机内存
			unsafe.allocateMemory(_1MB);
		}
		
	}
}
