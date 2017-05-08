package ch2;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * <p>Description:测试Java堆溢出</p>
 * <p>VM参数:-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails 
 * 		-XX:SurvivorRatio=8 -XX:+HeapDumpOnOutOfMemoryError</p>
 * @author ZhangShenao
 * @date 2017年5月8日
 */
public class HeapOOM {
	public static void main(String[] args) {
		List<HeapOOM> list = new ArrayList<>();
		
		for (;;){
			list.add(new HeapOOM());
		}
		
	}
}
