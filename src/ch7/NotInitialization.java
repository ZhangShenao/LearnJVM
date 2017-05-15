package ch7;

import org.junit.Test;

/**
 * 
 * <p>Description:类的被动引用,不会触发类的初始化</p>
 * @author ZhangShenao
 * @date 2017年5月15日
 */
public class NotInitialization {
	public static class ConstClass{
		public static final String CONST = "const";
		
		static{
			System.out.println("ConstClass类被初始化了");
		}
	}
	//父类
	public static class SuperClass{
		public static int value = 123;
		
		static{
			System.out.println("SuperClass类被初始化了");
		}
	}
	
	//子类
	public static class SubClass extends SuperClass{
		static{
			System.out.println("SuperClass类被初始化了");
		}
	}
	
	/**
	 * 通过子类引用父类的静态字段,并不会触发子类的初始化
	 */
	@Test
	public void test1(){
		System.out.println("value= " + SubClass.value);
	}
	
	/**
	 * 通过数组定义来引用类,不会触发该类的初始化
	 */
	@Test
	public void test2(){
		SuperClass[] classes = new SuperClass[10];
		System.out.println(classes.length);
	}
	
	/**
	 * 引用类中的常量,并不会触发类的初始化
	 * 因为在编译过程中,通过常量传播优化,已经将常量存储在所在类的常量池中
	 */
	@Test
	public void test3(){
		System.out.println("常量: " + ConstClass.CONST);
	}
}
