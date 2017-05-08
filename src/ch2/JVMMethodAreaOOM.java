package ch2;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;


/**
 * 
 * <p>Description:测试JVM方法区溢出,使用CGLib字节码增强技术动态加载大量的类</p>
 * <p>JVM参数:-XX:PermSize=10M -XX:MaxPermSize=10M</p>
 * @author ZhangShenao
 * @date 2017年5月8日
 */
public class JVMMethodAreaOOM {
	public static void main(String[] args) {
		Enhancer enhancer = null;
		
		for (;;){
			enhancer = new Enhancer();
			enhancer.setSuperclass(OOMObject.class);
			enhancer.setUseCache(false);
			enhancer.setCallback(new MethodInterceptor() {
				
				@Override
				public Object intercept(Object obj, Method method, Object[] args,
						MethodProxy proxy) throws Throwable {
					return proxy.invokeSuper(obj, args);
				}
			});
			enhancer.create();
		}
	}
	
	public static class OOMObject{
		
	}
}
