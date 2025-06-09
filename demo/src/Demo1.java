import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo1 {

	private int j;
	private Lock lock = new ReentrantLock();

	public static void main(String[] args) {
		Demo1 tt = new Demo1();
		for (int i = 0; i < 2; i++) {
			new Thread(tt.new Adder()).start();
			new Thread(tt.new Subtractor()).start();
		}
	}

	private class Subtractor implements Runnable {
		@Override
		public void run() {
			while (true) {
//				synchronized (Demo1.this) {
//					System.out.println("j--=" + j--);
//					//这里抛异常了，锁会自动释放
//					throw new RuntimeException();
//				}
				lock.lock();
				try {
					System.out.println("j--=" + j--);
				} finally {
					//lock.unlock();
				}
			}
		}

	}

	private class Adder implements Runnable {
		@Override
		public void run() {
			while (true) {
//				synchronized (Demo1.this) {
//				System.out.println("j++=" + j++);
//					throw new RuntimeException();
//				}
				lock.lock();
				try {
					System.out.println("j++=" + j++);
				} finally {
					//lock.unlock();
				}
			}
		}

	}
}
