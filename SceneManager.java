import java.net.Socket;

import javafx.stage.Stage;

public class SceneManager {
	private static Socket connection;
	private static Stage stage;

	private static StartingScene startingScene;
	private static SettingsScene settingsScene;
	private static PrimaryScene primaryScene = null;  // Store a single instance of PrimaryScene
	private static ShipsScene shipsScene;
	private static SceneType currentSceneType;
	private static SceneType lastSceneType;

	public enum SceneType {
		start, settings, primary, ships, lose, win
	}

	public SceneManager() {
		startingScene = new StartingScene();
		primaryScene = new PrimaryScene();
		settingsScene = new SettingsScene();
		shipsScene = new ShipsScene();
	}

	public static synchronized void setSocket(Socket setConnection) {
		connection = setConnection;
	}

	public static synchronized Socket getSocket() {
		return connection;
	}

	public void setStage(Stage setStage) {
		stage = setStage;
	}

	public static void setScene(SceneType sceneType) {
		lastSceneType = currentSceneType;
		currentSceneType = sceneType;
		switch (sceneType) {
		case start:
			stage.setScene(startingScene.getScene());
			break;
		case settings:
			stage.setScene(settingsScene.getScene());
			break;
		case primary:
			if (primaryScene == null) {
				primaryScene = new PrimaryScene();
			}
			primaryScene.resetSocket();
			primaryScene.setShips(ShipsScene.getShips());
			stage.setScene(primaryScene.getScene());
			break;
		case ships:
			stage.setScene(shipsScene.getScene());
			break;
		}
	}

	public static SceneType getLastScene() {return lastSceneType;}
}
