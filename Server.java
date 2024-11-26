import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private static int LISTENING_PORT = 32007;

	public static void main(String[] args) {
		ServerSocket listener;
		Socket client;
		try {
			listener = new ServerSocket(LISTENING_PORT);
			System.out.println("Listening on port " + LISTENING_PORT);
			while (true) {
				Socket client1;
				client1 = listener.accept();
				Socket client2;
				client2 = listener.accept();
				PrintWriter outgoing;
				outgoing = new PrintWriter (client1.getOutputStream());
				outgoing.println("PLAYER 1");
				PrintWriter outgoing2;
				outgoing2 = new PrintWriter(client2.getOutputStream());
				outgoing2.println("PLAYER 2");
				workerThread worker = new workerThread(client1, client2);
				worker.start();
			}
		}
		catch (Exception e) {
			System.out.println("There was an error: " + e);
		}

	}

}
