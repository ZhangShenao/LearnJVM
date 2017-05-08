package ch2;

/**
 * 
 * <p>Description:测试String的intern方法</p>
 * <p>intern方法会返回字符串在常量池中首次出现的引用,如果常量池中不存在,则创建字符串并放入常量池中,并返回新创建的字符串在堆中的引用</p>
 * @author ZhangShenao
 * @date 2017年5月8日
 */
public class InternTest {
	public static void main(String[] args) {
		String java = new StringBuffer().append("java").toString();
		System.out.println(java.intern() == java);
		String javaee = new StringBuilder(java).append("ee").toString();
		System.out.println(javaee.intern() == javaee);
	}
}
