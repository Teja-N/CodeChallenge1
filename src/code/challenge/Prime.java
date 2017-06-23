package code.challenge;

import java.util.concurrent.BlockingQueue;

public class Prime implements Runnable {
	private final BlockingQueue<Integer> randomNumberQueue; //randomNumberQueue for random numbers
	private final BlockingQueue<String> resultQueue; // resultQueue for prime numbers

    public Prime(BlockingQueue<Integer> randomNumberQueue,BlockingQueue<String> resultQueue) {
        this.randomNumberQueue = randomNumberQueue; 
        this.resultQueue = resultQueue;
    }

	@Override
	public void run() {
		if(randomNumberQueue != null && randomNumberQueue.size() >0){
			try {
				int number =randomNumberQueue.take(); //take out the top most element from randomNumberQueue
				if(isPrime(number)){//if the passed element is prime number
					resultQueue.put(number+"@"+true);//storing message in shared queue
				}else{
					resultQueue.put(number+"@"+false);//storing message in shared queue
				}
			} catch (InterruptedException e) {
				e.printStackTrace();//printing exception stack on console
			}
		}
		//executing run method forever
		while(true){
			try {
				Thread.sleep(1000);//making to sleep the Thread for 1 sec
			} catch (InterruptedException e) {
				e.printStackTrace();//printing the exception stack trace in console
			}
			run();
		}
	}
	// method to check if the number is prime or not
	public boolean isPrime(int n) {
        //check if n is a multiple of 2
        if (n%2==0) return false;
        //if not, then just check the odds
        for(int i=3;i*i<=n;i+=2) {
            if(n%i==0)
                return false;
        }
        return true;
    }

}
