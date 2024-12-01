

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.control.*;

public class PrimaryScene extends Application {
	protected Scene scene;
	Button send = new Button("Send Coor");
	Button receive = new Button("Receive Coor");
	TextField xField = new TextField();
	TextField yField = new TextField();
	Socket socket;
	String[] ships = ShipsScene.getShips();
	private GraphicsContext graphicsContext;



	public void drawPicture(GraphicsContext g, int width, int height) {
		g.strokeLine(50, 200, 550, 200);
		g.strokeLine(50, 400, 550, 400);
		g.strokeLine(200, 50, 200, 550);
		g.strokeLine(400, 50, 400, 550);
		g.strokeLine(50, 100, 550, 100);
		g.strokeLine(100, 50, 100, 550);
		g.strokeLine(50, 300, 550, 300);
		g.strokeLine(300, 50, 300, 550);
		g.strokeLine(500, 50, 500, 550);
		g.strokeLine(50, 500, 550, 500);
		g.strokeText("1", 130, 40);
		g.strokeText("2", 230, 40);
		g.strokeText("3", 330, 40);
		g.strokeText("4", 430, 40);
		g.strokeText("1", 30, 130);
		g.strokeText("2", 30, 230);
		g.strokeText("3", 30, 330);
		g.strokeText("4", 30, 430);

	}

	public void drawShips() {
		if (ships != null) {
			for (String coordinate : ships) {
				String[] parts = coordinate.split(",");
				try {
					int col = Integer.parseInt(parts[0]);
					int row = Integer.parseInt(parts[1]);
					int xPos = 50 + (col - 1) * 100;
					int yPos = 50 + (row - 1) * 100;
					graphicsContext.strokeOval(xPos + 75, yPos + 75, 50, 50);
				} catch (NumberFormatException e) {
					System.out.println("Warning: Invalid number format in coordinate: " + coordinate);
				}
			}
		}
	}

	public PrimaryScene() {
		initializeScene();
	}

	public void initializeScene() {
		int width = 600;
		int height = 600;
		Canvas canvas = new Canvas(width, height);
		graphicsContext = canvas.getGraphicsContext2D();
		drawPicture(graphicsContext, width, height);
		HBox controlBar = new HBox(send, xField, yField, receive);
		controlBar.setAlignment(Pos.CENTER);
		BorderPane root = new BorderPane();
		send.setOnAction(e -> sendServer());
		receive.setOnAction(e -> receiveServer());
		root.setCenter(canvas);
		root.setBottom(controlBar);
		root.setStyle("-fx-border-width: 4px; -fx-border-color: #444");
		scene = new Scene(root);
	}

	public void setShips(String[] newShips) {
		this.ships = newShips;
		drawShips();
	}

	public void resetSocket() {
		this.socket = SceneManager.getSocket();
		if (socket == null) {
			System.out.println("Error: Socket is null in PrimaryScene.");
		} else {
			System.out.println("Socket initialized in PrimaryScene: " + socket);
		}
	}

	public void sendServer() {
		try {
			if (socket != null) {
				PrintWriter outgoing = new PrintWriter(socket.getOutputStream());
				outgoing.println("SENDCOOR");
				String xCoor = xField.getText();
				String yCoor = yField.getText();
				outgoing.println(xCoor);
				outgoing.println(yCoor);
				outgoing.flush();
				System.out.println("SENT COOR");
			} else {
				System.out.println("Socket is null, cannot send coordinates.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void receiveServer() {
		try {
			if (socket != null) {
				BufferedReader incoming = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String coor = incoming.readLine();
				System.out.println("Received from server: " + coor);
				int count=0;
				if(ships[0].equals(coor)||ships[1].equals(coor)||ships[2].equals(coor)) {
					System.out.println("HERE2");
					String[] parts = coor.split(",");
					int col = Integer.parseInt(parts[0]);
					int row = Integer.parseInt(parts[1]);
					int xPos = 50 + (col - 1) * 100;
					int yPos = 50 + (row - 1) * 100;
					graphicsContext.setFill(Color.RED);
					graphicsContext.fillOval(xPos + 75, yPos + 75, 50, 50);
				}
				else {
					System.out.println("HERE3");
					String[] parts = coor.split(",");
					int col = Integer.parseInt(parts[0]);
					int row = Integer.parseInt(parts[1]);
					int xPos = 50 + (col - 1) * 100;
					int yPos = 50 + (row - 1) * 100;
					graphicsContext.setFill(Color.BLACK);
					graphicsContext.fillOval(xPos + 75, yPos + 75, 50, 50);
				}
			} else {
				System.out.println("Socket is null, cannot receive coordinates.");
			}
		} catch (Exception e) {
			System.out.println("Error receiving coordinate: " + e);
		}
	}


	public Scene getScene() {
		if (scene == null) {
			initializeScene();
		}
		return scene;
	}

	@Override
	public void start(Stage stage) {
		stage.setScene(getScene());
		stage.show();
		stage.setResizable(false);
	}
}
