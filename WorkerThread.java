import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class WorkerThread extends Thread{
	private Socket client;
	private Socket client2;
	PrintWriter outgoing1;
	PrintWriter outgoing2;
	BufferedReader incoming1;
	BufferedReader incoming2;

	public WorkerThread(Socket client, Socket client2) {
		this.client=client;
		this.client2=client2;
		try {
			outgoing1 = new PrintWriter(client.getOutputStream());
			outgoing2 = new PrintWriter(client2.getOutputStream());
			incoming1 = new BufferedReader(new InputStreamReader(client.getInputStream()));
			incoming2 = new BufferedReader(new InputStreamReader(client2.getInputStream()));

			//			outgoing.println("PLAYER 1");
			//			outgoing2.println("PLAYER 2");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			System.out.println("HERE");
			while(true) {
				if(incoming1.readLine() != null) {
					sendCoor(incoming1, outgoing2);
				}

				if(incoming2.readLine() != null) {
					sendCoor(incoming2, outgoing1);
				}
			}
		}catch (IOException e) {
			System.out.println("Error in client thread: " + e);
		}

	}

	public void sendCoor(BufferedReader coor, PrintWriter sent) {
		try {
			System.out.println("HERE1");
			String x = coor.readLine();
			String y = coor.readLine();
			sent.println(x+","+y);
			sent.flush();
			System.out.println("SERVER SENT COOR");
		}catch (IOException e) {
			System.out.println("Error reading coordinate: " + e);
		}
	}
}
