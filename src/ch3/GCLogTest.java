package ch3;

/**
 * 
 * <p>Description:  理解GC日志</p>
 * @author ZhangShenao
 * @date 2017年5月13日 上午11:22:24
 */
public class GCLogTest {
	public static void main(String[] args) {
		byte[] buf = null;
		for (int i = 0;i < 5;i++){
			buf = new byte[1024 * 1024];
		}
		buf = null;
		System.gc();
	}
}
