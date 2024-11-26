/* Author: Christopher Robles. For Project #5.
   Description: Manages changes from one scene to another scene.
   Usage: The setScene() method should be called using a SceneType enumerated constant. Examples:
   
   SceneManager.setScene(SceneManager.SceneType.login);
   SceneManager.setScene(SceneManager.SceneType.admin);
   SceneManager.setScene(SceneManager.SceneType.customer);
   SceneManager.setScene(SceneManager.SceneType.changePassword);
   SceneManager.setScene(SceneManager.SceneType.accountList);
   SceneManager.setScene(SceneManager.SceneType.profile);
   SceneManager.setScene(SceneManager.SceneType.settings);
   SceneManager.setScene(SceneManager.SceneType.placeOrder);
   SceneManager.setScene(SceneManager.SceneType.viewOrders);
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class SceneManager {
    private static Socket connection; // Socket connection to server
	public static enum SceneType {start, primary, settings} // Custom data type for scene selection
	private static HashMap<SceneType, SceneBasic> scenes = new HashMap<SceneType, SceneBasic>(); // Lookup table for retrieving scene objects
	private static Stage stage; // Stage used for all scenes
	private static BufferedReader incoming;
	private static PrintWriter outgoing;

	// Constructor
	public SceneManager() {
		scenes.put(SceneType.start, new StartingScene());
		scenes.put(SceneType.primary, new PrimaryScene());
		scenes.put(SceneType.settings, new SettingsScene());
	}
	
	// Set socket connection to server
	public static void setSocket(Socket setConnection) {
		connection = setConnection;
		
		try {
			incoming = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			outgoing = new PrintWriter(connection.getOutputStream(), true);
		} catch (IOException e) {
			System.out.println("Error: " + e);
		}
	}
	
	public static BufferedReader getIncoming() {
		return incoming;
	}
	
	public static PrintWriter getOutgoing() {
		return outgoing;
	}

	// Get socket connection to server
	public static Socket getSocket() {
		return connection;
	}

	// Set initial stage to be used by all scenes
	public void setStage(Stage stage) {
		SceneManager.stage = stage;
	}
	
	// Change view to selected scene
	public static void setScene(SceneType type) {
		if (type == SceneType.start)
			((StartingScene) scenes.get(type)).getStartingScene(); // Make AccountListScene request account list from server
		else if (type == SceneType.primary)
			((PrimaryScene) scenes.get(type)).getPrimaryScene(); // Make AccountListScene request account list from server

		stage.setScene(scenes.get(type).getScene()); // Switch to the selected scene
	}
}