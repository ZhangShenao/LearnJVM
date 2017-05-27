package ch12;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * <p>Description:测试死锁</p>
 * @author ZhangShenao
 * @date 2017年5月24日
 */
public class DeadLockTest {
	//对象锁
	private String A = "A";
	private String B = "B";
	
	//显示锁
	private Lock lock1 = new ReentrantLock(false);
	private Lock lock2 = new ReentrantLock(false);
	
	/**
	 * 使用synchronized关键字实现死锁
	 * 处于死锁的线程都处于Blocked阻塞状态
	 */
	private void deadLockWithSynchronized(){
		//线程1,先拿锁A再拿锁B
		Thread t1 = new Thread(new Runnable(){
			@Override
			public void run() {
				synchronized (A) {
					try {
						Thread.sleep(1000);
						synchronized (B) {
							System.err.println("1");
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		},"T1");
		
		//线程2,先拿锁B再拿锁A
		Thread t2 = new Thread(new Runnable(){
			@Override
			public void run() {
				synchronized (B) {
					synchronized (A) {
						System.err.println("2");
					}
				}
			}
		},"T2");
		
		t1.start();
		t2.start();
	}
	
	/**
	 * 使用显示Lock实现死锁
	 * 处于死锁的线程都处于Waiting状态等待获取锁对象
	 */
	private void deadLockWithLock(){
		//线程1,先拿锁1再拿锁2
		Thread t1 = new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					lock1.lock();
					Thread.sleep(1000);
					lock2.lock();
					System.err.println("1");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally {
					lock2.unlock();
					lock1.unlock();
				}
			}
		},"T1");
		
		//线程2,先拿锁2再拿锁1
		Thread t2 = new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					lock2.lock();
					Thread.sleep(1000);
					lock1.lock();
					System.err.println("1");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally {
					lock1.unlock();
					lock2.unlock();
				}
			}
		},"T2");
		
		t1.start();
		t2.start();
	}
	
	public static void main(String[] args) {
		DeadLockTest deadLockTest = new DeadLockTest();
		deadLockTest.deadLockWithSynchronized();
		deadLockTest.deadLockWithLock();
	}
}
