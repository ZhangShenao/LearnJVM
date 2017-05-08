package ch3;

/**
 * 
 * <p>Description:引用计数算法,为每个对象添加一个引用计数器,当有一个地方引用它时,计数器就+1;有一个引用失效时,计数器就-1。
 * 当计数器的值为0时就认为该对象不可用</p>
 * <p>缺点：难以解决对象之间相互循环引用的问题</p>
 * @author ZhangShenao
 * @date 2017年5月8日
 */
public class ReferenceCountingGC {
	private static final int _1MB = 1024 * 1024;
	
	/**
	 * 模拟较占内存的对象
	 */
	private byte[] bigSize = new byte[2 * _1MB];
	
	private Object obj;

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	public static void main(String[] args) {
		ReferenceCountingGC objA = new ReferenceCountingGC();
		ReferenceCountingGC objB = new ReferenceCountingGC();
		
		//将两个对象相互循环引用
		objA.setObj(objB);
		objB.setObj(objA);
		
		//GC
		objA = null;
		objB = null;
		System.gc();
	}
}
