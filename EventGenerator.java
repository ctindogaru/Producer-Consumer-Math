import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.ArrayBlockingQueue;

public class EventGenerator implements Runnable {
	String fileName;
	ArrayBlockingQueue<Event> queue;
	int nrOfEvents;

	public EventGenerator(String fileName, ArrayBlockingQueue<Event> queue, int nrOfEvents) {
		this.fileName = fileName;
		this.queue = queue;
		this.nrOfEvents = nrOfEvents;
	}

	@Override
	public void run() {
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			for (int j = 0; j < nrOfEvents; j++) {
				String line = br.readLine();
				String[] words = line.split(",");
				Thread.sleep((Integer.parseInt(words[0])));
				Event event = new Event(words[1], Integer.parseInt(words[2]));
				queue.put(event);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
