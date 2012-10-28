package benblack86.notification;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Notify {
	private static ExecutorService service = Executors.newFixedThreadPool(2);
	
	public static void main(String args[]) {
		final Notify notify = new Notify();
		long start = System.currentTimeMillis();
		
		service.submit(new Runnable() {

			@Override
			public void run() {
				Random generator = new Random();
				long waitTime = generator.nextInt(5000);
				boolean success = generator.nextBoolean();
				
				System.out.printf("Runnable|Wait:%sms|Success:%s\n", waitTime, success);
				
				try {
					Thread.sleep(waitTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				if(success) {
					notify.processMsg("hi");
				} else {
					notify.processMsg("bye");
				}
			}
			
		});
		
		try {
			notify.waitForMsg();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		
		System.out.printf("Notify|Waited:%sms|Status:%s\n", end-start, notify.getStatus());
		
		// doesn't shutdown until the run is complete
		service.shutdown();
	}
	
	
	private Status status = Status.NO_REPLY;
	
	public synchronized void reset() {
		this.status = Status.NO_REPLY;
	}
	
	public synchronized Status getStatus() {
		return status;
	}

	public synchronized void processMsg(String msg) {
		if(msg.equals("hi")) {
			status = Status.SUCCESS;
		} else {
			status = Status.FAILURE;
		}
		notifyAll();
	}

	public synchronized void waitForMsg() throws InterruptedException {
		if (status != Status.NO_REPLY) {
			return;
		}

		wait(2000);
	}
}

enum Status {
	SUCCESS,
	FAILURE,
	NO_REPLY;
}