import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SendCoor_Test{

	@Test
	public void testSendCoor() {
		try{
			Socket client1 = new Socket("localhost", 32007);
			Socket client2 = new Socket("localhost", 32007);
			PrintWriter outgoing = new PrintWriter(client1.getOutputStream(), true);
			outgoing.println("3");
			outgoing.println("4");
			BufferedReader incoming = new BufferedReader(new InputStreamReader(client2.getInputStream()));
			String received = incoming.readLine();
			assertEquals("3,4", received, "Client 2 should receive coordinates '3,4'.");
		} catch (Exception e) {
			fail("Error during test: " + e.getMessage());
		}
	}
}
