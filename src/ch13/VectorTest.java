package ch13;

import java.util.Vector;

/**
 * 
 * <p>Description:对Vector集合线程安全性的测试</p>
 * @author ZhangShenao
 * @date 2017年5月27日
 */
public class VectorTest {
	private static Vector<Integer> vector = new Vector<Integer>();
	
	public static void main(String[] args) {
		while (true){
			//主线程向vector中添加元素
			for (int i = 0;i < 10;i++){
				vector.add(i);
			}
			
			//removeThread删除vector中的元素
			Thread removeThread = new Thread(){
				@Override
				public void run() {
					for (int i = 0;i < vector.size();i++){
						vector.remove(i);
					}
				}
			};
			
			//printThread线程打印vector中的元素
			Thread printThread = new Thread(){
				@Override
				public void run() {
					for (int i = 0;i < vector.size();i++){
						System.out.println(vector.get(i));
					}
				}
			};
			
			removeThread.start();
			printThread.start();
			
			//避免产生过多的线程
			while (Thread.activeCount() > 20);
			
		}
	}
}
