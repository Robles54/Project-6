import java.net.Socket;

import javafx.stage.Stage;

public class SceneManager {
	private static Socket connection;
	private static Stage stage;

	private static StartingScene startingScene;
	private static SettingsScene settingsScene;
	private static PrimaryScene primaryScene;
	private static SceneType currentSceneType;
	private static SceneType lastSceneType;

	public enum SceneType {
		start, settings, primary
	}

	public SceneManager() {
		startingScene = new StartingScene();
		primaryScene = new PrimaryScene();
		settingsScene = new SettingsScene();
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
			stage.setScene(primaryScene.getScene());
			break;
		}
	}

	public static SceneType getLastScene() {return lastSceneType;}
}
