package ch2;

import java.util.ArrayList;

/**
 * 
 * <p>Description:测试JVM运行时常量池/方法区溢出</p>
 * <p>JVM参数:-XX:PermSize=10M -XX:MaxPermSize=10M</p>
 * @author ZhangShenao
 * @date 2017年5月8日
 */
public class RuntimeConstantPoolOOM {
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		for (int i=0;;i++){
			list.add(String.valueOf(i).intern());
		}
	}
}
