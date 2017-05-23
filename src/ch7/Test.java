package ch7;

public class Test {
	static {
		i = 0;
		
		/**
		 * 非法前向引用
		 * 静态代码块只能访问到定义在前面的变量,定义在后面的变量可以赋值,但是不能使用
		 */
		//System.out.println(i);	
	}
	
	static int i = 1;
}
