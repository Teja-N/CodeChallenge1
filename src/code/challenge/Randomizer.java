package code.challenge;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Randomizer implements Runnable{
		private final BlockingQueue<Integer> randomNumberQueue;
		private final BlockingQueue<String> resultQueue;

	    public Randomizer(BlockingQueue<Integer> randomNumberQueue,BlockingQueue<String> resultQueue) {
	        this.randomNumberQueue = randomNumberQueue;
	        this.resultQueue = resultQueue;
	    }

		@Override
		public void run() {
			
			try {
				int number = generateRandomNumber();// generating random number 
				randomNumberQueue.put(number);//storing random number in a queue
			} catch (InterruptedException e) {
				e.printStackTrace();//printing exception stack on console
			}
			if(resultQueue.size() > 0 && resultQueue!=null){ //if the queue is not null & size more then zero
				try {
					String result = resultQueue.take();//take out the top most element from queue
					String[] arr = result.split("@");//split the message by comma
					if(arr!=null && arr.length >0){//if the String array is not null & size of the array is not zero
						System.out.println("Generated Number is "+arr[0]+" ----->Is Prime : "+arr[1]);//printing the number and result in a console
					}
				} catch (InterruptedException e) {
					e.printStackTrace();//printing the exception stack trace in a console
				}
				
			}//end if

			//executing while loop forever
			while(true){
				try {
					Thread.sleep(1000);//Making the thread to sleep for 1 sec
				} catch (InterruptedException e) {
					e.printStackTrace();//printing the exception stack trace on a console
				}
				run();//calling run method
			}//end while
		}//end of run method
		
		//Method to generate Random number between 1 and 1000
				public  int generateRandomNumber() {
					Random rand = new Random();
					return ( 1 + rand.nextInt((1000 - 1) + 1));
				}

}
