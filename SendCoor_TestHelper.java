import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SendCoor_TestHelper {
    private static final int LISTENING_PORT = 32007;

    public static void main(String[] args) {
        try {
            ServerSocket listener = new ServerSocket(LISTENING_PORT);
            System.out.println("Listening on port: " + LISTENING_PORT);
            Socket client1 = listener.accept();
            System.out.println("Client 1 connected");
            Socket client2 = listener.accept();
            System.out.println("Client 2 connected");
            WorkerThread worker = new WorkerThread(client1, client2);
            BufferedReader incoming1 = new BufferedReader(new InputStreamReader(client1.getInputStream()));
            PrintWriter outgoing2 =  new PrintWriter(client2.getOutputStream());
            worker.sendCoor(incoming1, outgoing2);
            listener.close();
        } catch (Exception e) {
            System.out.println("Server error: " + e);
        }
    }
}
