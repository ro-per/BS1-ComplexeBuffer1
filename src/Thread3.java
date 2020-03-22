import java.util.concurrent.Semaphore;

public class Thread3 extends Thread {
	private BlockList freeList;
	private BlockList list1;
	private BlockList list2;
	private Semaphore sem;
	private int M;

	public Thread3(BlockList freeList, BlockList list1, BlockList list2, Semaphore sem, int M) {
		this.freeList = freeList;
		this.list1 = list1;
		this.list2 = list2;
		this.sem = sem;
		this.M = M;
	}

	public void run() {
		for (int i = 0; i < M; i++) {
			Block c = list2.unlink();// -> synchronized
			System.out.println(c.getNumber());
			freeList.link(c);

			System.out.println("THREAD 3 - [ freelist: " + freeList.getCount() + " | " + "list1: " + list1.getCount()
					+ " | " + "list2: " + list2.getCount() + " ] " + " --> " + c.getNumber());

		}
		System.out.println("EINDE 3__________________________________" );

	}
}
