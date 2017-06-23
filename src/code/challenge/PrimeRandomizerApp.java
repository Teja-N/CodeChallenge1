package code.challenge;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PrimeRandomizerApp {

	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<Integer> randomNumberQueue = new LinkedBlockingQueue<Integer>(); // randomNumberQueue to post random numbers
	    BlockingQueue<String> resultQueue = new LinkedBlockingQueue<String>(); // resultQueue to post Prime Number check result
	     Prime prime = new Prime(randomNumberQueue,resultQueue); 
	     Randomizer randomizer = new Randomizer(randomNumberQueue,resultQueue); 

	        new Thread(randomizer).start(); //Randomizer Thread Starts
	        new Thread(prime).start();      //Prime Thread Starts

	        Thread.sleep(3000); // Main Thread sleep for 3 secs
	 



	}

}
