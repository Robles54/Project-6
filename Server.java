import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketImpl;

import javafx.application.Application;
import javafx.stage.Stage;

public class Server extends Application{
	int LISTENING_PORT = 32007;
	private ServerSocket listener;

	@Override
	public void start(Stage primaryStage) {
		startServer();
	}

	public static void main(String[] args) {
		System.out.println("Starting Server...");
		launch(args);
	}

	private void startServer() {
		try {
			listener = new ServerSocket(LISTENING_PORT);
			System.out.println(LISTENING_PORT);
			while(true) {
				Socket client;
				Socket client2;
				client = listener.accept();
				System.out.println("client1 connected");
				client2 = listener.accept();
				System.out.println("client2 connected");
				WorkerThread worker = new WorkerThread(client, client2);
				worker.start();
			}
		} catch (Exception e) {
			System.out.println("Server error: " + e);
		}
	}
}





