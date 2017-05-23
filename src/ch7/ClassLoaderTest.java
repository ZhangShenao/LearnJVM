package ch7;

import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * <p>Description:判断两个类是否相等的两个条件:1.被同一个类加载器所加载;2.来源于同一个Class文件</p>
 * @author ZhangShenao
 * @date 2017年5月23日
 */
public class ClassLoaderTest {
	public static void main(String[] args) throws ClassNotFoundException {
		ClassLoader myLoader = new ClassLoader() {
			@Override
			public Class<?> loadClass(String name){
				InputStream in = null;
				try {
					String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
					in = getClass().getResourceAsStream(fileName);
					if (in == null){
						return super.loadClass(name);
					}
					byte[] buf = new byte[in.available()];
					in.read(buf);
					return defineClass(name, buf, 0,buf.length);
					
				}catch (Exception e){
					throw new RuntimeException(e);
				}finally {
					if (null != in){
						try {
							in.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		};
		
		//虚拟机中会存在两个ClassLoaderTest类,一个由系统应用类加载器加载,另一个由自定义类加载器加载,由于使用了不同的类加载器,因此虚拟机会认为这是两个不同的类
		Object obj = myLoader.loadClass("ch7.ClassLoaderTest");
		System.out.println(obj.getClass());
		System.out.println(obj instanceof ch7.ClassLoaderTest);
		
	}
}
