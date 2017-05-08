package ch3;

/**
 * 
 * <p>Description:一个本该被GC回收的对象,可通过在finalize方法中重新获得引用而复活。由于JVM对每个对象的finalize只调用1次,因此只有一次复活机会。</p>
 * @author ZhangShenao
 * @date 2017年5月8日
 */
public class FinalizeEscapeGC {
	private static FinalizeEscapeGC saveHook = null; 
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("执行FinalizeEscapeGC对象的finalize方法");
		
		//使即将被回收的对象重新获得引用
		saveHook = this;
	}
	
	public void alive(){
		System.out.println("FinalizeEscapeGC对象仍然存活");
	}
	
	public static void main(String[] args) {
		saveHook = new FinalizeEscapeGC();
		
		//第一次回收FinalizeEscapeGC对象
		//对象在执行finalize方法时重新获得引用,成功拯救自己
		saveHook = null;
		System.gc();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if (null != saveHook){
			saveHook.alive();
		}else {
			System.out.println("FinalizeEscapeGC对象已经被回收");
		}
		
		//第二次回收FinalizeEscapeGC对象
		//由于一个对象只能执行一次finalize方法,因此该次会被回收
		saveHook = null;
		System.gc();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if (null != saveHook){
			saveHook.alive();
		}else {
			System.out.println("FinalizeEscapeGC对象已经被回收");
		}
	}
}
