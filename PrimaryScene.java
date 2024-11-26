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

public class PrimaryScene extends Application{
    protected Scene scene;
	Button red = new Button("Red");
	Button green = new Button("Green");
	TextField xField = new TextField();
	TextField yField = new TextField();

	public void drawPicture(GraphicsContext g, int width, int height) {
		g.strokeLine(50, 200, 550, 200);
		g.strokeLine(50, 400, 550, 400);
		g.strokeLine(200, 50, 200, 550);
		g.strokeLine(400, 50, 400, 550);
	}

	public void start(Stage stage) {
		int width = 600;
		int height = 600;
		Canvas canvas = new Canvas(width,height);
		drawPicture(canvas.getGraphicsContext2D(), width, height) ;
        
		// Set up controls and add to BorderPane layout 
		red.setOnAction( e -> drawRed(canvas.getGraphicsContext2D()));
		green.setOnAction(e-> drawGreen(canvas.getGraphicsContext2D()));
		HBox controlBar = new HBox(red, green, xField, yField );
		controlBar.setAlignment(Pos.CENTER);
		BorderPane root = new BorderPane();
		root.setCenter(canvas);
		root.setBottom(controlBar);

		// Get ready to show the GUI
		root.setStyle("-fx-border-width: 4px; -fx-border-color: #444");
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Tic Tac Toe"); // STRING APPEARS IN WINDOW TITLEBAR!
		stage.show();
		stage.setResizable(false);
	} 

	public void drawRed(GraphicsContext g) {
		int x = Integer.valueOf(xField.getText());
        int y = Integer.valueOf(yField.getText());
		int centerX = x*200+100;    // The x-coord of the center of a disk.
		int centerY = y*200+100; // The y-coord of the center of a disk.
        g.setFill(Color.RED);
        g.fillOval(centerX-75, centerY-75, 150, 150 );
	}
	
	public void drawGreen(GraphicsContext g) {
		int x = Integer.valueOf(xField.getText());
        int y = Integer.valueOf(yField.getText());
		int centerX = x*200+100;    // The x-coord of the center of a disk.
		int centerY = y*200+100; // The y-coord of the center of a disk.
        g.setFill(Color.GREEN);
        g.fillOval(centerX-75, centerY-75, 150, 150 );
	}
	
    public Scene getScene() {
        return scene;
    }
}

