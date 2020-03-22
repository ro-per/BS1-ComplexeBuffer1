import java.util.concurrent.Semaphore;

public class Thread1 extends Thread {
	private BlockList freeList;
	private BlockList list1;
	private BlockList list2;
	private Semaphore sem;
	private int M;

	public Thread1(BlockList freeList, BlockList list1, BlockList list2, Semaphore sem, int M) {
		this.freeList = freeList;
		this.list1 = list1;
		this.list2 = list2;
		this.sem = sem;
		this.M = M;
	}

	public void run() {
		for (int i = 0; i < M; i++) {
			try {
				sem.acquire();
			} catch (Exception e) {
				System.out.println("sem.acquire() geeft problemen");
			}
			Block b = freeList.unlink(); // -> synchronized
			b.setNumber(Math.random() * 10000); // produceer informatie in blok b
			list1.link(b);

			System.out.println("THREAD 1 - [ freelist: " + freeList.getCount() + " | " + "list1: " + list1.getCount()
					+ " | " + "list2: " + list2.getCount() + " ] ");
		}
		System.out.println("EINDE 1__________________________________");

	}
}
