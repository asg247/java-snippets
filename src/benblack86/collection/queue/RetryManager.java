package benblack86.collection.queue;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class RetryManager extends Thread {

	public static void main(String[] args) {
		Random random = new Random();
		RetryManager manager = new RetryManager();
		manager.start();
		for(int i = 0; i < 1000; i++) {
			manager.addToQueue(new Item("task_"+i, new Object()));
			try {
				int wait = random.nextInt(10);
				System.out.printf("Wait:%s\n", wait);
				Thread.sleep(wait);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private final static int QUEUE_MAX_CAPACITY = 100;
	private final static int WARN_LEVEL = 10;
	private final BlockingQueue<Item> queue = new ArrayBlockingQueue<Item>(QUEUE_MAX_CAPACITY);
	

	
	public void addToQueue(Item item) {
		// waits until there is space
		try {
			queue.offer(item, 1, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if (queue.size() > WARN_LEVEL) {
			System.out.printf("Queue warning|Size:%s|Capacity:%s|Filled:%s%%\n", queue.size(), QUEUE_MAX_CAPACITY, ((double)queue.size()/QUEUE_MAX_CAPACITY)*100);
		}
	}
 
	@Override
	public void run() {
		while (true) {
			try {
				// take waits if necessary until an element becomes available
				Item item = queue.take();
				
				System.out.printf("Doing something with item|Name:%s\n", item.name);
				
				Thread.sleep(5);
				
			} catch(Throwable t) {
				t.printStackTrace();
			}
		}
	}

}

class Item {
	String name;
	Object object;
	
	public Item(String name, Object object) {
		this.name = name;
		this.object = object;
	}
}
