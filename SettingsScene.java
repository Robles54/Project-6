import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.HashMap;

public class SettingsScene extends SceneBasic {
    private Label errorMessage = new Label();
    private UserInput usernameInput; // User can type their username here
    private ComboBox<String> colorDropdown;
    private Color currentColor = Color.RED;
    private final File settingsFile = new File("settings.xml");
    private static Color selectedColor = Color.RED;

    public SettingsScene() {
        super("Settings");

        usernameInput = new UserInput("Username:");

        ComboBox <String> colorDropdown = new ComboBox<>();
        Label colorLabel = new Label("Select a Color:");
        Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.YELLOW, Color.BLACK, Color.WHITE};
        String[] colorNames = {"Red", "Green", "Blue", "Cyan", "Magenta", "Yellow", "Black", "White"};
        colorDropdown.getItems().addAll(colorNames);
        colorDropdown.setValue("Red");
//        colorDropdown.setOnAction(e -> currentColor = colors[colorDropdown.getSelectionModel().getSelectedIndex()]);
        colorDropdown.setOnAction(e -> {
            int selectedIndex = colorDropdown.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < colors.length) {
                selectedColor = colors[selectedIndex];
            }
        });

        ButtonBar bar = new ButtonBar();
        bar.addButton("Save", e -> apply());
        bar.addButton("Back", e -> SceneManager.setScene(SceneManager.SceneType.start));

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(usernameInput, colorLabel, colorDropdown, bar, errorMessage);
        root.getChildren().add(layout);
    }

    private void apply() {
        String username = usernameInput.getText().trim();
        if (username.isEmpty()) {
            errorMessage.setText("Username cannot be empty.");
            return;
        }

        saveSettings(username, currentColor);
        errorMessage.setText("Settings saved for user: " + username);
        System.out.println("Settings applied for user: " + username);
        System.out.println("Selected Color: " + currentColor);
    }

    private void saveSettings(String username, Color color) {
        try {
            HashMap<String, String> userSettings = loadSettings();

            userSettings.put(username, color.toString());

            writeSettingsToFile(userSettings);
        } catch (Exception e) {
            errorMessage.setText("Error saving settings.");
            e.printStackTrace();
        }
    }
    
    public static Color getSelectedColor() {
    	return selectedColor;
    }

    private HashMap<String, String> loadSettings() {
        HashMap<String, String> settings = new HashMap<>();
        if (!settingsFile.exists()) return settings;

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(settingsFile);
            doc.getDocumentElement().normalize();

            NodeList userList = doc.getElementsByTagName("user");
            for (int i = 0; i < userList.getLength(); i++) {
                Node node = userList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element userElement = (Element) node;
                    String username = userElement.getElementsByTagName("name").item(0).getTextContent();
                    String color = userElement.getElementsByTagName("color").item(0).getTextContent();
                    settings.put(username, color);
                }
            }
        } catch (Exception e) {
            errorMessage.setText("Error loading settings.");
            e.printStackTrace();
        }
        return settings;
    }

    private void writeSettingsToFile(HashMap<String, String> settings) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(settingsFile))) {
            writer.write("<settings>\n");
            for (String username : settings.keySet()) {
                String color = settings.get(username);
                writer.write("  <user>\n");
                writer.write("    <name>" + username + "</name>\n");
                writer.write("    <color>" + color + "</color>\n");
                writer.write("  </user>\n");
            }
            writer.write("</settings>");
        } catch (IOException e) {
            errorMessage.setText("Error writing settings to file.");
            e.printStackTrace();
        }
    }
}
