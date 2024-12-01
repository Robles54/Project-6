import javafx.scene.control.Button;

public class ShipsScene extends SceneBasic{
	private UserInput x1;
	private UserInput y1;
	private UserInput x2;
	private UserInput y2;
	private UserInput x3;
	private UserInput y3;
	private static String[] shipsLoc = new String[3];

	public ShipsScene() {
		super("Add Ships");
		x1 = new UserInput("1st x:");
		y1 = new UserInput("1st y:");
		x2 = new UserInput("2nd x:");
		y2 = new UserInput("2nd y:");
		x3 = new UserInput("3rd x:");
		y3 = new UserInput("3rd y:");
		root.getChildren().addAll(x1, y1, x2, y2, x3, y3);
		addButton("Save", e -> saveShips());
	}
	
	public void saveShips() {
		shipsLoc[0] = x1.getText() + "," + y1.getText();
		shipsLoc[1] = x2.getText() + "," + y2.getText();
		shipsLoc[2] = x3.getText() + "," + y3.getText();
		PrimaryScene primaryScene = new PrimaryScene();
	    primaryScene.setShips(shipsLoc);
	    SceneManager.setScene(SceneManager.SceneType.start);
	}
	
	public static String[] getShips() {
		return shipsLoc;
	}
}
