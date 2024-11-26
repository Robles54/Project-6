import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class workerThread extends Thread {
	private Socket client1;
	private Socket client2;
	private BufferedReader incoming1;
	private PrintWriter outgoing1;
	private BufferedReader incoming2;
	private PrintWriter outgoing2;
	
	public workerThread(Socket client1, Socket client2) {
		this.client1 = client1;
		this.client2 = client2;
		System.out.println("Sockets loaded");
	}
	
	public void run() {
		try {
			System.out.println("CONNECTED");
			outgoing1 = new PrintWriter(client1.getOutputStream());
			outgoing2 = new PrintWriter(client2.getOutputStream());
			incoming1 = new BufferedReader(new InputStreamReader(client1.getInputStream()));
			incoming2 = new BufferedReader(new InputStreamReader(client2.getInputStream()));
			
			System.out.println("Waiting for Request");
			String request1 = incoming1.readLine();
			String request2 = incoming2.readLine();
			System.out.println("Request from the first client: " + request1);
			System.out.println("Request from the second client: " + request2);
			
			//While loops for cases....
			
			outgoing1.flush();
			outgoing2.flush();
			request1 = incoming1.readLine();
			request2 = incoming2.readLine();
			
			
			
		} catch (IOException e) {
			System.out.println("Error in the workerThread: " + e);
		} finally {
			try {
				client1.close();
				client2.close();
			} catch (IOException e) {
				System.out.println("Error closing the clients: " + e);
			}
		}
	}
}
