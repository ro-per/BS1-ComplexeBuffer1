import java.util.concurrent.Semaphore;

public class Thread2 extends Thread {
	private BlockList freeList;
	private BlockList list1;
	private BlockList list2;
	private Semaphore sem;
	private int M;

	public Thread2(BlockList freeList, BlockList list1, BlockList list2, Semaphore sem, int M) {
		this.freeList = freeList;
		this.list1 = list1;
		this.list2 = list2;
		this.sem = sem;
		this.M = M;
	}

	public void run() {
		for (int i = 0; i < M; i++) {
			Block x = list1.unlink();// -> synchronized
			Block y = freeList.unlink();// -> synchronized
			y.setNumber(x.getNumber() / 2); // y=x:2
			freeList.link(x);

			sem.release(); // mag enkel hier, niet in Thread 3 !!!
			list2.link(y);

			System.out.println("THREAD 2 - [ freelist: " + freeList.getCount() + " | " + "list1: " + list1.getCount()
					+ " | " + "list2: " + list2.getCount() + " ] ");

		}
		System.out.println("EINDE 2__________________________________" );

	}
}
