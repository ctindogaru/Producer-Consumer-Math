import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class EventCalculator implements Runnable {
	ArrayBlockingQueue<Event> queue;
	
	public EventCalculator(ArrayBlockingQueue<Event> queue) {
		this.queue = queue;
	}
	
	@Override
	public void run() {
		try {
			Event event = queue.take();
			int N = event.N;
			int closestToN = 0;
			int i;
			PrintWriter writer;
			
			switch (event.type) {
				case "PRIME":
					closestToN = 2;
					i = N;
					boolean flag = true;
					
					while (flag) {
						flag = false;
						for (int k = 2; k <= Math.sqrt(i); k++) {
							if (i % k == 0) {
								i--;
								flag = true;
								break;
							}
						}
					}
					closestToN = i;
					try {
						writer = new PrintWriter(new BufferedWriter(new FileWriter("PRIME.out", true)));
						writer.println(closestToN);
						writer.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					break;
				case "FACT":
					closestToN = 1;
					i = 2;
					while (closestToN * i <= N) {
						closestToN *= i;
						i++;
					}
					try {
						writer = new PrintWriter(new BufferedWriter(new FileWriter("FACT.out", true)));
						writer.println(i-1);
						writer.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case "SQUARE":
					closestToN = 1;
					i = 2;
					while (i * i <= N) {
						closestToN = i * i;
						i++;
					}
					try {
						writer = new PrintWriter(new BufferedWriter(new FileWriter("SQUARE.out", true)));
						writer.println(i-1);
						writer.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case "FIB":
					closestToN = 1;
					i = 0;
					int j = 1;
					while (i + j <= N) {
						int aux = i + j;
						i = j;
						j = aux;
						closestToN++;
					}
					try {
						writer = new PrintWriter(new BufferedWriter(new FileWriter("FIB.out", true)));
						writer.println(closestToN);
						writer.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
