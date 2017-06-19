import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
	
	public static void sortNumbers(String fileName) throws FileNotFoundException, IOException {
		ArrayList<Integer> array = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	array.add(Integer.parseInt(line));
		    }
		}
		Collections.sort(array);
		try{
		    PrintWriter writer = new PrintWriter(fileName, "UTF-8");
		    for (int i = 0; i < array.size(); i++)
		    	writer.println(array.get(i));
		    writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		int queueSize = Integer.parseInt(args[0]);
		ArrayBlockingQueue<Event> queue = new ArrayBlockingQueue<Event>(queueSize);
		int nrOfEvents = Integer.parseInt(args[1]);
		Thread[] threads = new Thread[args.length - 2];

		PrintWriter writer = new PrintWriter("PRIME.out", "UTF-8");
		writer = new PrintWriter("FACT.out", "UTF-8");
		writer = new PrintWriter("SQUARE.out", "UTF-8");
		writer = new PrintWriter("FIB.out", "UTF-8");
		
		for (int i = 2; i < args.length; i++) {
			String fileName = args[i];
			threads[i-2] = new Thread(new EventGenerator(fileName, queue, nrOfEvents));
		}
		
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
		
		ExecutorService tpe = Executors.newFixedThreadPool(4);
		
		for(int i = 0; i < threads.length * nrOfEvents; i++)
			tpe.execute(new EventCalculator(queue));
		
		tpe.shutdown();
		while (!tpe.isTerminated()) {
		}

		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		sortNumbers("PRIME.out");
		sortNumbers("FACT.out");
		sortNumbers("SQUARE.out");
		sortNumbers("FIB.out");
	}
}
